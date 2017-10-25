package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class ForgetActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this,0);
        ButterKnife.bind(this);
        setTitle("忘记密码");
    }
}
