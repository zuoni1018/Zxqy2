package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvPositionManagementAdapter;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class PositionManagementActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    private LRecyclerViewAdapter mAdapter;
    private List<Job> mList;

    @Override
    public int setLayoutId() {
        return R.layout.activity_position_management;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("职位管理");

        mList = new ArrayList<>();
        mAdapter = new LRecyclerViewAdapter(new RvPositionManagementAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        getPosition();
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
                    mList.clear();
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    showToast("获取失败");
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }


}
