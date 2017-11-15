package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.ResumeManager;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/14
 */

public class GetCompanyFav extends  BaseHttpResponse {


    /**
     * p : 1
     * total_page : 1
     * status : true
     * data : [{"workerId":"134500","name":"陈周淼","sex":"男","education":"大专","speciality":"法学","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2015/11/08/m_563ea7973e603.jpg","is_hide":"0","birth":"1988-03-30","hopepostion":"销售","favId":"80022","addTime":"2017-11-14 12:01:25","favJobName":null,"age":29},{"workerId":"135176","name":"黄燕芳","sex":"女","education":"大���","speciality":"会计电算化","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1995-03-26","hopepostion":"出纳，会计助理","favId":"80021","addTime":"2017-11-14 11:47:57","favJobName":null,"age":22}]
     */

    private String p;
    private int total_page;
    private List<ResumeManager> data;

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

    public List<ResumeManager> getData() {
        return data;
    }

    public void setData(List<ResumeManager> data) {
        this.data = data;
    }

}
