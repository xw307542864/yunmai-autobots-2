package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.NetworkEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NetworkDao {
    int delete(String guid);

    int insert(NetworkEntity entity);

    NetworkEntity select(String guid);

    int update(NetworkEntity entity);

    List<NetworkEntity> getEntityByType(@Param("entId") String entId,@Param("type") String type,@Param("networkNameLike") String networkNameLike);
}