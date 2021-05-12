package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.GpsPackTimeVo;
import com.logibeat.cloud.common.vo.OrderReminderInfoVo;
import com.logibeat.cloud.common.vo.OrdersEventInfoVo;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.TaskDto;
import com.logibeat.cloud.core.dto.TaskOrdersAreaDto;
import com.logibeat.cloud.core.dto.UpdateTaskDto;

/**
 * @author sean
 * @version 1.0
 * @ClassName: TaskService
 * @Description: 任务服务类
 * @date 2016年1月4日 上午9:37:51
 */
public interface TaskService {

    /**
     * 获取在途详情
     * @Title: getRunDetail
     * @param orderCarId
     * @param allOrLast
     * @return
     * OrdersEventInfoVo
     * @date 2017年8月19日 下午2:22:02
     *
     */
    OrdersEventInfoVo getRunDetail(String orderCarId, String allOrLast);



    void createTask(TaskDto taskDto);

    /**
     * 删除行程
     *
     * @param orderId
     * @return
     */
    void delRoute(String orderId);
    /**
     * 新任务提醒
     * @Title: getOrderReminder
     * @param orderCarId
     * @param baseRequestDTO
     * @return
     * OrderReminderInfoVo
     * @date 2017年8月19日 下午2:01:01
     *
     */
    OrderReminderInfoVo getOrderReminder(String orderCarId, BaseRequestDTO baseRequestDTO);

    /**
     * 发车提醒
     */
    OrderReminderInfoVo getSendCarReminder(String orderCid);

    void changeEndArea(TaskOrdersAreaDto taskOrdersAreaDto);


    GpsPackTimeVo isTask(String baseUserId);

    /**
     * 即将发车提醒返回信息
     * @return
     */
    OrderReminderInfoVo getGoingToSendCarReminder(String orderCid, BaseRequestDTO baseRequestDTO);


    void updateTask(UpdateTaskDto updateTaskDto);

    /**
     * 删除任务
     * @Title: deleteTaskOrderCar
     * @param orderCarId
     * void
     * @date 2017年8月16日 下午2:54:51
     *
     */
    void deleteTaskOrderCar(String orderCarId);



}
