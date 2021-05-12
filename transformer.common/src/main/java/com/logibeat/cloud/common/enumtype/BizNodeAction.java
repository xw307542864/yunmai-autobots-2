package com.logibeat.cloud.common.enumtype;

/**
 * Created by wilson on 2017/8/11.
 */
public enum BizNodeAction {

    CREATE_ORDER(2001,"创建订单"),
    SEND_ORDER(2002, "下单"),
    CREATE_SEND_ORDER(2003, "保存并下单"),
    BACK_ORDER(2004, "撤回订单"),
    RETURN_ORDER(2005,"退回订单"),
    ACCEPT_ORDER(2006,"接单"),
    CARRIER_ORDER(2007,"承运"),
    TRANSPORT(2008,"转运"),
    LOAD(2009,"配载"),
    CANCEL_LOAD(2010,"取消配载"),
    SEND_ENT(2011,"派给企业"),
    SNED_CAR(2012,"派给车辆"),
    CALCEL_SNED_CAR(2013,"撤回派车"),
    TYD_CALCEL_SNED_CAR(20131,"托运单撤回派车"),
    DRIVER_ACCEPT(2014,"司机接单"),
    DRIVER_DEPART(2015,"发车"),
    DRIVER_ARRIVE(2016,"到达"),
    ENT_DEPART(2017,"配载单标记发车"),
    ENT_ARRIVE(2018,"配载单标记到达"),
    SIGN(2019, "签收"),
    SIGN_EXCEPTION(2020, "异常签收"),
	MANUAL_ARRIVE(2021,"手动到达"),
    DELIVERY(2022,"派送"),
    DELIVERY_CALCEL_SNED_CAR(2023,"派送撤回派车"),
    ENT_DELIVERY_DEPART(2024,"派送单标记发车"),
    ENT_DELIVERY_ARRIVE(2025,"派送单标记到达"),
    RETURN(2026,"返仓"),
    CANCEL_DELIVERY(2027,"取消派送"),
    COLLECTION(2028,"揽收"),
    //CANCEL_COLLECTION(2028,"取消揽收"),
    COLLECTION_CALCEL_SNED_CAR(2029,"揽收单取消派车"),
    ENT_COLLECTION_DEPART(2030,"揽收单标记发车"),
    ENT_COLLECTION_ARRIVE(2031,"揽收单标记到达"),
    GO_LOAD(2032,"去装货"),
    ARRIVE_LOAD(2033,"到达装货点"),
    START_LOAD(2034,"开始装货"),
    FINISH_LOAD(2035,"完成装货"),
    GO_UNLOAD(2036,"去卸货"),
    ARRIVE_UNLOAD(2037,"到达卸货点"),
    START_UNLOAD(2038,"开始卸货"),
    FINISH_UNLOAD(2039,"完成卸货");

    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param
     * @param
     */
    BizNodeAction(Integer value, String description)
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

    public static CarStatus getSelfByValue(Integer value){
        CarStatus result = null;
        switch (value) {
            case 0:
                result = CarStatus.All;
                break;
            case 1:
                result = CarStatus.Free;
                break;
            case 2:
                result = CarStatus.Ready;
                break;
            case 3:
                result = CarStatus.Running;
                break;
            case 4:
                result = CarStatus.No;
                break;

        }

        return result;
    }
}
