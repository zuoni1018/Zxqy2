package com.zuoni.zxqy.cache;

import android.content.Context;

import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zuoni.common.utils.SPUtils;

/**
 * Created by zangyi_shuai_ge on 2017/9/27
 * SharedPreferences 辅助类
 */

public class CacheUtils {


    public void setLoginInfo(LoginInfo loginInfo, Context context) {

        SPUtils.put(context, "wyAccount", loginInfo.getAccount());
        SPUtils.put(context, "wyToken", loginInfo.getToken());
        SPUtils.put(context, "wyAppKey", loginInfo.getAppKey());
    }

    public  LoginInfo getLoginInfo(Context context){
        String wyAccount= (String) SPUtils.get(context,"wyAccount","");
        String wyToken= (String) SPUtils.get(context,"wyToken","");
        String wyAppKey= (String) SPUtils.get(context,"wyAppKey","");

        if("".equals(wyAccount)){
            return null;
        }else {
            return  new LoginInfo(wyAccount,wyToken);
        }
    }


}
