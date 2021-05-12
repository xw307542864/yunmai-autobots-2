package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.NewFriendLogDetailEntity;

public interface NewFriendLogDetailDao {
    int delete(String id);

    int insert(NewFriendLogDetailEntity entity);

    NewFriendLogDetailEntity select(String id);

    int update(NewFriendLogDetailEntity entity);
}