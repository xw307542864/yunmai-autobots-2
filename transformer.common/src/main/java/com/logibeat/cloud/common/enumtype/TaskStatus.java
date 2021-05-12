package com.logibeat.cloud.common.enumtype;

public enum  TaskStatus {

    Unknown(0,"未知（全部）",0),


    WaitRun(100,"待发车",4),


    Runing(400,"在途中",5),


    Cancel(800,"已取消",0),


    Finish(1200,"已完成",6);


    protected Integer  value;

    protected String  description;

    protected Integer remark;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    TaskStatus(Integer value, String description,Integer remark) {
        this.value = value;
        this.description = description;
        this.remark =remark;
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

    public Integer getRemark()
    {
        return remark;
    }

    public void setRemark(Integer remark)
    {
        this.value = remark;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value.toString();
    }


    public static TaskStatus find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (TaskStatus obj : TaskStatus.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
