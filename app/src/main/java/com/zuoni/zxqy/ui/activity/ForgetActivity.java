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
import com.zuoni.zxqy.GlobalVariable;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
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

public class ForgetActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.bt04)
    Button bt04;

    @Override
    public int setLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        ButterKnife.bind(this);
        setTitle("忘记密码");
        //自动填写手机号码
        String phone = CacheUtils.getPhone(getContext());
        if (!phone.equals("")) {
            et01.setText(phone);
            iv01.setVisibility(View.VISIBLE);
            isShow01 = true;
            et01.setSelection(et01.getText().length());
            if (phone.equals("15168212330")) {
                et03.setText("848813");
                et02.setText("123456");
            }
        }
        et01.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    iv01.setVisibility(View.INVISIBLE);
                } else {
                    iv01.setVisibility(View.VISIBLE);
                }

            }
        });
        timer = new CountDownTimer(GlobalVariable.CountTime, 1000) {
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

    private CountDownTimer timer;
    private boolean isShow01 = false;

    @OnClick({R.id.iv01, R.id.iv02, R.id.tv03, R.id.bt04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv01:
                //输入手机号码 旁边的×
                et01.setText("");
                iv01.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv02:
                isShow01 = !isShow01;
                if (isShow01) {
                    et02.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//明文
                    et02.setSelection(et02.getText().length());
                    iv02.setImageResource(R.mipmap.zx_88);
                } else {
                    et02.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//密码
                    iv02.setImageResource(R.mipmap.zx_12);
                    et02.setSelection(et02.getText().length());
                }
                break;
            case R.id.tv03:
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
            case R.id.bt04:


//                phone	字符串	M		手机号码
//                passwd_new	字符串	M		新密码
//                verify	字符串	M		验证码

                String phone2 = et01.getText().toString().trim();
                String passwd_new = et02.getText().toString().trim();
                String verify = et03.getText().toString().trim();

                if(phone2.equals("")){
                    showToast("请输入手机号");
                }else {
                    if(passwd_new.equals("")){
                        showToast("请输入密码");
                    }else {
                        if(verify.equals("")){
                            showToast("请输入验证码");
                        }else {
                            forgetPasswd(phone2,passwd_new,verify);
                        }
                    }
                }


                break;
        }
    }

    private void forgetPasswd(String phone, String passwd_new, String verify) {

        HttpRequest httpRequest = new HttpRequest(AppUrl.FORGET_PASSWD);//忘记密码
        httpRequest.add("phone", phone);
        httpRequest.add("passwd_new", passwd_new);
        httpRequest.add("verify", verify);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("忘记密码" + response);
                BaseHttpResponse baseHttpResponse = gson.fromJson(response, BaseHttpResponse.class);
                showToast(baseHttpResponse.getMessage());

            }

            @Override
            public void onFailed(Exception exception) {
                LogUtil.i("忘记密码" + exception);
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
}
