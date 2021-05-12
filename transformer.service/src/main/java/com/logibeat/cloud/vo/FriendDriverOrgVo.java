package com.logibeat.cloud.vo;

import com.logibeat.cloud.common.vo.PerOrgVo;

import java.util.List;

/**
 * 组织架构司机VO
 * @author wangjie
 *
 */
public class FriendDriverOrgVo {
	/**
	 * 企业司机组织列表
	 */
	private List<PerOrgVo> orgList;
	
	/**
	 * 企业司机信息列表
	 */
	private List<FriendDriverInfoVo> driverList;
	
	/**
	 * 组织下企业司机数量
	 */
	private long driverNum;
	
	/**
	 * 企业司机总数
	 */
	private String driverTotal;

	public List<PerOrgVo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<PerOrgVo> orgList) {
		this.orgList = orgList;
	}

	public List<FriendDriverInfoVo> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<FriendDriverInfoVo> driverList) {
		this.driverList = driverList;
	}

	public long getDriverNum() {
		return driverNum;
	}

	public void setDriverNum(long driverNum) {
		this.driverNum = driverNum;
	}

	public String getDriverTotal() {
		return driverTotal;
	}

	public void setDriverTotal(String driverTotal) {
		this.driverTotal = driverTotal;
	}
}
