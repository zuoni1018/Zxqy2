package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class Regist extends BaseHttpResponse {


    /**
     * token : f575c125ce78f1ef78ae66904af140bf
     * data : {"userid":"黄家驹","siteId":"8"}
     * status : true
     */

    private String token;
    private DataBean data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : 黄家驹
         * siteId : 8
         */

        private String userid;
        private String siteId;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }
    }
}
