package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.Job;
import com.zuoni.zxqy.callback.OnPositionManagementListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvPositionManagementAdapter extends RecyclerView.Adapter<RvPositionManagementAdapter.MyViewHolder> {

    private Context mContext;
    private List<Job> mList;
    private LayoutInflater mInflater;

    public RvPositionManagementAdapter(Context mContext, List<Job> mList) {
        this.mContext = mContext;
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(mContext);
    }


    private OnPositionManagementListener onPositionManagementListener;

    public void setOnPositionManagementListener(OnPositionManagementListener onPositionManagementListener) {
        this.onPositionManagementListener = onPositionManagementListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_position_management_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.hits.setText(mList.get(position).getHits());
        holder.ordid.setText(mList.get(position).getOrdid());
        holder.update_time.setText(mList.get(position).getUpdate_time());
//        holder.title.setText(mList.get(position).getTitle());

        if (mList.get(position).isChoose()) {
            holder.ivChoose.setImageResource(R.mipmap.zx_107);
        } else {
            holder.ivChoose.setImageResource(R.mipmap.zx_108);
        }

        holder.layoutChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(position).setChoose(!mList.get(position).isChoose());
                RvPositionManagementAdapter.this.notifyDataSetChanged();
            }
        });

        if (mList.get(position).getStatus().equals("1")) {
            holder.status.setBackgroundResource(R.drawable.bg_10);
            holder.status.setText("招聘中");
        } else {
            holder.status.setBackgroundResource(R.drawable.bg_14);
            holder.status.setText("已关闭");
        }

        holder.ordid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositionManagementListener.onClick01(mList.get(position), position);
            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositionManagementListener.onClick02(mList.get(position), position);
            }
        });

        holder.menu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositionManagementListener.onClick03(mList.get(position), position);
            }
        });
        holder.menu02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositionManagementListener.onClick04(mList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, hits, status, update_time, ordid;
        RelativeLayout layoutChoose;
        ImageView menu01, menu02, ivChoose;

        MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            hits = (TextView) itemView.findViewById(R.id.hits);
            status = (TextView) itemView.findViewById(R.id.status);
            update_time = (TextView) itemView.findViewById(R.id.update_time);
            ordid = (TextView) itemView.findViewById(R.id.ordid);

            layoutChoose = (RelativeLayout) itemView.findViewById(R.id.layoutChoose);
            menu01 = (ImageView) itemView.findViewById(R.id.menu01);
            menu02 = (ImageView) itemView.findViewById(R.id.menu02);
            ivChoose = (ImageView) itemView.findViewById(R.id.ivChoose);
        }
    }
}
