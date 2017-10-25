package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvChartListAdapter extends RecyclerView.Adapter<RvChartListAdapter.MyViewHolder> {

    private Context mContext;
    private List<RecentContact> mList;
    private LayoutInflater mInflater;

    public RvChartListAdapter(Context mContext, List<RecentContact> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_chart_list_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


//        UserInfoProvider.UserInfo zz= NimUIKit.getUserInfoProvider().getUserInfo(mList.get(position).getContactId());
//        LogUtil.i("xixixxi",zz.getAvatar());
        holder.tvName.setText(mList.get(position).getFromNick());
        holder.tvMessage.setText(mList.get(position).getContent());
        String timeString = TimeUtil.getTimeShowString(mList.get(position).getTime(), true);
        holder.tvTime.setText(timeString);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvMessage,tvTime;


        MyViewHolder(View itemView) {
            super(itemView);
            tvTime=(TextView) itemView.findViewById(R.id.tvTime);
            tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvMessage=(TextView) itemView.findViewById(R.id.tvMessage);
        }
    }
}
