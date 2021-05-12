package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author zhangf
 */
public class ErrorEnums {
	
	/**
	 * 实例、需根据自身需求制定
	 * @author zhangf
	 *
	 */
	public enum UserErrors implements ExceptionEnums {
		
		UNKNOW("N1B70", "不知道什么错误"),
		ENT_INFO_IS_NOT_EXIST("ENT0001", "企业信息不存在"),
		OAUTH_IS_NOT_EXIST("ENT0002", "没有权限，请联系管理员"),
		BASEUSERID_IS_NOT_EXIST("ENT0003", "baseuserid不存在"),
		/**
		 * 有动态参数需要输入时，用{0}往后累加
		 */
		UNKNOW1("N1B71", "{0}还是不知道什么错误{1}");
		
		private String value;
		private String description;
		
		UserErrors(String value, String description){
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "user";
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


	
	/**
	 * 实例、需根据自身需求制定
	 * @author zhangf
	 *
	 */
	public enum DriveErrors implements ExceptionEnums{

		CREATROUTE_FAILD("001", "创建行程失败"),
		OBJECT_IS_NULL("002", "对象为空"),
		GET_ENT_LIST_FAILED("003", "司机端获得企业列表失败"),
		FIND_PAGE_NEAR_FAILED("004", "查找附近网点列表失败"),
		ENT_ID_NOT_NULL("005", "企业id不能为空"),
		NETWORK_IS_NOT_EXIST("006", "网点不存在"),
		CAR_INFO_IS_NOT_EXIST("007", "未分配车辆"),
		START_NET_IS_NOT_EXIST("008", "起点网点不存在"),
		END_NET_IS_NOT_EXIST("009", "终点网点不存在"),
		IS_NOT_SELF_DRIVER("010", "已经不是该企业的司机"),
		ENT_INFO_IS_NOT_EXIST("011", "未获得企业信息"),
		START_END_NET_IS_NOT_EXIST("012", "起点和网点都不存在"),
		ADD_CAR_INFO("013", "请先完善车辆信息"),
		NETWORK_ID_IS_NOT_NULL("014", "网点id不能为空"),
		COOP_ENT_IS_NOT_EXIST("015", "和该企业不是合作关系");
		
		private String value;
		private String description;
		
		DriveErrors(String value, String description){
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "DRIVE";
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
