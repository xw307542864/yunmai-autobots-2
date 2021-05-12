package com.logibeat.cloud.common.enumtype;

public enum TaskordersCarType {

	Unknown(0, "未知（全部）"),
    
    ZHONGTONG(2, "中通"),
    
    YUMAI(3,"运脉"),
	
	CreateRoute(30, "行程"),
	 
	MUCK_GeneralOrder(31, "普通运输单(渣土应用)"),

    MUCK_SelfOwnedOrder(32, "自运单(渣土应用)"),
    
    BIZ_TASK(33, "普通任务（原任务）"),
    
    BIZ_ORDER(34, "业务单据-配载派车"),

    BIZ_DELIVERY_ORDER(35, "业务单据-派送派车"),

    BIZ_COLLECTION_ORDER(36, "业务单据-揽收派车"),

    PLAN_CAR_ORDER(37, "预约单");


    private Integer value;
    private String description;

    TaskordersCarType(Integer value, String description) {
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
