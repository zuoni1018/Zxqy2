package com.zuoni.zxqy.ui.fragment.resume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvResumeAdapter2;
import com.zuoni.zxqy.bean.gson.GetViewresume;
import com.zuoni.zxqy.bean.model.InvitationPeople;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.callback.OnResumeManagerListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.InvitationInterviewActivity;
import com.zuoni.zxqy.ui.activity.ResumeDetailsActivity;
import com.zuoni.zxqy.ui.activity.ResumeManagementActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 * 查看过的简历
 */
public class ResumeManagementFragment02 extends Fragment {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.bottom01)
    TextView bottom01;
    @BindView(R.id.bottom02)
    TextView bottom02;
    @BindView(R.id.bottom03)
    TextView bottom03;
    private View view;

    private int INVITATION_TAG = 11111;

    ArrayList<InvitationPeople> peoples;
    private ResumeManagementActivity resumeManagementActivity;

    public void setResumeManagementActivity(ResumeManagementActivity resumeManagementActivity) {
        this.resumeManagementActivity = resumeManagementActivity;
    }

    private LRecyclerViewAdapter mAdapter;
    private List<ResumeManager> mList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resume_management2, null);
        unbinder = ButterKnife.bind(this, view);
        bottom02.setVisibility(View.GONE);

        mList = new ArrayList<>();
        peoples = new ArrayList<>();//需要邀请的人
        RvResumeAdapter2 rvResumeAdapter = new RvResumeAdapter2(getContext(), mList);

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
                //邀请面试

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
                isChooseAll = false;
                nowPage = 1;
                isFirst = true;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                get_sendresume(resumeManagementActivity.getPositionType());
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFirst = false;
                get_sendresume(resumeManagementActivity.getPositionType());
            }
        });
        mRecyclerView.forceToRefresh();

        return view;
    }

    public void refreshData(String positionType) {
        mRecyclerView.refresh();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int nowPage;
    private boolean isFirst = true;

    private void get_sendresume(String positionType) {

        String url = "/Resume/get_viewresume/p/1/size/10";

        if (!isFirst) {
            nowPage++;
            url = "/Resume/get_viewresume/p/" + nowPage + "/size/10";
        }
        resumeManagementActivity.showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL + url);//获取查看过的的简历列表

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
                LogUtil.i("获取查看过的的简历列表" + response);
                GetViewresume info = gson.fromJson(response, GetViewresume.class);
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


    private boolean isChooseAll = false;

    @OnClick({R.id.bottom01, R.id.bottom02, R.id.bottom03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom01:
                //全选
                isChooseAll = !isChooseAll;
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).setChoose(isChooseAll);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.bottom02:

                break;
            case R.id.bottom03:
                //批量邀请面试
                peoples.clear();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChoose()) {
                        InvitationPeople invitationPeople = new InvitationPeople();
                        invitationPeople.setHeadUrl(mList.get(i).getImg());
                        invitationPeople.setName(mList.get(i).getName());
                        invitationPeople.setWorkName("");
                        invitationPeople.setWorkId(mList.get(i).getWorkerId());
                        peoples.add(invitationPeople);
                    }
                }
                if (peoples.size() == 0) {
                    resumeManagementActivity.showToast("请先选择简历");
                } else {
                    //邀请面试
                    Intent mIntent = new Intent(getContext(), InvitationInterviewActivity.class);
                    mIntent.putExtra("peoples", peoples);
                    startActivity(mIntent);
                }
                break;
        }
    }

}
