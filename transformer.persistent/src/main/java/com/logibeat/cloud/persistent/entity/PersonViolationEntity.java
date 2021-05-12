package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

/**
 * 表 person_violation
 * 
 * @author wilson
 * @date 2020-03-12
 */
public class PersonViolationEntity extends EntitySerialize {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;


	private String orderNumber;

	/**
	 *违规来源
	 */
	private Integer violationSource;

	/**  */
	private String entId;
	/**  */
	private String entName;
	/**  */
	private String personId;
	/**  */
	private String personName;
	/**  */
	private String personPhone;
	/**  */
	private String punishPersonName;
	/**  */
	private Integer punishStatus;
	/**  */
	private String punishType;
	/**  */
	private String punishTypeValue;

	private String punishDataId;


	private Date violationTime;

	private String violationRemark;

	/**  */
	private Date punishTime;
	/**  */
	private String punishRemark;
	/**  */
	private Integer parkViolation;
	/**  */
	private Integer businessViolation;
	/**  */
	private Integer speedViolation;
	/**  */
	private Integer operationActionViolation;
	/**  */
	private Integer fatigueViolation;
	/**  */
	private Integer driveActionViolation;
	/**  */
	private Integer vehicleActionViolation;


	private String message;


	private Date updateTime;


	private Date createTime;


	private Integer confirm;


	/**
	 * 取消原因
	 */
	private String cancelReason;



	private Integer appeal;



	private Integer scan;


	private Integer dataSource;

	private String penaltyOrg;




	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setEntId(String entId)
	{
		this.entId = entId;
	}

	public String getEntId() 
	{
		return entId;
	}
	public void setEntName(String entName) 
	{
		this.entName = entName;
	}

	public String getEntName() 
	{
		return entName;
	}
	public void setPersonId(String personId) 
	{
		this.personId = personId;
	}

	public String getPersonId() 
	{
		return personId;
	}
	public void setPersonName(String personName) 
	{
		this.personName = personName;
	}

	public String getPersonName() 
	{
		return personName;
	}
	public void setPersonPhone(String personPhone) 
	{
		this.personPhone = personPhone;
	}

	public String getPersonPhone() 
	{
		return personPhone;
	}
	public void setPunishPersonName(String punishPersonName) 
	{
		this.punishPersonName = punishPersonName;
	}

	public String getPunishPersonName() 
	{
		return punishPersonName;
	}
	public void setPunishStatus(Integer punishStatus) 
	{
		this.punishStatus = punishStatus;
	}

	public Integer getPunishStatus()
	{
		return punishStatus;
	}
	public void setPunishType(String punishType)
	{
		this.punishType = punishType;
	}

	public String getPunishType()
	{
		return punishType;
	}

	public String getPunishTypeValue() {
		return punishTypeValue;
	}

	public void setPunishTypeValue(String punishTypeValue) {
		this.punishTypeValue = punishTypeValue;
	}

	public void setPunishTime(Date punishTime)
	{
		this.punishTime = punishTime;
	}

	public Date getPunishTime() 
	{
		return punishTime;
	}
	public void setPunishRemark(String punishRemark) 
	{
		this.punishRemark = punishRemark;
	}

	public String getPunishRemark() 
	{
		return punishRemark;
	}
	public void setParkViolation(Integer parkViolation) 
	{
		this.parkViolation = parkViolation;
	}

	public Integer getParkViolation() 
	{
		return parkViolation;
	}
	public void setBusinessViolation(Integer businessViolation) 
	{
		this.businessViolation = businessViolation;
	}

	public Integer getBusinessViolation() 
	{
		return businessViolation;
	}
	public void setSpeedViolation(Integer speedViolation) 
	{
		this.speedViolation = speedViolation;
	}

	public Integer getSpeedViolation() 
	{
		return speedViolation;
	}
	public void setOperationActionViolation(Integer operationActionViolation) 
	{
		this.operationActionViolation = operationActionViolation;
	}

	public Integer getOperationActionViolation() 
	{
		return operationActionViolation;
	}
	public void setFatigueViolation(Integer fatigueViolation) 
	{
		this.fatigueViolation = fatigueViolation;
	}

	public Integer getFatigueViolation() 
	{
		return fatigueViolation;
	}
	public void setDriveActionViolation(Integer driveActionViolation) 
	{
		this.driveActionViolation = driveActionViolation;
	}

	public Integer getDriveActionViolation() 
	{
		return driveActionViolation;
	}
	public void setVehicleActionViolation(Integer vehicleActionViolation) 
	{
		this.vehicleActionViolation = vehicleActionViolation;
	}

	public Integer getVehicleActionViolation() 
	{
		return vehicleActionViolation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}


	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}


	public Integer getAppeal() {
		return appeal;
	}

	public void setAppeal(Integer appeal) {
		this.appeal = appeal;
	}

	public Integer getScan() {
		return scan;
	}

	public void setScan(Integer scan) {
		this.scan = scan;
	}


	public Integer getViolationSource() {
		return violationSource;
	}

	public void setViolationSource(Integer violationSource) {
		this.violationSource = violationSource;
	}

	public Date getViolationTime() {
		return violationTime;
	}

	public void setViolationTime(Date violationTime) {
		this.violationTime = violationTime;
	}

	public String getViolationRemark() {
		return violationRemark;
	}

	public void setViolationRemark(String violationRemark) {
		this.violationRemark = violationRemark;
	}

	public String getPunishDataId() {
		return punishDataId;
	}

	public void setPunishDataId(String punishDataId) {
		this.punishDataId = punishDataId;
	}


	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	public String getPenaltyOrg() {
		return penaltyOrg;
	}

	public void setPenaltyOrg(String penaltyOrg) {
		this.penaltyOrg = penaltyOrg;
	}



}
