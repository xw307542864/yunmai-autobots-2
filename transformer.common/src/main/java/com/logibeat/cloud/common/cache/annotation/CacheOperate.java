package com.logibeat.cloud.common.cache.annotation;

//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
public @interface CacheOperate {

	String value();
	
	String key();
	
	String[] addition() default {};
	
	Class classType();
	
	CacheOperateType cacheOperateType() default CacheOperateType.SELECT;
	
	public enum CacheOperateType {
		SELECT, ADD, DELETE, UPDATE;
	}
}
