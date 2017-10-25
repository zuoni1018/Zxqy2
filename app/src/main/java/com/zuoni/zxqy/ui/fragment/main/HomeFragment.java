package com.zuoni.zxqy.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.HomePreviewActivity;
import com.zuoni.zxqy.ui.activity.InvitationInterviewRecordActivity;
import com.zuoni.zxqy.ui.activity.MyMailboxActivity;
import com.zuoni.zxqy.ui.activity.OnlineComplaintsActivity;
import com.zuoni.zxqy.ui.activity.PositionManagementActivity;
import com.zuoni.zxqy.ui.activity.SettingsActivity;
import com.zuoni.zxqy.ui.activity.YlzpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class HomeFragment extends Fragment {
    Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
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

    @OnClick(R.id.ivSettings)
    public void onViewClicked() {
        jumpToActivity(SettingsActivity.class);
    }

    @OnClick(R.id.ivHomePreview)
    public void onViewClicked2() {
        jumpToActivity(HomePreviewActivity.class);
    }
}
