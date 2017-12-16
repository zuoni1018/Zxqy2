package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 */

public class ForgetActivity2 extends BaseTitleActivity {


    @BindView(R.id.btSure)
    Button btSure;

    @Override
    public int setLayoutId() {
        return R.layout.activity_forget2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("忘记密码");
    }

    @OnClick(R.id.btSure)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1234567890"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
