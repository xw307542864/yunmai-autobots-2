/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/abs/toubao">JeeSite</a> All rights reserved.
 */
package com.logibeat.cloud.common.cache.util;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs){
    	if (str != null){
        	for (String s : strs){
        		if (str.equals(trim(s))){
        			return true;
        		}
        	}
    	}
    	return false;
    }
    
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}
	

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
    
    /**
     * 如果不为空，则设置值
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)){
			target = source;
		}
	}
 
    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     * @param objectString 对象串
     *   例如：row.user.id
     *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString){
    	StringBuilder result = new StringBuilder();
    	StringBuilder val = new StringBuilder();
    	String[] vals = split(objectString, ".");
    	for (int i=0; i<vals.length; i++){
    		val.append("." + vals[i]);
    		result.append("!"+(val.substring(1))+"?'':");
    	}
    	result.append(val.substring(1));
    	return result.toString();
    }
	/**
	 * 
	 * @Description: 判断字符串是否为空
	 *
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string){
		return string == null || string.length() == 0;
	}
	
	/** Base64 加密用  */
    private static final int fillchar = '=';
    private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "abcdefghijklmnopqrstuvwxyz"
                                    + "0123456789+/";
    /**
     * 
     * @Description
     *  Base64加密
     * @param str：要加密的字符串
     * @return：加密后的字符串
     */
    public static String encodeBase64(String str){
    	byte[] data = str.getBytes();

    	int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(cvt.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if (++i < len)
                c |= (data[i] >> 4) & 0x0f;

            ret.append(cvt.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if (++i < len)
                    c |= (data[i] >> 6) & 0x03;

                ret.append(cvt.charAt(c));
            }
            else {
                ++i;
                ret.append((char) fillchar);
            }

            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(cvt.charAt(c));
            }
            else {
                ret.append((char) fillchar);
            }
        }
        return ret.toString();
    }

    /**
     * 
     * @Description
     *  Base64 解密
     * @param str：要解密的Base64数据
     * @return：解密后的字符串
     */
    public static String decodeBase64(String str){
		byte[] data = str.getBytes();
		int c, c1;
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; ++i) {
		    c = cvt.indexOf(data[i]);
		    ++i;
		    c1 = cvt.indexOf(data[i]);
		    c = ((c << 2) | ((c1 >> 4) & 0x3));
		    ret.append((char) c);
		    if (++i < len) {
		        c = data[i];
		        if (fillchar == c)
		            break;
		        c = cvt.indexOf((char) c);
		        c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
		        ret.append((char) c1);
		    }
		    if (++i < len) {
		        c1 = data[i];
		        if (fillchar == c1)
		            break;
		        c1 = cvt.indexOf((char) c1);
		        c = ((c << 6) & 0xc0) | c1;
		        ret.append((char) c);
		    }
		}
		return ret.toString();
    }
    /** 随机字符串用 */
    private static Random randGen = new Random();
    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    
    /**
     * 
     * @Description
     *  随机生成制定长度的字符串
     * @param length：生成的字符串长度
     * @return：生成的字符串
     */
    public static String randomString(int length){
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }
    
    /**
     * 
     * @Description
     *  Md5加密
     * @param s：要加密的数据
     * @return：Md5加密
     */
    public static String encryptMD5(String s){
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
		'a', 'b', 'c', 'd', 'e', 'f' };   
		try {   
			byte[] strTemp = s.getBytes();   
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");   
			mdTemp.update(strTemp);   
			byte[] md = mdTemp.digest();   
			int j = md.length;   
			char str[] = new char[j * 2];   
			int k = 0;   
			for (int i = 0; i < j; i++) {   
				byte byte0 = md[i];   
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
				str[k++] = hexDigits[byte0 & 0xf];   
			}   
			return new String(str);   
		} catch (Exception e) {   
			return null;   
		}
    }
    /**
     * 
     * @Description: 生成GUID
     *
     * @return：返回GUID
     */
    public static String generateGUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
	/**
	 * 将null的字符串转换为""
	* @describe:
	* @version    1.1
	* @author  zhaojianjiang   
	* @date:2013-4-18       
	* @param str
	* @return
	 */
	public static String getNullStr(String str) {
		if (str == null)
			return "";

		if (str.equals("null") || str.equals("NULL")) {
			return "";
		} else {
			return str;
		}
	}

	public static String getNull(Object obj) {
		if (obj == null) {
			return "";
		}
		return getNullStr(obj.toString());
	}
	
	public static String toLowerCaseFirst(String str)
	{
		if(isEmpty(str))
		{
			return "";
		}
		
		str.toCharArray();
		return "";
	}
	
	public static String toUpperCaseFirst(String str)
	{
		
		return "";
	}
	
	/**
	 * 
	 * @Description TODO 将 string =string1，string2，string3 转换成 List =[string1，string2，string3]
	 * @param @param labelDictNameList
	 * @param @return 参数 
	 * @return List<String> 返回类型  stringList
	 * @throws
	 */
	public static List<String> stringArrayToString(String stringList){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotBlank(stringList)){
			String[] string = stringList.split(",") ;
	        if(string.length > 0  && !string[0].equals("")){
	            for(int i=0 ; i < string.length; i++){
	            	list.add(string[i]);
	            }
	        }
		}
        return list;
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.generateGUID());
	}
}
