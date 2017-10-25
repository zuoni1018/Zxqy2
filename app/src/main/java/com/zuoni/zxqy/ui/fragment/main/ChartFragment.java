package com.zuoni.zxqy.ui.fragment.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvChartListAdapter;
import com.zuoni.zxqy.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/16
 */

public class ChartFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private View view;
    private MainActivity mainActivity;
    List<RecentContact > mList;
    private RvChartListAdapter mAdapter;
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, null);
        unbinder = ButterKnife.bind(this, view);
         mList=new ArrayList<>();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter=new RvChartListAdapter(getContext(),mList);
        mRecyclerView.setAdapter(mAdapter);

        //获取最近列表
        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        LogUtil.i("最近联系人列表");
                        mList.clear();
                        mList.addAll(recents);
                        mAdapter.notifyDataSetChanged();

                        for (int i = 0; i < recents.size(); i++) {
                            RecentContact mRecentContact = recents.get(i);
                            LogUtil.i("最近联系人列表" + mRecentContact.toString());

                        }
                    }
                });

        //  创建观察者对象
        Observer<List<RecentContact>> messageObserver =
                new Observer<List<RecentContact>>() {
                    @Override
                    public void onEvent(List<RecentContact> messages) {
                        LogUtil.i("最近联系人列表");
                        mList.clear();
                        mList.addAll(messages);
                        mAdapter.notifyDataSetChanged();
                        for (int i = 0; i < messages.size(); i++) {
                            RecentContact mRecentContact = messages.get(i);
                            LogUtil.i("最近联系人列表" + mRecentContact.toString());

                        }
                    }
                };
//  注册/注销观察者
        NIMClient.getService(MsgServiceObserve.class)
                .observeRecentContact(messageObserver, true);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    Intent mIntent;
//    @OnClick({R.id.btRegister, R.id.btLogin})
//    public void mainLoginView(View view) {
//        switch (view.getId()) {
//            case R.id.btRegister:
//                mIntent=new Intent(getContext(), RegisterActivity.class);
//                startActivity(mIntent);
//                break;
//            case R.id.btLogin:
//                mIntent=new Intent(getContext(), LoginActivity.class);
//                startActivity(mIntent);
//                break;
//
//        }
//    }
}
