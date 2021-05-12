package com.logibeat.cloud.common.enumtype;

public enum ViolationStatusType {

    Unknown(0, "未知"),

    IN(100, "待处理"),

    SHENSHU(200, "申述中"),

    CANCEL(300, "已取消"),

    FINISH(400, "已处理"),

    APPEAL_SUC(500, "申诉成功"),

    APPEAL_FAIL(600, "申诉失败");




    private Integer value;
    private String description;

    ViolationStatusType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }



    public static ViolationStatusType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (ViolationStatusType obj : ViolationStatusType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
