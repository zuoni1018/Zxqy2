package com.zuoni.zxqy.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.MainActivity;

/**
 * Created by zangyi_shuai_ge on 2017/10/16
 */

public class NewsFragment extends Fragment {

    private View view;
    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
//        List<String > mList=new ArrayList<>();
//        mList.add("");
//        mList.add("");

//        LRecyclerViewAdapter mAdapter=new LRecyclerViewAdapter(new RvMainNewsAdapter(getContext(),mList));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


//    @OnClick(R.id.tvTest)
//    public void onViewClicked() {
//        Intent mIntent = new Intent(getContext(), PaymentActivity.class);
//        startActivity(mIntent);
//    }

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
