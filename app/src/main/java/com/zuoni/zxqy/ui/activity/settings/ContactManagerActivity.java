package com.zuoni.zxqy.ui.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvContactManagerAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetContact;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.callback.ContactManagerListener;
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
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class ContactManagerActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;

    private List<Contact> mList;
    private LRecyclerViewAdapter mAdapter;
    private boolean isChoose = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_settings_contact_manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("联系人管理");
        ButterKnife.bind(this);
        isChoose = getIntent().getBooleanExtra("isChoose", false);


        mList = new ArrayList<>();

        RvContactManagerAdapter mRvContactManagerAdapter = new RvContactManagerAdapter(getContext(), mList);
        mRvContactManagerAdapter.setLayout01OnClickListener(new ContactManagerListener() {
            @Override
            public void onClickListener(Contact contact, int pos) {
                deleteContact(contact.getContactId());
            }
        });
        mRvContactManagerAdapter.setLayout02OnClickListener(new ContactManagerListener() {
            @Override
            public void onClickListener(Contact contact, int pos) {
                Intent mIntent = new Intent(getContext(), ContactActivity.class);
                mIntent.putExtra("isAdd", false);
                mIntent.putExtra("Contact", contact);
                startActivityForResult(mIntent, 10086);
            }
        });

        if (isChoose) {
            mRvContactManagerAdapter.setLayoutMainOnClickListener(new ContactManagerListener() {
                @Override
                public void onClickListener(Contact contact, int pos) {
                    Intent mIntent = new Intent();
                    mIntent.putExtra("Contact", contact);
                    setResult(10087, mIntent);
                    myFinish();
                }
            });
        } else {
            mRvContactManagerAdapter.setLayoutMainOnClickListener(new ContactManagerListener() {
                @Override
                public void onClickListener(Contact contact, int pos) {

                }
            });
        }


        mAdapter = new LRecyclerViewAdapter(mRvContactManagerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContact();
            }
        });

        mRecyclerView.refresh();

    }

    @OnClick(R.id.layoutRight)
    public void onViewClicked() {
        Intent mIntent = new Intent(getContext(), ContactActivity.class);
        mIntent.putExtra("isAdd", true);
        startActivityForResult(mIntent, 10086);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == 10087) {
            //做了添加或者修改的操作需要更新列表
            getContact();
        }


    }

    private void getContact() {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_CONTACT);//联系人列表

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                LogUtil.i("联系人列表" + response);
                GetContact info = gson.fromJson(response, GetContact.class);
                if (info.getStatus().equals("true")) {
                    mList.clear();
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    showToast("未添加联系人");
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                LogUtil.i("联系人列表" + exception);
            }
        }, getContext());


    }

    private void deleteContact(String contactId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.DELETE_CONTACT);//删除联系人
        httpRequest.add("contactId", contactId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("删除联系人" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("删除成功");
                    getContact();

                } else {
                    showToast("删除失败");
                    getContact();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("删除联系人" + exception);
            }
        }, getContext());
    }
}
