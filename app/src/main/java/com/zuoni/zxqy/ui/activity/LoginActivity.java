package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class LoginActivity extends BaseTitleActivity {
    @BindView(R.id.btSure)
    Button btSure;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        ButterKnife.bind(this);
        setTitle("登录");
    }

    @OnClick({R.id.btSure, R.id.tv1, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSure:
                LoginInfo info = new LoginInfo("123456", "123456"); // config...
                RequestCallback<LoginInfo> callback =
                        new RequestCallback<LoginInfo>() {
                            @Override
                            public void onSuccess(LoginInfo param) {
                                LogUtil.i("onSuccess");
                                showToast("登陆成功");
                                jumpToActivity(MainActivity.class);
                            }

                            @Override
                            public void onFailed(int code) {
                                LogUtil.i("onFailed");
                                showToast("登陆失败");
                            }

                            @Override
                            public void onException(Throwable exception) {

                            }
                        };

                NIMClient.getService(AuthService.class).login(info).setCallback(callback);


                break;
            case R.id.tv1:
                jumpToActivity(RegisterActivity.class);
                break;
            case R.id.tv2:
                jumpToActivity(ForgetActivity.class);
                break;
        }
    }
}
