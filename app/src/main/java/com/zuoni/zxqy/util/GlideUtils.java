package com.zuoni.zxqy.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zuoni.zxqy.R;

/**
 * Created by zangyi_shuai_ge on 2017/11/13
 * Glide  设置展位图之类的 进行同一管理
 */

public class GlideUtils {

    public static  void setHead(Context context, String url, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.zx_113)
                .error(R.mipmap.zx_113);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


}
