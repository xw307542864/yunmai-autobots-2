package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.BaseDTO;

public class EntFriendDto extends BaseDTO {

	private Integer PageIndex;

	private Integer PageSize;

	private boolean IsAll;
	
	private Integer StarMark;
	
	private String Keyword;
	
	private String EntTypeDictGUID;
	
	private Integer Friend;

	public Integer getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		PageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}

	public boolean isIsAll() {
		return IsAll;
	}

	public void setIsAll(boolean isAll) {
		IsAll = isAll;
	}

	public Integer getStarMark() {
		return StarMark;
	}

	public void setStarMark(Integer starMark) {
		StarMark = starMark;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}

	public String getEntTypeDictGUID() {
		return EntTypeDictGUID;
	}

	public void setEntTypeDictGUID(String entTypeDictGUID) {
		EntTypeDictGUID = entTypeDictGUID;
	}

	public Integer getFriend() {
		return Friend;
	}

	public void setFriend(Integer friend) {
		Friend = friend;
	}
	

}
