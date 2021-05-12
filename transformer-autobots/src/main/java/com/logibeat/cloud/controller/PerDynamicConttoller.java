package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.TaskCarService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态
 *
 * @author Wilson
 * @version V1.0
 * @Title: Driver_PerDynamic Conttoller.java
 * @Package com.yunmaigo.qtz.driver.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2016年1月5日 上午11:08:21
 */
@RestController
@RequestMapping(value = "Driver/Im/api/PerDynamic")
@Scope("prototype")
public class PerDynamicConttoller extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PerDynamicConttoller.class);


	@Autowired
	protected TaskCarService taskCarService;

	/**
	 * 司机任务反馈（新版本）
	 *
	 * @return
	 */
	@RequestMapping(value = "/sendTaskEvent")
	@ResponseBody
	@NotLogin
	public JSONPrompt sendTaskEvent(String event) {
		JSONPrompt jsonResult = new JSONPrompt();
		taskCarService.sendTaskEvent(event, baseRequestDTO);
		return jsonResult;
	}


	/**
	 * 获取单据变更
	 * @param resKey
	 * @return
	 */
	@RequestMapping(value = "/getChangeStatus")
	@ResponseBody
	@NotLogin
	public JSONPrompt getChangeStatus(String resKey){
		return taskCarService.getChangeStatus(resKey);
	}



	@RequestMapping(value = "/getDriverLocation")
	@ResponseBody
	@NotLogin
	public JSONPrompt getDriverLocation(String resKey){
		return taskCarService.getDriverLocation(resKey);
	}





}
