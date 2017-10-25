package com.zuoni.zxqy.callback;

import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface OnBMapLocationListener {
    void onGetPoiList(List<Poi> list);
    void onGetLatLng(LatLng latLng);
}