package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/11/2
 */

public class getPositionDetail extends  BaseHttpResponse {

    /**
     * status : true
     * data : {"id":"114486","companyId":"21896","title":"职位1","tele":"先投简历再电话联系","area":"工作地点1","edu":"中专","jobs":"全职","info":"天","pay":"面议","years":"1","catePid":"1","cateId":"22","contactId":"3424","hukou":"乐","gender":"男","ages":"菁吃了","house":"面议","nums":"5","cateName":"外贸经理/主管","contactName":"扣扣555"}
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
         * id : 114486
         * companyId : 21896
         * title : 职位1
         * tele : 先投简历再电话联系
         * area : 工作地点1
         * edu : 中专
         * jobs : 全职
         * info : 天
         * pay : 面议
         * years : 1
         * catePid : 1
         * cateId : 22
         * contactId : 3424
         * hukou : 乐
         * gender : 男
         * ages : 菁吃了
         * house : 面议
         * nums : 5
         * cateName : 外贸经理/主管
         * contactName : 扣扣555
         */

        private String id;
        private String companyId;
        private String title;
        private String tele;
        private String area;
        private String edu;
        private String jobs;
        private String info;
        private String pay;
        private String years;
        private String catePid;
        private String cateId;
        private String contactId;
        private String hukou;
        private String gender;
        private String ages;
        private String house;
        private String nums;
        private String cateName;
        private String contactName;

        public String getTag() {
            if(tag==null){
                return "";
            }
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        private String tag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTele() {
            return tele;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getEdu() {
            return edu;
        }

        public void setEdu(String edu) {
            this.edu = edu;
        }

        public String getJobs() {
            return jobs;
        }

        public void setJobs(String jobs) {
            this.jobs = jobs;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public String getCatePid() {
            return catePid;
        }

        public void setCatePid(String catePid) {
            this.catePid = catePid;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public String getHukou() {
            return hukou;
        }

        public void setHukou(String hukou) {
            this.hukou = hukou;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAges() {
            return ages;
        }

        public void setAges(String ages) {
            this.ages = ages;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }
    }
}
