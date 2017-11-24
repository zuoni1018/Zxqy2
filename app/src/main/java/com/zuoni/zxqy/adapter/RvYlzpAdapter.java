package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.netease.nim.uikit.NimUIKit;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.callback.OnResumeManagerListener;
import com.zuoni.zxqy.ui.activity.ResumeDetailsActivity;
import com.zuoni.zxqy.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvYlzpAdapter extends RecyclerView.Adapter<RvYlzpAdapter.MyViewHolder> {

    private Context mContext;
    private List<ResumeManager> mList;
    private LayoutInflater mInflater;
    private OnResumeManagerListener onResumeManagerListener;

    public void setOnResumeManagerListener(OnResumeManagerListener onResumeManagerListener) {
        this.onResumeManagerListener = onResumeManagerListener;
    }

    public RvYlzpAdapter(Context mContext, List<ResumeManager> mList) {
        this.mContext = mContext;
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_ylzp_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        GlideUtils.setHead(mContext, mList.get(position).getImg(), holder.img);

        if (mList.get(position).getSex().equals("女")) {
            holder.sex.setBackgroundResource(R.mipmap.zx_8);
        } else {
            holder.sex.setBackgroundResource(R.mipmap.zx_27);
        }

        holder.name.setText(mList.get(position).getName());
        holder.jobName.setText(mList.get(position).getHopepostion());
        holder.education.setText(mList.get(position).getEducation());
        holder.speciality.setText(mList.get(position).getSpeciality());
        holder.age.setText(mList.get(position).getAge() + "");
        holder.requestsalary.setText("期望薪资："+mList.get(position).getRequestsalary());
        holder.ivChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NimUIKit.startP2PSession(mContext, mList.get(position).getUserChatId(), null);
            }
        });
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, ResumeDetailsActivity.class);
                mIntent.putExtra("name", mList.get(position).getName());
                mIntent.putExtra("workId", mList.get(position).getWorkerId());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SelectableRoundedImageView img;
        TextView name, jobName, education, speciality, age,requestsalary;
        RelativeLayout sex;
        RelativeLayout layoutMain;

        ImageView ivChart;

        MyViewHolder(View itemView) {
            super(itemView);
            layoutMain = itemView.findViewById(R.id.layoutMain);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            jobName = itemView.findViewById(R.id.jobName);
            education = itemView.findViewById(R.id.education);
            speciality = itemView.findViewById(R.id.speciality);
            sex = itemView.findViewById(R.id.sex);
            age = itemView.findViewById(R.id.age);
            ivChart = itemView.findViewById(R.id.ivChart);
            requestsalary = itemView.findViewById(R.id.requestsalary);
        }
    }
}
