package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.vo.InterestLabelVo;
import com.logibeat.cloud.persistent.dao.DriverInterestLabelDao;
import com.logibeat.cloud.persistent.entity.DriverInterestLabelEntity;
import com.logibeat.cloud.services.InterestLabelService;
import com.logibeat.cloud.util.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InterestLabelServiceImpl implements InterestLabelService {
	
	@Autowired
	private DriverInterestLabelDao driverInterestLabelDao;

	@Override
	public void addInterestLabel(String baseUserId, String labels) {
		//判断数据是否存
		DriverInterestLabelEntity interestLabelEntity = driverInterestLabelDao.findByUserId(baseUserId);
		if(interestLabelEntity == null){
			interestLabelEntity = new DriverInterestLabelEntity();
			interestLabelEntity.setGuid(StringUtils.generateGUID());
			interestLabelEntity.setBaseUserId(baseUserId);
			interestLabelEntity.setCreateTime(DateUtils.getSqlTime());
			interestLabelEntity.setInterestLabel(labels);
			interestLabelEntity.setModifyTime(DateUtils.getSqlTime());
			driverInterestLabelDao.insert(interestLabelEntity);
		}else{
			interestLabelEntity.setInterestLabel(labels);
			interestLabelEntity.setModifyTime(DateUtils.getSqlTime());
			driverInterestLabelDao.update(interestLabelEntity);
		}
	}

	@Override
	public InterestLabelVo getInterestLabel(String baseUserId) {
		InterestLabelVo result = new InterestLabelVo();
		DriverInterestLabelEntity interestLabelEntity = driverInterestLabelDao.findByUserId(baseUserId);
		if(interestLabelEntity != null){
			result.setGuid(interestLabelEntity.getGuid());
			result.setBaseUserId(interestLabelEntity.getBaseUserId());
			result.setLabel(interestLabelEntity.getInterestLabel());
		}
		return result;
	}
}
