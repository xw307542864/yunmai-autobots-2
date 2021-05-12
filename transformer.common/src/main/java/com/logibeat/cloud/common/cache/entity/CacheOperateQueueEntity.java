package com.logibeat.cloud.common.cache.entity;

import java.util.List;

public class CacheOperateQueueEntity {
	String random;
	List<CacheOperateEntity> cacheOperateList;

	public CacheOperateQueueEntity() {
		super();
	}

	public CacheOperateQueueEntity(String random, List<CacheOperateEntity> cacheOperateList) {
		super();
		this.random = random;
		this.cacheOperateList = cacheOperateList;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public List<CacheOperateEntity> getCacheOperateList() {
		return cacheOperateList;
	}

	public void setCacheOperateList(List<CacheOperateEntity> cacheOperateList) {
		this.cacheOperateList = cacheOperateList;
	}
}
