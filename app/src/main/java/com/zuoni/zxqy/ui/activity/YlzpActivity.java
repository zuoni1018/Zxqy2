package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvYlzpAdapter;
import com.zuoni.zxqy.bean.gson.get_chat_resume;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/24
 */

public class YlzpActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
     LRecyclerViewAdapter mAdapter;
    @Override
    public int setLayoutId() {
        return R.layout.activity_ylzp;
    }

    private StringBuilder workId;
    private List<ResumeManager> mList;
    private int nowPage=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("约聊招聘");
        workId= new StringBuilder();
        showLoading();
        mList = new ArrayList<>();
        mAdapter = new LRecyclerViewAdapter(new RvYlzpAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(workId!=null&!workId.toString().equals("")){
                    nowPage=1;
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                    get_chat_resume(workId.toString());
                }
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                get_chat_resume(workId.toString());
            }
        });

        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable e) {

                        if(recents==null){
                            showToast("还未聊天");
                            closeLoading();
                            return;
                        }
                        if(recents.size()==0){
                            showToast("还未聊天");
                            closeLoading();
                            return;
                        }

                        // recents参数即为最近联系人列表（最近会话列表）
                        for (int i = 0; i <recents.size() ; i++) {
                            workId.append(recents.get(i).getFromAccount()).append(",");
                        }
                        if(!workId.toString().equals("")){
                            workId = new StringBuilder(workId.substring(0, workId.length() - 1));
                        }
                        get_chat_resume(workId.toString());

                    }
                });

    }

    private void get_chat_resume(String workId) {
        showLoading();
        String url = "/Chat/get_chat_resume/p/" + nowPage + "/size/10";

        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL+url);//获取已约聊简历接口
        httpRequest.add("accid",workId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("获取已约聊简历接口" + response);
                mRecyclerView.refreshComplete(1);
                closeLoading();
                get_chat_resume info = gson.fromJson(response, get_chat_resume.class);
                if (info.getStatus().equals("true")) {
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                    nowPage++;
                } else {
                    showToast(info.getMessage());
//                    myFinish();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }
}
