package com.logibeat.cloud.common.annotation;import java.lang.annotation.*;/** * Created by Yujinjun on 2016/5/24. */@Target({ElementType.TYPE, ElementType.METHOD})@Retention(RetentionPolicy.RUNTIME)@Documentedpublic @interface Oauth {    String value() default "";}