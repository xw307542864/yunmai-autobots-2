package com.logibeat.cloud.common.enumtype;


/**
 * 
* @ClassName: InviteState 
* @Description: 邀请状态
* @author sean 
* @date 2015年12月10日 上午11:20:07 
* @version 1.0
 */
public enum InviteState
{
    Unknown(0, "未知"),
    Pass(1, "已审核（审核通过） "), 
    Refuse(2, "已审核（审核不通过） "), 
    Stop(3, "终止（关注状态） "), 
    Blacklist(4, "黑名单 "), 
    Temporary(5, "临时会话 "),
    Wait(6, "待审核 "),

    //当InviteType为AddPerson时使用
    NotActive(7, "未激活"),
    Activation(8, "已激活"),
    ACKNOWLEDGEMENT(9,"待确认");
	
    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    InviteState(Integer value, String description)
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
    
    
    
    
    public InviteState getSelfByValue(String value){
        InviteState result = null;
        switch (value) {
		case "0":
			result = InviteState.Unknown;
			break;
		case "1":
			result = InviteState.Pass;
			break;
		case "2":
			result = InviteState.Refuse	;
			break;
		case "3":
			result = InviteState.Stop;
			break;
		case "4":
			result = InviteState.Blacklist;
			break;
		case "5":
			result = InviteState.Blacklist;
			break;
        case "6":
            result = InviteState.Wait;
            break;
        case "7":
            result = InviteState.NotActive;
            break;
        case "8":
            result = InviteState.Activation;
            break;
		}
        
        return result;
    }
}
