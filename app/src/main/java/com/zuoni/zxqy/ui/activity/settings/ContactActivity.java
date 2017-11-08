package com.zuoni.zxqy.ui.activity.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ContactActivity extends BaseTitleActivity {

    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.et04)
    EditText et04;
    @BindView(R.id.et05)
    EditText et05;
    @BindView(R.id.bt06)
    Button bt06;
    private boolean isAdd = false;//是否为添加联系人
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        isAdd = getIntent().getBooleanExtra("isAdd", false);

        if (isAdd) {
            bt06.setText("增加");
            setTitle("增加联系人");

        } else {
            //访问该联系人的基本信息或者从上一个界面传递过来
            bt06.setText("修改");
            setTitle("修改联系人");
            contact = (Contact) getIntent().getSerializableExtra("Contact");
            et01.setText(contact.getName());
            et02.setText(contact.getEmail());
            et03.setText(contact.getTele());
            et04.setText(contact.getFax());
            et05.setText(contact.getAddress());
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_contact;
    }

    @OnClick(R.id.bt06)
    public void onViewClicked() {

//        name	字符串	M		联系人名
//        fax	字符串			传真号码
//        tele	字符串	M		公司联系人电话
//        address	字符串	M		公司地址
//        email	字符串			公司邮箱

        String name = et01.getText().toString().trim();
        String email = et02.getText().toString().trim();
        String tele = et03.getText().toString().trim();
        String fax = et04.getText().toString().trim();
        String address = et05.getText().toString().trim();

        if (name.equals("")) {
            showToast("请填写姓名");
        } else {
            if (tele.equals("")) {
                showToast("请填写联系电话");
            } else {
                if (address.equals("")) {
                    showToast("请填写公司地址");
                } else {
                    if (isAdd) {
                        addContact(name, email, tele, fax, address);
                    } else {
                        updateContact(contact.getContactId(), name, email, tele, fax, address);
                    }
                }
            }
        }
    }


    private void addContact(String name, String email, String tele, String fax, String address) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.ADD_CONTACT);//添加联系人
        httpRequest.add("name", name);
        httpRequest.add("email", email);
        httpRequest.add("tele", tele);
        httpRequest.add("fax", fax);
        httpRequest.add("address", address);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("添加联系人" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("添加成功");
                    setResult(10087);
                    myFinish();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("添加联系人" + exception);
            }
        }, getContext());
    }

    private void updateContact(String contactId, String name, String email, String tele, String fax, String address) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.UPDATE_CONTACT);//修改联系人
        httpRequest.add("contactId", contactId);
        httpRequest.add("name", name);
        httpRequest.add("email", email);
        httpRequest.add("tele", tele);
        httpRequest.add("fax", fax);
        httpRequest.add("address", address);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改联系人" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("修改成功");
                    setResult(10087);
                    myFinish();
                } else {
                    showToast(info.getMessage());
                    setResult(1);
                    myFinish();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("修改联系人" + exception);
            }
        }, getContext());
    }
}
