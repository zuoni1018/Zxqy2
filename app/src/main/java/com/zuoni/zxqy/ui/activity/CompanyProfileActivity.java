package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class CompanyProfileActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_company_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("公司简介");
        ButterKnife.bind(this);
    }
}
