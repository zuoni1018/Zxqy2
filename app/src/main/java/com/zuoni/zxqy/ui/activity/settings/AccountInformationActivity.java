package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.CompanyInfo;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class AccountInformationActivity extends BaseTitleActivity {

    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.layout3)
    LinearLayout layout3;

    private CompanyInfo companyInfo;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("账号信息");
        companyInfo = CacheUtils.getCompanyInfo(getContext());

        tv01.setText(companyInfo.getCname());
//        tv02.setText(companyInfo.getPhone());
//        tv03.setText(companyInfo.getEmail());
        AppSetting.phone = companyInfo.getPhone();
        AppSetting.email = companyInfo.getEmail();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv02.setText(AppSetting.phone);
        tv03.setText(AppSetting.email);

        if(!AppSetting.email.equals(companyInfo.getEmail())){
            companyInfo.setEmail(AppSetting.email);
        }
        if(!AppSetting.phone.equals(companyInfo.getPhone())){
            companyInfo.setPhone(AppSetting.phone);
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_account_information;
    }

    @OnClick({R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                break;
            case R.id.layout2:
                //修改手机号码;
                mIntent = new Intent(getContext(), ModifyPhoneActivity.class);
                mIntent.putExtra("isFirstStep", true);
                mIntent.putExtra("phone", companyInfo.getPhone());
                startActivity(mIntent);
                break;
            case R.id.layout3:
                //修改email
                mIntent = new Intent(getContext(), ModifyEmailActivity.class);
                mIntent.putExtra("email", companyInfo.getEmail());
                startActivity(mIntent);
                break;
            case R.id.layout4:
                //修改密码
                jumpToActivity(ModifyPasswordActivity.class);
                break;
        }
    }
}
