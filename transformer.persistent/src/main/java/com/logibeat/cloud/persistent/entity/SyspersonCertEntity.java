package com.logibeat.cloud.persistent.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: Model类
 * Date: 2020-12-07
 */
public class SyspersonCertEntity implements Serializable {
    //TODO: Do not forget add "serialVersionUID" field AND change package path!

    
    
    private String guid;
    
    /**
     * 司机ID
     */
    private String personId;
    
    /**
     * 证件类型
     */
    private String certType;
    
    /**
     * 证件名称
     */
    private String certName;
    
    /**
     * 证件来源 100运脉 200自建 300星软
     */
    private Integer certSource;
    
    /**
     * 证件信息
     */
    private String certInfo;
    
    private Integer isDel;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String startsoftCertId;
    
    public String getStartsoftCertId() {
		return startsoftCertId;
	}

	public void setStartsoftCertId(String startsoftCertId) {
		this.startsoftCertId = startsoftCertId;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public Integer getCertSource() {
        return certSource;
    }

    public void setCertSource(Integer certSource) {
        this.certSource = certSource;
    }

    public String getCertInfo() {
        return certInfo;
    }

    public void setCertInfo(String certInfo) {
        this.certInfo = certInfo;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
