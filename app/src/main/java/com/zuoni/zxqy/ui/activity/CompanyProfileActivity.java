package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
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
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class CompanyProfileActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.bt01)
    Button bt01;

    private boolean isSetPersonalInfo = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_company_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("公司简介");
        ButterKnife.bind(this);


        isSetPersonalInfo = getIntent().getBooleanExtra("isSetPersonalInfo", false);
        String text = getIntent().getStringExtra("text");
        et01.setText(text);
        et01.setSelection(et01.getText().length());

    }

    @OnClick(R.id.bt01)
    public void onViewClicked() {

        if (isSetPersonalInfo) {
            Intent mIntent = new Intent();
            mIntent.putExtra("text", et01.getText().toString());
            setResult(10088, mIntent);
            myFinish();
        } else {
            String info=et01.getText().toString().trim();
            if(info.equals("")){
                showToast("请输入公司简介");
            }else {
                editInfo(info);
            }
        }
    }

    private void editInfo(String info) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.EDIT_INFO);//修改公司信息
        httpRequest.add("info", info);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改公司信息" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if(info.getStatus().equals("true")){
                    showToast("修改成功");
                    Intent mIntent = new Intent();
                    mIntent.putExtra("text", et01.getText().toString());
                    setResult(10088, mIntent);
                    myFinish();
                }else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("修改公司信息" + exception);
            }
        }, getContext());
    }
}
