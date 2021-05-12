package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author wangxp
 */
public enum CertErrorEnums implements ExceptionEnums {

		CERT_ALREADY("10001", "证件已经被注销，请联系主管部门咨询！"),
		NOT_EXIT("10002","数据不存在"),
		CERT_EXIT("10003","证件已存在"),
		NAME_AUDIT("1004","请先实名认证"),
		NAME_DIFFERENT("1005","姓名与实名信息不一致，请修改！"),
		ALREADY_EXIT("1006","当前证件类型已添加，不可重复添加！"),
		NUMBER_DIFFERENT("1007","身份证号与实名信息不一致，请修改！"),
		DRIVER_NOT_EXIT("1008","司机不存在"),
		ENT_NOT_EXIT("1009","企业不存在")
		;

		private String value;
		private String description;

		CertErrorEnums(String value, String description) {
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "ent_net";
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
