package com.zuoni.zxqy.bean.model;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/10/27
 */

public class EnterpriseInformation  {
    /**
     * cname : 臧艺
     * title : 鸿鹄科技
     * email :
     * phone : 15168212330
     * web :
     * info : 公司简介童年up哦上YY是我我嘻嘻嘻嘻是我是休息我是是我是我是是我是我我我是我是我是我是我是我我是我是我是我是我是我是我是我是我是我是我/n \n
     * address : 杭州市赛银国际广场龙
     * type : 集体企业
     * logo : http://106.14.212.85:8080/52dyjob/Public/Uploads/company/2017/11/04/59fd1bbe4da7d.jpg
     * jobs : [{"id":"114491","job_name":"同","company_name":"鸿鹄科技","gender":"2","pay":"1000-1999元","nums":"88","update_time":"2017-11-03 15:47:07"}]
     */

    private String cname;
    private String title;
    private String email;
    private String phone;
    private String web;
    private String info;
    private String address;
    private String type;
    private String logo;
    private List<JobsBean> jobs;

    private String cateName;

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<JobsBean> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsBean> jobs) {
        this.jobs = jobs;
    }

    public static class JobsBean {
        /**
         * id : 114491
         * job_name : 同
         * company_name : 鸿鹄科技
         * gender : 2
         * pay : 1000-1999元
         * nums : 88
         * update_time : 2017-11-03 15:47:07
         */

        private String id;
        private String job_name;
        private String company_name;
        private String gender;
        private String pay;
        private String nums;
        private String update_time;

        public String getArea() {
            if(area==null){
                return "";
            }
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        private String area;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
