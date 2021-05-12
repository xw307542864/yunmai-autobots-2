package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

/**
 * 表 person_violation_appeal
 * 
 * @author wilson
 * @date 2020-03-12
 */
public class PersonViolationAppealEntity extends EntitySerialize {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String violationId;
	/**  */
	private String content;
	/**  */
	private String picUrls;

	private Date createTime;

	private Integer status;

	private String refuseReason;

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setViolationId(String violationId) 
	{
		this.violationId = violationId;
	}

	public String getViolationId() 
	{
		return violationId;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setPicUrls(String picUrls) 
	{
		this.picUrls = picUrls;
	}

	public String getPicUrls() 
	{
		return picUrls;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
}
