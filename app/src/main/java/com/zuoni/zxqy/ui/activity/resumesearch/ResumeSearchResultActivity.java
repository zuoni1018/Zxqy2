package com.zuoni.zxqy.ui.activity.resumesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvResumeSearchAdapter;
import com.zuoni.zxqy.bean.gson.SearchResume;
import com.zuoni.zxqy.bean.model.InvitationPeople;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.InvitationInterviewActivity;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class ResumeSearchResultActivity extends BaseTitleActivity {
    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.tv02)
    TextView tv02;


    String searchType="normal";
    String key="";
    String hopepostion="不限";
    String workerId="不限";
    String jiguan="不限";

    String img="不限";
    String lastTime="不限";
    String sex="不限";
    String speciality="不限";
    String jobhistory="不限";

    String education="不限";
    String minAge="不限";
    String maxAge="不限";
    String hopelocation="不限";
    String name="不限";

    private int nowPage;
    private int allPage;
    private boolean isFirst=true;
    private String url="/Resume/search_resume/p/1/size/10";
    private LRecyclerViewAdapter mAdapter;
private    List<SearchResume.DataBean> mList;
    @Override
    public int setLayoutId() {
        return R.layout.activity_resume_search_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("简历搜索");

        searchType=getIntent().getStringExtra("searchType");
        key=getIntent().getStringExtra("key");
        hopepostion=getIntent().getStringExtra("hopepostion");
        workerId=getIntent().getStringExtra("workerId");
        jiguan=getIntent().getStringExtra("jiguan");

        img=getIntent().getStringExtra("img");
        lastTime=getIntent().getStringExtra("lastTime");
        sex=getIntent().getStringExtra("sex");
        speciality=getIntent().getStringExtra("speciality");
        jobhistory=getIntent().getStringExtra("jobhistory");

        education=getIntent().getStringExtra("education");
        minAge=getIntent().getStringExtra("minAge");
        maxAge=getIntent().getStringExtra("maxAge");
        hopelocation=getIntent().getStringExtra("hopelocation");
        name=getIntent().getStringExtra("name");
        mList = new ArrayList<>();
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isFirst=true;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                search_resume(searchType,key,hopepostion,workerId,jiguan,img,lastTime,sex,speciality,jobhistory,education,minAge,maxAge,hopelocation,name);

            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFirst=false;
                search_resume(searchType,key,hopepostion,workerId,jiguan,img,lastTime,sex,speciality,jobhistory,education,minAge,maxAge,hopelocation,name);
            }
        });
        mAdapter = new LRecyclerViewAdapter(new RvResumeSearchAdapter(getContext(), mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.refresh();
//        search_resume(searchType,key,hopepostion,workerId,jiguan,img,lastTime,sex,speciality,jobhistory,education,minAge,maxAge,hopelocation,name);
    }

    private boolean isChooseAll=false;




    @OnClick({R.id.tv01, R.id.tv02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv01:
                isChooseAll=!isChooseAll;
                for (int i = 0; i <mList.size() ; i++) {
                    mList.get(i).setChoose(isChooseAll);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.tv02:
                ArrayList<InvitationPeople> peoples=new ArrayList<>();
                String workId="";

                for (int i = 0; i <mList.size() ; i++) {
                    if(mList.get(i).isChoose()){
                        InvitationPeople invitationPeople=new InvitationPeople();
                        invitationPeople.setHeadUrl(mList.get(i).getImg());
                        invitationPeople.setName(mList.get(i).getName());
                        invitationPeople.setWorkName(mList.get(i).getHopepostion());
                        invitationPeople.setWorkId(mList.get(i).getWorkerId());
                        peoples.add(invitationPeople);
                        workId=workId+mList.get(i).getWorkerId()+",";
                    }
                }

                if(peoples.size()==0){
                    showToast("请先选择投递者");
                }else {
                    Intent mIntent=new Intent(getContext(), InvitationInterviewActivity.class);
                    mIntent.putExtra("peoples",peoples);
                    mIntent.putExtra("workId",workId);
                    startActivity(mIntent);
                }


                break;
        }
    }


    private void search_resume(String searchType, String key, String hopepostion, String workerId, String jiguan,
                               String img, String lastTime, String sex, String speciality, String jobhistory,
                               String education, String minAge, String maxAge, String hopelocation, String name) {

        showLoading();
        String url="/Resume/search_resume/p/1/size/10";

        if(!isFirst){
            nowPage++;
            url="/Resume/search_resume/p/"+nowPage+"/size/10";
        }

        HttpRequest httpRequest = new HttpRequest(AppUrl.BASE_URL+url);//搜索
        httpRequest.add("searchType",searchType);
        httpRequest.add("key",key);
        httpRequest.add("hopepostion",hopepostion);
        httpRequest.add("workerId",workerId);
        httpRequest.add("jiguan",jiguan);

        httpRequest.add("img",img);
        httpRequest.add("lastTime",lastTime);
        httpRequest.add("sex",sex);
        httpRequest.add("speciality",speciality);
        httpRequest.add("jobhistory",jobhistory);

        httpRequest.add("education",education);
        httpRequest.add("minAge",minAge);
        httpRequest.add("maxAge",maxAge);
        httpRequest.add("hopelocation",hopelocation);
        httpRequest.add("name",name);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                mRecyclerView.refreshComplete(1);
                LogUtil.i("搜索" + response);
                SearchResume info = gson.fromJson(response, SearchResume.class);
                if(info.getStatus().equals("true")){
                    nowPage=Integer.parseInt(info.getP());
                    mList.addAll(info.getData());
                }else {
                    showToast(info.getMessage());
                }

            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
                myFinish();
            }
        }, getContext());
    }
}
