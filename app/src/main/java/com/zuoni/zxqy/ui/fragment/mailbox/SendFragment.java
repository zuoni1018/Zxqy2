package com.zuoni.zxqy.ui.fragment.mailbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuoni.zxqy.R;

/**
 * Created by zangyi_shuai_ge on 2017/10/18
 */

public class SendFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_box_get, null);
        return view;
    }
}
