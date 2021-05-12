package com.logibeat.cloud.common.cache.entity;

import com.logibeat.cloud.common.cache.annotation.CacheOperate.CacheOperateType;
import com.logibeat.cloud.common.cache.enumType.CacheAnnotationType;

public class CacheOperateObjectEntity extends CacheOperateEntity {

	private CacheOperateType cacheOperateType;

	public CacheOperateObjectEntity() {
		super();
		this.setCacheAnnotationType(CacheAnnotationType.Object);
	}

	public CacheOperateType getCacheOperateType() {
		return cacheOperateType;
	}

	public void setCacheOperateType(CacheOperateType cacheOperateType) {
		this.cacheOperateType = cacheOperateType;
	}

}
