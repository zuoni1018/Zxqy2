package com.zuoni.zxqy.bean.gson;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class getHotKeyword extends BaseHttpResponse {


    /**
     * data : ["会计","业务员","文员","生产","仓管","品质","设计","技术","开发","检验","司机","采购","营业员","销售","驾驶员","办公室"]
     * status : true
     */

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
