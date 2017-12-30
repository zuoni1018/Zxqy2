package com.zuoni.zxqy.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joooonho.SelectableRoundedImageView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.constant.UserInfoFieldEnum;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetUiInfo;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.HomePreviewActivity;
import com.zuoni.zxqy.ui.activity.InvitationInterviewRecordActivity;
import com.zuoni.zxqy.ui.activity.MainActivity;
import com.zuoni.zxqy.ui.activity.MyMailboxActivity;
import com.zuoni.zxqy.ui.activity.PositionManagementActivity;
import com.zuoni.zxqy.ui.activity.ResumeManagementActivity;
import com.zuoni.zxqy.ui.activity.YlzpActivity;
import com.zuoni.zxqy.ui.activity.ZXLTActivity;
import com.zuoni.zxqy.ui.activity.resumesearch.ResumeSearchActivity;
import com.zuoni.zxqy.ui.activity.settings.EssentialInformationActivity;
import com.zuoni.zxqy.ui.activity.settings.SettingsActivity;
import com.zuoni.zxqy.util.GlideUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.zuoni.zxqy.AppUrl.refresh_position;

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
    @BindView(R.id.ivLeve2)
    ImageView ivLeve2;
    @BindView(R.id.sccc)
    ScrollView sccc;
    private View view;

    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);

        tvhycz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                company_upgrade();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("您已成功通知客服");
                builder.setPositiveButton("知道了", null);
                builder.create().show();
            }
        });
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LogUtil.i("changezzz1");
                LogUtil.i("zzzzz", "bottom" + bottom + "===oldBottom" + oldBottom);

//                if(bottom!=oldBottom){
//                    sccc.fullScroll(ScrollView.FOCUS_DOWN);
//                }
//
            }
        });


        return view;
    }

    private void company_upgrade() {
        HttpRequest httpRequest = new HttpRequest(AppUrl.company_upgrade);//会员申请
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("会员申请" + response);

            }

            @Override
            public void onFailed(Exception exception) {

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

    private boolean isFirstUpDateYX = true;//是否第一次去更新云信资料

    public static String chatLast = "0";

    /**
     * 刷新职位
     * 将职位信息改为当前
     */
    private void refresh_position() {
        mainActivity.showLoading();
        HttpRequest httpRequest = new HttpRequest(refresh_position);//刷新职位
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mainActivity.closeLoading();
                LogUtil.i("刷新");
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    mainActivity.showToast("刷新成功");
                } else if (info.getStatus().equals("false")) {
                    mainActivity.showToast("未发布任何职位，请发布");
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mainActivity.closeLoading();
                mainActivity.showToast("服务器异常");
            }
        }, getContext());
    }

    public  static String VipLevel="";
    private void getUiInfo() {

        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_UI_INFO);//获取主页
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mainActivity.closeLoading();
                LogUtil.i("获取主页" + response);
                GetUiInfo info = gson.fromJson(response, GetUiInfo.class);
                if (info.getStatus().equals("true")) {
                    cname.setText(info.getData().getCname() + "");
                    title.setText(info.getData().getTitle() + "");
                    email.setText(info.getData().getEmail() + "");
                    msgSend.setText(info.getData().getMsgSend() + "");
                    chatLast = info.getData().getChatLast();
                    msgReceive.setText(info.getData().getMsgReceive() + "");
                    viewResume.setText(info.getData().getViewResume() + "");
                    resumeLeft.setText(info.getData().getResumeLeft() + "");
                    favs.setText(info.getData().getFavs() + "");
                    vipTime.setText(info.getData().getVipTime() + "");
                    receiveResume.setText(info.getData().getReceiveResume() + "");

                    GlideUtils.setHead(getContext(), info.getData().getImg(), ivHead);

                    if (info.getData().getAddress() != null) {
                        CacheUtils.setAddress(info.getData().getAddress(), getContext());
                    }


//                    RequestOptions requestOptions = new RequestOptions()
//                            .centerCrop()
//                            .placeholder(R.mipmap.zx_113)
//                            .error(R.mipmap.zx_113);
//                    Glide.with(getContext().getApplicationContext())
//                            .asBitmap()
//                            .load(info.getData().getImg())
//                            .apply(requestOptions)
//                            .into(ivHead);
                    VipLevel=info.getData().getVipLevel();
                    if (info.getData().getVipLevel().equals("1")) {
                        //绿色会员
                        ivLevel.setImageResource(R.mipmap.zx_64);
                        ivLevel.setVisibility(View.VISIBLE);
                        ivLeve2.setVisibility(View.GONE);
                    } else if (info.getData().getVipLevel().equals("2")) {
                        ivLevel.setImageResource(R.mipmap.zx_65);
                        ivLevel.setVisibility(View.VISIBLE);
                        ivLeve2.setVisibility(View.GONE);
                    } else {
                        ivLevel.setVisibility(View.GONE);
                        ivLeve2.setVisibility(View.VISIBLE);
                    }

                    if (isFirstUpDateYX) {
                        upDateYX(info.getData().getTitle() + "", info.getData().getImg());
                    }


                }
            }

            @Override
            public void onFailed(Exception exception) {
                mainActivity.closeLoading();
                mainActivity.showToast("服务器异常");
                LogUtil.i("获取主页" + exception);
            }
        }, getContext());


    }

    /**
     * 更新云信资料
     */
    private void upDateYX(String name, String headUrl) {

        Map<UserInfoFieldEnum, Object> fields = new HashMap<>();
        fields.put(UserInfoFieldEnum.Name, name);
        fields.put(UserInfoFieldEnum.AVATAR, headUrl);
        NIMClient.getService(UserService.class).updateUserInfo(fields)
                .setCallback(new RequestCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        isFirstUpDateYX = false;
                    }

                    @Override
                    public void onFailed(int i) {
                        isFirstUpDateYX = true;
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        isFirstUpDateYX = true;
                    }
                });
    }

    @OnClick({R.id.menu_1, R.id.menu_2, R.id.menu_3, R.id.menu_4, R.id.menu_5, R.id.menu_7, R.id.menu_6})
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
//                //在线投诉留言
                jumpToActivity(ZXLTActivity.class);
                break;
            case R.id.menu_7:
                //一键刷新
                refresh_position();
//                mainActivity.showLoading();
//                getUiInfo();
//                jumpToActivity(OnlineComplaintsActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent mIntent = new Intent(getContext(), cls);
        startActivity(mIntent);
    }

    @OnClick({R.id.ivSettings})
    public void onViewClicked2(View view) {
        jumpToActivity(SettingsActivity.class);
    }

    @OnClick({R.id.iv11111111, R.id.ivHead, R.id.title})
    public void onViewClicked3(View view) {
        jumpToActivity(EssentialInformationActivity.class);
    }

    @OnClick(R.id.ivHomePreview)
    public void onViewClicked2() {
        jumpToActivity(HomePreviewActivity.class);
    }


    @OnClick({R.id.positionManagement01, R.id.positionManagement02, R.id.positionManagement03})
    public void onPositionManagementClicked(View view) {
        Intent mIntent = new Intent(getContext(), ResumeManagementActivity.class);
        switch (view.getId()) {
            case R.id.positionManagement01:
                mIntent.putExtra("pos", 0);
                break;
            case R.id.positionManagement02:
                mIntent.putExtra("pos", 1);
                break;
            case R.id.positionManagement03:
                mIntent.putExtra("pos", 3);
                break;
        }
        startActivity(mIntent);
    }
}
