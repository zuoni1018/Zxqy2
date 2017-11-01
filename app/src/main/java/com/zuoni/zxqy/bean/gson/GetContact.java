package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.Contact;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/30
 */

public class GetContact extends BaseHttpResponse {


    /**
     * data : [{"contactId":"3416","name":"zangyi","tele":"15168212330","fax":"","email":"","address":"know"},{"contactId":"3415","name":"zangyi","tele":"15168212330","fax":"","email":"","address":"know"},{"contactId":"3417","name":"zangyi","tele":"15168212330","fax":"","email":"","address":"know"}]
     * status : true
     */

    private List<Contact> data;

    public List<Contact> getData() {
        return data;
    }
    public void setData(List<Contact> data) {
        this.data = data;
    }

}
