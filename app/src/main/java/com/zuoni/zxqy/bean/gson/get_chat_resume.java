package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.ResumeManager;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/24
 */

public class get_chat_resume extends BaseHttpResponse {

    /**
     * p : 1
     * total_page : 1
     * status : true
     * data : [{"workerId":"135371","name":"hyf","birth":"2016-11-10","sex":"男","jobyear":"2","jiguan":"gjhj","hopepostion":"Adadaddd","lastTime":"2017-09-28 13:43:28","img":"2017/11/02/59fad25d31997.png","userChatId":"user_135371","education":"中专","age":1}]
     */

    private List<ResumeManager> data;

    public List<ResumeManager> getData() {
        return data;
    }
    public void setData(List<ResumeManager> data) {
        this.data = data;
    }
}
