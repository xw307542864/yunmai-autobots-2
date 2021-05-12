package com.logibeat.cloud.common.enumtype;

public enum ApprovalType {

    Unknown(0, "未知",""),
    NOCAR(1, "无车司机申请","司机审批"),
    DRIVEREXTRAWORK(2, "加班班次","加班班次"),
	HAVECAR(3, "有车申请","有车申请"),
	COOP(4, "合作伙伴","合作伙伴"),
    SETTLEMENT(5, "财务审批","财务审批"),
    DRIVERADDCAR(6, "司机添加车辆","企业运力审核");




    protected Integer  value;
    protected String  description;
    protected String webDescription;

    ApprovalType(Integer value, String description,String webDescription){
        this.value = value;
        this.description = description;
        this.webDescription = webDescription;
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

    public String getWebDescription() {
        return webDescription;
    }

    public void setWebDescription(String webDescription) {
        this.webDescription = webDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ApprovalType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (ApprovalType obj : ApprovalType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
