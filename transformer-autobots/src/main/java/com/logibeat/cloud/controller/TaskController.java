package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.enumtype.OrdersStatus;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.dto.*;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.services.CoopEntService;
import com.logibeat.cloud.services.TaskCarService;
import com.logibeat.cloud.services.TaskService;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务操作
 *
 * @author Wilson
 * @version V1.0
 * @Title: Driver_TaskController.java
 * @Package com.yunmaig0o.qtz.driver.controller
 * @Description:
 * @date 2016年1月7日 上午9:12:39
 */
@RestController(value = "autobots.TaskController")
@RequestMapping(value = "Driver/Task/api/DriverTask")
@Scope("prototype")
public class TaskController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected CoopEntService coopEntService;
	

	@Autowired
	private TaskCarService taskCarService;

	/**
	 * 运输中
	 *
	 * @return
	 */
	@RequestMapping(value = "/UnFinished")
	@ResponseBody
	public JSONPrompt<List<OrdersInfoVo>> getUnFinished(String entId, Integer status, Page page) {
		return new JSONPrompt<>(taskCarService.getRunningList(baseUserId,entId,status,page));
	}


	/**
	 * 待发车
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/waitDepart")
	@ResponseBody
	public JSONPrompt getWaitDepart(Page page){
		return new JSONPrompt<>(taskCarService.getWaitDepartList(baseUserId,page));
	}

	/**
	 * 已完成列表
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/Finished")
	@ResponseBody
	public JSONPrompt<List<OrdersInfoVo>> getFinished(String entId, Page page) {
		return new JSONPrompt<>(taskCarService.getHistoryList(baseUserId,entId,page));
	}

	/**
	 * 全部订单列表
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/All")
	@ResponseBody
	public JSONPrompt<PageResultDTO<OrdersInfoVo>> getAll(String entId, Page page,Integer status) {
		return new JSONPrompt<>(taskCarService.getAllList(baseUserId,entId,page,status));
	}


	/**
	 * 任务附加项（数字 红点）
	 * @return
	 */
	@RequestMapping(value = "/getDriverTaskItem")
	@ResponseBody
	public JSONPrompt getDriverTaskItem(){
		return new JSONPrompt<>(taskCarService.getDriverTaskItem(baseUserId));
	}

	/**
	 * 获取订单详细：
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "GetOrdersInfo/{id}")
	@ResponseBody
	@NotLogin
	public JSONPrompt<OrdersDriverDetailVo> getOrdersInfo(@PathVariable String id, String orderCarId) {
		if(StringUtils.isBlank(orderCarId)) {
			orderCarId = id;
		}
		return new JSONPrompt<>(taskCarService.getInfo(orderCarId));
	}


	/**
	 * 任务已读
	 * @param taskCarId
	 * @return
	 */
	@RequestMapping(value = "read")
	@ResponseBody
	public JSONPrompt read(String taskCarId){
		taskCarService.read(taskCarId);
		return  new JSONPrompt();
	}

	/**
	 * 获取在途详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "RunDetail/{id}")
	@ResponseBody
	public JSONPrompt<OrdersEventInfoVo> getRunDetail(@PathVariable String id, String orderCarId, String allOrLast) {
		if(StringUtils.isBlank(orderCarId)) {
			orderCarId = id;
		}
		return new JSONPrompt<>(taskService.getRunDetail(orderCarId, allOrLast));
	}

	/**
	 * @param taskDto
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/createRoute")
	@ResponseBody
	public JSONPrompt<?> createRoute(@RequestBody() TaskDto taskDto) {
		if(StringUtils.isBlank(taskDto.getPersonId())){
			taskDto.setPersonId(baseUserId);
		}
		taskService.createTask(taskDto);
		return new JSONPrompt<>();
	}

	/**
	 * 获得司机端的企业列表 by yjj in 2016-5-13
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getEntList")
	@ResponseBody
	@Deprecated
	public JSONPrompt<List<DriverEntVo>> getEntListOfDriver() {
		return new JSONPrompt<>(coopEntService.getDriverEntList(baseUserId));
	}

	/**
	 * 获得司机端合作企业列表 by yjj in 2016-7-14
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCoopEntList")
	@ResponseBody
	@Deprecated
	public JSONPrompt<List<DriverEntVo>> getCoopEntListOfDriver() {
		return new JSONPrompt<>(coopEntService.getCoopEntList(baseUserId));
	}

	/**
	 * 删除行程 by wangxp in 2016-5-26
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/delRoute")
	@ResponseBody
	public JSONPrompt<?> delRoute(String orderId) {
		JSONPrompt<?> jsonResult = new JSONPrompt<>();
		taskService.delRoute(orderId);
		return jsonResult;
	}
	
	/**
	 * 接单提醒
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getOrderReminder")
	@ResponseBody
	public JSONPrompt<OrderReminderInfoVo> getOrderReminder(String orderCid) {
		return new JSONPrompt<>(taskService.getOrderReminder(orderCid, baseRequestDTO));
	}
	
	/**
	 * 更改目的点
	 * @param taskOrdersAreaDto
	 * @return
     */
	@RequestMapping(value = "/changeEndArea")
	@ResponseBody
	public JSONPrompt<?> changeEndArea(TaskOrdersAreaDto taskOrdersAreaDto){
		JSONPrompt<?> jsonResult = new JSONPrompt<>();
		taskService.changeEndArea(taskOrdersAreaDto);
		return jsonResult;
	}

	/**
	 * 到达提醒
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSendCarReminder")
	@ResponseBody
	public JSONPrompt<OrderReminderInfoVo> getArrivalReminder(String orderCid) {
		return new JSONPrompt<>(taskService.getSendCarReminder(orderCid));
	}
	
	/**
	 * 获取司机当前任务
	 */
	@RequestMapping(value = "/getRunningOrdersList")
	@ResponseBody
	@NotLogin
	public JSONPrompt<List<OrdersInfoVo>> getRunningOrdersList(Page page) {
		return new JSONPrompt<>(taskCarService.getRunningList(baseUserId, null , OrdersStatus.Runing.getValue(), page));
	}
	
	/**
	 * 判断司机是否有任务
	 */
	@RequestMapping(value = "/isTask")
	@ResponseBody
	@NotLogin
	public JSONPrompt<GpsPackTimeVo> isTask(@RequestHeader("baseuserid") String baseUserId) {
		return new JSONPrompt<>(taskService.isTask(baseUserId)) ;
	}
	
	/**
	 * 即将发车提醒详细信息
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getGoingToSendCarReminder")
	@ResponseBody
	@NotLogin
	public JSONPrompt<OrderReminderInfoVo> getGoingToSendCarReminder(String orderCid) {
		return new JSONPrompt<>(taskService.getGoingToSendCarReminder(orderCid, baseRequestDTO));
	}

	/**
	 * 返回司机当前任务或者车辆
	 */
	//TODO  新增接口
	@RequestMapping(value = "/choiceCar")
	@ResponseBody
	@NotLogin
	public JSONPrompt<List<ChoiceCarVo>> choiceCar() {
		JSONPrompt<List<ChoiceCarVo>>  jsonResult = new JSONPrompt<List<ChoiceCarVo>>();
		List<ChoiceCarVo> list = taskCarService.choiceCar(baseRequestDTO);
		jsonResult.setData(list);
		return jsonResult;
	}

	/**
	 * 修改任务单
	 * @param updateTaskDto
	 * @return
	 */
	@RequestMapping(value = "/updateTask")
	@ResponseBody
	@NotLogin
	public JSONPrompt changeTask(UpdateTaskDto updateTaskDto){
		taskService.updateTask(updateTaskDto);
		return  new JSONPrompt();
	}


	/**
	 * 获取当前任务执行位置
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/getTaskPosition")
	@ResponseBody
	@NotLogin
	public JSONPrompt getTaskPosition(String taskId){
       return new JSONPrompt(taskCarService.getTaskPosiotn(taskId));
	}

	/**
	 * 删除任务
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JSONPrompt<?> delete(String taskId) {
		taskService.deleteTaskOrderCar(taskId);
		return new JSONPrompt<>();
	}
    /**
     * 司机发车
     * @param personId
     * @param eventTask
     * @param baseRequestDTO
     * @return
     */
	@RequestMapping(value = "/driverDepart")
	@ResponseBody
	public JSONPrompt driverDepart(String personId, EventTaskDto eventTask, BaseRequestDTO baseRequestDTO){
        taskCarService.driverDepart(personId, eventTask, baseRequestDTO);
        return  new JSONPrompt();
    }

    /**
     * 司机到达
     * @param personId
     * @param eventTask
     * @param baseRequestDTO
     * @return
     */
	@RequestMapping(value = "/driverArrive")
	@ResponseBody
	public JSONPrompt driverArrive(String personId, EventTaskDto eventTask, BaseRequestDTO baseRequestDTO){
        taskCarService.driverArrive(personId, eventTask, baseRequestDTO);
        return  new JSONPrompt();
    }

    /**
     * 司机完成任务
     * @param personId
     * @param eventTask
     * @param baseRequestDTO
     * @return
     */
	@RequestMapping(value = "/driverFinish")
	@ResponseBody
	public JSONPrompt driverFinish(String personId, EventTaskDto eventTask, BaseRequestDTO baseRequestDTO){
        taskCarService.driverFinish(personId, eventTask, baseRequestDTO);
        return  new JSONPrompt();
    }


	/**
	 * 任务IM推送（action :1:派车、2：撤回派车、3：完成）
	 * @return
	 */
	@RequestMapping(value = "/taskImPush")
	@ResponseBody
	@NotLogin
    public JSONPrompt taskImPush(String taskId,String action ){
		taskCarService.taskImPush(taskId,action);
		return  new JSONPrompt();
	}


	/**
	 * 自动完成
	 * @return
	 */
	@RequestMapping(value = "/autoFinishPlanCar")
	@ResponseBody
	@NotLogin
	public JSONPrompt autoFinishPlanCar(){
		taskCarService.autofinishPlanCar();
		return  new JSONPrompt();

	}

	public static void main(String[] args) {
		System.out.println(RandomTool.getRandomString(18));
	}


}
