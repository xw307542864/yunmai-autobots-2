package com.logibeat.cloud.common.enumtype;

/**
 * 
 * @ClassName:  DynamicType   
 * @Description:动态的类型   
 * @author: zk 
 * @date:   2017年4月12日 下午4:15:31     
 *
 */
public enum DynamicType {
	//以后2为非任务动态  3为任务动态
	Unknown(0, "未知"),//废弃
	One(1, "随便说说"),//废弃
	Two(2, "发路况"),//非任务动态
	Three(3, "任务");//任务动态
    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    DynamicType(Integer value, String description)
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
    
    
    public static DynamicType find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (DynamicType obj : DynamicType.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
    
   
}
