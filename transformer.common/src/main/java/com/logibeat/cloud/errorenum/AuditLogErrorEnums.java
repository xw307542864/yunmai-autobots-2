package com.logibeat.cloud.errorenum;

import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class AuditLogErrorEnums {

	public enum AuditLogError implements ExceptionEnums{
		UNKNOW("001", "不知道什么错误"),
		IMG_IS_NOT_NULL("002", "图片不能为空"),
		IMG_NAME_IS_NOT_NULL("003", "图片名称不能为空"),
		FILE_NAME_IS_NOT_NULL("004", "文件名不能为空"),
		INFO_VALI_AUDIT_PARAM("005", "参数不能为空"),
		DRIVER_CAR_NOT_EXIST("006", "司机的车不存在"),
		AUDIT_INFO_NOT_EXIST("007", "认证信息不存在"),
		PERSONAL_INFO_NOT_EXIST("008", "司机信息不存在"),
		ADDRESS_IS_NOT_NULL("009","企业地址不能为空"),
		BASEUSERID_IS_NOT_NULL("010","baseUserId不能为空"),
		CLENT_SYSTEM_IS_NOT_NULL("011","认证来源不能为空"),
		ENT_ID_IS_NOT_NULL("012","企业id不能为空"),
		ENT_TYPE_IS_NOT_NULL("013","企业类型不能为空"),
		ID_CARD_NUMBER_IS_NOT_NULL("014","身份证不能为空"),
		OWNER_NAME_IS_NOT_NULL("015","法人名字不能为空"),
		REGION_CODE_IS_NOT_NULL("016","企业名字不能为空"),
		HAND_ID_CARD_PIC_IS_NOT_NULL("017","手持身份证信息不能为空"),
		ID_CARD_BACK_PIC_IS_NOT_NULL("018","身份证背面照不能为空"),
		ID_CARD_FRONT_PIC_IS_NOT_NULL("019","身份证正面照不能为空"),
		BUSINESS_LICENSE_PIC_IS_NOT_NULL("020","工商营业执照不能为空"),
		AUDIT_STATUS_IS_WAITING("021","正在审核中，不能重新提交"),
		AUDIT_STATUS_IS_PASS("022","已通过审核，不能重新提交"),
		ENT_NAME_IS_PASS("023","企业名字不能为空"),
		ROAD_TRANSPORT_PERMIT_PIC("024","道路运输经营许可证不能为空"),
		ENT_NAME_IS_AUDIT("025","提交失败,该企业认证信息已存在"),
		SUBMIT_TYPE_IS_ERROR("026","认证提交类型错误"),
		ENT_IS_AUDITING("027","该企业已提交过认证申请，暂不能认证"),
		ID_CARD_LENGTH_IS_ERROR("028","输入的身份证号有误"),
		PERSON_NAME_IS_NOT_NULL("029","实名认证姓名不能为空"),
		COOP_PER_RELATION_IS_NOT_EXIST("030","企业与人员关系不存在"),
		IS_NOT_MANAGER("031","不是管理员，没有权限认证"),
		ENITY_IS_NOT_EXIST("032","用户不存在"),
		PERSON_VERITFY("033","公安验证失败"),
		DRIVER_LICENSE_PIC_IS_NOT_NULL("034","驾驶证照片不能为空"),
		DRIVER_LICENSE_NUMBER_IS_NOT_NULL("035","驾驶证号码不能为空"),
		DRIVER_TYPE_IS_NOT_NULL("036","驾照类型不能为空"),
		DRIVER_START_TIME_IS_NOT_NULL("037","驾照有效期开始时间不能为空"),
		DRIVER_END_TIME_IS_NOT_NULL("038","驾照有效期结束时间 不能为空"),
		CERTIFICATES_TYPE_IS_NOT_NULL("039","从业资格证书类型不能为空"),
		CERTIFICATES_PIC_IS_NOT_NULL("040","从业资格证不能为空"),
		CERTIFICATES_PIC_IS_SUBMIT("041","当前证书类型已提交，不能重复提交"),
		ORIGINALVEHICLELICENSE_IS_NOT_NULL("042","行驶证正本不能为空"),
		COPYVEHICLELICENSE_IS_NOT_NULL("043","行驶证副本不能为空"),
		VEHICLELICENSE_IS_NOT_DRIVER("044","行驶证不是个人车辆"),
		IDCARD__EXPIRE("045","证件已过期，请检查证件！"),
		VEHICLELICENSE_IS_NOT_ENT("046","行驶证不是企业车辆"),
		AUDIT_FAIL("047","提交认证失败"),
		DRIVING_LICENSE_NOT_AUDIT("048","驾驶证未认证"),
		DRIVING_LICENSE_NOT_SAME("049","您的车辆行驶证所有人姓名，与驾驶证不一致，请您核对后修改。"),

		HAVE_UNAUDIT_CAR("050","您的企业已为您分配了车辆，您可将已分配车辆进行认证，若添加新的车辆可联系车队长"),
		HAVE_WAITAUDIT_CAR("051","您已有一辆企业车辆正在审核中，请先撤回审核后再添加企业车辆。"),
		HAVE_WAITENTAUDIT_CAR("052","您已有一辆企业车辆待企业审核中，无法继续添加企业车辆，可联系车队长添加。"),
		HAVE_ENTAUDIT_CAR("053","您已有一辆企业车辆已编制，无法继续添加车辆，可联系车队长添加"),
		HAVE_AUDIT_CAR("054","每个司机只能认证1辆企业车辆，添加新的车辆可联系车队长。"),
		SECOND_DRIVING_NOT_AUDIT("055","只有主驾才可以进行车辆认证！"),
		BING_AND_AUDIT_CAR_NO_EQUALLY("056","绑定车辆和认证车辆不一致，请重新认证。");




		private String value;
		private String description;
		
		AuditLogError(String value, String description){
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
