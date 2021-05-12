package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.vo.CarShortInfoVo;
import com.logibeat.cloud.common.vo.DriverEntVo;
import com.logibeat.cloud.vo.FriendDetailVo;
import com.logibeat.cloud.vo.FriendInfoVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.services.FriendService;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sean
 * @version 1.0
 * @ClassName: FriendServiceImpl
 * @Description: 联系人实现类
 * @date 2015年12月8日 上午9:57:36
 */
@Service
public class FriendServiceImpl implements FriendService {

	private static final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

	@Autowired
	protected SyspersonDao syspersonDao;

	@Autowired
	protected ImUserDao imUserDao;

	@Autowired
	protected EnterpriseCooperatePerDao enterpriseCooperatePerDao;

	@Autowired
	protected EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	protected EnterpriseOrganizationDictDao enterpriseOrganizationDictDao;

	@Autowired
	protected EnterpriseCooperateCarDao enterpriseCooperateCarDao;

	@Autowired
	protected CarsDao carsDao;

	@Autowired
	protected DictDao dictDao;

	@Autowired
	protected ImFriendRelationDao imFriendRelationDao;

	@Autowired
	protected EnterpriseOrganizationDictDao entOrganizationDictDao;

	/**
	 * 司机好友详细信息
	 */
	@Override
	public FriendDetailVo getFriendDetail(String flag, String personId, String mobile, String baseUserId) {
		FriendDetailVo friendDetail = new FriendDetailVo();
		SystemPersonEntity systemPersonEntity;
		if (org.apache.commons.lang.StringUtils.isNotBlank(personId)) {
			systemPersonEntity = syspersonDao.select(personId);
		} else {
			systemPersonEntity = syspersonDao.getEntityByMobile(mobile);
		}
		// 获取im信息
		ImUserEntity imUserEntity = imUserDao.getByPersonId(systemPersonEntity.getPersonID());
		// 平台不存在（未注册）
		if (null == systemPersonEntity) {
			friendDetail.setIsReg(ConstantUtil.FALSE);
			friendDetail.setMobile(mobile);
			friendDetail.setSendRegist(ConstantUtil.TRUE); // 邀请注册
		}
		// 平台已存在（已注册）
		else {
			// 当前用户与好友所属相同的企业id列表
			//List<DriverEntVo> entList = enterpriseCooperatePerDao.getCommonEntList(baseUserId, systemPersonEntity.getPersonID());
			List<String> entIdList = enterpriseCooperatePerDao.getCommonEntList(baseUserId, systemPersonEntity.getPersonID());
			if (null != entIdList && !entIdList.isEmpty()) {
				List<DriverEntVo> driverEntVoList = new ArrayList<>();
				for (String entId : entIdList){
					DriverEntVo driverEntVo = new DriverEntVo();

                    EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
					if(enterpriseInfoEntity != null){
                        driverEntVo.setEntName(enterpriseInfoEntity.getName());
                    }

                    EnterpriseCooperatePerEntity enterpriseCooperatePerEntity = enterpriseCooperatePerDao
                            .getSelfEntPassedByEntIdAndPersonId(entId, personId);
					if(enterpriseCooperatePerEntity != null){
                        driverEntVo.setNameRemark(enterpriseCooperatePerEntity.getNameRemark());

                        String orgName = "";
                        String orgGuid = enterpriseCooperatePerEntity.getOrgGuid();
                        if(StringUtils.isNotBlank(orgGuid) && !ConstantUtil.String_CODE_ZERO.equals(orgGuid)){
                            String[] orgGuids = orgGuid.split(",");
                            for (int i = 0; i < orgGuids.length; i++) {
                                EnterpriseOrganizationDictEntity orgEntity = enterpriseOrganizationDictDao.select(orgGuids[i]);
                                if(orgEntity != null){
                                    orgName += orgEntity.getName();
                                    if(i != orgGuids.length-1){
                                        orgName += ",";
                                    }
                                }
                            }

                            driverEntVo.setOrgName(orgName);
                        }

                    }

                    EnterpriseCooperateCarEntity entCarEntity = enterpriseCooperateCarDao
                            .getSelfCarByEntIdAndPersonId(entId, personId);
					if(entCarEntity != null){
                        driverEntVo.setIsFirstDriver(personId.equals(entCarEntity.getFirstDriverPersonID()));

                        CarsEntity carsEntity = carsDao.select(entCarEntity.getCarid());
                        if(carsEntity != carsEntity){
                            driverEntVo.setPlateNumber(carsEntity.getPlateNumber());
                            driverEntVo.setCarLength(dictDao.getDictNameByGuid(carsEntity.getCarLengthDictGUID()));
                            driverEntVo.setCarCoachType(dictDao.getDictNameByGuid(carsEntity.getCoachTypeDictGUID()));
                            driverEntVo.setRatedLoad(carsEntity.getRatedLoad());
                            driverEntVo.setRatedVolume(carsEntity.getRatedVolume());
                        }
                    }

				}
				friendDetail.setEntList(driverEntVoList);
			}
			
			// 好友车辆信息
			CarsEntity carsEntity = carsDao
					.getMySelfCar(systemPersonEntity.getPersonID());
			if(carsEntity != null){
                CarShortInfoVo carShortInfoVo = new CarShortInfoVo();
                carShortInfoVo.setPlateNumber(carsEntity.getPlateNumber().substring(0, 2) + "******");
                carShortInfoVo.setCarLength(dictDao.getDictNameByGuid(carsEntity.getCarLengthDictGUID()));
                carShortInfoVo.setCarCoachType(dictDao.getDictNameByGuid(carsEntity.getCoachTypeDictGUID()));
                carShortInfoVo.setRatedLoad(carsEntity.getRatedLoad());
                carShortInfoVo.setRatedVolume(carsEntity.getRatedVolume());

                friendDetail.setCar(carShortInfoVo);
            }

			// 基本信息
			friendDetail.setMobile(systemPersonEntity.getMobilePhoneNumber());
			friendDetail.setIsReg(ConstantUtil.TRUE);
			friendDetail.setPersonID(systemPersonEntity.getPersonID());
			friendDetail.setNiChen(systemPersonEntity.getNiChen());
			friendDetail.setLogo(systemPersonEntity.gethDpic());
			friendDetail
					.setDriverAuditStatus(systemPersonEntity.getDriverAuditStatus() == null ? 0 : systemPersonEntity.getDriverAuditStatus());
			friendDetail.setChildAdminType(systemPersonEntity.getChildAdminType());
			friendDetail.setLogitalkId(systemPersonEntity.getLogitalkId());
			if (imUserEntity != null) {
				friendDetail.setDisplayName(imUserEntity.getName());
			} else {
				friendDetail.setDisplayName("某某某");
			}

//			// 当前用户与好友所属相同的企业列表
//			List<DriverEntVo> entList = coopEntService.getCommonEntList(baseUserId, systemPersonEntity.getPersonId());
//			friendDetail.setEntList(entList);

		}
		friendDetail.setSendMessage(ConstantUtil.TRUE);
		// 判断和司机是否是好友关系
		ImFriendRelationEntity imFriend = imFriendRelationDao.getFriendByPersonId(baseUserId,
				personId);
		if(imFriend != null){
			if(imFriend.getStatus() == 20){
				friendDetail.setIsImFriend(true);
			}
		}
		return friendDetail;
	}


