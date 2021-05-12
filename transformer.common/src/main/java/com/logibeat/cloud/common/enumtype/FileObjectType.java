package com.logibeat.cloud.common.enumtype;

/**
 * 
* @ClassName: FileObjectType 
* @Description: 文件对象类型
* @author sean 
* @date 2016年1月26日 下午10:44:50 
* 
* 
* 
* @version 1.0
 */
public enum FileObjectType {
	UNKNOWN(0, "未知"),
    CASUALSAY(1, "随便说"),
    ROADCONDITION (2, "路况"),
    ROADFEEDBACK(3, "在途反馈"),
    PERSONLOGO(4,"头像"),
    PERSONAUDIT(5,"司机认证"),
    ENTAUDIT(6,"企业认证材料"),
    ENTLOGO(7,"企业LOGO"),
    CARLOGO(8,"车辆LOGO"),
    IMGROUPLOGO(9,"IM群组头像"),
    INDEXIMG(10, "首页图片");
	
    private Integer code;
    private String name;

    private FileObjectType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
