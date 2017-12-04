package com.zuoni.zxqy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.zuoni.common.dialog.picker.DataPickerSingleDialog;
import com.zuoni.common.dialog.picker.callback.OnSingleDataSelectedListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.AppUrl;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.adapter.RvPositionManagementAdapter;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.bean.gson.GetPosition;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.callback.OnPositionManagementListener;
import com.zuoni.zxqy.http.CallServer;
import com.zuoni.zxqy.http.HttpRequest;
import com.zuoni.zxqy.http.HttpResponseListener;
import com.zuoni.zxqy.ui.activity.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zuoni.zxqy.AppUrl.alter_position_status;
import static com.zuoni.zxqy.AppUrl.refresh_position;
import static com.zuoni.zxqy.AppUrl.update_order;

/**
 * Created by zangyi_shuai_ge on 2017/10/21
 */

public class PositionManagementActivity extends BaseTitleActivity {

    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;
    @BindView(R.id.bt01)
    Button bt01;
    @BindView(R.id.bt02)
    Button bt02;
    @BindView(R.id.layoutRight01)
    RelativeLayout layoutRight01;
    @BindView(R.id.layoutRight02)
    RelativeLayout layoutRight02;

    private LRecyclerViewAdapter mAdapter;

    private List<Job> mList;
    private List<Job> upDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("职位管理");

        mList = new ArrayList<>();
        upDateList = new ArrayList<>();

        RvPositionManagementAdapter rvPositionManagementAdapter = new RvPositionManagementAdapter(getContext(), mList);
        rvPositionManagementAdapter.setOnPositionManagementListener(new OnPositionManagementListener() {
            @Override
            public void onClick01(Job job, int position) {
                createPicker(position, job);
            }

            @Override
            public void onClick02(Job job, int position) {
                //本地改状态
                String state = mList.get(position).getStatus();
                if (state.equals("1")) {
                    mList.get(position).setStatus("0");
                } else {
                    mList.get(position).setStatus("1");
                }
                mAdapter.notifyDataSetChanged();

                alter_position_status(job.getJobId());
            }

            @Override
            public void onClick03(Job job, int position) {
                //编辑职位
                Intent mIntent = new Intent(getContext(), PostingPositionActivity.class);
                mIntent.putExtra("isAdd", false);
                mIntent.putExtra("jobId", job.getJobId());
                startActivityForResult(mIntent, 10086);
            }

            @Override
            public void onClick04(Job job, int position) {
                //删除职位
                deletePosition(job.getJobId());
            }

            @Override
            public void onClick05(Job job, int position) {
                //刷新
//                refresh_position(job.getJobId());
            }
        });

        mAdapter = new LRecyclerViewAdapter(rvPositionManagementAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPosition();
            }
        });
        getPosition();

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_position_management;
    }

    //公司性质
    private void createPicker(final int position, final Job job) {

        DataPickerSingleDialog.Builder builder = new DataPickerSingleDialog.Builder(getContext());
        List<String> list = new ArrayList<>();

        for (int i = 0; i < mList.size(); i++) {
            list.add(i + "");
        }

        builder.setOnDataSelectedListener(new OnSingleDataSelectedListener() {
            @Override
            public void onDataSelected(String itemValue) {
                mList.get(position).setOrdid(itemValue);
                mAdapter.notifyDataSetChanged();
                for (int i = 0; i < upDateList.size(); i++) {
                    if (upDateList.get(i).getJobId().equals(job.getJobId())) {
                        upDateList.remove(i);
                        break;
                    }
                }
                upDateList.add(job);
            }
        });

        builder.setData(list);
        builder.create().show();
    }

    /**
     * 删除职位
     */
    private void deletePosition(String jobId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.DELETE_POSITION);//删除职位
        httpRequest.add("jobId", jobId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                closeLoading();
                LogUtil.i("删除职位" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    getPosition();
                    showToast("删除成功");
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


    /**
     * 修改职位状态
     */
    private void alter_position_status(String jobId) {

        HttpRequest httpRequest = new HttpRequest(alter_position_status);//修改职位状态
        httpRequest.add("jobId", jobId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                LogUtil.i("修改职位状态" + response);
            }

            @Override
            public void onFailed(Exception exception) {
                showToast("服务器异常");
            }
        }, getContext());
    }

    /**
     * 获取职位列表
     */
    private void getPosition() {

        showLoading();
        HttpRequest httpRequest = new HttpRequest(AppUrl.GET_POSITION);//获取发布职位
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                LogUtil.i("获取发布职位" + response);

                GetPosition info = gson.fromJson(response, GetPosition.class);
                if (info.getStatus().equals("true")) {
                    mList.clear();
                    mList.addAll(info.getData());
                    mAdapter.notifyDataSetChanged();
                } else {
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }


    /**
     * 更改职位顺序
     */
    private void update_order(String jobId, String ordid) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(update_order);//职位显示顺序修改
        httpRequest.add("jobId", jobId);
        httpRequest.add("ordid", ordid);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                LogUtil.i("职位显示顺序修改" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    upDateList.clear();
                    getPosition();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    /**
     * 刷新职位
     * 将职位信息改为当前
     */
    private void refresh_position(String jobId) {
        showLoading();
        HttpRequest httpRequest = new HttpRequest(refresh_position);//刷新职位
//        httpRequest.add("jobId",jobId);
        CallServer.getInstance().request(httpRequest, new HttpResponseListener() {
            @Override
            public void onSucceed(String response, Gson gson) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                LogUtil.i("刷新职位" + response);
                BaseHttpResponse info = gson.fromJson(response, BaseHttpResponse.class);
                if (info.getStatus().equals("true")) {
                    getPosition();
                } else {
                    showToast(info.getMessage());
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mRecyclerView.refreshComplete(1);
                closeLoading();
                showToast("服务器异常");
            }
        }, getContext());
    }

    @OnClick({R.id.bt01, R.id.bt02,R.id.bt03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt01:
                String ids = "";
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChoose()) {
                        ids = ids + mList.get(i).getJobId() + ",";
                    }
                }

                if (ids.equals("")) {
                    showToast("请先选择职位");
                } else {
                    ids = ids.substring(0, ids.length() - 1);
                    LogUtil.i("删除职位", ids);
                    deletePosition(ids);
                }

                break;
            case R.id.bt02:
                Intent mIntent = new Intent(getContext(), PostingPositionActivity.class);
                startActivityForResult(mIntent, 10086);
                break;
            case R.id.bt03:
                refresh_position("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //修改或者发布职位成功回来要刷新列表
        if (requestCode == 10086 && resultCode == 10087) {
            getPosition();
        }
    }

    @OnClick({R.id.layoutRight01, R.id.layoutRight02})
    public void topBarClick(View view) {
        switch (view.getId()) {
            case R.id.layoutRight01:
                if (upDateList.size() == 0) {
                    showToast("还未修改排序");
                    return;
                }
                String jobId = "";
                String ordid = "";
                for (int i = 0; i < upDateList.size(); i++) {
                    jobId = jobId + upDateList.get(i).getJobId() + ",";
                    ordid = ordid + upDateList.get(i).getOrdid() + ",";
                }
                jobId = jobId.substring(0, jobId.length() - 1);
                ordid = ordid.substring(0, ordid.length() - 1);
                update_order(jobId, ordid);
                break;
            case R.id.layoutRight02:

                break;
        }
    }
}
