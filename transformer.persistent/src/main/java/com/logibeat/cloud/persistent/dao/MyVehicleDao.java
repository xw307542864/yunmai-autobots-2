package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.MyVehicleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MyVehicleDao {

    List<MyVehicleEntity> getMyVehicleList(@Param("personId") String personId);

   MyVehicleEntity select(@Param("guid") String guid);
   
   MyVehicleEntity selectByPersonIdAndEntCarId(@Param("personId") String personId,@Param("entCarId") String entCarId);
   
   /**
    * 更新状态
    * @param entCarId
    * @param auditStatus
    * @param vehicleLicense
    */
   void updateStatus(@Param("guid")String guid,@Param("auditStatus")Integer auditStatus,@Param("status")Integer status);
   
   void updateHandle(@Param("guid")String guid,@Param("handlePersonName")String handlePersonName,@Param("handleMessage")String handleMessage,@Param("type")Integer type);
   
   void updateStatusByGuid(@Param("guid") String guid, @Param("status") Integer status);
   
   void updateStatusByCarId(@Param("carId") String carId, @Param("status") Integer status);
   
   void updateAuditStatusByCarId(@Param("carId") String carId, @Param("auditStatus") Integer auditStatus);
   
   void updateMyVehicleByEntCarId(@Param("vehiclePic") String vehiclePic, @Param("vehicleIdentificationCode") String vehicleIdentificationCode, @Param("vehicleEngineCode") String vehicleEngineCode, 
			@Param("vehicleLicense") String vehicleLicense, @Param("vehicleLicenseOwner") String vehicleLicenseOwner, @Param("entCarId") String entCarId);
}


