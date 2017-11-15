package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.Message;
import com.zuoni.zxqy.callback.OnMailBoxListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvMailboxAdapter extends RecyclerView.Adapter<RvMailboxAdapter.MyViewHolder> {

    private Context mContext;
    private List<Message> mList;
    private LayoutInflater mInflater;


    private OnMailBoxListener onMailBoxListener;

    public void setOnMailBoxListener(OnMailBoxListener onMailBoxListener) {
        this.onMailBoxListener = onMailBoxListener;
    }

    public RvMailboxAdapter(Context mContext, List<Message> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_mailbox_get_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onMailBoxListener.onClick03(mList.get(position),position);
//            }
//        });

        holder.layoutMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMailBoxListener.onClick01(mList.get(position),position);
            }
        });

        holder.layoutMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMailBoxListener.onClick02(mList.get(position),position);
            }
        });


        //时间
        String time=mList.get(position).getAddTime();
        String[]a= time.split("\\s+");
        holder.addTime1.setText(a[0]);
        if(a[1]!=null){
            holder.addTime2.setText(a[1]);
        }else {
            holder.addTime2.setText("");
        }

        holder.type.setText("类型："+mList.get(position).getType());
        holder.info.setText("信息内容："+mList.get(position).getInfo());
        holder.name.setText(mList.get(position).getName());

        if (mList.get(position).isChoose()) {
            holder.ivChoose.setImageResource(R.mipmap.zx_107);
        } else {
            holder.ivChoose.setImageResource(R.mipmap.zx_108);
        }

        holder.layoutChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(position).setChoose(!mList.get(position).isChoose());
                RvMailboxAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView type,info,addTime1,addTime2,name;
        LinearLayout layoutMain,layoutMenu2,layoutMenu1;
        RelativeLayout layoutChoose;
        ImageView ivChoose;

        MyViewHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            info=itemView.findViewById(R.id.info);
            addTime1=itemView.findViewById(R.id.addTime1);
            addTime2=itemView.findViewById(R.id.addTime2);
            name=itemView.findViewById(R.id.name);
            layoutMain=itemView.findViewById(R.id.layoutMain);
            layoutMenu2=itemView.findViewById(R.id.layoutMenu2);
            layoutMenu1=itemView.findViewById(R.id.layoutMenu1);
            layoutChoose=itemView.findViewById(R.id.layoutChoose);
            ivChoose=itemView.findViewById(R.id.ivChoose);
        }
    }
}
