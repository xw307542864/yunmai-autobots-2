package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.Page;

/**
 * 
* @ClassName: DriverDTO 
* @Description: 企业搜索DTO
* @author sean 
* @date 2016年5月30日 上午11:40:37 
* @version 1.0
 */
public class DriverSearchDTO extends Page {
    //企业名称
    private String entName;
    
    private String driverName;
    
    private String phone;
    
    //认证状态
    private Integer auditStatus;
    
    
    
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }
    
    
}
