package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvInvitationInterviewAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.bean.model.InvitationPeople;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.activity.settings.ContactManagerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/24
 */

public class InvitationInterviewActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.layout02)
    LinearLayout layout02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.et04)
    EditText et04;

    @BindView(R.id.et05)
    EditText et05;
    @BindView(R.id.et06)
    EditText et06;
    @BindView(R.id.btSure)
    Button btSure;

    @Override
    public int setLayoutId() {
        return R.layout.activity_invitation_interview;
    }
    ArrayList<InvitationPeople> peoples;
    //    CompanyAddressActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("邀请面试");
        setResult(101);//不成功
         peoples = new ArrayList<>();
        Jobs = new ArrayList<>();
        peoples = (ArrayList<InvitationPeople>) getIntent().getSerializableExtra("peoples");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new RvInvitationInterviewAdapter(getContext(), peoples));
    }

    private void interview_invite( String workerId, String jobId, String name, String tele, String address, String info,String sendresumeId) {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.interview_invite);//邀请面试
        httpRequest.add("workerId", workerId);
        httpRequest.add("info", info);
        httpRequest.add("name", name);

        httpRequest.add("tele", tele);
        httpRequest.add("address", address);
        httpRequest.add("jobId", jobId);


        if(!sendresumeId.equals("")){
            httpRequest.add("sendresumeId", sendresumeId);
        }



        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("邀请面试" + response);

                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);

                if (info.getStatus().equals("true")) {
                    showToast("发布成功");
                    setResult(100);
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

    private Intent mIntent;

    @OnClick({R.id.tv01, R.id.layout02,R.id.btSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv01:
                //选择面试职位
                if (Jobs.size() == 0) {
                    getPosition();
                } else {
                    createPicker(Jobs);
                }
                break;
            case R.id.layout02:
                mIntent = new Intent(getContext(), ContactManagerActivity.class);
                mIntent.putExtra("isChoose", true);
                startActivityForResult(mIntent, 10086);
                break;
            case R.id.btSure:
//                workerId	字符串	M		批量操作多个用逗号隔开
//                jobId	字符串	M		职位ID
//                name	字符串	M		联系人
//                tele	字符串	M		联系方式
//                address	字符串	M		地址
//                info	字符串	M		邀请内容

                String workerId="";
                String sendresumeId="";

                for (int i = 0; i <peoples.size() ; i++) {
                    workerId=workerId+peoples.get(i).getWorkId()+",";
                    sendresumeId=sendresumeId+peoples.get(i).getSendresumeId()+",";
                }
                if(!workerId.equals("")){
                    workerId=workerId.substring(0,workerId.length()-1);
                    sendresumeId=sendresumeId.substring(0,sendresumeId.length()-1);
                }

                if(peoples.get(0).getSendresumeId().equals("")){
                    sendresumeId="";
                }


                String jobId=tv01.getText().toString();
                if(jobId.equals("选择面试职位")){
                    showToast("请先选择面试职位");
                    return;
                }else {
                    for (int i = 0; i <Jobs.size() ; i++) {
                        if(jobId.equals(Jobs.get(i).getTitle())){
                            jobId=Jobs.get(i).getJobId();
                            break;
                        }
                    }
                }
                String name=et03.getText().toString().trim();
                String tele=et04.getText().toString().trim();
                String address=et05.getText().toString().trim();
                String info=et06.getText().toString().trim();


                if(name.equals("")){
                    showToast("请填写联系人");
                }else {
                    if(tele.equals("")){
                        showToast("请填写联系方式");
                    }else {
                        if(address.equals("")){
                            showToast("请填写地址");
                        }else {
                            if(info.equals("")){
                                showToast("请填写邀请内容");
                            }else {
                                interview_invite(workerId,jobId,name,tele,address,info,sendresumeId);
                            }
                        }
                    }
                }




                break;
        }
    }

    private List<Job> Jobs;

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

    private Contact contact;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == 10087) {
            //做了添加或者修改的操作需要更新列表
            contact = (Contact) data.getSerializableExtra("Contact");
            et03.setText(contact.getName());
            et04.setText(contact.getTele());
        }
    }

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
            }
        });

        builder.setData(list);
        builder.create().show();
    }

    @OnClick(R.id.btSure)
    public void onViewClicked() {
    }
}
