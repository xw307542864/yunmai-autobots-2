
package com.logibeat.cloud.core.constant;

/**
 * 
* @Title: ConstantUtil
* @Description:
* @Company: 运脉科技
* @author   wilson
* @date     2015年12月7日
 */
public class ConstantUtil extends BaseConstantUtil{



	public static final String MOBILE = "Mobile";
	public static final String EQUIPMENT = "Equipment";
	public static final String BASEUSERID="baseuserid";
	public static final String USERID = "ID";
	public static final String ENTID = "entId";
	public static final String UNICRON = "Ent-";
	public static final String AUTOBOTS = "Driver-";
	public static final String WEB = "Web-";
	public static final String WEBIM = "WebIM-";
	
	public static final String CLIENTTYPE = "ClientType";
	public static final String CLIENTSYSTEM = "ClientSystem";
	public static final String SESSIONID = "SESSIONID";
	public static final String USER_AGENT = "User-Agent";
	

 	/**
	 * 发送短信jetfire请求地址
	 */
	public static final String sendjetfireUrl ="sms/send.htm";
	
	public static final String ReqjetfireUrl = "custom/send.htm";


	public static final String ShortUrl = "short/addUrl";

	/**
	 * 推送jetfire请求地址
	 */
	public static final String pushjetfireUrl ="task/send.htm";

	public static final  String pushImModeUrl = "user/sendModeMessage.htm";

	public static final  String pushImSysUrl = "user/sendSysMessage.htm";




	// 企业车辆关系表里的车辆信息，key值，前缀加entid
	public  static  final String COOP_CAR_LIST = "coopCarList_";
	// 车辆表里的车辆信息， key值，前缀加entid
	public  static final String CAR_LIST = "carList_";
	


	public  static final String HEARTBEAT_CALLBACK_SECOND = "HERATBEAT_CALLBACK_SECOND";

	public  static final String HEARTBEAT_CALLBACK_URL = "HERATBEAT_CALLBACK_URL";

	public  static final String MESSAGE_EXCEPTION_NUM = "MESSAGE_EXCEPTION_NUM";

	public  static final String PHONE_EXCEPTION_NUM = "PHONE_EXCEPTION_NUM";
	
	public  static final String GOING_TO_TASK_SEND_CAR_MINUTES = "GOING_TO_TASK_SEND_CAR_MINUTES";
	
	public  static final String PUSH_GOING_TO_SEND_CAR_MINUTES = "PUSH_GOING_TO_SEND_CAR_MINUTES";


	public  static final String PUSH_CAR_GPS_TO_ZTO_URL = "PUSH_CAR_GPS_TO_ZTO_URL";

	public  static final String CAR_GPS_CALLBACK_ZTO_SECOND = "CAR_GPS_CALLBACK_ZTO_SECOND";


	// 发车时段操作
	public static final String OPRATION_TIME_SEND_CAR = "common/Bs/api/OperationTime/SendCar.htm";

	// 完成任务时段操作
	public static final String OPRATION_TIME_FINISH_TASK = "common/Bs/api/OperationTime/FinishTask.htm";

	// 司机同意加入企业
	public static final String OPRATION_TIME_DRIVER_JOIN_ENT = "common/Bs/api/OperationTime/DriverJoinEnt.htm";


    //更新物流端预约单状态
	public static final String UPDATE_INVITECAR_STATUS="biz/inviteCar/updStatus.htm";
	
	public static final String WATER_MARK_URL = "?x-oss-process=style/id-images-wartermark";


	public static  final  String taskInfoPage = "logibeat://bumblebee.app/feature/task/executeTask";

	public static  final  String planCarInfoPage = "logibeat://bumblebee.app/feature/task/appointmentTask";

	public static final  String myIndexPage = "logibeat://bumblebee.app/feature/my/index?";

	public static final  String carInfoPage = "logibeat://bumblebee.app/feature/car/detail?";

	public static final  String approvalPage = "logibeat://bumblebee.app/feature/ent/index?";
	
	public static final  String driverInfoPage = "logibeat://bumblebee.app/verify/driving/detail?";


	public static final String violationPage="logibeat://bumblebee.app/feature/work/violation/detail?";

	public static final  String guaranteeInfoPage = "logibeat://bumblebee.app/verify/car/insurance/detail?";

	public static final  String Change_Status_Res = "change_status-";

	public static final  String Driver_Location_Res = "driver_location-";

	public static final  String certInfoPage = "logibeat://bumblebee.app/certificates/detail?";


}
