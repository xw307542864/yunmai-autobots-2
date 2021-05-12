package com.logibeat.cloud.common.vo;

public class DriverInfoButtonVo {
	
	/**
	 * 发送邀请
	 */
	private Boolean sendInviteBtn;
	/**
	 * 发送消息
	 */
	private Boolean sendMessageBtn;
	/**
	 * 添加企业司机
	 */
	private Boolean addSelfDriverBtn;
	/**
	 * 添加外协司机
	 */
	private Boolean addCoopDriverBtn;
	/**
	 * 通过验证
	 */
	private Boolean approvedBtn;
	/**
	 *  发任务
	 */
	private Boolean sendTaskBtn;
	
	
	public Boolean getSendInviteBtn() {
		return sendInviteBtn;
	}
	public void setSendInviteBtn(Boolean sendInviteBtn) {
		this.sendInviteBtn = sendInviteBtn;
	}
	public Boolean getSendMessageBtn() {
		return sendMessageBtn;
	}
	public void setSendMessageBtn(Boolean sendMessageBtn) {
		this.sendMessageBtn = sendMessageBtn;
	}
	public Boolean getAddSelfDriverBtn() {
		return addSelfDriverBtn;
	}
	public void setAddSelfDriverBtn(Boolean addSelfDriverBtn) {
		this.addSelfDriverBtn = addSelfDriverBtn;
	}
	public Boolean getAddCoopDriverBtn() {
		return addCoopDriverBtn;
	}
	public void setAddCoopDriverBtn(Boolean addCoopDriverBtn) {
		this.addCoopDriverBtn = addCoopDriverBtn;
	}
	public Boolean getApprovedBtn() {
		return approvedBtn;
	}
	public void setApprovedBtn(Boolean approvedBtn) {
		this.approvedBtn = approvedBtn;
	}
	public Boolean getSendTaskBtn() {
		return sendTaskBtn;
	}
	public void setSendTaskBtn(Boolean sendTaskBtn) {
		this.sendTaskBtn = sendTaskBtn;
	}

}
