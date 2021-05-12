package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.HeartbeatEventEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeartbeatEventDao {
    int delete(String id);

    int insert(HeartbeatEventEntity entity);

    HeartbeatEventEntity select(String id);

    int update(HeartbeatEventEntity entity);

    List<HeartbeatEventEntity> getEventList(@Param("type") Integer type, @Param("taskId") String taskId);
}