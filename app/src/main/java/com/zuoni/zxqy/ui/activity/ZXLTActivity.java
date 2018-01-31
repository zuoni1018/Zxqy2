package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.zuoni.common.utils.ScreenUtils;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 17.12.19
 */

public class ZXLTActivity extends BaseTitleActivity {
    @BindView(R.id.iv01)
    ImageView iv01;
    @BindView(R.id.layoutzzzzzzz)
    LinearLayout layoutzzzzzzz;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.layout4)
    LinearLayout layout4;

    @Override
    public int setLayoutId() {
        return R.layout.activity_zxlt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("众信猎头");
        //动态设置高度
        RelativeLayout.LayoutParams l = (RelativeLayout.LayoutParams) iv01.getLayoutParams();
        int w = ScreenUtils.getScreenWidth(getContext());
        int h = (int) (w * 3246.0000001 / 1125.0000001);//1125 3411
        l.width = w;
        l.height = h;
        iv01.setLayoutParams(l);
        Glide.with(getContext()).load(R.mipmap.xxxxxxxxxx).into(iv01);


        RelativeLayout.LayoutParams l2 = (RelativeLayout.LayoutParams) layoutzzzzzzz.getLayoutParams();
        l2.width = w;
        l2.height = (int) (h * (124 / 815.0001));
        layoutzzzzzzz.setLayoutParams(l2);

    }

    @OnClick({R.id.tv01, R.id.tv02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv01:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "057983840599"));
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

    private Intent mIntent;
    @OnClick({R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                mIntent=new Intent(getContext(),ZXLTDetailsActivity.class);
                mIntent.putExtra("type",0);
                startActivity(mIntent);
                break;
            case R.id.layout2:
                mIntent=new Intent(getContext(),ZXLTDetailsActivity.class);
                mIntent.putExtra("type",1);
                startActivity(mIntent);
                break;
            case R.id.layout3:
                mIntent=new Intent(getContext(),ZXLTDetailsActivity.class);
                mIntent.putExtra("type",2);
                startActivity(mIntent);
                break;
            case R.id.layout4:
                mIntent=new Intent(getContext(),ZXLTDetailsActivity.class);
                mIntent.putExtra("type",3);
                startActivity(mIntent);
                break;
        }
    }
}
