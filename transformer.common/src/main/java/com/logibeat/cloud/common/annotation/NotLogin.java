package com.logibeat.cloud.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
	
	// 预留
	String value() default "";
}
