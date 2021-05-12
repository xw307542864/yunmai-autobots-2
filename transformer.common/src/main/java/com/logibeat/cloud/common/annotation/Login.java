package com.logibeat.cloud.common.annotation;

import java.lang.annotation.*;

/**
 * 登录方法 使用该注解
 * @ClassName: Login 
 * @Description: 
 * @author kzz 
 * @date 2017年6月13日 下午3:14:02 
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

}
