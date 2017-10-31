package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnDoubleDataSelectedListener;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetJobsCate;
import com.zuoni.zxqy.bean.gson.GetSetting;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.activity.settings.ContactManagerActivity;
import com.zuoni.zxqy.view.DataPickerLinkageDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zangyi_shuai_ge on 2017/10/19
 */

public class PostingPositionActivity extends BaseTitleActivity {
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.layout02)
    LinearLayout layout02;
    @BindView(R.id.layout03)
    LinearLayout layout03;
    @BindView(R.id.layout04)
    LinearLayout layout04;
    @BindView(R.id.layout05)
    LinearLayout layout05;
    @BindView(R.id.layout06)
    LinearLayout layout06;
    @BindView(R.id.layout07)
    LinearLayout layout07;
    @BindView(R.id.layout08)
    LinearLayout layout08;
    @BindView(R.id.layout09)
    LinearLayout layout09;
    @BindView(R.id.layout10)
    LinearLayout layout10;
    @BindView(R.id.layout11)
    LinearLayout layout11;

    @BindView(R.id.layout12)
    LinearLayout layout12;
    @BindView(R.id.layout13)
    LinearLayout layout13;
    @BindView(R.id.layout14)
    LinearLayout layout14;
    @BindView(R.id.layout15)
    LinearLayout layout15;
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.tv02)
    TextView tv02;
    @BindView(R.id.tv03)
    TextView tv03;
    @BindView(R.id.tv04)
    TextView tv04;
    @BindView(R.id.tv05)
    TextView tv05;
    @BindView(R.id.et06)
    EditText et06;
    @BindView(R.id.tv07)
    TextView tv07;
    @BindView(R.id.tv08)
    TextView tv08;
    @BindView(R.id.tv09)
    TextView tv09;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.et11)
    EditText et11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.tv13)
    TextView tv13;
    @BindView(R.id.et14)
    EditText et14;
    @BindView(R.id.et16)
    EditText et16;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.bt17)
    Button bt17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("发布新职位");
        getUserCompany();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_posting_position;
    }

    private void getUserCompany() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_SETTING);//行业
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("获取字典" + response);
                GetSetting info = gson.fromJson(response, GetSetting.class);
                if (info.getStatus().equals("true")) {
                    AppSetting.mList = new ArrayList<>();
                    AppSetting.mList.clear();
                    AppSetting.mList.addAll(info.getData());
                } else {
                    showToast(info.getMessage());
                    myFinish();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
                myFinish();
            }
        }, getContext());
    }

    private void getJobsCate() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_JOBS_CATE);//获取职位类别
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("获取职位类别" + response);

                GetJobsCate info = gson.fromJson(response, GetJobsCate.class);
                if (info.getStatus().equals("true")) {
                    info.getData();
                    DataPickerLinkageDialog.Builder builder = new DataPickerLinkageDialog.Builder(getContext());
                    List<String> m = new ArrayList<>();

                    for (int i = 0; i < info.getData().size(); i++) {
                        m.add(info.getData().get(i).getName());
                    }
                    builder.setData(m);
                    builder.setOnDataSelectedListener(new OnDoubleDataSelectedListener() {
                        @Override
                        public void onDataSelectedLeft(String itemValue) {
                        }

                        @Override
                        public void onDataSelectedRight(String itemValue) {
                            tv02.setText(itemValue);

                        }
                    });
                    builder.setList(info.getData());
                    builder.create().show();


                } else {
//                    showToast(info.getMessage());
//                    myFinish();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    //公司性质
    private void createPicker(String data, final TextView textView) {
        if (data == null) {
            return;
        }

        String[] aa = data.split(",");
        if (aa.length == 0) {
            showToast("获取失败");
            return;
        }

        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();
        Collections.addAll(list, aa);
        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                textView.setText(itemValue);
            }
        });

        builder.setData(list);
        builder.create().show();
    }

    private Intent mIntent;

    @OnClick({R.id.layout01, R.id.layout02, R.id.layout03, R.id.layout04, R.id.layout05, R.id.layout06, R.id.layout07, R.id.layout08, R.id.layout09, R.id.layout10, R.id.layout11, R.id.textView, R.id.layout12, R.id.layout13, R.id.layout14, R.id.layout15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout01:
                break;
            case R.id.layout02:
                //职业类别
                getJobsCate();
//                createPicker(AppSetting.getDictionary("comp_jobs"));
                break;
            case R.id.layout03:
                //选择联系人
                mIntent = new Intent(getContext(), ContactManagerActivity.class);
                mIntent.putExtra("isChoose", true);
                startActivityForResult(mIntent, 10086);
                break;
            case R.id.layout04:
                //工作地点
                createPicker("工作地点1,工作地点2,工作地点3", tv04);
                break;
            case R.id.layout05:
                //学历要求
                createPicker(AppSetting.getDictionary("comp_edu"), tv05);

                break;
            case R.id.layout06:
                //户口要求
//                createPicker(AppSetting.getDictionary("comp_edu"));
                break;
            case R.id.layout07:
                //工作类型
                createPicker(AppSetting.getDictionary("comp_jobs"), tv07);
                break;
            case R.id.layout08:
                //工作经验
                createPicker(AppSetting.getDictionary("comp_years"), tv08);

                break;
            case R.id.layout09:
                //月薪
                createPicker(AppSetting.getDictionary("comp_pay"), tv09);
                break;
            case R.id.layout10:
                //性别要求
                createPicker("男,女,不要求", tv10);
                break;
            case R.id.layout11:
                break;

            case R.id.layout12:
                //住房要求
                createPicker(AppSetting.getDictionary("comp_house"), tv12);
                break;
            case R.id.layout13:
                //应聘方式
                createPicker(AppSetting.getDictionary("user_tele"), tv13);

                break;
            case R.id.layout14:
                break;
            case R.id.layout15:
                break;
        }
    }

    private Contact contact;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == 10087) {
            //做了添加或者修改的操作需要更新列表
            contact = (Contact) data.getSerializableExtra("Contact");
            tv03.setText(contact.getName() + "   " + contact.getTele());
        }
    }

    @OnClick(R.id.bt17)
    public void onViewClicked() {
//        title	字符串	M		职位名称
//        contactId	字符串			联系人ID 仅该字段需要ID 其他都传显示内容
//        area	字符串	M		工作地点
//        edu	字符串	M		学历要求
//        hukou	字符串	M		户口要求
//        jobs	字符串	M		工作类型
//        years	字符串			工作经验
//        pay	字符串	M		月薪
//        gender				性别要求 性别要求，0无限制 1男2女
//        ages				年龄要求
//        house				住房要求
//        tele				应聘方式
//        nums				招聘人数
//        info	字符串	M		岗位要求
//        cateName	字符串			职业类型


        String title = et01.getText().toString().trim();
        String contactId = "";
        String area=tv04.getText().toString().trim();
        String edu=tv05.getText().toString().trim();
        String hukou=et06.getText().toString().trim();

        String jobs=tv07.getText().toString().trim();
        String years=tv08.getText().toString().trim();
        String pay=tv09.getText().toString().trim();
        String gender=tv10.getText().toString().trim();
        String ages=et11.getText().toString().trim();

        String house=tv12.getText().toString().trim();
        String tele=tv13.getText().toString().trim();
        String nums=et14.getText().toString().trim();
        String info=et16.getText().toString().trim();
        String cateName=tv02.getText().toString().trim();
        if(isInPut(title)){
            showToast("请填写职位名称");
        }else {
            if(isInPut(cateName)){
                showToast("请选择职位类别");
            }else {

                if(contact==null){
                    showToast("请选择联系人");
                }else {
                    contactId=contact.getContactId();
                    if(isInPut(area)){
                        showToast("请选择工作地点");
                    }else {
                        if(isInPut(edu)){
                            showToast("请选择学历要求");
                        }else {
                            if(isInPut(hukou)){
                                showToast("请填写户口要求");
                            }else {
                                if(isInPut(jobs)){
                                    showToast("请选择工作类型");
                                }else {
                                    if(isInPut(years)){
                                        showToast("请选择工作经验");
                                    }else {
                                        if(isInPut(pay)){
                                            showToast("请选择月薪");
                                        }else {
                                            if(isInPut(gender)){
                                                showToast("请选择性别要求");
                                            }else {
                                                if(isInPut(ages)){
                                                    showToast("请输入年龄要求");
                                                }else {
                                                    if(isInPut(house)){
                                                        showToast("请选择住房要求");
                                                    }else {
                                                        if(isInPut(tele)){
                                                            showToast("请选择应聘方式");
                                                        }else {
                                                            if(isInPut(nums)){
                                                                showToast("请输入招聘人数");
                                                            }else {
                                                                if(isInPut(info)){
                                                                    showToast("请输入岗位要求");
                                                                }else {
                                                                    postPosition(title,contactId,area,edu,hukou
                                                                            ,jobs,years,pay,gender,ages,house,tele,nums,info,cateName);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isInPut(String text) {
        return text.equals("")|text.equals("请选择");
    }

    private void postPosition(String title, String contactId, String area, String edu, String hukou,
                              String jobs, String years, String pay, String gender, String ages,
                              String house, String tele, String nums, String info, String cateName) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.POST_POSITION);//发布
        httpRequest.add("title",title);
        httpRequest.add("contactId", contactId);
        httpRequest.add("area", area);
        httpRequest.add("edu", edu);
        httpRequest.add("hukou", hukou);

        httpRequest.add("jobs",jobs);
        httpRequest.add("years", years);
        httpRequest.add("pay", pay);
        httpRequest.add("gender", gender);
        httpRequest.add("ages", ages);

        httpRequest.add("house",house);
        httpRequest.add("tele", tele);
        httpRequest.add("nums", nums);
        httpRequest.add("info", info);
        httpRequest.add("cateName", cateName);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("发布" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("发布成功");
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
}
