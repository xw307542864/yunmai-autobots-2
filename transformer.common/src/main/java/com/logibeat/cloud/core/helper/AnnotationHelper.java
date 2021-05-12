package com.logibeat.cloud.core.helper;

import com.logibeat.cloud.common.annotation.Login;
import com.logibeat.cloud.common.annotation.NotLogin;
import org.springframework.web.method.HandlerMethod;

public class AnnotationHelper {

	public static boolean isNotLogin(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			NotLogin notLogin = method.getMethod().getAnnotation(NotLogin.class);
			if (notLogin != null) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isLogin(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			Login login = method.getMethod().getAnnotation(Login.class);
			if (login != null) {
				return true;
			}
		}
		return false;
	}
	
	
}
