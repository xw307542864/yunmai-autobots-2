package com.logibeat.cloud.vo;

import com.logibeat.cloud.common.vo.OrgInfoVo;

import java.util.List;

/**
 * 按组织架构查询企业人员
 * @author wangjie
 *
 */
public class EntPerOrgVo {
	
	/**
	 * 企业人员组织列表
	 */
	private List<OrgInfoVo> orgList;
	
	/**
	 * 企业人员信息列表
	 */
	private List<EntPersonnelVo> entPersonList;
	
	/**
	 * 组织下企业人员数量
	 */
	private Long perNum;
	
	/**
	 * 企业人员总数
	 */
	private String entPersonTotal;
	
	public List<OrgInfoVo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<OrgInfoVo> orgList) {
		this.orgList = orgList;
	}

	public List<EntPersonnelVo> getEntPersonList() {
		return entPersonList;
	}

	public void setEntPersonList(List<EntPersonnelVo> entPersonList) {
		this.entPersonList = entPersonList;
	}

	public Long getPerNum() {
		return perNum;
	}

	public void setPerNum(Long perNum) {
		this.perNum = perNum;
	}

	public String getEntPersonTotal() {
		return entPersonTotal;
	}

	public void setEntPersonTotal(String entPersonTotal) {
		this.entPersonTotal = entPersonTotal;
	}
}
