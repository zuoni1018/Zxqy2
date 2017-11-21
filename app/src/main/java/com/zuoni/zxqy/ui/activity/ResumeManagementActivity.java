package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.FragmentPagerAddTitlesAdapter;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.fragment.resume.ResumeManagementFragment01;
import com.zuoni.zxqy.ui.fragment.resume.ResumeManagementFragment02;
import com.zuoni.zxqy.ui.fragment.resume.ResumeManagementFragment03;
import com.zuoni.zxqy.ui.fragment.resume.ResumeManagementFragment04;

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
    @BindView(R.id.tvRight)
    TextView tvRight;
    private List<Fragment> mList;
    private List<Job> Jobs;
    private String positionType = "不限";
    private ResumeManagementFragment01 resumeManagementFragment01;
    private ResumeManagementFragment02 resumeManagementFragment02;
    private ResumeManagementFragment03 resumeManagementFragment03;
    private ResumeManagementFragment04 resumeManagementFragment04;

    private int pos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("简历管理");
        pos=getIntent().getIntExtra("pos",-1);
        Jobs = new ArrayList<>();
        tvRight.setText("职位分类");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择面试职位
                if (Jobs.size() == 0) {
                    getPosition();
                } else {
                    createPicker(Jobs);
                }
            }
        });


        List<String> titles = new ArrayList<>();
        titles.add("收到的简历");
        titles.add("查看过的简历");
        titles.add("谁看了我");
        titles.add("人才收藏夹");

        resumeManagementFragment01 = new ResumeManagementFragment01();
        resumeManagementFragment01.setResumeManagementActivity(this);

        resumeManagementFragment02 = new ResumeManagementFragment02();
        resumeManagementFragment02.setResumeManagementActivity(this);

        resumeManagementFragment03 = new ResumeManagementFragment03();
        resumeManagementFragment03.setResumeManagementActivity(this);

        resumeManagementFragment04 = new ResumeManagementFragment04();
        resumeManagementFragment04.setResumeManagementActivity(this);

        mList = new ArrayList<>();
        mList.add(resumeManagementFragment01);
        mList.add(resumeManagementFragment02);
        mList.add(resumeManagementFragment03);
        mList.add(resumeManagementFragment04);
        FragmentPagerAddTitlesAdapter mPagerAdapter = new FragmentPagerAddTitlesAdapter(getSupportFragmentManager(), mList, titles);
        MyViewPager.setAdapter(mPagerAdapter);
        xTablayout.setupWithViewPager(MyViewPager);
        MyViewPager.setOffscreenPageLimit(4);//设置缓存最大

        if(pos!=-1){
            MyViewPager.setCurrentItem(pos,false);
        }
    }

    /**
     * 选完刷新数据
     */
    public void refresh() {
        resumeManagementFragment01.refreshData(getPositionType());
        resumeManagementFragment02.refreshData(getPositionType());
        resumeManagementFragment03.refreshData(getPositionType());
        resumeManagementFragment04.refreshData(getPositionType());
    }

    public String getPositionType() {
        return positionType;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_resume_management;
    }

    private void getPosition() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_POSITION);//获取发布职位
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("获取发布职位" + response);

                GetPosition info = gson.fromJson(response, GetPosition.class);
                if (info.getStatus().equals("true")) {
                    if (Jobs == null) {
                        Jobs = new ArrayList<>();
                    }
                    Jobs.clear();
                    Jobs.addAll(info.getData());
                    createPicker(Jobs);
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

    //公司性质
    private void createPicker(List<Job> jobs) {

        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();
        list.add("不限");
        for (int i = 0; i < jobs.size(); i++) {
            list.add(jobs.get(i).getTitle());
        }

        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                if(!positionType.equals(itemValue)){
                    positionType = itemValue;
                    refresh();
                }
            }
        });

        builder.setData(list);
        builder.create().show();
    }
}
