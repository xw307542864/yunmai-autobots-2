package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.GpsPackTimeVo;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;

import java.util.List;
import java.util.Set;


public interface GpsPackService {

	void sendTaskEventGps(DynamicGpsDTO dynamicGpsDTO, String equipmentId, String personId, String pushTime);

	Set<String> packGps(String gpsInfo, String personId);

	GpsPackTimeVo getGpsPackTime(String baseUserId, String userAgent);

	List<GpsPackTimeVo> getGpsSetTime(String baseUserId, String userAgent);

}
