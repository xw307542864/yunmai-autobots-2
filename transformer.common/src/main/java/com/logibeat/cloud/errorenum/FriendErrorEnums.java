package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author wangxp
 */
public enum FriendErrorEnums implements ExceptionEnums {

        HAS_NO_PERSON("10001", "用户不存在"),
		PARAM_IS_NOT_NULL("10002", "参数不能为空"),
		RELATIONSHIP_VERIFICATION_FAIL("10003", "关系类型验证错误，操作失败"),
		PERSON_INFO_IS_NULL("10004", "人员信息为空，查询失败"),
		CRUX_INFO_IS_NOT_NULL("10005", "关键查询信息不能为空"),
        NAME_IS_NOT_NULL("10006", "姓名不能为空"),
        IS_OTHER_CAR("10007", "已是该企业外协车"),
        IS_SELF_DRIVER("10007", "已是该企业的企业司机"),
        IS_NOT_OTHER_CAR("10008", "不是外协车"),
        IS_NOT_OTHER_DRIVER("10009", "不是外协司机"),
		HAS_NO_ENT_DRIVER("10010", "企业司机不存在"),
		FRIEND_REQUEST_FAIL("10011", "处理好友请求失败"),
		HAS_NO_ENT_RELATIONSHIP("10012", "合作伙伴不存在"),
		SAVE_ENT_EXCEPTION("10013", "保存企业关系对象时发生异常"),
		FRIENT_NULL("10014", "该联系人不存在"),
		ALREAY_FRIEND("10015", "已是好友关系"),
		IS_INVITE_SELF_DRIVER("10016", "已经添加为企业司机"),
		IS_INVITE_OTHER_CAR("10017", "已经添加为外协车辆"),
		HAS_NO_DRIVER("10018", "司机不存在"),
		NO_ORGANIZARION("10019","没有权限查看"),
		HAS_NO_OATUTH_TO_DRIVER_ALL("10020", "您没有企业全部权限，不能添加企业全部权限司机"),
		HAS_NO_UPDATE_OATUTH_TO_DRIVER_ALL("10021", "您没有企业全部权限，不能修改企业全部权限司机"),
	    OTHER_SIDE_HAS_HANDLE("10022", "对方已添加我为合作伙伴");
	
		private String value;
		private String description;

		FriendErrorEnums(String value, String description) {
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "friend";
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
