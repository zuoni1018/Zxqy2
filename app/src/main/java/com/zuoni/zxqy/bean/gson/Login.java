package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class Login extends BaseHttpResponse {


    /**
     * status : 2
     * data : {"userid":"黑胡椒","siteId":"7"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * userid : 黑胡椒
         * siteId : 7
         */

        private String userid;
        private String siteId;
        private String accid;
        private String accToken;
        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getAccToken() {
            return accToken;
        }

        public void setAccToken(String accToken) {
            this.accToken = accToken;
        }




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
