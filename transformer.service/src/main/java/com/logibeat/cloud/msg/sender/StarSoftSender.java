package com.logibeat.cloud.msg.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.common.enumtype.StarSoftApiType;
import com.logibeat.cloud.common.enumtype.StartSoftApiKeyType;
import com.logibeat.cloud.common.starsoft.StarSoft_DriverScoreRecordVo;
import com.logibeat.cloud.common.starsoft.StarSoft_DriverScoreVo;
import com.logibeat.cloud.common.starsoft.StarSoft_SafeScoreSetVo;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.constant.SenderConstantUtil;
import com.logibeat.cloud.core.dto.AppealDto;
import com.logibeat.cloud.core.tools.StarsoftHttpUtil;
import com.logibeat.cloud.dto.push.StarAdviseClockDto;
import com.logibeat.cloud.dto.push.StarPushDto;
import com.logibeat.cloud.mq.producer.StarPushProducer;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.cloud.vo.startsoft.StarSoft_AssisInfoVo;
import com.logibeat.cloud.vo.startsoft.StarSoft_CarSafeCodeReasonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class StarSoftSender {


    private static final Logger logger = LoggerFactory.getLogger(StarSoftSender.class);


    @Autowired
    private StarPushProducer starPushProducer;

    @Autowired
    private SysSettingDao sysSettingDao;



    /**
     * 司机确认工单
     * @param personId
     * @param violationId
     */
    public void  sureViolation(String personId,String violationId,StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("ViolationGUID",violationId);
        paramJson.put("DriverGUID",personId);
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        String postInfo = JSON.toJSONString(paramJson);

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.SUREVIOLATION.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.SUREVIOLATION.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
		
        try {
            logger.info("司机确认工单发送消息 , Param={}",postInfo);
            starPushProducer.send(JSON.toJSONString(starPushDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 司机申诉
     * @param appealDto
     */
    public void  appeal(AppealDto appealDto, String personId, String apperalId, StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("ViolationGUID",appealDto.getViolationId());
        paramJson.put("DriverGUID",personId);
        paramJson.put("Reason",appealDto.getContent());
        paramJson.put("AttachList",appealDto.getPicList());
        paramJson.put("AppealID",apperalId);
        String postInfo = JSON.toJSONString(paramJson);;

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.APPEAL.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.APPEAL.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        try {
            logger.info("司机申诉发送消息 , Param={}",postInfo);
            starPushProducer.send(JSON.toJSONString(starPushDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 司机浏览
     * @param violationId
     */
    public void scan(String violationId,StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("ViolationGUID",violationId);
        String postInfo = JSON.toJSONString(paramJson);

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.SCAN.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.SCAN.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
         try {
             logger.info("司机浏览发送消息 , Param={}",postInfo);
             starPushProducer.send(JSON.toJSONString(starPushDto));
         }
         catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 司机同意加入企业
     * @param confirmGuid
     * @param status
     * @param startSoftApiKeyType
     */
    public void  driverJoinEnt(String confirmGuid,Integer status,StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("ConfirmGUID",confirmGuid);
        paramJson.put("Status",status);
        String postInfo = JSON.toJSONString(paramJson);

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.DRIVERJOINENT.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.DRIVERJOINENT.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

         try {
             logger.info("司机同意加入企业发送消息 , Param={}",postInfo);
             starPushProducer.send(JSON.toJSONString(starPushDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    /**
     * 获取司机安全分
     * @param personId
     */
    public StarSoft_DriverScoreVo getDriverScore(String personId, String starsoftId){
               JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("DriverGUID",personId);
        paramJson.put("CompanyID",starsoftId);
        String postInfo = JSON.toJSONString(paramJson);


        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.GETDRIVERSCORE.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.V8.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.GETDRIVERSCORE.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        //请求地址
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_DRIVER_SCORE;
        try {
             String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
             JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
             StarSoft_DriverScoreVo starSoft_resultVo = JsonMapper.buildNonDefaultMapper().
                     fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),StarSoft_DriverScoreVo.class);
             return starSoft_resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取司机安全分流水
     * @param personId
     * @return
     */
    public List<StarSoft_DriverScoreRecordVo> getDriverScoreRecord(String personId, String startTime,
                                                                   String endTime, String starsoftId){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("DriverGUID",personId);
        paramJson.put("CompanyID",starsoftId);
        paramJson.put("FromTime",startTime);
        paramJson.put("ToTime",endTime);
        String postInfo = JSON.toJSONString(paramJson);

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.GETDRIVERSCORERECORD.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.V8.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.GETDRIVERSCORERECORD.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        //请求地址
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_DRIVER_SCORE_RECORD;

        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            Type type = new TypeToken<List<StarSoft_DriverScoreRecordVo>>(){}.getType();
            List<StarSoft_DriverScoreRecordVo> starSoft_resultVo = new Gson().fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),type);
            return starSoft_resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 获取安全分设置
     * @param companyId
     */
    public StarSoft_SafeScoreSetVo getSafeScoreSet(String companyId){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("CompanyID",companyId);
        String postInfo = JSON.toJSONString(paramJson);


        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.GETSAFESCORESET.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.V8.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.GETSAFESCORESET.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        //请求地址
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_DRIVER_SCORE__SET;


        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            StarSoft_SafeScoreSetVo starSoft_resultVo = JsonMapper.buildNonDefaultMapper().
                    fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),StarSoft_SafeScoreSetVo.class);
            return starSoft_resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取司机安全码
     * @param personId
     * @return
     */

    public JSONPrompt getSafeCode(String personId){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("DriverGUID",personId);
        String postInfo = JSON.toJSONString(paramJson);
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_SAFE_CODE;

        //拼装参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setCallParam(postInfo);
        starPushDto.setUrl(StarSoftApiType.GETSAFECODE.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.GOV.getCode().toString());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            return jsonPrompt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *安全码详情
     * @param personId
     * @return
     */
    public StarSoft_AssisInfoVo getAssisInfoVO(String personId){

        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("DriverGUID",personId);
        String postInfo = JSON.toJSONString(paramJson);;
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_ASSIS_INFO;

        //拼装参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setCallParam(postInfo);
        starPushDto.setUrl(StarSoftApiType.GETASSISINFO.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.GOV.getCode().toString());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
		
        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            Type type = new TypeToken<StarSoft_AssisInfoVo>(){}.getType();
            StarSoft_AssisInfoVo starSoft_resultVo = new Gson().fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),type);
            return starSoft_resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
    
    /**
     * 获取车辆安全码
     * @return
     */
    public JSONPrompt getCarSafeCode(String plateNumber,Integer plateColor,String entId){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("CarBrand",plateNumber);
        paramJson.put("CarBrandColor",plateColor);
        paramJson.put("CompanyID",entId);
        String postInfo = JSON.toJSONString(paramJson);
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_CAR_SAFE_CODE;

        //拼装参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setCallParam(postInfo);
        starPushDto.setUrl(StarSoftApiType.GET_CAR_SAFE_CODE.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.GOV.getCode().toString());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            return jsonPrompt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     *安全码详情
     * @param personId
     * @return
     */
    public List<StarSoft_CarSafeCodeReasonVo> getCarSafeCodeReason(String plateNumber, Integer plateColor, String entId){

        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("CarBrand",plateNumber);
        paramJson.put("CarBrandColor",plateColor);
        paramJson.put("CompanyID",entId);
        paramJson.put("PageSize",5);//查询记录数Default:5 最大:100
        String postInfo = JSON.toJSONString(paramJson);;
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.OPENAPI_URL);
        String postUrl = rootUrl+SenderConstantUtil.GET_CAR_SAFE_CODE_REASON;

        //拼装参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setCallParam(postInfo);
        starPushDto.setUrl(StarSoftApiType.GET_CAR_SAFE_CODE_REASON.getUrl());
        starPushDto.setPushPlatform(StartSoftApiKeyType.GOV.getCode().toString());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
		
        try {
            String result =   StarsoftHttpUtil.post(postUrl, JSON.toJSONString(starPushDto));
            JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
            Type type = new TypeToken<List<StarSoft_CarSafeCodeReasonVo>>(){}.getType();
            List<StarSoft_CarSafeCodeReasonVo> dataList = new Gson().fromJson(JsonMapper.toNonNullJson(jsonPrompt.getData()),type);
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
    
    /**
     * 违规劝导信息反馈
     */
    public void  violationPersuasion(String violationId, Integer finishStatus, List<StarAdviseClockDto> clockList, StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("ViolationGUID",violationId);//违规GUID
        paramJson.put("State",finishStatus);//完成状态 1已完成 0未完成
        if(finishStatus.equals(1)) {
        	paramJson.put("FinishTime",DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));//完成时间(未完成不需要时间)
            paramJson.put("AttachList",clockList);//劝导信息
        }
        
        Gson gson = new Gson();
        String postInfo = gson.toJson(paramJson);;

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.VIOLATION_PERSUASION.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.VIOLATION_PERSUASION.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        try {
            logger.info("违规劝导信息反馈 , Param={}",postInfo);
            starPushProducer.send(JSON.toJSONString(starPushDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 违规劝导回执单
     */
    public void  ViolationReceipt(String violationId,String receiptPicUrl, StartSoftApiKeyType startSoftApiKeyType){
        JSONObject paramJson = new JSONObject();
        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
        paramJson.put("ViolationGUID",violationId);//违规GUID
        paramJson.put("ReceiptPath",receiptPicUrl);//回执单图片地址
        paramJson.put("ReceiptTime",DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));//回执单时间
        
        Gson gson = new Gson();
        String postInfo = gson.toJson(paramJson);;

        //拼装消息参数
        StarPushDto starPushDto = new StarPushDto();
        starPushDto.setUrl(StarSoftApiType.VIOLATION_RECEIPT.getUrl());
        starPushDto.setPushPlatform(startSoftApiKeyType.getCode().toString());
        starPushDto.setCallParam(postInfo);
        starPushDto.setPushType(StarSoftApiType.VIOLATION_RECEIPT.getType());
        starPushDto.setKeyId(RandomTool.getGUId());
		starPushDto.setTime(DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));

        try {
            logger.info("违规劝导回执单 , Param={}",postInfo);
            starPushProducer.send(JSON.toJSONString(starPushDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
