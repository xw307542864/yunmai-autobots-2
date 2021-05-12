package com.logibeat.cloud.common.enumtype;

/**
 * 
* @ClassName: InviteType 
* @Description: 申请类型
* @author sean 
* @date 2015年12月10日 上午9:29:26 
* @version 1.0
 */
public enum InviteType
{
    Unknown(0, "未知", "未知"),
    EntToDriver(1, "企业邀请自有司机", "邀请您加为企业司机"), 
    EntToCar(2, "企业邀请熟车司机", "邀请您加入其外协车队"), 
    EntToEnt(3, "企业邀请合作企业", "申请与 %s 建立合作"), 
    DriverToEnt(4, "司机申请加入企业", "司机申请加入企业"), 
    CarToEnt(5, "司机申请关注企业", "我有车 申请与 %s 建立合作"), 
    EntToEntFriend(6, "企业申请关注企业", "企业申请关注企业"),  
    Friend(7, "会员互加好友", "会员互加好友"),
    Owner(8, "自拥有", "自拥有"),
    AddPerson(9, "管理员添加企业人员", "管理员添加企业人员"),
	PersonToEnt(10, "用户申请加入企业", "申请加入 %s"),
	AddFriendDriver(11, "添加外协司机", "可加为 %s 外协运力"),
	AddEnt(12, "添加合作企业", "添加合作企业"),
    ShareCarToEnt(13, "企业共享车辆给企业", "共享车辆给企业"),
    ShareCarToPer(14, "企业共享车辆给个人", "共享车辆给个人"),
    CliamEnt(15,"认领企业","认领企业"),
    PersonOauthToEntOauth(16,"个人认证申请成为企业认证","个人认证申请成为企业认证");
	
    
    protected Integer  value;

    protected String  description;
    
    protected String  message;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    InviteType(Integer value, String description, String message)
    {
        this.value = value;
        this.description = description;
        this.message = message;
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
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InviteType getSelfByValue(String value){
    	InviteType result = null;
    	
    	switch (value) {
		case "0":
			result = InviteType.Unknown;
			break;
		case "1":
			result = InviteType.EntToDriver;
			break;
		case "2":
			result = InviteType.EntToCar;
			break;
		case "3":
			result = InviteType.EntToEnt;
			break;
		case "4":
			result = InviteType.DriverToEnt;
			break;
		case "5":
			result = InviteType.CarToEnt;
			break;
		case "6":
			result = InviteType.EntToEntFriend;
			break;
		case "7":
			result = InviteType.Friend;
			break;
        case "8":
            result = InviteType.Owner;
            break;
        case "9":
            result = InviteType.AddPerson;
            break;
        case "10":
        	result = InviteType.PersonToEnt;
        	break;
        case "11":
        	result = InviteType.AddFriendDriver;
        	break;
        case "12":
        	result = InviteType.AddEnt;
        	break;

		}
    	
    	return result;
    }    
    
    
    
}
