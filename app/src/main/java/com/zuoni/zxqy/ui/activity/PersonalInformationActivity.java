package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class PersonalInformationActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("个人信息");
    }

    @OnClick({R.id.tvGetAddress, R.id.btSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetAddress:
                jumpToActivity(CompanyAddressActivity.class);
                break;
            case R.id.btSure:
                break;
        }
    }
}
