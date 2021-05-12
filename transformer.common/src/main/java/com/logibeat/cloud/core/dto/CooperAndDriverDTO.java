package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.Page;

public class CooperAndDriverDTO extends Page {
	/**
	 * 组织id
	 */
	private String orgGuid;
	
	/**
	 * 用户id
	 */
	private String baseUserId;
	
	/**
	 * 企业id
	 */
	private String entId;
	
	/**
	 * 搜索关键字
	 */
	private String keyWord;

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
