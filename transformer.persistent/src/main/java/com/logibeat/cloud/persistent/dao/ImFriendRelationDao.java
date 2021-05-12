package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.ImFriendRelationEntity;
import org.apache.ibatis.annotations.Param;

public interface ImFriendRelationDao {
    int delete(String guid);

    int insert(ImFriendRelationEntity entity);

    ImFriendRelationEntity select(String guid);

    int update(ImFriendRelationEntity entity);

    ImFriendRelationEntity getFriendByPersonId(@Param("personId") String personId, @Param("friendPersonId")String friendPersonId);
}