package com.logibeat.cloud.common.enumtype;

public enum TaskType {

    Unknown(0,"未知（全部）"),


    Stowage(100,"配载单派车"),


    Deliery(200,"派送单派车"),


    Collect(300,"揽收单派车"),


    Consign(400,"托运单派车"),


    InviteCar(500,"请车单派车"),


    Concrete_YUYUE(600,"商砼(混凝土)预约单"),


    Assemble(700,"整车拼装派车"),


    Concrete_FAHUO(800,"商砼发货单");





    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    TaskType(Integer value, String description) {
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


    public static TaskType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (TaskType obj : TaskType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
