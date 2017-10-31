package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.Industry;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class GetCompanyCate extends BaseHttpResponse {

    /**
     * data : [{"cateId":"26","name":"计算机互联网","pid":"0"},{"cateId":"27","name":"电子/通讯/电器","pid":"0"},{"cateId":"11","name":"平面设计","pid":"0"}]
     * status : true
     * message :
     */

    private List<Industry> data;

    public List<Industry> getData() {
        return data;
    }

    public void setData(List<Industry> data) {
        this.data = data;
    }


}
