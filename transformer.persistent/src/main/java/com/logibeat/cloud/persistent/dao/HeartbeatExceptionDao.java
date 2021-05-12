package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.HeartbeatExceptionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeartbeatExceptionDao {
    int delete(String id);

    int insert(HeartbeatExceptionEntity entity);

    HeartbeatExceptionEntity select(String id);

    int update(HeartbeatExceptionEntity entity);

    List<HeartbeatExceptionEntity> getExceptionList(@Param("exceptionNum") Integer exceptionNum, @Param("type")Integer type);
}