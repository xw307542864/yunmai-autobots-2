package com.logibeat.cloud.common.enumtype;

public enum LoadType {


    Unknown(0, "未知（全部）"),


    Load(1,"装货点"),


    UnLoad(2,"卸货点");


    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    LoadType(Integer value, String description) {
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


    public static LoadType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (LoadType obj : LoadType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }


}
