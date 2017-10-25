package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class EssentialInformationActivity extends BaseTitleActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_essential_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("基本信息");

    }
}
