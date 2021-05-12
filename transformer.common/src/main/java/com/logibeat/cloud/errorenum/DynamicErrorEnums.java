package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

/**
 * @author wangxp
 */
public enum DynamicErrorEnums implements ExceptionEnums {

		SAVE_IMG_FAIL("10001", "保存图片失败"),
		TASK_CAN_NOT_REPEAT("10002", "您已接单，不能重复"),
		REJECT_THE_ORDER("10003", "您已拒绝接单，不能重复"),
		START_CAN_NOT_REPEAT("10004", "您已发车，不能重复"),
	    ARRIVE_CAN_NOT_REPEAT("100011","您已到达，不能重复"),
		FINISH_CAN_NOT_REPEAT("100010","您已完成，不到重复"),

		ORDER_IS_NOT("10005", "订单不存在，查询失败"),
        MESSAGE_DTO_FAIL("10006", "参数异常"),
        MESSAGE_FILE_NAME_NULL("10007", "文件名为空"),
        ORDER_INFO_IS_NOT("10008", "派单信息不存在"),
        MESSAGE_DYNAMIC_NOT_EXIST("10008", "动态不存在或已被删除"),
	    MESSAGE_DYNAMIC_TYPE_FAIL("10009","动态类别,参数异常"),
	    MESSAGE_DYNAMIC_equipmentType_FAIL("10010","动态客户端类型,参数异常"),
	    CAR_ID_NULL("10011", "车辆id不能为空"),
	    DRIVER_HAVE_RUNNING_TASK("10012", "司机有在途任务，不能发车"),
	    CAR_HAVE_RUNNING_TASK("10013", "车辆有在途任务，不能发车");
	
		private String value;
		private String description;

		DynamicErrorEnums(String value, String description) {
			this.value = value;
			this.description = description;
		}

		@Override
		public String module() {
			return "dynamic";
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
