package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.FriendsEntity;
import org.apache.ibatis.annotations.Param;

public interface FriendsDao {
    int delete(String guid);

    int insert(FriendsEntity entity);

    FriendsEntity select(String guid);

    int update(FriendsEntity entity);

    FriendsEntity getMyFriendByPersonId(@Param("friendPersonId") String friendPersonId, @Param("personId")String personId);

    void cancelEntFriend(@Param("entId")String entId, @Param("personId")String personId);

}