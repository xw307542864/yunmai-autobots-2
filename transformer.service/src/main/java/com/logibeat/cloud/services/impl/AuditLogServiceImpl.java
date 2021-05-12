package com.logibeat.cloud.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.common.cache.util.KeyWordUtil;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.SettingConstantUtil;
import com.logibeat.cloud.core.dto.*;
import com.logibeat.cloud.core.tools.StarsoftHttpUtil;
import com.logibeat.cloud.dto.AuditDto;
import com.logibeat.cloud.errorenum.AuditLogErrorEnums;
import com.logibeat.cloud.errorenum.CarErrorEnums;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.msg.constant.MessageConstant;
import com.logibeat.cloud.msg.dto.ImModeDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.AuditExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.remote.CarSender;
import com.logibeat.cloud.services.AuditLogService;
import com.logibeat.cloud.services.VehicleService;
import com.logibeat.cloud.util.tools.DateUtils;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.vo.CarShortInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logibeat.jetfire.client.template.SmsTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证日志操作
 *
 * @author Wilson
 * @version V1.0
 * @Title: AuditLogService.java
 * @Package com.yunmaigo.qtz.driver.serviceimpl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2016年1月14日 下午3:47:47
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	private AuditlogDao auditlogDao;

	@Autowired
	private DictDao dictDao;

	@Autowired
	protected SyspersonDao personDao;

	@Autowired
	private AuditDao auditDao;

	@Autowired
	private UserAuditResultDao userAuditResultDao;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private ImMsgSender imMsgSender;

	@Autowired
	private JetfireMsgSender jetfireMsgSender;

	@Autowired
	private ImUserDao imUserDao;

	@Autowired
	private CarSender carSender;

	@Autowired
	private MyVehicleDao myVehicleDao;

	@Autowired
	private CarsDao carsDao;

	@Autowired
	private EnterpriseCooperateCarDao enterpriseCooperateCarDao;

	@Autowired
	private SysSettingDao sysSettingDao;

	@Autowired
	private ApprovalDao approvalDao;

	@Autowired
	private EnterpriseCooperatePerDao enterpriseCooperatePerDao;


	@Override
	public AuditStatusVo getAuditStatus(String entId) {
		AuditLogEntity entAuditLogEntity = auditlogDao.getEnterPriseAuditLog(entId, AuditEventType.EnterpriseVerification.getValue());
		AuditLogEntity qualityAuditLogEntity = auditlogDao.getEnterPriseAuditLog(entId, AuditEventType.QualityVerification.getValue());
		List<AuditTypeVo> auditTypeList = new ArrayList<>();
		//企业认证信息
		EnterpriseInfoEntity enterEntity = enterpriseInfoDao.select(entId);
		if(enterEntity == null){
			throw new ServiceException(EnterpriseErrorEnums.MESSAGE_ENT_NOT_EXIST);
		}
		Integer entAuditStatus = enterEntity.getAuditStatus();
		Integer qualityAuditStatus = enterEntity.getQualityAuditStatus();
		if(entAuditStatus == null){
			entAuditStatus = 4;
		}
		if(qualityAuditStatus == null){
			qualityAuditStatus = 4;
		}
		//获得认证企业类型（货主/物流）
		Integer entLargeType = getEntLargType(enterEntity);

		if(entAuditLogEntity != null){
			AuditTypeVo auditTypeVo = new AuditTypeVo();
			auditTypeVo.setAuditEventType(entAuditLogEntity.getAuditEventType());
			auditTypeVo.setAuditStatus(entAuditStatus);
			if(ConstantUtil.INTEGER_CODE_THREE.equals(entAuditLogEntity.getAuditStatus())){
				auditTypeVo.setFailMessage(entAuditLogEntity.getFailMessage());
			}
			auditTypeList.add(auditTypeVo);
		}else{
			AuditTypeVo auditTypeVo = new AuditTypeVo();
			auditTypeVo.setAuditEventType(AuditEventType.EnterpriseVerification.getValue());
			auditTypeVo.setAuditStatus(entAuditStatus);
			auditTypeList.add(auditTypeVo);
		}



		//资质认证信息
		if(ConstantUtil.INTEGER_CODE_TWO.equals(entLargeType)){
			if(qualityAuditLogEntity != null){
				AuditTypeVo auditTypeVo = new AuditTypeVo();
				auditTypeVo.setAuditEventType(qualityAuditLogEntity.getAuditEventType());
				auditTypeVo.setAuditStatus(qualityAuditStatus);
				if(ConstantUtil.INTEGER_CODE_THREE.equals(qualityAuditLogEntity.getAuditStatus())){
					auditTypeVo.setFailMessage(qualityAuditLogEntity.getFailMessage());
				}
				auditTypeList.add(auditTypeVo);
			}else{
				AuditTypeVo auditTypeVo = new AuditTypeVo();
				auditTypeVo.setAuditEventType(AuditEventType.QualityVerification.getValue());
				auditTypeVo.setAuditStatus(qualityAuditStatus);
				auditTypeList.add(auditTypeVo);
			}
		}
		//计算认证完成进度条
		Integer auditProgress = 0;
		if(ConstantUtil.INTEGER_CODE_TWO.equals(entLargeType)){
			if(ConstantUtil.INTEGER_CODE_TWO.equals(entAuditStatus) && ConstantUtil.INTEGER_CODE_TWO.equals(qualityAuditStatus)){
				auditProgress = 100;
			}
			else if(ConstantUtil.INTEGER_CODE_TWO.equals(entAuditStatus) && !ConstantUtil.INTEGER_CODE_TWO.equals(qualityAuditStatus)){
				auditProgress = 80;
			}
			else if(!ConstantUtil.INTEGER_CODE_TWO.equals(entAuditStatus)){
				auditProgress = 0;
			}
		}else{
			if(ConstantUtil.INTEGER_CODE_TWO.equals(entAuditStatus)){
				auditProgress = 100;
			}else{
				auditProgress = 0;
			}
		}

		AuditStatusVo auditStatusVo = new AuditStatusVo();
		auditStatusVo.setEntId(entId);
		auditStatusVo.setCertificationStatus(entAuditStatus);
		auditStatusVo.setEntLargeType(entLargeType);
//		auditStatusVo.setEntName(entName);
		auditStatusVo.setAuditProgress(auditProgress);
		auditStatusVo.setAuditList(auditTypeList);
		return auditStatusVo;
	}

	//获得认证企业类型
	Integer getEntLargType(EnterpriseInfoEntity enterEntity){
		Integer entLargeType = 2;//默认物流企业1.货主企业，2.物流企业
		if(enterEntity != null){
			DictEntity dictEntity = dictDao.select(enterEntity.getDictGUID());
			if(dictEntity != null){
				if("HUOZHUQIYE".equals(dictEntity.getCode())){
					entLargeType = 1;
				}else{
					entLargeType = 2;
				}
			}
		}
		return entLargeType;

	}


	/**
	 * 驾驶证认证
	 * @param drivingLicenseDto
	 */

	@Override
	public void auditDrivingLicense(DrivingLicenseDto drivingLicenseDto) {
		Gson gson = new Gson();
		AuditDto auditDto = new AuditDto();
		String personId  = drivingLicenseDto.getBaseUserId();
		if(StringUtils.isBlank(personId)){
			throw new ServiceException(AuditLogErrorEnums.AuditLogError.BASEUSERID_IS_NOT_NULL);
		}
		SystemPersonEntity person = personDao.select(personId);
		if(null == person){
			throw new ServiceException(AuditLogErrorEnums.AuditLogError.BASEUSERID_IS_NOT_NULL);
		}
		if(StringUtils.isBlank(drivingLicenseDto.getClientSystem())){
			throw new ServiceException(AuditLogErrorEnums.AuditLogError.CLENT_SYSTEM_IS_NOT_NULL);
		}

		if(ConstantUtil.INTEGER_CODE_ONE.equals(drivingLicenseDto.getException())){
			drivingLicenseDto.setAuditType(ConstantUtil.String_CODE_ONE);
			//判断当前用户是否有认证成功的记录
			Long successCount = auditlogDao.getSuccessCount(personId,AuditEventType.DrivingLicenseVerification.getValue());
			if(successCount>0){
				drivingLicenseDto.setAuditType(ConstantUtil.String_CODE_TWO);
			}
			drivingLicenseDto.setAuditStatus(CertificationStatus.waiting.getValue());
			auditDto.setMessageBizType(MessageBizType.AUDIT_SUBMIT_PUSH);

		}
		//正常情况下才去验证
		else{
			if(StringUtils.isBlank(drivingLicenseDto.getDriverLicensePic())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVER_LICENSE_PIC_IS_NOT_NULL);
			}
			if(StringUtils.isBlank(drivingLicenseDto.getDriverLicenseNumber())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVER_LICENSE_NUMBER_IS_NOT_NULL);
			}
			if(StringUtils.isBlank(drivingLicenseDto.getDriverType())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVER_TYPE_IS_NOT_NULL);
			}
			if(StringUtils.isBlank(drivingLicenseDto.getDriverStartTime())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVER_START_TIME_IS_NOT_NULL);
			}
			if(StringUtils.isBlank(drivingLicenseDto.getDiverEndTime())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVER_END_TIME_IS_NOT_NULL);
			}
			if(StringUtils.isNotBlank(drivingLicenseDto.getDiverEndTime()) ){
				long expireTime = DateUtils.dateStrToDate(drivingLicenseDto.getDiverEndTime(),"yyyy-MM-dd").getTime();
				long currentTime = System.currentTimeMillis();
				long  diff=(expireTime-currentTime)/(1000 * 60 * 60 * 24);
				if(diff<0){
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.IDCARD__EXPIRE);
				}
			}
			auditDto.setMessageBizType(MessageBizType.AUDIT_SUCCESS_PUSH);
			drivingLicenseDto.setAuditStatus(CertificationStatus.verified.getValue());


		}
		//有水印的照片，去水印
		String driverLicensePic = drivingLicenseDto.getDriverLicensePic();
		if(driverLicensePic.contains(ConstantUtil.WATER_MARK_URL)){
			driverLicensePic = driverLicensePic.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		drivingLicenseDto.setDriverLicensePic(driverLicensePic);
		String auditInfo = gson.toJson(drivingLicenseDto);
		auditDto.setObjectId(personId);
		auditDto.setPersonId(personId);
		auditDto.setPhoneNumber(person.getLoginName());
		auditDto.setIdcardNumber(person.getSocialLic());
		auditDto.setPersonName(person.getMyName());
		auditDto.setAuditEventType(AuditEventType.DrivingLicenseVerification.getValue());
		auditDto.setAuditInfo(auditInfo);
		auditDto.setAuditStatus(CertificationStatus.verified.getValue());
		auditDto.setIdentificationNumber(drivingLicenseDto.getDriverLicenseNumber());
		auditDto.setExpireDate(drivingLicenseDto.getDiverEndTime());
		auditDto.setPushUrl(ConstantUtil.driverInfoPage);
		auditDto.setMessageType(MessageType.AUTHEN.getValue());
		if(null != drivingLicenseDto.getAuditStatus()){
			auditDto.setAuditStatus(drivingLicenseDto.getAuditStatus());

			//审核状态是通过 则将所有反馈信息置为已审核
			if(CertificationStatus.verified.getValue().equals(drivingLicenseDto.getAuditStatus())){
				List<UserAuditResultEntity> userAuditResultEntityList = userAuditResultDao.getUserAuditInfo(personId,AuditEventType.DrivingLicenseVerification.getValue());
				if(!userAuditResultEntityList.isEmpty() && userAuditResultEntityList.size()>0){
					for(UserAuditResultEntity userAuditResult : userAuditResultEntityList){
						userAuditResult.setAuditTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
						userAuditResult.setAuditPersonName("系统审核");
						userAuditResult.setAuditStatus(CertificationStatus.failed.getValue());
						userAuditResultDao.update(userAuditResult);
					}
				}


			}
		}
		//认证
		audit(auditDto);
	}


	/**
	 * 从业资格认证
	 * @param auditQualityDto
	 */
	public  void auditQuality(AuditQualityDto auditQualityDto){
		Gson gson = new Gson();
		String personId = auditQualityDto.getPersonId();
		if(null == personId){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.ENITY_IS_NOT_EXIST);
		}
		SystemPersonEntity person = personDao.select(personId);
		if(null == person){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.ENITY_IS_NOT_EXIST);
		}
		if(StringUtils.isBlank(auditQualityDto.getCertificatesTypeCode())){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.CERTIFICATES_TYPE_IS_NOT_NULL);
		}
		if(StringUtils.isBlank(auditQualityDto.getCertificatesPic())){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.CERTIFICATES_PIC_IS_NOT_NULL);
		}
		if(StringUtils.isNotBlank(auditQualityDto.getExpireDate()) ){
			long expireTime = DateUtils.dateStrToDate(auditQualityDto.getExpireDate(),"yyyy-MM-dd").getTime();
			long currentTime = System.currentTimeMillis();
			long  diff=(expireTime-currentTime)/(1000 * 60 * 60 * 24);
			if(diff<0){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.IDCARD__EXPIRE);
			}
		}
		Integer auditEventType = AuditEventType.PersonQualityVerification.getValue();
		String auditEventTypeItem = auditQualityDto.getCertificatesTypeCode();
		String auditId = auditQualityDto.getAuditId();
		//判断当前证件类型是否已经提交了认证申请(其他除外)
		if(!auditEventTypeItem.equals(QualityCertificatesType.QTYSZGZS.getValue())){
			AuditEntity auditEntity = auditDao.getsAuditEnityByItem(personId,auditEventType ,auditEventTypeItem);
			if(null != auditEntity && StringUtils.isBlank(auditId)){
				throw  new ServiceException(AuditLogErrorEnums.AuditLogError.CERTIFICATES_PIC_IS_SUBMIT);

			}
		}
		//有水印的照片，去水印
		String certificatesPic = auditQualityDto.getCertificatesPic();
		if(certificatesPic.contains(ConstantUtil.WATER_MARK_URL)){
			certificatesPic = certificatesPic.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		auditQualityDto.setCertificatesPic(certificatesPic);
		String auditInfo = gson.toJson(auditQualityDto);
		AuditDto auditDto = new AuditDto();
		auditDto.setObjectId(personId);
		auditDto.setPersonId(personId);
		auditDto.setPersonName(person.getMyName());
		auditDto.setPhoneNumber(person.getLoginName());
		auditDto.setIdcardNumber(person.getSocialLic());
		auditDto.setAuditEventType(auditEventType);
		auditDto.setAuditEventTypeItem(auditQualityDto.getCertificatesTypeCode());
		auditDto.setAuditInfo(auditInfo);
		auditDto.setAuditId(auditQualityDto.getAuditId());
		auditDto.setAuditStatus(CertificationStatus.waiting.getValue());
		auditDto.setIdentificationNumber(auditQualityDto.getCertificatesCardNumber());
		auditDto.setExpireDate(auditQualityDto.getExpireDate());
		auditDto.setPushUrl(ConstantUtil.myIndexPage);
		auditDto.setMessageType(MessageType.AUTHEN.getValue());
		auditDto.setMessageBizType(MessageBizType.AUDIT_SUBMIT_PUSH);
		//认证
		audit(auditDto);
	}




	/**
	 * 车辆认证
	 * @param auditVehicleDto
	 */
	public CarShortInfoVo auditVehicle(AuditVehicleDto auditVehicleDto){
		Gson gson = new Gson();
		String personId = auditVehicleDto.getPersonId();
		if(null == personId){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.ENITY_IS_NOT_EXIST);
		}
		SystemPersonEntity person = personDao.select(personId);
		if(null == person){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.ENITY_IS_NOT_EXIST);
		}
		if(StringUtils.isBlank(auditVehicleDto.getOriginalVehicleLicense())){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.ORIGINALVEHICLELICENSE_IS_NOT_NULL);
		}
		if(StringUtils.isBlank(auditVehicleDto.getCopyVehicleLicense1())){
//			if(StringUtils.isBlank(auditVehicleDto.getCopyVehicleLicense1()) || StringUtils.isBlank(auditVehicleDto.getCopyVehicleLicense2())){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.COPYVEHICLELICENSE_IS_NOT_NULL);
		}
		AuditEntity drivingLicenseAudit = auditDao.getAuditEnity(personId, AuditEventType.DrivingLicenseVerification.getValue());
		if(null == drivingLicenseAudit){
			throw  new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVING_LICENSE_NOT_AUDIT);
		}
		//判断行驶证名称是否包含企业关键字
		String vehicleLicenseOwner = auditVehicleDto.getVehicleLicenseOwner();
		KeyWordUtil keyWordUtil = new KeyWordUtil(KeyWordUtil.getKeySet());
		Set<String> result = keyWordUtil.getWords(vehicleLicenseOwner);
		Integer coopType = result.size()>0 ? CoopType.SelfCar.getValue() : CoopType.Car.getValue();
		auditVehicleDto.setCoopType(coopType);

		//判断该车辆是是否已经认证
		Long auditCarNum = carsDao.getCountByAuditStatus(auditVehicleDto.getPlateNumber(),auditVehicleDto.getPlateColorCode());
		if(auditCarNum>0){
			throw  new ServiceException(CarErrorEnums.CarError.PLATE_NUMBER_IS_AUDITED);
		}

		//自有车辆 判断  行驶证和驾驶证是否一致
		if(coopType.equals(CoopType.Car.getValue())){
			String auditInfo = drivingLicenseAudit.getAuditInfo();
			DrivingLicenseDto drivingLicenseDto = gson.fromJson(auditInfo,DrivingLicenseDto.class);
			if(!drivingLicenseDto.getPersonName().equals(auditVehicleDto.getVehicleLicenseOwner())){
				throw new ServiceException(AuditLogErrorEnums.AuditLogError.DRIVING_LICENSE_NOT_SAME);
			}
		}
		//企业车辆添加校验
		else if(coopType.equals(CoopType.SelfCar.getValue())) {

			List<EnterpriseInfoEntity> entInfoList = enterpriseInfoDao.getEntInfoByEntName(auditVehicleDto.getVehicleLicenseOwner());
			if(entInfoList!=null && entInfoList.size()>0) {
				EnterpriseInfoEntity entInfo = entInfoList.get(0);
				auditVehicleDto.setEntId(entInfo.getId());
				if(CertificationStatus.verified.getValue()!=entInfo.getAuditStatus()) {
					throw  new ServiceException(EnterpriseErrorEnums.ENT_NOT_AUDIT);
				}
			}else {
				throw  new ServiceException(EnterpriseErrorEnums.ENT_NOT_AUDIT);
			}

			//判断该车辆是是否已经认证
			Long auditEntCarNum = enterpriseCooperateCarDao.getCountByAuditStatus(auditVehicleDto.getPlateNumber(),auditVehicleDto.getPlateColorCode(),CoopType.SelfCar.getValue());
			if(auditEntCarNum>0){
				throw  new ServiceException(CarErrorEnums.CarError.PLATE_NUMBER_IS_AUDITED);
			}

			//只有主驾才能认证
			if(StringUtils.isNotBlank(auditVehicleDto.getMyVehicleId())) {
				MyVehicleEntity myVehicle = myVehicleDao.select(auditVehicleDto.getMyVehicleId());
				if(myVehicle!=null && StringUtils.isNotBlank(myVehicle.getEntCarId())) {
					EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.select(myVehicle.getEntCarId());
					if(entCar!=null) {
						if(!personId.equals(entCar.getFirstDriverPersonID())) {
							throw new ServiceException(AuditLogErrorEnums.AuditLogError.SECOND_DRIVING_NOT_AUDIT);
						}
					}
				}
			}

			List<MyVehicleEntity> myVehicleList = myVehicleDao.getMyVehicleList(personId);
			myVehicleList = myVehicleList.parallelStream().filter(p -> CoopType.SelfCar.getValue().equals(p.getCoopType())).collect(Collectors.toList());
			if (!myVehicleList.isEmpty() && myVehicleList.size() > 0) {

				//如果是企业分配的未认证企业车辆，不需要判断车辆是否未认证，只有新加的才需要
				if(StringUtils.isBlank(auditVehicleDto.getCarId())) {
					Long unAuditCount = myVehicleList.parallelStream().filter(p -> p.getStatus().equals(InviteCarStatus.verify.getValue())).count();
					if (unAuditCount > 0) {
						throw new ServiceException(AuditLogErrorEnums.AuditLogError.HAVE_UNAUDIT_CAR);
					}
				}

				Long waitAuditCount = myVehicleList.parallelStream().filter(p -> p.getStatus().equals(InviteCarStatus.waiting.getValue())).count();
				if (waitAuditCount > 0) {
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.HAVE_WAITAUDIT_CAR);
				}
				Long auditCount = myVehicleList.parallelStream().filter(p -> p.getStatus().equals(InviteCarStatus.verified.getValue())).count();
				if (auditCount > 0) {
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.HAVE_AUDIT_CAR);
				}
				Long waitEntAuditCount = myVehicleList.parallelStream().filter(p -> p.getStatus().equals(InviteCarStatus.ent_waiting.getValue())).count();
				if (waitEntAuditCount > 0) {
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.HAVE_WAITENTAUDIT_CAR);
				}
				Long entAuditCount = myVehicleList.parallelStream().filter(p -> p.getStatus().equals(InviteCarStatus.ent_verified.getValue())).count();
				if (entAuditCount > 0) {
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.HAVE_ENTAUDIT_CAR);
				}
			}

			if(StringUtils.isNotBlank(auditVehicleDto.getCarId())) {
				CarsEntity carInfo = carsDao.select(auditVehicleDto.getCarId());
				if(!auditVehicleDto.getPlateNumber().equals(carInfo.getPlateNumber()) || !auditVehicleDto.getPlateColorCode().equals(carInfo.getPlateColor())) {
					throw new ServiceException(AuditLogErrorEnums.AuditLogError.BING_AND_AUDIT_CAR_NO_EQUALLY);
//					//如果分配的车和认证的车不是同一辆
//					EnterpriseCooperateCarEntity auditEntCar = enterpriseCooperateCarDao.getEntCarByPlateNumberAndColor(auditVehicleDto.getPlateNumber(), auditVehicleDto.getPlateColorCode(), CoopType.SelfCar.getValue(),auditVehicleDto.getEntId());
//					if(auditEntCar!=null) {
//						EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getEntCarByPlateNumberAndColor(carInfo.getPlateNumber(), carInfo.getPlateColor(), CoopType.SelfCar.getValue(),auditVehicleDto.getEntId());
//
//						if(StringUtils.isNotBlank(entCar.getFirstDriverPersonID())) {//解除绑定
//							MyVehicleEntity myVehicleInfo = myVehicleDao.selectByPersonIdAndEntCarId(entCar.getFirstDriverPersonID(), entCar.getGuid());
//							if(myVehicleInfo!=null) {
//								//进入历史车辆
//								carSender.deleteDriverCar(entCar.getFirstDriverPersonID(),entCar.getFirstDriverName(),
//										auditVehicleDto.getMyVehicleId(),VehicleHistoryType.remove.getValue(),null);
//							}
//						}
//						if(StringUtils.isNotBlank(entCar.getSecondDriverPersonID())) {//解除绑定
//							MyVehicleEntity myVehicleInfo = myVehicleDao.selectByPersonIdAndEntCarId(entCar.getSecondDriverPersonID(), entCar.getGuid());
//							if(myVehicleInfo!=null) {
//								//进入历史车辆
//								carSender.deleteDriverCar(entCar.getFirstDriverPersonID(),entCar.getSecondDriverName(),
//										auditVehicleDto.getMyVehicleId(),VehicleHistoryType.remove.getValue(),null);
//							}
//						}
//						if(StringUtils.isNotBlank(auditEntCar.getFirstDriverPersonID())) {//解除绑定
//							MyVehicleEntity myVehicleInfo = myVehicleDao.selectByPersonIdAndEntCarId(auditEntCar.getFirstDriverPersonID(), auditEntCar.getGuid());
//							if(myVehicleInfo!=null) {
//								//进入历史车辆
//								carSender.deleteDriverCar(auditEntCar.getFirstDriverPersonID(),auditEntCar.getFirstDriverName(),
//										auditVehicleDto.getMyVehicleId(),VehicleHistoryType.remove.getValue(),null);
//							}
//						}
//						if(StringUtils.isNotBlank(auditEntCar.getSecondDriverPersonID())) {//解除绑定
//							MyVehicleEntity myVehicleInfo = myVehicleDao.selectByPersonIdAndEntCarId(auditEntCar.getSecondDriverPersonID(), auditEntCar.getGuid());
//							if(myVehicleInfo!=null) {
//								//进入历史车辆
//								carSender.deleteDriverCar(auditEntCar.getSecondDriverPersonID(),auditEntCar.getSecondDriverName(),
//										auditVehicleDto.getMyVehicleId(),VehicleHistoryType.remove.getValue(),null);
//							}
//						}
//
//						enterpriseCooperateCarDao.setCoopCarToNullByCarId(entCar.getEntid(), entCar.getCarid());
//						enterpriseCooperateCarDao.setCoopCarToNullByCarId(auditEntCar.getEntid(), auditEntCar.getCarid());
//						enterpriseCooperatePerDao.setCoopPerToNullByCarId(entCar.getEntid(), entCar.getCarid());
//						enterpriseCooperatePerDao.setCoopPerToNullByCarId(auditEntCar.getEntid(), auditEntCar.getCarid());
//
//						auditEntCar.setFirstDriverPersonID(entCar.getFirstDriverPersonID());
//						auditEntCar.setFirstDriverName(entCar.getFirstDriverName());
//						auditEntCar.setFirstDriverMobile(entCar.getFirstDriverMobile());
//						enterpriseCooperateCarDao.update(auditEntCar);
//
//						auditVehicleDto.setCarId(auditEntCar.getCarid());
//						auditVehicleDto.setEntCarId(auditEntCar.getGuid());
//					}else {
//						//企业不存在，走新添加企业车辆流程
//						auditVehicleDto.setCarId(null);
//						auditVehicleDto.setMyVehicleId(null);
//						auditVehicleDto.setEntCarId(null);
//					}
				}
			}
		}
		Integer auditEventType = AuditEventType.VehicleVerification.getValue();
		//行驶证正本
		String originalVehicleLicense = auditVehicleDto.getOriginalVehicleLicense();
		if(originalVehicleLicense.contains(ConstantUtil.WATER_MARK_URL)){
			originalVehicleLicense = originalVehicleLicense.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		auditVehicleDto.setOriginalVehicleLicense(originalVehicleLicense);

		//行驶证副本
		String copyVehicleLicense1 = auditVehicleDto.getCopyVehicleLicense1();
		if(copyVehicleLicense1.contains(ConstantUtil.WATER_MARK_URL)){
			copyVehicleLicense1 = copyVehicleLicense1.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		auditVehicleDto.setCopyVehicleLicense1(copyVehicleLicense1);

		//行驶证副本
		String copyVehicleLicense2 = auditVehicleDto.getCopyVehicleLicense2();
		if(StringUtils.isNotBlank(copyVehicleLicense2) && copyVehicleLicense2.contains(ConstantUtil.WATER_MARK_URL)){
			copyVehicleLicense2 = copyVehicleLicense2.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		auditVehicleDto.setCopyVehicleLicense2(copyVehicleLicense2);

		//车辆照片
		String vehiclePic = auditVehicleDto.getVehiclePic();
		if(StringUtils.isNotBlank(vehiclePic) && vehiclePic.contains(ConstantUtil.WATER_MARK_URL)){
			vehiclePic = vehiclePic.replaceAll("\\"+ConstantUtil.WATER_MARK_URL, "");
		}
		auditVehicleDto.setVehiclePic(vehiclePic);
		//走添加车辆的流程
		CarShortInfoVo carShortInfoVo = vehicleService.addOrUpdateVehicle(auditVehicleDto,person);
		if(null != carShortInfoVo){
			carShortInfoVo.setCoopType(coopType);
			auditVehicleDto.setMyVehicleId(carShortInfoVo.getMyVehicleId());
			auditVehicleDto.setCarId(carShortInfoVo.getCarId());
			String auditInfo = gson.toJson(auditVehicleDto);
			AuditDto auditDto = new AuditDto();
			auditDto.setObjectId(carShortInfoVo.getCarId());
			auditDto.setPersonId(auditVehicleDto.getPersonId());
			auditDto.setPersonName(person.getMyName());
			auditDto.setPhoneNumber(person.getLoginName());
			auditDto.setIdcardNumber(person.getSocialLic());
			auditDto.setAuditEventType(auditEventType);
			auditDto.setAuditInfo(auditInfo);
//			auditDto.setAuditStatus(CertificationStatus.waiting.getValue());
			auditDto.setAuditStatus(CertificationStatus.verified.getValue());

			//点击消息跳转界面
			String pushUrl = ConstantUtil.carInfoPage+"myVehicleId="+carShortInfoVo.getMyVehicleId();
			auditDto.setPushUrl(pushUrl);
			auditDto.setMessageType(MessageType.AUTHEN.getValue());
			auditDto.setMessageBizType(MessageBizType.DRIVERADDCAR_AUDIT_SUBMIT_PUSH);



			if(coopType.equals(CoopType.SelfCar.getValue())) {

				EnterpriseCooperateCarEntity entCar = null;
				if(StringUtils.isNotBlank(auditVehicleDto.getEntCarId())) {
					entCar = enterpriseCooperateCarDao.select(auditVehicleDto.getEntCarId());
				}else {
					MyVehicleEntity myVehicle = myVehicleDao.select(auditVehicleDto.getMyVehicleId());
					if(myVehicle!=null && StringUtils.isNotBlank(myVehicle.getEntCarId())) {
						entCar = enterpriseCooperateCarDao.select(myVehicle.getEntCarId());
					}
				}

				if(entCar==null) {
					entCar = enterpriseCooperateCarDao.getEntCarByPlateNumberAndColor(auditVehicleDto.getPlateNumber(), auditVehicleDto.getPlateColorCode(), CoopType.SelfCar.getValue(),auditVehicleDto.getEntId());
				}

				if(entCar!=null) {
//					if(!"Driver".equals(entCar.getSourceType())) {
//						//企业端添加的

					auditDto.setMessageBizType(MessageBizType.DRIVERADDSELFCAR_AUDIT_SUCCESS_PUSH);

					StringBuffer vehicleLicenseBuffer = new StringBuffer();
					vehicleLicenseBuffer.append(auditVehicleDto.getOriginalVehicleLicense())
							.append(",").append(auditVehicleDto.getCopyVehicleLicense1())
							.append(",").append(auditVehicleDto.getCopyVehicleLicense2());

					enterpriseCooperateCarDao.updateInvitestate(entCar.getGuid(), InviteCarStatus.ent_verified.getValue());
					enterpriseCooperateCarDao.updateAuditStatusByEntCarId(entCar.getGuid(), CertificationStatus.verified.getValue());
					enterpriseCooperateCarDao.updateEntCarByEntCarId(auditVehicleDto.getVehiclePic(), auditVehicleDto.getVehicleIdentificationCode(), auditVehicleDto.getVehicleEngineCode(),
							vehicleLicenseBuffer.toString(), vehicleLicenseOwner, entCar.getGuid());

					myVehicleDao.updateStatusByCarId(entCar.getCarid(), InviteCarStatus.ent_verified.getValue());
					myVehicleDao.updateAuditStatusByCarId(entCar.getCarid(), CertificationStatus.verified.getValue());
					myVehicleDao.updateMyVehicleByEntCarId(auditVehicleDto.getVehiclePic(), auditVehicleDto.getVehicleIdentificationCode(), auditVehicleDto.getVehicleEngineCode(),
							vehicleLicenseBuffer.toString(), vehicleLicenseOwner, entCar.getGuid());

					carsDao.updateAuditStatus(entCar.getCarid(), CertificationStatus.verified.getValue(), vehicleLicenseBuffer.toString(), auditVehicleDto.getVehicleIdentificationCode(), auditVehicleDto.getVehicleEngineCode(), vehiclePic, vehicleLicenseOwner);

//					}else {
//						//司机端添加的
//
//						//企业车辆，发送给企业审核
//						auditDto.setMessageBizType(MessageBizType.DRIVERADDENTCAR_AUDIT_SUCCESS_PUSH);
//
//						String postUrl = sysSettingDao.getValue(SettingConstantUtil.ADD_ENT_CAR_APPROVAL);
//						String body = JsonMapper.toNonNullJson(auditVehicleDto);
//				        StarsoftHttpUtil.post(postUrl, body);
//					}
				}else {
					//企业车辆，发送给企业审核
					auditDto.setMessageBizType(MessageBizType.DRIVERADDENTCAR_AUDIT_SUCCESS_PUSH);

					String postUrl = sysSettingDao.getValue(SettingConstantUtil.ADD_ENT_CAR_APPROVAL);
					VehicleAuditInfoVo vehicleAuditInfoVo = gson.fromJson(auditInfo,VehicleAuditInfoVo.class);
					String body = JsonMapper.toNonNullJson(vehicleAuditInfoVo);
					StarsoftHttpUtil.post(postUrl, body);
				}

			}else {
				auditDto.setMessageBizType(MessageBizType.DRIVERADDSELFCAR_AUDIT_SUCCESS_PUSH);
			}

			//认证
			audit(auditDto);
		}
		else {
			throw new ServiceException(AuditLogErrorEnums.AuditLogError.AUDIT_FAIL);
		}
		return carShortInfoVo;
	}





	/**
	 * 认证业务入口
	 * @param auditDto
	 */
	public void audit(AuditDto auditDto) {
		Integer source=AuditSource.DriverApp.getValue();
		Integer auditEventModel = AuditEventModel.Person.getValue();
		Integer submitType = SubmitType.Audit.getValue();
		String personId = auditDto.getPersonId();
		String personName = auditDto.getPersonName();
		String phoneNumber = auditDto.getPhoneNumber();
		String idcardNumber = auditDto.getIdcardNumber();
		Integer auditEventType = auditDto.getAuditEventType();
		String auditInfo = auditDto.getAuditInfo();
		Integer auditStatus = auditDto.getAuditStatus();
		String auditEventTypeItem = auditDto.getAuditEventTypeItem();
		String auditId = auditDto.getAuditId();
		String objectId = auditDto.getObjectId();
		String identificationNumber = auditDto.getIdentificationNumber();
		String expireDate = auditDto.getExpireDate();
		AuditEntity auditEntity = null;
		if(StringUtils.isNotBlank(auditId)){
			auditEntity = auditDao.select(auditId);
		}
		else{
			auditEntity = auditDao.getAuditEnity(objectId, auditEventType);
			if(StringUtils.isNotBlank(auditEventTypeItem)){
				if(auditEventTypeItem.equals(QualityCertificatesType.QTYSZGZS.getValue())){
					auditEntity = null;
				}
				else{
					auditEntity = auditDao.getsAuditEnityByItem(objectId,auditEventType,auditEventTypeItem);
				}
			}
		}

		//数据存入认证表-------------------------------
		if(auditEntity != null){
			auditEntity.setAuditInfo(auditInfo);
			auditEntity.setAuditSource(source);
			auditEntity.setEditTime(DateUtil.getSqlTime());
			auditEntity.setApplyPersonId(personId);
			auditEntity.setAuditStatus(auditStatus);
			auditEntity.setIdentificationNumber(identificationNumber);
			auditEntity.setExpireDate(expireDate);
			if(null != auditStatus && CertificationStatus.verified.getValue().equals(auditStatus)) {
				auditEntity.setAuditTime(DateUtil.getSqlTime());
			}
			auditDao.update(auditEntity);
		}else{
			auditEntity = new AuditEntity();
			auditEntity.setGuid(RandomTool.getGUId());
			auditEntity.setApplyPersonId(personId);
			auditEntity.setAuditEventType(auditEventType);
			auditEntity.setAuditEventTypeItem(auditDto.getAuditEventTypeItem());
			auditEntity.setAuditInfo(auditInfo);
			auditEntity.setAuditEventModel(auditEventModel);
			auditEntity.setAuditSource(source);
			auditEntity.setAuditStatus(auditStatus);
			auditEntity.setAddTime(DateUtil.getSqlTime());
			auditEntity.setEditTime(DateUtil.getSqlTime());
			auditEntity.setObjectId(objectId);
			auditEntity.setIdentificationNumber(identificationNumber);
			auditEntity.setExpireDate(expireDate);
			if(null != auditStatus && CertificationStatus.verified.getValue().equals(auditStatus)) {
				auditEntity.setAuditTime(DateUtil.getSqlTime());
			}
			auditDao.merge(auditEntity);
		}
		//数据存入认证历史表-------------------------
		AuditLogEntity auditlogEntity = new AuditLogEntity();
		auditlogEntity.setGuid(RandomTool.getGUId());
		auditlogEntity.setAddTime(DateUtil.getSqlTime());
		auditlogEntity.setEditTime(DateUtil.getSqlTime());
		auditlogEntity.setApplyPersonId(personId);
		auditlogEntity.setAuditInfo(auditInfo);
		auditlogEntity.setAuditEventModel(auditEventModel);
		auditlogEntity.setAuditEventType(auditEventType);
		auditlogEntity.setAuditType(auditEventType);
		auditlogEntity.setAuditSource(source);
		auditlogEntity.setAuditStatus(auditStatus);
		auditlogEntity.setObjectId(objectId);
		auditlogEntity.setSubmitType(submitType);
		auditlogDao.merge(auditlogEntity);



		//数据存入user_audit_result表--------------------
		UserAuditResultEntity userAuditEntity = new UserAuditResultEntity();
		userAuditEntity.setGuid(RandomTool.getGUId());
		userAuditEntity.setApplyPersonId(personId);
		userAuditEntity.setApplyPersonName(personName);
		userAuditEntity.setApplyTime(DateUtil.getSqlTime());
		userAuditEntity.setObjectId(objectId);
		userAuditEntity.setObjectType(auditEventType);
		userAuditEntity.setSource(source);
		userAuditEntity.setIsAudit(0);
		userAuditEntity.setType(ConstantUtil.INTEGER_CODE_ZERO);
		userAuditEntity.setAuditResult(ConstantUtil.INTEGER_CODE_ZERO);
		userAuditEntity.setAuditStatus(auditStatus);
		//系统自动审核通过
		if(CertificationStatus.verified.getValue().equals(auditStatus)){
			userAuditEntity.setAuditTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
			userAuditEntity.setAuditPersonName("系统审核");
		}
		userAuditEntity.setApplyPersonSocialLic(idcardNumber);
		userAuditEntity.setApplyPersonPhone(phoneNumber);
		userAuditEntity.setAuditInfo(auditInfo);
		userAuditEntity.setAuditId(auditEntity.getGuid());
		userAuditEntity.setAuditLogId(auditlogEntity.getGuid());
		userAuditResultDao.merge(userAuditEntity);


		//IM消息推送
		sendImPush(auditEntity,auditDto.getMessageType(),auditDto.getMessageBizType(),auditDto.getPushUrl());

	}


	/**
	 * 获取驾驶证认证信息
	 *
	 * @param baseUserId
	 * @return
	 */
	@Override
	public DrivingLicenseAuditVo getDrivingLicenseAudit(String baseUserId) {
		AuditEntity auditEntity = auditDao.getAuditEnity(baseUserId, AuditEventType.DrivingLicenseVerification.getValue());
		AuditLogEntity personAuditLogEntity = auditlogDao.getEnterPriseAuditLog(baseUserId, AuditEventType.DrivingLicenseVerification.getValue());
		DrivingLicenseAuditVo driverCertificationVo=new DrivingLicenseAuditVo();
		Integer driverAuditStatus = 4;
		if(auditEntity!=null){
			String auditInfo = auditEntity.getAuditInfo();
			Gson gson = new Gson();
			DrivingLicenseDto drivingLicenseDto = gson.fromJson(auditInfo,DrivingLicenseDto.class);
			if(StringUtils.isNotBlank(drivingLicenseDto.getDriverLicensePic())){
				if(drivingLicenseDto.getDriverLicensePic().contains(ConstantUtil.WATER_MARK_URL)){
					driverCertificationVo.setDriverLicensePic(drivingLicenseDto.getDriverLicensePic());
				}else{
					driverCertificationVo.setDriverLicensePic(drivingLicenseDto.getDriverLicensePic()+ConstantUtil.WATER_MARK_URL);
				}
			}
			if(StringUtils.isNotBlank(drivingLicenseDto.getDriverLicenseBackPic())){
				if(drivingLicenseDto.getDriverLicenseBackPic().contains(ConstantUtil.WATER_MARK_URL)){
					driverCertificationVo.setDriverLicenseBackPic(drivingLicenseDto.getDriverLicenseBackPic());
				}else{
					driverCertificationVo.setDriverLicenseBackPic(drivingLicenseDto.getDriverLicenseBackPic()+ConstantUtil.WATER_MARK_URL);
				}
			}
			driverCertificationVo.setDriverLicenseNumber(drivingLicenseDto.getDriverLicenseNumber());
			driverCertificationVo.setDriverStartTime(drivingLicenseDto.getDriverStartTime());
			String endTime = drivingLicenseDto.getDiverEndTime();
			driverCertificationVo.setDiverEndTime(endTime);
			driverCertificationVo.setDriverType(drivingLicenseDto.getDriverType());
			driverCertificationVo.setPersonName(drivingLicenseDto.getPersonName());
			driverCertificationVo.setFirstTakeTime(drivingLicenseDto.getFirstTakeTime());
			driverCertificationVo.setExceptionReason(drivingLicenseDto.getExceptionReason());
//			driverCertificationVo.setAuditDate(null == auditEntity.getAuditTime() ? auditEntity.getEditTime() : auditEntity.getAuditTime());
			driverCertificationVo.setAuditDate(auditEntity.getAuditTime());
			driverCertificationVo.setAuditMessage(auditEntity.getAuditMessage());
			//认证状态
			driverAuditStatus = auditEntity.getAuditStatus();
			if (driverAuditStatus.equals(CertificationStatus.verified.getValue())){
				driverAuditStatus = dealExpire(endTime);
			}
			//驾驶证号
			String driverLicenseNumber = drivingLicenseDto.getDriverLicenseNumber();
			driverCertificationVo.setNoEncryptionNumber(driverLicenseNumber);
			if(StringUtils.isNotBlank(driverLicenseNumber)){
				String start = driverLicenseNumber.substring(0, 4);
				String end = driverLicenseNumber.substring(driverLicenseNumber.length()-4, driverLicenseNumber.length());
				driverCertificationVo.setDriverLicenseNumber(start+"**********"+end);
			}
		}
		if(personAuditLogEntity != null && CertificationStatus.failed.getValue().equals(personAuditLogEntity.getAuditStatus())){
			driverCertificationVo.setFailMessage(personAuditLogEntity.getFailMessage());
			driverCertificationVo.setCheckTime(personAuditLogEntity.getAuditTime());
		}
		driverCertificationVo.setDriverAuditStatus(driverAuditStatus);
		return driverCertificationVo;
	}


	/**
	 * 司机从业资格认证列表
	 * @param personId
	 * @return
	 */
	public  List<QualityAuditVo> getQualityAuditList(String personId){
		Gson gson = new Gson();
		List<QualityAuditVo> resultList = new ArrayList<>();
		List<AuditEntity> auditList = auditDao.getsAuditListByType(personId, AuditEventType.PersonQualityVerification.getValue());
		for(AuditEntity auditEntity : auditList){
			QualityAuditVo qualityAuditVo = new QualityAuditVo();
			qualityAuditVo.setAuditId(auditEntity.getGuid());
			qualityAuditVo.setAuditDate(null == auditEntity.getAuditTime() ? auditEntity.getEditTime().toString() : auditEntity.getAuditTime().toString());
			qualityAuditVo.setAuditMessage(auditEntity.getAuditMessage());
			String auditInfo = auditEntity.getAuditInfo();
			AuditQualityDto auditQualityDto = gson.fromJson(auditInfo,AuditQualityDto.class);
			String expireDateDate = auditQualityDto.getExpireDate();
			qualityAuditVo.setExpireDate(expireDateDate);
			qualityAuditVo.setCertificatesTypeCode(auditQualityDto.getCertificatesTypeCode());
			qualityAuditVo.setCertificatesTypeValue(auditQualityDto.getCertificatesTypeValue());
			qualityAuditVo.setCertificatesPic(auditQualityDto.getCertificatesPic());
			qualityAuditVo.setPersonName(auditQualityDto.getPersonName());
			qualityAuditVo.setIdCardNumber(auditQualityDto.getIdCardNumber());
			qualityAuditVo.setQualityType(auditQualityDto.getQualityType());
			qualityAuditVo.setExpireDate(expireDateDate);
			qualityAuditVo.setCertificatesCardNumber(auditQualityDto.getCertificatesCardNumber());
			//认证状态
			Integer auditStatus = auditEntity.getAuditStatus();
			if (auditStatus.equals(CertificationStatus.verified.getValue())){
				auditStatus = dealExpire(expireDateDate);
			}
			qualityAuditVo.setAuditStatus(auditStatus);

			resultList.add(qualityAuditVo);
		}
		return resultList;
	}


	/**
	 * 获取认证失败原因
	 * @param auditId
	 * @param objectId
	 * @param objectType
	 * @param objectItem
	 * @return
	 */
	public AuditFailInfo getAuditFailInfo(String auditId,String objectId,Integer objectType,String objectItem){
		AuditEntity audit = null;
		AuditFailInfo auditFailInfo = null;
		if(StringUtils.isNotBlank(auditId)){
			audit = auditDao.select(auditId);
		}
		else{
			if(StringUtils.isBlank(objectItem)){
				audit = auditDao.getAuditEnity(objectId,objectType);
			}
			else{
				audit = auditDao.getsAuditEnityByItem(objectId,objectType,objectItem);
			}

		}
		if(null != audit){
			auditFailInfo = new AuditFailInfo();
			auditFailInfo.setAuditMessage(audit.getAuditMessage());
			auditFailInfo.setAuditTime(audit.getAuditTime());
		}

		return  auditFailInfo;

	}


	/**
	 * 我的认证是否即将到期情况
	 * @param personId
	 * @return
	 */
	public AuditExpireVo myAuthenticationExpire(String personId){
		AuditExpireVo auditExpireVo = new AuditExpireVo();
		//我的所有已通过的认证
		List<AuditEntity> resultList = auditDao.getWillExpireList(personId);
		resultList = resultList.parallelStream().filter(p -> (!p.getDiffDays().equals(-1))).collect(Collectors.toList());
		if(!resultList.isEmpty() && resultList.size()>0){
			//实名认证
			List<AuditEntity> identityList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.PersonVerification.getValue())).collect(Collectors.toList());
			if(!identityList.isEmpty() && identityList.size()>0){
				auditExpireVo.setRealNameAuthentication(true);
			}
			//驾驶证
			List<AuditEntity> licenseList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.DrivingLicenseVerification.getValue())).collect(Collectors.toList());
			if(!licenseList.isEmpty() && licenseList.size()>0){
				auditExpireVo.setDrivingLicenseAuthentication(true);
			}
			//从业资格
			List<AuditEntity> qualityList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.PersonQualityVerification.getValue())).collect(Collectors.toList());
			if(!qualityList.isEmpty() && qualityList.size()>0){
				auditExpireVo.setQualityAuthentication(true);
			}
		}
		return  auditExpireVo;
	}



	/**
	 *过期 、即将到期推送
	 */
	public void authenticationExpirePush(){
		List<AuditEntity> resultList = auditDao.getExpireList(null);
		for(AuditEntity audit : resultList){
			Integer messageType = MessageType.EXPIRE.getValue();
			MessageBizType messageBizType = null;
			//过期提醒
			if(audit.getDiffDays().equals(-1)){
				messageBizType = MessageBizType.EXPIRE_PUSH;
			}
			//即将过期提醒
			else{
				messageBizType = MessageBizType.WAIL_EXPIRE_PUSH;
			}
			sendImPush(audit,messageType,messageBizType,ConstantUtil.myIndexPage);
		}
	}


	/**
	 * 认证审核结果推送
	 * @param auditId
	 * @param auditStatus
	 */
	public void pushAuditResult(String auditId,Integer auditStatus){
		AuditEntity audit = auditDao.select(auditId);
		Integer messageType = MessageType.AUTHEN.getValue();
		MessageBizType messageBizType = null;
		if(auditStatus.equals(CertificationStatus.verified.getValue())){
			messageBizType = MessageBizType.AUDIT_SUCCESS_PUSH;

			if(audit.getAuditEventType().equals(AuditEventType.VehicleVerification.getValue())){
				messageBizType = null;
			}
		}
		else{
			messageBizType = MessageBizType.AUDIT_FAIL_PUSH;
			
			if(audit.getAuditEventType().equals(AuditEventType.GuaranteeVerification.getValue())){
				messageBizType = MessageBizType.GUARENTEE_AUDIT_FAIL_PUSH;
			}
		}
		String pushUrl = "";
		if(audit.getAuditEventType().equals(AuditEventType.VehicleVerification.getValue())){
			Gson gson = new Gson();
			AuditVehicleDto auditVehicleDto = gson.fromJson(audit.getAuditInfo(),AuditVehicleDto.class);
			if(auditStatus.equals(CertificationStatus.verified.getValue())) {
//				if (auditVehicleDto.getCoopType().equals(CoopType.Car.getValue())) {
//					messageBizType = MessageBizType.DRIVERADDSELFCAR_AUDIT_SUCCESS_PUSH;
//				} else if (auditVehicleDto.getCoopType().equals(CoopType.SelfCar.getValue())) {
//
//					messageBizType = MessageBizType.DRIVERADDENTCAR_AUDIT_SUCCESS_PUSH;
//
//					EnterpriseCooperateCarEntity entCar = null;
//					if(StringUtils.isNotBlank(auditVehicleDto.getEntCarId())) {
//						entCar = enterpriseCooperateCarDao.select(auditVehicleDto.getEntCarId());
//					}else {
//						MyVehicleEntity myVehicle = myVehicleDao.select(auditVehicleDto.getMyVehicleId());
//						if(myVehicle!=null && StringUtils.isNotBlank(myVehicle.getEntCarId())) {
//							entCar = enterpriseCooperateCarDao.select(myVehicle.getEntCarId());
//						}
//					}
//					if(entCar!=null) {
//						if(CoopType.SelfCar.getValue()==entCar.getCoopType() && !"Driver".equals(entCar.getSourceType())) {
//							messageBizType = MessageBizType.DRIVERADDSELFCAR_AUDIT_SUCCESS_PUSH;
//						}
//					}
//
//				}
			}
			else{
				messageBizType = MessageBizType.DRIVERADDCAR_AUDIT_FAIL_PUSH;
				UserAuditResultEntity auditResult = userAuditResultDao.getByAudit(auditId);

				if (auditVehicleDto.getCoopType().equals(CoopType.Car.getValue())) {
					//自有车辆

					//进入历史车辆
					carSender.deleteDriverCar(auditVehicleDto.getPersonId(),auditResult.getAuditPersonName(),
							auditVehicleDto.getMyVehicleId(),VehicleHistoryType.failed.getValue(),audit.getAuditMessage());

				}
//				else if (auditVehicleDto.getCoopType().equals(CoopType.SelfCar.getValue())) {
//					//企业车辆
//
//					EnterpriseCooperateCarEntity entCar = null;
//					if(StringUtils.isNotBlank(auditVehicleDto.getEntCarId())) {
//						entCar = enterpriseCooperateCarDao.select(auditVehicleDto.getEntCarId());
//					}else {
//						MyVehicleEntity myVehicle = myVehicleDao.select(auditVehicleDto.getMyVehicleId());
//						if(myVehicle!=null && StringUtils.isNotBlank(myVehicle.getEntCarId())) {
//							entCar = enterpriseCooperateCarDao.select(myVehicle.getEntCarId());
//						}
//					}
//
//					if(entCar==null) {
//						entCar = enterpriseCooperateCarDao.getEntCarByPlateNumberAndColor(auditVehicleDto.getPlateNumber(), auditVehicleDto.getPlateColorCode(), CoopType.SelfCar.getValue(),auditVehicleDto.getEntId());
//					}
//
//					if(entCar!=null) {
//						if(!"Driver".equals(entCar.getSourceType())) {
//							//企业端添加的,司机端车辆状态变更回未认证，企业端保留该车辆，并认证状态同步为未认证。
//
//							entCar.setAuditStatus(CertificationStatus.verify.getValue());
//							entCar.setInviteState(InviteCarStatus.verify.getValue());
//		            		enterpriseCooperateCarDao.update(entCar);
//
//		            		myVehicleDao.updateHandle(auditVehicleDto.getMyVehicleId(), auditResult.getAuditPersonName(), audit.getAuditMessage(), VehicleHistoryType.failed.getValue());
//		            		myVehicleDao.updateStatus(auditVehicleDto.getMyVehicleId(),CertificationStatus.verify.getValue(),InviteCarStatus.verify.getValue());
//
//						}else {
//							//司机端添加的,审核失败进入历史，同时删除企业内该企业车辆
//
//							//进入历史车辆
//							carSender.deleteDriverCar(auditVehicleDto.getPersonId(),auditResult.getAuditPersonName(),
//									auditVehicleDto.getMyVehicleId(),VehicleHistoryType.failed.getValue(),audit.getAuditMessage());
//
//							//删除企业车辆
//							enterpriseCooperateCarDao.delete(entCar.getGuid());
//
//							//删除该车辆未审核的记录
//							approvalDao.deleteApproval(auditVehicleDto.getCarId(), auditVehicleDto.getEntId(), ApprovalType.DRIVERADDCAR.getValue());
//						}
//					}
//
//				}

//				boolean isHis = true;
//				if(StringUtils.isNoneBlank(auditVehicleDto.getMyVehicleId())) {
//					MyVehicleEntity myVehicle = myVehicleDao.select(auditVehicleDto.getMyVehicleId());
//					if(myVehicle!=null) {
//						EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.select(myVehicle.getEntCarId());
//						if(entCar!=null) {
//							if(CoopType.SelfCar.getValue()==entCar.getCoopType() && !"Driver".equals(entCar.getSourceType())) {
//
//								entCar.setAuditStatus(CertificationStatus.verify.getValue());
//								entCar.setInviteState(InviteCarStatus.verify.getValue());
//			            		enterpriseCooperateCarDao.update(entCar);
//
//			            		myVehicleDao.updateHandle(auditVehicleDto.getMyVehicleId(), auditResult.getAuditPersonName(), audit.getAuditMessage(), VehicleHistoryType.failed.getValue());
//			            		myVehicleDao.updateStatus(auditVehicleDto.getMyVehicleId(),CertificationStatus.verify.getValue(),InviteCarStatus.verify.getValue());
//
//								isHis = false;//企业分配的企业车辆认证失败后不进入历史车辆
//							}
//						}
//					}
//				}
//
//				if(isHis) {
//					//进入历史车辆
//					carSender.deleteDriverCar(auditVehicleDto.getPersonId(),auditResult.getAuditPersonName(),
//							auditVehicleDto.getMyVehicleId(),VehicleHistoryType.failed.getValue(),audit.getAuditMessage());
//				}
			}
			pushUrl = ConstantUtil.carInfoPage+"myVehicleId="+auditVehicleDto.getMyVehicleId();
		}
		else{
//			pushUrl = ConstantUtil.myIndexPage;
			pushUrl = ConstantUtil.driverInfoPage;
			if(audit.getAuditEventType().equals(AuditEventType.PersonQualityVerification.getValue())){
				pushUrl = ConstantUtil.myIndexPage;
			}
			if(audit.getAuditEventType().equals(AuditEventType.GuaranteeVerification.getValue())){
				Gson gson = new Gson();
				GuaranteeAuditDTO guaranteeAuditDTO = gson.fromJson(audit.getAuditInfo(),GuaranteeAuditDTO.class);
				pushUrl = ConstantUtil.guaranteeInfoPage+"carId="+guaranteeAuditDTO.getCarId();
			}
		}

		if(messageBizType!=null) {
			sendImPush(audit,messageType,messageBizType,pushUrl);
		}

	}



	/**
	 * 发送IM推送 发送短信
	 * @param audit
	 * @param messageType
	 * @param messageBizType
	 */
	private void sendImPush(AuditEntity audit,Integer messageType,MessageBizType messageBizType,String pushUrl){
		Gson gson = new Gson();
		ImModeDto imModeDto = new ImModeDto();
		SmsTemplate smsTemplate = new SmsTemplate();
		AuditExtraDto auditExtraDto = new AuditExtraDto();
		String personId = audit.getApplyPersonId();
		Integer auditEventType = audit.getAuditEventType();
		String zhengj = auditEventType.equals(AuditEventType.PersonVerification.getValue()) ? "身份证" :
				auditEventType.equals(AuditEventType.DrivingLicenseVerification.getValue()) ? "驾驶证" :
						auditEventType.equals(AuditEventType.PersonQualityVerification.getValue())	 ? "从业资格证" :
								auditEventType.equals(AuditEventType.VehicleVerification.getValue()) ?  "车辆":
									auditEventType.equals(AuditEventType.GuaranteeVerification.getValue()) ?  "交强险认证" : "";
		
		SystemPersonEntity sysperson = personDao.select(personId);
		ImUserEntity imUser = imUserDao.getByType(ConstantUtil.YUNMAI_PLATFORMID,messageType);
		//推送消息类容
		imModeDto.setType(messageType.toString());
		imModeDto.setTitle(messageBizType.getTitle());
		imModeDto.setContent(messageBizType.getContent());
		imModeDto.setFromUser(imUser.getImId());
		imModeDto.setUrl(pushUrl);
		List toUserList = new ArrayList();
		toUserList.add(sysperson.getLogitalkId());
		imModeDto.setToUser(toUserList);
		imModeDto.setSendTime(DateUtil.timestamp2Str(DateUtil.getSqlTime()));
		//自定义内容
		auditExtraDto.setBizType(messageBizType.getType());
		auditExtraDto.setAuditEventType(auditEventType);
		auditExtraDto.setAuditEventTypeValue(AuditEventType.find(auditEventType).getDescription());
		//到期、认证通过展示证件号码
		if(messageBizType.getType().equals(MessageBizType.AUDIT_SUCCESS_PUSH.getType()) || messageType.equals(MessageType.EXPIRE.getValue())){
			String identificationNumber = audit.getIdentificationNumber();
			//实名认证、驾驶证证件号需要➕*
			if(auditEventType.equals(AuditEventType.DrivingLicenseVerification.getValue()) || auditEventType.equals(AuditEventType.PersonVerification.getValue())){
				if(StringUtils.isNotBlank(identificationNumber)){
					String start = identificationNumber.substring(0, 4);
					String end = identificationNumber.substring(identificationNumber.length()-4, identificationNumber.length());
					identificationNumber=start+"**********"+end;
				}
			}
			auditExtraDto.setIdentificationNumber(identificationNumber);
		}
		if(audit.getAuditEventType().equals(AuditEventType.VehicleVerification.getValue())){
			AuditVehicleDto auditVehicleDto = gson.fromJson(audit.getAuditInfo(),AuditVehicleDto.class);
			auditExtraDto.setPlateNumber(auditVehicleDto.getPlateNumber());
			auditExtraDto.setCarTypeValue(auditVehicleDto.getCarTypeValue());
			auditExtraDto.setRatedLoad(auditVehicleDto.getRatedLoad());
			auditExtraDto.setRatedVolume(auditVehicleDto.getRatedVolume());
			auditExtraDto.setPlateColorValue(auditVehicleDto.getPlateColorValue());
			auditExtraDto.setVehicleLicenseOwner(auditVehicleDto.getVehicleLicenseOwner());
			auditExtraDto.setCoopTypeValue(CoopType.getSelfByValue(auditVehicleDto.getCoopType()).getDescription());
		}
		
		//交强险认证
		if(audit.getAuditEventType().equals(AuditEventType.GuaranteeVerification.getValue())){
			GuaranteeAuditDTO guaranteeAuditDTO = gson.fromJson(audit.getAuditInfo(),GuaranteeAuditDTO.class);
			auditExtraDto.setPlateNumber(guaranteeAuditDTO.getPlatNumber());
			auditExtraDto.setPlateColorValue(guaranteeAuditDTO.getPlateColorValue());
			auditExtraDto.setCarId(guaranteeAuditDTO.getCarId());
		}
		
		auditExtraDto.setExpireDate(audit.getExpireDate());
		auditExtraDto.setLinkUrl(pushUrl);
		imModeDto.setExtra(gson.toJson(auditExtraDto));


		//短信模板
		smsTemplate.setBaseUserId(personId);
		smsTemplate.setMobile(sysperson.getLoginName());
		smsTemplate.setType(messageBizType.getSmsType());
		smsTemplate.setIsVoice(1);
		if(StringUtils.isNotBlank(zhengj)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(MessageConstant.SMS_PARAM_ZHENG, zhengj);
			smsTemplate.setParameter(jsonObject);
		}
		//im 推送
		imMsgSender.sendModePush(imModeDto);

		//发短信
		jetfireMsgSender.sendSms(smsTemplate);
	}








	public void initEx(){
		List<AuditEntity> resultList = auditDao.selectAll();
		List<AuditEntity> identityList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.PersonVerification.getValue())).collect(Collectors.toList());
		for(AuditEntity auditEntity : identityList){
			Gson gson = new Gson();
			String auditInfo = auditEntity.getAuditInfo();
			PersonCertificationDTO personCertificationDTO = gson.fromJson(auditInfo, PersonCertificationDTO.class);
			auditEntity.setExpireDate(personCertificationDTO.getIdCardExpireDate());
			auditEntity.setIdentificationNumber(personCertificationDTO.getIdCardNumber());
			auditDao.update(auditEntity);
		}
		List<AuditEntity> licenseList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.DrivingLicenseVerification.getValue())).collect(Collectors.toList());
		for(AuditEntity auditEntity : licenseList){
			Gson gson = new Gson();
			String auditInfo = auditEntity.getAuditInfo();
			DrivingLicenseDto drivingLicenseDto = gson.fromJson(auditInfo,DrivingLicenseDto.class);
			auditEntity.setExpireDate(drivingLicenseDto.getDiverEndTime());
			auditEntity.setIdentificationNumber(drivingLicenseDto.getDriverLicenseNumber());
			auditDao.update(auditEntity);
		}

		List<AuditEntity> qualityList = resultList.parallelStream().filter(p -> p.getAuditEventType().equals(AuditEventType.PersonQualityVerification.getValue())).collect(Collectors.toList());
		for(AuditEntity auditEntity : qualityList){
			Gson gson = new Gson();
			String auditInfo = auditEntity.getAuditInfo();
			AuditQualityDto auditQualityDto = gson.fromJson(auditInfo,AuditQualityDto.class);
			auditEntity.setExpireDate(auditQualityDto.getExpireDate());
			auditEntity.setIdentificationNumber(auditQualityDto.getCertificatesCardNumber());
			auditDao.update(auditEntity);
		}
	}

	@Override
	public GetDriverIdentityVo getDriverIdentity(String personId) {
		SystemPersonEntity person = personDao.select(personId);
		if (null == person) {
			throw new ServiceException(AuditLogErrorEnums.AuditLogError.ENITY_IS_NOT_EXIST);
		}

		GetDriverIdentityVo vo = new GetDriverIdentityVo();

		// 驾驶证
		Long drivingLicenseAuditCount = auditlogDao.getSuccessCount(personId,
				AuditEventType.DrivingLicenseVerification.getValue());
		if (drivingLicenseAuditCount > 0) {
			vo.setDrivingLicenseVerification(1);
		}

		// 实名认证信息
		Long personAuditCount = auditlogDao.getSuccessCount(personId,
				AuditEventType.PersonVerification.getValue());
		if (personAuditCount > 0) {
			vo.setPersonVerification(1);
		}

		if (vo.getPersonVerification() == 0 && vo.getDrivingLicenseVerification() == 0) {
			vo.setIdentityType(1);
		} else if (vo.getPersonVerification() == 1 && vo.getDrivingLicenseVerification() == 0) {
			vo.setIdentityType(2);
		} else if (vo.getPersonVerification() == 1 && vo.getDrivingLicenseVerification() == 1) {
			vo.setIdentityType(3);
		}

		return vo;
	}


	/**
	 * 删除认证相关数据
	 * @param objectId
	 * @param auditType
	 */
	public void delete(String objectId,Integer auditType){
		AuditEntity auditEntity = auditDao.getAuditEnity(objectId,auditType);
		if(null != auditEntity) {
			auditDao.delete(auditEntity.getGuid());
			userAuditResultDao.deleteByAudit(auditEntity.getGuid());
		}
	}



	/**
	 * 计算到期时间
	 * @param expireDate
	 * @return
	 */
	private Integer dealExpire(String expireDate){
		Integer auditStatus = CertificationStatus.verified.getValue();
		if(!expireDate.equals("2999-01-01")){
			long expireTime = DateUtils.dateStrToDate(expireDate,"yyyy-MM-dd").getTime();
			long currentTime = System.currentTimeMillis();
			long  diff=(expireTime-currentTime)/(1000 * 60 * 60 * 24);
			if(diff<=30){
				auditStatus = CertificationStatus.will_expire.getValue();
			}
			if(diff<0){
				auditStatus = CertificationStatus.expire.getValue();
			}
		}
		return auditStatus;

	}






}
