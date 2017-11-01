package com.zuoni.zxqy.bean.model;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class Dictionaries {
    /**
     * name : email
     * data : a:3:{s:6:"server";s:16:"smtp.vip.163.com";s:4:"mail";s:19:"52ykjob@vip.163.com";s:4:"pass";s:11:"wang1978223";}
     * remark : 发送邮件信息
     */

    private String name;
    private String data;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
