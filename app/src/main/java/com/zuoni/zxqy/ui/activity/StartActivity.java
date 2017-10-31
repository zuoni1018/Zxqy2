package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.jaeger.library.StatusBarUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.ui.activity.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class StartActivity extends BaseActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        CountDownTimer timer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if(CacheUtils.isLogin(getContext())){
                    jumpToActivity(MainActivity.class);
                }else {
                    jumpToActivity(LoginActivity.class);
                }
                finish();
            }
        };
        timer.start();

    }
}
