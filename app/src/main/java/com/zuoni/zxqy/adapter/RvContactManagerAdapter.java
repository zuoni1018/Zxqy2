package com.zuoni.zxqy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuoni.common.widget.SwipeMenuView;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.Contact;
import com.zuoni.zxqy.callback.ContactManagerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvContactManagerAdapter extends RecyclerView.Adapter<RvContactManagerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Contact> mList;
    private LayoutInflater mInflater;

    private ContactManagerListener layout01OnClickListener;

    public void setLayout01OnClickListener(ContactManagerListener listener){
        layout01OnClickListener=listener;
    }
    private ContactManagerListener layout02OnClickListener;

    public void setLayout02OnClickListener(ContactManagerListener listener){
        layout02OnClickListener=listener;
    }

    private ContactManagerListener layoutMainOnClickListener;

    public void setLayoutMainOnClickListener(ContactManagerListener listener){
        layoutMainOnClickListener=listener;
    }
    public RvContactManagerAdapter(Context mContext, List<Contact> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_contact_manager_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             layoutMainOnClickListener.onClickListener(mList.get(position),position);
                holder.swipeMenuView.smoothClose();
            }
        });

        holder.name.setText(mList.get(position).getName());
        holder.tele.setText(mList.get(position).getTele());
        holder.email.setText(mList.get(position).getEmail());
//        holder.address.setText(mList.get(position).getAddress());


        holder.layout01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout01OnClickListener.onClickListener(mList.get(position),position);
                holder.swipeMenuView.smoothClose();
            }
        });
        holder.layout02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout02OnClickListener.onClickListener(mList.get(position),position);
                holder.swipeMenuView.smoothClose();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutMain,layout01,layout02;
        TextView name,tele,fax,email,address,add_time;
        SwipeMenuView swipeMenuView;




        MyViewHolder(View itemView) {
            super(itemView);
            layoutMain=(LinearLayout)itemView.findViewById(R.id.layoutMain);
            layout01=(LinearLayout)itemView.findViewById(R.id.layout01);
            layout02=(LinearLayout)itemView.findViewById(R.id.layout02);
            name= (TextView) itemView.findViewById(R.id.name);
            tele= (TextView) itemView.findViewById(R.id.tele);
//            fax= (TextView) itemView.findViewById(R.id.fax);
            email= (TextView) itemView.findViewById(R.id.email);
//            address= (TextView) itemView.findViewById(R.id.address);

            add_time= (TextView) itemView.findViewById(R.id.add_time);
            swipeMenuView= (SwipeMenuView) itemView.findViewById(R.id.swipeMenuView);
        }
    }



}
