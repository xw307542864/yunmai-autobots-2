package com.logibeat.cloud.common.enumtype;

/**
 * 
 * @ClassName: GpsDeviceType 
 * @Description: gps设备类型
 * @author kzz 
 * @date 2016年12月26日 下午8:34:54 
 * @version 1.0
 */
public enum GpsDeviceType {

    Mobile(0, "手机"), 
    StarSoft(1, "星软");

    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    GpsDeviceType(Integer value, String description){
        this.value = value;
        this.description = description;
    }
    
    public Integer getValue(){
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
    
    
    
    public GpsDeviceType getSelfByValue(String value){
    	GpsDeviceType result = null;
        switch (value) {
		case "0":
			result = GpsDeviceType.Mobile;
			break;
		case "1":
			result = GpsDeviceType.StarSoft;
			break;
		
		}
        
        return result;
    }
}
