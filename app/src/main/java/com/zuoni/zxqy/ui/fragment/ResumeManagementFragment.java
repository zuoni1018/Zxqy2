package com.zuoni.zxqy.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvInvitationInterviewRecordAdapter;
import com.zuoni.zxqy.ui.activity.InvitationInterviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class ResumeManagementFragment extends Fragment {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resume_management, null);
        unbinder = ButterKnife.bind(this, view);
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        LRecyclerViewAdapter mAdapter = new LRecyclerViewAdapter(new RvInvitationInterviewRecordAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvInvitationInterview)
    public void onViewClicked() {
        Intent mIntent=new Intent(getContext(), InvitationInterviewActivity.class);
        startActivity(mIntent);
    }
}
