package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;
import android.view.View;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ModifyDataActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_modify_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改资料");
    }

    @OnClick({R.id.layout1, R.id.layout2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                //联系人管理
                jumpToActivity(ContactManagerActivity.class);
                break;
            case R.id.layout2:
                //修改密码
                jumpToActivity(ModifyPasswordActivity.class);
                break;
        }
    }
}
