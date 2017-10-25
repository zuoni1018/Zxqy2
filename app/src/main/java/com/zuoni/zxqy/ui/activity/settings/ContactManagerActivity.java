package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvContactManagerAdapter;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ContactManagerActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_contact_manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("联系人管理");
        ButterKnife.bind(this);
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        LRecyclerViewAdapter mAdapter = new LRecyclerViewAdapter(new RvContactManagerAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }
}
