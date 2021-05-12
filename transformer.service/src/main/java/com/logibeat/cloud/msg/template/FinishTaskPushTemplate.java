package com.logibeat.cloud.msg.template;

import com.logibeat.cloud.msg.constant.MessageConstant;
import com.logibeat.jetfire.client.template.PushTemplate;

public class FinishTaskPushTemplate extends PushTemplate {

	/**
	 * 任务完成推送
	 * @param entrustPersonId 委托人id
	 * @param carrierName 承运人名称 （企业名称或司机名称+车牌号）
	 */
	public FinishTaskPushTemplate(String entrustPersonId, String carrierName){
		super();
		setApp("ent");
		setBaseUserId(entrustPersonId);
		setDisplayType(MessageConstant.PushContentType.NOTIFICATION.getName());
		setSound(MessageConstant.DEFAULT_SOUND);
		setText("您给[" + carrierName + "]发的任务已完成");
		setTicker(MessageConstant.PushTipMessage.FINISH_TASK.getName());
		setTitle(MessageConstant.PushTipMessage.TASK_REMIND.getName());
		setType(MessageConstant.MessageTypeToDevice.MESSAGETASK.getValue());
	}
}
