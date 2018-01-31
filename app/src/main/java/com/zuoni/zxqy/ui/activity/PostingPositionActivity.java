package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnDoubleDataSelectedListener;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.DensityUtils;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppSetting;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvPostingTagAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetContact;
import com.zuoni.zxqy.bean.gson.GetJobsCate;
import com.zuoni.zxqy.bean.gson.GetSetting;
import com.zuoni.zxqy.bean.gson.getPositionDetail;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.callback.OnRowsCallbackListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;
import com.zuoni.zxqy.ui.activity.settings.ContactManagerActivity;
import com.zuoni.zxqy.ui.fragment.main.HomeFragment;
import com.zuoni.zxqy.view.DataPickerLinkageDialog;
import com.zuoni.zxqy.view.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zuoni.zxqy.AppUrl.get_position_detail;


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
    @BindView(R.id.tv18)
    TextView tv18;
    @BindView(R.id.rv18)
    RecyclerView rv18;
    @BindView(R.id.layout18)
    LinearLayout layout18;
    @BindView(R.id.tvzzzzzzz)
    TextView tvzzzzzzz;
    @BindView(R.id.ivzzzzzzz)
    ImageView ivzzzzzzz;
    @BindView(R.id.layoutzzzzzzz)
    LinearLayout layoutzzzzzzz;

    private boolean isAdd = true;
    private String jobId = "";
    private ArrayList<String> tags;
    private RvPostingTagAdapter mAdapter;
    //获取联系人列表
    private void getContact() {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_CONTACT);//联系人列表
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("联系人列表" + response);
                GetContact info = gson.fromJson(response, GetContact.class);
                if (info.getStatus().equals("true")) {
                    try {
                        contact=    info.getData().get(0);
                        tv03.setText(contact.getName() + "   " + contact.getTele());
                    }catch (Exception e){

                    }

//                    mList.clear();
//                    mList.addAll(info.getData());
//                    mAdapter.notifyDataSetChanged();
                } else {
                    showToast("未添加联系人");
//                    mList.clear();
//                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("联系人列表" + exception);
            }
        }, getContext());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("发布新职位");
        tv18.setVisibility(View.VISIBLE);
        rv18.setVisibility(View.GONE);
        tags = new ArrayList<>();
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(getContext());
        flowLayoutManager.setOnRowsCallbackListener(new OnRowsCallbackListener() {
            @Override
            public void onRowsCallback(int row) {
                LogUtil.i("行号回调" + row);
                if (needDo) {
                    needDo = false;
                    final LinearLayout.LayoutParams para1;
                    para1 = (LinearLayout.LayoutParams) rv18.getLayoutParams();
                    para1.height = DensityUtils.dp2px(getContext(), 27) * (row + 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rv18.setLayoutParams(para1);
                        }
                    }, 50);
                }
//                rv18.setLayoutParams(para1);
//                mAdapter.notifyDataSetChanged();
            }
        });
        rv18.setLayoutManager(flowLayoutManager);
        mAdapter = new RvPostingTagAdapter(getContext(), tags);

        rv18.setAdapter(mAdapter);
        getUserCompany();

        isAdd = getIntent().getBooleanExtra("isAdd", true);
        if (!isAdd) {
            jobId = getIntent().getStringExtra("jobId");
            setTitle("修改职位");
            get_position_detail(jobId);
        }else {
            //先设置联系人

            getContact();

        }
        //设置可约聊次数
