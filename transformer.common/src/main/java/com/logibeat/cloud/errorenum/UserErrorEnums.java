package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class UserErrorEnums {

	/**
	 * 实例、需根据自身需求制定
	 * @author zhangf
	 *
	 */
	public enum UserErrors implements ExceptionEnums {
		UNKNOW("001", "不知道什么错误"),
		MOBILE_CODE_NOT_EXIST("002", "手机验证码不存在"),
		MOBILE_CODE_NULL("003", "手机验证码为空"),
		MOILE_CODE_ERROR("004","手机验证码错误"),
		MOBILE_IS_REGIST("005","该手机号已注册"),
		PASS_ERROR("006","密码错误"),
		PASS_NULL(" ","密码为空"),
		USER_NOT_EXIST("","用户不存在"),
		UPLOAD_HEAD_PIC_FAIL("007","上传头像失败"),
		PIC_NULL("","图片为空"),
		SOCIALLIC_IS_EXIST("008","身份证号已存在"),
		ENT_NAME_IS_EXIST("009", "需要创建的企业名已经存在，并且通过认证或正在认证,操作失败"),
		ENT_CODE_IS_EXIST("010", "需要创建的企业编号已经存在，操作失败"),
		ENT_NOT_EXIST("","企业不存在"),
		EASEMOB_REGIST_FAIL("011", "环信注册失败"),
		MANAGER_TIP_USERNAME_PASSWORD_NOT_NULL("012","用户名和密码不能为空!"),
		MANAGER_TIP_USERNAME_NOT_NULL("013","用户名不能为空"),
		MANAGER_TIP_PASSWORD_NOT_NULL("014","密码不能为空"),
		MANAGER_TIP_USERNAME_OR_PASSWORD_ERROR("015","用户名或密码错误"),
		HEAD_PIC_IS_NULL("016","头像为空"),
		OBJECT_IS_NOT_NULL("017", "对象不能为空"),
		PHONE_IS_NOT_NULL("018", "手机号码不能为空"),
		ENT_NAME_IS_NOT_NULL("019", "企业名称不能为空"),
		USERID_NULL("020", "用户ID不能为空"),
		PERSONINFO_NULL("021", "人员基础信息不存在"),
		INFO_EXCEPTION_AUDIT_STATUS("10015", "非验证状态，无法执行此操作"),
		MANAGER_NULL("022", "企业管理员不能为空"),
		LOGIN_BAN("023","已被管理员禁止登录"),
		PHONE_NULL("024", "没有获取到管理员手机号码"),
		USER_IS_REG("025","用户已注册"),
		VERIFYCODE_IS_ERROR("026","验证码错误"),
		DTO_IS_NOT_ERRORL("10100", "参数对象错误"),
		DICT_DATA_IS_NOT_EXIST("10020", "企业类型不存在"),
		REGION_CODE_IS_NULL("10021","区域码为空"),
		CREDIT_CODE_IS_EXIST("10022","信用代码已经存在"),
		SAME_ACCOUNT_IS_NOT_EXIST("10031","同一账号下不能有相同的企业"),
		ENTID_IS_NOT_NULL("10030","企业唯一标识不能为空");

		private String value;
		private String description;
		
		UserErrors(String value, String description){
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "USER";
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
