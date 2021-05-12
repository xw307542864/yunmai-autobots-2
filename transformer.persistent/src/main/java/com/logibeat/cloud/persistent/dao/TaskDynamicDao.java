package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskDynamicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDynamicDao {
    int delete(String guid);

    int insert(TaskDynamicEntity entity);

    TaskDynamicEntity select(String guid);

    int update(TaskDynamicEntity entity);

    List<TaskDynamicEntity> findByOrderAsDynamic(@Param("ordersCid")String ordersCid);

    List<TaskDynamicEntity> getRunDynamicList(@Param("ordersCid")String ordersCid, @Param("allOrLast")String allOrLast);

    TaskDynamicEntity getTaskDynamic(@Param("ordersCid")String ordersCid, @Param("action")int action);

    List<TaskDynamicEntity> getDynamicListByAreaGUid(@Param("ordersCid")String ordersCid, @Param("action")int action, @Param("ordersAreaGUID")String ordersAreaGUID);

}