package com.logibeat.cloud.msg.enumtype;

public enum MessageType {

    NOTICE(0,"公告"),
    MUCK(1,"渣土"),
    ORDER(2,"订单"),
    YUNDAN(3,"运单"),
    ZHUANYUN(4,"转运单"),
    LINDANORDER(5,"零担订单"),
    LINDANYUN(6,"零担运单"),
    TASK(7,"运单"),
    AUTHEN(8,"认证"),
    EXPIRE(9,"到期"),
    TOEXAMINE(10,"审核"),


    ADDDRIVER(14,"添加司机"),

    VIOLATION(15,"违规"),


    COMMON(16,"通用"),


    CERT(17,"证件");




    protected Integer value;
    protected String description;

    MessageType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
