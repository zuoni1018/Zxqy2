package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvInvitationInterviewAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/24
 */

public class InvitationInterviewActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_invitation_interview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("邀请面试");
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
//        mList.add("");
//        mList.add("");
//        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new RvInvitationInterviewAdapter(getContext(), mList));
    }
}
