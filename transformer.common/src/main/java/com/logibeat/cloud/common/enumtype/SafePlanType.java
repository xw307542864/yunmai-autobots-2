package com.logibeat.cloud.common.enumtype;

public enum SafePlanType {



    BIZTYPE_YUNMAI(100, "运脉添加"),

    BIZTYPE_XRWG(200, "星软违规"),

    BIZTYPE_XRZF(300, "星软增分");


    private Integer value;
    private String description;

    SafePlanType(Integer value, String description) {
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
