package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/11/15
 */

public class MailDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.tv04)
    TextView tv04;

    @Override
    public int setLayoutId() {
        return R.layout.activity_mail_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("短信内容");
        String message1=getIntent().getStringExtra("message1");
        String message2=getIntent().getStringExtra("message2");
        String message3=getIntent().getStringExtra("message3");
        String message4=getIntent().getStringExtra("message4");


        tv01.setText("发件人："+message1);
        tv02.setText("收件人："+message2);
        tv03.setText("类型："+message4);
        tv04.setText("内容："+message3);


    }
}
