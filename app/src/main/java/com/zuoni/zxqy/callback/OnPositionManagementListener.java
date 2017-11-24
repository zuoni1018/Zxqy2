package com.zuoni.zxqy.callback;

import com.zuoni.zxqy.bean.model.Job;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface OnPositionManagementListener {
    void onClick01(Job job ,int position);
    void onClick02(Job job ,int position);
    void onClick03(Job job ,int position);
    void onClick04(Job job ,int position);
    void onClick05(Job job ,int position);
}