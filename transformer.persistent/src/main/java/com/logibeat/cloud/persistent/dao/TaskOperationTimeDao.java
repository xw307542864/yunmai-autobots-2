package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOperationTimeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TaskOperationTimeDao {
    int delete(String guid);

    int insert(TaskOperationTimeEntity entity);

    TaskOperationTimeEntity select(String guid);

    int update(TaskOperationTimeEntity entity);

    /**
     * 获取任务运行时段列表
     * @param ordersCid
     * @return
     */
    List<TaskOperationTimeEntity> getTaskOperationTimeList(String ordersCid);



    List<TaskOperationTimeEntity> getCustomTaskOperationTimeList(@Param("taskId") String taskId,
                                                                 @Param("start")Date start, @Param("end")Date end);
}