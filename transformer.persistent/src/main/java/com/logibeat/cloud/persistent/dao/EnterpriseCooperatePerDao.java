package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EnterpriseCooperatePerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseCooperatePerDao {
    int delete(String guid);

    int insert(EnterpriseCooperatePerEntity entity);

    EnterpriseCooperatePerEntity select(String guid);

    int update(EnterpriseCooperatePerEntity entity);

    /**
     * 获取企业与司机的关系 （自由司机和外协司机）
     * @param entId
     * @param personId
     * @return
     */
    EnterpriseCooperatePerEntity getCoopByEntIdAndPersonId(@Param("entId") String entId , @Param("personId") String personId);

    /**
     * @param entId
     * @param personId
     * @return EnterpriseCooperatePerEntity
     * @Title: getEntOwnerPerson
     * @Description: 查询企业拥有者或者员工
     */
    EnterpriseCooperatePerEntity getEntOwnerPerson(@Param("entId")String entId, @Param("personId")String personId);

    int updateIsFriend(@Param("entId")String entId, @Param("firstDriverPersonId")String firstDriverPersonId,
                       @Param("sencondDriverPersonId")String sencondDriverPersonId, @Param("isFriend")byte isFriend);

    EnterpriseCooperatePerEntity getCoopByEntAndPersonByDelete(@Param("entId")String entId, @Param("personId")String personId);

    /**
     * 获取企业联系人
     * @param entId
     * @return
     */
    EnterpriseCooperatePerEntity getEntContact(@Param("entId")String entId);

    List<EnterpriseCooperatePerEntity> getEnterpriseCooperatePer(
            @Param("keyword")String keyword, @Param("isAll")boolean isAll, @Param("starMark")Integer starMark,
            @Param("entTypeDictGUID")String entTypeDictGUID, @Param("friend")Integer friend, @Param("personId")String personId,
            @Param("coopType")Integer coopType);

    /**
     * 获取司机所属企业列表
     * @param personId
     * @param coopType
     * @return
     */
    List<EnterpriseCooperatePerEntity> getDriverEntList(@Param("personId")String personId, @Param("coopType")Integer coopType);

    /**
     * 获取企业联系人数量
     * @param entId
     * @param orgGuid
     * @return
     */
    Long getCoopEntIsVisiblePersonCount(@Param("entId")String entId, @Param("orgGuid")String orgGuid);

    /**
     * 获取企业联系人列表
     * @param entId
     * @param orgGuid
     * @return
     */
    List<EnterpriseCooperatePerEntity> getCoopEntIsVisiblePerson(@Param("entId")String entId, @Param("orgGuid")String orgGuid);

    Long getEntPerNumByOrgGuid(@Param("entId")String entId, @Param("orgGuid")String orgGuid, @Param("all")Boolean all);

    List<EnterpriseCooperatePerEntity> getEntPerByOrgGuid(@Param("entId")String entId, @Param("orgGuid")String orgGuid, @Param("all")Boolean all);

    /**
     * 司机端查组织外协车数量
     */
    Long getFriendDriverNum(@Param("entId") String entId,@Param("orgGuid") String orgGuid);

    /**
     * 获取我的企业列表
     * @param personId
     * @return
     */
    List<EnterpriseCooperatePerEntity> getSelfEntByPersonId(@Param("personId")String personId);

    /**
     * 获取我的合作企业列表
     * @param personId
     * @return
     */
    List<EnterpriseCooperatePerEntity> getCoopEntByPersonId(@Param("personId")String personId);

    /**
     * 根据企业Id和用户id获取合作企业的关系（inviteStatus = 1）
     * @param entId
     * @param personId
     * @return
     */
    EnterpriseCooperatePerEntity getCoopEntPassedByEntIdAndPersonId(@Param("entId")String entId, @Param("personId")String personId);

    /**
     * 根据企业Id和用户id获取所属企业的关系（inviteStatus = 1）
     * @param entId
     * @param personId
     * @return
     */
    EnterpriseCooperatePerEntity getSelfEntPassedByEntIdAndPersonId(@Param("entId")String entId, @Param("personId")String personId);

    /**
     * 根据企业Id和用户id获取合作企业的关系
     * @param entId
     * @param personId
     * @return
     */
    EnterpriseCooperatePerEntity getCoopEntByEntIdAndPersonId(@Param("entId")String entId, @Param("personId")String personId);

    /**
     * 获取企业管理员
     * @param entId
     * @return
     */
    List<EnterpriseCooperatePerEntity> getEntAdministrators(@Param("entId")String entId);

    /**
     * 获取两人共同的企业
     * @param baseUserId
     * @param personId
     * @return
     */
    List<String> getCommonEntList(@Param("baseUserId") String baseUserId,@Param("personId") String personId);
    
    void setCoopPerToNullByCarId(@Param("entId") String entId,@Param("carId") String carId);
    
    void setDriverCar(@Param("entId") String entId,@Param("carId") String carId,@Param("plateNumber") String plateNumber,@Param("personId") String personId);

}