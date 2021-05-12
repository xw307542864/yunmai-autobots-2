package com.logibeat.cloud.core.helper;

import java.util.HashMap;
import java.util.Map;

public class FilePathHelper {

	private static Map<String, Object> imagePath = new HashMap<String, Object>(){
		private static final long serialVersionUID = 1L;
		{
			put("HDPATH", "D:\\images\\HdPics");
			put("SBSPATH", "D:\\images\\SbsPics");// 随便说
			put("LKPATH", "D:\\images\\LkPics");// 路况
			put("FKPATH", "D:\\images\\FkPics");// 反馈
			put("RZPATH", "D:\\images\\RZPics");
			put("ENTPATH", "D:/images/EntPics");// 企业
			put("ENTLOGOPATH", "D:/images/EntLogoPics");// 企业
			put("CARLOGOPATH", "D:\\images\\CARLogoPics");// 车辆LOGO
			put("GPSPATH", "D:\\images\\gps");// gps
			//put("9547", userMap);
		}
	};

	/**
	 * 根据业务类型获取图片上传根路径
	 * 
	 * @param key
	 * @return
	 */
	public static String getRootPathMap(String key) {
		return imagePath.get(key).toString();
	}
}
