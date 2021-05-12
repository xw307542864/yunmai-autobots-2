package com.logibeat.cloud.common.enumtype;

public enum PointType {


    Unknown(0, "未知（全部）"),


    Fixed(100,"指定"),


    Change(200,"推荐");


    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    PointType(Integer value, String description) {
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


    public static PointType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (PointType obj : PointType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
