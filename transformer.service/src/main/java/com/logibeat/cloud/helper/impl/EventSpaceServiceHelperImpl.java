package com.logibeat.cloud.helper.impl;

import com.google.gson.Gson;
import com.logibeat.cloud.common.enumtype.EventAction;
import com.logibeat.cloud.common.enumtype.MonitorDistance;
import com.logibeat.cloud.common.enumtype.PointState;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.core.dto.EventSpaceDto;
import com.logibeat.cloud.dto.EventPreDto;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.helper.EventSpaceServiceHelper;
import com.logibeat.cloud.helper.GrimlockServiceHelper;
import com.logibeat.cloud.helper.HeartBeatServiceHelper;
import com.logibeat.cloud.msg.constant.MessageConstant;
import com.logibeat.cloud.persistent.dao.CarOperationTimeDao;
import com.logibeat.cloud.persistent.dao.EventSpaceLogDao;
import com.logibeat.cloud.persistent.dao.NetworkDao;
import com.logibeat.cloud.persistent.dao.TaskOrdersAreaDao;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.util.tools.GsonUtil;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.StringUtil;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wilson on 2017/8/2.
 */
@Service
public class EventSpaceServiceHelperImpl implements EventSpaceServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(EventSpaceServiceHelperImpl.class);


    @Autowired
    private TaskOrdersAreaDao taskOrdersAreaDao;

    @Autowired
    private CarOperationTimeDao carOperationTimeDao;

    @Autowired
    private EventSpaceLogDao eventSpaceLogDao;

    @Autowired
    private GrimlockServiceHelper grimlockServiceHelper;
    
    @Autowired
    private NetworkDao networkDao;

    @Autowired
    private HeartBeatServiceHelper heartBeatServiceHelper;

    // 初始化gson
    Gson gson = new Gson();


    public void registByCurrent(TaskOrdersAreaEntity currentArea, Integer action, TaskOrdersCarEntity taskCar){
    	int pre = 0;
    	
    	if (!action.equals(EventAction.DriverDeparting.getValue())) {
    		pre = pre + 1;
		}
		
		if (!action.equals(EventAction.DriverArrive.getValue())) {
			pre = pre + 1;
		}
		
		//如果司机是到达终点，不注册下一个电子围栏，返回
		if(pre >= 2){
			return ;
		}
    	Integer pointState = 0;
        TaskOrdersAreaEntity targetArea = null;
        boolean isStart = false;
        boolean isEnd = false;
        boolean isWay = false;
        if (currentArea.getIsStartPoint().equals(new Byte("1"))) {
            isStart = true;
        } else if (currentArea.getIsEndPoint().equals(new Byte("1"))) {
            isEnd = true;
        } else {
            isWay = true;
        }
        List<TaskOrdersAreaEntity> unLeaveList = taskOrdersAreaDao.getUnLeaveNode(currentArea.getOrdersCID());
        TaskOrdersAreaEntity nextArea = null;
        if(action.equals(EventAction.DriverDeparting.getValue())){
        	if(!unLeaveList.isEmpty() && unLeaveList.size() > 0){
        		nextArea = unLeaveList.get(0);
        	}else{
        		return;
        	}
        }else if(!action.equals(EventAction.DriverDeparting.getValue())){
        	if(!unLeaveList.isEmpty() && unLeaveList.size() > 0){
        		nextArea = unLeaveList.get(0);
        	}else{
        		return;
        	}
        }
//        TaskOrdersAreaEntity nextArea = taskOrdersareaExpandDao.getNextArea(currentArea.getOrdersCid());
        String taskAreaId = nextArea.getGuid();
        targetArea  = nextArea;
        //发车
        if (isStart && action.equals(EventAction.DriverDeparting.getValue())) {
            if(nextArea.getIsEndPoint().equals(new Byte("1"))){
                pointState = PointState.ARRIVE_250.getValue();
                action = EventAction.DriverArriveEndArea.getValue();
            } else {
                pointState = PointState.WAY_ARRIVE.getValue();
                action = EventAction.DriverArrive.getValue();
            }
        }

        //到达途经点
        else if(isWay && action.equals(EventAction.DriverArrive.getValue())){
            pointState = PointState.WAY_DEPART.getValue();
            action = EventAction.DriverDeparting.getValue();
        }
        else if(isWay && action.equals(EventAction.DriverDeparting.getValue())){
        	if(nextArea.getIsEndPoint().equals(new Byte("1"))){
                pointState = PointState.ARRIVE_250.getValue();
                action = EventAction.DriverArriveEndArea.getValue();
            } else {
                pointState = PointState.WAY_ARRIVE.getValue();
                action = EventAction.DriverArrive.getValue();
            }
        }
        // 跨点到终点
        if(isEnd){
            action = EventAction.DriverArriveEndArea.getValue();
            pointState = PointState.OTHER.getValue();
            targetArea = currentArea;
        }
        registByTarget(targetArea, action, pointState , taskCar);

    }


    /**
     * 根据目标网点注册电子蔚蓝
     * @param targetArea
     * @param action
     * @param pointState
     * @param taskCar
     */
    public void registByTarget(TaskOrdersAreaEntity targetArea,Integer action,Integer pointState ,TaskOrdersCarEntity taskCar){
    	
//    	eventPreDto.setEventAction(EventAction.DriverDeparting.getValue());
//    	eventPreDto.setOrdersCID(taskCar.getOrdersCid());
//    	eventPreDto.setCarID(taskCar.getCarId());
//    	eventPreDto.setBaseEntId(taskCar.getEntrustEntid());
//    	eventPreDto.setBaseUserId(taskCar.getFirstDriverPersonId());
//    	//entInfo.getPreConditionNum()
//    	eventPreDto.setPre(1);
//    	eventPreDto.setPointState(0);
//    	eventPreDto.setIsAutoSend(taskCar.getIsAutoDepart().intValue());
//    	eventPreDto.setIsAutoArrive(taskCar.getIsAutoArrive().intValue());
    	
    	EventPreDto eventPreDto = new EventPreDto();
        eventPreDto.setTargetArea(targetArea);
        eventPreDto.setAction(action);
        eventPreDto.setPointState(pointState);
        eventPreDto.setCarId(taskCar.getCarID());
        eventPreDto.setPlanArriveTime(taskCar.getPlanArriveTime());
        eventPreDto.setPlanLeaveTime(taskCar.getPlanLeaveTime());
        eventPreDto.setEffectTime(taskCar.getEffectiveTime());
        eventPreDto.setEntId(taskCar.getEntrustEntid());
        eventPreDto.setIsAutoArrive(taskCar.getIsAutoArrive().intValue());
        eventPreDto.setIsAutoDepart(taskCar.getIsAutoDepart().intValue());
        eventPreDto.setTaskId(taskCar.getOrdersCID());
        eventPreDto.setTaskId(taskCar.getId());
        eventPreDto.setPersonId(taskCar.getFirstDriverPersonID());
        autoDepOrArrive(eventPreDto,taskCar);
    }






    /**
     * 根据不同位置注册不同的电子围栏（发/到）
     * @param eventPreDto
     */
    public void autoDepOrArrive(EventPreDto eventPreDto,TaskOrdersCarEntity taskorderscar) {
        String remark = "";
        Integer spaceType = 0;
        Integer meter = 0 ;
        //查该网点范围设置
        TaskOrdersAreaEntity areaEntity = eventPreDto.getTargetArea();
        if(null != areaEntity && StringUtils.isNotBlank(areaEntity.getNetWorkGuid())){
    		NetworkEntity networkEntity = networkDao.select(areaEntity.getNetWorkGuid());
    		if(null != networkEntity){
    			meter = networkEntity.getNetworkRange();
    		}
    	}
        //任务周期
        Date taskStart = null;
        Date taskEnd = null;
        //电子围栏周期
        Date eventStart = new Date();
        Date eventEnd = null;
        Date planLeave = eventPreDto.getPlanLeaveTime();
        Date planArrive = eventPreDto.getPlanArriveTime();
        Integer effectTime = eventPreDto.getEffectTime();
        if(planLeave != null && effectTime != null){
        	planArrive = null;
        }
        else if(planArrive != null){
        	planLeave = null;
        }
        String entId = eventPreDto.getEntId();
        Integer pointState =  eventPreDto.getPointState();
        TaskOrdersAreaEntity targetArea = eventPreDto.getTargetArea();
        // 关联ID 拥有该ID的不同事件，其一触发其他事件也自动完成
        String random = StringUtil.getRandom(12);
        //车辆时间段
        String carId = eventPreDto.getCarId();
        List<CarOperationTimeEntity> carOperationTimeList = new ArrayList<>();
        if(StringUtils.isNotBlank(carId)){
             carOperationTimeList = carOperationTimeDao.getCarOperationTimeNoPerson(carId);
        }
        if(StringUtils.isNotBlank(eventPreDto.getPersonId())){
            CarOperationTimeEntity carOperationTime = new CarOperationTimeEntity();
            carOperationTime.setDeviceType(0);
            carOperationTime.setKeyWord(eventPreDto.getPersonId());
            carOperationTimeList.add(carOperationTime);
        }


        //任务周期
        if(planLeave != null){
            taskStart = planLeave;
        } else{
            taskStart = new Date();
        }
        if(planArrive != null){
            taskEnd = planArrive;
        } else {
            if (effectTime != null && planLeave != null) {
                taskEnd = DateUtil.getTimeAddMinutes(planLeave, eventPreDto.getEffectTime());
            } else {
                taskEnd = DateUtil.getTimeAddDate(new Date(), 3);
            }
        }
        //起点发车
        if (pointState.equals(0)) {
            // 电子围栏周期  从现在监控到预计发车时间后6个小时/ 没有预计发车时间则从当前时间到后3天
            if(planLeave != null){
                eventStart = DateUtil.getTimeAdd(planLeave, -6);
                eventEnd = DateUtil.getTimeAdd(planLeave, 24);
            } else{
                eventEnd = DateUtil.getTimeAdd(new Date(), 36);
            }
            remark = MessageConstant.MessageTypeToDevice.TASKSENDCAR.getValue();
            spaceType = 0;
            if(null == meter || meter == 0){
            	meter =  MonitorDistance.OutPoint.getValue();
            }
        }

        //到达途经点
        else if (pointState.equals(2)) {
            if (effectTime != null && planLeave != null) {
                eventEnd = DateUtil.getTimeAddMinutes(planLeave, 36 * 60 + effectTime);
            } else {
                eventEnd = DateUtil.getTimeAddDate(new Date(), 3);
            }
            remark = MessageConstant.MessageTypeToDevice.TASKWAY.getValue();
            spaceType = 1;
            if(null == meter || meter == 0){
            	meter = MonitorDistance.InPoint.getValue();
            }
        }
        else if (pointState.equals(3)) {
        	// 电子围栏周期  从现在监控到预计发车时间后6个小时/ 没有预计发车时间则从当前时间到后3天
            if(planLeave != null){
                eventStart = DateUtil.getTimeAdd(planLeave, -6);
                eventEnd = DateUtil.getTimeAdd(planLeave, 24);
            } else{
                eventEnd = DateUtil.getTimeAdd(new Date(), 36);
            }
            remark = MessageConstant.MessageTypeToDevice.TASKSENDCAR.getValue();
            spaceType = 0;
            if(null == meter || meter == 0){
            	meter =  MonitorDistance.OutPoint.getValue();
            }
		}
        else if (pointState.equals(7)) {
            if (effectTime != null && planLeave != null) {
                eventEnd = DateUtil.getTimeAddMinutes(planLeave, 36 * 60 + effectTime);
            } else {
                eventEnd = DateUtil.getTimeAddDate(new Date(), 3);
            }
            remark = MessageConstant.MessageTypeToDevice.TASKARRIVE.getValue();
            spaceType = 1;
            if(null == meter || meter == 0){
            	meter = MonitorDistance.InPoint.getValue();
            }
        }
        for(CarOperationTimeEntity carOperationTime: carOperationTimeList){
            EventSpaceDto eventSpaceDto = new EventSpaceDto();
            eventSpaceDto.setRemark(remark);
            eventSpaceDto.setSpaceType(spaceType);
            eventSpaceDto.setMeter(meter);
            eventSpaceDto.setRemark1(random);
            eventSpaceDto.setGroupId(entId);
            eventSpaceDto.setTag(getGrimlockTag(targetArea,eventPreDto.getAction(),pointState,taskorderscar));
            //设置是否是自动单
            if((eventPreDto.getIsAutoArrive() != null && eventPreDto.getIsAutoArrive() == 1) 
            		|| (eventPreDto.getIsAutoDepart() != null && eventPreDto.getIsAutoDepart() == 1) ){
            	eventSpaceDto.setSpaceOnly(1);
            }else{
            	eventSpaceDto.setSpaceOnly(0);
            }
            eventSpaceDto.setEventId(eventPreDto.getTaskId());
            eventSpaceDto.setEventState(eventPreDto.getPointState());
            eventSpaceDto.setLat(targetArea.getLat());
            eventSpaceDto.setLng(targetArea.getLng());
            eventSpaceDto.setKey(carOperationTime.getKeyWord());
            eventSpaceDto.setSource(carOperationTime.getDeviceType());
            eventSpaceDto.setEquipment(carOperationTime.getEquipment());
            eventSpaceDto.setStartTime(eventStart);
            eventSpaceDto.setEndTime(eventEnd);
            eventSpaceDto.setPlanStart(taskStart);
            eventSpaceDto.setPlanEnd(taskEnd);
            regSpace(eventSpaceDto);
        }
    }


    /**
     * 用于回调拼接参数
     * @param tagetArea
     * @param action
     * @param pointState
     * @param taskCarEntity
     * @return
     */
    public String getGrimlockTag(TaskOrdersAreaEntity tagetArea,Integer action,Integer pointState,TaskOrdersCarEntity taskCarEntity){
//        HandleTaskDto handleTaskDto = new HandleTaskDto();
//        handleTaskDto.setAction(action);
//        handleTaskDto.setLat(tagetArea.getLat());
//        handleTaskDto.setLng(tagetArea.getLng());
//        handleTaskDto.setAddress(tagetArea.getAddress());
//        handleTaskDto.setPointState(pointState);
//        handleTaskDto.setTaskAreaId(tagetArea.getGuid());
//        handleTaskDto.setTaskCarId();
        
        EventTaskDto eventPreDto = new EventTaskDto();
    	eventPreDto.setEventAction(action);
    	//***********************************改成taskCarId
    	eventPreDto.setOrdersCid(taskCarEntity.getId());
    	eventPreDto.setCarId(taskCarEntity.getCarID());
    	eventPreDto.setBaseEntId(taskCarEntity.getEntrustEntid());
    	eventPreDto.setBaseUserId(taskCarEntity.getFirstDriverPersonID());
    	//entInfo.getPreConditionNum()
    	eventPreDto.setPre(1);
    	eventPreDto.setPointState(pointState);
    	eventPreDto.setIsAutoSend(taskCarEntity.getIsAutoDepart().intValue());
    	eventPreDto.setIsAutoArrive(taskCarEntity.getIsAutoArrive().intValue());
    	eventPreDto.setOrdersAreaGuid(tagetArea.getGuid());
    	DynamicGpsDTO gps = new DynamicGpsDTO();
		gps.setAddress(tagetArea.getAddress());
		gps.setLat(tagetArea.getLat());
		gps.setLng(tagetArea.getLng());
    	eventPreDto.setGps(gps);
    	return  gson.toJson(eventPreDto);
    }



    /**
     * 注册电子围栏
     * @param eventSpaceDto
     */
    private void regSpace(EventSpaceDto eventSpaceDto) {
        try {
            // 插入事件
            EventSpaceLogEntity eventSpaceLogEntity = new EventSpaceLogEntity();
            eventSpaceLogEntity.setId(RandomTool.getGUId());
            eventSpaceLogEntity.setEventId(eventSpaceDto.getEventId());
            eventSpaceLogEntity.setIsSuccess("0");
            eventSpaceLogEntity.setEventType("0");
            eventSpaceLogEntity.setTag(eventSpaceDto.getTag());
            eventSpaceLogEntity.setEventRemark(eventSpaceDto.getRemark());
            eventSpaceLogEntity.setEventRemark1(eventSpaceDto.getRemark1()); // 用户ID
            eventSpaceLogEntity.setEventRemark2(eventSpaceDto.getSource() + "|" + eventSpaceDto.getKey());
            eventSpaceLogEntity.setCreateTime(DateUtil.getCurrentTime());
            eventSpaceLogDao.insert(eventSpaceLogEntity);

            // 到grimlock注册事件 (出发点)
//            String grimlockTag = JsonMapper.toLogJson(eventSpaceLogEntity);
//            String msg = eventSpaceDto.getEventId() + eventSpaceDto.getTag() + eventSpaceDto.getRemark()
//                    + eventSpaceDto.getRemark1() + eventSpaceDto.getSource() + "|" + eventSpaceDto.getKey();
//            String msgMD5 = TypeCastUtil.getMD5(msg);
//            RegisterSpaceDto registerSpaceDto = new RegisterSpaceDto();
//            registerSpaceDto.setMsgId(msgMD5);
//            registerSpaceDto.setEventId(eventSpaceDto.getEventId());
//            registerSpaceDto.setEventState(eventSpaceDto.getEventState());
//            registerSpaceDto.setGroupId(eventSpaceDto.getGroupId());
//            registerSpaceDto.setSpaceOnly(eventSpaceDto.getSpaceOnly() == null ? "0" : eventSpaceDto.getSpaceOnly().toString());
//            registerSpaceDto.setSpaceType(eventSpaceDto.getSpaceType());
//            registerSpaceDto.setLongitude(eventSpaceDto.getLng());
//            registerSpaceDto.setLatitude(eventSpaceDto.getLat());
//            registerSpaceDto.setTag(grimlockTag);
//            registerSpaceDto.setMeter(eventSpaceDto.getMeter());
//            registerSpaceDto.setSource(eventSpaceDto.getSource());
//            registerSpaceDto.setKey(eventSpaceDto.getKey());
//            registerSpaceDto.setEquipment(eventSpaceDto.getEquipment());
//            registerSpaceDto.setTotalPre(eventSpaceDto.getTotalPre());
//
//            // 转换
//            if(eventSpaceDto.getStartTime() != null && eventSpaceDto.getEndTime() != null){
//                registerSpaceDto.setStartTime(DateUtil.dateTOString(eventSpaceDto.getStartTime(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//                registerSpaceDto.setEndTime(DateUtil.dateTOString(eventSpaceDto.getEndTime(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//            }
//            if(eventSpaceDto.getPlanEnd() != null && eventSpaceDto.getPlanStart() != null){
//                registerSpaceDto.setPlanStart(DateUtil.dateTOString(eventSpaceDto.getPlanStart(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//                registerSpaceDto.setPlanEnd(DateUtil.dateTOString(eventSpaceDto.getPlanEnd(), DateUtil.YYYY_MM_DD_HH_mm_ss));
//            }
//            grimlockServiceHelper.register(registerSpaceDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[grimlock] 注册电子围栏失败..");
        }
    }


    /**
     * 发车、到达后，结束电子围栏
     * @param isAutoDepart
     * @param isAutoArrive
     * @param orderscid
     * @param eventAction
     */
    public void finishEventLog(Integer isAutoDepart,Integer isAutoArrive,String orderscid,Integer eventAction, String taskAreaGUID){
        if((isAutoDepart == null || isAutoDepart == 0) && (isAutoArrive == null || isAutoArrive == 0)){
            String eventRemark = "";
            if(eventAction == EventAction.DriverDeparting.getValue()){
                eventRemark = "TaskSendCar";
            }
            else if(eventAction == EventAction.DriverArrive.getValue()){
                eventRemark = "TaskWay";
            }
            else if(eventAction == EventAction.DriverArriveEndArea.getValue()||eventAction == EventAction.DriverFinish.getValue()){
                eventRemark = "TaskArrive";
            }
            else{
                return;
            }

            List<EventSpaceLogEntity> eventSpaceLogList = eventSpaceLogDao.getEntity(orderscid, eventRemark);
            eventSpaceLogList.forEach(p -> {
                String tag = p.getTag();
                if(StringUtils.isNotBlank(tag)){
                    EventTaskDto eventTaskDto = (EventTaskDto) GsonUtil.fromJson(tag, EventTaskDto.class);
                    if(eventTaskDto != null && taskAreaGUID.equals(eventTaskDto.getOrdersAreaGuid())){
                        String msg = p.getEventId() + p.getTag()
                                + p.getEventRemark() + p.getEventRemark1()
                                + p.getEventRemark2();
                        String msgMd5 = TypeCastUtil.getMD5(msg);
                        logger.info("msgMd5:"+msgMd5+",orderscid:"+orderscid);
//                        grimlockServiceHelper.finishEventLog(msgMd5);
                    }
                }
            });
        }
    }

}
