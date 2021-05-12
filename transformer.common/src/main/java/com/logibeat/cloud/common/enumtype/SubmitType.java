package com.logibeat.cloud.common.enumtype;

public enum SubmitType {


    Unknown(0, "未知"),

    Audit(1, "认证"),

    Claim(2, "认领");

    private Integer value;
    private String description;

    SubmitType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


}
