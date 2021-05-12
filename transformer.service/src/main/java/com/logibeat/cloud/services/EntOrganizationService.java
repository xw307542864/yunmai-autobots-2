package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.EnterpriseVo;
import com.logibeat.cloud.common.vo.OrgVo;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.CooperAndDriverDtTO;

/**
 * 企业组织业务
 * @author wangjie
 *
 */
public interface EntOrganizationService {

	EnterpriseVo getPersonOrgList(String orgGuid, String entId, BaseRequestDTO baseRequestDTO);

	/**
	 * 司机端我的组织
	 */
	OrgVo getMyOrganization(CooperAndDriverDtTO cooperAndDriverDtTO, BaseRequestDTO baseRequestDTO);

}
