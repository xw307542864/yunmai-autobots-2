package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.ShareLogEntity;

public interface ShareLogDao {
    int delete(String guid);

    int insert(ShareLogEntity entity);

    ShareLogEntity select(String guid);

    int update(ShareLogEntity entity);
}