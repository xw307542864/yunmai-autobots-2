package com.logibeat.cloud.core.constant;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期时间操作工具
 * @author sean
 * @date 2015年12月15日 上午10:53:57
 * @version 1.0
 */
public class DateUtil {
    public static final String YEAR                = "yyyy";

    public static final String MONTH               = "MM";

    public static final String DAY                 = "dd";

    public static final String YYYY_MM             = "yyyy-MM";

    public static final String YYYY_MM_DD          = "yyyy-MM-dd";

    public static final String YYYYMMDD            = "yyyyMMdd";

    public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String CHN_Mode            = "yyyy年MM月dd日 HH:mm:ss EEE";

    public static final String PM_Mode             = "hh:mm a";

    public static final String yyyyMMddHHmmss      = "yyyyMMddHHmmss";



    public static final String yydd      = "MM/dd HH:mm";



    public static  String LAST_TIME=  "2099-01-01 00:00:00";

    /**
     * 
    * @Title: getTimeValue 
    * @Description: 返回当前时间 
    * @return  
    * @return Long
     */
    public static Long getTimeValue(){
        return System.currentTimeMillis();
    }
    
    public static String formatDate2SomeFormat(String date) {
        DateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df3 = new SimpleDateFormat("yyyy年MM月");
        DateFormat df4 = new SimpleDateFormat("yyyy-MM");
        DateFormat df5 = new SimpleDateFormat("yyyyMMdd");
        try {
            if (date.length() == 10) {
                date = df1.format(df2.parse(date));
            }
            else if (date.length() == 7) {
                date = df3.format(df4.parse(date));
            }
            else if (date.length() == 8) {
                date = df1.format(df5.parse(date));
            }
        } catch(ParseException e) {
            System.out.println("时间格式转换错误！");
        }
        return date;
    }
    
    public static Timestamp dateToTimestamp(Date date) {
    	if(date == null){
    		return null;
    	}
        Long timeDate = date.getTime();
        return new Timestamp(timeDate);
    }
    
    public static Timestamp dateStrToTimestamp(String dateStr) {
        Date date = dateStrToDate(dateStr, YYYY_MM_DD_HH_mm_ss);
        Long timeDate = date.getTime();
        return new Timestamp(timeDate);
    }

