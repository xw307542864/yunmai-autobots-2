package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOrdersCarInfoEntity;
import org.apache.ibatis.annotations.Param;

public interface TaskOrdersCarInfoDao {
    int delete(String id);

    int insert(TaskOrdersCarInfoEntity entity);

    TaskOrdersCarInfoEntity select(String id);

    int update(TaskOrdersCarInfoEntity entity);

    TaskOrdersCarInfoEntity getByTaskId(@Param("taskId") String taskId);
}