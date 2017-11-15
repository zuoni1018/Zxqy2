package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.Message;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/15
 */

public class get_message_tome extends BaseHttpResponse {


    /**
     * p : 1
     * total_page : 1
     * status : true
     * data : [{"messId":"8000","type":"建议","info":"test收到","addTime":"2017-11-14 13:31:41","name":"Rer3r3","fromid":"135370"}]
     */

    private String p;
    private int total_page;
    private List<Message> data;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

}
