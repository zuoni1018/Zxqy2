package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvResumeAdapter5;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.gson.GetSendresume;
import com.zuoni.zxqy.bean.model.InvitationPeople;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.callback.OnResumeManagerListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class InvitationInterviewRecordActivity extends BaseTitleActivity {

    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.bottom01)
    TextView bottom01;
    @BindView(R.id.bottom02)
    TextView bottom02;

    private List<Job> Jobs;
    private String positionType = "不限";
    private LRecyclerViewAdapter mAdapter;
    private List<ResumeManager> mList;
    private ArrayList<InvitationPeople> peoples;
    private int nowPage;
    private boolean isFirst = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("邀请面试记录");
        tvRight.setText("职位分类");
        Jobs = new ArrayList<>();
        mList = new ArrayList<>();
        peoples = new ArrayList<>();//需要邀请的人
        RvResumeAdapter5 rvResumeAdapter = new RvResumeAdapter5(getContext(), mList);

        rvResumeAdapter.setOnResumeManagerListener(new OnResumeManagerListener() {
            @Override
            public void onClick01(ResumeManager resume, int position) {
                //点击整个
                Intent mIntent = new Intent(getContext(), ResumeDetailsActivity.class);
                mIntent.putExtra("name", resume.getName());
                mIntent.putExtra("workId", resume.getWorkerId());
//                mIntent.putExtra("sendresumeId", resume.getSendresumeId());
                startActivity(mIntent);
//                if (mList.get(position).getJobstatus().equals("1")) {
//                    mList.get(position).setJobstatus("2");
//                    mAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onClick02(ResumeManager resume, int position) {
                //邀请面试
                Intent mIntent = new Intent(getContext(), InterviewContentActivity.class);
                mIntent.putExtra("inviteId", resume.getInviteId()+"");
                startActivity(mIntent);
            }

            @Override
            public void onClick03(ResumeManager resume, int position) {
                //直接本地移除
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                //删除简历
                delete_invite(resume.getInviteId(), false);

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
                isChooseAll=false;
                nowPage = 1;
                isFirst = true;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                get_company_invite(positionType);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFirst = false;
                get_company_invite(positionType);
            }
        });
        mRecyclerView.forceToRefresh();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_invitation_interview_record;
    }

    private void get_company_invite(String positionType) {

        String url = "/Resume/get_company_invite/p/1/size/10";

        if (!isFirst) {
            nowPage++;
            url = "/Resume/get_company_invite/p/" + nowPage + "/size/10";
        }
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL + url);//邀请面试记录

        httpRequest.add("positionType", positionType);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                //若fragment没有初始化则不执行
                if (mRecyclerView == null) {
                    return;
                }
                mRecyclerView.refreshComplete(1);
                LogUtil.i("邀请面试记录" + response);
                GetSendresume info = gson.fromJson(response, GetSendresume.class);
                if (info.getStatus().equals("true")) {
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    if (!isFirst) {
                        nowPage--;
                    }
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                if (!isFirst) {
                    nowPage--;
                }
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }


    @OnClick(R.id.tvRight)
    public void onViewClicked() {
        //选择面试职位
        if (Jobs.size() == 0) {
            getPosition();
        } else {
            createPicker(Jobs);
        }
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

    /**
     * 选择器
     */
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
                //不相等再去刷
                if(!positionType.equals(itemValue)){
                    positionType = itemValue;
                    mRecyclerView.forceToRefresh();//每次选完了去刷数据
                }
            }
        });
        builder.setData(list);
        builder.create().show();
    }

    private boolean isChooseAll = false;

    @OnClick({R.id.bottom01, R.id.bottom02})
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
                //删除选中
                List<String> inviteId = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChoose()) {
                        inviteId.add(mList.get(i).getInviteId());
                    }
                }
                if (inviteId.size() == 0) {
                    showToast("请先选择简历");
                } else {
                    //执行删除
                    String sendresumeId = "";
                    for (int i = 0; i < inviteId.size(); i++) {
                        sendresumeId = sendresumeId + inviteId.get(i) + ",";
                    }
                    sendresumeId = sendresumeId.substring(0, sendresumeId.length() - 1);
                    delete_invite(sendresumeId, true);
                }
                break;
        }
    }

    private void delete_invite(String inviteId, final boolean isRefresh) {
        if (isRefresh) {
            showLoading();
        }

        HttpRequest httpRequest = new HttpRequest(AppUrl.delete_invite);//删除邀请面试记录
        httpRequest.add("inviteId", inviteId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("删除邀请面试记录" + response);
                if (isRefresh) {
                    closeLoading();
                    BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                    if (info.getStatus().equals("true")) {
                        if(info.getMessage().equals("")){
                            showToast("删除成功");
                        }else {
                            showToast(info.getMessage());
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerView.refresh();//重新刷新
                            }
                        },300);
                    } else {
                        showToast(info.getMessage());
                    }
                }
            }

            @Override
            public void onFailed(Exception exception) {
                if (isRefresh) {
                    closeLoading();
                }
                showToast("服务器异常");
            }
        }, getContext());
    }
}
