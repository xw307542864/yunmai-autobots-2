package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.CarsEntity;
import org.apache.ibatis.annotations.Param;

public interface CarsDao {
    int delete(String carID);

    int insert(CarsEntity entity);

    CarsEntity select(String carID);

    int update(CarsEntity entity);

    /**
     * 获取司机自己的车辆信息
     * @param personId
     * @return
     */
    CarsEntity getMySelfCar(@Param("personId") String personId);


    /**
     * 更新认证状态
     * @param carId
     * @param auditStatus
     * @param vehicleLicense
     */
    void updateAuditStatus(@Param("carId")String carId,@Param("auditStatus")Integer auditStatus,
                           @Param("vehicleLicense")String vehicleLicense,
                           @Param("vehicleIdentificationCode")String vehicleIdentificationCode,
                           @Param("vehicleEngineCode")String vehicleEngineCode,
                           @Param("vehiclePic")String vehiclePic,
                           @Param("vehicleLicenseOwner")String vehicleLicenseOwner);
    
    public Long getCountByAuditStatus(@Param("plateNumber")String plateNumber, @Param("plateColor")String plateColor);
}