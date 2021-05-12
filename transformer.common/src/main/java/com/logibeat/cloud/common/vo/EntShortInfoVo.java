package com.logibeat.cloud.common.vo;

/**
 * 
* @ClassName: EntShortInfoVo 
* @Description: 企业简短信息
* @author sean 
* @date 2016年1月18日 上午11:46:09 
* @version 1.0
 */
public class EntShortInfoVo {
    private String entID;
    
    private String name;
    
    private String imgPath;
    
    private boolean isNew;
    
    private boolean isBan;
    
    private boolean isOauth;//是否认证
    
    private Integer oauthType;//认证类型（1-个人 2.企业）
    
    private boolean isMeEnt;//是否是当前企业
    
    private Integer auditStatus;//企业认证状态(1. 待审核 2. 审核通过 3. 审核不通过 4. 未认证 )
    
    public boolean isBan() {
		return isBan;
	}

	public void setIsBan(boolean isBan) {
		this.isBan = isBan;
	}

	public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getEntId() {
        return entID;
    }

    public void setEntId(String entId) {
        this.entID = entId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    
	public boolean getIsOauth() {
		return isOauth;
	}

	public void setIsOauth(boolean isOauth) {
		this.isOauth = isOauth;
	}

	public Integer getOauthType() {
		return oauthType;
	}

	public void setOauthType(Integer oauthType) {
		this.oauthType = oauthType;
	}

	public boolean getIsMeEnt() {
		return isMeEnt;
	}

	public void setIsMeEnt(boolean isMeEnt) {
		this.isMeEnt = isMeEnt;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
