package com.zuoni.zxqy.callback;

import com.zuoni.zxqy.bean.model.Contact;

/**
 * Created by zangyi_shuai_ge on 2017/7/1
 * 双列滚轮监听
 */

public interface ContactManagerListener {
    void onClickListener(Contact contact ,int position);
}