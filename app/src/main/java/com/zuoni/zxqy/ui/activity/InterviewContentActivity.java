package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.get_invite_detail;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.util.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/11/15
 */

public class InterviewContentActivity extends BaseTitleActivity {
    @BindView(R.id.img)
    SelectableRoundedImageView img;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.tv04)
    TextView tv04;
    @BindView(R.id.tv05)
    TextView tv05;
    @BindView(R.id.tv06)
    TextView tv06;
    @BindView(R.id.tv07)
    TextView tv07;
    @BindView(R.id.tv08)
    TextView tv08;

    @Override
    public int setLayoutId() {
        return R.layout.activity_interview_content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("邀请面试内容");
        String  inviteId=getIntent().getStringExtra("inviteId");
//        name2=getIntent().getStringExtra("name");
        get_invite_detail(inviteId);
    }


    private void get_invite_detail(final String inviteId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.get_invite_detail);//4.15获取邀请信
        httpRequest.add("inviteId", inviteId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("4.15获取邀请信" + response);
                closeLoading();
                get_invite_detail info = gson.fromJson(response, get_invite_detail.class);
                if (info.getStatus().equals("true")) {

                    if(info.getData()==null){
                        myFinish();
                        return;
                    }


                    GlideUtils.setHead(getContext(),info.getData().getImg(),img);
                    tv02.setText(info.getData().getWorkerName());
                    tv03.setText("邀请面试的职位："+info.getData().getJobName());
                    tv04.setText(info.getData().getName()+"    "+info.getData().getTele());
                    tv05.setText(info.getData().getName());
                    tv06.setText(info.getData().getTele());
                    tv07.setText(info.getData().getAddress());
                    tv08.setText(info.getData().getInfo());
                } else {
                    showToast(info.getMessage());
                    myFinish();
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
