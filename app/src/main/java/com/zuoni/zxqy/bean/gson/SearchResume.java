package com.zuoni.zxqy.bean.gson;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class SearchResume extends  BaseHttpResponse {

    /**
     * p : 1
     * total_page : 2
     * data : [{"workerId":"135176","name":"黄燕芳","birth":"1995-03-26","sex":"女","jobyear":"","jiguan":"安徽","hopepostion":"出纳，会计助理","lastTime":"2015-12-03 18:30:33","img":"","education":"大专","age":22},{"workerId":"134370","name":"钱兴娟","birth":"1989-05-05","sex":"女","jobyear":"3","jiguan":"浙江绍兴","hopepostion":"会计，出纳","lastTime":"2015-11-12 14:59:25","img":"","education":"大专","age":28},{"workerId":"134048","name":" 徐林仕","birth":"1982-04-18","sex":"男","jobyear":"","jiguan":"永康","hopepostion":"采购、计划","lastTime":"2015-12-07 10:29:30","img":"","education":"大专","age":35},{"workerId":"134051","name":"叶景","birth":"1994-12-14","sex":"女","jobyear":"1","jiguan":"武义","hopepostion":"助理会计、出纳、统计","lastTime":"2015-10-20 14:13:03","img":"","education":"大专","age":22},{"workerId":"134499","name":"黄晶晶","birth":"1989-01-16","sex":"女","jobyear":"","jiguan":"浙江","hopepostion":"会计或人事","lastTime":"2015-11-29 09:47:43","img":"","education":"大专","age":28},{"workerId":"134501","name":"胡艳琳","birth":"1995-06-21","sex":"女","jobyear":"","jiguan":"浙江永康","hopepostion":"出纳，及会计相关岗位","lastTime":"2015-11-10 20:19:38","img":"","education":"大专","age":22},{"workerId":"134504","name":"吴永生","birth":"1990-11-25","sex":"男","jobyear":"1","jiguan":"江西","hopepostion":"行政助理 人事专员","lastTime":"2015-11-12 15:20:25","img":"2015/11/08/m_563edb2fca399.jpeg","education":"本科","age":26},{"workerId":"135067","name":"王杜娟","birth":"1995-12-20","sex":"女","jobyear":"","jiguan":"浙江永康","hopepostion":"会计,出纳,文员,销售","lastTime":"2015-11-30 09:02:17","img":"","education":"大专","age":21},{"workerId":"135069","name":"王梦雅","birth":"1994-07-03","sex":"女","jobyear":"","jiguan":"浙江永康","hopepostion":"会计助理","lastTime":"2015-11-30 13:46:26","img":"2015/11/30/m_565bbb840ef01.jpg","education":"本科","age":23},{"workerId":"135191","name":"郦依璐","birth":"1995-06-05","sex":"女","jobyear":"","jiguan":"浙江省永康市","hopepostion":"会计","lastTime":"2015-12-06 12:35:08","img":"","education":"大专","age":22}]
     * status : true
     */

    private String p;
    private int total_page;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * workerId : 135176
         * name : 黄燕芳
         * birth : 1995-03-26
         * sex : 女
         * jobyear :
         * jiguan : 安徽
         * hopepostion : 出纳，会计助理
         * lastTime : 2015-12-03 18:30:33
         * img :
         * education : 大专
         * age : 22
         */

        private String workerId;
        private String name;
        private String birth;
        private String sex;
        private String jobyear;
        private String jiguan;
        private String hopepostion;
        private String lastTime;
        private String img;
        private String education;
        private int age;
        private boolean isChoose=false;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

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

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getJobyear() {
            return jobyear;
        }

        public void setJobyear(String jobyear) {
            this.jobyear = jobyear;
        }

        public String getJiguan() {
            return jiguan;
        }

        public void setJiguan(String jiguan) {
            this.jiguan = jiguan;
        }

        public String getHopepostion() {
            return hopepostion;
        }

        public void setHopepostion(String hopepostion) {
            this.hopepostion = hopepostion;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
