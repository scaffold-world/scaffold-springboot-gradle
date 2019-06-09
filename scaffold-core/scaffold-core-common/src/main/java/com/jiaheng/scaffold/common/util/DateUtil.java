package com.jiaheng.scaffold.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类-日期处理
 *
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils{

    public static final long ONE_DAY_IN_MILISECONDS = 24 * 60 * 60 * 1000;

    private static final String[] WEEK_NAMES = { "", "周日", "周一", "周二", "周三",
            "周四", "周五", "周六" };

    private static final String[] WEEK_NAMESS = { "", "星期日", "星期一", "星期二", "星期三",
            "星期四", "星期五", "星期六" };


    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    /**
     * 格式 ：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATEFORMAT_STR_001 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式 ：yyyy-MM-dd
     */
    public static final String DATEFORMAT_STR_002 = "yyyy-MM-dd";
    /**
     * 格式 ：MM-dd
     */
    public static final String DATEFORMAT_STR_003 = "MM-dd";
    /**
     * 格式 ：HH:mm:ss
     */
    public static final String DATEFORMAT_STR_004 = "HH:mm:ss";

    /**
     * 格式 ：yyyyMMddHHmmss
     */
    public static final String DATEFORMAT_STR_011 = "yyyyMMddHHmmss";
    /**
     * 格式 ：yyyyMMdd
     */
    public static final String DATEFORMAT_STR_012 = "yyyyMMdd";

    /**
     * 格式 ：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String DATEFORMAT_STR_021 = "yyyy年MM月dd日 HH时mm分ss秒";
    /**
     * 格式 ：yyyy年MM月dd日
     */
    public static final String DATEFORMAT_STR_022 = "yyyy年MM月dd日";
    /**
     * 格式 ：MM月dd日 hh:mm
     */
    public static final String DATEFORMAT_STR_023 = "MM月dd日 hh:mm";
    /**
     * 格式 ：MMddyyyy
     */
    public static final String DATEFORMAT_STR_024 = "MMddyyyy";


    public enum EDateFormatPattern
    {
        yyyy("yyyy"), yyyy_MM("yyyy-MM"), yyyyMMdd("yyyyMMdd"), yyyy_MM_dd("yyyy-MM-dd"), HH_mm_ss("HH_mm_ss"), yyyy_MM_dd_HH("yyyy-MM-dd HH"), yyyy_MM_dd_HH_mm(
            "yyyy-MM-dd HH-mm"), yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), MMdd("MMdd"), MM_dd_HH_mm_ss(
            "MM-dd HH:mm:ss"), yyyyMMddhhmmss("yyyyMMddhhmmss");

        private final String pattern;

        public String getPattern()
        {
            return this.pattern;
        }

        private EDateFormatPattern(String pattern)
        {
            this.pattern = pattern;
        }

        public static EDateFormatPattern getPattern(String pattern)
        {
            if (yyyy.getPattern().equals(pattern))
            {
                return yyyy;
            }
            else if (yyyy_MM.getPattern().equals(pattern))
            {
                return yyyy_MM;
            }
            else if (yyyyMMdd.getPattern().equals(pattern))
            {
                return yyyyMMdd;
            }
            else if (HH_mm_ss.getPattern().equals(pattern))
            {
                return HH_mm_ss;
            }
            else if (yyyy_MM_dd_HH.getPattern().equals(pattern))
            {
                return yyyy_MM_dd_HH;
            }
            else if (yyyy_MM_dd_HH.getPattern().equals(pattern))
            {
                return yyyy_MM_dd_HH;
            }
            else if (yyyy_MM_dd_HH_mm.getPattern().equals(pattern))
            {
                return yyyy_MM_dd_HH_mm;
            }
            else if (yyyy_MM_dd_HH_mm_ss.getPattern().equals(pattern))
            {
                return yyyy_MM_dd_HH_mm_ss;
            }
            else if (MMdd.getPattern().equals(pattern))
            {
                return MMdd;
            }
            else if (MM_dd_HH_mm_ss.getPattern().equals(pattern))
            {
                return MM_dd_HH_mm_ss;
            }
            else
            {
                throw new RuntimeException("日期格式不支持");
            }
        }
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

    /**
     * 日期转换为字符串 格式自定义
     *
     * @param date
     * @param f
     * @return
     */
    public static String dateStr(Date date, String f) {
        SimpleDateFormat format = new SimpleDateFormat(f);
        if (date != null) {
            String str = format.format(date);
            return str;
        }
        return "";
    }

    /**
     * 日期转换为字符串 MM月dd日 hh:mm
     *
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return dateStr(date, DATEFORMAT_STR_023);
    }


    /**
     * 日期转换为字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr2(Date date) {
        return dateStr(date, DATEFORMAT_STR_002);
    }

    /**
     * yyyy年MM月dd日HH时mm分ss秒
     *
     * @param date
     * @return
     */
    public static String dateStr5(Date date) {
        return dateStr(date, DATEFORMAT_STR_021);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String dateStr3(Date date) {
        return dateStr(date, DATEFORMAT_STR_011);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateStr4(Date date) {
        return dateStr(date, DATEFORMAT_STR_001);

    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String dateStr6(Date date) {
        return dateStr(date, DATEFORMAT_STR_022);
    }

    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String dateStr7(Date date) {
        return dateStr(date, DATEFORMAT_STR_012);
    }

    /**
     * MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr8(Date date) {
        return dateStr(date, DATEFORMAT_STR_003);
    }

    /**
     * TODO：返回mmddyyyy格式的日期字符串 如果日和月份小于10，0不显示，例：472017  （2017-04-07）
     *
     * @param date
     * @return
     * @author yangdk yangdk@erongdu.com
     * @date 2017-6-2
     */
    public static String dateStr9(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String month = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(calendar.DATE));
        String year = String.valueOf(calendar.get(calendar.YEAR));
        String dateStr = month + day + year;
        return dateStr;
    }

    /**
     * 将时间戳转换为Date
     *
     * @param times
     * @return
     */
    public static Date getDate(String times) {
        long time = Long.parseLong(times);
        return new Date(time * 1000);
    }

    /**
     * 将时间戳转换为Date(时间已经包含毫秒)
     *
     * @param times
     * @return
     */
    public static Date getDate2(String times) {
        long time = Long.parseLong(times);
        return new Date(time);
    }

    public static String dateStr(String times) {
        return dateStr(getDate(times));
    }

    public static String dateStr2(String times) {
        return dateStr2(getDate(times));
    }

    public static String dateStr3(String times) {
        return dateStr3(getDate(times));
    }

    public static String dateStr4(String times) {
        return dateStr4(getDate(times));
    }

    public static String dateStr5(String times) {
        return dateStr5(getDate(times));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param date
     * @return
     */
    public static long getTime(Date date) {
        return date.getTime() / 1000;
    }

    public static int getDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 默认的valueOf 方法，格式化 yyyy-mm-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Date valueOf(String str) {
        return valueOf(str, DATEFORMAT_STR_001);
    }


    /**
     * 自定义format格式化字符串为date
     *
     * @param str           要格式化的字符串
     * @param dateFormatStr
     * @return
     */
    public static Date valueOf(String str, String dateFormatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        ParsePosition pos = new ParsePosition(0);
        Date strtoDate = formatter.parse(str, pos);
        return strtoDate;
    }


    /**
     * @param Begin
     * @param end   传入开始时间 和 结束时间 格式如：2012-09-07
     * @return 返回Map 获取相隔多少年 get("YEAR")及为俩个时间年只差，月 天，类推 Key ： YEAR MONTH DAY 如果开始时间 晚于 结束时间 return null；
     * @author lijie
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map getApartTime(String Begin, String end) {
        String[] temp = Begin.split("-");
        String[] temp2 = end.split("-");
        if (temp.length > 1 && temp2.length > 1) {
            Calendar ends = Calendar.getInstance();
            Calendar begin = Calendar.getInstance();

            begin.set(StringUtil.toInt(temp[0]), StringUtil.toInt(temp[1]), StringUtil.toInt(temp[2]));
            ends.set(StringUtil.toInt(temp2[0]), StringUtil.toInt(temp2[1]), StringUtil.toInt(temp2[2]));
            if (begin.compareTo(ends) < 0) {
                Map map = new HashMap();
                ends.add(Calendar.YEAR, -StringUtil.toInt(temp[0]));
                ends.add(Calendar.MONTH, -StringUtil.toInt(temp[1]));
                ends.add(Calendar.DATE, -StringUtil.toInt(temp[2]));
                map.put("YEAR", ends.get(Calendar.YEAR));
                map.put("MONTH", ends.get(Calendar.MONTH) + 1);
                map.put("DAY", ends.get(Calendar.DATE));
                return map;
            }
        }
        return null;
    }

    /**
     *
     * 前/后?小时
     * @param d
     * @param hour
     * @return
     * @author yangdeke@jianbing.com
     * @date 2017-9-20
     */
    public static Date rollHour(Date d,int hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 前/后?年
     *
     * @param d
     * @param year
     * @return
     */
    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date rollDate(Date d, int year, int mon, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, mon);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取当前时间-时间戳字符串
     *
     * @return
     */
    public static String getNowTimeStr() {
        String str = Long.toString(System.currentTimeMillis() / 1000);
        return str;
    }

    /**
     * 获取当前时间-时间戳
     *
     * @return
     */
    public static int getNowTime() {
        return Integer.parseInt(StringUtil.isNull(System.currentTimeMillis() / 1000));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param time
     * @return
     */
    public static String getTimeStr(Date time) {
        long date = time.getTime();
        String str = Long.toString(date / 1000);
        return str;
    }

    public static String getTimeStr(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        String str = DateUtil.getTimeStr(date);
        return str;
    }

    public static String rollMonth(Date addTime, String time_limit) {
        Date t = DateUtil.rollDate(addTime, 0, StringUtil.toInt(time_limit), 0);
        return t.getTime() / 1000 + "";
    }

    public static String rollDay(Date addTime, String time_limit_day) {
        Date t = DateUtil.rollDate(addTime, 0, 0, StringUtil.toInt(time_limit_day));
        return t.getTime() / 1000 + "";
    }

    public static Date getIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static long getTime(String format) {
        long t = 0;
        if (StringUtil.isBlank(format))
            return t;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(format);
            t = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 获取本周日的日期
    @SuppressWarnings("unused")
    public static String getCurrentWeekday() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得本天的开始时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getCurrentDayStartDate() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND,0);
            return calendar.getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 获得本天的结束时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getCurrentDayEndDate() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得本天的起始时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDayStartTime(Date now) {
        try {
            return (new SimpleDateFormat(DATEFORMAT_STR_002)).format(now)+" 00:00:00";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得本天的结束时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDayEndTime(Date now) {
        try {
            return (new SimpleDateFormat(DATEFORMAT_STR_002)).format(now)+" 23:59:59";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得本周一的日期
    @SuppressWarnings("unused")
    public static String getMondayOFWeek() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获取当前月第一天：
    public static String getFirstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    // 获取当月最后一天
    public static String getLastDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    /**
     * 日期月份处理
     *
     * @param d     时间
     * @param month 相加的月份，正数则加，负数则减
     * @return
     */
    public static Date timeMonthManage(Date d, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(d);
        rightNow.add(Calendar.MONTH, month);
        return rightNow.getTime();
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthLastDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time, 0, 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthFirstDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time - 1, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的第一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthFirstDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的最后一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthLastDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定时间的年
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeYear(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeMonth(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的天
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeDay(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static Date getFirstSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 0);
        return cal.getTime();
    }

    /**
     * 指定天数 d + day天后的结束时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDayEndTime(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MILLISECOND,0);
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }


    /**
     * 获取指定时间天的结束时间
     *
     * @param d
     * @return
     */
    public static Date getDayEndTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的开始时间
     *
     * @param d
     * @return
     */
    public static Date getDayStartTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }
    /**
     * 获取19位的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByFullDateStr(String dateStr) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        DateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_012);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr7(date1));
            Date d2 = sdf.parse(DateUtil.dateStr7(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int hoursBetween(Date date1, Date date2) {

        DateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_012);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr7(date1));
            Date d2 = sdf.parse(DateUtil.dateStr7(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 3600000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得传入日期的年、月、日
     *
     * @param date
     */
    public static int[] yearMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int[] result = new int[3];
        result[0] = year;
        result[1] = month;
        result[2] = day;
        return result;
    }

    /**
     * 获取两个时间秒差
     */
    public static long getDatePoor(Date endDate, Date nowDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        long min = diff / 1000;
        return min;
    }
    /**
     * 获取日期的开始时间
     */
    public static String getBeginOfDate(Date date){
        String timeStr = dateStr2(date);
        return timeStr+" 00:00:00";
    }
    /**
     * 获取日期的结束时间
     */
    public static String getEndOfDate(Date date){
        String timeStr = dateStr2(date);
        return timeStr+" 23:59:59";
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取给定时间(日)
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getGiveDay(Date date, EDateFormatPattern pattern, int amount)
    {
        return DateFormatUtils.format(
                org.apache.commons.lang3.time.DateUtils.addDays(date == null ? new Date() : date, amount),
                pattern.getPattern());
    }

    /**
     *
     * TODO：判断当前时间是否为今天
     * @param date
     * @return
     * @author yangdk yangdk@erongdu.com
     * @date 2017-9-8
     */
    public static boolean isToDay(Date date){
        return dateStr2(date).equals(dateStr2(DateUtil.getNow()));
    }

    /**
     * 将date时间 扩展成一天或一个月范围
     * @param date
     * @return
     */
    public static String[] getDateRange(String date) {
        String startTime = "", endTime = "";

        if (date.length() == 7) {
            startTime = date + "-01 00:00:00";

            String pattern = "(\\d{4})-(\\d{2})";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(date);
            if (m.find()) {
                int year = Integer.parseInt(m.group(1));
                int month = Integer.parseInt(m.group(2));
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        endTime = ""+year+"-" +(month>9?"":"0")+ month + "-31 23:59:59";
                        break;
                    case 2:
                        if((year%4==0&&year%100!=0)||year%400==0){
                            endTime = ""+year+"-" +(month>9?"":"0")+ month + "-29 23:59:59";
                        }else{
                            endTime = ""+year+"-" +(month>9?"":"0")+ month + "-28 23:59:59";
                        }
                        break;
                    default:
                        endTime = ""+year+"-" +(month>9?"":"0")+ month + "-30 23:59:59";
                        break;
                }
            }
        }else{
            startTime = date + " 00:00:00";
            endTime = date + " 23:59:59";
        }

        return new String[]{startTime, endTime};
    }

    /**
     * 获得本月的开始时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentMonthStartTime(Date now) {

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(now);
            c.set(Calendar.DATE, 1);
            return (new SimpleDateFormat(DATEFORMAT_STR_002)).format(c.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 当前月的结束时间，即yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentMonthEndTime(Date now) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(now);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            return (new SimpleDateFormat(DATEFORMAT_STR_002)).format(c.getTime()) + " 23:59:59";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
