package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.HeartbeatCallbackEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeartbeatCallbackDao {
    int delete(String id);

    int insert(HeartbeatCallbackEntity entity);

    HeartbeatCallbackEntity select(String id);

    int update(HeartbeatCallbackEntity entity);

    List<HeartbeatCallbackEntity> getEntityByEventId(@Param("eventId") String eventId);
}