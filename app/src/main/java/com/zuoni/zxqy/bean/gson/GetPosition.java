package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.Job;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/31
 */

public class GetPosition extends BaseHttpResponse {

    /**
     * data : [{"jobId":"114462","title":"fggh","hits":"0","status":"1","update_time":"2017-10-31 09:53:38"},{"jobId":"114463","title":"fggh","hits":"0","status":"1","update_time":"2017-10-31 09:53:38"},{"jobId":"114464","title":"yyyuu","hits":"0","status":"1","update_time":"2017-10-31 09:56:51"},{"jobId":"114465","title":"yyyuu","hits":"0","status":"1","update_time":"2017-10-31 09:56:51"}]
     * status : true
     */

    private List<Job> data;

    public List<Job> getData() {
        return data;
    }

    public void setData(List<Job> data) {
        this.data = data;
    }

}
