package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author wangxp
 */
public enum EntNetworkErrorEnums implements ExceptionEnums {

		START_NETID_IS_NOT_EXIST("10001", "开始网点id不存在"),
		END_NETID_IS_NOT_EXIST("10002", "结束网点id不存在"),
		ENT_ID_IS_NOT_EXIST("10003", "企业id不存在"),
		BASEUSER_ID_IS_NOT_EXIST("10004", "baseuserid不存在"),
		CHOICE_STATION("10005", "请选择要删除的网点"),
		STATION_IS_NULL("10006", "要删除的网点不存在"),
		CAN_NOT_DELETE_OTHER_STATION("10007", "对不起，您无权删除其他企业下的网点"),
		INPUT_STATION_NAME("10008", "请输入网点名称"),
		INPUT_STATION_DETAIL_NAME("10009", "请输入网点详细地址"),
		STATION_NAME_IS_TOO_LONG("10010", "网点名称不能多于20个字,请修改后重新提交"),
		CONTACTS_NAME_IS_TOO_LONG("10011", "联系人不能多于10个字,请修改后重新提交"),
		CONTACTS_PHONE_IS_TOO_LONG("10012", "联系电话不能多于11位数字,请重新输入"),
		STATION_IS_REPEAT("10013", "同名网点已存在，请修改后重新提交"),
		ENT_IS_NOT_SELF_DRIVER("10014", "不是企业的企业司机"),
		ENT_DRIVER_RELATION_IS_NOT_EXIST("10015", "企业和司机的关系不存在"),
		LNG_IS_NOT_NULL("10016","经度不能为空"),
		LAT_IS_NOT_NULL("10017","经度不能为空"),
		NET_WORK_IS_NOT_NULL("10018","网点唯一标识不能为空"),
		OBJECT_IS_NOT_NULL("10019","对象不能为空"),
		NETWORK_CODE_IS_TOO_LONG("10020","网点编号太长"),
		INPUT_NETWORK_CODE("10021", "请输入网点编号"),
		NETWORK_CODE_IS_REPEAT("10022", "网点编号已存在，请修改后重新提交"),
		NETWORK_CODE_IS_ERROR("10023", "网点编号不符合规范，请修改后重新提交"),
		NETWORK_IS_NOT_EXIST("10024", "网点不存在"),
		START_NET_IS_NOT_EXIST("10025", "起点网点不存在"),
		END_NET_IS_NOT_EXIST("10026", "终点网点不存在");

		private String value;
		private String description;

		EntNetworkErrorEnums(String value, String description) {
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
