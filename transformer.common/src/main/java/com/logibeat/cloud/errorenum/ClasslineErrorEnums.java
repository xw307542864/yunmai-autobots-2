package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class ClasslineErrorEnums {
	public enum ClasslineError implements ExceptionEnums {
		UNKNOW("001", "不知道什么错误"),
		LINE_IS_NOT_EXIST("002", "该线路不存在"),
		LINE_NAME_IS_NULL("003", "线路名称为空"),
		LINE_NAME_IS_EXIST("004", "该线路名称已存在"),
		BASE_USER_ID_IS_NOT_NULL("005", "baseUserId不能为空");
		private String value;
		private String description;
		
		ClasslineError(String value, String description){
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "CLASSLINE";
		}

		@Override
		public String getValue() {
			return this.value;
		}

		@Override
		public String getDescription() {
			return this.description;
		}
	}
}
