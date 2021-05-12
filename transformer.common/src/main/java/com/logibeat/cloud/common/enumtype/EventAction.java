package com.logibeat.cloud.common.enumtype;


/**
 * @author Wilson
 * @version 1.0
 * @ClassName: OrdersStatus
 * @Description: 订单状态
 * @date 2015年1月7日 下午2:12:24
 */
public enum EventAction {

    Unknown(0, "未知（全部）"),

    OrdersCreate(1, "订单创建"),

    OrdersModify(2, "订单修改（网点、货物等）"),

    OrdersDelete(3, "订单删除"),

    OrdersSendCar(4, "订单派车"),

    OrdersEntrust(5, "订单委托(派单)"),

    OrdersCarrier(6, "订单接单"),

    OrdersCancleEntrust(7, "订单取消委托"),

    OrdersCancleCarrier(8, "订单取消接单"),

    OrdersChangeCar(9, "订单改派"),

    DriverReceiv(10, "司机接单"),

    DriverRefuse(11, "司机拒绝接单"),

    DriverDeparting(12, "司机确定发车"),

    DriverArrive(13, "司机确定到达"),

    SysDeparting(14, "系统发车提醒"),

    SysArrive(15, "系统到达提醒"),

    DriverFinish(16, "司机完成任务"),

    OrdersCancleSendCar(17, "订单取消发车"),

    OrdersCancle(18, "订单改派"),

    EntFinish(19, "企业完成任务"),

    DriverDriveing(20, "司机车辆行驶"),

    DriverStop(21, "司机车辆停车"),

    RoadJam(30, "路途反馈堵车"),

    RoadClosure(31, "路途反馈封道"),

    RoadRepair(32, "路途反馈维修"),

    RoadAccident(33, "路途反馈事故"),

    RoadOther(34, "路途反馈其他"),

    RoadFault(35, "路途反馈故障"),

    Ordinary(40, "随便说"),
	
    DriverArriveEndArea(55,"司机已到达目的地"),

    ExceptionSendCar(60,"异常发车"),

    ExceptionArrive(61,"异常到达"),

    ExceptionFinish(62,"异常完成"),
    
    MuckTaskOrderSign(300,"签收"),
    
	MuckTaskOrderCreate(301,"开单"),
	
	MuckTaskOrderChange(302,"改单"),
	
	MuckTaskOrderCancel(303,"作废"),
    
    RoadWeatherRain(3601,"“大雨”"),
    
    RoadWeatherFog(3602,"“大雾”"),
    
    RoadWeatherSnow(3603,"“大雪”"),
    
    RoadWeatherCloud(3604," “大风”"),
    
    RoadInspectCar(37,"机动查车"),
  	
    RoadRefuel(38,"车辆加油"),
    
    RoadRefuelVolume(3801,"加油量:"),//---展示使用
    
    RoadRefuelMoney(3802,"金额:"),//---展示使用
    
    GoShipment(64,"前往装货点"),
    
    GoDisburden(65,"前往卸货点"),
    
    ArriveShipment(66,"到达装货点"),
    
    ArriveDisburden(67,"到达卸货点"),
    
    StartShipment(68,"开始装货"),
    
    StartDisburden(69,"开始卸货"),
    
    FinishShipment(70,"完成装货"),
    
    FinishDisburden(71,"完成卸货"),
    
    ExceptionGoShipment(72,"异常前往装货点"),
    
    ExceptionGoDisburden(73,"异常前往卸货点"),
    
    ExceptionArriveShipment(74,"异常到达装货点"),
    
    ExceptionArriveDisburden(75,"异常到达卸货点");

    protected Integer value;

    protected String description;


    /**
     * 构造函数
     *
     * @param description
     * @param value
     */
    EventAction(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value.toString();
    }

	public static String getName(int index) {
		for (EventAction c : EventAction.values()) {
			if (c.getValue() == index) {
				return c.getDescription();
			}
		}
		return null;
	}

}
