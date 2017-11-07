package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.EnterpriseInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvHomePreviewAdapter extends RecyclerView.Adapter<RvHomePreviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<EnterpriseInformation.JobsBean> mList;
    private LayoutInflater mInflater;

    public RvHomePreviewAdapter(Context mContext, List<EnterpriseInformation.JobsBean> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_home_preview_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.job_name.setText(mList.get(position).getJob_name());
        holder.company_name.setText(mList.get(position).getCompany_name());
        holder.update_time.setText(mList.get(position).getUpdate_time());
        holder.pay.setText(mList.get(position).getPay());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView job_name,company_name,pay,update_time;

        MyViewHolder(View itemView) {
            super(itemView);
            job_name=itemView.findViewById(R.id.job_name);
            company_name=itemView.findViewById(R.id.company_name);
            update_time=itemView.findViewById(R.id.update_time);
            pay=itemView.findViewById(R.id.pay);
        }
    }
}
