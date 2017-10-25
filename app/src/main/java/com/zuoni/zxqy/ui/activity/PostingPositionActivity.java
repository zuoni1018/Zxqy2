package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zangyi_shuai_ge on 2017/10/19
 */

public class PostingPositionActivity extends BaseTitleActivity {
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.layout02)
    LinearLayout layout02;
    @BindView(R.id.layout03)
    LinearLayout layout03;
    @BindView(R.id.layout04)
    LinearLayout layout04;
    @BindView(R.id.layout05)
    LinearLayout layout05;
    @BindView(R.id.layout06)
    LinearLayout layout06;
    @BindView(R.id.layout07)
    LinearLayout layout07;
    @BindView(R.id.layout08)
    LinearLayout layout08;
    @BindView(R.id.layout09)
    LinearLayout layout09;
    @BindView(R.id.layout10)
    LinearLayout layout10;
    @BindView(R.id.layout11)
    LinearLayout layout11;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.layout12)
    LinearLayout layout12;
    @BindView(R.id.layout13)
    LinearLayout layout13;
    @BindView(R.id.layout14)
    LinearLayout layout14;
    @BindView(R.id.layout15)
    LinearLayout layout15;

    @Override
    public int setLayoutId() {
        return R.layout.activity_posting_position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("发布新职位");
    }

    @OnClick({R.id.layout01, R.id.layout02, R.id.layout03, R.id.layout04, R.id.layout05, R.id.layout06, R.id.layout07, R.id.layout08, R.id.layout09, R.id.layout10, R.id.layout11, R.id.textView, R.id.layout12, R.id.layout13, R.id.layout14, R.id.layout15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout01:
                break;
            case R.id.layout02:
                break;
            case R.id.layout03:
                break;
            case R.id.layout04:
                break;
            case R.id.layout05:
                break;
            case R.id.layout06:
                break;
            case R.id.layout07:
                break;
            case R.id.layout08:
                break;
            case R.id.layout09:
                break;
            case R.id.layout10:
                break;
            case R.id.layout11:
                break;
            case R.id.textView:
                break;
            case R.id.layout12:
                break;
            case R.id.layout13:
                break;
            case R.id.layout14:
                break;
            case R.id.layout15:
                break;
        }
    }
}
