package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;

import java.util.List;

/**
 * Created by wilson on 2017/8/8.
 */
public interface TaskCarService {

	/**
	 * 获取未完成的任务
	 *
	 * @param personId
	 * @param entId
	 * @param status
	 * @param page
	 * @return List<OrdersInfoVo>
	 * @Title: getRunningList
	 * @date 2017年8月21日 下午8:40:21
	 */
	List<OrdersInfoVo> getRunningList(String personId, String entId, Integer status, Page page);


	List<OrdersInfoVo> getWaitDepartList(String personId, Page page);

	List<OrdersInfoVo> getHistoryList(String personId, String entId, Page page);

	DriverTaskItemVo getDriverTaskItem(String personId);

	PageResultDTO<OrdersInfoVo> getAllList(String personId, String entId, Page page,Integer status);

	OrdersDriverDetailVo getInfo(String taskCarId);


	void read(String taskCarId);

	/**
	 * 返回司机当前任务或者车辆
	 * @param baseRequestDTO
	 * @return
	 */
	List<ChoiceCarVo> choiceCar(BaseRequestDTO baseRequestDTO);


	/**
	 * 任务发车/到达
	 * @param event
	 * @param baseRequestDTO
	 */
	void sendTaskEvent(String event, BaseRequestDTO baseRequestDTO);



	TaskPositionVo getTaskPosiotn(String taskId);

	/**
	 * 司机发车
	 * @param personId
	 * @param eventTask
	 * @param baseRequestDTO
	 */
	void driverDepart(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO);

	/**
	 * 司机到达
	 * @param personId
	 * @param eventTask
	 * @param baseRequestDTO
	 */
	void driverArrive(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO);

	/**
	 * 司机完成任务
	 * @param personId
	 * @param eventTask
	 * @param baseRequestDTO
	 */
	void driverFinish(String personId, EventTaskDto eventTask, BaseRequestDTO  baseRequestDTO);


	/**
	 * 任务推送
	 * @param taskId action
	 *
	 */
	void taskImPush(String taskId,String action);


	JSONPrompt getChangeStatus(String key);

	JSONPrompt getDriverLocation(String key);


	void autofinishPlanCar();

}