package com.zuoni.zxqy.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zangyi_shuai_ge on 2017/10/17
 * 扫一扫支付
 */

public class PaymentActivity extends BaseTitleActivity {


    @BindView(R.id.layout222)
    LinearLayout layout222;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public int setLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageView(this, layout222);
        ButterKnife.bind(this);
//        Glide
//                .with(getContext())
//                .load(GlobalVariable.TEST_IMAGE_URL)
//                .into(image);
    }
}
