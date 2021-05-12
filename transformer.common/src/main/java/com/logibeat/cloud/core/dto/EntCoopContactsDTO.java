package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2017/2/15.
 */
public class EntCoopContactsDTO {

    private String contactsId; //接口人/外部联系人ID

    private String entId;

    private String mobile;

    private String name;

    private String position;

    private String entPerId;

    private Integer type;  // 1:接口人  2：外部联系人

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEntPerId() {
        return entPerId;
    }

    public void setEntPerId(String entPerId) {
        this.entPerId = entPerId;
    }
}
