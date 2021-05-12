package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

/**
 * 表 person_violation_info
 * 
 * @author wilson
 * @date 2020-03-12
 */
public class PersonViolationInfoEntity extends EntitySerialize {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String violationGuid;
	/**  */
	private String type;

	private String value;

	/**  */
	private Integer number;
	/**  */
	private String detail;

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setViolationGuid(String violationGuid) 
	{
		this.violationGuid = violationGuid;
	}

	public String getViolationGuid() 
	{
		return violationGuid;
	}
	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
	}
	public void setDetail(String detail) 
	{
		this.detail = detail;
	}

	public String getDetail() 
	{
		return detail;
	}


}
