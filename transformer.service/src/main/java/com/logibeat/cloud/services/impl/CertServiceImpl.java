package com.logibeat.cloud.services.impl;

import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.enumtype.AuditEventType;
import com.logibeat.cloud.common.vo.CertVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.CertDto;
import com.logibeat.cloud.core.dto.DrivingLicenseDto;
import com.logibeat.cloud.core.dto.PersonCertificationDTO;
import com.logibeat.cloud.core.dto.SyncCertDto;
import com.logibeat.cloud.errorenum.CertErrorEnums;
import com.logibeat.cloud.msg.dto.ImModeDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.CertExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.services.CertService;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private SyspersonCertDao syspersonCertDao;
	@Autowired
	private SyspersonInfoDao syspersonInfoDao;
	@Autowired
	private ImMsgSender imMsgSender;
	@Autowired
	private ImUserDao imUserDao;
	@Autowired
	private SyspersonDao personDao;
	@Autowired
	private EntStarSoftDao entStarSoftDao;
	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	private AuditDao auditDao;

	@Override
	public void add(CertDto certDto) {
		Gson gson = new Gson();
		// 判断姓名和身份证号是否和实名认证一致
		AuditEntity auditEnity = auditDao.getAuditEnity(certDto.getPersonId(),
				AuditEventType.PersonVerification.getValue());
		if (auditEnity == null) {
			throw new ServiceException(CertErrorEnums.CERT_ALREADY);
		}
		PersonCertificationDTO personCertificationDTO = gson.fromJson(auditEnity.getAuditInfo(),
				PersonCertificationDTO.class);
		if(certDto.getSource() != 300){
            if (!personCertificationDTO.getName().equals(certDto.getName())) {
                throw new ServiceException(CertErrorEnums.NAME_DIFFERENT);
            }
			if (!personCertificationDTO.getIdCardNumber().equals(certDto.getIdCardNumber())) {
				throw new ServiceException(CertErrorEnums.NUMBER_DIFFERENT);
			}
        }

		// 查询是否存在
		SyspersonCertEntity certEntity = syspersonCertDao.findByType(certDto.getPersonId(), certDto.getCertType());
		if (certEntity != null && !certDto.getCertType().equals("6504")) {
			throw new ServiceException(CertErrorEnums.ALREADY_EXIT);
		}

		String certInfo = gson.toJson(certDto);
		certEntity = new SyspersonCertEntity();
		certEntity.setGuid(StringUtils.generateGUID());
		certEntity.setPersonId(certDto.getPersonId());
		certEntity.setCertType(certDto.getCertType());
		certEntity.setCertName(certDto.getCertName());
		certEntity.setCertSource(certDto.getSource());
		certEntity.setCertInfo(certInfo);
		certEntity.setIsDel(0);
		certEntity.setCreateTime(DateUtil.getSqlTime());
		certEntity.setUpdateTime(DateUtil.getSqlTime());
		syspersonCertDao.insert(certEntity);
	}

	public void insertAllPersonCard(){
		//查询所有已经认证成功的人
		List<AuditEntity> allPersonAudit = auditDao.getAllEntityByEventType(AuditEventType.PersonVerification.getValue());
		List<String> personIdList = allPersonAudit.stream().map(p -> p.getObjectId()).collect(Collectors.toList());
		List<SyspersonCertEntity> certList = syspersonCertDao.findByPersonList(personIdList, "6001");
		List<String> certPersonList = certList.stream().map(p -> p.getPersonId()).collect(Collectors.toList());
//		if(certList != null){
//			Map<String, List<SyspersonCertEntity>> collect = certList.stream().collect(Collectors.groupingBy(p -> p.getPersonId()));
//		}
		Gson gson = new Gson();
		allPersonAudit.forEach(p -> {
			if(!certPersonList.contains(p.getObjectId())){
				PersonCertificationDTO personCertificationDTO = gson.fromJson(p.getAuditInfo(), PersonCertificationDTO.class);
				CertDto dto = new CertDto();
				dto.setPersonId(p.getObjectId());
				dto.setCertType("6001");
				dto.setCertName("居民身份证");
				dto.setName(personCertificationDTO.getName());
				dto.setIdCardNumber(personCertificationDTO.getIdCardNumber());
				dto.setCertExpireDate(personCertificationDTO.getIdCardExpireDate());
				dto.setSource(100);
				dto.setSex(personCertificationDTO.getSex());
				dto.setNation(personCertificationDTO.getNation());
				SyspersonCertEntity certEntity = new SyspersonCertEntity();
				certEntity.setGuid(StringUtils.generateGUID());
				certEntity.setPersonId(p.getObjectId());
				certEntity.setCertType("6001");
				certEntity.setCertName("居民身份证");
				certEntity.setCertSource(100);
				certEntity.setCertInfo(gson.toJson(dto));
				certEntity.setIsDel(0);
				certEntity.setCreateTime(DateUtil.getSqlTime());
				certEntity.setUpdateTime(DateUtil.getSqlTime());
				syspersonCertDao.insert(certEntity);
			}
		});
	}

	public void insertAllDriverCard(){
		//查询所有已经认证成功的人
		List<AuditEntity> allPersonAudit = auditDao.getAllEntityByEventType(AuditEventType.DrivingLicenseVerification.getValue());
		List<String> personIdList = allPersonAudit.stream().map(p -> p.getObjectId()).collect(Collectors.toList());
		List<SyspersonCertEntity> certList = syspersonCertDao.findByPersonList(personIdList, "6002");
		List<String> certPersonList = certList.stream().map(p -> p.getPersonId()).collect(Collectors.toList());
		Gson gson = new Gson();
		allPersonAudit.forEach(p -> {
			if(!certPersonList.contains(p.getObjectId())){
				DrivingLicenseDto drivingLicenseDto = gson.fromJson(p.getAuditInfo(), DrivingLicenseDto.class);
				CertDto dto = new CertDto();
				dto.setPersonId(p.getObjectId());
				dto.setCertType("6002");
				dto.setCertName("机动车驾驶证");
				dto.setCardUrl(drivingLicenseDto.getDriverLicensePic());
				dto.setName(drivingLicenseDto.getPersonName());
				dto.setCertCardNumber(drivingLicenseDto.getDriverLicenseNumber());
				dto.setIdCardNumber(drivingLicenseDto.getDriverLicenseNumber());
				dto.setSource(100);
				dto.setDriverStartTime(drivingLicenseDto.getDriverStartTime());
				dto.setDiverEndTime(drivingLicenseDto.getDiverEndTime());
				dto.setDriverType(drivingLicenseDto.getDriverType());
				dto.setFirstTakeTime(drivingLicenseDto.getFirstTakeTime());
				SyspersonCertEntity certEntity = new SyspersonCertEntity();
				certEntity.setGuid(StringUtils.generateGUID());
				certEntity.setPersonId(p.getObjectId());
				certEntity.setCertType("6002");
				certEntity.setCertName("机动车驾驶证");
				certEntity.setCertSource(100);
				certEntity.setCertInfo(gson.toJson(dto));
				certEntity.setIsDel(0);
				certEntity.setCreateTime(DateUtil.getSqlTime());
				certEntity.setUpdateTime(DateUtil.getSqlTime());
				syspersonCertDao.insert(certEntity);
			}
		});
	}

	@Override
	public void update(CertDto certDto) {
		// 查询是否存在
		SyspersonCertEntity certEntity = syspersonCertDao.findByType(certDto.getPersonId(), certDto.getCertType());
		if (certEntity == null) {
			add(certDto);
			return;
		}

		Gson gson = new Gson();
		CertDto certInfo = gson.fromJson(certEntity.getCertInfo(), CertDto.class);
		certEntity.setCertType(certDto.getCertType());
		certEntity.setCertName(certDto.getCertName());
		if (StringUtils.isNotEmpty(certDto.getCertType())) {
			certInfo.setCertType(certDto.getCertType());
		}
		if (StringUtils.isNotEmpty(certDto.getCertName())) {
			certInfo.setCertName(certDto.getCertName());
		}
		if (StringUtils.isNotEmpty(certDto.getCardUrl())) {
			certInfo.setCardUrl(certDto.getCardUrl());
		}
		if (StringUtils.isNotEmpty(certDto.getName())) {
			certInfo.setName(certDto.getName());
		}
		if (StringUtils.isNotEmpty(certDto.getCertCardNumber())) {
			certInfo.setCertCardNumber(certDto.getCertCardNumber());
		}
		if (StringUtils.isNotEmpty(certDto.getIdCardNumber())) {
			certInfo.setIdCardNumber(certDto.getIdCardNumber());
		}
		if (StringUtils.isNotEmpty(certDto.getCertExpireDate())) {
			certInfo.setCertExpireDate(certDto.getCertExpireDate());
		}
		if (StringUtils.isNotEmpty(certDto.getSendCertStartDate())) {
			certInfo.setSendCertStartDate(certDto.getSendCertStartDate());
		}
		if (StringUtils.isNotEmpty(certDto.getSendCertCompany())) {
			certInfo.setSendCertCompany(certDto.getSendCertCompany());
		}
		if (StringUtils.isNotEmpty(certDto.getNation())) {
			certInfo.setNation(certDto.getNation());
		}
		if (StringUtils.isNotEmpty(certDto.getDriverType())) {
			certInfo.setDriverType(certDto.getDriverType());
		}
		if (StringUtils.isNotEmpty(certDto.getDriverStartTime())) {
			certInfo.setDriverStartTime(certDto.getDriverStartTime());
		}
		if (StringUtils.isNotEmpty(certDto.getDiverEndTime())) {
			certInfo.setDiverEndTime(certDto.getDiverEndTime());
		}
		if (StringUtils.isNotEmpty(certDto.getFirstTakeTime())) {
			certInfo.setFirstTakeTime(certDto.getFirstTakeTime());
		}
		certEntity.setCertInfo(gson.toJson(certInfo));
		certEntity.setUpdateTime(DateUtil.getSqlTime());
		syspersonCertDao.update(certEntity);
	}

	@Override
	public List<CertVo> list(String personId) {
		List<CertVo> result = new ArrayList<>();
		List<SyspersonCertEntity> certList = syspersonCertDao.findByPerson(personId);
		if (certList != null && certList.size() > 0) {
			certList.forEach(p -> {
				Gson gson = new Gson();
				CertVo vo = gson.fromJson(p.getCertInfo(), CertVo.class);
				vo.setGuid(p.getGuid());
				vo.setCreateTime(DateUtil.dateToTimestamp(p.getCreateTime()));
				result.add(vo);
			});
		}
		return result;
	}

	@Override
	public CertVo detail(String guid) {
		SyspersonCertEntity certen = syspersonCertDao.findById(guid);
		if(certen == null){
		    throw new ServiceException(CertErrorEnums.CERT_ALREADY);
        }
		Gson gson = new Gson();
		CertVo vo = gson.fromJson(certen.getCertInfo(), CertVo.class);
		vo.setGuid(certen.getGuid());
		vo.setCreateTime(DateUtil.dateToTimestamp(certen.getCreateTime()));

		// 查询实名认证照片
		String personId = certen.getPersonId();
		SyspersonInfoEntity personInfo = syspersonInfoDao.findByPersonId(personId);
		vo.setIdcardHandPic(personInfo.getIdcardHandPic());
		return vo;
	}

	@Override
	public void del(String guid) {
		// 查询数据是否存在
		SyspersonCertEntity certen = syspersonCertDao.findById(guid);
		if (certen == null) {
			throw new ServiceException(CertErrorEnums.NOT_EXIT);
		}
		certen.setIsDel(1);
		certen.setUpdateTime(DateUtil.getSqlTime());
		// 删除数据
		syspersonCertDao.update(certen);
	}

	@Override
	public void sync(SyncCertDto syncCertDto) {

		
		SystemPersonEntity sysperson = personDao.select(syncCertDto.getPersonId());
		if(sysperson==null) {
			throw new ServiceException(CertErrorEnums.DRIVER_NOT_EXIT);
		}
		
		EntStarSoftEntity entStarSoft = entStarSoftDao.getBySoft(syncCertDto.getCompanyId());
		if (entStarSoft == null) {
			throw new ServiceException(CertErrorEnums.ENT_NOT_EXIT);
		}
		
		EnterpriseInfoEntity entInfo = enterpriseInfoDao.select(entStarSoft.getEntId());

		Gson gson = new Gson();

		if (syncCertDto.getOpType().equals(100) || syncCertDto.getOpType().equals(200)) {
			// 新增 or 更新

			SyspersonCertEntity certInfo = syspersonCertDao.findByStartsoftCertId(syncCertDto.getStarCertId());
			if (certInfo == null) {
				//新增
				SyspersonCertEntity certEntity = syspersonCertDao.findByType(syncCertDto.getPersonId(),
						syncCertDto.getCertType());
				if (certEntity != null) {
					throw new ServiceException(CertErrorEnums.CERT_EXIT);
				}

				certInfo = new SyspersonCertEntity();

				certInfo.setGuid(StringUtils.generateGUID());
				certInfo.setPersonId(syncCertDto.getPersonId());
				certInfo.setCertType(syncCertDto.getCertType());
				certInfo.setCertName(syncCertDto.getCertName());
				certInfo.setCertSource(syncCertDto.getSource());
				certInfo.setCertInfo(gson.toJson(syncCertDto));
				certInfo.setIsDel(0);
				certInfo.setCreateTime(DateUtil.getSqlTime());
				certInfo.setUpdateTime(DateUtil.getSqlTime());
				certInfo.setStartsoftCertId(syncCertDto.getStarCertId());
				syspersonCertDao.insert(certInfo);

				if (entInfo != null) {
					String pushUrl = ConstantUtil.certInfoPage + "certId=" + certInfo.getGuid();
					sendImPush(entInfo.getId(), certInfo.getPersonId(), MessageType.CERT.getValue(),
							MessageBizType.CERT_ADD, pushUrl, certInfo, entInfo.getEntTypeCode());
				}
			}else {
				//更新
				certInfo.setCertType(syncCertDto.getCertType());
				certInfo.setCertName(syncCertDto.getCertName());
				certInfo.setCertInfo(gson.toJson(syncCertDto));
				certInfo.setUpdateTime(DateUtil.getSqlTime());
				certInfo.setStartsoftCertId(syncCertDto.getStarCertId());
				certInfo.setIsDel(0);
				syspersonCertDao.update(certInfo);

				if (entInfo != null) {
					String pushUrl = ConstantUtil.certInfoPage + "certId=" + certInfo.getGuid();
					sendImPush(entInfo.getId(), certInfo.getPersonId(), MessageType.CERT.getValue(),
							MessageBizType.CERT_UPDATE, pushUrl, certInfo, entInfo.getEntTypeCode());
				}
			}
			

		}else if (syncCertDto.getOpType().equals(300)) {
			// 注销
			SyspersonCertEntity certInfo = syspersonCertDao.findByStartsoftCertId(syncCertDto.getStarCertId());
			if (certInfo == null) {
				throw new ServiceException(CertErrorEnums.NOT_EXIT);
			}
			
			certInfo.setIsDel(1);
			certInfo.setUpdateTime(DateUtil.getSqlTime());
			syspersonCertDao.update(certInfo);

			if (entInfo != null) {
				sendImPush(entInfo.getId(), certInfo.getPersonId(), MessageType.CERT.getValue(),
						MessageBizType.CERT_DEL, "", certInfo, entInfo.getEntTypeCode());
			}
		}
	}

	/**
	 * 发送IM推送 发送短信
	 *
	 * @param messageType
	 * @param messageBizType
	 */
	private void sendImPush(String entId, String personId, Integer messageType, MessageBizType messageBizType,
			String pushUrl, SyspersonCertEntity certInfo, String entType) {
		Gson gson = new Gson();
		ImModeDto imModeDto = new ImModeDto();

		SystemPersonEntity sysperson = personDao.select(personId);
		ImUserEntity imUser = imUserDao.getByEnt(entId);
		// 推送消息类容
		imModeDto.setType(messageType.toString());
		imModeDto.setTitle(messageBizType.getTitle());
		imModeDto.setContent(messageBizType.getContent());
		imModeDto.setFromUser(imUser.getImId());
		imModeDto.setUrl(pushUrl);
		List toUserList = new ArrayList();

		toUserList.add(sysperson.getLogitalkId());
		imModeDto.setToUser(toUserList);

		imModeDto.setSendTime(DateUtil.timestamp2Str(DateUtil.getSqlTime()));

		if ("government".equals(entType)) {
			imModeDto.setSendPrefix("100");
		} else if ("association".equals(entType)) {
			imModeDto.setSendPrefix("200");
		} else {
			imModeDto.setSendPrefix("300");
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		// 自定义内容
		CertExtraDto certExtraDto = new CertExtraDto();
		certExtraDto.setBizType(messageBizType.getType());
		certExtraDto.setCertId(certInfo.getGuid());
		certExtraDto.setCertName(certInfo.getCertName());
		certExtraDto.setSendPrefix(imModeDto.getSendPrefix());
		certExtraDto.setCertTime(sf.format(certInfo.getUpdateTime()));

		imModeDto.setExtra(gson.toJson(certExtraDto));

		// im 推送
		imMsgSender.sendModePush(imModeDto);

	}
}
