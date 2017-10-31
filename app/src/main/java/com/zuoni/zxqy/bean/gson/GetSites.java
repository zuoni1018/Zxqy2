package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.City;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class GetSites extends  BaseHttpResponse{


    /**
     * data : [{"siteId":"1","name":"众信人才网--总站","domain":"www.52dyjob.com"},{"siteId":"2","name":"永康众信人才网","domain":"www.52ykjob.com"},{"siteId":"3","name":"中国五金人才网","domain":"www.52wjjob.com"},{"siteId":"4","name":"武义众信人才网","domain":"www.52wyjob.com"},{"siteId":"5","name":"东阳众信人才网","domain":"www.52dyjob.com"},{"siteId":"6","name":"金华众信人才网","domain":"www.52jhjob.com"},{"siteId":"7","name":"嵊州中信人才网","domain":"www.52szrc.com"},{"siteId":"8","name":"嘉兴众达人才网","domain":"www.52jxjob.com"},{"siteId":"9","name":"桐乡众达人才网","domain":"www.52txjob.com"},{"siteId":"10","name":"海宁众达人才网","domain":"www.52hnjob.com"},{"siteId":"11","name":"安吉众达人才网","domain":"www.52ajjob.com"},{"siteId":"12","name":"诸暨中信人才网","domain":"www.52zjjob.com"}]
     * status : true
     */
    private List<City> data;

    public List<City> getData() {
        return data;
    }
    public void setData(List<City> data) {
        this.data = data;
    }
}
