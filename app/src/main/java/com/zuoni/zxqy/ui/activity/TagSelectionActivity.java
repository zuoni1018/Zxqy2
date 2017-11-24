package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvTagAdapter;
import com.zuoni.zxqy.bean.gson.get_tags;
import com.zuoni.zxqy.callback.ItemOnClickListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.view.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/11/24
 */

public class TagSelectionActivity extends BaseTitleActivity {
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private List<get_tags.DataBean> hotList;
    private RvTagAdapter mAdapter;
    private ArrayList<String> oldTags;

    @Override
    public int setLayoutId() {
        return R.layout.activity_tag_selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("标签选择");
        tvRight.setText("完成");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                for (int i = 0; i < hotList.size(); i++) {
                    if (hotList.get(i).isChoose()) {
                        result = result + hotList.get(i).getContent() + ",";
                    }
                }
                Intent mIntent = new Intent();
                mIntent.putExtra("result", result);
                setResult(777, mIntent);
                myFinish();
            }
        });
        oldTags = getIntent().getStringArrayListExtra("list");
        if(oldTags==null){
            oldTags=new ArrayList<>();
        }

        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(getContext());
        hotList = new ArrayList<>();
        mRecyclerView.setLayoutManager(flowLayoutManager);
        mAdapter = new RvTagAdapter(getContext(), hotList);
        mAdapter.setItemOnClickListener(new ItemOnClickListener() {
            @Override
            public void onClickListener(int position) {
                if (hotList.get(position).isChoose()) {
                    hotList.get(position).setChoose(false);
                    mAdapter.notifyDataSetChanged();
                } else {
                    int a = 0;
                    for (int i = 0; i < hotList.size(); i++) {
                        if (hotList.get(i).isChoose()) {
                            a++;
                        }
                    }
                    if (a >= 8) {
                        showToast("最多只能选取8个标签");
                    } else {
                        hotList.get(position).setChoose(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        get_tags();
    }

    private void get_tags() {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.get_tags);//获取标签
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("获取标签" + response);
                closeLoading();
                get_tags info = gson.fromJson(response, get_tags.class);
                if (info.getStatus().equals("true")) {
                    if (info.getData() != null) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            for (int j = 0; j < oldTags.size(); j++) {
                                if (info.getData().get(i).getContent().equals(oldTags.get(j))) {
                                    info.getData().get(i).setChoose(true);
                                    break;
                                }
                            }
                        }
                        hotList.addAll(info.getData());
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    showToast(info.getMessage());
                    myFinish();
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