//        HomeFragment.chatLast.equals("0")

        if(isAdd){
            if(HomeFragment.chatLast.equals("0")){
                tvzzzzzzz.setText("当前约聊次数为0"+"次，是否发布可约聊岗位");
                ivzzzzzzz.setImageResource(R.mipmap.zx_108);
                isKeYueLiao="0";//当前为不可约聊
                layoutzzzzzzz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("当前约聊次数为0");
                    }
                });
            }else {
                tvzzzzzzz.setText("当前约聊次数为"+HomeFragment.chatLast+"次，是否发布可约聊岗位");
                ivzzzzzzz.setImageResource(R.mipmap.zx_108);
                layoutzzzzzzz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isKeYueLiao.equals("0")){
                            isKeYueLiao="1";
                        }else {
                            isKeYueLiao="0";
                        }
                        if(isKeYueLiao.equals("0")){
                            ivzzzzzzz.setImageResource(R.mipmap.zx_108);
                        }else {
                            ivzzzzzzz.setImageResource(R.mipmap.zx_107);
                        }
                    }
                });
            }
        }else {
            layoutzzzzzzz.setVisibility(View.GONE);
//            tvzzzzzzz.setTextColor(Color.parseColor("#999999"));
//            layoutzzzzzzz.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });


        }





    }
    private String  isKeYueLiao="0";

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

    private void get_position_detail(String jobId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(get_position_detail);//职位详情
        httpRequest.add("jobId", jobId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("职位详情" + response);
                getPositionDetail info = gson.fromJson(response, getPositionDetail.class);
                if (info.getStatus().equals("true")) {
                    contact = new Contact();
                    contact.setContactId(info.getData().getContactId());

                    et01.setText(info.getData().getTitle());
                    tv02.setText(info.getData().getCateName());
                    tv03.setText(info.getData().getContactName());
                    tv04.setText(info.getData().getArea());
                    tv05.setText(info.getData().getEdu());

                    et06.setText(info.getData().getHukou());
                    tv08.setText(info.getData().getYears());
                    tv07.setText(info.getData().getJobs());
                    tv09.setText(info.getData().getPay());
                    tv10.setText(info.getData().getGender());

                    et11.setText(info.getData().getAges());
                    tv12.setText(info.getData().getHouse());
                    tv13.setText(info.getData().getTele());
                    if (info.getData().getNums().equals("0")) {
                        et14.setHint("若干");
                    } else {
                        et14.setText(info.getData().getNums());
                    }
                    et16.setText(info.getData().getInfo());
                    if (!info.getData().getTag().equals("")) {

                        String[] a = info.getData().getTag().split(",");
                        tags.clear();
                        tags.addAll(Arrays.asList(a));

                        if (tags.size() > 0) {
                            tv18.setVisibility(View.GONE);
                            rv18.setVisibility(View.VISIBLE);
                        } else {
                            tv18.setVisibility(View.VISIBLE);
                            rv18.setVisibility(View.GONE);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
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
                createPicker(AppSetting.getDictionary("citys"), tv04);
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
                createPicker("男,女", tv10);
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
        } else if (requestCode == 666 && resultCode == 777) {

            String result = data.getStringExtra("result");
            LogUtil.i("标签选择回调", result);
            if (result != null) {
                String[] a = result.split(",");
                tags.clear();
                tags.addAll(Arrays.asList(a));
                if (result.equals("")) {
                    tags.clear();
                }
                if (tags.size() > 0) {
                    tv18.setVisibility(View.GONE);
                    rv18.setVisibility(View.VISIBLE);
                } else {
                    tv18.setVisibility(View.VISIBLE);
                    rv18.setVisibility(View.GONE);
                }
                mAdapter.notifyDataSetChanged();
            }
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


        final String title = et01.getText().toString().trim();
        String contactId = "";
        final String area = tv04.getText().toString().trim();
        final String edu = tv05.getText().toString().trim();
        final String hukou = et06.getText().toString().trim();

        final String jobs = tv07.getText().toString().trim();
        final String years = tv08.getText().toString().trim();
        final String pay = tv09.getText().toString().trim();
        final String gender = tv10.getText().toString().trim();
        final String ages = et11.getText().toString().trim();

        final String house = tv12.getText().toString().trim();
        final String tele = tv13.getText().toString().trim();
        String nums = et14.getText().toString().trim();//招聘人数
        final String info = et16.getText().toString().trim();
        final String cateName = tv02.getText().toString().trim();

        //0为若干
        if (nums.equals("")) {
            nums = "0";
        }

        if (isInPut(title)) {
            showToast("请填写职位名称");
        } else {
            if (isInPut(cateName)) {
                showToast("请选择职位类别");
            } else {
                if (contact == null) {
                    showToast("请选择联系人");
                } else {
                    contactId = contact.getContactId();
                    if (isInPut(area)) {
                        showToast("请选择工作地点");
                    } else {
                        if (isInPut(edu)) {
                            showToast("请选择学历要求");
                        } else {
                            if (isInPut("222")) {
                                showToast("请填写户口要求");
                            } else {
                                if (isInPut(jobs)) {
                                    showToast("请选择工作类型");
                                } else {
                                    if (isInPut(years)) {
                                        showToast("请选择工作经验");
                                    } else {
                                        if (isInPut(pay)) {
                                            showToast("请选择月薪");
                                        } else {
                                            if (isInPut(gender)) {
                                                showToast("请选择性别要求");
                                            } else {
                                                if (isInPut(ages)) {
                                                    showToast("请输入年龄要求");
                                                } else {
                                                    if (isInPut(house)) {
                                                        showToast("请选择住房要求");
                                                    } else {
                                                        if (isInPut(tele)) {
                                                            showToast("请选择应聘方式");
                                                        } else {
//                                                            if (isInPut(nums)) {
//                                                                showToast("请输入招聘人数");
//                                                            } else {
                                                            if (isInPut("222")) {
                                                                showToast("请输入岗位要求");
                                                            } else {
                                                                if (isAdd) {
                                                                    postPosition(title, contactId, area, edu, hukou
                                                                                        , jobs, years, pay, gender, ages, house, tele, nums, info, cateName, isKeYueLiao);

//                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                                                                    if (HomeFragment.chatLast.equals("0")) {
//                                                                        builder.setMessage("剩余可发布约聊岗位0次。是否发布不可约聊岗位?");
//                                                                        final String finalContactId = contactId;
//                                                                        final String finalNums1 = nums;
//                                                                        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
//                                                                            @Override
//                                                                            public void onClick(DialogInterface dialog, int which) {
//                                                                                postPosition(title, finalContactId, area, edu, hukou
//                                                                                        , jobs, years, pay, gender, ages, house, tele, finalNums1, info, cateName, "0");
//
//                                                                            }
//                                                                        });
//                                                                        builder.setNegativeButton("否", null);
//                                                                    } else {
//                                                                        final String finalContactId = contactId;
//                                                                        builder.setMessage("剩余可发布约聊岗位" + HomeFragment.chatLast + "次 是否发布可约聊岗位?");
//                                                                        final String finalNums = nums;
//                                                                        builder.setPositiveButton("可约聊", new DialogInterface.OnClickListener() {
//                                                                            @Override
//                                                                            public void onClick(DialogInterface dialog, int which) {
//                                                                                postPosition(title, finalContactId, area, edu, hukou
//                                                                                        , jobs, years, pay, gender, ages, house, tele, finalNums, info, cateName, "1");
//                                                                            }
//                                                                        });
//                                                                        final String finalNums2 = nums;
//                                                                        builder.setNegativeButton("不可约聊", new DialogInterface.OnClickListener() {
//                                                                            @Override
//                                                                            public void onClick(DialogInterface dialog, int which) {
//                                                                                postPosition(title, finalContactId, area, edu, hukou
//                                                                                        , jobs, years, pay, gender, ages, house, tele, finalNums2, info, cateName, "0");
//                                                                            }
//                                                                        });
//                                                                    }
//                                                                    builder.create().show();
                                                                } else {
                                                                    update_position(title, contactId, area, edu, hukou
                                                                            , jobs, years, pay, gender, ages, house, tele, nums, info, cateName);
                                                                }
                                                            }
//                                                            }
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
        return text.equals("") | text.equals("请选择");
    }

    private void postPosition(String title, String contactId, String area, String edu, String hukou,
                              String jobs, String years, String pay, String gender, String ages,
                              String house, String tele, String nums, String info, String cateName, String chat) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.POST_POSITION);//发布
        httpRequest.add("title", title);
        httpRequest.add("contactId", contactId);
        httpRequest.add("area", area);
        httpRequest.add("edu", edu);
        httpRequest.add("hukou", hukou);

        httpRequest.add("jobs", jobs);
        httpRequest.add("years", years);
        httpRequest.add("pay", pay);
        httpRequest.add("gender", gender);
        httpRequest.add("ages", ages);

        httpRequest.add("house", house);
        httpRequest.add("tele", tele);
        httpRequest.add("nums", nums);
        httpRequest.add("info", info);
        httpRequest.add("cateName", cateName);
        httpRequest.add("chat", chat);

        String tag = "";
        for (int i = 0; i < tags.size(); i++) {
            tag = tag + tags.get(i) + ",";
        }
        if (!tag.equals("")) {
            tag = tag.substring(0, tag.length() - 1);
        }
        httpRequest.add("tag", tag);

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("发布" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("发布成功");
                    setResult(10087);
                    myFinish();
                    jumpToActivity(PositionManagementActivity.class);
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

    private void update_position(String title, String contactId, String area, String edu, String hukou,
                                 String jobs, String years, String pay, String gender, String ages,
                                 String house, String tele, String nums, String info, String cateName) {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.update_position);//修改
        httpRequest.add("title", title);
        httpRequest.add("contactId", contactId);
        httpRequest.add("area", area);
        httpRequest.add("edu", edu);
        httpRequest.add("hukou", hukou);

        httpRequest.add("jobs", jobs);
        httpRequest.add("years", years);
        httpRequest.add("pay", pay);
        httpRequest.add("gender", gender);
        httpRequest.add("ages", ages);

        httpRequest.add("house", house);
        httpRequest.add("tele", tele);
        httpRequest.add("nums", nums);
        httpRequest.add("info", info);
        httpRequest.add("cateName", cateName);

        httpRequest.add("jobId", jobId);

        //
        String tag = "";
        for (int i = 0; i < tags.size(); i++) {
            tag = tag + tags.get(i) + ",";
        }
        if (!tag.equals("")) {
            tag = tag.substring(0, tag.length() - 1);
        }
        httpRequest.add("tag", tag);


        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("修改" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("修改成功");
                    setResult(10087);
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


    private boolean needDo = true;

    @OnClick({R.id.rv18, R.id.layout18})
    public void onlayout18Clicked() {
        needDo = true;
        Intent mIntent = new Intent(getContext(), TagSelectionActivity.class);
        mIntent.putStringArrayListExtra("list", tags);
        startActivityForResult(mIntent, 666);
    }

}
