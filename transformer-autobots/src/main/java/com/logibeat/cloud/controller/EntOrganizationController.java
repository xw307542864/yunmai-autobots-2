package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.core.dto.CooperAndDriverDtTO;
import com.logibeat.cloud.services.EntOrganizationService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 企业组织
 * @author 
 *
 */
@Controller
@RequestMapping("Driver/Org/api/EntOrganization")
@Scope("prototype")
public class EntOrganizationController extends BaseController{
	
	@Autowired
	private EntOrganizationService entOrganizationService;
	
	/**
	 * 司机端查询企业组织
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/getEntOrganization")
	@ResponseBody
	@NotLogin
	public JSONPrompt<?> getEntOrganization(String orgGuid, String entId){
		return new JSONPrompt<>(entOrganizationService.getPersonOrgList(orgGuid,entId,baseRequestDTO));
	}
	
	@RequestMapping("/getMyOrganization")
	@ResponseBody
	@NotLogin
	public JSONPrompt<?> getMyOrganization(CooperAndDriverDtTO cooperAndDriverDtTO){
		return new JSONPrompt<>(entOrganizationService.getMyOrganization(cooperAndDriverDtTO,baseRequestDTO));
	}
}
