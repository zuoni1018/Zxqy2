package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.GetCompanyInfo;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.CompanyProfileActivity;
import com.zuoni.zxqy.ui.activity.LoginActivity;
import com.zuoni.zxqy.ui.activity.OnlineComplaintsActivity;
import com.zuoni.zxqy.ui.activity.base.ActivityCollector;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class SettingsActivity extends BaseTitleActivity {
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("设置");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCompanyInfo();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings;
    }

    private void getCompanyInfo() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_COMPANY_INFO);//企业信息2

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("企业信息2" + response);
                GetCompanyInfo info = gson.fromJson(response, GetCompanyInfo.class);
                if (info.getStatus().equals("true")) {
                    CacheUtils.setCompanyInfo(info.getData(),getContext());
                    tvInfo.setText(info.getData().getInfo());
                } else {
                    showToast("获取信息失败");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myFinish();
                        }
                    },500);
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("企业信息2" + exception);
                showToast("获取信息失败");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myFinish();
                    }
                }, 500);
            }
        }, getContext());

    }



    @OnClick({R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                //账号信息
                jumpToActivity(AccountInformationActivity.class);
                break;
            case R.id.layout2:
                //基本信息
                jumpToActivity(EssentialInformationActivity.class);
                break;
            case R.id.layout3:
                //投诉建议
//                jumpToActivity(OtherFunctionsActivity.class);
                jumpToActivity(OnlineComplaintsActivity.class);
                break;
            case R.id.layout4:
                //退出登录
                NIMClient.getService(AuthService.class).logout();
                CacheUtils.setAccount("",getContext());
                //销毁界面
                ActivityCollector.finishAll();
                CacheUtils.setLogin(false,getContext());
                Intent mIntent=new Intent(getContext(), LoginActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @OnClick(R.id.ivCompanyProfile)
    public void onViewClicked() {
        //修改公司介绍
        Intent mIntent = new Intent(getContext(), CompanyProfileActivity.class);
        mIntent.putExtra("isSetPersonalInfo", false);
        mIntent.putExtra("text", tvInfo.getText().toString().trim());
        startActivityForResult(mIntent, 10086);
    }
}
