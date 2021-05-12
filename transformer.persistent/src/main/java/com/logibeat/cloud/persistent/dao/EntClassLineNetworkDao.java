package com.logibeat.cloud.persistent.dao;


import com.logibeat.cloud.persistent.entity.EntClassLineNetworkEntity;

public interface EntClassLineNetworkDao {
    int delete(String guid);

    int insert(EntClassLineNetworkEntity entity);

    EntClassLineNetworkEntity select(String guid);

    int update(EntClassLineNetworkEntity entity);
}