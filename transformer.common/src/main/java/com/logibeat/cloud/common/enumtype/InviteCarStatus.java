package com.logibeat.cloud.common.enumtype;

public enum InviteCarStatus {

    unknown(0, "未知"),
    waiting(1, "审核中 "),
    verified(2, "已(通过)认证"),
    failed(3, "认证失败"),
    verify(4, "申请认证 "),
    ent_verify(5, "待企业审核 "),
    ent_waiting(6, "待企业审核 "),
    ent_verified(7, "企业已编制 "),
    ent_failed(8, "企业已拒绝 ");





    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    InviteCarStatus(Integer value, String description)
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

    public static InviteCarStatus find(Integer type) {
        if (type == null) {
            return unknown;
        }
        for (InviteCarStatus obj : InviteCarStatus.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return unknown;
    }
}
