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
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.GlobalVariable;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetSites;
import com.zuoni.zxqy.bean.gson.Regist;
import com.zuoni.zxqy.bean.model.City;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class RegisterActivity extends BaseTitleActivity {
    @BindView(R.id.btSure)
    Button btSure;
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
    @BindView(R.id.et04)
    EditText et04;
    @BindView(R.id.iv04)
    ImageView iv04;
    @BindView(R.id.et05)
    EditText et05;
    @BindView(R.id.iv05)
    ImageView iv05;
    @BindView(R.id.tv06)
    TextView tv06;
    @BindView(R.id.tv07)
    TextView tv07;
    private CountDownTimer timer;


    private List<City> citys;
    private String siteId = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        ButterKnife.bind(this);
        setTitle("注册");

        citys = new ArrayList<>();

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

        et02.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    iv02.setVisibility(View.INVISIBLE);
                } else {
                    iv02.setVisibility(View.VISIBLE);
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

    @Override
    public int setLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.btSure})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.btSure:
                String userid = et01.getText().toString().trim();//用户名称
                String phone = et02.getText().toString().trim();//手机号码
                String verify = et03.getText().toString().trim();//验证码
                String passwd = et04.getText().toString().trim();
                String passwd2 = et05.getText().toString().trim();

                if (userid.equals("")) {
                    showToast("请输入用户名称");
                } else {
                    if (phone.equals("")) {
                        showToast("请输入手机号");
                    } else {
                        if (verify.equals("")) {
                            showToast("请输入验证码");
                        } else {
                            if (passwd.equals("")) {
                                showToast("请输入密码");
                            } else {
                                if (passwd2.equals("")) {
                                    showToast("请输入确认密码");
                                } else {
                                    if (siteId.equals("-1")) {
                                        showToast("请选择城市");
                                    } else {
                                        if (passwd.equals(passwd2)) {
                                            register(userid, phone, verify, siteId, passwd);
                                        } else {
                                            showToast("密码与确认密码不一致");
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    private void register(String userid, String phone, String verify, String siteId, String passwd) {

        showLoading();

        HttpRequest httpRequest = new HttpRequest(AppUrl.REGIST);//注册
        httpRequest.add("passwd", passwd);
        httpRequest.add("verify", verify);
        httpRequest.add("siteId", siteId);
        httpRequest.add("userid", userid);
        httpRequest.add("phone", phone);

        CallServer.getInstance().request2(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("注册" + response);
                Regist info = gson.fromJson(response, Regist.class);
                if (info.getStatus().equals("true")) {
                    CacheUtils.setToken(info.getToken(), getContext());
                    CacheUtils.setUserid(info.getData().getUserid(), getContext());
                    CacheUtils.setSiteId(info.getData().getSiteId(), getContext());
                    CacheUtils.setPhone(et02.getText().toString().trim(), getContext());
                    showToast("注册成功");
                    jumpToActivity(PersonalInformationActivity.class);
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("注册" + exception);
            }
        }, getContext());


    }

    private boolean isShow01 = false;
    private boolean isShow02 = false;

    @OnClick({R.id.iv01, R.id.iv02, R.id.tv03, R.id.iv04, R.id.iv05, R.id.tv06, R.id.tv07})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv01:
                //请输入用户 旁边的×
                et01.setText("");
                iv01.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv02:
                //输入手机号码 旁边的×
                et02.setText("");
                iv02.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv03:
                //发送验证码按钮
                String userid = et01.getText().toString();
                String phone = et02.getText().toString();

                if (userid.equals("") | phone.equals("")) {
                    showToast("请输入用户名和手机号码");
                } else {
                    //获取验证码
                    getCode(userid, phone);
                }
                break;
            case R.id.iv04:

                isShow01 = !isShow01;
                if (isShow01) {
                    et04.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//明文
                    et04.setSelection(et04.getText().length());
                    iv04.setImageResource(R.mipmap.zx_88);
                } else {
                    et04.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//密码
                    iv04.setImageResource(R.mipmap.zx_12);
                    et04.setSelection(et04.getText().length());
                }

                break;
            case R.id.iv05:
                isShow02 = !isShow02;
                if (isShow02) {
                    et05.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//明文
                    et05.setSelection(et05.getText().length());
                    iv05.setImageResource(R.mipmap.zx_88);
                } else {
                    et05.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//密码
                    iv05.setImageResource(R.mipmap.zx_12);
                    et05.setSelection(et05.getText().length());
                }
                break;
            case R.id.tv06:
                //获取城市列表
                showLoading();
                getSites();
                break;
            case R.id.tv07:
                showToast("用户协议");
                break;
        }
    }


    private void getSites() {
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_SITES);
        httpRequest.add("phone", "");
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("城市列表" + response);
                closeLoading();
                GetSites info = gson.fromJson(response, GetSites.class);
                if (info.getStatus().equals("true")) {
                    DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
                    citys.clear();
                    citys.addAll(info.getData());
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < citys.size(); i++) {
                        list.add(citys.get(i).getName());
                    }

                    builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
                        @Override
                        public void onDataSelected(String itemValue) {
                            tv06.setText(itemValue);
                            for (int i = 0; i < citys.size(); i++) {
                                if (itemValue.equals(citys.get(i).getName())) {
                                    siteId = citys.get(i).getSiteId();
                                    return;
                                }
                            }
                        }
                    });

                    builder.setData(list);
                    builder.create().show();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
            }
        }, getContext());
    }


    private void getCode(String userid, String phone) {
        HttpRequest httpRequest = new HttpRequest(AppUrl.VERIFY);//注册
        httpRequest.add("phone", phone);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
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
