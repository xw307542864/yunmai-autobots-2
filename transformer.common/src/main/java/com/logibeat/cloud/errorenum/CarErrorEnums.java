package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class CarErrorEnums {

	
	public enum CarError implements ExceptionEnums {
		
		PLATE_NUMBER_IS_EXISTED("001", "车牌号已存在"),
		CAR_IS_NOT_EXISTED("002", "车牌不存在"),
		CAR_ID_IS_NOT_EXISTED("003", "车辆ID不存在"),
		CAR_INFO_NOT_EXISTED("004", "车辆信息不存在"),
		CAR_WEIHU("005", "请先维护车辆信息"),
		
		IMG_IS_NOT_NULL("0004", "图片不能为空"),
		IMG_NAME_IS_NOT_NULL("0005", "图片名称不能为空"),
		IMG_SUFFIX_IS_NOT_NULL("0006", "图片后缀不能为空"),
		FILE_NAME_IS_NOT_NULL("0007", "文件名不能为空"),
		LOGO_IS_NOT_NULL("0008", "Logo参数不能为空"),
		CHOICE_IMG("0009", "请选择图片"),
		LOGO_UP_IS_FAIL("0010", "上传Logo失败"),
		INFO_EXCEPTION_AUDIT_STATUS("0015", "非验证状态，无法执行此操作"),
		INFO_VALI_AUDIT_PARAM("0011", "参数不能为空"),
		CAR_GROUP_NOT_DELETE("0012", "该分组下有车辆，不能删除"),
        PLATE_NUMBER_IS_AUDITED("0013","该车牌已经认证过了"),
		FIRSTDRIVER_IS_SAME_AS_SECONDDRIVER("0016", "主副驾相同"),
        NOT_HANDLE_CAR("0017", "无查看车辆详情权限"),
        HAS_NO_OATUTH_TO_ALL("0018", "您没有企业全部权限，不能添加企业全部权限车辆"),
		DRIVER_CANCEL_COOPERATION("0019","对方司机已取消合作或车辆已删除，不能查看车辆详情"),
		HAS_NO_OATUTH_TO_DRIVER_ALL("0020", "您没有企业全部权限，不能添加企业全部权限司机"),
		PLATE_NUMBER_IS_NOT_NULL("0021","车牌不能为空"),
		FIRST_DRIVER_NAME_IS_NOT_NULL("0021","主驾名字不能为空"),
		FRIST_DRIVER_MOBILE_IS_NOT_NULL("0021","主驾手机不能为空");

        private String value;
        private String description;

        CarError(String value, String description){
            this.value = value;
            this.description = description;
        }

        @Override
        public String module() {
            return "Car";
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
