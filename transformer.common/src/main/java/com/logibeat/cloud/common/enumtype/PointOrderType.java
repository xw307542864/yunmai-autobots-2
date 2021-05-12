package com.logibeat.cloud.common.enumtype;

public enum PointOrderType {

    Unknown(0, "未知（全部）"),


    Order(100,"订单"),


    Consign(200,"托运单"),


    Stowage(300,"配载单"),


    Concrete(400,"预约单");




    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    PointOrderType(Integer value, String description) {
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value.toString();
    }


    public static PointOrderType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (PointOrderType obj : PointOrderType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
