package com.zuoni.zxqy.bean.gson;

import com.zuoni.zxqy.bean.model.ResumeManager;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/14
 */

public class GetViewresume extends  BaseHttpResponse {


    /**
     * p : 1
     * total_page : 1
     * status : true
     * data : [{"workerId":"134500","name":"陈周淼","sex":"男","education":"大专","speciality":"法学","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2015/11/08/m_563ea7973e603.jpg","is_hide":"0","birth":"1988-03-30","hopepostion":"销售","viewresumeId":"1790316","addTime":"2017-11-14 12:01:22","age":29},{"workerId":"135176","name":"黄燕芳","sex":"女","education":"大专","speciality":"会计电算化","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1995-03-26","hopepostion":"出纳，会计助理","viewresumeId":"1790315","addTime":"2017-11-14 11:46:57","age":22},{"workerId":"134370","name":"钱兴娟","sex":"女","education":"大专","speciality":"会计","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1989-05-05","hopepostion":"会计，出纳","viewresumeId":"1790314","addTime":"2017-11-14 11:45:31","age":28},{"workerId":"134102","name":"赵尘影","sex":"女","education":"大专","speciality":"市场营销","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1992-10-31","hopepostion":"销售内勤  人事 采购","viewresumeId":"1790310","addTime":"2017-11-10 17:02:57","age":25},{"workerId":"134102","name":"赵尘影","sex":"女","education":"大专","speciality":"市场营销","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1992-10-31","hopepostion":"销售内勤  人事 采购","viewresumeId":"1790309","addTime":"2017-11-10 17:02:55","age":25},{"workerId":"135298","name":"吕思思","sex":"女","education":"中专","speciality":"学前教育","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"1","birth":"1991-05-25","hopepostion":"接单员  文员","viewresumeId":"1790308","addTime":"2017-11-10 17:02:52","age":26},{"workerId":"135184","name":"李黎明","sex":"男","education":"大专","speciality":"计算机信息管理","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1986-11-23","hopepostion":"PMC主管   PMC经理","viewresumeId":"1790307","addTime":"2017-11-10 17:02:48","age":30},{"workerId":"135192","name":"王诗洋","sex":"男","education":"大专","speciality":"市场营销","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1987-10-14","hopepostion":"业务经理","viewresumeId":"1790306","addTime":"2017-11-10 17:02:44","age":30},{"workerId":"135371","name":"hyf","sex":"男","education":"中专","speciality":"ufyf","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2017/11/02/59fad25d31997.png","is_hide":"0","birth":"2016-11-10","hopepostion":"Adadaddd","viewresumeId":"1790302","addTime":"2017-11-10 16:33:08","age":1},{"workerId":"134320","name":"华笑凡","sex":"男","education":"","speciality":"","img":"http://106.14.212.85:8080/52dyjob/Public/Uploads/users/","is_hide":"0","birth":"1994-07-01","hopepostion":"司机 驾驶员 销售司机","viewresumeId":"1790299","addTime":"2017-11-10 15:31:48","age":23}]
     */

    private String p;
    private int total_page;
    private List<ResumeManager> data;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<ResumeManager> getData() {
        return data;
    }

    public void setData(List<ResumeManager> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * workerId : 134500
         * name : 陈周淼
         * sex : 男
         * education : 大专
         * speciality : 法学
         * img : http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2015/11/08/m_563ea7973e603.jpg
         * is_hide : 0
         * birth : 1988-03-30
         * hopepostion : 销售
         * viewresumeId : 1790316
         * addTime : 2017-11-14 12:01:22
         * age : 29
         */

        private String workerId;
        private String name;
        private String sex;
        private String education;
        private String speciality;
        private String img;
        private String is_hide;
        private String birth;
        private String hopepostion;
        private String viewresumeId;
        private String addTime;
        private int age;

        public String getWorkerId() {
            return workerId;
        }

        public void setWorkerId(String workerId) {
            this.workerId = workerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_hide() {
            return is_hide;
        }

        public void setIs_hide(String is_hide) {
            this.is_hide = is_hide;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getHopepostion() {
            return hopepostion;
        }

        public void setHopepostion(String hopepostion) {
            this.hopepostion = hopepostion;
        }

        public String getViewresumeId() {
            return viewresumeId;
        }

        public void setViewresumeId(String viewresumeId) {
            this.viewresumeId = viewresumeId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
