package com.logibeat.cloud.common.vo;

import java.util.List;

/**
 * 
* @ClassName: DriverVo 
* @Description: 司机模块视图对象
* @author sean 
* @date 2016年8月2日 上午10:14:54 
* @version 1.0
 */
public class DriverVo {
    
    private String id;
    
    private String entName;
    
    private String niChen;
    
    private String phone;
    
    private String auditStatusTitle;
    
    private Integer auditStatus;
    
    private String socialLic;
    
    /**
     * 注册时间
     */
    private String regTime;
    
    /**
     * 归属企业
     */
    private List<DriverEntVo> driverEntVos;
    
    
    

    public String getSocialLic() {
        return socialLic;
    }

    public void setSocialLic(String socialLic) {
        this.socialLic = socialLic;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public List<DriverEntVo> getDriverEntVos() {
        return driverEntVos;
    }

    public void setDriverEntVos(List<DriverEntVo> driverEntVos) {
        this.driverEntVos = driverEntVos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getNiChen() {
        return niChen;
    }

    public void setNiChen(String niChen) {
        this.niChen = niChen;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuditStatusTitle() {
        return auditStatusTitle;
    }

    public void setAuditStatusTitle(String auditStatusTitle) {
        this.auditStatusTitle = auditStatusTitle;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    
    
    

}
