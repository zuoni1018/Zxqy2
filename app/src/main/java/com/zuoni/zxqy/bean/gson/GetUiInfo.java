package com.zuoni.zxqy.bean.gson;

/**
 * Created by zangyi_shuai_ge on 2017/10/26
 */

public class GetUiInfo extends  BaseHttpResponse{


    /**
     * data : {"cname":"hjij","title":"fyuu","email":"","phone":"15168212330","msgSend":"0","msgReceive":"0","resumeTotal":"0","resumeMax":"0","vipLevel":"0","vipStart":"0000-00-00","vipEnd":"0000-00-00","img":"","resumeLeft":0,"vipTime":0,"viewResume":"0","receiveResume":"0","favs":"0"}
     * status : true
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
         * cname : hjij
         * title : fyuu
         * email :
         * phone : 15168212330
         * msgSend : 0
         * msgReceive : 0
         * resumeTotal : 0
         * resumeMax : 0
         * vipLevel : 0
         * vipStart : 0000-00-00
         * vipEnd : 0000-00-00
         * img :
         * resumeLeft : 0
         * vipTime : 0
         * viewResume : 0
         * receiveResume : 0
         * favs : 0
         */

        private String cname;
        private String title;
        private String email;
        private String phone;
        private String msgSend;
        private String msgReceive;
        private String resumeTotal;
        private String resumeMax;
        private String vipLevel;
        private String vipStart;
        private String vipEnd;
        private String img;
        private int resumeLeft;
        private int vipTime;
        private String viewResume;
        private String receiveResume;
        private String favs;
        private String chatLast;

      private    int postJobs;

        public int getPostJobs() {
            return postJobs;
        }

        public void setPostJobs(int postJobs) {
            this.postJobs = postJobs;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private String address;

        public String getChatLast() {
            return chatLast;
        }

        public void setChatLast(String chatLast) {
            this.chatLast = chatLast;
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

        public String getMsgSend() {
            return msgSend;
        }

        public void setMsgSend(String msgSend) {
            this.msgSend = msgSend;
        }

        public String getMsgReceive() {
            return msgReceive;
        }

        public void setMsgReceive(String msgReceive) {
            this.msgReceive = msgReceive;
        }

        public String getResumeTotal() {
            return resumeTotal;
        }

        public void setResumeTotal(String resumeTotal) {
            this.resumeTotal = resumeTotal;
        }

        public String getResumeMax() {
            return resumeMax;
        }

        public void setResumeMax(String resumeMax) {
            this.resumeMax = resumeMax;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public String getVipStart() {
            return vipStart;
        }

        public void setVipStart(String vipStart) {
            this.vipStart = vipStart;
        }

        public String getVipEnd() {
            return vipEnd;
        }

        public void setVipEnd(String vipEnd) {
            this.vipEnd = vipEnd;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getResumeLeft() {
            return resumeLeft;
        }

        public void setResumeLeft(int resumeLeft) {
            this.resumeLeft = resumeLeft;
        }

        public int getVipTime() {
            return vipTime;
        }

        public void setVipTime(int vipTime) {
            this.vipTime = vipTime;
        }

        public String getViewResume() {
            return viewResume;
        }

        public void setViewResume(String viewResume) {
            this.viewResume = viewResume;
        }

        public String getReceiveResume() {
            return receiveResume;
        }

        public void setReceiveResume(String receiveResume) {
            this.receiveResume = receiveResume;
        }

        public String getFavs() {
            return favs;
        }

        public void setFavs(String favs) {
            this.favs = favs;
        }
    }
}