    @Override
    public FriendInfoVo getFriendInfo(String friendId, String entId, String personId, String imGuid) {
        FriendInfoVo friendInfoVo = new FriendInfoVo();
        EnterpriseCooperatePerEntity per = null;
        Boolean isShowOrgName = false;
        String orgName = "";
        String orgGuid = "";
        if(entId != null){
            per = enterpriseCooperatePerDao.getEntOwnerPerson(entId, friendId);
            if(per != null){
                isShowOrgName = true;
                orgGuid = per.getOrgGuid();
                String[] orgGuids = orgGuid.split(",");
                List<String> orgList = Arrays.asList(orgGuids);
                if(!"0".equals(orgGuid)){
                    for(int i=0;i<orgList.size()-1;i++){
                        String org = orgList.get(i);
                        EnterpriseOrganizationDictEntity orgEntity = entOrganizationDictDao.select(org);
                        String name = orgEntity.getName();
                        orgName += name + ",";
                    }
                    EnterpriseOrganizationDictEntity orgEntity = entOrganizationDictDao.select(orgList.get(orgList.size()-1));
                    String name = orgEntity.getName();
                    orgName += name;
                }
            }
        }
        friendInfoVo.setOrgGuid(orgGuid);
        friendInfoVo.setOrgName(orgName);
        friendInfoVo.setIsShowOrgName(isShowOrgName);

        SystemPersonEntity systemPersonEntity = syspersonDao.getEntityByImId(imGuid);
        if (null != systemPersonEntity) {
            // 获取im信息
            ImUserEntity imUserEntity = imUserDao.getByPersonId(systemPersonEntity.getPersonID());
            //查询是否是好友
            // 判断和司机是否是好友关系
            ImFriendRelationEntity imFriend = imFriendRelationDao.getFriendByPersonId(personId,
                    systemPersonEntity.getPersonID());

            if(imFriend != null){
                if(imFriend.getStatus() == 20){
                    friendInfoVo.setIsImFriend(true);
                }
            }
            friendInfoVo.setPersonID(systemPersonEntity.getPersonID());
            friendInfoVo.setNiChen(systemPersonEntity.getNiChen());
            friendInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
            friendInfoVo.setHDpic(systemPersonEntity.gethDpic());
            friendInfoVo.setImGUID(imGuid);
            friendInfoVo.setIsDriver(TypeCastUtil.byteToBoolean(systemPersonEntity.getIsDriver()));
            friendInfoVo.setLogitalkId(systemPersonEntity.getLogitalkId());
            if (null != imUserEntity) {
                friendInfoVo.setDisplayName(imUserEntity.getName());
            } else {
                friendInfoVo.setDisplayName("某某某");
            }
        }
        return friendInfoVo;
    }






}
