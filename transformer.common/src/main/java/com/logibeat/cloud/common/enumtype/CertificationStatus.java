package com.logibeat.cloud.common.enumtype;

public enum CertificationStatus {


	Unknown(0, "未知"),
	waiting(1, "审核中 "),
	verified(2, "已(通过)认证"),
	failed(3, "认证失败"),
	verify(4, "申请认证 "),
    will_expire(21,"即将到期"),
    expire(22,"过期");

    protected Integer  value;

    protected String  description;

    /**
     * 构造函数
     *
     * @param code
     * @param desc
     */
    CertificationStatus(Integer value, String description)
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
    
    
    public static CertificationStatus find(Integer type) {
        if (type == null) {
            return Unknown;
        }
        for (CertificationStatus obj : CertificationStatus.values()) {
            Integer value = obj.value;
            if (value.equals(type)) {
                return obj;
            }
        }
        return Unknown;
    }
    
   

}
