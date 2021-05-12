package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class CoopEntEnums {
	public enum CoopEntErrors implements ExceptionEnums {
		ACCEPT_FRIEND_REQUEST_FAIL("001","接受好友请求失败!"),
		REFUSE_FRIEND_REQUEST_FAIL("002","拒绝好友请求失败!"),
		REPEATE_ADD_FAIL("003","已添加，不能重复添加"),
		MESSAGE_HANDLE_FAIL("004","message.handle.fail"),
		MAINTENANCE_VEHICLE_FAIL("005","请先维护车辆"),
		ENT_EXTERNAL_DRIVER_FAIL("006","已是该企业的外协司机"),
		NOT_EXTERNAL_DRIVER_FAIL("007","不是该企业的外协司机"),
		NOT_OWN_DRIVER_FAIL("008","不是该企业的自有司机"),
		ENT_NOT_EXISTENT_FAIL("009","被邀请的企业不存在，操作失败"),
		NOT_NULL_FAIL("010","参数不能为空"),
		NOT_USER_FAIL("011","用户不存在"),
		NOT_DRIVER_FAIL("011","不是该企业的司机"),
		TASK_THREAD_POOL_IS_ERROR("012","添加合作企业的线程池出错"),
		ENT_OWN_DRIVER_FAIL("013","已是该企业的自有司机"),
		ENT_REPEATE_ADD_FAIL("014","已添加该企业，待对方确认"),
		ENT_PERSON_COOP_IS_NOT_EXIST("015","企业和人员的关系不存在"),
		EXITS_COOP_ENT("016","该企业已是我的合作伙伴"),
		NOT_EXITS_COOP_ENT("017","该企业不是我的合作伙伴"),
		NOT_ADD_MYENT("018","不能添加本企业为合作伙伴"),
		STAR_LEVEL_NOT_NULL("019","星级不能为空"),
		LABLE_LIST_NOT_NULL("020","标签不能为空"),
		ENT_CONTACTS_LIST_NOT_NULL("020","接口人不能为空"),
		ENT_CONTACTS_NAME_NOT_NULL("021","接口人姓名不能为空"),
		ENT_CONTACTS_PHONE_NOT_NULL("022","接口人手机号不能为空"),
		NOT_ADD_Transportation("023","不能添加本企业为运力"),
		ENT_RALATION_IS_NOT_EXIST("024","企业合作关系不存在");
		
		private String value;
		private String description;
		
		CoopEntErrors(String value, String description){
			this.value = value;
			this.description = description;
		}
		
		@Override
		public String module() {
			return "CoopEnt";
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
