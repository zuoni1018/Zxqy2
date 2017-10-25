package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class OnlineComplaintsActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_online_complaints;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("在线投诉留言");

    }
}
