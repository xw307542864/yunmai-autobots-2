package com.logibeat.cloud.services;

import com.logibeat.cloud.dto.task.CreateDriverTaskDto;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import com.logibeat.cloud.vo.task.TaskPointVo;

import java.util.List;

public interface DriverTaskPointService {

    /**
     * 保存装卸点
     * @param createDriverTaskDto
     * @param driverTask
     */
    void saveTaskPoint(CreateDriverTaskDto createDriverTaskDto, DriverTaskEntity driverTask);


    /**
     * 获取任务单装卸点
     * @param taskId
     * @return
     */
    List<TaskPointVo> getPointList(String taskId);


    /**
     * 工作的装卸点
     * @param taskId
     * @return
     */
    TaskPointVo workPoint(String taskId);


    /**
     * 是否需要签收
     * @param taskPoint
     * @param driverTask
     * @return
     */
    boolean showSign(DriverTaskPointEntity taskPoint, DriverTaskEntity driverTask);


    /**
     * 签收
     * @param consignOrderId
     */
    void sign(String consignOrderId);


    /**
     * 待签收
     * @param taskId
     * @return
     */
    List<TaskPointVo> getWaitSignList(String taskId);


}
