package com.logibeat.cloud.services;


import com.logibeat.cloud.vo.FriendDriverInfoVo;
import com.logibeat.cloud.common.vo.FriendDrvierDetailVo;
import com.logibeat.cloud.common.vo.NewFriendSearchInfoVo;
import com.logibeat.cloud.core.dto.SearchDriverDTO;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperatePerEntity;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;

/**
 * 
* @ClassName: CoopPerService 
* @Description: 企业与个人关系类 
* @author sean 
* @date 2016年2月22日 上午11:05:28 
* @version 1.0
 */
public interface CoopPerService {
    /**
     * 
    * @Title: getDriverInfo 
    * @Description: 根据手机号获取司机信息
    * @param phoneMobile
    * @return  
    * @return NewFriendSearchInfoVo
     */
    NewFriendSearchInfoVo getDriverInfo(String phoneMobile, String userId);
    
    /**
     * 
    * @Title: sendReg 
    * @Description: 发送注册邀请
    * @param phoneMobile
    * @param userId
    * @return  
    * @return NewFriendSearchInfoVo
     */
    NewFriendSearchInfoVo sendReg(String phoneMobile, String userId, String baseEntId) throws Exception;

    /**
     *
    * @Title: coopDriverInfo
    * @Description: 司机详情
    * @param coopId
    * @return
    * @return FriendDrvierDetailVo
     */
    FriendDrvierDetailVo coopDriverInfo(String coopId, String coopDriverInfo, String appKey);

    /**
     * 获取企业自有司机
     * @param
     * @return List<DriverShortInfoVo>
     */
    PageResultDTO<FriendDriverInfoVo> getSelfDrivers(SearchDriverDTO searchDriverDTO);

    FriendDriverInfoVo fillFriendInfoByCoopPer(String baseUserId, EnterpriseCooperatePerEntity enterpriseCooperatePer, String appKey);

    EnterpriseCooperatePerEntity getRolePersonType(String baseUserId, String userParam);

    EnterpriseCooperatePerEntity getCoopDriver(String entId, String personId);

    FriendDriverInfoVo fillFriendListByCoopPer(String entId,
                                                      EnterpriseCooperatePerEntity enterpriseCooperatePer, String appKey);

    FriendDriverInfoVo fillDriverFirendByCoopPer(String entId, EnterpriseCooperatePerEntity entPer);
}

