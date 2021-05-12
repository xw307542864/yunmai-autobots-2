package com.logibeat.cloud.persistent.dao;


import com.logibeat.cloud.persistent.entity.EnterpriseCooperateCarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseCooperateCarDao {
    int delete(String guid);

    int insert(EnterpriseCooperateCarEntity entity);

    EnterpriseCooperateCarEntity select(String guid);

    int update(EnterpriseCooperateCarEntity entity);

    /**
     * 根据企业id和用户id获取企业和车辆的关系（自有和外协）
     * @param personId
     * @param entId
     * @return
     */
    EnterpriseCooperateCarEntity getCarsByEntIdAndPersonId(@Param("entId")String entId , @Param("personId")String personId);

    /**
     * 根据企业id和用户id获取企业和车辆的关系（自有）
     * @param personId
     * @param entId
     * @return
     */
    EnterpriseCooperateCarEntity getSelfCarByEntIdAndPersonId(@Param("entId") String entId, @Param("personId")String personId );



    /**
     * 根据企业id和用户id获取企业和外协车辆的关系
     * @param personId
     * @param entId
     * @return
     */
    EnterpriseCooperateCarEntity getCoopCarByEntIdAndPersonId(@Param("entId") String entId, @Param("personId")String personId );

    /**
     * 更新车辆状态
     * @param entId
     * @param carId
     * @param status
     * @return
     */
    int updateCarStatus(@Param("entId")String entId, @Param("carId")String carId, @Param("status")Integer status);

    /**
     * 获取企业和车辆的关系
     *
     * @Title: getEnterpriseCooperateCarEntityByCar
     * @param carId
     * @param entId
     * @return EnterpriseCooperateCarEntity
     */
    EnterpriseCooperateCarEntity getCooperCarByEntIdAndCarId(@Param("entId")String entId, @Param("carId")String carId);

    /**
     * 通过carId获取企业与车辆关系
     *
     * @Title: getEntCar
     * @param entId
     * @param carId
     * @return EnterpriseCooperateCarEntity
     */
    EnterpriseCooperateCarEntity getEntCarByCarId(@Param("entId")String entId, @Param("carId")String carId);

    int updateIsFriend(@Param("entId")String entId, @Param("carId")String carId, @Param("isFriend")byte isFriend);

    List<EnterpriseCooperateCarEntity> getDriverEntCarList(@Param("personId")String personId, @Param("coopType")String coopType);

    /**
     * 获取企业车辆数量
     * @param entId
     * @param coopType
     * @return
     */
    Long getEntCarNum(@Param("entId")String entId, @Param("coopType")Integer coopType);

    /**
     * 获取企业自有车辆
     * @param carId
     * @param entId
     * @return
     */
    EnterpriseCooperateCarEntity getSelfCarByEntId(@Param("carId") String carId, @Param("entId") String entId);



    
    List<EnterpriseCooperateCarEntity> getDriverCarList(@Param("phoneNumber")String phoneNumber,
			   @Param("personId")String personId,
			   @Param("coopType")String coopType);
    
    List<EnterpriseCooperateCarEntity> getCarByPersonIdAndEntId(@Param("firstDriverId") String firstDriverId, @Param("entId") String entId);

    /**
     * 更新认证状态
     * @param entCarId
     * @param auditStatus
     * @param vehicleLicense
     */
    void updateAuditStatus(@Param("entCarId")String entCarId,
                           @Param("auditStatus")Integer auditStatus,
                           @Param("vehicleLicense")String vehicleLicense,
                           @Param("vehicleIdentificationCode")String vehicleIdentificationCode,
                           @Param("vehicleEngineCode")String vehicleEngineCode,
                           @Param("vehiclePic") String vehiclePic,
                           @Param("vehicleLicenseOwner")String vehicleLicenseOwner);
    
    public Long getCountByAuditStatus(@Param("plateNumber")String plateNumber, @Param("plateColor")String plateColor, @Param("coopType")Integer coopType);
    
    EnterpriseCooperateCarEntity getEntCarByPlateNumberAndColor(@Param("plateNumber")String plateNumber, @Param("plateColor")String plateColor, @Param("coopType")Integer coopType, @Param("entId")String entId);
    
    void updateAuditStatusByEntCarId(@Param("entCarId") String entCarId, @Param("auditStatus") Integer auditStatus);
	
	void updateInvitestate(@Param("entCarId") String entCarId, @Param("invitestate") Integer invitestate);
	
	void updateEntCarByEntCarId(@Param("vehiclePic") String vehiclePic, @Param("vehicleIdentificationCode") String vehicleIdentificationCode, @Param("vehicleEngineCode") String vehicleEngineCode, 
			@Param("vehicleLicense") String vehicleLicense, @Param("vehicleLicenseOwner") String vehicleLicenseOwner, @Param("entCarId") String entCarId);
	
	void setCoopCarToNullByCarId(@Param("entId") String entId, @Param("carId") String carId);
	
	void updateCoopNum(@Param("entId") String entId, @Param("carId") String carId);

}