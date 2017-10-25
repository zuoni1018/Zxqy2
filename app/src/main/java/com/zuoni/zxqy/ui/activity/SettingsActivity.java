package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.activity.settings.AccountInformationActivity;
import com.zuoni.zxqy.ui.activity.settings.EssentialInformationActivity;
import com.zuoni.zxqy.ui.activity.settings.OtherFunctionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class SettingsActivity extends BaseTitleActivity {
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layout3)
    LinearLayout layout3;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("设置");

    }

    @OnClick({R.id.layout1, R.id.layout2, R.id.layout3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                //账号信息
                jumpToActivity(AccountInformationActivity.class);
                break;
            case R.id.layout2:
                //基本信息
                jumpToActivity(EssentialInformationActivity.class);
                break;
            case R.id.layout3:
                //其他功能
                jumpToActivity(OtherFunctionsActivity.class);
                break;
        }
    }

    @OnClick(R.id.ivCompanyProfile)
    public void onViewClicked() {
        jumpToActivity(CompanyProfileActivity.class);
    }
}
