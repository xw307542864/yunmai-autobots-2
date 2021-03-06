package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.InviteState;
import com.logibeat.cloud.common.enumtype.InviteType;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.NewFriendDto;
import com.logibeat.cloud.errorenum.CoopEntEnums;
import com.logibeat.cloud.errorenum.FriendErrorEnums;
import com.logibeat.cloud.errorenum.UserErrorEnums;
import com.logibeat.cloud.helper.GrimlockServiceHelper;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.redis.CarListTac;
import com.logibeat.cloud.redis.CoopcarListTac;
import com.logibeat.cloud.remote.OperationTimeSender;
import com.logibeat.cloud.services.NewFriendService;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.vo.CarShortInfoVo;
import com.logibeat.cloud.vo.NewFriendDriverInfoVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewFriendServiceImpl implements NewFriendService {

	private static final Logger logger = LoggerFactory.getLogger(NewFriendServiceImpl.class);

	@Autowired
	protected NewFriendLogDao newFriendDao;

	@Autowired
	protected NewFriendLogDetailDao newFriendLogDetailDao;

	@Autowired
	protected NewFriendLogDao newFriendLogDao;

	@Autowired
	protected EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	protected EnterpriseCooperatePerDao enterpriseCooperatePerDao;

	@Autowired
	protected EnterpriseCooperateCarDao enterpriseCooperateCarDao;


	@Autowired
	protected SyspersonDao syspersonDao;

	@Autowired
	protected DictDao dictDao;

	@Autowired
	protected FriendsDao friendsDao;

	@Autowired
	private GrimlockServiceHelper grimlockServiceHelper;

	@Autowired
	private CarsDao carsDao;

	@Autowired
	private ImUserDao imUserDao;

    @Autowired
	private CoopcarListTac coopcarListTac;

    @Autowired
	private CarListTac carListTac;

    @Autowired
	private OperationTimeSender operationTimeSender;

	/**
	 * ?????????????????????
	 */
	@Override
	public List<NewFriendSearchInfoVo> searchNewFriend(String keyWord, String personId) {
		List<NewFriendSearchInfoVo> newFriendSearchInfoVos = new ArrayList<NewFriendSearchInfoVo>();
		List<SystemPersonEntity> systemPersonEntities = syspersonDao.getSearchList(keyWord, personId);
		for (SystemPersonEntity systemPersonEntity : systemPersonEntities) {
			NewFriendSearchInfoVo newFriendSearchInfoVo = new NewFriendSearchInfoVo();
			newFriendSearchInfoVo.setHDpic(systemPersonEntity.gethDpic());
			newFriendSearchInfoVo.setMyName(systemPersonEntity.getNiChen());
			newFriendSearchInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
			newFriendSearchInfoVo.setPersonID(systemPersonEntity.getPersonID());
			newFriendSearchInfoVos.add(newFriendSearchInfoVo);
		}
		return newFriendSearchInfoVos;
	}

	/**
	 * 
	 * ??????: getNewFriendCount ??????: ????????????????????????
	 * 
	 * @return
	 */
	@Override
	public long getNewFriendCount(String baseUserId) {
		return newFriendLogDao.getNewCount(baseUserId);
	}

	/**
	 * ??????????????????
	 */
	@Override
	public List<NewFriendInfoVo> getMyNewFriend(String baseUserId) {
		TValide.notNull(baseUserId, UserErrorEnums.UserErrors.USERID_NULL);
		List<NewFriendInfoVo> resultList = new ArrayList<>();
		List<NewFriendLogEntity> newFriendLogList = newFriendLogDao.getNewFriend(baseUserId);
		for (NewFriendLogEntity newFriendLogEntity : newFriendLogList) {
			NewFriendInfoVo newFriendInfoVo = new NewFriendInfoVo();
			newFriendInfoVo.setAddTime(newFriendLogEntity.getAddTime());
			newFriendInfoVo.setInviteState(newFriendLogEntity.getInviteState());
			newFriendInfoVo.setInviteType(newFriendLogEntity.getInviteType());
			newFriendInfoVo.setNewFriendDescription(InviteType.class.getEnumConstants()[newFriendLogEntity.getInviteType().intValue()].getMessage());
			newFriendInfoVo.setFriendObjectID(newFriendLogEntity.getFriendObjectID());
			newFriendInfoVo.setIsFriendEnt(TypeCastUtil.byteToBoolean(newFriendLogEntity.getIsFriendEnt()));
			newFriendInfoVo.setMessage(newFriendLogEntity.getRemark());
			newFriendInfoVo.setNewFriendGUID(newFriendLogEntity.getNewFriendGUID());
			newFriendInfoVo.setObjectID(newFriendLogEntity.getObjectID());
			if(InviteType.EntToCar.getValue() == newFriendLogEntity.getInviteType()){
				newFriendInfoVo.setCarId(newFriendLogEntity.getRemark());
			}
			
			String carId = newFriendLogEntity.getRemark();
			String entId = newFriendLogEntity.getFriendObjectID();
			if(StringUtils.isNotBlank(carId) && StringUtils.isNotBlank(entId)){
				EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getCooperCarByEntIdAndCarId(entId, carId);
				if(null != entCar){
					String plateNumber = entCar.getPlateNumber();
					String descrip = InviteType.class.getEnumConstants()[newFriendLogEntity.getInviteType().intValue()].getMessage();
					descrip = descrip.substring(0,3)+"("+plateNumber+")"+descrip.substring(3);
					newFriendInfoVo.setNewFriendDescription(descrip);
				}
			}
			
			// ???????????????
			if (newFriendInfoVo.getIsFriendEnt()) {
				EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(newFriendInfoVo.getFriendObjectID());
				newFriendInfoVo.setLogo(enterpriseInfoEntity.getLogo());
				newFriendInfoVo.setName(enterpriseInfoEntity.getName());
			}
			// ???????????????
			else {
				SystemPersonEntity systemPersonEntity = syspersonDao.select(newFriendInfoVo.getFriendObjectID());
				if (null != systemPersonEntity) {
					newFriendInfoVo.setLogo(systemPersonEntity.gethDpic());
					if (StringUtils.isNotBlank(systemPersonEntity.getNiChen())) {
						newFriendInfoVo.setName(systemPersonEntity.getNiChen());
					} else {
						if (StringUtils.isNotBlank(systemPersonEntity.getNiChen())) {
							newFriendInfoVo.setName(systemPersonEntity.getNiChen());
						} else {
							newFriendInfoVo.setName(systemPersonEntity.getLoginName());
						}
					}
					newFriendInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
				}
				// ??????
				CarShortInfoVo carShortInfoVo = this.getMySelfCar(newFriendInfoVo.getObjectID());
				if (null != carShortInfoVo) {
					newFriendInfoVo.setCarID(carShortInfoVo.getCarId());
					newFriendInfoVo.setPlateNumber(carShortInfoVo.getPlateNumber());
				}
			}
			resultList.add(newFriendInfoVo);
		}
		return resultList;
	}

	/**
	 * ??????????????????
	 */
	@Override
	public void handleNewFriend(NewFriendDto newFriendDto, BaseRequestDTO baseRequestDTO) {
		String personId = newFriendDto.getBaseUserId();
		Integer inviteState = newFriendDto.getInviteState();
		NewFriendLogEntity newFriendLog = newFriendLogDao.select(newFriendDto.getNewFriendGUID());
		if(null == newFriendLog){
			throw  new ServiceException(FriendErrorEnums.FRIENT_NULL);
		}
		//?????????????????????
		if (newFriendLog.getInviteType() == InviteType.EntToCar.getValue()) {
		    EnterpriseCooperateCarEntity entCar = 
		    		enterpriseCooperateCarDao.getCooperCarByEntIdAndCarId(newFriendLog.getFriendObjectID(), newFriendLog.getRemark());
		    // ??????
			if (inviteState == InviteState.Pass.getValue()) {
				if(null == entCar){
					throw  new ServiceException(FriendErrorEnums.FRIENT_NULL);
				}
				if(entCar.getInviteState().equals(InviteState.Pass.getValue())){
					throw new ServiceException(CoopEntEnums.CoopEntErrors.REPEATE_ADD_FAIL);
				}
				//?????????????????????????????????
				entCar.setInviteState(InviteState.Pass.getValue());
				enterpriseCooperateCarDao.update(entCar);

				//????????????????????????
				newFriendLog.setInviteState(InviteState.Pass.getValue());
				newFriendLog.setIsHandle(ConstantUtil.BYTE_TRUE);
				newFriendLog.setHandlePersonID(personId);
				newFriendLogDao.update(newFriendLog);
			}
		}
	}


	/**
	 * ????????????????????????
	 */
	@Override
	public Object getStrangerInfo(String guid) {
		NewFriendLogEntity newFriendLogEntity = newFriendLogDao.select(guid);
		TValide.notNull(newFriendLogEntity, FriendErrorEnums.FRIENT_NULL);
		Object object = null;
		// ??????
		if (newFriendLogEntity.getIsFriendEnt().equals(ConstantUtil.BYTE_TRUE)) {
			NewFriendEntInfoVo newFriendEntInfoVo = new NewFriendEntInfoVo();
			// ??????????????????
			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(newFriendLogEntity.getFriendObjectID());
			if (null != enterpriseInfoEntity) {
				// ???????????????
				SystemPersonEntity ownSystemPersonEntity = syspersonDao.select(enterpriseInfoEntity.getOwerPersonID());
				//??????im??????
				ImUserEntity imUserEntity = imUserDao.getByPersonId(ownSystemPersonEntity.getPersonID());
				newFriendEntInfoVo.setAddress(enterpriseInfoEntity.getAddress());
				newFriendEntInfoVo.setCode(enterpriseInfoEntity.getCode());
				newFriendEntInfoVo.setEntAuditStatus(enterpriseInfoEntity.getAuditStatus());
				newFriendEntInfoVo.setEntTypeDictName(dictDao.getDictNameByGuid(enterpriseInfoEntity.getDictGUID()));
				newFriendEntInfoVo.setInviteState(newFriendLogEntity.getInviteState());
				newFriendEntInfoVo.setInviteType(newFriendLogEntity.getInviteType());
				newFriendEntInfoVo.setLogo(enterpriseInfoEntity.getLogo());
				newFriendEntInfoVo.setMessage(newFriendLogEntity.getRemark());
				newFriendEntInfoVo.setName(enterpriseInfoEntity.getName());
				newFriendEntInfoVo.setProfile(enterpriseInfoEntity.getProfile());
				newFriendEntInfoVo.setNewFriendGUID(guid);
				newFriendEntInfoVo.setMobile(ownSystemPersonEntity.getMobilePhoneNumber());
				//newFriendEntInfoVo.setNiChen(ownSystemPersonEntity.getNiChen());
				newFriendEntInfoVo.setPersonID(ownSystemPersonEntity.getPersonID());
				newFriendEntInfoVo.setLogitalkId(ownSystemPersonEntity.getLogitalkId());
				//??????????????????????????????
				String entId=newFriendLogEntity.getFriendObjectID();
				EnterpriseCooperatePerEntity enterpriseCooperatePer = enterpriseCooperatePerDao.getEntContact(entId);
			    if(enterpriseCooperatePer != null) {
			        newFriendEntInfoVo.setMobile(enterpriseCooperatePer.getPhoneNumber());
			        newFriendEntInfoVo.setNiChen(enterpriseCooperatePer.getNameRemark());
			    } 
			    else {
			        EnterpriseCooperatePerEntity entCoopPer = enterpriseCooperatePerDao.getEntOwnerPerson(entId,null);
			        if (entCoopPer != null) {
			            newFriendEntInfoVo.setMobile(entCoopPer.getPhoneNumber());
			            newFriendEntInfoVo.setNiChen(entCoopPer.getNameRemark());
			        }
			    }
				
				if(null != imUserEntity){
					newFriendEntInfoVo.setDisplayName(imUserEntity.getName());
				}else{
					newFriendEntInfoVo.setDisplayName("?????????");
				}
				// ????????????????????????
				EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(
						enterpriseInfoEntity.getId(), newFriendLogEntity.getObjectID());
				if (null != entPer && entPer.getInviteState() == InviteState.Pass.getValue()) {
					newFriendEntInfoVo.setIsFriend(ConstantUtil.TRUE);
					newFriendEntInfoVo.setIsShareGps(TypeCastUtil.byteToBoolean(entPer.getIsShareGps()));
				} else {
					newFriendEntInfoVo.setIsFriend(ConstantUtil.FALSE);
				}
			}
			object = newFriendEntInfoVo;
		}
		// ??????
		else {
			NewFriendDriverInfoVo newFriendDriverInfoVo = new NewFriendDriverInfoVo();
			// ????????????????????????
			SystemPersonEntity systemPersonEntity = syspersonDao.select(newFriendLogEntity.getInvitePersonID());
			//??????im??????
			ImUserEntity imUserEntity = imUserDao.getByPersonId(systemPersonEntity.getPersonID());
			// ??????
			CarShortInfoVo carShortInfoVo = this.getMySelfCar(newFriendLogEntity.getInvitePersonID());
			if (null != carShortInfoVo) {
				newFriendDriverInfoVo.setCar(carShortInfoVo);
			}
			// ???????????????
			FriendsEntity friendsEntity = friendsDao.getMyFriendByPersonId(newFriendLogEntity.getInvitePersonID(),
					newFriendLogEntity.getObjectID());
			// ??????????????????
			if (null == friendsEntity) {
				newFriendDriverInfoVo.setIsFriend(ConstantUtil.FALSE);
			} else {
				newFriendDriverInfoVo.setIsFriend(ConstantUtil.TRUE);
			}
			newFriendDriverInfoVo.setDriverAuditStatus(systemPersonEntity.getDriverAuditStatus());
			newFriendDriverInfoVo.setDrivingRange(systemPersonEntity.getDrivingRange());
			newFriendDriverInfoVo.setHDpic(systemPersonEntity.gethDpic());
			newFriendDriverInfoVo.setLogitalkId(systemPersonEntity.getLogitalkId());
			newFriendDriverInfoVo.setDisplayName(imUserEntity.getName());
			newFriendDriverInfoVo.setInviteState(newFriendLogEntity.getInviteState());
			newFriendDriverInfoVo.setInviteType(newFriendLogEntity.getInviteType());
			newFriendDriverInfoVo.setMessage(newFriendLogEntity.getRemark());
			newFriendDriverInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
			newFriendDriverInfoVo.setNewFriendGUID(newFriendLogEntity.getNewFriendGUID());
			newFriendDriverInfoVo.setNiChen(systemPersonEntity.getNiChen());
			newFriendDriverInfoVo.setPersonID(systemPersonEntity.getPersonID());
			newFriendDriverInfoVo.setStar(ConstantUtil.DOUBLE_CODE_FIVE);
			newFriendDriverInfoVo.setEconomic(ConstantUtil.DOUBLE_CODE_FIVE);
			newFriendDriverInfoVo.setAging(ConstantUtil.DOUBLE_CODE_FIVE);
			newFriendDriverInfoVo.setSecurity(ConstantUtil.DOUBLE_CODE_FIVE);
			object = newFriendDriverInfoVo;
		}
		return object;
	}

	private CarShortInfoVo getMySelfCar(String personId) {
		CarShortInfoVo carShortInfoVo = null;
		EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.getDriverShamEnt(personId);
		String entId = enterpriseInfoEntity.getId();
		if (null != enterpriseInfoEntity) {
			EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getSelfCarByEntIdAndPersonId(entId,personId);
			if (null != entCar) {
				carShortInfoVo = new CarShortInfoVo();
				CarsEntity carsEntity = carsDao.select(entCar.getCarid());
				if(carsEntity.getIsDelete() == 1){
					return carShortInfoVo;
				}
				carShortInfoVo.setCarId(carsEntity.getCarID());
				carShortInfoVo.setCarLengthDictGuid(carsEntity.getCarLengthDictGUID());
				carShortInfoVo.setCarCoachDictGuid(carsEntity.getCoachTypeDictGUID());
				carShortInfoVo.setLogo(carsEntity.getLogo());
				carShortInfoVo.setPlateNumber(carsEntity.getPlateNumber());
				carShortInfoVo.setRatedLoad(carsEntity.getRatedLoad());
				carShortInfoVo.setRatedVolume(carsEntity.getRatedVolume());
				carShortInfoVo.setAuditStatus(carsEntity.getCarAuditStatus());
				carShortInfoVo.setEntId(enterpriseInfoEntity.getId());
				carShortInfoVo.setCarLogo(carsEntity.getLogo());
				if(carsEntity.getCarLengthDictGUID() != null && StringUtils.isNotBlank(carsEntity.getCarLengthDictGUID())){
					DictEntity carLength = dictDao.select(carsEntity.getCarLengthDictGUID());
					if (null != carLength) {
						carShortInfoVo.setCarLength(carLength.getName());
					}
				}
				if(carsEntity.getCoachTypeDictGUID() != null && StringUtils.isNotBlank(carsEntity.getCoachTypeDictGUID())){
					DictEntity carCoachType = dictDao.select(carsEntity.getCoachTypeDictGUID());
					if (null != carCoachType) {
						carShortInfoVo.setCarCoachType(carCoachType.getName());
					}
				}
			}
		}
		return carShortInfoVo;
	}

    public void deleteNewFriendLog(String newFriendGuid){
	    newFriendLogDao.delete(newFriendGuid);
    }


	// ??????????????????????????????
	private void addFriend(NewFriendLogEntity newFriendLogEntity, NewFriendDto newFriendDto, BaseRequestDTO baseRequestDTO) {
		String personId = baseRequestDTO.getBaseUserId();
		SystemPersonEntity systemPersonEntity = syspersonDao.select(personId);
		TValide.notNull(systemPersonEntity, FriendErrorEnums.HAS_NO_PERSON);
		EnterpriseCooperateCarEntity entCar = null; // ?????????????????????
		// ???????????? ???????????????????????????
		if (newFriendLogEntity.getIsFriendEnt().equals(ConstantUtil.BYTE_TRUE)) {
			// ?????????????????????????????????
			EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao
					.getCoopByEntIdAndPersonId(newFriendLogEntity.getFriendObjectID(), personId);
			if (null != entPer) {
				entPer.setIsDelete(ConstantUtil.BYTE_FALSE);
				entPer.setInviteState(InviteState.Pass.getValue());
				entPer.setEditTime(DateUtil.getSqlTime());
				entPer.setEditPersonID(personId);
				if (newFriendLogEntity.getInviteType() == InviteType.EntToCar.getValue()) {
					if(org.apache.commons.lang3.StringUtils.isBlank(entPer.getCarid())){
						entPer.setCarid(newFriendDto.getCarId());
						entPer.setPlateNumber(newFriendDto.getPlateNumber());
					}
				}
				enterpriseCooperatePerDao.update(entPer);
			}

			// ?????????????????????
			entCar = enterpriseCooperateCarDao.getCarsByEntIdAndPersonId(newFriendLogEntity.getFriendObjectID(),newFriendLogEntity.getObjectID());

			// ????????????????????????????????????????????????????????????????????????
			if (newFriendLogEntity.getInviteType() == InviteType.EntToCar.getValue()) {
				if (entCar != null) {
					if (StringUtils.isBlank(entCar.getCarid())) {
						entCar.setCarid(newFriendDto.getCarId());
						entCar.setPlateNumber(newFriendDto.getPlateNumber());
					}
					entCar.setInviteState(InviteState.Pass.getValue());
					entCar.setEdittime(DateUtil.getSqlTime());
					entCar.setIsDelete(ConstantUtil.BYTE_FALSE);
					enterpriseCooperateCarDao.update(entCar);

				}

			}



		}
	}




}
