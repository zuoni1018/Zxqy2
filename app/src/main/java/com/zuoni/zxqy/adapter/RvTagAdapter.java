package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.gson.get_tags;
import com.zuoni.zxqy.callback.ItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<get_tags.DataBean> mList;
    private LayoutInflater mInflater;
    private ItemOnClickListener itemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public RvTagAdapter(Context mContext, List<get_tags.DataBean> mList) {
        this.mContext = mContext;
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_tag_item, parent, false);
                holder = new MyViewHolder(view);
                break;
            case 0://显示底部
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_tag_footer, parent, false);
                holder = new FooterHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (position == mList.size()) {

        } else {
            MyViewHolder MyViewHolder = (RvTagAdapter.MyViewHolder) holder;
            MyViewHolder.tv01.setText(mList.get(position).getContent());

            if (mList.get(position).isChoose()) {
                MyViewHolder.tv01.setTextColor(mContext.getResources().getColor(R.color.white));
                MyViewHolder.tv01.setBackgroundResource(R.drawable.tag_bg_02);
            } else {
                MyViewHolder.tv01.setTextColor(mContext.getResources().getColor(R.color.text_color_01));
                MyViewHolder.tv01.setBackgroundResource(R.drawable.tag_bg_01);
            }



            MyViewHolder.tv01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClickListener.onClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv01;

        MyViewHolder(View itemView) {
            super(itemView);
            tv01 = itemView.findViewById(R.id.tv01);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
