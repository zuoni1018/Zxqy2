package com.zuoni.zxqy.ui.fragment.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvChartListAdapter;
import com.zuoni.zxqy.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zangyi_shuai_ge on 2017/10/16
 */

public class ChartFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.et1)
    EditText et1;

    private View view;
    private MainActivity mainActivity;
    List<RecentContact> mList;
    private RvChartListAdapter mAdapter;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chart, null);
        unbinder = ButterKnife.bind(this, view);
        mList = new ArrayList<>();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    // 将最近联系人列表fragment动态集成进来。
    private void addRecentContactsFragment() {
//      RecentContactsFragment  fragment = new RecentContactsFragment();
//        // 设置要集成联系人列表fragment的布局文件
//        fragment.setContainerId(R.id.messages_fragment);
//
//        final UI activity = (UI) getActivity();
//
//        // 如果是activity从堆栈恢复，FM中已经存在恢复而来的fragment，此时会使用恢复来的，而new出来这个会被丢弃掉
//        fragment = (RecentContactsFragment) activity.addFragment(fragment);
    }

    @OnClick(R.id.bt1)
    public void onViewClicked() {

        NimUIKit.startP2PSession(getContext(), et1.getText().toString().trim(),null);
    }
}
