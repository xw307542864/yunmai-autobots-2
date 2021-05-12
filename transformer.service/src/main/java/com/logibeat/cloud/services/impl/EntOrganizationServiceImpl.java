package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.vo.EnterpriseVo;
import com.logibeat.cloud.common.vo.OrgVo;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.CooperAndDriverDtTO;
import com.logibeat.cloud.persistent.dao.EnterpriseCooperatePerDao;
import com.logibeat.cloud.persistent.dao.EnterpriseOrganizationDictDao;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperatePerEntity;
import com.logibeat.cloud.persistent.entity.EnterpriseOrganizationDictEntity;
import com.logibeat.cloud.services.EntOrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntOrganizationServiceImpl implements EntOrganizationService {
	
	@Autowired
	private EnterpriseOrganizationDictDao enterpriseOrganizationDictDao;
	
	@Autowired
	private EnterpriseCooperatePerDao enterpriseCooperatePerDao;

	public EnterpriseVo getPersonOrgList(String orgGuid, String entId, BaseRequestDTO baseRequestDTO){
		String baseUserId = baseRequestDTO.getBaseUserId();
		EnterpriseVo result = new EnterpriseVo();
		List<OrgVo> orgList = new ArrayList<>();
		Long allPerNum = 0L;
		String appKey = baseRequestDTO.getAppKey();

		EnterpriseCooperatePerEntity per =
				enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);
		String myOrg = per.getOrgGuid();
		if(StringUtils.isNoneBlank(orgGuid)){

			List<EnterpriseOrganizationDictEntity> entOrgList = enterpriseOrganizationDictDao.getEntityListByEntIdAndParentId(entId,orgGuid);
			List<String> orgs = new ArrayList<>();
			
			allPerNum = 0L;
			Boolean all = false;
			Boolean flag = false;
			if(entOrgList.size()>0){
				for (EnterpriseOrganizationDictEntity entOrg : entOrgList) {
					Long perNum = 0L;
					all = false;
					List<String> child = this.getChildOrgList(entOrg.getGuid());
					child.add(entOrg.getGuid());
					if(child.contains(myOrg)){
						all = true;
						flag = true;
					}
					perNum = enterpriseCooperatePerDao.getEntPerNumByOrgGuid(entId, entOrg.getGuid(), false);
					if(all){
						perNum += enterpriseCooperatePerDao.getFriendDriverNum(entId, per.getOrgGuid());
					}
					OrgVo orgVo = new OrgVo();
					orgVo.setGuid(entOrg.getGuid());
					orgVo.setName(entOrg.getName());
					orgVo.setOrgLevel(entOrg.getOrgLevel().longValue());
					orgVo.setParentId(entOrg.getParentId());
					orgVo.setPerNum(perNum);
					orgList.add(orgVo);
				}
				result.setOrgVoList(orgList);
			}
			allPerNum = enterpriseCooperatePerDao.getEntPerNumByOrgGuid(entId, orgGuid, false);
			if(flag || orgGuid.equals(myOrg)){
				allPerNum += enterpriseCooperatePerDao.getFriendDriverNum(entId, per.getOrgGuid());
			}
			result.setPerNum(allPerNum);
		}
		return result;
		
	}

	public List<String> getChildOrgList(String parentOrgId){
		List<String> resultList = new ArrayList<>();
		List<EnterpriseOrganizationDictEntity> list = enterpriseOrganizationDictDao.getEntityListByEntIdAndParentId(null,parentOrgId);
		for(EnterpriseOrganizationDictEntity organizationDictEntity  : list){
			resultList.add(organizationDictEntity.getGuid());
			resultList.addAll(getChildOrgList(organizationDictEntity.getGuid()));
		}
		return  resultList;
	}

	public 	OrgVo getMyOrganization(CooperAndDriverDtTO cooperAndDriverDtTO, BaseRequestDTO baseRequestDTO){
		String baseUserId = baseRequestDTO.getBaseUserId();
		String entId = cooperAndDriverDtTO.getEntId();
		OrgVo orgVo = new OrgVo();
		String appKey = baseRequestDTO.getAppKey();
		Long perNum = 0L;
		Boolean all = false;
		EnterpriseCooperatePerEntity cooper  = enterpriseCooperatePerDao.getSelfEntPassedByEntIdAndPersonId(entId, baseUserId);
		if(cooper != null){
			String org = cooper.getOrgGuid();
			if(!"0".equals(org)){
				EnterpriseOrganizationDictEntity orgEntity = enterpriseOrganizationDictDao.select(org);
				if(orgEntity != null){
					String orgName = orgEntity.getName();
					long level = orgEntity.getOrgLevel();
					orgVo.setGuid(org);
					orgVo.setName(orgName);
					orgVo.setOrgLevel(level);
					orgVo.setParentId(orgEntity.getParentId());
					perNum = enterpriseCooperatePerDao.getEntPerNumByOrgGuid(entId, org, true);
					orgVo.setPerNum(perNum);
				}
			}

		}
		return orgVo;

	}


}
