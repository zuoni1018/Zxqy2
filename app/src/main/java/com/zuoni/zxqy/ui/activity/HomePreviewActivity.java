package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvHomePreviewAdapter;
import com.zuoni.zxqy.bean.gson.PreviewCompanyInfo;
import com.zuoni.zxqy.bean.model.EnterpriseInformation;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class HomePreviewActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    @BindView(R.id.layoutLeft)
    RelativeLayout layoutLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivHead)
    SelectableRoundedImageView ivHead;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.cateName)
    TextView cateName;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.info)
    TextView tvinfo;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    private Handler mHandler = new Handler();
    private List<EnterpriseInformation.JobsBean> jobs;
    private LRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        previewCompanyInfo();
        setTitle("首页预览");
        jobs = new ArrayList<>();
        mRecyclerView.setPullRefreshEnabled(false);
        mAdapter = new LRecyclerViewAdapter(new RvHomePreviewAdapter(getContext(), jobs));
        //禁止滑动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home_preview;
    }

    private void previewCompanyInfo() {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.PREVIEW_COMPANY_INFO);//企业信息
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("企业信息2" + response);
                PreviewCompanyInfo info = gson.fromJson(response, PreviewCompanyInfo.class);
                if (info.getStatus().equals("true")) {
                    EnterpriseInformation enterpriseInformation = info.getData();
                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.zx_113)
                            .error(R.mipmap.zx_113);
                    Glide.with(getContext().getApplicationContext())
                            .asBitmap()
                            .load(enterpriseInformation.getLogo())
                            .apply(requestOptions)
                            .into(ivHead);

                    title.setText(enterpriseInformation.getTitle());
                    type.setText(enterpriseInformation.getType());
                    web.setText(enterpriseInformation.getWeb());
                    address.setText(enterpriseInformation.getAddress());
                    cateName.setText(enterpriseInformation.getCateName());
                    tvinfo.setText(enterpriseInformation.getInfo());

                    jobs.clear();
                    if (enterpriseInformation.getJobs() != null) {
                        jobs.addAll(enterpriseInformation.getJobs());
                    }
                    mAdapter.notifyDataSetChanged();

                    nestedScrollView.fullScroll(ScrollView.FOCUS_UP);//滚动到顶部
                } else {
                    showToast("获取信息失败");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myFinish();
                        }
                    }, 500);
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("企业信息" + exception);
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
}
