package com.logibeat.cloud.msg.template;

import com.alibaba.fastjson.JSONObject;
import com.logibeat.cloud.msg.constant.MessageConstant;
import com.logibeat.jetfire.client.template.PushTemplate;

public class NewTaskPushTemplate extends PushTemplate {

	private final transient String extraVoiceContent_sameCity_noPassPoint = "您有新的任务，请您尽快执行。";
	private final transient String extraVoiceContent_sameCity_passPoint = "您有新的任务，包含<%s>个卸货点，请您尽快执行。";
	private final transient String extraVoiceContent_noPassPoint = "您有新的任务，从<%s>到<%s>，请您尽快执行。";
	private final transient String extraVoiceContent_passPoint = "您有新的任务，从<%s>到<%s>，包含<%s>个卸货点，请您尽快执行。";

	/**
	 * 新任务推送模板
	 * 
	 * @param entName      企业名称
	 * @param personId      接收人id
	 * @param orderCarId   派车信息id
	 * @param startAreaName  发车点名称
	 * @param endAreaName     终点名称
	 * @param isSameCity     是否同城
	 * @param areaNum     网点数量 (不含发车点)
	 */
	public NewTaskPushTemplate(String entName, String personId, String orderCarId, String startAreaName, String endAreaName,
                               boolean isSameCity, int areaNum) {
		super();
		setApp("driver");
		setBaseUserId(personId);
		setDisplayType(MessageConstant.PushContentType.NOTIFICATION.getName());
		setRemark(MessageConstant.ENT_TASK_DRIVER);
		setSound(MessageConstant.DEFAULT_SOUND);
		setText(entName + MessageConstant.PushTipMessage.NEWTASK.getName());
		setTicker(entName + MessageConstant.PushTipMessage.NEWTASK.getName());
		setTitle(MessageConstant.PushTipMessage.TASK_REMIND.getName());
		setType(MessageConstant.MessageTypeToDevice.MESSAGETASK.getValue());

		JSONObject json = new JSONObject();
		// 同城没有途经点
		if (isSameCity && areaNum <= 1) {
			json.put("extraVoiceContent", extraVoiceContent_sameCity_noPassPoint);
		}

		// 同城有途经点
		if (isSameCity && areaNum > 1) {
			json.put("extraVoiceContent", String.format(extraVoiceContent_sameCity_passPoint, areaNum));
		}

		// 跨城没有途经点
		if (!isSameCity && areaNum <= 1) {
			json.put("extraVoiceContent", String.format(extraVoiceContent_noPassPoint, startAreaName, endAreaName));
		}

		// 跨城有途经点
		if (!isSameCity && areaNum > 1) {
			json.put("extraVoiceContent",
					String.format(extraVoiceContent_passPoint, startAreaName, endAreaName, areaNum));
		}

		json.put("extraOrderId", orderCarId);
		setExtra(json);

	}

	
}
