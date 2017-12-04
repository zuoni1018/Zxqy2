package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoni.common.widget.MyViewPager;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.FragmentPagerAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseActivity;
import com.zuoni.zxqy.ui.fragment.main.ChartFragment;
import com.zuoni.zxqy.ui.fragment.main.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    MyViewPager viewPager;
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.iv02)
    ImageView iv02;
    @BindView(R.id.tv02)
    TextView tv02;

    private List<Fragment> mList;
    private FragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        HomeFragment homeFragment=new HomeFragment();
        homeFragment.setMainActivity(this);
        mList.add(homeFragment);
        mList.add(new ChartFragment());
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(mPagerAdapter);

//        api = WXAPIFactory.createWXAPI(this, GlobalVariable.WX_APP_ID);
//        HttpRequest  mHttpRequest=new HttpRequest("");
//
//
//        CallServer.getInstance().request(mHttpRequest, new HttpResponseListener() {
//            @Override
//            public void onSucceed(String response, Gson gson) {
//                BaseHttpResponse baseHttpResponse=gson.fromJson(response,BaseHttpResponse.class);
//            }
//
//            @Override
//            public void onFailed(Exception exception) {
//
//            }
//        });




    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

//    private IWXAPI api;

//    @OnClick(R.id.btTest)
//    public void onViewClicked() {
////        Intent mIntent=new Intent(GlobalVariable.BROADCAST_TOKEN_ERROR);
////        sendBroadcast(mIntent);
////        sendBroadcast(mIntent);
////        jumpToActivity(CreateQrCodeActivity.class);
//
//        //
////        WxShareWeb wxWebShare=new WxShareWeb(
////                "https://www.baidu.com/index.php?tn=02049043_14_pg",
////                "古早日月村标题",
////                "古早日月村描述",
////                R.mipmap.zzz);
////
////        wxWebShare.share(getContext(),api);
//
//        jumpToActivity(FindStoresActivity.class);
//
//    }


    private long lastTime = 0;

    @Override
    public void onBackPressed() {
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastTime > 2000) {
            lastTime = nowTime;
            showToast("再按一次退出App");
        } else {
            finish();
        }
    }


    @OnClick({R.id.layoutMenu1, R.id.layoutMenu2, R.id.layoutMenu3, R.id.layoutMenu4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutMenu1:
                restMenu();
                viewPager.setCurrentItem(0);
                tv01.setTextColor(getResources().getColor(R.color.tab_color_02));
                iv01.setImageResource(R.mipmap.zx_46);
                break;
            case R.id.layoutMenu2:
                jumpToActivity(PostingPositionActivity.class);
                break;
            case R.id.layoutMenu3:
//                LoginInfo info = new LoginInfo("123456", "123456"); // config...
//                RequestCallback<LoginInfo> callback =
//                        new RequestCallback<LoginInfo>() {
//                            @Override
//                            public void onSuccess(LoginInfo param) {
//                                LogUtil.i("onSuccess");
//                                showToast("登陆成功");
//                                jumpToActivity(MainActivity.class);
//                            }
//
//                            @Override
//                            public void onFailed(int code) {
//                                LogUtil.i("onFailed");
//                                showToast("登陆失败");
//                            }
//
//                            @Override
//                            public void onException(Throwable exception) {
//
//                            }
//                        };
//
//                NIMClient.getService(AuthService.class).login(info).setCallback(callback);
                jumpToActivity(ResumeManagementActivity.class);
                break;
            case R.id.layoutMenu4:
                restMenu();
                viewPager.setCurrentItem(1);
                tv02.setTextColor(getResources().getColor(R.color.tab_color_02));
                iv02.setImageResource(R.mipmap.zx_54);
                break;
        }
    }

    private void restMenu() {
        tv01.setTextColor(getResources().getColor(R.color.tab_color_01));
        tv02.setTextColor(getResources().getColor(R.color.tab_color_01));

        iv01.setImageResource(R.mipmap.zx_60);
        iv02.setImageResource(R.mipmap.zx_6);
    }
}
