package com.zuoni.zxqy.bean.model;

/**
 * Created by zangyi_shuai_ge on 2017/10/31
 */

public class Job {
    /**
     * jobId : 114462
     * title : fggh
     * hits : 0
     * status : 1
     * update_time : 2017-10-31 09:53:38
     */

    private String jobId;
    private String title;
    private boolean isChoose=false;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String hits;
    private String status;
    private String update_time;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }


    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
