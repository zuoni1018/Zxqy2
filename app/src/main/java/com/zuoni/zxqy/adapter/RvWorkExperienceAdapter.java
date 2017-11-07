package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.ResumeDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvWorkExperienceAdapter extends RecyclerView.Adapter<RvWorkExperienceAdapter.MyViewHolder> {

    private Context mContext;
    private List<ResumeDetail.DataBean.JobhistoryBean > mList;
    private LayoutInflater mInflater;

    public RvWorkExperienceAdapter(Context mContext, List<ResumeDetail.DataBean.JobhistoryBean > mList) {
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
        View mView = mInflater.inflate(R.layout.rv_work_experience_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.position.setText(mList.get(position).getPosition());
        holder.time.setText(mList.get(position).getBegin_time()+" - "+mList.get(position).getEnd_time());
        holder.company.setText(mList.get(position).getCompany_name()+"  "+mList.get(position).getCompany_nature());
        holder.skill.setText(mList.get(position).getSkill());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView position,time,company,skill;

        MyViewHolder(View itemView) {
            super(itemView);
            position=itemView.findViewById(R.id.position);
            time=itemView.findViewById(R.id.time);
            company=itemView.findViewById(R.id.company);
            skill=itemView.findViewById(R.id.skill);
        }
    }
}
