package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.enumtype.TaskStatus;
import com.logibeat.cloud.common.vo.CarGpsOperationVo;
import com.logibeat.cloud.common.vo.CarGpsSourceVo;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.errorenum.DriverTaskErrorEnums;
import com.logibeat.cloud.persistent.dao.DriverTaskDao;
import com.logibeat.cloud.persistent.dao.DriverTaskPointDao;
import com.logibeat.cloud.persistent.dao.TaskOperationTimeDao;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import com.logibeat.cloud.persistent.entity.TaskOperationTimeEntity;
import com.logibeat.cloud.services.GrimlockService;
import com.logibeat.cloud.services.TaskTrackService;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskTrackServiceImpl implements TaskTrackService {

	@Autowired
	private TaskOperationTimeDao taskOperationTimeDao;

	@Autowired
	private DriverTaskDao driverTaskDao;

	@Autowired
	private DriverTaskPointDao driverTaskPointDao;




	/**
	 * 任务跟踪
	 * @param taskId
	 * @return
	 */
	@Override
	public List<CarGpsSourceVo> getTaskHistoryOrbit(String taskId, String consignId){
		List<CarGpsSourceVo> resultList = new ArrayList<>();
		DriverTaskEntity driverTask  = null;
		String fromOperation = null;
		Integer taskStatus = TaskStatus.WaitRun.getValue();
		String endOperation = null;
		//托运单
		if(StringUtils.isNotBlank(consignId)){
			List<DriverTaskPointEntity> pointList = driverTaskPointDao.getPointListByRelationId(consignId);
			if(null == pointList || pointList.isEmpty()){
				throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
			}
			String relationTaskId = pointList.get(0).getTaskId();
			fromOperation = DateUtil.dateTOString(pointList.get(0).getActualStartTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);
			driverTask = driverTaskDao.selectDriverTaskById(relationTaskId);
			if(null == driverTask){
				throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
			}
			List<DriverTaskPointEntity> arriveList = pointList.parallelStream().filter(p -> null == p.getActualArriveTime()).collect(Collectors.toList());
			if(arriveList.size() == 0){
				taskStatus = TaskStatus.Finish.getValue();
				endOperation = DateUtil.dateTOString(pointList.get(pointList.size()-1).getActualArriveTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);
			}
		}

        //派车单
		else if(StringUtils.isNotBlank(taskId)){
			driverTask = driverTaskDao.selectDriverTaskById(taskId);
			if(null == driverTask){
				throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
			}
			taskStatus = driverTask.getTaskStatus();
		}
		List<TaskOperationTimeEntity> taskOperationTimeList = taskOperationTimeDao.getTaskOperationTimeList(driverTask.getGuid());
		List<Integer> sourceTypeList =  taskOperationTimeList.parallelStream().map(TaskOperationTimeEntity::getDeviceType).distinct().collect(Collectors.toList());
		for(Integer source : sourceTypeList){
			CarGpsSourceVo carGpsSourceVo = new CarGpsSourceVo();
			carGpsSourceVo.setSource(source.toString());
			List<CarGpsOperationVo> operationVoList = new ArrayList<>();
			//根据来源过滤
			List<TaskOperationTimeEntity> sourceTimeList = taskOperationTimeList.parallelStream().filter(p->p.getDeviceType().equals(source)).collect(Collectors.toList());
			for(int i = 0;i<sourceTimeList.size();i++){
				TaskOperationTimeEntity sourceTime = sourceTimeList.get(i);
				CarGpsOperationVo carGpsOperationVo = new CarGpsOperationVo();
				if(StringUtils.isBlank(fromOperation)){
					fromOperation=DateUtil.dateTOString(sourceTime.getStartTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);
				}
				if(StringUtils.isBlank(endOperation)){
					endOperation =DateUtil.dateTOString(sourceTime.getEndTime(),DateUtil.YYYY_MM_DD_HH_mm_ss);
					if(!TaskStatus.Finish.getValue().equals(taskStatus)){
						if(sourceTimeList.size() == 1){
							endOperation = DateUtil.dateTOString(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss);
						}
						else{
							if(i == sourceTimeList.size()-1){
								endOperation = DateUtil.dateTOString(new Date(),DateUtil.YYYY_MM_DD_HH_mm_ss);
							}
						}
					}
				}
				carGpsOperationVo.setFrom(fromOperation);
				carGpsOperationVo.setTo(endOperation);
				carGpsOperationVo.setKey(sourceTime.getKeyWord());
				carGpsOperationVo.setEquipment(sourceTime.getEquipment());
				operationVoList.add(carGpsOperationVo);
			}
			carGpsSourceVo.setOperationVoList(operationVoList);
			resultList.add(carGpsSourceVo);
		}
		return  resultList;
	}


	/**
	 * 计算里程
	 * @param driverTask
	 * @param taskPoint
	 */
	@Override
	public void countMileage(DriverTaskEntity driverTask, DriverTaskPointEntity taskPoint){
		//任务单
//		DriverTaskEntity driverTask = driverTaskDao.selectDriverTaskById(taskId);
//		if(null == driverTask){
//			throw new ServiceException(DriverTaskErrorEnums.TaskErrors.TASK_NOT_EXIST);
//		}
//		List<HistoryGpsQuery> queries = new ArrayList<>();
//		Date startTime = taskPoint.getActualStartTime();
//		Date arriveTime = taskPoint.getActualArriveTime();
//		List<TaskOperationTimeEntity> taskTimeList = taskOperationTimeDao.getCustomTaskOperationTimeList(driverTask.getGuid(),startTime,arriveTime);
//		if(null != taskTimeList && !taskTimeList.isEmpty()){
//			// 获取最大值的deviceType
//			Integer maxDeviceType = taskTimeList.parallelStream().mapToInt(TaskOperationTimeEntity::getDeviceType).max().orElse(0);
//			taskTimeList = taskTimeList.parallelStream().filter(p -> maxDeviceType.equals(p.getDeviceType())).collect(Collectors.toList());
//			for(TaskOperationTimeEntity taskOperationTime : taskTimeList){
//				Date start = taskOperationTime.getStartTime();
//				Date end = taskOperationTime.getEndTime();
//				if(startTime.getTime()>=start.getTime()){
//					taskOperationTime.setStartTime(DateUtil.dateToTimestamp(startTime));
//				}
//				if(arriveTime.getTime()<=end.getTime()){
//					taskOperationTime.setEndTime(DateUtil.dateToTimestamp(arriveTime));
//				}
//
//				//拼装参数
//				HistoryGpsQuery query = new HistoryGpsQuery();
//				query.setKey(taskOperationTime.getKeyWord());
//				query.setSource(taskOperationTime.getDeviceType());
//				query.setFrom(DateUtils.dateTOString(taskOperationTime.getStartTime(), DateUtils.YYYY_MM_DD_HH_mm_ss));
//				query.setTo(DateUtils.dateTOString(taskOperationTime.getEndTime(), DateUtils.YYYY_MM_DD_HH_mm_ss));
//				queries.add(query);
//
//			}
//			//计算里程
//			CarGpsHistoryTotalDto disDto = grimlockService.getTotalDis(queries);
//			if(null != disDto){
//
//				//装卸点里程
//				Double actualMileage = disDto.getTotalDis()*1000;
//				taskPoint.setActualRuningMileage(actualMileage.intValue());
//				driverTaskPointDao.updateDriverTaskPoint(taskPoint);
//
//
//				//派车单里程
//				Integer taskMillage = driverTask.getActualRuningMileage();
//				if(null == taskMillage){
//					driverTask.setActualRuningMileage(actualMileage.intValue());
//				}
//				else{
//					taskMillage += actualMileage.intValue();
//					driverTask.setActualRuningMileage(taskMillage);
//				}
//				driverTaskDao.updateDriverTask(driverTask);
//
//
//				//更新车辆里程
//
//
//			}
//		}
	}


}
