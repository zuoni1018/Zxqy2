package com.zuoni.zxqy.ui.fragment.mailbox;

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
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvMailboxAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.get_message_tome;
import com.zuoni.zxqy.bean.model.Message;
import com.zuoni.zxqy.cache.CacheUtils;
import com.zuoni.zxqy.callback.OnMailBoxListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.MailDetailsActivity;
import com.zuoni.zxqy.ui.activity.MyMailboxActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class SendFragment extends Fragment {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    Unbinder unbinder;
    private View view;
    private boolean isFirst = true;
    private int nowPage;
    private MyMailboxActivity myMailboxActivity;


    //列表
    private List<Message> mList;
    private LRecyclerViewAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_box_get, null);
        unbinder = ButterKnife.bind(this, view);
        mList = new ArrayList<>();

        RvMailboxAdapter rvMailboxAdapter =new RvMailboxAdapter(getContext(), mList) ;
        rvMailboxAdapter.setOnMailBoxListener(new OnMailBoxListener() {
            @Override
            public void onClick01(Message message, int position) {
                //删除
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                delete_mess(message.getMessId()+"",false);
            }

            @Override
            public void onClick02(Message message, int position) {
                //查看
                Intent mIntent =new Intent(getContext(), MailDetailsActivity.class);
                mIntent.putExtra("message2",message.getName());
                mIntent.putExtra("message1", CacheUtils.getUserid(getContext()));
                mIntent.putExtra("message3",message.getInfo());
                mIntent.putExtra("message4",message.getType());
                startActivity(mIntent);
            }

            @Override
            public void onClick03(Message message, int position) {
                //全部
            }

            @Override
            public void onClick04(Message message, int position) {

            }
        });


        mAdapter = new LRecyclerViewAdapter(rvMailboxAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isChooseAll=false;
                nowPage=1;
                isFirst=true;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                get_message_tome();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFirst = false;
                get_message_tome();
            }
        });
        mRecyclerView.refresh();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setMyMailboxActivity(MyMailboxActivity myMailboxActivity) {
        this.myMailboxActivity = myMailboxActivity;
    }


    private void get_message_tome() {

        String url = "/User/get_message_fromme/p/1/size/10";

        if (!isFirst) {
            nowPage++;
            url = "/User/get_message_fromme/p/" + nowPage + "/size/10";
        }
        myMailboxActivity.showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL + url);//发出的消息
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                myMailboxActivity.closeLoading();
                //若fragment没有初始化则不执行
                if (mRecyclerView == null) {
                    return;
                }
                mRecyclerView.refreshComplete(1);
                LogUtil.i("发出的消息" + response);
                get_message_tome info = gson.fromJson(response, get_message_tome.class);
                if (info.getStatus().equals("true")) {
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    if (!isFirst) {
                        nowPage--;
                    }
                    myMailboxActivity.showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                if (!isFirst) {
                    nowPage--;
                }
                mRecyclerView.refreshComplete(1);
                myMailboxActivity.closeLoading();
                myMailboxActivity.showToast("服务器异常");
            }
        }, getContext());
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
                List<String> messId2 = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChoose()) {
                        messId2.add(mList.get(i).getMessId());
                    }
                }
                if (messId2.size() == 0) {
                    myMailboxActivity.showToast("请先选择简历");
                } else {
                    //执行删除
                    String sendresumeId = "";
                    for (int i = 0; i < messId2.size(); i++) {
                        sendresumeId = sendresumeId + messId2.get(i) + ",";
                    }
                    sendresumeId = sendresumeId.substring(0, sendresumeId.length() - 1);
                    delete_mess(sendresumeId, true);
                }

                break;

        }
    }

    private void delete_mess(String messId, final boolean refresh) {

        if (refresh) {
            myMailboxActivity.showLoading();
        }
        HttpRequest httpRequest = new HttpRequest(AppUrl.delete_mess);//删除消息
        httpRequest.add("messId", messId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("删除消息" + response);
                if (refresh) {
                    myMailboxActivity.closeLoading();
                    BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                    if (info.getStatus().equals("true")) {
                        myMailboxActivity.showToast("删除消息");
                        mRecyclerView.refresh();
                    } else {
                        myMailboxActivity.showToast(info.getMessage());
                    }
                }
            }

            @Override
            public void onFailed(Exception exception) {
                if (refresh) {
                    myMailboxActivity.closeLoading();
                }
                myMailboxActivity.showToast("服务器异常");
            }
        }, getContext());
    }

}
