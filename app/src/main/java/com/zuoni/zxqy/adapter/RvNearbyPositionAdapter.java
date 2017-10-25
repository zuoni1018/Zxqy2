package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.zuoni.zxqy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvNearbyPositionAdapter extends RecyclerView.Adapter<RvNearbyPositionAdapter.MyViewHolder> {

    private Context mContext;
    private List<PoiInfo> mList;
    private LayoutInflater mInflater;

    public RvNearbyPositionAdapter(Context mContext, List<PoiInfo> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_nearby_position_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvAddress.setText(mList.get(position).address);
        holder.tvName.setText(mList.get(position).name);
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvAddress;
        LinearLayout layoutMain;

        MyViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvAddress=(TextView) itemView.findViewById(R.id.tvAddress);
            layoutMain= (LinearLayout) itemView.findViewById(R.id.layoutMain);
        }
    }
}
