package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DriverInterestLabelEntity;

import java.util.List;

/**
 * Desc: Dao类
 * Date: 2020-05-26
 */
public interface DriverInterestLabelDao {
    int insert(DriverInterestLabelEntity driverInterestLabel);

    DriverInterestLabelEntity findById(String guid);

    DriverInterestLabelEntity findByUserId(String baseUserId);

    int update(DriverInterestLabelEntity driverInterestLabel);

    int delete(String guid);

    long count(DriverInterestLabelEntity driverInterestLabel);

    List<DriverInterestLabelEntity> paging(DriverInterestLabelEntity driverInterestLabel);
}
