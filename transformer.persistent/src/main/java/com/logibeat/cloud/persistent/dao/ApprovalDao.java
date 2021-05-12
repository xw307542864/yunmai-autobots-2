package com.logibeat.cloud.persistent.dao;

import org.apache.ibatis.annotations.Param;

public interface ApprovalDao {
	
	void deleteApproval(@Param("fromObjectId")String fromObjectId,
            @Param("toObjectId")String toObjectId,
            @Param("approvalType")Integer approvalType);

}
