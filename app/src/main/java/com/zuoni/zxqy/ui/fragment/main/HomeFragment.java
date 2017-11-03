package com.zuoni.zxqy.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.GetUiInfo;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.HomePreviewActivity;
import com.zuoni.zxqy.ui.activity.InvitationInterviewRecordActivity;
import com.zuoni.zxqy.ui.activity.MyMailboxActivity;
import com.zuoni.zxqy.ui.activity.OnlineComplaintsActivity;
import com.zuoni.zxqy.ui.activity.PositionManagementActivity;
import com.zuoni.zxqy.ui.activity.ResumeSearchActivity;
import com.zuoni.zxqy.ui.activity.SettingsActivity;
import com.zuoni.zxqy.ui.activity.YlzpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class HomeFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.ivHomePreview)
    ImageView ivHomePreview;
    @BindView(R.id.ivSettings)
    ImageView ivSettings;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.cname)
    TextView cname;
    @BindView(R.id.ivLevel)
    ImageView ivLevel;
    @BindView(R.id.tvhycz)
    TextView tvhycz;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.resumeLeft)
    TextView resumeLeft;
    @BindView(R.id.vipTime)
    TextView vipTime;
    @BindView(R.id.msgSend)
    TextView msgSend;
    @BindView(R.id.msgReceive)
    TextView msgReceive;
    @BindView(R.id.receiveResume)
    TextView receiveResume;
    @BindView(R.id.viewResume)
    TextView viewResume;
    @BindView(R.id.favs)
    TextView favs;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.menu_1)
    LinearLayout menu1;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.menu_2)
    LinearLayout menu2;
    @BindView(R.id.menu_3)
    LinearLayout menu3;
    @BindView(R.id.menu_4)
    LinearLayout menu4;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.menu_5)
    LinearLayout menu5;
    @BindView(R.id.menu_6)
    LinearLayout menu6;
    @BindView(R.id.ivHead)
    SelectableRoundedImageView ivHead;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void getUiInfo() {

        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_UI_INFO);//获取主页
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("获取主页" + response);
                GetUiInfo info = gson.fromJson(response, GetUiInfo.class);
                if (info.getStatus().equals("true")) {
                    cname.setText(info.getData().getCname() + "");
                    title.setText(info.getData().getTitle() + "");
                    email.setText(info.getData().getEmail() + "");
                    msgSend.setText(info.getData().getMsgSend() + "");

                    msgReceive.setText(info.getData().getMsgReceive() + "");
                    viewResume.setText(info.getData().getViewResume() + "");
                    resumeLeft.setText(info.getData().getResumeLeft() + "");
                    favs.setText(info.getData().getFavs() + "");
                    vipTime.setText(info.getData().getVipTime() + "");
                    receiveResume.setText(info.getData().getReceiveResume() + "");

                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.zx_113)
                            .error(R.mipmap.zx_113);
                    Glide.with(getContext().getApplicationContext())
                            .asBitmap()
                            .load(info.getData().getImg())
                            .apply(requestOptions)
                            .into(ivHead);
                }
            }

            @Override
            public void onFailed(Exception exception) {
                LogUtil.i("获取主页" + exception);
            }
        }, getContext());


    }

    @Override
    public void onResume() {
        super.onResume();
        getUiInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.menu_1, R.id.menu_2, R.id.menu_3, R.id.menu_4, R.id.menu_5, R.id.menu_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_1:
                //职位管理
                jumpToActivity(PositionManagementActivity.class);
                break;
            case R.id.menu_2:
                //简历搜索
                jumpToActivity(ResumeSearchActivity.class);
                break;
            case R.id.menu_3:
                //邀请面试记录
                jumpToActivity(InvitationInterviewRecordActivity.class);
                break;
            case R.id.menu_4:
                //约聊招聘
                jumpToActivity(YlzpActivity.class);
                break;
            case R.id.menu_5:
                //我的信息
                jumpToActivity(MyMailboxActivity.class);
                break;
            case R.id.menu_6:
                //在线投诉留言
                jumpToActivity(OnlineComplaintsActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent mIntent = new Intent(getContext(), cls);
        startActivity(mIntent);
    }
    @OnClick({R.id.ivSettings,R.id.ivHead})
    public void onViewClicked2(View view) {
        jumpToActivity(SettingsActivity.class);
    }

    @OnClick(R.id.ivHomePreview)
    public void onViewClicked2() {
        jumpToActivity(HomePreviewActivity.class);
    }
}
