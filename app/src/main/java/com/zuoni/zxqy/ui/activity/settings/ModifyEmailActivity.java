package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/11/8
 */

public class ModifyEmailActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.bt02)
    Button bt02;
    @BindView(R.id.tvRight)
    TextView tvRight;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改邮箱");
        tvRight.setText("提交");
        email = getIntent().getStringExtra("email");
        et01.setText(email);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_email;
    }

    @OnClick(R.id.tvRight)
    public void onViewClicked() {
        email = et01.getText().toString().trim();
        if (email.equals("")) {
            showToast("请输入邮箱");
        } else {
            updateCompanyInfo(email);
        }
    }

    private void updateCompanyInfo(final String email) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.UPDATE_COMPANY_INFO);
//        httpRequest.add("contactName", contactName);
//        httpRequest.add("fax", fax);
//        httpRequest.add("tele", tele);
//        httpRequest.add("address", address);
//        httpRequest.add("cateId", cateId);
//        httpRequest.add("cateName", cateId);
//        httpRequest.add("type", type);
//        httpRequest.add("web", web);
//        httpRequest.add("lawer", lawer);
        httpRequest.add("email", email);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改基本信息" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    AppSetting.email = ModifyEmailActivity.this.email;
                    showToast("修改成功");
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("修改基本信息" + exception);
            }
        }, getContext());
    }


}
