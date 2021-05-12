package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.DriverTaskItemVo;
import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.dto.task.HandleTaskDto;
import com.logibeat.cloud.dto.task.SearchTaskDto;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.vo.task.CreateTaskByOutVo;
import com.logibeat.cloud.vo.task.DriverTaskListVo;
import com.logibeat.cloud.vo.task.RunningTaskVo;
import com.logibeat.cloud.vo.task.TaskDetailVo;

import java.util.List;

public interface DriverTaskService {


    /**
     * 创建任务单
     * @param createDriverTaskDto
     */
    DriverTaskEntity create(CreateDriverTaskDto createDriverTaskDto);



    /**
     * 创建任务单
     * @param createDriverTaskDto
     */
    CreateTaskByOutVo createByOut(CreateDriverTaskDto createDriverTaskDto);






    /**
     *任务单列表
     * @param searchTaskDto
     * @return
     */
    List<DriverTaskListVo> list(SearchTaskDto searchTaskDto);


    /**
     * 详情
     * @param taskId
     * @return
     */
    TaskDetailVo detail(String taskId);


    /**
     * 在途任务
     * @param personId
     * @return
     */
    RunningTaskVo runningTask(String personId);


    /**
     * 执行中任务
     * @param personId
     * @return
     */
    RunningTaskVo executingTask(String personId);




    /**
     * 去装货
     * @param handleTaskDto
     */
    void goLoad(HandleTaskDto handleTaskDto);




    /**
     * 确认到达（装卸货点）
     * @param handleTaskDto
     */
    void arrive(HandleTaskDto handleTaskDto);


    /**
     * 开始装货
     * @param handleTaskDto
     */
    void startLoad(HandleTaskDto handleTaskDto);



    /**
     * 完成装货
     * @param handleTaskDto
     */
    void finishLoad(HandleTaskDto handleTaskDto);


    /**
     * 去卸货
     * @param handleTaskDto
     */
    void goUnLoad(HandleTaskDto handleTaskDto);


    /**
     * 开始卸货
     * @param handleTaskDto
     */
    void startUnLoad(HandleTaskDto handleTaskDto);


    /**
     * 完成卸货
     * @param handleTaskDto
     */
    void finishUnLoad(HandleTaskDto handleTaskDto);


    /**
     * 派单推送
     * @param taskId
     * @param action
     */
    void taskImPush(String taskId,String action);


    /**
     * 任务附加项
     * @param personId
     * @return
     */
    DriverTaskItemVo getDriverTaskItem(String personId, String entId);

    /**
     * 删除
     * @param taskId
     */
    void delete(String taskId);


    void deleteByRelationId(String relationOrderId,String vehicleId);


}
