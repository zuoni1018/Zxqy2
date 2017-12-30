package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.SearchResume;
import com.zuoni.zxqy.ui.activity.ResumeDetailsActivity;
import com.zuoni.zxqy.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvResumeSearchAdapter extends RecyclerView.Adapter<RvResumeSearchAdapter.MyViewHolder> {

    private Context mContext;
    private List<SearchResume.DataBean> mList;
    private LayoutInflater mInflater;

    public RvResumeSearchAdapter(Context mContext, List<SearchResume.DataBean> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_resume_search_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(mContext, ResumeDetailsActivity.class);
                mIntent.putExtra("name",mList.get(position).getName());
                mIntent.putExtra("workId",mList.get(position).getWorkerId());
                mContext.startActivity(mIntent);

            }
        });
        holder.workerId.setText(mList.get(position).getWorkerId());
//        holder.name.setText(mList.get(position).getName());

        if(mList.get(position).getInvite().equals("1")){
            holder.name.setText(mList.get(position).getName());
        }else {
            holder.name.setText(mList.get(position).getWorkerId());
        }


        holder.jobyear.setText(mList.get(position).getJobyear()+"年");
        holder.hopepostion.setText(mList.get(position).getHopepostion());
        holder.lastTime.setText("最近登录："+mList.get(position).getLastTime());
        holder.education.setText(mList.get(position).getEducation());
        holder.jiguan.setText(mList.get(position).getJiguan());
        holder.age.setText(mList.get(position).getAge() + "");

        GlideUtils.setHead(mContext,mList.get(position).getImg(),holder.img);

        if (mList.get(position).getSex().equals("女")) {
            holder.sex.setBackgroundResource(R.mipmap.zx_8);
        } else {
            holder.sex.setBackgroundResource(R.mipmap.zx_27);
        }

        if (mList.get(position).isChoose()) {
            holder.ivChoose.setImageResource(R.mipmap.zx_107);
        } else {
            holder.ivChoose.setImageResource(R.mipmap.zx_108);
        }

        holder.layoutChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(position).setChoose(!mList.get(position).isChoose());
                RvResumeSearchAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SelectableRoundedImageView img;
        TextView workerId, name, jobyear, jiguan, hopepostion, lastTime, education, age;
        RelativeLayout sex,layoutChoose;
        ImageView ivChoose;
        LinearLayout layoutMain;

        MyViewHolder(View itemView) {
            super(itemView);
            ivChoose = (ImageView) itemView.findViewById(R.id.ivChoose);
            sex = (RelativeLayout) itemView.findViewById(R.id.sex);
            img = (SelectableRoundedImageView) itemView.findViewById(R.id.img);
            workerId = (TextView) itemView.findViewById(R.id.workerId);
            name = (TextView) itemView.findViewById(R.id.name);
            jobyear = (TextView) itemView.findViewById(R.id.jobyear);
            jiguan = (TextView) itemView.findViewById(R.id.jiguan);
            hopepostion = (TextView) itemView.findViewById(R.id.hopepostion);
            lastTime = (TextView) itemView.findViewById(R.id.lastTime);
            education = (TextView) itemView.findViewById(R.id.education);
            age = (TextView) itemView.findViewById(R.id.age);
            layoutChoose = (RelativeLayout) itemView.findViewById(R.id.layoutChoose);
            layoutMain = (LinearLayout) itemView.findViewById(R.id.layoutMain);
        }
    }
}
