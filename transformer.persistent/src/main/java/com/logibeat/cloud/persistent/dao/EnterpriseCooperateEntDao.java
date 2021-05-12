package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EnterpriseCooperateEntEntity;

public interface EnterpriseCooperateEntDao {
    int delete(String guid);

    int insert(EnterpriseCooperateEntEntity entity);

    EnterpriseCooperateEntEntity select(String guid);

    int update(EnterpriseCooperateEntEntity entity);

}