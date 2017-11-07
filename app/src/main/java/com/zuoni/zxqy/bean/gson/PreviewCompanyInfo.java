package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.EnterpriseInformation;

/**
 * Created by zangyi_shuai_ge on 2017/10/27
 */

public class PreviewCompanyInfo extends BaseHttpResponse {
    /**
     * data : {"cname":"臧艺","title":"鸿鹄科技","email":"","phone":"15168212330","web":"","info":"公司简介童年up哦上YY是我我嘻嘻嘻嘻是我是休息我是是我是我是是我是我我我是我是我是我是我是我我是我是我是我是我是我是我是我是我是我是我/n \\n","address":"杭州市赛银国际广场龙","type":"集体企业","logo":"http://106.14.212.85:8080/52dyjob/Public/Uploads/company/2017/11/04/59fd1bbe4da7d.jpg","jobs":[{"id":"114491","job_name":"同","company_name":"鸿鹄科技","gender":"2","pay":"1000-1999元","nums":"88","update_time":"2017-11-03 15:47:07"}]}
     * status : true
     */

    private EnterpriseInformation data;

    public EnterpriseInformation getData() {
        return data;
    }

    public void setData(EnterpriseInformation data) {
        this.data = data;
    }


}
