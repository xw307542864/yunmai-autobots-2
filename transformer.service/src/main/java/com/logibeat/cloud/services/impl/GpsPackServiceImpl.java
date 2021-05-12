package com.logibeat.cloud.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logibeat.cloud.common.cache.util.HttpServletUtil;
import com.logibeat.cloud.common.vo.GpsPackTimeVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.helper.GrimlockServiceHelper;
import com.logibeat.cloud.persistent.dao.MobileSettingDao;
import com.logibeat.cloud.persistent.dao.TaskOrdersCarDao;
import com.logibeat.cloud.persistent.entity.MobileSettingEntity;
import com.logibeat.cloud.services.GpsPackService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GpsPackServiceImpl implements GpsPackService {

	private static final Logger logger = LoggerFactory.getLogger(GpsPackServiceImpl.class);

	@Autowired
	private TaskOrdersCarDao taskOrdersCarDao;

	@Autowired
	private MobileSettingDao mobileSettingDao;

	/**
	 * 司机操作任务，上传一条GPS
	 * @param dynamicGpsDTO
	 * @param personId
	 */
	public void sendTaskEventGps(DynamicGpsDTO dynamicGpsDTO, String equipmentId, String personId, String pushTime){
		if(null != dynamicGpsDTO){
//			CarGpsPhoneEntity entity = new CarGpsPhoneEntity();
//			entity.setEquipmentId(equipmentId);
//			entity.setPushTime(pushTime);
//			entity.setLatitude(dynamicGpsDTO.getLat());
//			entity.setLongitude(dynamicGpsDTO.getLng());
//			entity.setPersonId(personId);
//			entity.setAddress(dynamicGpsDTO.getAddress());
//			grimlockServiceHelper.uploadLastGps(entity);
		}
	}

	@Override
	public Set<String> packGps(String gpsInfo, String personId) {
		if(StringUtils.isBlank(gpsInfo)) {
			return null;
		}

//		Set<String> carSet = this.getCarPlates(personId);
		Gson gson = new Gson();
		List<Map<String, Object>> objectList = gson.fromJson(gpsInfo, new TypeToken<List<Map<String, Object>>>() {
		}.getType());

		// 初始化
//		List<CarGpsPhoneHistoryEntity> phoneGpsList = new ArrayList<>();

		// 循环前端传入的json参数
//		for (Map<String, Object> objectMap : objectList) {
//			Double lat = null == objectMap.get("Lat") ? null : Double.valueOf(objectMap.get("Lat") + "");
//			Double lng = null == objectMap.get("Lng") ? null : Double.valueOf(objectMap.get("Lng") + "");
//			String gpsTime = null == objectMap.get("GpsTime") ? DateUtil.getCurrentTime() : objectMap.get("GpsTime") + "";
//			String address = objectMap.get("Address") + "";
//			String equipmentId = null == objectMap.get("equipmentId") ? null :  objectMap.get("equipmentId").toString();
//			Double mileage = null == objectMap.get("mileage") ? null : Double.valueOf(objectMap.get("mileage") + "");
//
//			CarGpsPhoneHistoryEntity entity = this.createHistory(equipmentId, null, lat, lng, gpsTime, address, null, null, mileage, personId);
//			phoneGpsList.add(entity);
//		}
//
//		if(phoneGpsList != null && phoneGpsList.size() > 0){
//			// 上传到最新记录
//			CarGpsPhoneHistoryEntity history = phoneGpsList.get(phoneGpsList.size() - 1);
//			CarGpsPhoneEntity lastGps = createLast(history);
//			grimlockServiceHelper.uploadLastGps(lastGps);
//		}


		logger.info("[上传手机gps] >>>>> end.. personId：{}， gpsInfo：{}", personId, gpsInfo);
		return null;
	}


	public GpsPackTimeVo getGpsPackTime(String baseUserId, String userAgent){
		GpsPackTimeVo gpsPackTimeVo = new GpsPackTimeVo();
		userAgent="iphone";
		MobileSettingEntity entity = null;
		entity = mobileSettingDao.getMobileSettingByModel(userAgent, ConstantUtil.BYTE_FALSE);
		Integer postTime = null;
		Integer lastPostTtime = null;
		Integer collectTtime = null;
		Integer seconds = null;
		Integer distance = null;
		if(entity == null){
			userAgent = "other";
			entity = mobileSettingDao.getMobileSettingByModel(userAgent, ConstantUtil.BYTE_FALSE);
		}
		if(entity != null){
			postTime = entity.getGpsUpload();//gps上传频率
			lastPostTtime = entity.getGpsAcquisition();//gps采集频率
			collectTtime = entity.getGpsHistory();//gps历史
			seconds = entity.getSeconds();//上传时间间隔，秒
			distance = entity.getDistance();//上传距离,米
		}

		Long unfinishTaskCount = taskOrdersCarDao.getUnfinishTaskCount(baseUserId, null);
		if(unfinishTaskCount > 0){
			entity = mobileSettingDao.getMobileSettingByModel(userAgent, ConstantUtil.BYTE_TRUE);
			if(entity == null){
				userAgent = "other";
				entity = mobileSettingDao.getMobileSettingByModel(userAgent, ConstantUtil.BYTE_TRUE);
			}
			if(entity != null){
				postTime = entity.getGpsUpload();//gps上传频率
				lastPostTtime = entity.getGpsAcquisition();//gps采集频率
				collectTtime = entity.getGpsHistory();//gps历史
				seconds = entity.getSeconds();//上传时间间隔，秒
				distance = entity.getDistance();//上传距离,米
			}
		}
		gpsPackTimeVo.setUploadLastGpsTime(postTime);
		gpsPackTimeVo.setCollectTime(collectTtime);
		gpsPackTimeVo.setUploadHistoryGpsTime(lastPostTtime);
		gpsPackTimeVo.setSeconds(seconds);
		gpsPackTimeVo.setDistance(distance);
		return gpsPackTimeVo;
	}

	@Override
	public List<GpsPackTimeVo> getGpsSetTime(String baseUserId, String userAgent) {
		List<MobileSettingEntity> mobileSettingList = new ArrayList<>();
		if(StringUtils.isNotBlank(userAgent)){
			List<MobileSettingEntity> mobileList0 = mobileSettingDao.getMobileSettingList().parallelStream().filter(p->p.getIsTask().equals(new Byte("0"))).collect(Collectors.toList());
			for(MobileSettingEntity entity : mobileList0){
				if(userAgent.toLowerCase().contains(entity.getPhoneModel().toLowerCase())){
					mobileSettingList.add(entity);
					break ;
				}
			}

			List<MobileSettingEntity> mobileList1 = mobileSettingDao.getMobileSettingList().parallelStream().filter(p->p.getIsTask().equals(new Byte("1"))).collect(Collectors.toList());
			for(MobileSettingEntity entity : mobileList1){
				if(userAgent.toLowerCase().contains(entity.getPhoneModel().toLowerCase())){
					mobileSettingList.add(entity);
					break ;
				}
			}
		}

		if(mobileSettingList.isEmpty() || mobileSettingList.size() < 1){
			mobileSettingList = mobileSettingDao.getListByModel("other");
		}

		List<GpsPackTimeVo> result = new ArrayList<>();
		for(MobileSettingEntity entity : mobileSettingList){
			GpsPackTimeVo gpsPackTimeVo = new GpsPackTimeVo();
			gpsPackTimeVo.setPhoneModel(entity.getPhoneModel());
			gpsPackTimeVo.setUploadLastGpsTime(entity.getGpsUpload());//最新上传频率
			gpsPackTimeVo.setCollectTime(entity.getGpsAcquisition()); //采集频率
			gpsPackTimeVo.setUploadHistoryGpsTime(entity.getGpsHistory());//历史频率
			gpsPackTimeVo.setSeconds(entity.getSeconds()); //上传时间间隔，秒
			gpsPackTimeVo.setDistance(entity.getDistance()); //上传距离,米
			gpsPackTimeVo.setIsTask(entity.getIsTask()!= null&&entity.getIsTask() == 1?true:false);
			result.add(gpsPackTimeVo);
			logger.info("userAgent:"+userAgent+" ,<>数据库返回机型:"+entity.getPhoneModel());
		}
		return result;
	}

	private String getEquipmentId() {
		HttpServletRequest request = HttpServletUtil.getRequest();
		String equipmentId = request.getHeader("equipment");
		return equipmentId;
	}

}