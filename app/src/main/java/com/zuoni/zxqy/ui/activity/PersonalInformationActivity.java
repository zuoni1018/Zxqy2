package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetCompanyCate;
import com.zuoni.zxqy.bean.gson.GetSetting;
import com.zuoni.zxqy.bean.model.Industry;
import com.zuoni.zxqy.cache.CacheUtils;
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
 * Created by zangyi_shuai_ge on 2017/10/23
 */

public class PersonalInformationActivity extends BaseTitleActivity {
    @BindView(R.id.tvGetAddress)
    EditText tvGetAddress;
    @BindView(R.id.layout02)
    LinearLayout layout02;
    @BindView(R.id.layout03)
    LinearLayout layout03;
    @BindView(R.id.layout04)
    LinearLayout layout04;
    @BindView(R.id.btSure)
    Button btSure;
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.tv05)
    TextView tv05;
    @BindView(R.id.tv06)
    TextView tv06;
    @BindView(R.id.tv07)
    TextView tv07;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivGetAddress)
    ImageView ivGetAddress;


    private List<Industry> industrys;
    private String cateId = "-1";
    private String type = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("个人信息");
        industrys = new ArrayList<>();
        setBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NIMClient.getService(AuthService.class).logout();
                CacheUtils.setAccount("", getContext());
                myFinish();
            }
        });
    }

    @OnClick({R.id.tvGetAddress, R.id.layout02, R.id.layout03, R.id.layout04, R.id.btSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetAddress:

                break;
            case R.id.layout02:
                //所属行业
                getCompanyCate();
                break;
            case R.id.layout03:
                //公司性质
                getUserCompany();
                break;
            case R.id.layout04:
                //公司简介
                Intent mIntent = new Intent(getContext(), CompanyProfileActivity.class);
                mIntent.putExtra("isSetPersonalInfo", true);
                mIntent.putExtra("text", tv07.getText().toString().trim());
                startActivityForResult(mIntent, 10086);
                break;
            case R.id.btSure:
                //提交
                String title = et01.getText().toString().trim();
                String contactName = et02.getText().toString().trim();
                String tele = et03.getText().toString().trim();

                String address = tvGetAddress.getText().toString().trim();
                String cateId = this.cateId;
                String type = tv06.getText().toString().trim();
                String info = tv07.getText().toString().trim();

                if (title.equals("")) {
                    showToast("请输入公司名称");
                } else {
                    if (contactName.equals("")) {
                        showToast("请输入招聘负责人");
                    } else {
                        if (tele.equals("")) {
                            showToast("请输入联系电话");
                        } else {
                            if (address.equals("")) {
                                showToast("请获取公司地址");
                            } else {
                                if (type.equals("公司性质")) {
                                    showToast("请选择公司性质");
                                } else {
                                    if (info.equals("")) {
                                        showToast("请输入公司简介");
                                    } else {
                                        if (info.equals("-1")) {
                                            showToast("请选择所属行业");
                                        } else {
                                            addCompanyInfo(title, contactName, tele, address, cateId, type, info);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    private void addCompanyInfo(String title, String contactName, String tele, String address, String cateId, String type, String info) {

        showLoading();

        HttpRequest httpRequest = new HttpRequest(AppUrl.ADD_COMPANY_INFO);
        httpRequest.add("title", title);
        httpRequest.add("contactName", contactName);
        httpRequest.add("tele", tele);

        httpRequest.add("address", address);
        httpRequest.add("cateId", cateId);
        httpRequest.add("type", type);
        httpRequest.add("info", info);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("添加" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    jumpToActivity(MainActivity.class);
                    CacheUtils.setLogin(true, getContext());
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("添加" + exception);
            }
        }, getContext());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {

            if (resultCode == 10087) {
                //            公司简介传回来的信息
                String text = data.getStringExtra("message");
                if (text != null) {
                    tvGetAddress.setText(text);
                }
            } else if (resultCode == 10088) {
                String text = data.getStringExtra("text");
                if (text != null) {
                    tv07.setText(text);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        //登出
        NIMClient.getService(AuthService.class).logout();
        CacheUtils.setAccount("", getContext());
        myFinish();
    }


    private void getUserCompany() {
        //获取企业信息
        final String data = AppSetting.getDictionary("user_company");
        if (data == null) {
            //重新获取字典
            showLoading();
            HttpRequest httpRequest = new HttpRequest(AppUrl.GET_SETTING);//行业
            httpRequest.add("passwd", "");

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
                        createPicker(AppSetting.getDictionary("user_company"));
                    } else {
                        showToast(info.getMessage());
                    }
                }

                @Override
                public void onFailed(Exception exception) {
                    closeLoading();
                    LogUtil.i("获取字典" + exception);
                }
            }, getContext());
        } else {
            createPicker(data);
        }
    }

    //公司性质
    private void createPicker(String data) {
        if (data == null) {
            return;
        }

        String[] aa = data.split(",");
        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }

        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                tv06.setText(itemValue);
            }
        });

        builder.setData(list);
        builder.create().show();
    }

    private void getCompanyCate() {
        showLoading();

        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_COMPANY_CATE);//行业
        httpRequest.add("passwd", "");

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("行业" + response);
                GetCompanyCate info = gson.fromJson(response, GetCompanyCate.class);
                if (info.getStatus().equals("true")) {
                    DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
                    industrys.clear();
                    industrys.addAll(info.getData());
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < industrys.size(); i++) {
                        list.add(industrys.get(i).getName());
                    }

                    builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
                        @Override
                        public void onDataSelected(String itemValue) {
                            tv05.setText(itemValue);
                            for (int i = 0; i < industrys.size(); i++) {
                                if (itemValue.equals(industrys.get(i).getName())) {
                                    cateId = industrys.get(i).getCateId();
                                    return;
                                }
                            }
                        }
                    });

                    builder.setData(list);
                    builder.create().show();

                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("行业" + exception);
            }
        }, getContext());

    }

    @OnClick(R.id.ivGetAddress)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), CompanyAddressActivity.class);
        startActivityForResult(intent, 10086);
    }

//    @OnClick({R.id.tvGetAddress, R.id.btSure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tvGetAddress:
//                jumpToActivity(CompanyAddressActivity.class);
//                break;
//            case R.id.btSure:
//                break;
//        }
//    }
}
