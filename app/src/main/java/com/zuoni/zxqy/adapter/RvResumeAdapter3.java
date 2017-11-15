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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.joooonho.SelectableRoundedImageView;
import com.zuoni.common.utils.ToastUtils;
import com.zuoni.zxqy.R;
import com.zuoni.zxqy.bean.model.ResumeManager;
import com.zuoni.zxqy.callback.OnResumeManagerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/9/1
 */

public class RvResumeAdapter3 extends RecyclerView.Adapter<RvResumeAdapter3.MyViewHolder> {

    private Context mContext;
    private List<ResumeManager> mList;
    private LayoutInflater mInflater;
    private OnResumeManagerListener onResumeManagerListener;

    public void setOnResumeManagerListener(OnResumeManagerListener onResumeManagerListener) {
        this.onResumeManagerListener = onResumeManagerListener;
    }

    public RvResumeAdapter3(Context mContext, List<ResumeManager> mList) {
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
        View mView = mInflater.inflate(R.layout.rv_resume_item3, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.zx_113)
                .error(R.mipmap.zx_113);
        Glide.with(mContext)
                .asBitmap()
                .load(mList.get(position).getImg())
                .apply(requestOptions)
                .into(holder.img);

        holder.name.setText(mList.get(position).getName());
        holder.jobName.setText(mList.get(position).getViewJobName());

        holder.education.setText(mList.get(position).getEducation());
        holder.speciality.setText(mList.get(position).getSpeciality());

        holder.age.setText(mList.get(position).getAge()+"");

        if (mList.get(position).getSex().equals("女")) {
            holder.sex.setBackgroundResource(R.mipmap.zx_8);
        } else {
            holder.sex.setBackgroundResource(R.mipmap.zx_27);
        }

//        if (mList.get(position).isChoose()) {
//            holder.ivChoose.setImageResource(R.mipmap.zx_107);
//        } else {
//            holder.ivChoose.setImageResource(R.mipmap.zx_108);
//        }

//        holder.layoutChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mList.get(position).setChoose(!mList.get(position).isChoose());
//                RvResumeAdapter3.this.notifyDataSetChanged();
//            }
//        });

        String time=mList.get(position).getAddTime();

        String[]a= time.split("\\s+");

        holder.time1.setText(a[0]);
        if(a[1]!=null){
            holder.time2.setText(a[1]);
        }else {
            holder.time2.setText("");
        }

        if(mList.get(position).getIs_hide().equals("1")){
            holder.is_hide.setVisibility(View.VISIBLE);
        }else {
            holder.is_hide.setVisibility(View.GONE);
        }
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.get(position).getIs_hide().equals("1")){
                    ToastUtils.showToast(mContext,"该简历已被隐藏");
                }else {
                    onResumeManagerListener.onClick01(mList.get(position),position);
                }
            }
        });
//
//        holder.layoutMenu02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!mList.get(position).getJobstatus().equals("3")){
//                    onResumeManagerListener.onClick02(mList.get(position),position);
//                }
//            }
//        });
//
//        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onResumeManagerListener.onClick01(mList.get(position),position);
//            }
//        });

//        holder.tv01.setText("删除简历");
//        holder.tv02.setText("邀请面试");

        // 2 已查看 1未查看 3已查看并已邀请
//        if(mList.get(position).getJobstatus().equals("1")){
//            holder.jobstatus.setVisibility(View.VISIBLE);
//            holder.tv02.setText("邀请面试");
//        }else if(mList.get(position).getJobstatus().equals("2")){
//            holder.tv02.setText("邀请面试");
//            holder.jobstatus.setVisibility(View.GONE);
//
//        }else if(mList.get(position).getJobstatus().equals("3")){
////            3已查看并已邀请
//            holder.tv02.setText("已邀请");
//            holder.jobstatus.setVisibility(View.GONE);
//        }
        holder.jobstatus.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SelectableRoundedImageView img;
        TextView name, jobName, education, speciality,age,time1,time2;
        RelativeLayout sex,layoutChoose;
        ImageView ivChoose,jobstatus;


        LinearLayout  layoutMenu02,layoutMenu01,layoutMain;
        ImageView iv01,iv02,is_hide;
        TextView tv01,tv02;

        MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            jobName = itemView.findViewById(R.id.jobName);
            education = itemView.findViewById(R.id.education);
            speciality = itemView.findViewById(R.id.speciality);
            sex = itemView.findViewById(R.id.sex);
            age = itemView.findViewById(R.id.age);

            layoutChoose = itemView.findViewById(R.id.layoutChoose);
            ivChoose = itemView.findViewById(R.id.ivChoose);

            time1 = itemView.findViewById(R.id.time1);
            time2 = itemView.findViewById(R.id.time2);

            layoutMenu02 = itemView.findViewById(R.id.layoutMenu02);
            layoutMenu01 = itemView.findViewById(R.id.layoutMenu01);

            iv01 = itemView.findViewById(R.id.iv01);
            iv02 = itemView.findViewById(R.id.iv02);

            tv01 = itemView.findViewById(R.id.tvMenu01);
            tv02 = itemView.findViewById(R.id.tvMenu02);

            is_hide = itemView.findViewById(R.id.is_hide);

            layoutMain = itemView.findViewById(R.id.layoutMain);

            jobstatus = itemView.findViewById(R.id.jobstatus);
        }
    }
}
