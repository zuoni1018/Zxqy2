package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class RegisterActivity extends BaseTitleActivity {
    @BindView(R.id.btSure)
    Button btSure;

    @Override
    public int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        ButterKnife.bind(this);
        setTitle("注册");
    }

    @OnClick({R.id.btSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSure:
                jumpToActivity(PersonalInformationActivity.class);
                break;
        }
    }
}
