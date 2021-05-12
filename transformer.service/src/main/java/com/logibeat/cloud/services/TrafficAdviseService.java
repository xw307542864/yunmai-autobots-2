package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.TrafficAdviseDetailVo;
import com.logibeat.cloud.common.vo.TrafficAdviseVo;
import com.logibeat.cloud.util.tools.pageMdl.Page;

import java.util.List;

public interface TrafficAdviseService {

	public List<TrafficAdviseVo> getList(String personId, Integer relationSafeCode, String entId, Page page);
	
	public void clockIn(String adviseId,Double lng,Double lat,String picUrl,String address,String placeName);
	
	public void receipt(String adviseId, String picUrl);
	
	TrafficAdviseDetailVo detail(String adviseId);
	
	int getAdviseNum(String personId);
	
	void handleExpireData();
	
	void checkExecuteData();
	
}
