package com.zuoni.zxqy.ui.activity.resumesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvHotSearchAdapter;
import com.zuoni.zxqy.bean.gson.GetSetting;
import com.zuoni.zxqy.bean.gson.getHotKeyword;
import com.zuoni.zxqy.callback.ItemOnClickListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.view.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/11/3
 */

public class ResumeSearchActivity extends BaseTitleActivity {
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.layoutRight)
    RelativeLayout layoutRight;
    @BindView(R.id.et01)
    EditText et01;
    @BindView(R.id.layout01)
    LinearLayout layout01;
    @BindView(R.id.et02)
    EditText et02;
    @BindView(R.id.layout02)
    LinearLayout layout02;
    @BindView(R.id.et03)
    EditText et03;
    @BindView(R.id.layout03)
    LinearLayout layout03;
    @BindView(R.id.tv04)
    TextView tv04;
    @BindView(R.id.layout04)
    LinearLayout layout04;
    @BindView(R.id.tv05)
    TextView tv05;
    @BindView(R.id.layout05)
    LinearLayout layout05;
    @BindView(R.id.tv06)
    TextView tv06;
    @BindView(R.id.layout06)
    LinearLayout layout06;
    @BindView(R.id.et07)
    EditText et07;
    @BindView(R.id.layout07)
    LinearLayout layout07;
    @BindView(R.id.et08)
    EditText et08;
    @BindView(R.id.layout08)
    LinearLayout layout08;
    @BindView(R.id.tv09)
    TextView tv09;
    @BindView(R.id.layout09)
    LinearLayout layout09;
    @BindView(R.id.et10left)
    EditText et10left;
    @BindView(R.id.et10right)
    EditText et10right;
    @BindView(R.id.layout10)
    LinearLayout layout10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.layout11)
    LinearLayout layout11;
    @BindView(R.id.et12)
    EditText et12;
    @BindView(R.id.layout12)
    LinearLayout layout12;
    @BindView(R.id.bt13)
    Button bt13;
    @BindView(R.id.layoutSearch)
    NestedScrollView layoutSearch;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private boolean isShowMenu = false;

    private int nowPageNum;


    private List<String > hotList;
    private RvHotSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getUserCompany();
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(getContext());
        hotList=new ArrayList<>();
        mRecyclerView.setLayoutManager(flowLayoutManager);
        mAdapter=new RvHotSearchAdapter(getContext(),hotList);
        mRecyclerView.setAdapter(mAdapter);

        etSearch.clearFocus();

        mAdapter.setItemOnClickListener(new ItemOnClickListener() {
            @Override
            public void onClickListener(int position) {
                String searchType = "normal";
                String key = hotList.get(position);
                String hopepostion = "不限";
                String workerId = "不限";
                String jiguan = "不限";

                String img = "不限";
                String lastTime = "不限";
                String sex = "不限";
                String speciality = "不限";
                String jobhistory = "不限";

                String education = "不限";
                String minAge = "不限";
                String maxAge = "不限";
                String hopelocation = "不限";
                String name = "不限";

                Intent mIntent = new Intent(getContext(), ResumeSearchResultActivity.class);
                mIntent.putExtra("searchType", searchType);
                mIntent.putExtra("key", key);
                mIntent.putExtra("hopepostion", hopepostion);
                mIntent.putExtra("workerId", workerId);
                mIntent.putExtra("jiguan", jiguan);

                mIntent.putExtra("img", img);
                mIntent.putExtra("lastTime", lastTime);
                mIntent.putExtra("sex", sex);
                mIntent.putExtra("speciality", speciality);
                mIntent.putExtra("jobhistory", jobhistory);

                mIntent.putExtra("education", education);
                mIntent.putExtra("minAge", minAge);
                mIntent.putExtra("maxAge", maxAge);
                mIntent.putExtra("hopelocation", hopelocation);
                mIntent.putExtra("name", name);
                startActivity(mIntent);

            }
        });


        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

