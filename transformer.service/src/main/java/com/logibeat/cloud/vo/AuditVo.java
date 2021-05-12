package com.logibeat.cloud.vo;

import java.util.List;

public class AuditVo {

    /**
     * ID
     */
    private String         personId;

    /**
     * 姓名
     */
    private String         myName;

    /**
     * 身份证号
     */
    private String         socialLic;

    /**
     * 认证状态
     */
    private Integer        auditStatus;

    /**
     * 认证失败原因
     */
    private String         auditMsg;

    private CarShortInfoVo car;

    private List<String>   pics;
    
    private Integer driverAuditStatus;

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getSocialLic() {
        return socialLic;
    }

    public void setSocialLic(String socialLic) {
        this.socialLic = socialLic;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public CarShortInfoVo getCar() {
        return car;
    }

    public void setCar(CarShortInfoVo car) {
        this.car = car;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

	public Integer getDriverAuditStatus() {
		return driverAuditStatus;
	}

	public void setDriverAuditStatus(Integer driverAuditStatus) {
		this.driverAuditStatus = driverAuditStatus;
	}

}
