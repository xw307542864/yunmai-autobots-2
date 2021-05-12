package com.logibeat.cloud.common.vo;

/**
 * 
 * @Description TODO 搜索企业按钮
 * @ClassName   SimpEntButton 
 * @Date        2017年2月23日 下午5:57:30 
 * @Author      jin
 * Copyright (c) All Rights Reserved, 2017.
 */
public class SimpEntButton {
	
	private boolean cooperationBtn;//建立合作
	
	private boolean cancelBtn;//取消合作
	
	private boolean sendTaskBtn;//派单
	
	private boolean acceptBtn;//通过验证
	
	private boolean sendMessage;//发送信息
	
	private boolean sendInviteBtn;//发送邀请
	
	private boolean deleteBtn;//删除按钮

	public boolean isCooperationBtn() {
		return cooperationBtn;
	}

	public void setCooperationBtn(boolean cooperationBtn) {
		this.cooperationBtn = cooperationBtn;
	}

	public boolean isCancelBtn() {
		return cancelBtn;
	}

	public void setCancelBtn(boolean cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public boolean isSendTaskBtn() {
		return sendTaskBtn;
	}

	public void setSendTaskBtn(boolean sendTaskBtn) {
		this.sendTaskBtn = sendTaskBtn;
	}

	public boolean isAcceptBtn() {
		return acceptBtn;
	}

	public void setAcceptBtn(boolean acceptBtn) {
		this.acceptBtn = acceptBtn;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public boolean isSendInviteBtn() {
		return sendInviteBtn;
	}

	public void setSendInviteBtn(boolean sendInviteBtn) {
		this.sendInviteBtn = sendInviteBtn;
	}

	public boolean isDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(boolean deleteBtn) {
		this.deleteBtn = deleteBtn;
	}
	
}