//                    searchType	字符串	M		normal 关键词检索（只需要传key） advanced各类条件检索（全部都要传，未选传不限）
//                    key	字符串	M		关键词 没填则传””空值
//                    hopepostion	字符串	M		求职者期望职位
//                    workerId	字符串	M		求职者编号
//                    jiguan	字符串	M		籍贯
//                    img	字符串	M		是否有头像
//                    lastTime	字符串	M		最近上网日期 单位 天 一个月30天
//                    sex	字符串	M		求职者性别
//                    speciality	字符串	M		求职者专业
//                    jobhistory	字符串	M		求职者工作经历
//                    education	字符串	M		求职者学历
//                    minAge	字符串	M		年龄下限
//                    maxAge	字符串	M		年龄上限
//                    hopelocation	字符串	M		期望工作地点
//                    name	字符串	M		姓名
                    String searchType = "normal";
                    String key = etSearch.getText().toString().trim();
                    String hopepostion = "不限";
                    String workerId = "不限";
                    String jiguan = "不限";

                    String img = "不限";
                    String lastTime = "不限";
                    String sex = "不限";
                    String speciality = "不限";
                    String jobhistory = "不限";

                    String education = "不限";
                    String minAge = "不限";
                    String maxAge = "不限";
                    String hopelocation = "不限";
                    String name = "不限";

                    if (key.equals("")) {
                        showToast("请输入关键字");
                    } else {

                        Intent mIntent = new Intent(getContext(), ResumeSearchResultActivity.class);
                        mIntent.putExtra("searchType", searchType);
                        mIntent.putExtra("key", key);
                        mIntent.putExtra("hopepostion", hopepostion);
                        mIntent.putExtra("workerId", workerId);
                        mIntent.putExtra("jiguan", jiguan);

                        mIntent.putExtra("img", img);
                        mIntent.putExtra("lastTime", lastTime);
                        mIntent.putExtra("sex", sex);
                        mIntent.putExtra("speciality", speciality);
                        mIntent.putExtra("jobhistory", jobhistory);

                        mIntent.putExtra("education", education);
                        mIntent.putExtra("minAge", minAge);
                        mIntent.putExtra("maxAge", maxAge);
                        mIntent.putExtra("hopelocation", hopelocation);
                        mIntent.putExtra("name", name);
                        startActivity(mIntent);
//                        search_resume(searchType,key,hopepostion,workerId,jiguan,img,lastTime,sex,speciality,jobhistory,education,minAge,maxAge,hopelocation,name);
                    }
                    return false;
                }
                return false;
            }

        });

        get_hot_keyword();


    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_resume_search;
    }

    @OnClick({R.id.layoutRight, R.id.layout04, R.id.layout05, R.id.layout06, R.id.layout09, R.id.layout11, R.id.bt13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutRight:
                //右边菜单按钮
                isShowMenu = !isShowMenu;
                if (isShowMenu) {
                    layoutSearch.setVisibility(View.VISIBLE);
                } else {
                    layoutSearch.setVisibility(View.GONE);
                }

                break;
            case R.id.layout01:
                //求职者期望职位
                break;
            case R.id.layout02:
                //求职者编号
                break;
            case R.id.layout03:
                //籍贯
                break;
            case R.id.layout04:
                //有无照片
                createPicker("有,无", tv04);
                break;
            case R.id.layout05:
                //最近上网日期
                createPicker("一天内,三天内,一周内,半月内,一个月内,三个月内,半年内,一年内", tv05);
                break;
            case R.id.layout06:
                //求职者性别
                createPicker("男,女", tv06);
                break;
            case R.id.layout07:
                break;
            case R.id.layout08:
                break;
            case R.id.layout09:
                //学历要求
                createPicker(AppSetting.getDictionary("comp_edu"), tv09);
                break;
            case R.id.layout10:
                break;
            case R.id.layout11:
                //工作地点
                createPicker(AppSetting.getDictionary("citys"), tv11);
                break;
            case R.id.layout12:
                break;
            case R.id.bt13:
                String searchType = "advanced";
                String key = "";

                String hopepostion = "不限";
                String workerId = "不限";
                String jiguan = "不限";

                String img = "不限";
                String lastTime = "不限";
                String sex = "不限";
                String speciality = "不限";
                String jobhistory = "不限";

                String education = "不限";
                String minAge = "不限";
                String maxAge = "不限";
                String hopelocation = "不限";
                String name = "不限";

                hopepostion = initString(et01.getText().toString().trim());
                workerId = initString(et02.getText().toString().trim());
                jiguan = initString(et03.getText().toString().trim());
                img = initString(tv04.getText().toString().trim());
                lastTime = initString(tv05.getText().toString().trim());

                sex = initString(tv06.getText().toString().trim());
                speciality = initString(et07.getText().toString().trim());
                jobhistory = initString(et08.getText().toString().trim());
                education = initString(tv09.getText().toString().trim());
                minAge = initString(et10left.getText().toString().trim());

                maxAge = initString(et10right.getText().toString().trim());
                hopelocation = initString(tv11.getText().toString().trim());
                name = initString(et12.getText().toString().trim());
                //立即搜索
                Intent mIntent = new Intent(getContext(), ResumeSearchResultActivity.class);
                mIntent.putExtra("searchType", searchType);
                mIntent.putExtra("key", key);
                mIntent.putExtra("hopepostion", hopepostion);
                mIntent.putExtra("workerId", workerId);
                mIntent.putExtra("jiguan", jiguan);

                mIntent.putExtra("img", img);
                mIntent.putExtra("lastTime", lastTime);
                mIntent.putExtra("sex", sex);
                mIntent.putExtra("speciality", speciality);
                mIntent.putExtra("jobhistory", jobhistory);

                mIntent.putExtra("education", education);
                mIntent.putExtra("minAge", minAge);
                mIntent.putExtra("maxAge", maxAge);
                mIntent.putExtra("hopelocation", hopelocation);
                mIntent.putExtra("name", name);
                startActivity(mIntent);

                break;
        }
    }


    private String initString(String str) {

        if (str.equals("") | str.equals("请选择")) {
            return "不限";
        }
        return str;
    }
    private void get_hot_keyword() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.get_hot_keyword);//热门

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("热门" + response);

                getHotKeyword info = gson.fromJson(response, getHotKeyword.class);

                if (info.getStatus().equals("true")) {
                    hotList.clear();
                    hotList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
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
        list.add("不限");
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
}
