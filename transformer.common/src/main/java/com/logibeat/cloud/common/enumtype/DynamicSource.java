package com.logibeat.cloud.common.enumtype;

/**
 * 
 * @ClassName:  DynamicSource   
 * @Description:动态来源   
 * @author: zk 
 * @date:   2017年4月12日 下午4:16:39     
 *
 */
public enum DynamicSource {
	//如有后期其他  直接添加即可
	Unknown(0, "未知"),
	One(1, "transformer系统"),
	Two(2, "渣土车");
    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    DynamicSource(Integer value, String description)
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
    
    
    public static DynamicSource find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (DynamicSource obj : DynamicSource.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
    
   
}
