package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuoni.zxqy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvPostingTagAdapter extends RecyclerView.Adapter<RvPostingTagAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;

    public RvPostingTagAdapter(Context mContext, List<String> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_posting_tag_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv01.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv01;

        MyViewHolder(View itemView) {
            super(itemView);
            tv01 = itemView.findViewById(R.id.tv01);
        }
    }
}
