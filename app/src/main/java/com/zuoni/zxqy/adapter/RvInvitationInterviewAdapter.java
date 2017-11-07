package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.InvitationPeople;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvInvitationInterviewAdapter extends RecyclerView.Adapter<RvInvitationInterviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<InvitationPeople> mList;
    private LayoutInflater mInflater;

    public RvInvitationInterviewAdapter(Context mContext, List<InvitationPeople> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_invitation_interview_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(mList.get(position).getName());
        holder.workName.setText(mList.get(position).getWorkName());

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.zx_113)
                .error(R.mipmap.zx_113);
        Glide.with(mContext)
                .asBitmap()
                .load(mList.get(position).getHeadUrl())
                .apply(requestOptions)
                .into(holder.ivHead);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SelectableRoundedImageView ivHead;
        TextView name,workName;


        MyViewHolder(View itemView) {
            super(itemView);
            ivHead=itemView.findViewById(R.id.ivHead);
            name=itemView.findViewById(R.id.name);
            workName=itemView.findViewById(R.id.workName);
        }
    }
}
