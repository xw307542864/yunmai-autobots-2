package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageHelper;
import com.logibeat.cloud.common.enumtype.StartSoftApiKeyType;
import com.logibeat.cloud.common.vo.TrafficAdviseDetailVo;
import com.logibeat.cloud.common.vo.TrafficAdviseVo;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.dto.push.StarAdviseClockDto;
import com.logibeat.cloud.msg.sender.StarSoftSender;
import com.logibeat.cloud.persistent.dao.TrafficAdviseClockLogDao;
import com.logibeat.cloud.persistent.dao.TrafficAdviseDao;
import com.logibeat.cloud.persistent.entity.TrafficAdviseClockLogEntity;
import com.logibeat.cloud.persistent.entity.TrafficAdviseEntity;
import com.logibeat.cloud.services.TrafficAdviseService;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrafficAdviseServiceImpl implements TrafficAdviseService {
	
	private static final Logger logger = LoggerFactory.getLogger(TrafficAdviseServiceImpl.class);

	@Autowired
	private TrafficAdviseDao trafficAdviseDao;

	@Autowired
	private TrafficAdviseClockLogDao trafficAdviseClockLogDao;
	
	@Autowired
    private StarSoftSender starSoftSender;


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public List<TrafficAdviseVo> getList(String personId, Integer relationSafeCode,String entId, Page page) {

		logger.debug("personId:"+personId);
		List<TrafficAdviseVo> resultList = new ArrayList<TrafficAdviseVo>();

		PageHelper.startPage(page.getPageIndex(), page.getPageSize());

		TrafficAdviseEntity queryParam = new TrafficAdviseEntity();
		queryParam.setPersonId(personId);
		queryParam.setEntId(entId);
		queryParam.setRelationSafeCode(relationSafeCode);
		queryParam.setIsDel(0);
		List<TrafficAdviseEntity> dataList = trafficAdviseDao.selectTrafficAdviseList(queryParam);

		for (TrafficAdviseEntity entity : dataList) {
			TrafficAdviseVo vo = new TrafficAdviseVo();
			vo.setAdviseId(entity.getGuid());
			vo.setEntId(entity.getEntId());
			vo.setEntName(entity.getEntName());
			vo.setBeginTime(entity.getBeginTime());
			vo.setEndTime(entity.getEndTime());
			vo.setStatus(entity.getStatus());
			vo.setDuration(entity.getDuration());
			vo.setCreateTime(entity.getCreateTime());

			resultList.add(vo);
		}

		return resultList;
	}

	@Override
	public void clockIn(String adviseId, Double lng, Double lat, String picUrl,String address,String placeName) {

		Date now = new Date();
		TrafficAdviseEntity adviseInfo = trafficAdviseDao.selectTrafficAdviseById(adviseId);

		TrafficAdviseClockLogEntity logEntity = new TrafficAdviseClockLogEntity();
		logEntity.setGuid(RandomTool.getGUId());
		logEntity.setAdviseId(adviseId);
		logEntity.setLat(lat);
		logEntity.setLng(lng);
		logEntity.setPicUrl(picUrl);
		logEntity.setAddress(address);
		logEntity.setCreateTime(now);
		logEntity.setPlaceName(placeName);
		
		if(StringUtils.isNotBlank(placeName)) {
			logEntity.setPlaceName(placeName);
		}else {
			logEntity.setPlaceName(adviseInfo.getPlaceName());
		}
		

		trafficAdviseClockLogDao.insertTrafficAdviseClockLog(logEntity);

		Integer useTime = 0;
		adviseInfo.setUpdateTime(now);
		if(adviseInfo.getStatus().equals(0) || adviseInfo.getStatus().equals(3)) {
			adviseInfo.setStatus(1);
		}else if(adviseInfo.getStatus().equals(1)){
			Integer intervalTime = adviseInfo.getIntervalTime() == null ? 0 : adviseInfo.getIntervalTime();
			useTime = adviseInfo.getUseTime() + intervalTime;
			adviseInfo.setUseTime(useTime);
		}
		
		//达到劝导时长时，标记完成，并推送结果
		if (useTime >= adviseInfo.getDuration()) {
			adviseInfo.setStatus(4);
			adviseInfo.setFinishTime(now);
			adviseInfo.setUseTime(adviseInfo.getDuration());
			
			List<StarAdviseClockDto> clockList = new ArrayList<StarAdviseClockDto>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TrafficAdviseClockLogEntity queryLog = new TrafficAdviseClockLogEntity();
			queryLog.setAdviseId(adviseId);
			queryLog.setCurDate(sdf.format(now));
			List<TrafficAdviseClockLogEntity> logList = trafficAdviseClockLogDao.selectTrafficAdviseClockLogList(queryLog);
			if(logList!=null & logList.size()>0) {
				for(int i=logList.size()-1;i>=0;i--) {
					TrafficAdviseClockLogEntity logInfo = logList.get(i);
					
					StarAdviseClockDto dto = new StarAdviseClockDto();
					dto.setSignPhoto(logInfo.getPicUrl());
					dto.setSignTime(DateUtil.dateTOString(logInfo.getCreateTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
					dto.setLongitude(new Double(logInfo.getLng()*3600000).intValue());
					dto.setLatitude(new Double(logInfo.getLat()*3600000).intValue());
					dto.setAddress(logInfo.getAddress());
					dto.setPlaceName(logInfo.getPlaceName());
					
					clockList.add(dto);
				}
			}
			
			
			//推送到星软
            for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
                taskExecutor.execute(() -> starSoftSender.violationPersuasion(adviseInfo.getRelationInfo(), 1, clockList, startSoftApiKeyType));
            }
		}
		trafficAdviseDao.updateTrafficAdvise(adviseInfo);

	}
	

	@Override
	public void receipt(String adviseId, String picUrl) {
		Date now = new Date();
		TrafficAdviseEntity adviseInfo = trafficAdviseDao.selectTrafficAdviseById(adviseId);
		adviseInfo.setReceiptPic(picUrl);
		adviseInfo.setReceiptTime(now);
		adviseInfo.setUpdateTime(now);
		trafficAdviseDao.updateTrafficAdvise(adviseInfo);
		
		//推送到星软
        for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
            taskExecutor.execute(() -> starSoftSender.ViolationReceipt(adviseInfo.getRelationInfo(), picUrl, startSoftApiKeyType));
        }
	}

	@Override
	public TrafficAdviseDetailVo detail(String adviseId) {
		
//		Date now = new Date();
		
		TrafficAdviseEntity adviseInfo = trafficAdviseDao.selectTrafficAdviseById(adviseId);

		TrafficAdviseDetailVo vo = new TrafficAdviseDetailVo();
		vo.setAdviseId(adviseInfo.getGuid());
		vo.setEntId(adviseInfo.getEntId());
		vo.setEntName(adviseInfo.getEntName());
		vo.setBeginTime(adviseInfo.getBeginTime());
		vo.setEndTime(adviseInfo.getEndTime());
		vo.setStatus(adviseInfo.getStatus());
		vo.setDuration(adviseInfo.getDuration());
		vo.setInterval(adviseInfo.getIntervalTime());
		vo.setLat(adviseInfo.getLat());
		vo.setLng(adviseInfo.getLng());
		vo.setRange(adviseInfo.getScope());
		vo.setAddress(adviseInfo.getAddress());
		vo.setPlaceName(adviseInfo.getPlaceName());
		vo.setReceiptPic(adviseInfo.getReceiptPic());
		
		int useTime = adviseInfo.getUseTime()*60;

		if(vo.getStatus().equals(1)) {
			TrafficAdviseClockLogEntity queryLog = new TrafficAdviseClockLogEntity();
			queryLog.setAdviseId(adviseId);
			List<TrafficAdviseClockLogEntity> logList = trafficAdviseClockLogDao.selectTrafficAdviseClockLogList(queryLog);
			if (logList != null && logList.size() > 0) {
				TrafficAdviseClockLogEntity logInfo = logList.get(0);
				int second = calLastedTime(logInfo.getCreateTime());
				if(second>vo.getInterval()*60) {
					second=vo.getInterval()*60;
				}else {
					vo.setNextClockInTime(vo.getInterval()*60-second);
				}
				useTime = useTime + second;
			}
		}
		
		vo.setUseTime(useTime);
		vo.setSurplusTime(vo.getDuration()*60-vo.getUseTime());

		return vo;
	}
	
	@Override
	public int getAdviseNum(String personId) {
		return trafficAdviseDao.getAdviseNum(personId);
	}
	
	public static int calLastedTime(Date startDate) { 
		long a = new Date().getTime(); 
		long b = startDate.getTime(); 
		int c = (int)((a - b) / 1000);
		return c; 
	}

	@Override
	public void handleExpireData() {
		Date now = new Date();
		List<TrafficAdviseEntity> dataList = trafficAdviseDao.queryExpireData();
		for(TrafficAdviseEntity entity : dataList) {
			entity.setStatus(5);
			entity.setUpdateTime(now);
			trafficAdviseDao.updateTrafficAdvise(entity);
			
			//推送到星软
          for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
              taskExecutor.execute(() -> starSoftSender.violationPersuasion(entity.getRelationInfo(), 0, null, startSoftApiKeyType));
          }
		}
	}

	@Override
	public void checkExecuteData() {
		Date now = new Date();
		List<TrafficAdviseEntity> dataList = trafficAdviseDao.queryExecuteData();
		for(TrafficAdviseEntity entity : dataList) {
			entity.setStatus(3);
			entity.setUseTime(0);
			entity.setUpdateTime(now);
			trafficAdviseDao.updateTrafficAdvise(entity);
		}
	}
	

}
