package com.logibeat.cloud.common.cache.entity;

import com.logibeat.cloud.common.cache.enumType.CacheAnnotationType;

import java.io.Serializable;

public class CacheOperateEntity implements Serializable {

	private static final long serialVersionUID = -1466499634299512377L;
	
	private CacheAnnotationType cacheAnnotationType;

	/**
	 * 保存缓存key
	 */
	private String value;
	private String key;
	private Object entity;
	private Class classType;

	public CacheOperateEntity() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Class getClassType() {
		return classType;
	}

	public void setClassType(Class classType) {
		this.classType = classType;
	}

	public CacheAnnotationType getCacheAnnotationType() {
		return cacheAnnotationType;
	}

	public void setCacheAnnotationType(CacheAnnotationType cacheAnnotationType) {
		this.cacheAnnotationType = cacheAnnotationType;
	}

}
