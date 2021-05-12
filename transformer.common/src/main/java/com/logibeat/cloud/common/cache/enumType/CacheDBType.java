package com.logibeat.cloud.common.cache.enumType;

/**
 * 
 * @ClassName: CacheDBType
 * @Description: 数据库类型
 * @author sean
 * @date 2016年3月8日 下午10:12:15
 * @version 1.0
 */
public enum CacheDBType {
	DataDB("单表缓存", 0), 
	BaseDB("基础数据库", 1), 
	StrategyDB("策略缓存库", 2), 
	AuthDB("账号数据库", 5), 
	CodeDB("验证码数据库", 6), 
	QueueDB("队列数据库", 8);

	private String name;
	private int value;

	CacheDBType(String name, int value) {
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
