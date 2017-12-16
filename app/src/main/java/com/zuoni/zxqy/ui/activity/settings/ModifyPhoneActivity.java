package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.GlobalVariable;
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
 * Created by zangyi_shuai_ge on 2017/10/30
 */

public class ModifyPhoneActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.bt03)
    Button bt03;
    @BindView(R.id.tv01)
    TextView tv01;


    private boolean isFirstStep = false;
    private CountDownTimer timer;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_modify_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改手机");
        timer = new CountDownTimer(GlobalVariable.CountTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int nowTime = (int) (millisUntilFinished / 1000);
                tv02.setText(nowTime + "秒后重新获取");
            }

            @Override
            public void onFinish() {
                tv02.setClickable(true);
                tv02.setBackgroundResource(R.drawable.bg_get_code_01);
                tv02.setText("获取验证码");
                tv02.setTextColor(getResources().getColor(R.color.get_code_text_01));
            }
        };


        isFirstStep = getIntent().getBooleanExtra("isFirstStep", false);

        if (isFirstStep) {
            et01.setHint("请输入旧手机号");
            bt03.setText("下一步");
            String phone = getIntent().getStringExtra("phone");
            et01.setText(phone);
            et01.setEnabled(false);
        } else {
            et01.setHint("请输入新手机号");
            bt03.setText("确认");
        }

    }


    private void getCode(String phone) {
        HttpRequest httpRequest = new HttpRequest(AppUrl.VERIFY);//获取验证码
        httpRequest.add("phone", phone);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("获取验证码" + response);
                BaseHttpResponse baseHttpResponse = gson.fromJson(response, BaseHttpResponse.class);
                showToast(baseHttpResponse.getMessage());
                if (baseHttpResponse.getStatus().equals("true")) {
                    tv02.setClickable(false);
                    tv02.setBackgroundResource(R.drawable.bg_get_code_02);
                    tv02.setTextColor(getResources().getColor(R.color.get_code_text_02));
                    timer.start();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                LogUtil.i("获取验证码" + exception);
            }
        }, getContext());
    }

    private void alter_phone(final String phone, String code) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.alter_phone);//换手机手机
        httpRequest.add("verify", code);
        httpRequest.add("phoneNew", phone);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("换手机手机" + response);
                BaseHttpResponse baseHttpResponse = gson.fromJson(response, BaseHttpResponse.class);
                if (baseHttpResponse.getStatus().equals("true")) {
                    AppSetting.phone=phone;
                    myFinish();
                } else {
                    showToast(baseHttpResponse.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("验证旧手机" + exception);
            }
        }, getContext());
    }

    private void check_phone(String phone, String code) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.check_phone);//验证旧手机
        httpRequest.add("verify", code);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("验证旧手机" + response);
                BaseHttpResponse baseHttpResponse = gson.fromJson(response, BaseHttpResponse.class);
                if (baseHttpResponse.getStatus().equals("true")) {
                    myFinish();
                    Intent mIntent = new Intent(getContext(), ModifyPhoneActivity.class);
                    mIntent.putExtra("isFirstStep", false);
                    startActivity(mIntent);
                } else {
                    showToast(baseHttpResponse.getMessage());
                }


            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("验证旧手机" + exception);
            }
        }, getContext());
    }

    @OnClick({R.id.tv02, R.id.bt03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv02:
                //获取验证码
                //发送验证码按钮
                String phone = et01.getText().toString();
                if (phone.equals("")) {
                    showToast("请输入手机号码");
                } else {
                    //获取验证码
                    getCode(phone);
                }

                break;
            case R.id.bt03:
                if (isFirstStep) {
                    String code = et02.getText().toString().trim();
                    if (code.equals("")) {
                        showToast("请输入验证码");
                    } else {
                        check_phone(et01.getText().toString().trim(), code);
                    }
                } else {
                    String phone1 = et01.getText().toString().trim();
                    String code = et02.getText().toString().trim();
                    if (phone1.equals("") | code.equals("")) {
                        showToast("请输入手机号或者验证码");
                    } else {
                        alter_phone(phone1, code);

                    }

                }
                break;
        }
    }
}
