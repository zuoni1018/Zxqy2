package com.zuoni.zxqy.http;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.zuoni.common.utils.LogUtil;
import com.zuoni.zxqy.GlobalVariable;
import com.zuoni.zxqy.bean.gson.BaseHttpResponse;
import com.zuoni.zxqy.cache.CacheUtils;

/**
 * Created by zangyi_shuai_ge on 2017/9/27
 * 网络访问辅助类
 */

public class CallServer {

    private static CallServer instance;

    public static CallServer getInstance() {
        if (instance == null)
            synchronized (CallServer.class) {
                if (instance == null)
                    instance = new CallServer();
            }
        return instance;
    }

    private RequestQueue queue;

    private CallServer() {
        queue = NoHttp.newRequestQueue(5);
    }

    public void request(final HttpRequest request, final HttpResponseListener httpResponseListener, final Context context) {

        request.add("token", CacheUtils.getToken(context));
        request.add("userid", CacheUtils.getUserid(context));
        request.add("siteId", CacheUtils.getSiteId(context));

        SimpleResponseListener<String> listener = new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                super.onSucceed(what, response);
                Gson gson=new Gson();
                try{
                    BaseHttpResponse baseHttpResponse=gson.fromJson(response.get(),BaseHttpResponse.class);
                    if(baseHttpResponse.getStatus().equals("3")){
                        Intent intent = new Intent();
                        intent.setAction(GlobalVariable.BROADCAST_TOKEN_ERROR);
                        context.sendBroadcast(intent);
                    }else {
                        httpResponseListener.onSucceed(response.get(),gson);
                    }
                }catch (JsonSyntaxException e){
                    LogUtil.i("手动报错"+response.get());
                    LogUtil.i("手动报错"+e);
                    httpResponseListener.onFailed(e);
                }


            }

            @Override
            public void onFailed(int what, Response<String> response) {
                super.onFailed(what, response);
                httpResponseListener.onFailed(response.getException());
            }
        };

        queue.add(1, request, listener);
    }
    public void request2(HttpRequest request, final HttpResponseListener httpResponseListener, final Context context) {

        SimpleResponseListener<String> listener = new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                super.onSucceed(what, response);
                Gson gson=new Gson();
//                BaseHttpResponse baseHttpResponse=gson.fromJson("",BaseHttpResponse.class);
//                if(baseHttpResponse.getHttpCode()==700){
//                    if(context instanceof Activity){
//                        Activity activity= (Activity) context;
//                        if(!activity.isDestroyed()){
//                            Intent mIntent=new Intent("token_error");
//                            context.sendBroadcast(mIntent);
//                        }
//                    }
//                }else {
                httpResponseListener.onSucceed(response.get(),gson);
//                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {
                super.onFailed(what, response);
                httpResponseListener.onFailed(response.getException());
            }
        };

        queue.add(1, request, listener);
    }

    public <T> void request(int what, Request<T> request, SimpleResponseListener<T> listener) {
        queue.add(what, request, listener);
    }

    // 完全退出app时，调用这个方法释放CPU。
    public void stop() {
        queue.stop();
    }
}
