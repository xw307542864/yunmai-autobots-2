package com.logibeat.cloud.core.dto;

/**
 * 
* @ClassName: DriverAuditDTO 
* @Description: 司机验证DTO 
* @author sean 
* @date 2016年8月11日 下午3:54:18 
* @version 1.0
 */
public class DriverAuditDTO {
    /**
     * 认证类型
     */
    private Integer type;
    
    /**
     * 车辆ID
     */
    private String carId;
    
    /**
     * 发动机号
     */
    private String engine;
    
    /**
     * 车架号
     */
    private String frame;
    
    /**
     * 品牌号
     */
    private String brand;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    
}
