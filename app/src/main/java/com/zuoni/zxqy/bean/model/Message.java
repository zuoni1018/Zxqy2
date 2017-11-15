package com.zuoni.zxqy.bean.model;

/**
 * Created by zangyi_shuai_ge on 2017/11/15
 */

public class Message {
    /**
     * messId : 8000
     * type : 建议
     * info : test收到
     * addTime : 2017-11-14 13:31:41
     * name : Rer3r3
     * fromid : 135370
     */

    private String messId;
    private String type;
    private String info;
    private String addTime;
    private String name;
    private String fromid;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    private boolean isChoose;

    public String getMessId() {
        return messId;
    }

    public void setMessId(String messId) {
        this.messId = messId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }
}
