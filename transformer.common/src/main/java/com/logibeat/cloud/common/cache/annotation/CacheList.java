package com.logibeat.cloud.common.cache.annotation;

//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
public @interface CacheList {

//	String[] value() default {};
	
	String key();
	
	String[] addition() default {};
	
	String id() default "guid";
	
	Class classType();
	
	CacheListType cacheListType() default CacheListType.SELECT;
	
	public enum CacheListType {
		ADD, DELETE, UPDATE, SELECT;
	}
}
