package com.logibeat.cloud.common.enumtype;

public enum VehicleHistoryType {

    unknown(0, "未知"),
    failed(100, "审核失败"),
    delete(200,"已删除"),
    remove(300, "移除主驾"),
    back(400, "已撤回"),
    leave(500, "扫码离岗"),
    refuse(600, "企业拒绝");


    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    VehicleHistoryType(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public static VehicleHistoryType find(Integer type) {
        if (type == null) {
            return unknown;
        }
        for (VehicleHistoryType obj : VehicleHistoryType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return unknown;
    }

}
