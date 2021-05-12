package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EventSpaceLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventSpaceLogDao {
    int delete(String id);

    int insert(EventSpaceLogEntity entity);

    EventSpaceLogEntity select(String id);

    int update(EventSpaceLogEntity entity);

    List<EventSpaceLogEntity> getEntity(@Param("eventId") String eventId, @Param("eventRemark")String eventRemark);
}