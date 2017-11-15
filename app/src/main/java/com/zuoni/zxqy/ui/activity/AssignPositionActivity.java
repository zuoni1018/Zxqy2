package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/11/14
 */

public class AssignPositionActivity extends BaseTitleActivity {
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.llayout02)
    LinearLayout llayout02;
    @BindView(R.id.bt03)
    Button bt03;
    private List<Job> Jobs;
    private String favId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_assign_position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("分配职位");
        favId = getIntent().getStringExtra("favId");
        Jobs = new ArrayList<>();
    }

    @OnClick({R.id.layout01, R.id.llayout02, R.id.bt03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout01:
                //选择面试职位
                if (Jobs.size() == 0) {
                    getPosition();
                } else {
                    createPicker(Jobs);
                }
                break;
            case R.id.llayout02:
                //写备注
                Intent mIntent = new Intent(getContext(), RemarksActivity.class);
                mIntent.putExtra("text", tv02.getText().toString().trim());
                startActivityForResult(mIntent, 100);
                break;
            case R.id.bt03:
                //提交
                if (jobId.equals("")) {
                    showToast("请选择分配职位");
                    return;
                }
                String remark = tv02.getText().toString().trim();

                assign_fav_job(favId, jobId, remark);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 1001) {
            tv02.setText(data.getStringExtra("Remarks"));
        }
    }

    private void assign_fav_job(String favId, String jobId, String remark) {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.assign_fav_job);//分配职位
        httpRequest.add("favId", favId);
        httpRequest.add("jobId", jobId);
        httpRequest.add("remark", remark);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("分配职位" + response);

                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("分配成功");
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    private void getPosition() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_POSITION);//获取发布职位
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("获取发布职位" + response);

                GetPosition info = gson.fromJson(response, GetPosition.class);
                if (info.getStatus().equals("true")) {
                    if (Jobs == null) {
                        Jobs = new ArrayList<>();
                    }
                    Jobs.clear();
                    Jobs.addAll(info.getData());
                    createPicker(Jobs);
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    private String jobId = "";

    //公司性质
    private void createPicker(List<Job> jobs) {

        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();
//        list.add("不限");
        for (int i = 0; i < jobs.size(); i++) {
            list.add(jobs.get(i).getTitle());
        }

        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                tv01.setText(itemValue);
                for (int i = 0; i < Jobs.size(); i++) {
                    if (Jobs.get(i).getTitle().equals(itemValue)) {
                        jobId = Jobs.get(i).getJobId();
                        break;
                    }
                }
            }
        });

        builder.setData(list);
        builder.create().show();
    }
}
