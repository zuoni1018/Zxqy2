package com.zuoni.zxqy.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoni.zxqy.R;

/**
 * Created by zangyi_shuai_ge on 2017/10/16
 */

public abstract class BaseTitleActivity extends BaseActivity {
    private RelativeLayout layoutLeft;
    private TextView tvTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutLeft = (RelativeLayout) findViewById(R.id.layoutLeft);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFinish();
            }
        });
        initDialog();
    }

    private void initDialog() {



    }


    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public  void setBackOnClickListener(View.OnClickListener listener){
        layoutLeft.setOnClickListener(listener);
    }
}
