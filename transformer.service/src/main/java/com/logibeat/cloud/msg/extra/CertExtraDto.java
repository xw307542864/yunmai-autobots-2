package com.logibeat.cloud.msg.extra;

public class CertExtraDto  {

	private String certId;
	 
    private String certName;
    
    private String certTime;
    
    private String sendPrefix;//100政府 200协会 300企业
    
    /**
     * 类型
     */
    private Integer bizType;
    
    
    
	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getSendPrefix() {
		return sendPrefix;
	}

	public void setSendPrefix(String sendPrefix) {
		this.sendPrefix = sendPrefix;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getCertTime() {
		return certTime;
	}

	public void setCertTime(String certTime) {
		this.certTime = certTime;
	}
}
