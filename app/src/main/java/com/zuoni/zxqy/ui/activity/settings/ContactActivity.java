package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ContactActivity extends BaseTitleActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("联系人管理");
        ButterKnife.bind(this);
//        List<String> mList = new ArrayList<>();
//        mList.add("");
//        mList.add("");
//        LRecyclerViewAdapter mAdapter = new LRecyclerViewAdapter(new RvContactManagerAdapter(getContext(), mList));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setAdapter(mAdapter);
    }
}
