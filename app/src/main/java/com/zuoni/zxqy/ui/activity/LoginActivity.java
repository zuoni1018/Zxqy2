package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zuoni.common.callback.SimpleTextWatcher;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.Login;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class LoginActivity extends BaseTitleActivity {


    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.btSure)
    Button btSure;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;


    private boolean isShow01 = true;
    private CountDownTimer timer;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        ButterKnife.bind(this);
        setTitle("登录");

        //自动填写手机号码
        String phone=CacheUtils.getPhone(getContext());
        if(!phone.equals("")){
            et1.setText(phone);
            iv01.setVisibility(View.VISIBLE);
            isShow01=true;
            et1.setSelection(et1.getText().length());
            if(phone.equals("15168212330")){
                et3.setText("672594");
                et2.setText("123456");
            }
        }



        et1.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    iv01.setVisibility(View.INVISIBLE);
                } else {
                    iv01.setVisibility(View.VISIBLE);
                }

            }
        });


        timer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int nowTime = (int) (millisUntilFinished / 1000);
                tv03.setText(nowTime + "秒后重新获取");
            }

            @Override
            public void onFinish() {
                tv03.setClickable(true);
                tv03.setBackgroundResource(R.drawable.bg_get_code_01);
                tv03.setText("获取验证码");
                tv03.setTextColor(getResources().getColor(R.color.get_code_text_01));
            }
        };


    }


    @OnClick({R.id.iv01, R.id.iv02, R.id.tv03, R.id.btSure, R.id.tv1, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv01:
                //输入手机号码 旁边的×
                et1.setText("");
                iv01.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv02:
                //输入密码旁边的眼睛
                isShow01 = !isShow01;
                if (isShow01) {
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//明文
                    et2.setSelection(et2.getText().length());
                    iv02.setImageResource(R.mipmap.zx_88);
                } else {
                    et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//密码
                    iv02.setImageResource(R.mipmap.zx_12);
                    et2.setSelection(et2.getText().length());
                }

                break;
            case R.id.tv03:
                //获取验证码按钮
                String phone2 = et1.getText().toString();
                if (phone2.equals("")) {
                    showToast("请输入手机号码");
                } else {
                    //获取验证码
                    getCode(phone2);
                }
                break;
            case R.id.tv1:
                //注册按钮
                jumpToActivity(RegisterActivity.class);
                break;
            case R.id.tv2:
                //忘记密码
                jumpToActivity(ForgetActivity.class);
                break;
            case R.id.btSure:
                //登录按钮
                String passwd = et2.getText().toString();
                String phone = et1.getText().toString();
                String verify = et3.getText().toString();

                if(!phone.equals("")){
                    if(!passwd.equals("")){
                        if(!verify.equals("")){
                            login(phone,passwd,verify);
                        }else {
                            showToast( "请输入验证码");
                        }
                    }else {
                        showToast( "请输入手机密码");
                    }
                }else {
                    showToast( "请输入手机号码");
                }

                break;
        }
    }

    private void login(String phone, String passwd, String verify) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.LOGIN);//登录
        httpRequest.add("passwd", passwd);
        httpRequest.add("verify", verify);
        httpRequest.add("phone", phone);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("登录" + response);
                Login info = gson.fromJson(response, Login.class);
                if (info.getStatus().equals("true")) {
                    CacheUtils.setUserid(info.getData().getUserid(),getContext());
                    CacheUtils.setToken(info.getToken(),getContext());
                    jumpToActivity(MainActivity.class);
                    myFinish();
                    CacheUtils.setLogin(true,getContext());
                } else {
                    showToast(info.getMessage());
                    if(info.getStatus().equals("2")){
                        //更新下信息
                        CacheUtils.setUserid(info.getData().getUserid(),getContext());
                        CacheUtils.setSiteId(info.getData().getSiteId(),getContext());
                        CacheUtils.setToken(info.getToken(),getContext());
                        jumpToActivity(PersonalInformationActivity.class);
                        finish();
                    }
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("登录" + exception);
            }
        }, getContext());
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
                    tv03.setClickable(false);
                    tv03.setBackgroundResource(R.drawable.bg_get_code_02);
                    tv03.setTextColor(getResources().getColor(R.color.get_code_text_02));
                    timer.start();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                LogUtil.i("获取验证码" + exception);
            }
        }, getContext());
    }
//    @OnClick({R.id.btSure, R.id.tv1, R.id.tv2})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btSure:
//                String a1=et1.getText().toString().trim();
//                String a2=et2.getText().toString().trim();
//
//

//
//
//                break;
//            case R.id.tv1:
//                jumpToActivity(RegisterActivity.class);
//                break;
//            case R.id.tv2:
//                jumpToActivity(ForgetActivity.class);
//                break;
//        }
//    }
}
