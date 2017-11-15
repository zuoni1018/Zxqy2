package com.zuoni.zxqy.callback;

import com.zuoni.zxqy.bean.model.Message;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface OnMailBoxListener {
    void onClick01(Message message, int position);
    void onClick02(Message message, int position);
    void onClick03(Message message, int position);
    void onClick04(Message message, int position);
}