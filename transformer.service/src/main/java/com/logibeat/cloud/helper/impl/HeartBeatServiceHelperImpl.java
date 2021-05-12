package com.logibeat.cloud.helper.impl;

import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.HeartBeatDto;
import com.logibeat.cloud.helper.HeartBeatServiceHelper;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.msg.template.GpsExceptionCustomTemplate;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.jetfire.client.template.CustomChannelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wilson on 2017/6/12.
 */
@Service
public class HeartBeatServiceHelperImpl implements HeartBeatServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatServiceHelperImpl.class);


    @Autowired
    private SysSettingDao sysSettingDao;

    @Autowired
    private HeartbeatEventDao heartbeatEventDao;

    @Autowired
    private HeartbeatExceptionDao heartbeatExceptionDao;

    @Autowired
    private HeartbeatCallbackDao heartbeatCallbackDao;

    @Autowired
    private SyspersonDao syspersonDao;

    @Autowired
    private TaskOrdersDao taskOrdersDao;

    @Autowired
    private JetfireMsgSender jetfireMsgSender;




    /**
     * 注册异常事件
     * @param taskId
     * @param personId
     * @param mobile
     */
    public void regHeartBeatException(String taskId,String personId,String mobile,String exceptionId,int type){
        HeartbeatExceptionEntity heartbeatExceptionEntity  = new HeartbeatExceptionEntity();
        heartbeatExceptionEntity.setId(exceptionId);
        heartbeatExceptionEntity.setTaskId(taskId);
        heartbeatExceptionEntity.setPersonId(personId);
        heartbeatExceptionEntity.setMobile(mobile);
        heartbeatExceptionEntity.setExceptionNum(0);
        heartbeatExceptionEntity.setIsSendMessage(ConstantUtil.BYTE_FALSE);
        heartbeatExceptionEntity.setSendMessageNum(0);
        heartbeatExceptionEntity.setIsCallPhone(ConstantUtil.BYTE_FALSE);
        heartbeatExceptionEntity.setCallPhoneNum(0);
        heartbeatExceptionEntity.setAddTime(DateUtil.getSqlTime());
        heartbeatExceptionEntity.setType(type);
        heartbeatExceptionDao.insert(heartbeatExceptionEntity);
    }


    /**
     * 删除注册心跳事件
     * @param eventAction
     * @param taskCar
     */
    public void delHeartBeatEvent(Integer eventAction,TaskOrdersCarEntity taskCar){
        if(taskCar.getIsAutoDepart() != null && taskCar.getIsAutoDepart()  == 1){
            if(eventAction == 12){
                delHeartBeatEvent(1, taskCar.getOrdersCID());
            }
            else if (eventAction == 55){
                delHeartBeatEvent(2, taskCar.getOrdersCID());
            }
        }
    }



    /**
     * 删除心跳相关
     * @param type
     * @param taskId
     */
    public void delHeartBeatEvent(Integer type,String taskId){
        String exceptionId = "";
        //删除心跳事件
        List<HeartbeatEventEntity> eventList =  heartbeatEventDao.getEventList(type,taskId);
        for(HeartbeatEventEntity heartbeatEvent : eventList){
            exceptionId = heartbeatEvent.getExceptionId();
            String eventId = heartbeatEvent.getId();
            //删除心跳事件反馈记录
            List<HeartbeatCallbackEntity> callbackList  = heartbeatCallbackDao.getEntityByEventId(eventId);
            for(HeartbeatCallbackEntity heartbeatCallbackEntity : callbackList){
                heartbeatCallbackDao.delete(heartbeatCallbackEntity.getId());
            }

        }
        //删除异常事件
        HeartbeatExceptionEntity heartbeatExceptionEntity = heartbeatExceptionDao.select(exceptionId);
        if(null != heartbeatExceptionEntity){
            heartbeatExceptionDao.delete(heartbeatExceptionEntity.getId());
        }

    }

    /**
     * 给连续多次不上报GPS的司机发短信
     */
    public void dealExceptionForMessage(){
        String setValue  = sysSettingDao.getValue(ConstantUtil.MESSAGE_EXCEPTION_NUM);
        Integer exceptionNum = Integer.parseInt(setValue);
        List<HeartbeatExceptionEntity> resultList = heartbeatExceptionDao.getExceptionList(exceptionNum,1);
        for(HeartbeatExceptionEntity heartbeatExceptionEntity : resultList){
        	//发短信
        	String mobile = heartbeatExceptionEntity.getMobile();
        	String ordersCid = heartbeatExceptionEntity.getTaskId();
        	TaskOrdersEntity driverOrder = taskOrdersDao.select(ordersCid);
        	SystemPersonEntity systemPersonEntity = syspersonDao.getEntityByMobile(mobile);
            if(systemPersonEntity != null && driverOrder != null){
                GpsExceptionCustomTemplate gpsExceptionCustomTemplate;
                int type = heartbeatExceptionEntity.getType();
                String startAreaName = driverOrder.getStartAreaName();
                String endAreaName = driverOrder.getEndAreaName();
                if(type == 1){
                    gpsExceptionCustomTemplate = new GpsExceptionCustomTemplate(startAreaName, endAreaName, CustomChannelType.Send43);
                    jetfireMsgSender.sendCustom(gpsExceptionCustomTemplate);
                }

                if(type == 2){
                    gpsExceptionCustomTemplate = new GpsExceptionCustomTemplate(startAreaName, endAreaName, CustomChannelType.Send42);
                    jetfireMsgSender.sendCustom(gpsExceptionCustomTemplate);
                }

                 heartbeatExceptionEntity.setIsSendMessage(ConstantUtil.BYTE_TRUE);
                 heartbeatExceptionDao.update(heartbeatExceptionEntity);
            }
        }
    }


    /**
     * 给连续多次不上报GPS的司机打电话
     */
    public void dealExceptionForPhone(){
        String setValue  = sysSettingDao.getValue(ConstantUtil.PHONE_EXCEPTION_NUM);
        Integer exceptionNum = Integer.parseInt(setValue);
        List<HeartbeatExceptionEntity> resultList = heartbeatExceptionDao.getExceptionList(exceptionNum,2);
        for(HeartbeatExceptionEntity heartbeatExceptionEntity : resultList){
        	//打电话
        	String mobile = heartbeatExceptionEntity.getMobile();
        	String ordersCid = heartbeatExceptionEntity.getTaskId();
        	TaskOrdersEntity driverOrder = taskOrdersDao.select(ordersCid);
        	SystemPersonEntity systemPersonEntity = syspersonDao.getEntityByMobile(mobile);
            if(systemPersonEntity != null && driverOrder != null){
                GpsExceptionCustomTemplate gpsExceptionCustomTemplate;
                int type = heartbeatExceptionEntity.getType();
                String startAreaName = driverOrder.getStartAreaName();
                String endAreaName = driverOrder.getEndAreaName();
                if(type == 1){
                    gpsExceptionCustomTemplate = new GpsExceptionCustomTemplate(startAreaName, endAreaName, CustomChannelType.Send41);
                    jetfireMsgSender.sendCustom(gpsExceptionCustomTemplate);
                }

                if(type == 2){
                    gpsExceptionCustomTemplate = new GpsExceptionCustomTemplate(startAreaName, endAreaName, CustomChannelType.Send40);
                    jetfireMsgSender.sendCustom(gpsExceptionCustomTemplate);
                }
                 heartbeatExceptionEntity.setIsCallPhone(ConstantUtil.BYTE_TRUE);
                 heartbeatExceptionDao.update(heartbeatExceptionEntity);
            }
        }
    }

    @Override
	public void regCarGpsEvent(HeartBeatDto heartBeatDto) {

        String eventId = RandomTool.getGUId();
        Integer callbackSecond =  Integer.valueOf(sysSettingDao.getValue(ConstantUtil.CAR_GPS_CALLBACK_ZTO_SECOND));
//        String callbackUrl = sysSettingDao.getValue(ConstantUtil.PUSH_CAR_GPS_TO_ZTO_URL);

//        //向grimlock注册心跳
//        SpaceHeartbeatLogEntity spaceHeartbeatLogEntity = new SpaceHeartbeatLogEntity();
//        spaceHeartbeatLogEntity.setEventId(eventId);
//        spaceHeartbeatLogEntity.setEventStartTime(heartBeatDto.getStartTime());
//        spaceHeartbeatLogEntity.setEventEndTime(heartBeatDto.getEndTime());
//        spaceHeartbeatLogEntity.setKeyId(heartBeatDto.getKeyId());
//        spaceHeartbeatLogEntity.setSource(heartBeatDto.getSource() == null? null : heartBeatDto.getSource().toString());
//        spaceHeartbeatLogEntity.setCallbackSecond(callbackSecond);
//        spaceHeartbeatLogEntity.setCallbackUrl(callbackUrl);
//        spaceHeartbeatLogEntity.setTag(heartBeatDto.getPlateNumber());
//        spaceHeartbeatLogEntity.setEventType(SpaceHeartbeatLogEntity.EventType.LAST_GPS.getValue());
//
//        String result = grimlockServiceHelper.regHeartBeat(spaceHeartbeatLogEntity);
//        if(result == null){
//        	logger.info("注册车辆GPS心跳事件失败:"+"keyId:"+heartBeatDto.getKeyId()+","+"source:"+heartBeatDto.getSource()
//        			+","+"callbackUrl:"+callbackUrl);
//        }

        //向transformers 注册心跳
        HeartbeatEventEntity heartbeatEventEntity = new HeartbeatEventEntity();
        heartbeatEventEntity.setId(eventId);
        heartbeatEventEntity.setType(heartBeatDto.getType());
        heartbeatEventEntity.setKeyWord(heartBeatDto.getKeyId());
        heartbeatEventEntity.setSource(heartBeatDto.getSource());
        heartbeatEventEntity.setStartTime(heartBeatDto.getStartTime());
        heartbeatEventEntity.setEndTime(heartBeatDto.getEndTime());
        heartbeatEventEntity.setTaskId(heartBeatDto.getTaskId());
        heartbeatEventEntity.setIntervalSecond(callbackSecond);
        heartbeatEventEntity.setAddTime(DateUtil.getSqlTime());
        heartbeatEventDao.insert(heartbeatEventEntity);
    
	}


	@Override
	public void delCarGpsEvent(Integer type, String taskId) {
        //删除车辆GPS注册事件
        List<HeartbeatEventEntity> eventList =  heartbeatEventDao.getEventList(type,taskId);
        for(HeartbeatEventEntity heartbeatEvent : eventList){
        	heartbeatEventDao.delete(heartbeatEvent.getId());
//        	grimlockServiceHelper.delCarGpsEvent(heartbeatEvent.getId());
        }
	}


}
