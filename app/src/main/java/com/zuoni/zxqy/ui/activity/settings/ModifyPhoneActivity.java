package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/30
 */

public class ModifyPhoneActivity extends BaseTitleActivity {
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.bt03)
    Button bt03;


    private boolean isFirstStep = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_modify_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("修改手机号");
        isFirstStep = getIntent().getBooleanExtra("isFirstStep", false);

        if (isFirstStep) {
            et01.setHint("请输入旧手机号");
            bt03.setText("下一步");
        } else {
            et01.setHint("请输入新手机号");
            bt03.setText("确认");
        }


    }

    @OnClick(R.id.bt03)
    public void onViewClicked() {
        if (isFirstStep) {
            myFinish();
            Intent mIntent = new Intent(getContext(), ModifyPhoneActivity.class);
            mIntent.putExtra("isFirstStep", false);
            startActivity(mIntent);
        } else {
            showToast("修改成功");
            myFinish();
        }


    }
}
