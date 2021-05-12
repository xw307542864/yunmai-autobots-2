package com.logibeat.cloud.common.enumtype;

public enum  TaskCurrentStatus {

    Unknown(0,"未知（全部）","未知"),


    WaitLoadDepart(1001,"装货点待出车","等待前往装货点"),


    GoLoad(1002,"去装货","请准时到达装货点"),


    ArriveLoad(1003,"到达装货点","请尽快装货"),


    StartLoad(1004,"开始装货","装货中，请确保货物安全"),


    FinishLoad(1005,"完成装货","请前往下一个点"),


    WaitUnLoadDepart(1006,"卸货点待出车","请尽快前往卸货点"),


    GoUnLoad(1007,"去卸货","请准时到达卸货点"),


    ArriveUnLoad(1008,"到达卸货点","请尽快卸货"),


    StartUnLoad(1009,"开始卸货","卸货中，请确保货物安全"),


    FinishUnLoad(1010,"完成卸货","请前往下一个点"),


    Finish(1011,"完成任务","任务已完结");





    protected Integer  value;

    protected String  description;

    protected String remark;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    TaskCurrentStatus(Integer value, String description,String remark) {
        this.value = value;
        this.description = description;
        this.remark = remark;
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


    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value.toString();
    }


    public static TaskCurrentStatus find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (TaskCurrentStatus obj : TaskCurrentStatus.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
}
