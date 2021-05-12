package com.logibeat.cloud.core.constant;

import com.logibeat.cloud.core.properties.CommonProperties;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
* @ClassName: DataHandler 
* @Description: 数据处理
* @author sean 
* @date 2016年1月11日 上午11:50:41 
* @version 1.0
 */
public class TypeCastUtil {
    
    /**
     * 
    * @Title: byteToBoolean 
    * @Description: byte转换成boolean
    * @param source
    * @return  
    * @return Boolean
     */
    public static Boolean byteToBoolean(Byte source){
        Boolean result = null;
        if(source == null || source.equals(CommonProperties.BYTE_ZERO)){
            result = false;
        }else{
            result = true;
        }
        return result;
    }
    
    public static Byte booleanToByte(boolean source){
    	return source ? ConstantUtil.BYTE_TRUE: ConstantUtil.BYTE_FALSE;
    }
    
    
    /**
     * 
    * @Title: getMD5 
    * @Description: 生成MD5值 
    * @param content
    * @return  
    * @return String
     */
    public static String getMD5(String content) {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(content.getBytes());
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            Integer k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    
    public static String getString(String object){
        String result = null;
        if(StringUtils.isBlank(object)){
            result = "";
        }else{
            result = object;
        }
        return result;
    }
    
    
    public static Double getBigDecimalToDouble(BigDecimal source){
        Double result = null;
        if(source != null){
            result = Double.valueOf(source.toString());
        }
        return result;
    }
    
    public static BigDecimal getDoubleToBigDecimal(Double source){
        BigDecimal result = null;
        if(source != null){
            result = BigDecimal.valueOf(source);
        }
        return result;
    }
    
    /**
     * 
    * @Title: isNullOrEmpty 
    * @Description: 检查list是否为空 
    * @param source
    * @return  
    * @return Boolean
     */
    public static Boolean isNullOrEmpty(List source){
        Boolean result = false;
        if(source == null || source.isEmpty()){
            result = true;
        }
        return result;
    }
    
    public static int IntegerToInt(Integer value){
        if(value != null){
            return value;
        }else{
            return 0;
        }
    }
    
}
