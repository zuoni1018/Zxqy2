package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvInvitationInterviewRecordAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class InvitationInterviewRecordActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_invitation_interview_record;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("邀请面试记录");
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        LRecyclerViewAdapter mAdapter = new LRecyclerViewAdapter(new RvInvitationInterviewRecordAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

    }
}
