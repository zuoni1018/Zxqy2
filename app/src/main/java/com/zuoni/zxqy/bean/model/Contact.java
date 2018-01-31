package com.zuoni.zxqy.bean.model;

import java.io.Serializable;

/**
 * Created by zangyi_shuai_ge on 2017/10/30
 */

public class Contact implements Serializable{
    /**
     * contactId : 3416
     * name : zangyi
     * tele : 15168212330
     * fax :
     * email :
     * address : know
     */

    private String contactId;
    private String name;
    private String tele;
    private String fax;
    private String email;
    private String address;
    private String is_lock;

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
