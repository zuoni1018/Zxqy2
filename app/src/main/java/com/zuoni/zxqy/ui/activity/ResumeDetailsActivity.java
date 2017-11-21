package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.joooonho.SelectableRoundedImageView;
import com.netease.nim.uikit.NimUIKit;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvWorkExperienceAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.ResumeDetail;
import com.zuoni.zxqy.bean.model.InvitationPeople;
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
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class ResumeDetailsActivity extends BaseTitleActivity {


    @BindView(R.id.head)
    SelectableRoundedImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.info01)
    TextView info01;
    @BindView(R.id.info02)
    TextView info02;
    @BindView(R.id.info03)
    TextView info03;
    @BindView(R.id.info04)
    TextView info04;
    @BindView(R.id.rv01)
    RecyclerView rv01;


    @BindView(R.id.hopepostion)
    TextView hopepostion;
    @BindView(R.id.info05)
    TextView info05;
    @BindView(R.id.info06)
    TextView info06;
    @BindView(R.id.educationhistory)
    TextView educationhistory;
    @BindView(R.id.specialty01)
    TextView specialty01;
    @BindView(R.id.specialty02)
    TextView specialty02;
    @BindView(R.id.specialty03)
    TextView specialty03;
    @BindView(R.id.specialty04)
    TextView specialty04;
    @BindView(R.id.contact01)
    TextView contact01;
    @BindView(R.id.contact02)
    TextView contact02;
    @BindView(R.id.contact03)
    TextView contact03;
    @BindView(R.id.layoutContact01)
    LinearLayout layoutContact01;
    @BindView(R.id.btGet)
    Button btGet;
    @BindView(R.id.layoutContact02)
    LinearLayout layoutContact02;
    @BindView(R.id.education01)
    TextView education01;
    @BindView(R.id.education02)
    TextView education02;
    @BindView(R.id.education03)
    TextView education03;
    @BindView(R.id.btBottomMenu01)
    Button btBottomMenu01;
    @BindView(R.id.btBottomMenu02)
    Button btBottomMenu02;
    @BindView(R.id.btBottomMenu03)
    Button btBottomMenu03;
    @BindView(R.id.layoutBottomMenu)
    LinearLayout layoutBottomMenu;
    private String name2;
    private String workId = "";

    private RvWorkExperienceAdapter mAdapter;

    private List<ResumeDetail.DataBean.JobhistoryBean> jobhistorys;
    private String sendresumeId;



    private InvitationPeople invitationPeople;

    @Override
    public int setLayoutId() {
        return R.layout.activity_resume_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        name2 = getIntent().getStringExtra("name");
        workId = getIntent().getStringExtra("workId");
        sendresumeId = getIntent().getStringExtra("sendresumeId");

        invitationPeople=new InvitationPeople();
        if(sendresumeId!=null){
            invitationPeople.setSendresumeId(sendresumeId);
        }
        invitationPeople.setWorkId(workId);
        invitationPeople.setWorkName("");

        setTitle(name2 + "的简历");
        if (workId.equals("")) {
            myFinish();
        } else {
            resume_detail(workId);
        }

        //工作经历
        jobhistorys = new ArrayList<>();

        mAdapter = new RvWorkExperienceAdapter(getContext(), jobhistorys);
        //禁止滑动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv01.setLayoutManager(linearLayoutManager);
        rv01.setAdapter(mAdapter);


    }

    private String iddd="123456";


    private void resume_detail(String workerId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.resume_detail);//简历详情
        httpRequest.add("workerId", workerId);
        if (sendresumeId != null) {
            httpRequest.add("sendresumeId", sendresumeId);
        }

        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("简历详情" + response);
                ResumeDetail info = gson.fromJson(response, ResumeDetail.class);
                if (info.getStatus().equals("true")) {

//                    iddd=info.getData().getId();
                    iddd=info.getData().getAccid();
                    //头像
                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.zx_113)
                            .error(R.mipmap.zx_113);
                    Glide.with(getContext().getApplicationContext())
                            .asBitmap()
                            .load(info.getData().getImg())
                            .apply(requestOptions)
                            .into(head);
                    invitationPeople.setHeadUrl(info.getData().getImg());

                    //姓名
                    name.setText(info.getData().getName());
                    setTitle(info.getData().getName() + "的简历");
                    invitationPeople.setName(info.getData().getName());
                    //info01  性别+年龄+身高+体重 男I26岁I175cmI63kg

                    info01.setText(info.getData().getSex() + "I" + info.getData().getAge() + "岁" + "I" + info.getData().getHeight() + "cm I" + info.getData().getWeight() + "kg");

                    info02.setText(info.getData().getPhone());

//                    户籍：重庆   所在地：杭州

                    info03.setText("户籍：" + info.getData().getJiguan() + "   所在地：" + info.getData().getHome());

