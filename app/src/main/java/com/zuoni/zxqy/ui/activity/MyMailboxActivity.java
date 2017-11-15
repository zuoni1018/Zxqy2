package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androidkun.xtablayout.XTabLayout;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.FragmentPagerAddTitlesAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.fragment.mailbox.GetFragment;
import com.zuoni.zxqy.ui.fragment.mailbox.SendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class MyMailboxActivity extends BaseTitleActivity {
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.MyViewPager)
    com.zuoni.common.widget.MyViewPager MyViewPager;

    @Override
    public int setLayoutId() {
        return R.layout.activity_my_mailbox;
    }
    private List<Fragment> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("我的信箱");

        List<String> titles = new ArrayList<>();
        titles.add("收到的信息");
        titles.add("发出的信息");
        mList = new ArrayList<>();

        GetFragment getFragment=new GetFragment();
        getFragment.setMyMailboxActivity(this);
        SendFragment sendFragment=new SendFragment();
        sendFragment.setMyMailboxActivity(this);
        mList.add(getFragment);
        mList.add(sendFragment);
        FragmentPagerAddTitlesAdapter   mPagerAdapter = new FragmentPagerAddTitlesAdapter(getSupportFragmentManager(), mList,titles);
        MyViewPager.setAdapter(mPagerAdapter);
        xTablayout.setupWithViewPager(MyViewPager);
    }
}
