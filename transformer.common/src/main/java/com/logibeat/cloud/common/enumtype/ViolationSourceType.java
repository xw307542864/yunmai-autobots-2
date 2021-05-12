package com.logibeat.cloud.common.enumtype;

public enum  ViolationSourceType {




    WEIGUIBAOJING(100,"违规报警"),
    SHOUGONGXINZENG(200,"手工新增"),
    WEIGUIGUIZE(300,"违规规则"),
    JIFENGUIZE(400,"积分规则");



    protected Integer code;
    protected String description;

    ViolationSourceType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setValue(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