//                    未婚 团员 1年以上工作经验

                    info04.setText(info.getData().getMarry() + "  " + info.getData().getPolitical() + " " + info.getData().getJobyear());

                    hopepostion.setText(info.getData().getHopepostion());//岗位
//                    全职I永嘉市I面议I提供住宿
                    info05.setText(info.getData().getRequestjobtype() + "I" + info.getData().getHopelocation() + "I" + info.getData().getRequestsalary() + "I" + info.getData().getRequeststay());

                    info06.setText(info.getData().getJobstatus());


                    if (info.getData().getJobhistory() != null) {
                        jobhistorys.addAll(info.getData().getJobhistory());
                        mAdapter.notifyDataSetChanged();
                    }

                    education01.setText(info.getData().getGraduatedfrom());
                    education02.setText(info.getData().getEducation());
                    education03.setText(info.getData().getGraduatetime());

                    educationhistory.setText(info.getData().getEducationhistory());
//                    第一外语 英语 一般
                    specialty01.setText("第一外语 " + info.getData().getForeignlanguage() + " " + info.getData().getForeignlanguagelevel());

//                    计算机水平一般

                    specialty02.setText("计算机水平 " + info.getData().getComputerlevel());

//                    相关证书：无

                    specialty03.setText("相关证书：" + info.getData().getCertificate());
//                    "其他能力：无

                    specialty04.setText("其他能力：" + info.getData().getOtherability());

                    contact01.setText(info.getData().getPhone());

                    contact02.setText("QQ:" + info.getData().getQq() + "   " + info.getData().getEmail());

                    contact03.setText(info.getData().getAddress());

                    if (!info.getData().isPermit()) {
                        layoutContact01.setVisibility(View.GONE);
                        layoutContact02.setVisibility(View.VISIBLE);
                        layoutBottomMenu.setVisibility(View.GONE);
                    } else {
                        layoutContact01.setVisibility(View.VISIBLE);
                        layoutContact02.setVisibility(View.GONE);
                        layoutBottomMenu.setVisibility(View.VISIBLE);
                    }

                } else {
                    showToast(info.getMessage());
                    myFinish();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("简历详情" + exception);
            }
        }, getContext());
    }

    private void view_contact(String workerId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.view_contact);//查看简历联系方式
        httpRequest.add("workerId", workerId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("查看简历联系方式" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    layoutContact01.setVisibility(View.VISIBLE);
                    layoutContact02.setVisibility(View.GONE);
                    layoutBottomMenu.setVisibility(View.VISIBLE);
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("简历详情" + exception);
            }
        }, getContext());
    }

    private void add_company_fav(String workerId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.add_company_fav);//收藏简历
        httpRequest.add("workerId", workerId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("收藏简历" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    showToast("收藏成功");
                    btBottomMenu03.setText("已收藏");
                    btBottomMenu03.setClickable(false);
                } else {
                    showToast(info.getMessage());
                    btBottomMenu03.setText("已收藏");
                    btBottomMenu03.setClickable(false);
                }
            }

            @Override
            public void onFailed(Exception exception) {
                closeLoading();
                LogUtil.i("简历详情" + exception);
            }
        }, getContext());
    }


    @OnClick(R.id.btGet)
    public void onViewClicked() {
        view_contact(workId);
    }

    @OnClick({R.id.btBottomMenu01, R.id.btBottomMenu02, R.id.btBottomMenu03})
    public void onBottomMenuClick(View view) {
        switch (view.getId()) {
            case R.id.btBottomMenu01:
                //邀请面试
                ArrayList<InvitationPeople> peoples=new ArrayList<>();
                peoples.add(invitationPeople);
                Intent mIntent = new Intent(getContext(), InvitationInterviewActivity.class);
                mIntent.putExtra("peoples", peoples);
                startActivity(mIntent);
                break;
            case R.id.btBottomMenu02:
                //立即沟通

//                int a= (int) (Math.random()*10);
//
//                int b=a%5;
//
//                switch (b){
//                    case 0:
//                        NimUIKit.startP2PSession(getContext(), "test2", null);
//                        break;
//                    case 1:
//                        NimUIKit.startP2PSession(getContext(), "000000002", null);
//                        break;
//                    case 2:
//                        NimUIKit.startP2PSession(getContext(), "000000003", null);
//                        break;
//                    case 3:
//                        NimUIKit.startP2PSession(getContext(), "000000004", null);
//                        break;
//                    case 4:
//                        NimUIKit.startP2PSession(getContext(), "15168212330", null);
//                        break;
//                }
                NimUIKit.startP2PSession(getContext(), "comp_21790", null);

                break;
            case R.id.btBottomMenu03:
                //加入收藏
                add_company_fav(workId);
                break;
        }
    }
}
