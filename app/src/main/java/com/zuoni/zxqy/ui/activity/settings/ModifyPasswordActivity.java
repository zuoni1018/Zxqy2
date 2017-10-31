package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
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
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ModifyPasswordActivity extends BaseTitleActivity {
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.et04)
    EditText et04;
    @BindView(R.id.bt05)
    Button bt05;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_modify_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改密码");
        String userId = CacheUtils.getUserid(getContext());
        LogUtil.i("获得UserId" + userId);
        tv01.setText(userId);
    }

    @OnClick(R.id.bt05)
    public void onViewClicked() {

        String passwd = et02.getText().toString().trim();
        String passwd_new1 = et03.getText().toString().trim();
        String passwd_new2 = et04.getText().toString().trim();

        if (passwd.equals("")) {
            showToast("请输入旧密码");
        } else {
            if (passwd_new1.equals("")) {
                showToast("请输入新密码");
            } else {
                if (passwd_new2.equals("")) {
                    showToast("请输入确认密码");
                } else {
                    if (passwd_new2.equals(passwd_new1)) {
                        alterPasswd(passwd, passwd_new1);
                    } else {
                        showToast("新密码与确认密码不一致");
                    }

                }
            }
        }


    }

    private void alterPasswd(String passwd, String passwd_new1) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.ALTER_PASSWD);//修改密码
        httpRequest.add("passwdNew", passwd_new1);
        httpRequest.add("passwd", passwd);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改密码" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("修改成功");
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("修改密码" + exception);
            }
        }, getContext());


    }
}
