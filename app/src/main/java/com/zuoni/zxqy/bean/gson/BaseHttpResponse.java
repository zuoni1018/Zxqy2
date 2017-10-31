package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/9/27
 */

public class BaseHttpResponse  {
    /**
     * status : true
     * message : 短信验证码发送成功
     */

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
