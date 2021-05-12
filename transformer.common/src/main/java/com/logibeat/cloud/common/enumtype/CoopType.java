package com.logibeat.cloud.common.enumtype;

/**
 * 
* @ClassName: CoopType 
* @Description: (这里用一句话描述这个类的作用) 
* @author sean 
* @date 2015年12月10日 上午10:10:02 
* @version 1.0
 */
public enum CoopType
{
    Unknown(0, "未知"),
    Employee(1, "员工（同事）"), 
    Owner(2, "创建者"),
	Contact(3, "企业联系人"),		//企业联系人默认为创建者，若修改过企业联系人则cooptype变更为3
    Driver(40, "司机（所有司机含公司司机及外部司机）"), 
    WaitDriver(43,"待认证司机"),
    SelfDriver(41, "企业自己的司机"), 
    FriendDriver(42, "企业外部的司机（可以关联出外部车辆）"), 
    AddFriendDriver(44, "企业添加外协司机"), 
    Car(50, "自有车辆"),
    SelfCar(51, "企业车辆"),
    FriendCar(52, "外协车辆"),
    ShareCar(53, "分享车"),
    FriendEnt(100, "合作企业"),
    FriendEntEmployee(101,"合作企业员工"),
    DriverFriend(152, "司机好友"),
    Friend(151, "好友"),
    EntFriend(153, "企业好友"),
	EntTransportation(154, "企业运力");
    

    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param
     * @param
     */
    CoopType(Integer value, String description)
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
    
    public static CoopType getSelfByValue(Integer value) {
    	CoopType result = null;
    	
    	switch (value) {
		case 0:
			result = CoopType.Unknown;
			break;
		case 1:
			result = CoopType.Employee;
			break;
        case 2:
            result = CoopType.Owner;
            break;
        case 3:
            result = CoopType.Contact;
            break;
		case 40:
			result = CoopType.Driver;
			break;
		case 41:
			result = CoopType.SelfDriver;
			break;
		case 42:
			result = CoopType.FriendDriver;
			break;
		case 50:
			result = CoopType.Car;
			break;
		case 51:
			result = CoopType.SelfCar;
			break;
		case 52:
			result = CoopType.FriendCar;
			break;
        case 53:
            result = CoopType.ShareCar;
            break;
		case 100:
			result = CoopType.FriendEnt;
			break;
		case 152:
			result = CoopType.DriverFriend;
			break;
		case 151:
			result = CoopType.Friend;
			break;
		case 153:
			result = CoopType.EntFriend;
			break;

		}
    	
    	return result;
		
	}
}
