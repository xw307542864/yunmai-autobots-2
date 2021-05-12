package com.logibeat.cloud.common.cache.annotation;

//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
public @interface CacheOperateList {
	CacheOperate[] cacheOperate() default {};
}
