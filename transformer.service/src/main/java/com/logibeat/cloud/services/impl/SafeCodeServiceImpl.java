package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.PlateColor;
import com.logibeat.cloud.common.enumtype.PunishType;
import com.logibeat.cloud.common.vo.CarSafeCodeReasonVo;
import com.logibeat.cloud.common.vo.StudyVo;
import com.logibeat.cloud.vo.AssisInfoVo;
import com.logibeat.cloud.common.vo.ViolationVo;
import com.logibeat.cloud.core.dto.PunishTypeDto;
import com.logibeat.cloud.msg.sender.SafeSender;
import com.logibeat.cloud.msg.sender.StarSoftSender;
import com.logibeat.cloud.persistent.dao.EntStarSoftDao;
import com.logibeat.cloud.persistent.dao.EnterpriseCooperateCarDao;
import com.logibeat.cloud.persistent.entity.EntStarSoftEntity;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperateCarEntity;
import com.logibeat.cloud.services.SafeCodeService;
import com.logibeat.cloud.util.tools.DateUtils;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.cloud.vo.startsoft.StarSoft_AssisInfoVo;
import com.logibeat.cloud.vo.startsoft.StarSoft_CarSafeCodeReasonVo;
import com.logibeat.cloud.vo.startsoft.StarSoft_ViolationInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SafeCodeServiceImpl implements SafeCodeService {


    @Autowired
    private StarSoftSender starSoftSender;

    @Autowired
    private SafeSender safeSender;

//    @Autowired
//    private PersonViolationDao personViolationDao;

//    @Autowired
//    private PersonViolationService personViolationService;

    @Autowired
    private EntStarSoftDao entStarSoftDao;
    
    @Autowired
    private EnterpriseCooperateCarDao entCarDao;


    private static final String SAFE_CODE = "code";

    private static final String SAFE_MSG = "msg";

    private static final String SAFE_DATA = "data";

    private static final String SAFE_GUID = "guid";

    private static final String SAFE_SUCCESS = "200";


    /**
     * ?????????????????????
     *
     * @param personId
     * @return
     */
    @Override
    public JSONPrompt getSafeCode(String personId) {
        return starSoftSender.getSafeCode(personId);

    }
    
    /**
     * ?????????????????????????????????
     *
     * @param personId
     * @return
     */
    @Override
    public AssisInfoVo safeAssisInfo(String personId) {
        StarSoft_AssisInfoVo starSoft_resultVo = starSoftSender.getAssisInfoVO(personId);
        AssisInfoVo assisInfoVo = new AssisInfoVo();
        if (null != starSoft_resultVo) {

            if (null != starSoft_resultVo) {
                List<StarSoft_ViolationInfoVo> vioList = starSoft_resultVo.getViolations();
                if (null != vioList) {
                    List<ViolationVo> violationVoList = new ArrayList<>();
                    for (StarSoft_ViolationInfoVo violationInfoVo : vioList) {
                        ViolationVo violationVo = new ViolationVo();
                        violationVo.setGuid(violationInfoVo.getViolationId());
                        violationVo.setOrderNumber(violationInfoVo.getOrderNumber());
                        violationVo.setPunishTime(DateUtils.dateStrToDateTime(violationInfoVo.getPunishTime()));
                        violationVo.setViolationRemark(violationInfoVo.getPunishRemark());
                        violationVo.setPenaltyOrg(violationInfoVo.getPenaltyOrg());
                        
                        //?????????AI?????????????????????
                        if(violationInfoVo.getPunishTypeList()!=null && violationInfoVo.getPunishTypeList().size()==1) {
                        	PunishTypeDto punishTypeDto = violationInfoVo.getPunishTypeList().get(0);
                        	if(PunishType.AiStudy.getCode().equals(punishTypeDto.getCode()) || PunishType.Persuasion.getCode().equals(punishTypeDto.getCode()) ) {
                        		continue;
                        	}
                        }
                        
                        //????????????
                        List<String> punishTypeList = violationInfoVo.getPunishTypeList().parallelStream().map(PunishTypeDto::getCode).collect(Collectors.toList());
                        violationVo.setPunishTypeList(punishTypeList);

                        violationVoList.add(violationVo);
                        assisInfoVo.setViolationList(violationVoList);
                        //??????  ???????????????
//                        PersonViolationEntity existVio = personViolationDao.selectPersonViolationById(violationInfoVo.getViolationId());
//                        if (null == existVio) {
//                            String companyId = violationInfoVo.getCompanyId();
//                            EntStarSoftEntity starSoftEntity = entStarSoftDao.getBySoft(companyId);
//                            Gson gson = new Gson();
//                            SyncViolationDto syncViolationDto = gson.fromJson(gson.toJson(violationInfoVo), SyncViolationDto.class);
//                            if (null != syncViolationDto) {
//                                if (null != starSoftEntity) {
//                                    syncViolationDto.setCompanyId(starSoftEntity.getEntId());
//                                }
//                                syncViolationDto.setDataSource(StartSoftApiKeyType.GOV.getCode());
//                                syncViolationDto.setPenaltyOrg(violationInfoVo.getPenaltyOrg());
//                                personViolationService.create(syncViolationDto);
//                            }
//                        }
                    }
                }
                //assisInfoVo.setStudyList(starSoft_assisInfoVo.getSafeStudys());
                assisInfoVo.setOtherList(starSoft_resultVo.getOthers());
            }
        }
        return assisInfoVo;
    }


    /**
     * ??????????????????
     *
     * @param personId
     * @return
     */
    @Override
    public  List<StudyVo> getStudyList(String personId, Integer state, Integer pageIndex, Integer pageSize) {
    	if(pageIndex==null) {
    		pageIndex = 1;
    	}
    	if(pageSize==null) {
    		pageSize = 10;
    	}
        List<StudyVo> studyVoList = new ArrayList<>();
        Map<String,Object> resultMap = safeSender.getStudy(personId,state,pageIndex,pageSize);
        if (null != resultMap) {
            if (resultMap.get(SAFE_CODE).toString().equals(SAFE_SUCCESS)) {
                List<Map<String, Object>>  dataMapList = ( List<Map<String, Object>>) resultMap.get(SAFE_DATA);
                for(Map<String,Object> dataMap : dataMapList) {
                    if (null != dataMap) {
                        StudyVo studyVo = new StudyVo();
                        studyVo.setPlanId(null != dataMap.get("planId") ? dataMap.get("planId").toString() : "");
                        studyVo.setPlanName(null != dataMap.get("title") ? dataMap.get("title").toString() : "");
                        studyVo.setPenaltyOrg(null != dataMap.get("entName") ? dataMap.get("entName").toString() : "");
                        studyVo.setPlanTime(null != dataMap.get("dealTime") ? dataMap.get("dealTime").toString() : "");
                        studyVo.setPlanType(null != dataMap.get("type") ? dataMap.get("type").toString() : "");
                        studyVo.setCycleId(null != dataMap.get("cycleId") ? dataMap.get("cycleId").toString() : "");
                        studyVo.setStudyFinishTime(null != dataMap.get("finishTime") ? dataMap.get("finishTime").toString() : "");
                        studyVo.setClassName(null != dataMap.get("className") ? dataMap.get("className").toString() : "");
                        studyVo.setStatus(null != dataMap.get("status") ? dataMap.get("status").toString() : "");
                        studyVoList.add(studyVo);
                    }
                }
            }
        }
        return studyVoList;
    }
    
    
    /**
     * ?????????????????????
     * @return
     */
    @Override
    public JSONPrompt getCarSafeCode(String entCarId) {
    	
    	EnterpriseCooperateCarEntity entCar = entCarDao.select(entCarId);
    	if(entCar!=null) {
    		EntStarSoftEntity entStarSoft = entStarSoftDao.getByStar(entCar.getEntid());
        	if(entStarSoft!=null) {
        		return starSoftSender.getCarSafeCode(entCar.getPlateNumber(), PlateColor.getSelfByValue(entCar.getPlateColor()).getStarSoftCode(), entStarSoft.getStarsoftId());
        	}
    	}
    	
        return  new JSONPrompt();
    }
    
    /**
     * ???????????????????????????
     * @return
     */
    @Override
    public List<CarSafeCodeReasonVo> getCarSafeCodeReason(String entCarId) {
    	
    	EnterpriseCooperateCarEntity entCar = entCarDao.select(entCarId);
    	if(entCar!=null) {
    		EntStarSoftEntity entStarSoft = entStarSoftDao.getByStar(entCar.getEntid());
        	if(entStarSoft!=null) {
        		List<StarSoft_CarSafeCodeReasonVo> dataList = starSoftSender.getCarSafeCodeReason(entCar.getPlateNumber(), PlateColor.getSelfByValue(entCar.getPlateColor()).getStarSoftCode(), entStarSoft.getStarsoftId());
        		if(dataList!=null && dataList.size()>0) {
        			
        			List<CarSafeCodeReasonVo> resultList = new ArrayList<CarSafeCodeReasonVo>();
        			
        			 for (StarSoft_CarSafeCodeReasonVo carSafeCodeReasonVo : dataList) {
        				 CarSafeCodeReasonVo vo = new CarSafeCodeReasonVo();
        				 vo.setSafeCode(carSafeCodeReasonVo.getSafeCode());
        				 if(vo.getSafeCode()!=null) {
        					 if(vo.getSafeCode().equals(1)) {
        						 vo.setSafeCodeText("???????????????");
        					 }else if(vo.getSafeCode().equals(2)) {
        						 vo.setSafeCodeText("???????????????");
        					 }else if(vo.getSafeCode().equals(3)) {
        						 vo.setSafeCodeText("???????????????");
        					 }
        				 }
        				 
        				 vo.setReason(carSafeCodeReasonVo.getRedCodeType());
        				 if(vo.getReason()!=null) {
        					 if(vo.getReason().equals(1)) {
        						 vo.setReasonText("??????");
        					 }else if(vo.getReason().equals(2)) {
        						 vo.setReasonText("????????????");
        					 }else if(vo.getReason().equals(3)) {
        						 vo.setReasonText("???????????????");
        					 }
        				 }
        				 vo.setExplain(carSafeCodeReasonVo.getRemark());
        				 vo.setOrgSource(carSafeCodeReasonVo.getOrgName());
        				 vo.setDealTime(carSafeCodeReasonVo.getHandleTime());
        				 vo.setStartTime(carSafeCodeReasonVo.getStartTime());
        				 vo.setEndTime(carSafeCodeReasonVo.getEndTime());
        				 vo.setStopRunMeta(carSafeCodeReasonVo.getStopRunMeta());
        				 if(StringUtils.isNotBlank(vo.getStartTime()) && StringUtils.isNotBlank(vo.getEndTime())) {
        					 vo.setEffective(vo.getStartTime()+" ??? "+vo.getEndTime());
        				 }
        				 
        				 resultList.add(vo);
        			 }
        			 
        			 return resultList;
        		}
        	}
    	}
    	
    	return null;
    	
    }

}
