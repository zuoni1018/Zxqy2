package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
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
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvNearbyPositionAdapter;
import com.zuoni.zxqy.callback.OnBMapLocationListener;
import com.zuoni.zxqy.ui.activity.base.BMapLocationBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompanyAddressActivity extends BMapLocationBaseActivity implements OnBMapLocationListener {

    @BindView(R.id.layoutLeft)
    RelativeLayout layoutLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
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
        tvTitle.setText("查找门店");
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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                baiduMap = getBaiduMap();
                setLocationMode(MyLocationConfiguration.LocationMode.FOLLOWING);
                setLocationMode(MyLocationConfiguration.LocationMode.NORMAL);
                closeLoading();
                mList = new ArrayList<>();
                mRecyclerView.setPullRefreshEnabled(false);
                mAdapter = new LRecyclerViewAdapter(new RvNearbyPositionAdapter(getContext(), mList));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                mRecyclerView.setAdapter(mAdapter);
            }
        }, 2000);

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

    //收到定位结果了
    @Override
    public void onGetLatLng(LatLng latLng) {
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
                    LogUtil.i("附近点数量===" + mPoiList.size());
                    mList.clear();
                    mList.addAll(mPoiList);
                    LogUtil.i("附近点数量2===" + mList.size());
                    mAdapter.notifyDataSetChanged();
                }

                mSearch.destroy();
            }
        });
        // 反Geo搜索
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));


////        search("");
//        //搜索该附近的POI
//        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword("写字楼")
//                .sortType(PoiSortType.distance_from_near_to_far)
//                .location(latLng)
//                .radius(10000)
//                .pageNum(10);
//        PoiSearch mPoiSearch = PoiSearch.newInstance();
//
//
//
//        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
//            public void onGetPoiResult(PoiResult result) {
//                Message message=Message.obtain();
//                handler.sendMessage(message);
//                //获取POI检索结果
//                result.getAllPoi();
//                //这里是异步操作
//                for (int i = 0; i < result.getAllPoi().size(); i++) {
//                    PoiInfo mPoiInfo = result.getAllPoi().get(i);
//                    LogUtil.i("检索结果" + mPoiInfo.address + mPoiInfo.name + mPoiInfo.describeContents());
//                }
//            }
//
//            public void onGetPoiDetailResult(PoiDetailResult result) {
//                //获取Place详情页检索结果
//            }
//
//            @Override
//            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//            }
//        };
//        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//        mPoiSearch.searchNearby(nearbySearchOption);
////        PoiNearbySearchOption option=new PoiNearbySearchOption();
////        option.location(latLng);
////        option.keyword("写字楼");
////        mPoiSearch.searchNearby(option);
    }
}
