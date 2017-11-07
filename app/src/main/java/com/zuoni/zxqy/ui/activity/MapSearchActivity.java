package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvNearbyPositionAdapter;
import com.zuoni.zxqy.callback.ItemOnClickListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/11/3
 */

public class MapSearchActivity extends BaseTitleActivity {
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.layoutLeft)
    RelativeLayout layoutLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    private LatLng latlng;
    private double latitude;
    private double longitude;


    private List<PoiInfo> mList;
    private List<PoiInfo> mList2;
    private LRecyclerViewAdapter mAdapter;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
        }
    };
    @Override
    public int setLayoutId() {
        return R.layout.activity_map_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        latitude = getIntent().getDoubleExtra("latitude", 0.0);
        longitude = getIntent().getDoubleExtra("longitude", 0.0);
        latlng = new LatLng(latitude, longitude);


        mList = new ArrayList<>();
        mList2= new ArrayList<>();
        mRecyclerView.setPullRefreshEnabled(false);
        RvNearbyPositionAdapter rvNearbyPositionAdapter=new RvNearbyPositionAdapter(getContext(), mList);
        rvNearbyPositionAdapter.setItemOnClickListener(new ItemOnClickListener() {
            @Override
            public void onClickListener(int pos) {

                Intent mIntent=new Intent();
                mIntent.putExtra("text",mList.get(pos).name);
                setResult(10087,mIntent);
                myFinish();
            }
        });

        mAdapter = new LRecyclerViewAdapter(rvNearbyPositionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void starch(String key) {
        //搜索该附近的POI
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(key)
                .sortType(PoiSortType.distance_from_near_to_far)
                .location(latlng)
                .radius(10000)
                .pageNum(20);

        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
            public void onGetPoiResult(PoiResult result) {
                //获取POI检索结果
                Message message= Message.obtain();
                mList.clear();
                if(result.getAllPoi()!=null){
                    mList.addAll(result.getAllPoi());
                }
                mHandler.sendMessage(message);

            }

            public void onGetPoiDetailResult(PoiDetailResult result) {
                //获取Place详情页检索结果
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
        mPoiSearch.searchNearby(nearbySearchOption);

    }


    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        String key = etSearch.getText().toString().trim();
        if (key.equals("")) {
            showToast("请输入搜索内容");
        } else {
            starch(key);
        }
    }
}
