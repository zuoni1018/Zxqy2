package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.ui.activity.LoginActivity;
import com.zuoni.zxqy.ui.activity.base.ActivityCollector;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class OtherFunctionsActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_other_functions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("其他功能");
    }

    @OnClick({R.id.layout1, R.id.layout2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                //修改资料
                jumpToActivity(ModifyDataActivity.class);
                break;
            case R.id.layout2:
                //登出
                NIMClient.getService(AuthService.class).logout();
                CacheUtils.setAccount("",getContext());
                //销毁界面
                ActivityCollector.finishAll();
                CacheUtils.setLogin(false,getContext());
                Intent mIntent=new Intent(getContext(), LoginActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
