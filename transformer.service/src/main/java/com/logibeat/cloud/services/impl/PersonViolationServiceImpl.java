package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logibeat.cloud.common.enumtype.PunishType;
import com.logibeat.cloud.common.enumtype.SafePlanType;
import com.logibeat.cloud.common.enumtype.StartSoftApiKeyType;
import com.logibeat.cloud.common.enumtype.ViolationStatusType;
import com.logibeat.cloud.common.vo.AppealVo;
import com.logibeat.cloud.common.vo.ViolationVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.*;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.msg.dto.ImModeDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.PersonViolationExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.msg.sender.SafeSender;
import com.logibeat.cloud.msg.sender.StarSoftSender;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.services.PersonViolationService;
import com.logibeat.cloud.util.tools.DateUtils;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonViolationServiceImpl implements PersonViolationService {




    @Autowired
    private PersonViolationDao personViolationDao;

    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;

    @Autowired
    private SyspersonDao syspersonDao;

    @Autowired
    private PersonViolationInfoDao personViolationInfoDao;

    @Autowired
    private PersonViolationAppealDao personViolationAppealDao;


    @Autowired
    private ImMsgSender imMsgSender;


    private Gson gson = new Gson();


    @Autowired
    private ImUserDao imUserDao;

    @Autowired
    private StarSoftSender starSoftSender;


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    @Autowired
    private SafeSender safeSender;
    
    @Autowired
	private TrafficAdviseDao trafficAdviseDao;
    
    @Autowired
    private EntStarSoftDao entStarSoftDao;


    private static final String SAFE_CODE="code";

    private static final String SAFE_MSG="msg";

    private static final String SAFE_DATA="data";

    private static final String SAFE_GUID="guid";

    private static final String SAFE_SUCCESS="200";


    /**
     * ??????????????????
     * @param syncViolationDto
     */
    @Override
    public void create(SyncViolationDto syncViolationDto){
        String violationId = syncViolationDto.getViolationId();
        String entId = syncViolationDto.getCompanyId();
        boolean pushIm = false;
        if(StringUtils.isBlank(entId)){
            throw  new ServiceException(EnterpriseErrorEnums.ENT_FIND_FAILURE);
        }
        EnterpriseInfoEntity ent = enterpriseInfoDao.select(entId);
        if(null == ent){
            throw  new ServiceException(EnterpriseErrorEnums.ENT_FIND_FAILURE);
        }
        String personId = syncViolationDto.getDriverId();
        if(StringUtils.isBlank(personId)){
            throw  new ServiceException(EnterpriseErrorEnums.USER_IS_NOT_NULL);
        }
        SystemPersonEntity person = syspersonDao.select(personId);
        if(null == person){
            throw  new ServiceException(EnterpriseErrorEnums.USER_IS_NOT_NULL);
        }
        
        EnterpriseInfoEntity topEnt = null;
        if(StringUtils.isNotBlank(syncViolationDto.getPenaltyOrgId())) {
        	EntStarSoftEntity starInfo = entStarSoftDao.getBySoft(syncViolationDto.getPenaltyOrgId());
        	if(starInfo!=null) {
        		entId = starInfo.getEntId();
        		topEnt = enterpriseInfoDao.select(entId);
        	}
        }
        
        if(topEnt == null) {
        	topEnt = ent;
        }
        
        PersonViolationEntity existPersonViolation =  personViolationDao.selectPersonViolationById(violationId);
        PersonViolationEntity  personViolation = null;
        if(null == existPersonViolation){
            personViolation = new PersonViolationEntity();
            personViolation.setGuid(violationId);
            personViolation.setOrderNumber(syncViolationDto.getOrderNumber());
            personViolation.setCreateTime(DateUtil.getSqlTime());
            personViolation.setConfirm(ConstantUtil.INTEGER_CODE_ZERO);
            personViolation.setAppeal(ConstantUtil.INTEGER_CODE_ONE);
            personViolation.setScan(ConstantUtil.INTEGER_CODE_ZERO);
            pushIm = true;
        }
        else{
            personViolation = existPersonViolation;
            //??????????????????
            personViolationInfoDao.deleteByViolationId(violationId);
        }
        personViolation.setEntId(syncViolationDto.getCompanyId());
        personViolation.setEntName(ent.getName());
        personViolation.setPersonId(personId);
        personViolation.setPersonName(person.getMyName());
        personViolation.setPersonPhone(person.getLoginName());
        personViolation.setPunishPersonName(syncViolationDto.getPunishPersonName());
        personViolation.setViolationSource(syncViolationDto.getViolationSource());
        personViolation.setViolationRemark(syncViolationDto.getViolationRemark());
        personViolation.setViolationTime(DateUtil.dateStrToDate(syncViolationDto.getViolationTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        personViolation.setPunishStatus(syncViolationDto.getPunishStatus());
        personViolation.setPunishTime(DateUtil.dateStrToDate(syncViolationDto.getPunishTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        personViolation.setPenaltyOrg(syncViolationDto.getPenaltyOrg());
        personViolation.setDataSource(syncViolationDto.getDataSource());
        if(StringUtils.isBlank(syncViolationDto.getPenaltyOrg())){
            personViolation.setPenaltyOrg(ent.getName());
        }
        //????????????
        List<PunishTypeDto> punishTypeDtoList = syncViolationDto.getPunishTypeList();
        if(null != punishTypeDtoList && punishTypeDtoList.size()>0){
            for(PunishTypeDto punishTypeDto : punishTypeDtoList){
                String punishTypeCode = punishTypeDto.getCode();
                //????????????????????? ??????????????? ?????????????????????/??????????????????????????????????????????
                if(PunishType.CanJiaXueXi.getCode().equals(punishTypeCode) || PunishType.CanJiaKaoShi.getCode().equals(punishTypeCode)){
//                    Integer taskType = PunishType.CanJiaXueXi.getCode().equals(punishTypeCode) ? 100 : 200;
                    pushPlanTaskToDriver(person.getLoginName(),entId,punishTypeDto.getData(),null,personViolation.getGuid(),personId,100,syncViolationDto.getIsSafeCode());
                }
                //AI??????
                if(PunishType.AiStudy.getCode().equals(punishTypeCode)){
                    pushPlanTaskToDriver(person.getLoginName(),entId,punishTypeDto.getData(),null,personViolation.getGuid(),personId,400,syncViolationDto.getIsSafeCode());
                }
                //????????????
                if(PunishType.Persuasion.getCode().equals(punishTypeCode)){
                	TrafficAdviseEntity adviseInfo = new TrafficAdviseEntity();
                	adviseInfo.setGuid(RandomTool.getGUId());
                	adviseInfo.setEntId(topEnt.getId());
                	adviseInfo.setEntName(topEnt.getName());
                	adviseInfo.setPersonId(personId);
                	adviseInfo.setStatus(0);
                	adviseInfo.setBeginTime(DateUtil.dateStrToDate(punishTypeDto.getPersuasionBeginTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
                	adviseInfo.setEndTime(DateUtil.dateStrToDate(punishTypeDto.getPersuasionEndTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
                	adviseInfo.setDuration(Integer.parseInt(punishTypeDto.getData()));
                	adviseInfo.setIntervalTime(punishTypeDto.getPersuasionSignInterval());
                	adviseInfo.setLng(convertGps(punishTypeDto.getLongitude()));
                	adviseInfo.setLat(convertGps(punishTypeDto.getLatitude()));
                	adviseInfo.setScope(punishTypeDto.getRange());
                	adviseInfo.setAddress(punishTypeDto.getAddress());
                	adviseInfo.setRelationInfo(violationId);
                	adviseInfo.setRelationSafeCode(syncViolationDto.getIsSafeCode());
                	adviseInfo.setCreateTime(DateUtils.now());
                	adviseInfo.setUpdateTime(DateUtils.now());
                	adviseInfo.setPlaceName(punishTypeDto.getPlaceName());
                	adviseInfo.setIsDel(0);
                	trafficAdviseDao.insertTrafficAdvise(adviseInfo);
                }
            }
            personViolation.setPunishType(gson.toJson(punishTypeDtoList));

        }
        personViolation.setPunishRemark(syncViolationDto.getPunishRemark());
        personViolation.setUpdateTime(DateUtil.getSqlTime());
        if(null == existPersonViolation){
            personViolationDao.insertPersonViolation(personViolation);
        }
        else{
            personViolationDao.updatePersonViolation(personViolation);
        }
        //????????????
        List<ViolationInfoDto> violationInfoDtoList = syncViolationDto.getViolationInfoList();

        if(null != violationInfoDtoList){
            for(ViolationInfoDto violationInfoDto : violationInfoDtoList){
                PersonViolationInfoEntity personViolationInfoEntity = new PersonViolationInfoEntity();
                personViolationInfoEntity.setType(violationInfoDto.getType());
                personViolationInfoEntity.setValue(violationInfoDto.getValue());
                personViolationInfoEntity.setGuid(RandomTool.getGUId());
                personViolationInfoEntity.setNumber(violationInfoDto.getNumber());
                personViolationInfoEntity.setViolationGuid(violationId);
                //????????????
                List<ViolationDetail> violationDetailList  = violationInfoDto.getViolationDetailList();
                if(null != violationDetailList && violationDetailList.size()>0){
                    String detail = gson.toJson(violationDetailList);
                    personViolationInfoEntity.setDetail(detail);
                }
                personViolationInfoDao.insertPersonViolationInfo(personViolationInfoEntity);

            }
        }


        //????????????????????????  ?????????V8
        if(pushIm && StartSoftApiKeyType.V8.getCode().equals(syncViolationDto.getDataSource())){
            sendIm(personViolation,person);
        }

    }
    
    private double convertGps(int targetNum) {
    	double num = Double.valueOf(targetNum)/Double.valueOf(3600000);
    	BigDecimal b = new BigDecimal(num);  
    	double ret = b.setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
    	return ret;
	}
    
    /**
     * ????????????
     * @param
     * @param
     * @param
     */
    @Override
    public  void updateStatus(UpdateViolationStatusDto updateViolationStatusDto){
        PersonViolationEntity personViolation = personViolationDao.selectPersonViolationById(updateViolationStatusDto.getViolationId());
        if(null != personViolation){
            personViolation.setPunishStatus(updateViolationStatusDto.getStatus());
            personViolation.setCancelReason(updateViolationStatusDto.getCancelReason());
            personViolation.setUpdateTime(DateUtil.getSqlTime());
            personViolationDao.updatePersonViolation(personViolation);
            
            if(updateViolationStatusDto.getStatus().equals(300)) {
            	trafficAdviseDao.updateByRelationInfo(updateViolationStatusDto.getViolationId());
            	taskExecutor.execute(() -> safeSender.delTask(updateViolationStatusDto.getViolationId()));
            }

            //???????????????????????????
            if(StringUtils.isNotBlank(updateViolationStatusDto.getAppealId())){
                PersonViolationAppealEntity appealEntity = personViolationAppealDao.selectPersonViolationAppealById(updateViolationStatusDto.getAppealId());
                if(null != appealEntity){
                    appealEntity.setStatus(updateViolationStatusDto.getAppealStatus());
                    appealEntity.setRefuseReason(updateViolationStatusDto.getCancelReason());
                    personViolationAppealDao.updatePersonViolationAppeal(appealEntity);
                }
            }
            //??????IM????????????
            SystemPersonEntity person = syspersonDao.select(personViolation.getPersonId());

            if(StartSoftApiKeyType.V8.getCode().equals(personViolation.getDataSource())){
                sendIm(personViolation,person);

            }

        }
    }


    /**
     * ????????????????????????
     * @param personViolationEntity
     */
    private void sendIm(PersonViolationEntity personViolationEntity,SystemPersonEntity person){
        ImModeDto imModeDto = new ImModeDto();
        String linkUrl = ConstantUtil.violationPage+"entId="+personViolationEntity.getEntId()+"&guid="+personViolationEntity.getGuid();
        imModeDto.setTitle(MessageBizType.PERSON_VIOLATION.getTitle());
        imModeDto.setContent(MessageBizType.PERSON_VIOLATION.getContent());
        imModeDto.setUrl(linkUrl);
        List<String> toUserList = new ArrayList<>();
        toUserList.add(person.getLogitalkId());
        imModeDto.setToUser(toUserList);
        imModeDto.setSendTime(DateUtil.timestamp2Str(DateUtil.getSqlTime()));
        imModeDto.setType(MessageType.VIOLATION.getValue().toString());
        ImUserEntity imUser = imUserDao.getByEnt(personViolationEntity.getEntId());
        if(null != imUser){
            imModeDto.setFromUser(imUser.getImId());
        }
        //???????????????
        PersonViolationExtraDto personViolationExtraDto = new PersonViolationExtraDto();
        personViolationExtraDto.setStatus(ViolationStatusType.find(personViolationEntity.getPunishStatus()).getDescription());
        personViolationExtraDto.setType(dealPunishType(personViolationEntity.getPunishType()));
        imModeDto.setExtra(gson.toJson(personViolationExtraDto));
        if(toUserList.size()>0) {
            //im??????
            imMsgSender.sendModePush(imModeDto);
        }
    }




    /**
     * ????????????
     * @param entId
     * @param personId
     * @param status
     * @return
     */
    @Override
    public List<ViolationVo> getList(String entId, String personId, Integer status, Page page){
        List<ViolationVo> resultList = new ArrayList<>();
        PageHelper.startPage(page.getPageIndex(),page.getPageSize());
        PersonViolationEntity personViolationParam = new PersonViolationEntity();
        personViolationParam.setEntId(entId);
        personViolationParam.setPersonId(personId);
        personViolationParam.setPunishStatus(status);
        personViolationParam.setDataSource(StartSoftApiKeyType.V8.getCode());
        List<PersonViolationEntity> personViolationList = personViolationDao.selectPersonViolationList(personViolationParam);
        for(PersonViolationEntity personViolation : personViolationList){
            ViolationVo violationVo = new ViolationVo();
            violationVo.setGuid(personViolation.getGuid());
            //????????????
            String typeJson = personViolation.getPunishType();
            violationVo.setType(dealPunishType(typeJson));
            violationVo.setStatus(personViolation.getPunishStatus());
            violationVo.setTime(personViolation.getCreateTime());
            violationVo.setPunishTime(personViolation.getPunishTime());
            violationVo.setOrderNumber(personViolation.getOrderNumber());
            violationVo.setConfirm(personViolation.getConfirm());
            resultList.add(violationVo);
        }
        return resultList;
    }


    /**
     * ??????
     * @param entId
     * @param personId
     * @param status
     * @return
     */
    @Override
    public Integer getInCount(String entId, String personId, Integer status){
        PersonViolationEntity personViolationParam = new PersonViolationEntity();
        personViolationParam.setEntId(entId);
        personViolationParam.setPersonId(personId);
        personViolationParam.setPunishStatus(status);
        personViolationParam.setDataSource(StartSoftApiKeyType.V8.getCode());
        List<PersonViolationEntity> personViolationList = personViolationDao.selectPersonViolationList(personViolationParam);
        return  personViolationList.size();
    }




    /**
     * ??????
     * @param violationId
     * @return
     */
    @Override
    public ViolationVo detail(String violationId){
        ViolationVo violationVo = new ViolationVo();
        PersonViolationEntity personViolation = personViolationDao.selectPersonViolationById(violationId);
        if(null != personViolation){
            violationVo.setGuid(personViolation.getGuid());
            violationVo.setStatus(personViolation.getPunishStatus());
            violationVo.setTime(personViolation.getCreateTime());
            violationVo.setPunishPersonName(personViolation.getPunishPersonName());
            violationVo.setRemark(personViolation.getPunishRemark());
            violationVo.setEntName(personViolation.getEntName());
            violationVo.setConfirm(personViolation.getConfirm());
            violationVo.setPunishTime(personViolation.getCreateTime());
            violationVo.setOrderNumber(personViolation.getOrderNumber());
            violationVo.setConfirm(personViolation.getConfirm());
            violationVo.setViolationTime(personViolation.getViolationTime());
            violationVo.setViolationRemark(personViolation.getViolationRemark());
            violationVo.setSource(personViolation.getViolationSource());
            violationVo.setPenaltyOrg(personViolation.getPenaltyOrg());
            //????????????
            String typeJson = personViolation.getPunishType();
            if(StringUtils.isNotBlank(typeJson)) {
                violationVo.setType(dealPunishType(typeJson));
                List<PunishTypeDto> punishTypeDtoList = gson.fromJson(typeJson, new TypeToken<List<PunishTypeDto>>() {}.getType());
                String typeValue = punishTypeDtoList.stream().map( PunishTypeDto :: getCode).collect(Collectors.joining(";"));
                //?????????????????????????????? ????????????id?????????
                if(typeValue.contains(PunishType.CanJiaXueXi.getCode()) || typeValue.contains(PunishType.CanJiaKaoShi.getCode()) ){
                    Map<String,Object> resultMap = safeSender.queryPlanByViolationId(violationId,"200");
                    if(null != resultMap){
                        if(resultMap.get(SAFE_CODE).toString().equals(SAFE_SUCCESS)){
                            Map<String,Object> dataMap = (Map<String,Object>)resultMap.get(SAFE_DATA);
                            if(null != dataMap){
                                violationVo.setDataId(dataMap.get("planTaskId").toString());
                                violationVo.setDataType(dataMap.get("type").toString());
                            }
                        }
                    }
                }
                violationVo.setTypeValue(typeValue);

            }
            //????????????
            PersonViolationInfoEntity personViolationInfoParam = new PersonViolationInfoEntity();
            personViolationInfoParam.setViolationGuid(violationId);
            List<PersonViolationInfoEntity> personViolationInfoList =  personViolationInfoDao.selectPersonViolationInfoList(personViolationInfoParam);
            List<ViolationInfoDto> violationInfoList = new ArrayList<>();
            for(PersonViolationInfoEntity personViolationInfoEntity : personViolationInfoList){
                ViolationInfoDto violationInfoDto = new ViolationInfoDto();
                violationInfoDto.setType(personViolationInfoEntity.getType());
                violationInfoDto.setValue(personViolationInfoEntity.getValue());
                violationInfoDto.setNumber(personViolationInfoEntity.getNumber());
                //??????
                if(StringUtils.isNotBlank(personViolationInfoEntity.getDetail())){
                    List<ViolationDetail> violationDetailList = gson.fromJson(personViolationInfoEntity.getDetail(),new TypeToken<List<ViolationDetail>>(){}.getType());

                    List<ViolationDetail> resultList  = new ArrayList<>();
                    for(ViolationDetail violationDetail : violationDetailList){
                        List<FileDto> proffList = violationDetail.getProffList();

                        if(null != proffList && !proffList.isEmpty() && proffList.size()>0){
                            violationDetail.setProffPicList(proffList.parallelStream().filter(p -> p.getFileType().equals(0)).collect(Collectors.toList()));
                            violationDetail.setProffVedioList(proffList.parallelStream().filter(p -> p.getFileType().equals(2)).collect(Collectors.toList()));
                        }
                        resultList.add(violationDetail);
                    }
                    violationInfoDto.setViolationDetailList(resultList);
                }
                violationInfoList.add(violationInfoDto);
            }
            violationVo.setViolationInfoList(violationInfoList);

            //???????????????????????????????????????
            if(personViolation.getScan().equals(ConstantUtil.INTEGER_CODE_ZERO)){
                personViolation.setScan(ConstantUtil.INTEGER_CODE_ONE);
                personViolationDao.updatePersonViolation(personViolation);
                //???????????????
                for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
                    taskExecutor.execute(() -> starSoftSender.scan(violationId,startSoftApiKeyType));
                }
            }
        }

        return violationVo;
    }





    /**
     * ????????????
     * @param typeJson
     * @return
     */
    private String dealPunishType(String typeJson){
        StringBuffer punishTypeBuffer = new StringBuffer();
        if(StringUtils.isNotBlank(typeJson)){
            List<PunishTypeDto> punishTypeDtoList = gson.fromJson(typeJson,new TypeToken<List<PunishTypeDto>>(){}.getType());
            for(PunishTypeDto punishTypeDto : punishTypeDtoList){
                punishTypeBuffer.append(punishTypeDto.getValue());
                String data = punishTypeDto.getData();
                //??????????????? ??????
                if(StringUtils.isNotBlank(data) && StringUtils.isNotBlank(punishTypeDto.getCode())){
                    if(PunishType.FaKuan.getCode().equals(punishTypeDto.getCode())){
                        data+="???";
                        punishTypeBuffer.append(data);
                    }
                    else if(PunishType.KouFen.getCode().equals(punishTypeDto.getCode())){
                        data+="???";
                        punishTypeBuffer.append(data);

                    }
                }
                punishTypeBuffer.append(";");
            }
        }
        return punishTypeBuffer.toString();
    }






    /**
     * ??????
     * @param appealDto
     */
    @Override
    public void appeal(AppealDto appealDto){
        PersonViolationEntity personViolation = personViolationDao.selectPersonViolationById(appealDto.getViolationId());
        if(null != personViolation){
            personViolation.setUpdateTime(DateUtil.getSqlTime());
            personViolation.setAppeal(ConstantUtil.INTEGER_CODE_ZERO);
            personViolation.setPunishStatus(ViolationStatusType.SHENSHU.getValue());
            personViolationDao.updatePersonViolation(personViolation);

            //????????????
            PersonViolationAppealEntity personViolationAppealEntity = new PersonViolationAppealEntity();
            personViolationAppealEntity.setGuid(RandomTool.getGUId());
            personViolationAppealEntity.setViolationId(appealDto.getViolationId());
            personViolationAppealEntity.setContent(appealDto.getContent());
            personViolationAppealEntity.setPicUrls(gson.toJson(appealDto.getPicList()));
            personViolationAppealEntity.setCreateTime(DateUtil.getSqlTime());
            personViolationAppealEntity.setStatus(ViolationStatusType.SHENSHU.getValue());
            personViolationAppealDao.insertPersonViolationAppeal(personViolationAppealEntity);


            //???????????????
            for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
                taskExecutor.execute(() -> starSoftSender.appeal(appealDto,personViolation.getPersonId(),personViolationAppealEntity.getGuid(),startSoftApiKeyType));
            }

        }
    }


    /**
     * ?????????????????????????????? ????????????/??????
     * @param personPhone
     * @param entId
     * @param dataId
     */
    public AddSafePlanTaskDto pushPlanTaskToDriver(String personPhone,String entId,String dataId,
                                                   Integer taskType,String violationId,String violationDriverId,Integer studyType,Integer relationSafeCode){
        AddSafePlanTaskDto addSafePlanTaskDto = new AddSafePlanTaskDto();
        addSafePlanTaskDto.setEntId(entId);
        addSafePlanTaskDto.setTaskType(taskType);
        addSafePlanTaskDto.setPersonPhone(personPhone);
        addSafePlanTaskDto.setDataId(dataId);
        addSafePlanTaskDto.setStartTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        addSafePlanTaskDto.setEndTime(DateUtil.LAST_TIME);
        addSafePlanTaskDto.setSafeRelationId(violationId);
        addSafePlanTaskDto.setViolationId(violationId);
        addSafePlanTaskDto.setViolationDriverId(violationDriverId);
        addSafePlanTaskDto.setBizType(SafePlanType.BIZTYPE_XRWG.getValue());
        addSafePlanTaskDto.setStudyType(studyType);
        addSafePlanTaskDto.setRelationSafeCode(relationSafeCode);
        //??????????????????????????????
        safeSender.pushPlanTaskToDriver(addSafePlanTaskDto);
//        taskExecutor.execute(() -> safeSender.pushPlanTaskToDriver(addSafePlanTaskDto));
        return addSafePlanTaskDto;
    }


    /**
     * ????????????
     * @param violationId
     * @return
     */
    @Override
    public List<AppealVo> getAppeal(String violationId){
        PersonViolationAppealEntity appealParam = new PersonViolationAppealEntity();
        appealParam.setViolationId(violationId);
        List<PersonViolationAppealEntity> resultList = personViolationAppealDao.selectPersonViolationAppealList(appealParam);
        List<AppealVo> appealVoList = new ArrayList<>();
        for(PersonViolationAppealEntity appealEntity : resultList){
            AppealVo appealVo = new AppealVo();
            appealVo.setContent(appealEntity.getContent());
            appealVo.setCreateTime(appealEntity.getCreateTime());
            appealVo.setStatus(appealEntity.getStatus());
            appealVo.setRefuseReason(appealEntity.getRefuseReason());
            appealVo.setStatusValue(ViolationStatusType.find(appealEntity.getStatus()).getDescription());
            List<String> picUrlList = gson.fromJson(appealEntity.getPicUrls(),new TypeToken<List<String>>(){}.getType());
            appealVo.setPicUrlList(picUrlList);
            appealVoList.add(appealVo);
        }
        return appealVoList;
    }




    /**
     * ??????
     * @param violationId
     */
    @Override
    public void confirm(String violationId){
        PersonViolationEntity personViolation = personViolationDao.selectPersonViolationById(violationId);
        if(null != personViolation){
            personViolation.setConfirm(ConstantUtil.INTEGER_CODE_ONE);
            personViolation.setUpdateTime(DateUtil.getSqlTime());
            personViolationDao.updatePersonViolation(personViolation);

            //???????????????
            for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
                taskExecutor.execute(() -> starSoftSender.sureViolation(personViolation.getPersonId(),personViolation.getGuid(),startSoftApiKeyType));
            }

        }
    }


}
