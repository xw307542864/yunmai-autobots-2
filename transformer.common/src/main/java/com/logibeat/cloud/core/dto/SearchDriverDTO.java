package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.Page;

public class SearchDriverDTO extends Page {
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	
}
