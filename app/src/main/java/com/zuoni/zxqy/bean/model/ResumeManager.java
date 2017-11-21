package com.zuoni.zxqy.bean.model;

/**
 * Created by zangyi_shuai_ge on 2017/11/9
 */

public class ResumeManager {
    /**
     * workerId : 135396
     * name : 晖鸿科技
     * sex : 女
     * education : 大专
     * speciality : 软件工程
     * img : http://106.14.212.85:8080/52dyjob/Public/Uploads/users/2017/11/07/5a0150abde55b.jpg
     * is_hide : 1
     * sendresumeId : 776345
     * addTime : 2017-11-09 14:27:36
     * jobstatus : 1
     * jobName : ghjh
     * age : false
     */


    private String workerId;
    private String name;
    private String sex;
    private String education;
    private String speciality;
    private String img;
    private String is_hide;
    private String sendresumeId;
    private String addTime;
    private String jobstatus; //2 已查看 1未查看 3已查看并已邀请
    private String jobName;
    private String  age;
    private String birth;
    private String favId;
    private String hopepostion;
    private String inviteId;

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    private String inviteStatus;

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }

    public String getHopepostion() {
        return hopepostion;
    }

    public void setHopepostion(String hopepostion) {
        this.hopepostion = hopepostion;
    }

    public String getFavId() {
        return favId;
    }

    public void setFavId(String favId) {
        this.favId = favId;
    }

    public String getFavJobName() {
        return favJobName;
    }

    public void setFavJobName(String favJobName) {
        this.favJobName = favJobName;
    }

    private String favJobName;

    public String getJobViewId() {
        return jobViewId;
    }

    public void setJobViewId(String jobViewId) {
        this.jobViewId = jobViewId;
    }

    private String jobViewId;

    public String getViewJobName() {
        return viewJobName;
    }

    public void setViewJobName(String viewJobName) {
        this.viewJobName = viewJobName;
    }

    private String viewJobName;


    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAge() {
        if(age.equals("false")){
            return "0";
        }
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    private boolean isChoose;

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
        if(is_hide==null){
            return "1";
        }
        return is_hide;
    }

    public void setIs_hide(String is_hide) {
        this.is_hide = is_hide;
    }

    public String getSendresumeId() {
        return sendresumeId;
    }

    public void setSendresumeId(String sendresumeId) {
        this.sendresumeId = sendresumeId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


}
