package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.CarImportEntity;

public interface CarImportDao {
    int delete(String carId);

    int insert(CarImportEntity entity);

    CarImportEntity select(String carId);

    int update(CarImportEntity entity);

}