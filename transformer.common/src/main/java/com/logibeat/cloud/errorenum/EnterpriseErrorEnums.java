package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author wangxp
 */
public enum EnterpriseErrorEnums implements ExceptionEnums {

		UNKNOW("10000", "未知错误"),
		DTO_IS_NOT_NULL("10001", "参数对象不能为空"),
		ENT_ID_IS_NOT_NULL("10002", "企业ID不能为空"),
		ENT_NAME_IS_HAS("10003", "需要创建的企业名已经存在，并且通过认证或正在认证,操作失败"),
		ENT_CODE_IS_HAS("10004", "需要创建的企业编号已经存在，操作失败"),
		MESSAGE_USER_NOT_EXIST("10005", "用户不存在"),
		MESSAGE_ENT_NOT_EXIST("10006", "企业不存在"),

        IMG_IS_NOT_NULL("10007", "图片不能为空"),
		IMG_NAME_IS_NOT_NULL("10008", "图片名称不能为空"),
		IMG_SUFFIX_IS_NOT_NULL("10009", "图片后缀不能为空"),
		FILE_NAME_IS_NOT_NULL("10010", "文件名不能为空"),
		LOGO_IS_NOT_NULL("10011", "Logo参数不能为空"),
		USER_IS_NOT_NULL("10012", "用户不存在"),
		CHOICE_IMG("10013", "请选择图片"),
		LOGO_UP_IS_FAIL("10014", "上传Logo失败"),
		INFO_EXCEPTION_AUDIT_STATUS("10015", "非验证状态，无法执行此操作"),
		INFO_VALI_AUDIT_PARAM("10016", "参数不能为空"),
        ENT_FIND_FAILURE("10018", "未找到相关企业，操作失败"),
        LOGOUT_ENT("5007", "您已退出企业，请重新登录."),

		EXCEL_TYPE_IS_ERROR("10019","不是Excel格式文件"),
		DICT_DATA_IS_NOT_EXIST("10020", "企业类型不存在"),
		FILE_IS_NOT_SELECTED("10021", "没有选择文件"),
		LAST_NOT_LOGOUT("10022", "最后一个企业员工不能退出企业"),
		LAST_MANAGER("10023", "请设置管理员再退出"),
		ENT_UN_EXISTS("10024","企业不存在或已被删除"),
	    ENT_PROFILE_IS_LIMIT_200("10025","企业简介字符数限制为200"),

	    HAND_IMG_IS_NULL("10026","手持身份证未上传"),
		LICENSE_IMG_IS_NULL("10027","营业执照未上传"),
		CARD_IMG_FACE_IS_NULL("10028","身份证正面照未上传"),
		CARD_IMG_BACK_IS_NULL("10029","身份证反面照未上传"),
		AUDIT_RESULT_IS_WAIT("10030","正在审核中，请耐心等待"),
	    AUDIT_RESULT_IS_UNAGREE("10031","该企业已经被认领通过了"),
	    ENT_IS_NOT_UNCLIANM_ENT("10032","该企业不是待认领的企业"),
		ENT_NAME_IS_NULL("10033","企业名称为空"),
	    ENT_IS_CERTIFICATED("1034","企业已经认证过了，不能重复认证"),
		PERSON_IS_CERTIFICATED("1036","个人已经认证过了，不能重复认证"),
	    PERSON_APPLY_TO_ENT_CERTIFICATE("1035","个人已经申请为企业认证，不能重复认证"),
		ZTO_ENTID_IS_REPEAT("10034","企业唯一标识已存在"),
		ENT_NOT_AUDIT("10035","车辆所属企业未在运脉平台认证，请联系企业管理员确认，感谢您的支持，谢谢！");


		private String value;
		private String description;

		EnterpriseErrorEnums(String value, String description) {
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "ent";
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
