package com.logibeat.cloud.common.enumtype;

/**
 * 
* @ClassName: CarStatus 
* @Description: 车辆状态
* @author karl 
* @date 2015年12月22日 上午10:35:09 
* @version 1.0
 */
public enum CarStatus {

	All(0, "所有"),
	Free(1, "空闲"), 
	Ready(2, "待发"), 
	Running(3, "在途"), 
    No(4, "不可用");
    
    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param
     * @param
     */
    CarStatus(Integer value, String description)
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
    
    public static CarStatus getSelfByValue(Integer value){
    	CarStatus result = null;
    	switch (value) {
		case 0:
			result = CarStatus.All;
			break;
		case 1:
			result = CarStatus.Free;
			break;
		case 2:
			result = CarStatus.Ready;
			break;
		case 3:
			result = CarStatus.Running;
			break;
		case 4:
			result = CarStatus.No;
			break;
		
		}
    	
    	return result;
    }
}
