package com.logibeat.cloud.common.valide;

import com.logibeat.cloud.util.tools.enumtype.ErrorEnums;
import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;
import com.logibeat.cloud.util.tools.exception.TException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

/**
 * 
* @ClassName: AssisUtil 
* @Description: 验证框架 _工具 可以增加一些验证规则，并抛出异常，spring框架会统一处理
* @author sean 
* @date 2015年12月29日 上午11:50:32 
* @version 1.0
 */
public class TValide{
	
    public static void notNull(Object object) {
        try {
            Assert.notNull(object);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void notNull(Object object, ExceptionEnums exceptionEnum){
        try {
            Assert.notNull(object);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void doesNotContain(String textToSearch, String substring){
        try {
            Assert.doesNotContain(textToSearch, substring);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void doesNotContain(String textToSearch, String substring, ExceptionEnums exceptionEnum){
        try {
            Assert.doesNotContain(textToSearch, substring);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void hasLength(String text, ExceptionEnums exceptionEnum) {
        try {
            Assert.hasLength(text);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void hasLength(String text) {
        try {
            Assert.hasLength(text);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void hasText(String text, ExceptionEnums exceptionEnum) {
        try {
            Assert.hasText(text);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void hasText(String text) {
        try {
            Assert.hasText(text);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        try {
            Assert.isAssignable(superType, subType);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, ExceptionEnums exceptionEnum) {
        try {
            Assert.isAssignable(superType, subType);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        try {
            Assert.isInstanceOf(clazz, obj);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
   
    public static void isInstanceOf(Class<?> type, Object obj, ExceptionEnums exceptionEnum) {
        try {
            Assert.isInstanceOf(type, obj);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
    
    public static void isNull(Object object, ExceptionEnums exceptionEnum) {
        try {
            Assert.isNull(object);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }
   
    public static void isNull(Object object) {
        try {
            Assert.isNull(object);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void isTrue(boolean expression, ExceptionEnums exceptionEnum) {
        try {
            Assert.isTrue(expression);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }

    public static void isTrue(boolean expression) {
        try {
            Assert.isTrue(expression);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
        
    }
    
    public static void noNullElements(Object[] array, ExceptionEnums exceptionEnum) {
        try {
            Assert.noNullElements(array);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }

    public static void noNullElements(Object[] array) {
        
        try {
            Assert.noNullElements(array);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void notEmpty(Object[] array, ExceptionEnums exceptionEnum) {
        
        try {
            Assert.notEmpty(array);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }

    public static void notEmpty(Object[] array) {
        
        try {
            Assert.notEmpty(array);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    public static void notEmpty(Collection<?> collection, ExceptionEnums exceptionEnum) {
        try {
            Assert.notEmpty(collection);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }

   
    public static void notEmpty(Collection<?> collection) {
        try {
            Assert.notEmpty(collection);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    
    public static void notEmpty(Map<?, ?> map, ExceptionEnums exceptionEnum) {
        try {
            Assert.notEmpty(map);
        } catch(Exception e) {
            throw new TException(exceptionEnum);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        try {
            Assert.notEmpty(map);
        } catch(Exception e) {
            throw new TException(ErrorEnums.UNKNOWEXCEPTION);
        }
    }
    
    /**
     * 
  	 * 大于指定size 则抛出异常
     * @author zhangf
     * @param string
     * @param size
     * @param message
     */
    public static void gtLength(String string, int size, ExceptionEnums exceptionEnum) {
    	// 空则不判断
    	if(StringUtils.isBlank(string)){
    		return ;
    	}
    	
    	if(string.length() > size){
    		throw new TException(exceptionEnum);
    	}
    }
    
}

