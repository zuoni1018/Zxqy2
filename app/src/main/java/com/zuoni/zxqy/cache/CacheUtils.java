package com.zuoni.zxqy.cache;

import android.content.Context;

import com.netease.nimlib.sdk.auth.LoginInfo;
import com.zuoni.common.utils.SPUtils;
import com.zuoni.zxqy.bean.model.CompanyInfo;
import com.zuoni.zxqy.bean.model.EnterpriseInformation;

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

    public LoginInfo getLoginInfo(Context context) {
        String wyAccount = (String) SPUtils.get(context, "wyAccount", "");
        String wyToken = (String) SPUtils.get(context, "wyToken", "");
        String wyAppKey = (String) SPUtils.get(context, "wyAppKey", "");

        if ("".equals(wyAccount)) {
            return null;
        } else {
            return new LoginInfo(wyAccount, wyToken);
        }
    }


    public static void setToken(String token, Context context) {
        SPUtils.put(context, "token", token);
    }

    public static String getToken(Context context) {
        String token = (String) SPUtils.get(context, "token", "");
        return token;
    }

    public static void setSiteId(String siteId, Context context) {
        if (siteId == null) {
            return;
        }
        SPUtils.put(context, "siteId", siteId);
    }

    public static String getSiteId(Context context) {
        String siteId = (String) SPUtils.get(context, "siteId", "");
        return siteId;
    }

    public static void setUserid(String userid, Context context) {
        if (userid == null) {
            return;
        }
        SPUtils.put(context, "userid", userid);
    }

    public static String getUserid(Context context) {

        String userid = (String) SPUtils.get(context, "userid", "");
        return userid;
    }

    public static void setPhone(String phone, Context context) {
        SPUtils.put(context, "phone", phone);
    }

    public static String getPhone(Context context) {
        String phone = (String) SPUtils.get(context, "phone", "");
        return phone;
    }

    public static void setInfo(String info, Context context) {
        SPUtils.put(context, "info", info);
    }

    public static String getInfo(Context context) {
        String info = (String) SPUtils.get(context, "info", "");
        return info;
    }


    public static void setLogin(boolean isLogin, Context context) {
        SPUtils.put(context, "isLogin", isLogin);
    }

    public static boolean isLogin(Context context) {
        boolean isLogin = (boolean) SPUtils.get(context, "isLogin", false);
        return isLogin;
    }

    public static void setEnterpriseInformation(EnterpriseInformation data,Context context) {
        //10个字段

        String cname=data.getCname();
        String title=data.getTitle();
        String email=data.getEmail();
        String phone=data.getPhone();
        String web=data.getWeb();



        String info=data.getInfo();
        String address=data.getAddress();
        String type=data.getType();
        String logo=data.getLogo();
//        String jobs=data.getJobs();

        SPUtils.put(context, "cname", cname);
        SPUtils.put(context, "title", title);
        SPUtils.put(context, "email", email);
        SPUtils.put(context, "phone", phone);
        SPUtils.put(context, "web", web);

        SPUtils.put(context, "info", info);
        SPUtils.put(context, "address", address);
        SPUtils.put(context, "type", type);
        SPUtils.put(context, "logo", logo);
//        SPUtils.put(context, "cname", cname);
    }


    public static EnterpriseInformation getEnterpriseInformation(Context context) {
        //10个字段

        EnterpriseInformation enterpriseInformation=new EnterpriseInformation();

        String cname= (String) SPUtils.get(context, "cname", "");
        String title= (String) SPUtils.get(context, "title", "");
        String email= (String) SPUtils.get(context, "email", "");
        String phone= (String) SPUtils.get(context, "phone", "");
        String web= (String) SPUtils.get(context, "web", "");



        String info= (String) SPUtils.get(context, "info", "");
        String address= (String) SPUtils.get(context, "address", "");
        String type= (String) SPUtils.get(context, "type", "");
        String logo= (String) SPUtils.get(context, "logo", "");
//        String jobs= (String) SPUtils.get(context, "userid", "");

        enterpriseInformation.setCname(cname);
        enterpriseInformation.setTitle(title);
        enterpriseInformation.setEmail(email);
        enterpriseInformation.setPhone(phone);
        enterpriseInformation.setWeb(web);

        enterpriseInformation.setInfo(info);
        enterpriseInformation.setAddress(address);
        enterpriseInformation.setTitle(type);
        enterpriseInformation.setLogo(logo);
//        enterpriseInformation.setCname(cname);
        return enterpriseInformation;
    }

    public static void setCompanyInfo(CompanyInfo data, Context context) {

        //14个字段

        String cname=data.getCname();
        String title=data.getTitle();
        String email=data.getEmail();
        String phone=data.getPhone();
        String web=data.getWeb();

        String info=data.getInfo();
        String address=data.getAddress();
        String type=data.getType();
        String logo=data.getLogo();

        String lawer=data.getLawer();
        String tele=data.getTele();
        String name=data.getName();
        String fax=data.getFax();
        String cateId=data.getCateId();
        String cateName=data.getCateName();

        SPUtils.put(context, "cname", cname);
        SPUtils.put(context, "title", title);
        SPUtils.put(context, "email", email);
        SPUtils.put(context, "phone", phone);
        SPUtils.put(context, "web", web);

        SPUtils.put(context, "info", info);
        SPUtils.put(context, "address", address);
        SPUtils.put(context, "type", type);
        SPUtils.put(context, "logo", logo);


        SPUtils.put(context, "lawer", lawer);
        SPUtils.put(context, "tele", tele);
        SPUtils.put(context, "name", name);
        SPUtils.put(context, "fax", fax);
        SPUtils.put(context, "cateId", cateId);
        SPUtils.put(context, "cateName", cateName);
    }

    public static CompanyInfo getCompanyInfo(Context context) {
        //10个字段

        CompanyInfo companyInfo=new CompanyInfo();

        String cname= (String) SPUtils.get(context, "cname", "");
        String title= (String) SPUtils.get(context, "title", "");
        String email= (String) SPUtils.get(context, "email", "");
        String phone= (String) SPUtils.get(context, "phone", "");
        String web= (String) SPUtils.get(context, "web", "");

        String info= (String) SPUtils.get(context, "info", "");
        String address= (String) SPUtils.get(context, "address", "");
        String type= (String) SPUtils.get(context, "type", "");
        String logo= (String) SPUtils.get(context, "logo", "");


        String lawer=(String) SPUtils.get(context, "lawer", "");
        String tele=(String) SPUtils.get(context, "tele", "");
        String name=(String) SPUtils.get(context, "name", "");
        String fax=(String) SPUtils.get(context, "fax", "");
        String cateId=(String) SPUtils.get(context, "cateId", "");
        String cateName=(String) SPUtils.get(context, "cateName", "");
        companyInfo.setCname(cname);
        companyInfo.setTitle(title);
        companyInfo.setEmail(email);
        companyInfo.setPhone(phone);
        companyInfo.setWeb(web);

        companyInfo.setInfo(info);
        companyInfo.setAddress(address);
        companyInfo.setType(type);
        companyInfo.setLogo(logo);

        companyInfo.setLawer(lawer);
        companyInfo.setTele(tele);
        companyInfo.setName(name);
        companyInfo.setFax(fax);
        companyInfo.setCateId(cateId);
        companyInfo.setCateName(cateName);
        return companyInfo;
    }
}
