package com.logibeat.cloud.msg.sender;

import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.core.constant.SenderConstantUtil;
import com.logibeat.cloud.core.dto.AddSafePlanTaskDto;
import com.logibeat.cloud.core.tools.StarsoftHttpUtil;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import com.logibeat.cloud.util.tools.properties.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SafeSender {


    private static final Logger logger = LoggerFactory.getLogger(SafeSender.class);


    private static String pushPlanTaskUrl = PropertiesUtil.getStringByKey("push.safe.task.url",
            PropertiesUtil.PropertiesConfig.QTZ.getPropertyName());


    private static String queryPlanTaskUrl = PropertiesUtil.getStringByKey("query.safe.task.url",
            PropertiesUtil.PropertiesConfig.QTZ.getPropertyName());



    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private SysSettingDao sysSettingDao;

    /**
     *推送学习/考试计划
     * @param addSafePlanTaskDto
     */
    public void pushPlanTaskToDriver(AddSafePlanTaskDto addSafePlanTaskDto){
        logger.info("调用URL"+pushPlanTaskUrl);
        logger.info("推送参数"+ JsonMapper.toNonNullJson(addSafePlanTaskDto));
        taskExecutor.execute(() -> StarsoftHttpUtil.post(pushPlanTaskUrl, JsonMapper.toNonNullJson(addSafePlanTaskDto)));
    }


    /**
     * 根据违规id查询计划详情
     * @param violationId
     */
    public  Map<String,Object> queryPlanByViolationId(String violationId,String type){
        Map<String,Object> resultMap = null;
        logger.info("调用URL"+queryPlanTaskUrl+"/"+violationId);
        try{
            Map<String,String> paraMap = new HashMap<>();
            paraMap.put("safeRelationId",violationId);
            paraMap.put("type",type);
            String result = HttpClientUtil.post(queryPlanTaskUrl, paraMap);
            resultMap = JsonMapper.buildNonDefaultMapper().fromJson(result, HashMap.class);
        }catch (Exception e){

        }
        return resultMap;
    }


    /**
     * '获取每日一学
     * @param personId
     */
    public Map<String,Object> getStudy(String personId,Integer state,Integer pageIndex,Integer pageSize){
        Map<String,Object> resultMap = null;
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.SAFE_URL);
        String postUrl = rootUrl+SenderConstantUtil.STUDY_URL;
        logger.info("调用URL"+postUrl);
        try{
            Map<String,String> paraMap = new HashMap<>();
            paraMap.put("personId",personId);
            if(state!=null) {
            	paraMap.put("state",state.toString());
            }
            paraMap.put("pageIndex",pageIndex.toString());
            paraMap.put("pageSize",pageSize.toString());
            String result = HttpClientUtil.post(postUrl, paraMap);
            resultMap =JsonMapper.buildNonDefaultMapper().fromJson(result, HashMap.class);
        }catch (Exception e){

        }
        return resultMap;

    }
    
    public Map<String,Object> delTask(String violationId){
        Map<String,Object> resultMap = null;
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.SAFE_URL);
        String postUrl = rootUrl+SenderConstantUtil.DEL_TASK_URL;
        logger.info("调用URL"+postUrl);
        try{
            Map<String,String> paraMap = new HashMap<>();
            paraMap.put("safeRelationId",violationId);
            paraMap.put("type","200");
            String result = HttpClientUtil.post(postUrl, paraMap);
            resultMap =JsonMapper.buildNonDefaultMapper().fromJson(result, HashMap.class);
        }catch (Exception e){

        }
        return resultMap;
    }


//    public static void main(String[] args) {
//
//        JSONObject paramJson = new JSONObject();
//        paramJson.put("Time", DateUtil.dateTOString(DateUtil.getSqlTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
//        paramJson.put("DriverGUID","3a99f0ad74cd4279aee28413959bb923");
//
//
//        StarPushDto starPushDto = new StarPushDto();
//
//        starPushDto.setCallParam(JSONObject.toJSONString(paramJson));
//        starPushDto.setUrl("");
//        starPushDto.setPushPlatform("2");
//
//        System.out.println(JSONObject.toJSONString(starPushDto));
//    }
}


