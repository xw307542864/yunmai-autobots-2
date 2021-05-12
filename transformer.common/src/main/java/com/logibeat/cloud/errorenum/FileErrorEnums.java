package com.logibeat.cloud.errorenum;

import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public class FileErrorEnums {
	
	public enum FileError implements ExceptionEnums{
		
		UPLOAD_FAIL("001", "用户图片上传到服务器失败！");

        private String value;
        private String description;

        FileError(String value, String description){
            this.value = value;
            this.description = description;
        }

        @Override
        public String module() {
            return "Car";
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
	}
}
