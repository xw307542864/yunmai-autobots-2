package com.logibeat.cloud.common.enumtype;

/**
 * 
* @ClassName: FileType 
* @Description: 文件类型 
* @author sean 
* @date 2016年1月20日 上午9:22:08 
* @version 1.0
 */
public enum FileType {
    IMAGEFILE(1, "图片文件"),
    TEXTFILE(2, "文本文件"),
    XLSXFILE(3, "表格文件"),
    VIDEOFILE(4,"视频文件");
    
    private int code;
    private String name;

    private FileType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
