package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class OnlineComplaintsActivity extends BaseTitleActivity {
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.iv03)
    ImageView iv03;
    @BindView(R.id.iv04)
    ImageView iv04;
    @BindView(R.id.iv05)
    ImageView iv05;
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.tvPeople)
    TextView tvPeople;

    private String workId;
    private String name;
    private boolean isAdmin = true;

    @Override
    public int setLayoutId() {
        return R.layout.activity_online_complaints;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("投诉建议");

        name = getIntent().getStringExtra("name");
        workId = getIntent().getStringExtra("workId");

        if (workId == null) {
            isAdmin = true;
        } else {
            isAdmin = false;
            setTitle("发送留言");
            tvPeople.setText("收信人："+name);
        }

    }

    private void send_mess_admin(String type, String info) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.send_mess_admin);//在线投诉留言
        httpRequest.add("type", type);
        httpRequest.add("info", info);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("在线投诉留言" + response);
                closeLoading();
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    String type = "回复";

    @OnClick({R.id.iv01, R.id.iv02, R.id.iv03, R.id.iv04, R.id.iv05})
    public void onViewClicked(View view) {
        rest();
        switch (view.getId()) {
            case R.id.iv01:
                iv01.setImageResource(R.mipmap.zx_3);
                type = "回复";
                break;
            case R.id.iv02:
                iv02.setImageResource(R.mipmap.zx_3);
                type = "咨询";
                break;
            case R.id.iv03:
                iv03.setImageResource(R.mipmap.zx_3);
                type = "建议";
                break;
            case R.id.iv04:
                iv04.setImageResource(R.mipmap.zx_3);
                type = "投诉";
                break;
            case R.id.iv05:
                iv05.setImageResource(R.mipmap.zx_3);
                type = "其他";
                break;
        }
    }

    private void rest() {
        iv01.setImageResource(R.mipmap.zx_117);
        iv02.setImageResource(R.mipmap.zx_117);
        iv03.setImageResource(R.mipmap.zx_117);
        iv04.setImageResource(R.mipmap.zx_117);
        iv05.setImageResource(R.mipmap.zx_117);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {

        String info = et01.getText().toString().trim();
        if (info.equals("")) {
            showToast("请输入留言");
        } else {
            if (isAdmin) {
                send_mess_admin(type, info);
            } else {
                send_mess_to(type, info);
            }

        }


    }

    private void send_mess_to(String type, String info) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.send_mess_to);//发送消息
        httpRequest.add("type", type);
        httpRequest.add("info", info);
        httpRequest.add("toid", workId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("发送消息" + response);
                closeLoading();
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }
}
