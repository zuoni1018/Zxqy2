package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.net.Uri;
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
 * Created by zangyi_shuai_ge on 17.12.25
 */

public class ZXLTDetailsActivity extends BaseTitleActivity {
    @BindView(R.id.tvText)
    TextView tvText;
    @BindView(R.id.layoutzzzzzzz)
    LinearLayout layoutzzzzzzz;

    @Override
    public int setLayoutId() {
        return R.layout.activity_zxlt_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra("type", 0);
        layoutzzzzzzz.setVisibility(View.GONE);
        if (type == 0) {
            tvText.setText(R.string.zxlt_type_0);
            setTitle("猎头简介");
        } else if (type == 1) {
            tvText.setText(R.string.zxlt_type_1);
            setTitle("服务行业");
        } else if (type == 2) {
            tvText.setText(R.string.zxlt_type_2);
            setTitle("收费标准");
        } else if (type == 3) {
            tvText.setVisibility(View.GONE);
            layoutzzzzzzz.setVisibility(View.VISIBLE);
            setTitle("联系方式");
        }
    }

    @OnClick({R.id.tv01, R.id.tv02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv01:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0579-83840599"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
            case R.id.tv02:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "13858924279"));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }
}
