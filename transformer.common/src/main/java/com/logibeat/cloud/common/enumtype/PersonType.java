package com.logibeat.cloud.common.enumtype;


/**
* @ClassName: PersonType 
* @Description: 人员类型 
* @author sean 
* @date 2015年12月7日 上午9:12:24 
* @version 1.0
 */
public enum PersonType
{

    SuperAdmin(0, "超级管理员"),//创建者
    
    SysAdmin(1,"系统管理员"),
    
    Ent(2,"企业用户"),
    
    Driver(3,"司机用户");
         
    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param description
     * @param value
     */
    PersonType(Integer value, String description)
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
    
    
    public PersonType getSelfByValue(String value){
    	PersonType result = null;
    	
    	switch (value) {
		case "0":
            result = PersonType.SuperAdmin;
            break;
		case "1":
			result = PersonType.SysAdmin;
			break;
		case "2":
			result = PersonType.Ent;
			break;
		case "3":
			result = PersonType.Driver;
			break;

		}
    	
    	return result;
    	
    }
}
