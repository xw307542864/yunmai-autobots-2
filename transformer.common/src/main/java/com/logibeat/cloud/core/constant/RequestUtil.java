package com.logibeat.cloud.core.constant;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: RequestUtil
 * @Description:
 * @author kzz
 * @date 2017年8月9日 下午8:45:17
 * @version 1.0
 */
public class RequestUtil {

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes.getRequest();
	}

	public static Object getSessionAttribute(String name) {
		HttpServletRequest request = getRequest();
		if(request!=null) {
			HttpSession session = request.getSession(false);
			return session == null ? null : session.getAttribute(name);
		}
		return null;
	}
	
	public static void setSessionAttribute(String key, String value) {
		HttpServletRequest request = getRequest();
		if(request!=null) {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.setAttribute(key, value);
			}
		}
	}

	public static String getRealRootPath() {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getServletContext().getRealPath("/");
	}

	public static String getIp() {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getRemoteAddr();
	}

	public static Object getRequestAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getAttribute(name);
	}

	public static String getContextPath() {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getContextPath();
	}

}
