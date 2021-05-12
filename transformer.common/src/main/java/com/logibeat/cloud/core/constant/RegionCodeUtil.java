package com.logibeat.cloud.core.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 城市编码工具类
 * @ClassName: RegionCodeUtil 
 * @Description: 
 * @author kzz 
 * @date 2017年8月20日 上午2:51:30 
 * @version 1.0
 */
public class RegionCodeUtil {

	/**
	 * 判断是否同城
	 * @Title: isSameCity  
	 * @param regionCode1
	 * @param regionCode2
	 * @return 
	 * boolean
	 * @date 2017年8月20日 上午3:03:34
	 *
	 */
	public static boolean isSameCity(String regionCode1, String regionCode2) {
		if(StringUtils.isBlank(regionCode1) || StringUtils.isBlank(regionCode2) || regionCode1.length()!=6 || regionCode2.length()!=6) {
			return false;
		}
		
		return regionCode1.substring(0, 4).equals(regionCode2.substring(0, 4));
	}
}
