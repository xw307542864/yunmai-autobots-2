package com.logibeat.cloud.common.cache.entity;

import com.logibeat.cloud.common.cache.annotation.CacheList.CacheListType;
import com.logibeat.cloud.common.cache.enumType.CacheAnnotationType;

public class CacheOperateListEntity extends CacheOperateEntity {

	private CacheListType cacheListType;

	private String id;

	public CacheOperateListEntity() {
		super();
		this.setCacheAnnotationType(CacheAnnotationType.List);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CacheListType getCacheListType() {
		return cacheListType;
	}

	public void setCacheListType(CacheListType cacheListType) {
		this.cacheListType = cacheListType;
	}

}
