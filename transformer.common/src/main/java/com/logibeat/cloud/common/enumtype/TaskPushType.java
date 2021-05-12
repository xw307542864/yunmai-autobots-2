package com.logibeat.cloud.common.enumtype;

public enum TaskPushType {

    Unknown("0","未知（全部）"),

    Stowage("1","新任务提醒"),


    Deliery("2","任务撤回提醒"),


    Collect("3","完成任务提醒"),


    Consign("4","新预约单提醒"),


    InviteCar("5","预约单变更提醒"),


    Concrete_YUYUE("6","预约单删除提醒");



    protected String  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    TaskPushType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
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


    public static TaskPushType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (TaskPushType obj : TaskPushType.values()) {
            String value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
