package com.logibeat.cloud.common.enumtype;

public enum AuditSource {



    Unknown(0, "未知"),
    LogisticsApp(1, "物流app"),
    Web(2, "web"),
	DriverApp(3,"司机app"),	
	WeChat(4,"微物流");
    private Integer value;
    private String description;

    AuditSource(Integer value, String description) {
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
