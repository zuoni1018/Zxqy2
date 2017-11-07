package com.zuoni.zxqy.bean.model;

import java.io.Serializable;

/**
 * Created by zangyi_shuai_ge on 2017/11/7
 */

public class InvitationPeople implements Serializable {

    private String headUrl="";
    private String name="";
    private String workName="";
    private String workId="";

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
