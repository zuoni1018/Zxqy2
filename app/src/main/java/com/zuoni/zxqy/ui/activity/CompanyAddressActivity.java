package com.zuoni.zxqy.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zuoni.common.utils.DensityUtils;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvNearbyPositionAdapter;
import com.zuoni.zxqy.callback.ItemOnClickListener;
import com.zuoni.zxqy.callback.OnBMapLocationListener;
import com.zuoni.zxqy.ui.activity.base.BMapLocationBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CompanyAddressActivity extends BMapLocationBaseActivity implements OnBMapLocationListener {

    @BindView(R.id.layoutLeft)
    RelativeLayout layoutLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tvSearch01)
    TextView tvSearch01;
    @BindView(R.id.layoutSearch)
    LinearLayout layoutSearch;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.layoutMap)
    LinearLayout layoutMap;
    @BindView(R.id.ivLalala)
    ImageView ivLalala;
    private BaiduMap baiduMap;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showToast("看下我是不是异步操作1");
            setTitle("zzzzz");
        }
    };


    private List<PoiInfo> mList;
    private LRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        onBMapLocationListener = this;
        tvTitle.setText("公司地址");
        layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFinish();
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                showLoading();
            }
        });
        mList = new ArrayList<>();
        mRecyclerView.setPullRefreshEnabled(false);
        RvNearbyPositionAdapter rvNearbyPositionAdapter = new RvNearbyPositionAdapter(getContext(), mList);
        rvNearbyPositionAdapter.setItemOnClickListener(new ItemOnClickListener() {
            @Override
            public void onClickListener(int pos) {
                tvSearch01.setText(mList.get(pos).name);
            }
        });

        mAdapter = new LRecyclerViewAdapter(rvNearbyPositionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                baiduMap = getBaiduMap();
                setLocationMode(MyLocationConfiguration.LocationMode.FOLLOWING);
                setLocationMode(MyLocationConfiguration.LocationMode.NORMAL);
                closeLoading();
//                baiduMap.setOnm
                baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
                    @Override
                    public void onMapStatusChangeStart(MapStatus mapStatus) {

                    }

                    @Override
                    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

                    }

                    @Override
                    public void onMapStatusChange(MapStatus mapStatus) {

                    }

                    @Override
                    public void onMapStatusChangeFinish(MapStatus mapStatus) {
                        LogUtil.i("地图移动结束");
                        //跳一下
                        mapMoveFinish();

                    }
                });
            }
        }, 2000);

    }

    private void mapMoveFinish() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivLalala, "translationY", -DensityUtils.dp2px(getContext(), 32),0 );
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(700).start();

        LogUtil.i("地图中心坐标",baiduMap.getMapStatus().target.latitude+"======="+baiduMap.getMapStatus().target.longitude);

        onGetLatLng(baiduMap.getMapStatus().target);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_find_stores;
    }


    @Override
    public void onGetPoiList(List<Poi> list) {

        for (int i = 0; i < list.size(); i++) {
            Poi info = list.get(i);
            LogUtil.i("定位列表:Name" + info.getName() + "Id" + info.getId() + "Rank" + info.getRank() + "" + info.describeContents());
            info.describeContents();
        }

    }

    GeoCoder mSearch;

    private LatLng latLng;

    //收到定位结果了
    @Override
    public void onGetLatLng(LatLng latLng) {
        this.latLng = latLng;
        LogUtil.i("拿到坐标");
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                //反地理编码搜索
                List<PoiInfo> mPoiList = reverseGeoCodeResult.getPoiList();
                if (mPoiList != null) {
                    mList.clear();
                    mList.addAll(mPoiList);
                    mAdapter.notifyDataSetChanged();
                }

                mSearch.destroy();
            }
        });
        // 反Geo搜索
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));


////        search("");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == 10087) {
            String text = data.getStringExtra("text");
            tvSearch01.setText(text);
        }
    }

    @Override
    public void onCity(String city) {
        LogUtil.i("城市999" + city);
        tv01.setText(city);
    }

    @OnClick({R.id.tvRight, R.id.layoutSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRight:
                //保存
                String door = etSearch.getText().toString().trim();
//                if(door.equals("")|tvSearch01.getText().toString().equals("")){
//                    showToast("请输入门牌号码或选择地址");
//                }else {
                String message = tv01.getText().toString() + tvSearch01.getText().toString() + door;
                Intent mIntent = new Intent();
                mIntent.putExtra("message", message);
                setResult(10087, mIntent);
                myFinish();
//                }
                break;
            case R.id.layoutSearch:
                //搜索框
                if (latLng == null) {
                    showToast("定位失败");
                    return;
                }
                Intent mIntent2 = new Intent(getContext(), MapSearchActivity.class);
                mIntent2.putExtra("latitude", latLng.latitude);
                mIntent2.putExtra("longitude", latLng.longitude);
                startActivityForResult(mIntent2, 10086);
                break;
        }
    }
}
