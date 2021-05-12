package com.logibeat.cloud.services.impl;

import com.google.gson.Gson;
import com.logibeat.cloud.common.enumtype.DynamicSource;
import com.logibeat.cloud.common.enumtype.DynamicType;
import com.logibeat.cloud.common.enumtype.EventAction;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.dto.EventTaskDto;
import com.logibeat.cloud.core.dto.TaskDto;
import com.logibeat.cloud.dto.DynamicDto;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.services.DynamicService;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.StringConverUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 动态服务实现类
 *
 * @author karl
 * @version 1.0
 * @date 2016年1月8日
 */
@Service
public class DynamicServiceImpl implements DynamicService {

	private static final Logger logger = LoggerFactory.getLogger(DynamicServiceImpl.class);


	@Autowired
	private TaskDynamicDao taskDynamicDao;


	//transformer系统中需要展示的 动态类别 定义为常量数组
	private static final int [] dynamicAction = {12,13,16,30,31,32,33,34,35,60,61,62};


	@Autowired
	private EnterpriseCooperatePerDao  enterpriseCooperatePerDao;

	@Autowired
	private SyspersonDao syspersonDao;
	
	Gson gson = new Gson();

	/**
	 * 准备添加动态的参数 (发车/到达)
	 * @param personId
	 * @param taskCar
	 * @param eventTaskDto
	 * @return
	 */
	public DynamicDto preAddDynamic(String personId, TaskOrdersCarEntity taskCar,
									EventTaskDto eventTaskDto, List<TaskOrdersEntity> taskList){
		DynamicDto dynamicDto = new DynamicDto();
		String entId = taskCar.getEntrustEntid();
		Byte isAutoDepart = taskCar.getIsAutoDepart();
		Byte isAutoArrive = taskCar.getIsAutoArrive();
		Integer isAtPoint = eventTaskDto.getIsAtPoint();
		Integer action = eventTaskDto.getEventAction();
		String taskId = taskCar.getId();
		Timestamp repeatTime = StringConverUtil.getTimestamp(eventTaskDto.getRepeatTime());
		//当前司机的信息
		EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopEntByEntIdAndPersonId(entId,personId);
		if(null != entPer){
			dynamicDto.setPersonMobile(entPer.getPhoneNumber());
			dynamicDto.setPersonName(entPer.getNameRemark());
		}
		else{
			SystemPersonEntity person =  syspersonDao.select(personId);
			dynamicDto.setPersonMobile(person.getLoginName());
			dynamicDto.setPersonName(person.getNiChen());
		}
		dynamicDto.setPersonId(personId);
		dynamicDto.setTaskId(taskCar.getId());
		dynamicDto.setTaskAreaId(eventTaskDto.getOrdersAreaGuid());
		dynamicDto.setCarId(taskCar.getCarID());
		dynamicDto.setPlateNumber(taskCar.getPlateNumber());
		dynamicDto.setDynamicGpsDTO(eventTaskDto.getGps());
		dynamicDto.setCotent(eventTaskDto.getTxtContent());
		dynamicDto.setAction(action);
		dynamicDto.setEntId(entId);
		dynamicDto.setPicUrl(eventTaskDto.getPicUrls());
		//司机到达发车，到达途经点，完成时，isAtPoint=1
		if(isAutoDepart != null
				&& isAutoDepart.equals(ConstantUtil.BYTE_TRUE)
				&& action.equals(EventAction.DriverDeparting.getValue())){
			isAtPoint = 1;
		}
		if(isAutoArrive != null && isAutoArrive.equals(ConstantUtil.BYTE_TRUE)
				&& (action.equals(EventAction.DriverArrive.getValue())
				|| action.equals(EventAction.DriverArriveEndArea.getValue())
				|| action.equals(EventAction.DriverFinish.getValue())) ){
			isAtPoint = 1;
		}
		dynamicDto.setIsAtPoint(null == isAtPoint ? null : isAtPoint.byteValue());
		dynamicDto.setRepeatTime(repeatTime);

		//拼接查询字段
		String entPrex = "";
		String orderPrex = "[" + taskId + "]";
		for (int i = 0; i < taskList.size(); i++) {
			entPrex += "[" + taskList.get(i).getEntID() + "]";
			orderPrex += "[" + taskList.get(i).getOrdersCID() + "]";
		}
		dynamicDto.setEntPrex(entPrex);
		dynamicDto.setTaskPrex(orderPrex);
		dynamicDto.setOrgId(taskCar.getOrgId());
		
		dynamicDto.setVoiceList(eventTaskDto.getVoiceList());
		return  dynamicDto;
	}


