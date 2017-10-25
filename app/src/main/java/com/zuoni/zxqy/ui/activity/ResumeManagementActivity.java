package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.androidkun.xtablayout.XTabLayout;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.FragmentPagerAddTitlesAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.fragment.ResumeManagementFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/25
 */

public class ResumeManagementActivity extends BaseTitleActivity {
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.MyViewPager)
    com.zuoni.common.widget.MyViewPager MyViewPager;

    @Override
    public int setLayoutId() {
        return R.layout.activity_resume_management;
    }
    private List<Fragment> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("我的信箱");

        List<String> titles = new ArrayList<>();
        titles.add("收到的简历");
        titles.add("查看过的简历");
        titles.add("谁看了我");
        titles.add("人才收藏夹");
        mList = new ArrayList<>();
        mList.add(new ResumeManagementFragment());
        mList.add(new ResumeManagementFragment());
        mList.add(new ResumeManagementFragment());
        mList.add(new ResumeManagementFragment());
        FragmentPagerAddTitlesAdapter   mPagerAdapter = new FragmentPagerAddTitlesAdapter(getSupportFragmentManager(), mList,titles);
        MyViewPager.setAdapter(mPagerAdapter);
        xTablayout.setupWithViewPager(MyViewPager);
    }
}
