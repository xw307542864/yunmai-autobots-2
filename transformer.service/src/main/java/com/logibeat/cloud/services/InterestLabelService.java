package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.InterestLabelVo;

public interface InterestLabelService {
	
    void addInterestLabel(String baseUserId, String labels);

    InterestLabelVo getInterestLabel(String baseUserId);


}
