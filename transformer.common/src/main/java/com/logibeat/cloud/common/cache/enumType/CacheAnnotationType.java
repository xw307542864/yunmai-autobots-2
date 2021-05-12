package com.logibeat.cloud.common.cache.enumType;

public enum CacheAnnotationType {
	List("缓存集合", 0), 
	Object("缓存实体", 1);

	private String name;
	private int value;

	CacheAnnotationType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
