package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/11/15
 */

public class get_invite_detail extends  BaseHttpResponse {


    /**
     * status : true
     * data : {"name":"臧艺","tele":"15168212330","address":"啦啦啦","info":"天天","jobName":"同","img":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 臧艺
         * tele : 15168212330
         * address : 啦啦啦
         * info : 天天
         * jobName : 同
         * img :
         */

        private String name;
        private String tele;
        private String address;
        private String info;
        private String jobName;
        private String img;

        public String getWorkerName() {
            return workerName;
        }

        public void setWorkerName(String workerName) {
            this.workerName = workerName;
        }

        private String workerName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTele() {
            return tele;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
