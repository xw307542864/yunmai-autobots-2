package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.TaskTrackService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "taskTrack/api")
@Scope("prototype")
public class TaskTrackController {

	@Autowired
	private TaskTrackService taskTrackService;
	
	
	/**
	 * 任务历史轨迹
	 * @Title: getTaskTrack  
	 * @return
	 * JSONPrompt<TaskTrackVo>
	 * @date 2017年8月15日 下午2:31:05
	 *
	 */
	@RequestMapping(value = "/getTaskTrack")
	@ResponseBody
	@NotLogin
	public JSONPrompt getTaskTrack(String taskId,String consignId){
		return new JSONPrompt(taskTrackService.getTaskHistoryOrbit(taskId,consignId));
	}
	
	/**
	 * 导航
	 * @Title: navigation  
	 * @param orderCarId
	 * @return 
	 * JSONPrompt<TaskTrackVo>
	 * @date 2017年8月15日 下午2:36:09
	 *
	 */
//	@RequestMapping(value = "/navigation")
//	@ResponseBody
//	@NotLogin
//	public JSONPrompt<TaskAreaVo> navigation(String orderCarId, String areaGuid){
//		return new JSONPrompt<>(taskTrackService.navigation(orderCarId, areaGuid));
//	}



	
}
