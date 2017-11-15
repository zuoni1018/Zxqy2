package com.zuoni.zxqy.ui.fragment.resume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvResumeAdapter3;
import com.zuoni.zxqy.bean.gson.GetSendresume;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.callback.OnResumeManagerListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.ResumeDetailsActivity;
import com.zuoni.zxqy.ui.activity.ResumeManagementActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 * 谁看了我
 */
public class ResumeManagementFragment03 extends Fragment {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    Unbinder unbinder;
    private View view;
    private ResumeManagementActivity resumeManagementActivity;
    private LRecyclerViewAdapter mAdapter;
    private List<ResumeManager> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resume_management03, null);
        unbinder = ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        RvResumeAdapter3 rvResumeAdapter = new RvResumeAdapter3(getContext(), mList);

        rvResumeAdapter.setOnResumeManagerListener(new OnResumeManagerListener() {
            @Override
            public void onClick01(ResumeManager resume, int position) {
                //点击整个
                Intent mIntent = new Intent(getContext(), ResumeDetailsActivity.class);
                mIntent.putExtra("name", resume.getName());
                mIntent.putExtra("workId", resume.getWorkerId());
                startActivity(mIntent);
            }

            @Override
            public void onClick02(ResumeManager resume, int position) {

            }

            @Override
            public void onClick03(ResumeManager resume, int position) {

            }

            @Override
            public void onClick04(ResumeManager resume, int position) {

            }
        });
        mAdapter = new LRecyclerViewAdapter(rvResumeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                isFirst = true;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                get_job_viewers(resumeManagementActivity.getPositionType());
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFirst = false;
                get_job_viewers(resumeManagementActivity.getPositionType());
            }
        });
        mRecyclerView.forceToRefresh();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setResumeManagementActivity(ResumeManagementActivity resumeManagementActivity) {
        this.resumeManagementActivity = resumeManagementActivity;
    }




    public void refreshData(String positionType) {
        mRecyclerView.refresh();
    }


    private int nowPage;
    private boolean isFirst = true;

    private void get_job_viewers(String positionType) {

        String url = "/Resume/get_job_viewers/p/1/size/10";

        if (!isFirst) {
            nowPage++;
            url = "/Resume/get_job_viewers/p/" + nowPage + "/size/10";
        }
        resumeManagementActivity.showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL + url);//谁看过我

        httpRequest.add("positionType", positionType);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                resumeManagementActivity.closeLoading();
                //若fragment没有初始化则不执行
                if (mRecyclerView == null) {
                    return;
                }
                mRecyclerView.refreshComplete(1);
                LogUtil.i("谁看过我" + response);
                GetSendresume info = gson.fromJson(response, GetSendresume.class);
                if (info.getStatus().equals("true")) {
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    if (!isFirst) {
                        nowPage--;
                    }
                    resumeManagementActivity.showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                if (!isFirst) {
                    nowPage--;
                }
                mRecyclerView.refreshComplete(1);
                resumeManagementActivity.closeLoading();
                resumeManagementActivity.showToast("服务器异常");
            }
        }, getContext());
    }

}
