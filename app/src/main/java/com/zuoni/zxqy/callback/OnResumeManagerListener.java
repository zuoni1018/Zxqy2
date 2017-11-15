package com.zuoni.zxqy.callback;

import com.zuoni.zxqy.bean.model.ResumeManager;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface OnResumeManagerListener {
    void onClick01(ResumeManager resume, int position);
    void onClick02(ResumeManager resume, int position);
    void onClick03(ResumeManager resume, int position);
    void onClick04(ResumeManager resume, int position);
}