    public static Date dateStrToDateTime(String dateStr) {
        if (!StringUtils.isBlank(dateStr)) {
            SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
            try {
                Date date = format.parse(dateStr);
                return date;
            } catch(ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date dateStrToDate(String dateStr, String pattern) {
        if (!StringUtils.isBlank(dateStr)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            try {
                Date date = format.parse(dateStr);
                return date;
            } catch(ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getToday() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(c.getTime());
    }
    
    public static String getTodayTime() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(c.getTime());
    }

    public static String dateTOString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String dateTOString(Date date, String pattern) {
        if (date == null)
            return null;
        if (!StringUtils.isBlank(pattern)) {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
        return dateTOString(date);
    }

    /**
     *
     */
    public static Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    public static Date getOtherday(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 获取 startDate 后 hours 个小时的日期
     * @param startDate
     * @param hours
     * @return
     */
    public static Date getTimeAdd(Date startDate, int hours){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.HOUR, hours);
		SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 获取 startDate 后 minutes 天的日期
     * @param startDate
     * @param days
     * @return
     */
    public static Date getTimeAddMinutes(Date startDate, int minutes){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MINUTE, minutes);
		SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    
    /**
     * 获取 startDate 后 second 秒的日期
     * @param startDate
     * @param second
     * @return
     */
    public static Date getTimeAddSeconds(Date startDate, int second){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.SECOND, second);
		SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 获取 startDate 后 days 天的日期
     * @param startDate
     * @param days
     * @return
     */
    public static Date getTimeAddDate(Date startDate, int days){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, days);
		SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        Date date = null;
        try {
            date = sf.parse(sf.format(cal.getTime()));
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public synchronized static Timestamp getSqlTime() {
	Date date = new Date();
	SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
	date = dateStrToDateTime(sf.format(date));
        //Long timeData = System.currentTimeMillis();
	Long timeData = date.getTime();
        return new Timestamp(timeData);
    }

    /**
     * 
    * @Title: getDistanceTime 
    * @Description: 日期相减并返回字符串类型的数据 
    * @param start
    * @param end
    * @return  
    * @return String
     */
    public static String getDistanceTimeStr(Date start, Date end) {
        Long startMill = start.getTime();
        Long endMill = end.getTime();

        Long distance = endMill - startMill;

        Integer resultHour = (int) (distance / 3600000);
        Integer resultmin = (int) ((distance - (resultHour * 3600000)) / 60000);
        StringBuffer result = new StringBuffer();
        if(resultHour > 0){
        	result.append(resultHour).append("小时");
        }
        result.append(resultmin).append("分钟");
        return result.toString();
    }
    
    /**
     * 根据所秒数,计算相差的时间并以**时**分**秒返回
     * @param
     * @param
     * @return
     */
     public static String getBeapartDate(Date start, Date end)
     {
    	 Long startMill = start.getTime();
         Long endMill = end.getTime();

         Long distance = endMill - startMill;

         StringBuffer beapartdate = new StringBuffer();
         long nDay = distance/(24*60*60*1000);
         long nHour = (distance-nDay*24*60*60*1000)/(60*60*1000);
         long nMinute = (distance-nDay*24*60*60*1000-nHour*60*60*1000)/(60*1000);
//         long nSecond = (distance-nDay*24*60*60*1000-nHour*60*60*1000-nMinute*60*1000)/1000;

         if(nDay != 0L){
             beapartdate.append(nDay +"天");
         }

         if(nHour != 0L){
             beapartdate.append(nHour +"小时");
         }

         if(nMinute != 0L){
             beapartdate.append(nMinute +"分");
         }

         return beapartdate.toString();
     }

    /**
     * 
     * @Title: timestamp2Str
     * @Description: timestamp2转换为字符串
     * @param time
     * @return
     * @return String
     */
    public static String timestamp2Str(Timestamp time) {
        Date date = null;
        if (null != time) {
            date = new Date(time.getTime());
            DateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);

            return format.format(date.getTime());
        }
        else {
            return null;
        }

    }
    
    /**
     * 获取两个日期中间的所有日
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] getDateBetweenDate(Date startDate, Date endDate){
    	List<String> days = new ArrayList<>();
    	
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.YYYY_MM_DD + " 00:00:00");
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		while (true) {
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {// TODO
				days.add(df.format(startCalendar.getTime()));
			} else {
				break;
			}
		}
		
		return (String[])days.toArray(new String[days.size()]);
    }
    
    /**
     * 
    * @Title: getDistanceTime
    * @Description: 获取日期相差毫秒数
    * @param start
    * @param end
    * @return  
    * @return String
     */
    public static Long getDistanceTime(Date start, Date end) {
        if(start == null || end == null){
            return null;
        }
        Long startMill = start.getTime();
        Long endMill = end.getTime();

        Long distance = endMill - startMill;
        return distance;
    }
    
    public static Long getDistanceTime(Timestamp start, Timestamp end) {
        if(start == null || end == null){
            return null;
        }
        Long startMill = start.getTime();
        Long endMill = end.getTime();

        Long distance = endMill - startMill;
        return distance;
    }



    /**
     * 获取时间差，返回秒
     * @param start
     * @param end
     * @return
     */
    public static Long getDistanceTimeForSecond(Date start, Date end) {
        if(start == null || end == null){
            return null;
        }
        Long distance = DateUtil.getDistanceTime(start, end);
        return distance / 1000;
    }


    
    /**
     * 获取时间差，返回分钟
     * @param start
     * @param end
     * @return
     */
    public static Long getDistanceTimeForMinute(Date start, Date end) {
        if(start == null || end == null){
            return null;
        }
        Long distance = DateUtil.getDistanceTime(start, end);
        return distance / 60000;
    }
    
    /**
     * 获取时间差，返回小时
     * @param start
     * @param end
     * @return
     */
    public static Long getDistanceTimeForHours(Date start, Date end) {
        if(start == null || end == null){
            return null;
        }
        Long distance = DateUtil.getDistanceTime(start, end);
        return distance / 60000 / 60;
    }
    
    /**
     * 根据Date返回格式如20120901的long型日期数值
     *
     * @param date 日期
     * @return long型日期
     */
    public static Long getLongDate(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return Long.valueOf(sd.format(date));
    }

    /**
     * 获取日期
     *
     * @param dateNum 格式:yyyyMMdd、yyyy-MM-dd等自定义
     * @param format  日期格式。
     * @return 日期Date
     * 业务异常
     */
    public static Date getDate(Long dateNum, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        Date date = null;
        if (dateNum == null) {
            return date;
        }
        try {
            date = sd.parse(dateNum.toString());
            return date;
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static String getCurrentTime() {
        Date t = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        return sdf.format(t);
    }
    
    /**
     * 
     * @Description TODO 今天零点零分零秒
     * @param @return 参数 
     * @return Timestamp 返回类型  
     * @throws
     */
    public static Timestamp  getTodayZero(){
    	long current=System.currentTimeMillis();//当前时间毫秒数
    	long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    	return new Timestamp(zero);
    }
    
    /**
     * 
     * @Description TODO 今天23点59分59秒
     * @param @return 参数 
     * @return Timestamp 返回类型  
     * @throws
     */
    public static Timestamp  getTodayTwelve(){
    	long current=System.currentTimeMillis();//当前时间毫秒数
    	long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    	long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
    	return new Timestamp(twelve);
    }
    
    /**
     * 
     * @Description TODO  获取当前时间 到年月日
     * @param @return 参数 
     * @return String 返回类型  
     * @throws
     */
    public static String getCurrentYMDTime() {
        Date t = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        return sdf.format(t);
    }


    public static String formatTime(Long ms) {
        String result = "0";
        if(null == ms){
            return  result;
        }
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分");
        }
        result = sb.toString();
        if(StringUtils.isBlank(result)){
            result = "1分";
        }
        return result;
    }


    public static void main(String []  args){

       System.out.print(DateUtil.dateTOString(new Date(),DateUtil.yydd));

    }
    
}