	/**
	 * 准备动态参数（发行程）
	 * @param personId
	 * @param taskCar
	 * @param taskDto
	 * @return
	 */
	public DynamicDto preAddDynamic(String personId, TaskOrdersCarEntity taskCar, TaskDto taskDto, String taskAreaId){
		DynamicDto dynamicDto = new DynamicDto();
		String entId = taskCar.getEntrustEntid();
		Byte isAutoDepart = taskCar.getIsAutoDepart();
		Byte isAutoArrive = taskCar.getIsAutoArrive();
		Integer isAtPoint = taskDto.getIsAtPoint();
		String taskId = taskCar.getId();
		//当前司机的信息
		EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopEntByEntIdAndPersonId(entId,personId);
		if(null != entPer){
			dynamicDto.setPersonMobile(entPer.getPhoneNumber());
			dynamicDto.setPersonName(entPer.getNameRemark());
		}
		else{
			SystemPersonEntity person =  syspersonDao.select(personId);
			dynamicDto.setPersonMobile(person.getLoginName());
			dynamicDto.setPersonName(person.getNiChen());
		}
		if (null != taskDto.getGps()) {
			DynamicGpsDTO dynamicGpsDTO = new DynamicGpsDTO();
			dynamicGpsDTO.setAddress(taskDto.getGps().getAddress());
			dynamicGpsDTO.setLastGpsTime(DateUtil.timestamp2Str(taskDto.getGps().getLastGpsTime()));
			dynamicGpsDTO.setLat(taskDto.getGps().getLat());
			dynamicGpsDTO.setLng(taskDto.getGps().getLng());
			dynamicDto.setDynamicGpsDTO(dynamicGpsDTO);
		}
		dynamicDto.setPersonId(personId);
		dynamicDto.setTaskId(taskId);
		dynamicDto.setTaskAreaId(taskAreaId);
		dynamicDto.setCarId(taskCar.getCarID());
		dynamicDto.setPlateNumber(taskCar.getPlateNumber());
		dynamicDto.setCotent(taskDto.getRemark());
		dynamicDto.setAction(EventAction.DriverDeparting.getValue());
		dynamicDto.setEntId(entId);
		dynamicDto.setPicUrl(taskDto.getPicUrls());
		dynamicDto.setIsAtPoint(null == isAtPoint ? null : isAtPoint.byteValue());
		//拼接查询字段
		String entPrex = "["+entId+"]";
		String taskPrex = "[" + taskId + "]["+taskCar.getOriginalcid()+"]";
		dynamicDto.setEntPrex(entPrex);
		dynamicDto.setTaskPrex(taskPrex);
		dynamicDto.setOrgId(taskCar.getOrgId());
		return  dynamicDto;
	}



	/**
	 * 添加动态
	 * @param dynamicDto
	 * @return
	 */
	public TaskDynamicEntity addDynamic(DynamicDto dynamicDto){
		String dynamicGuid = RandomTool.getGUId();
		String entId = dynamicDto.getEntId();
		String personId = dynamicDto.getPersonId();
		String taskId = dynamicDto.getTaskId();
		Integer action = dynamicDto.getAction();
		String carId = dynamicDto.getCarId();
		String taskAreaId = dynamicDto.getTaskAreaId();
		String picUrl = dynamicDto.getPicUrl();
		String content = dynamicDto.getCotent();
		String orgId=dynamicDto.getOrgId();
		Timestamp repeatTime = null != dynamicDto.getRepeatTime() ? dynamicDto.getRepeatTime() : DateUtil.getSqlTime();
		Byte lastPoint =  dynamicDto.getIsAtPoint();
		TaskDynamicEntity taskDynamic = null;
		List<TaskDynamicEntity> taskDynamicList = taskDynamicDao.getDynamicListByAreaGUid(taskId, action,taskAreaId);
		//编辑
		if(!taskDynamicList.isEmpty() && taskDynamicList.size() > 0){
			taskDynamic = taskDynamicList.get(0);
			taskDynamic.setDynamictime(repeatTime);
			taskDynamicDao.update(taskDynamic);
		}else{
			taskDynamic = new TaskDynamicEntity();
			taskDynamic.setGuid(dynamicGuid);
			taskDynamic.setCarId(carId);
			taskDynamic.setCarPlateNumber(dynamicDto.getPlateNumber());
			taskDynamic.setDriverName(dynamicDto.getPersonName());
			taskDynamic.setDriverMobile(dynamicDto.getPersonMobile());
			taskDynamic.setPersonId(personId);
			taskDynamic.setOrdersCid(taskId);
			taskDynamic.setAreaGuid(taskAreaId);
			taskDynamic.setEntId(entId);
			taskDynamic.setContent(content);
			taskDynamic.setPicUrls(picUrl);
			taskDynamic.setDynamictime(repeatTime);
			taskDynamic.setAction(action);
			taskDynamic.setType(DynamicType.Three.getValue()); // 标识为任务动态反馈
			taskDynamic.setIsDelete(ConstantUtil.BYTE_FALSE);
			taskDynamic.setIsAtPoint(lastPoint);
			taskDynamic.setActionName(EventAction.getName(action));
			taskDynamic.setOrderPrex(dynamicDto.getTaskPrex());
			taskDynamic.setEntPrex(dynamicDto.getEntPrex());
			taskDynamic.setOrgId(orgId);
			//如果不包含在展示数组中 则关闭显示开关
			if(ArrayUtils.contains(dynamicAction, action)){
				taskDynamic.setDyswitch(ConstantUtil.BYTE_CODE_ZERO);
			}else{
				taskDynamic.setDyswitch(ConstantUtil.BYTE_CODE_ONE);
			}
			taskDynamic.setDynamicSource(DynamicSource.One.getValue().byteValue());
			// GPS信息
			if (null != dynamicDto.getDynamicGpsDTO()) {
				DynamicGpsDTO dynamicGpsDTO = dynamicDto.getDynamicGpsDTO();
				taskDynamic.setAddress(dynamicGpsDTO.getAddress());
				taskDynamic.setLat(dynamicGpsDTO.getLat());
				taskDynamic.setLng(dynamicGpsDTO.getLng());
			}
			
			if(dynamicDto.getVoiceList()!=null) {
				taskDynamic.setVoiceInfo(gson.toJson(dynamicDto.getVoiceList()));
			}
			
			taskDynamicDao.insert(taskDynamic);

		}
		return  taskDynamic;
	}

}
