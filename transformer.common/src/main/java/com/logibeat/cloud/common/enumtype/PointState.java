package com.logibeat.cloud.common.enumtype;

/**
 * Created by wilson on 2017/7/26.
 */
public enum PointState {

    DEPART(0,"发车"),

    WAY_ARRIVE(2,"到达途经点"),

    WAY_DEPART(3,"离开途经点"),

    ARRIVE_XDIS(6,"临近N米"),

    ARRIVE_250(7,"临近250米"),

    OTHER(10,"其他");


    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    PointState(Integer value, String description)
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

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return value.toString();
    }
}
