package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class RemarksActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.bt01)
    Button bt01;


    @Override
    public int setLayoutId() {
        return R.layout.activity_company_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("备注");
        ButterKnife.bind(this);
        String text = getIntent().getStringExtra("text");
        et01.setText(text);
        et01.setSelection(et01.getText().length());
    }

    @OnClick(R.id.bt01)
    public void onViewClicked() {
        String info = et01.getText().toString().trim();
        if (info.equals("")) {
            showToast("请输入备注");
        } else {
            Intent mIntent = new Intent();
            mIntent.putExtra("Remarks", et01.getText().toString());
            setResult(1001, mIntent);
            myFinish();
        }
    }


}
