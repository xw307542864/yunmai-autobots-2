package com.logibeat.cloud.common.enumtype;


/**
 * 
* @ClassName: BizOrdersStatus 
* @Description: 业务单据之订单状态 
* @author sean 
* @date 2017年5月23日 下午2:35:49 
* @version 1.0
 */
public enum BizOrdersStatus {
   
    Unknown(0,"未知（全部）"),
    
    CreateOrder(101,"创建订单"),

    BeforeSender(102,"待下单"),
    
    SendOrder(103,"待接单"),//待接单

    ReceiveOrder(201,"待承运"),//接单之后待承运
    
    CarrierOrder(202,"承运"),//已承运

    BackOrder(301,"主动撤回"),

    CancelOrder(302,"取消"),

    RefuseOrder(303,"系统退回"),
    
    RefuseTakeUp(304,"拒绝接单"),
    
    WaitConfigure(401,"待配载"),

    Signed(402,"已签收"),

    WaitSendCar(403,"待派车"),
    
    SendCar(404,"派车中"),
    
    Transfer(405,"转运中"),

    PendingDeparture(4,"待发车"),
	
	Runing(5,"在途中 "),
	    
	Arrive(6,"已到达 "),

    WaitSendOrder(501,"待派送"),

    WaitOutOfStorage(502,"待出库"),

    SendingOrder(503,"派送中"),

    WaitSign(504,"待签收"),

    WaitCollect(601,"待揽收"),

    Collecting(602,"揽收中");

    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    BizOrdersStatus(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return value.toString();
    }
    
    
    public static BizOrdersStatus getSelfByValue(Integer value){
        BizOrdersStatus result = null;
    	switch (value) {
		case 101:
			result = BizOrdersStatus.CreateOrder;
			break;
		case 102:
			result = BizOrdersStatus.BeforeSender;
			break;
		case 103:
            result = BizOrdersStatus.SendOrder;
            break;
		case 201:
			result = BizOrdersStatus.ReceiveOrder;
			break;
		case 202:
			result = BizOrdersStatus.CarrierOrder;
			break;
        case 301:
            result = BizOrdersStatus.BackOrder;
            break;
        case 302:
            result = BizOrdersStatus.CancelOrder;
            break;
        case 303:
            result = BizOrdersStatus.RefuseOrder;
            break;
        case 304:
            result = BizOrdersStatus.RefuseTakeUp;
            break;
        case 401:
            result = BizOrdersStatus.WaitConfigure;
            break;
        case 402:
            result = BizOrdersStatus.Signed;
            break;
        case 403:
            result = BizOrdersStatus.WaitSendCar;
            break;
        case 404:
            result = BizOrdersStatus.SendCar;
            break;
        case 4:
            result = BizOrdersStatus.PendingDeparture;
            break;
        case 5:
            result = BizOrdersStatus.Runing;
            break;
        case 6:
            result = BizOrdersStatus.Arrive;
            break;
        case 501:
            result = BizOrdersStatus.WaitSendOrder;
            break;
        case 502:
            result = BizOrdersStatus.WaitOutOfStorage;
            break;
        case 503:
            result = BizOrdersStatus.SendingOrder;
            break;
        case 504:
            result = BizOrdersStatus.WaitSign;
            break;
        case 601:
            result = BizOrdersStatus.WaitCollect;
            break;
        case 602:
            result = BizOrdersStatus.Collecting;
            break;
		default:
			    result = BizOrdersStatus.Unknown;
        }
        return result;
    }
    public static BizOrdersStatus getStatusByValue(Integer value){
        BizOrdersStatus result = null;
        switch (value) {
            case 401:
                result = BizOrdersStatus.WaitConfigure;
                break;
            case 402:
                result = BizOrdersStatus.Signed;
                break;
            case 403:
                result = BizOrdersStatus.WaitSendCar;
                break;
            case 404:
                result = BizOrdersStatus.SendCar;
                break;
            case 4:
                result = BizOrdersStatus.PendingDeparture;
                break;
            case 5:
                result = BizOrdersStatus.Runing;
                break;
            case 6:
                result = BizOrdersStatus.Arrive;
                break;
            case 501:
                result = BizOrdersStatus.WaitSendOrder;
                break;
            case 502:
                result = BizOrdersStatus.WaitOutOfStorage;
                break;
            case 503:
                result = BizOrdersStatus.SendingOrder;
                break;
            case 504:
                result = BizOrdersStatus.WaitSign;
                break;
            case 601:
                result = BizOrdersStatus.WaitCollect;
                break;
            case 602:
                result = BizOrdersStatus.Collecting;
                break;
            default:
                result = BizOrdersStatus.Unknown;
        }
        return result;
    }
}
