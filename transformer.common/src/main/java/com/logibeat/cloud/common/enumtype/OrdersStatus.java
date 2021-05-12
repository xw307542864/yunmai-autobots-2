package com.logibeat.cloud.common.enumtype;


/**
* @ClassName: OrdersStatus 
* @Description: 订单状态
* @author Wilson 
* @date 2015年1月7日 下午2:12:24 
* @version 1.0
 */
public enum OrdersStatus
{
   
    Unknown(0,"未知（全部）"),
    
    WaitEntrust(1,"待派单(未派单) "),
    
    WaitCarrier(2,"未接单 "),
    
    WaitCar(3,"待派车"),
    
    SendToCar(8,"当前企业已派车"),
    
    WaitRun(4,"(车辆)已接单"),
    
    WaitRuning(845,"已派车"),
    
    Runing(5,"执行中（在途） "),
    
    Arrive(6,"已到达 "),
    
    Finish(7,"已完成 "),

    AlreadyEntrust(9, "已派单"),

    CreatRoute(10, "创建行程标志位"),

    QueueWait(11,"排队等待"),

    Product(12,"生产中"),

    StopProduct(13,"暂停生产"),

    Cancel(14,"已取消"),

    SEND(15,"已发货"),
    
    noFinish(101,"未完成");//包含运输中和待发车








//  /** 0: 未知（全部） */
//  public final static int Unknown = 0;
//  /** 1: 待派单(未派单) */
//  public final static int WaitEntrust = 1;
//  /** 2: 未接单 */
//  public final static int WaitCarrier = 2;
//  /** 3: 待派车(已派单) */
//  public final static int WaitCar = 3;
//  /** 4: 待发车（已派单） */
//  public final static int WaitRun = 4;
//  /** 5: 执行中（在途） */
//  public final static int Runing = 5;
//  /** 6: 已到达 */
//  public final static int Arrive = 6;
//  /** 7: 已完成 */
//  public final static int Finish = 7;
         
    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    OrdersStatus(Integer value, String description)
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


    public static OrdersStatus find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (OrdersStatus obj : OrdersStatus.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
    
}
