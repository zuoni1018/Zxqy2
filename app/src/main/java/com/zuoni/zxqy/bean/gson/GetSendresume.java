package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.ResumeManager;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/9
 */

public class GetSendresume extends  BaseHttpResponse {


    /**
     * status : true
     * data : [{"workerId":"135396","name":"晖鸿科技","sex":"女","education":"大专","speciality":"软件工程","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2017/11/07/5a0150abde55b.jpg","is_hide":"1","sendresumeId":"776345","addTime":"2017-11-09 14:27:36","jobstatus":"1","jobName":"ghjh","age":false}]
     */

    private List<ResumeManager> data;

    public List<ResumeManager> getData() {
        return data;
    }

    public void setData(List<ResumeManager> data) {
        this.data = data;
    }


}
