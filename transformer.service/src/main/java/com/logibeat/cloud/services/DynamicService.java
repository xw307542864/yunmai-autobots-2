package com.logibeat.cloud.services;


import com.logibeat.cloud.dto.DynamicDto;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.core.dto.TaskDto;
import com.logibeat.cloud.persistent.entity.TaskDynamicEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersEntity;

import java.util.List;


/**
 * 动态服务类
 * 
 * @author karl
 * @date 2016年1月5日
 * @version 1.0
 */
public interface DynamicService {


    /**
     * 发车/到达
     * @param personId
     * @param taskCar
     * @param eventTaskDto
     * @param taskList
     * @return
     */
    public DynamicDto preAddDynamic(String personId, TaskOrdersCarEntity taskCar,
                                    EventTaskDto eventTaskDto, List<TaskOrdersEntity> taskList);


    /**
     * 发行程
     * @param personId
     * @param taskCar
     * @param taskDto
     * @param taskAreaId
     * @return
     */
    public DynamicDto preAddDynamic(String personId, TaskOrdersCarEntity taskCar, TaskDto taskDto, String taskAreaId);


    /**
     * 动态的保存
     * @param dynamicDto
     * @return
     */

    public TaskDynamicEntity addDynamic(DynamicDto dynamicDto);



}
