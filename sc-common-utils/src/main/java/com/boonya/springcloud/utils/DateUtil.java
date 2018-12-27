package com.boonya.springcloud.utils;

import org.springframework.util.Assert;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @ClassName: DateUtil
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-21 14:40
 */
public class DateUtil {

    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 转换时间 格式： yyyy-MM-dd HH:mm:ss
     *
     * @MethodName: getDateFormat
     * @Description:
     * @param date
     * @return
     */

    public static String getDateFormat(Date date) {
        Assert.notNull(date, "时间不能为空");
        String dateResult = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_STANDARD);
        dateResult = dateFormat.format(date);
        return dateResult;
    }

    /**
     * 转换日期 格式： yyyy-MM-dd
     *
     * @MethodName: getDateFormat
     * @Description:
     * @param date
     * @return
     */
    public static String getDateTooFormat(Date date) {
        Assert.notNull(date, "日期不能为空");
        String dateResult = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
        dateResult = dateFormat.format(date);
        return dateResult;
    }

    /**
     * timestamp格式化时间串
     *
     * @MethodName: timestamp2String
     * @Description:
     * @param timestamp
     * @param pattern
     * @return
     * @throws
     */
    public static String timestamp2String(Timestamp timestamp, String pattern) {
        if (timestamp == null) {
            throw new java.lang.IllegalArgumentException(
                    "timestamp null illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(timestamp.getTime()));
    }

    /**
     * 时间类型转字符串类型
     *
     * @MethodName: date2String
     * @Description:
     * @param date
     * @param pattern
     * @return
     * @throws
     */
    public static String date2String(java.util.Date date, String pattern) {
        if (date == null) {
            throw new java.lang.IllegalArgumentException(
                    "timestamp null illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Date tostring方法
     *
     * @MethodName: date2String
     * @Description:
     * @param date
     * @return
     * @throws
     */
    public static String date2String(java.util.Date date) {
        return date2String(date, null);
    }

    /**
     * 获取当前时间的Timestamp
     *
     * @MethodName: currentTimestamp
     * @Description:
     * @return
     * @throws
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取当前时间的Timestamp转为字符串格式
     *
     * @MethodName: currentTimestamp2String
     * @Description:
     * @param pattern
     * @return
     * @throws
     */
    public static String currentTimestamp2String(String pattern) {
        return timestamp2String(currentTimestamp(), pattern);
    }

    /**
     * 字符串类型转Timestamp
     *
     * @MethodName: string2Timestamp
     * @Description:
     * @param strDateTime
     * @param pattern
     * @return
     * @throws
     */
    public static Timestamp string2Timestamp(String strDateTime, String pattern) {
        if (strDateTime == null || strDateTime.equals("")) {
            throw new java.lang.IllegalArgumentException(
                    "Date Time Null Illegal");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = PATTERN_STANDARD;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(strDateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 字符串类型转Date
     *
     * @MethodName: string2Date
     * @Description:
     * @param strDate
     * @param pattern
     * @return
     * @throws
     */
    public static Date string2Date(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("str date null");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = DateUtil.PATTERN_DATE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 获取字符串时间中的年
     *
     * @MethodName: stringToYear
     * @Description:
     * @param strDest
     * @return
     * @throws
     */
    public static String stringToYear(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new java.lang.IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    /**
     * 获取字符串时间中的月
     *
     * @MethodName: stringToMonth
     * @Description:
     * @param strDest
     * @return
     * @throws
     */
    public static String stringToMonth(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new java.lang.IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        if (month < 10) {
            return "0" + month;
        }
        return String.valueOf(month);
    }

    /**
     * 获取字符串时间中的日
     *
     * @MethodName: stringToDay
     * @Description:
     * @param strDest
     * @return
     * @throws
     */
    public static String stringToDay(String strDest) {
        if (strDest == null || strDest.equals("")) {
            throw new java.lang.IllegalArgumentException("str dest null");
        }

        Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            return "0" + day;
        }
        return "" + day;
    }

    /**
     * 获取日历中当前年月的第一天
     *
     * @MethodName: getFirstDayOfMonth
     * @Description:
     * @param c
     * @return
     * @throws
     */
    public static Date getFirstDayOfMonth(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取日历中当前年月的最后一天
     *
     * @MethodName: getLastDayOfMonth
     * @Description:
     * @param c
     * @return
     * @throws
     */
    public static Date getLastDayOfMonth(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = 1;
        if (month > 11) {
            month = 0;
            year = year + 1;
        }
        c.set(year, month, day - 1, 0, 0, 0);
        return c.getTime();
    }

    /**
     * Date转XMLGregorianCalendar字符串
     *
     * @MethodName: date2GregorianCalendarString
     * @Description:
     * @param date
     * @return
     * @throws
     */
    public static String date2GregorianCalendarString(Date date) {
        if (date == null) {
            throw new java.lang.IllegalArgumentException("Date is null");
        }
        long tmp = date.getTime();
        GregorianCalendar ca = new GregorianCalendar();
        ca.setTimeInMillis(tmp);
        try {
            XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory
                    .newInstance().newXMLGregorianCalendar(ca);
            return t_XMLGregorianCalendar.normalize().toString();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new java.lang.IllegalArgumentException("Date is null");
        }

    }

    /**
     * 比较两个时间的日期是否相等
     *
     * @MethodName: compareDate
     * @Description:
     * @param firstDate
     * @param secondDate
     * @return
     * @throws
     */
    public static boolean compareDate(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new java.lang.RuntimeException();
        }

        String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
        String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
        if (strFirstDate.equals(strSecondDate)) {
            return true;
        }
        return false;
    }

    /**
     * 获取日期Date的每日开始时间（00:00:00）
     *
     * @MethodName: getStartTimeOfDate
     * @Description:
     * @param currentDate
     * @return
     * @throws
     */
    public static Date getStartTimeOfDate(Date currentDate) {
        Assert.notNull(currentDate);
        String strDateTime = date2String(currentDate, "yyyy-MM-dd")
                + " 00:00:00";
        return string2Date(strDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取日期Date的每日结束时间（ 59:59:59）
     *
     * @MethodName: getEndTimeOfDate
     * @Description:
     * @param currentDate
     * @return
     * @throws
     */
    public static Date getEndTimeOfDate(Date currentDate) {
        Assert.notNull(currentDate);
        String strDateTime = date2String(currentDate, "yyyy-MM-dd")
                + " 59:59:59";
        return string2Date(strDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到今日的起始时间
     *
     * @MethodName: getTodayStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getTodayStartEnd() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        Calendar todayEnd = Calendar.getInstance();
        String _todayStart = DateUtil.date2String(todayStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _todayEnd = DateUtil.date2String(todayEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _todayStart, _todayEnd };
    }

    /**
     * 得到昨日的起始时间
     *
     * @MethodName: getYesterdayStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getYesterdayStartEnd() {
        Calendar yesterdayStart = Calendar.getInstance();
        yesterdayStart.add(Calendar.DATE, -1);
        yesterdayStart.set(Calendar.HOUR_OF_DAY, 0);
        yesterdayStart.set(Calendar.MINUTE, 0);
        yesterdayStart.set(Calendar.SECOND, 0);

        Calendar yesterdayEnd = Calendar.getInstance();
        yesterdayEnd.add(Calendar.DATE, -1);
        yesterdayEnd.set(Calendar.HOUR_OF_DAY, 23);
        yesterdayEnd.set(Calendar.MINUTE, 59);
        yesterdayEnd.set(Calendar.SECOND, 59);

        String _todayStart = DateUtil.date2String(yesterdayStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _todayEnd = DateUtil.date2String(yesterdayEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _todayStart, _todayEnd };
    }

    /**
     * 得到本周的起始时间(上周六到本周六)
     *
     * @MethodName: getCurrWeekStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getCurrWeekStartEnd() {
        Calendar currWeekStart = Calendar.getInstance();
        currWeekStart.add(Calendar.DATE, -7);
        currWeekStart.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        currWeekStart.set(Calendar.HOUR_OF_DAY, 0);
        currWeekStart.set(Calendar.MINUTE, 0);
        currWeekStart.set(Calendar.SECOND, 0);

        Calendar currWeekEnd = Calendar.getInstance();
        currWeekEnd.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        currWeekEnd.set(Calendar.HOUR_OF_DAY, 23);
        currWeekEnd.set(Calendar.MINUTE, 59);
        currWeekEnd.set(Calendar.SECOND, 59);

        String _currWeekStart = DateUtil.date2String(currWeekStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _currWeekEnd = DateUtil.date2String(currWeekEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _currWeekStart, _currWeekEnd };
    }

    /**
     * 得到上周的起始时间(上上周六到上周六)
     *
     * @MethodName: getLastWeekStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getLastWeekStartEnd() {
        Calendar lastWeekStart = Calendar.getInstance();
        lastWeekStart.add(Calendar.DATE, -14);
        lastWeekStart.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        lastWeekStart.set(Calendar.HOUR_OF_DAY, 0);
        lastWeekStart.set(Calendar.MINUTE, 0);
        lastWeekStart.set(Calendar.SECOND, 0);

        Calendar lastWeekEnd = Calendar.getInstance();
        lastWeekEnd.add(Calendar.DATE, -7);
        lastWeekEnd.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        lastWeekEnd.set(Calendar.HOUR_OF_DAY, 23);
        lastWeekEnd.set(Calendar.MINUTE, 59);
        lastWeekEnd.set(Calendar.SECOND, 59);

        String _lastWeekStart = DateUtil.date2String(lastWeekStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _lastWeekEnd = DateUtil.date2String(lastWeekEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _lastWeekStart, _lastWeekEnd };
    }

    /**
     * 得到本月的起始时间(当月1号到本月最后一天)
     *
     * @MethodName: getCurrMonthStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getCurrMonthStartEnd() {
        Calendar currMonthStart = Calendar.getInstance();
        currMonthStart.set(Calendar.DATE, 1);
        currMonthStart.set(Calendar.HOUR_OF_DAY, 0);
        currMonthStart.set(Calendar.MINUTE, 0);
        currMonthStart.set(Calendar.SECOND, 0);

        Calendar currMonthEnd = Calendar.getInstance();
        currMonthEnd.set(Calendar.DAY_OF_MONTH, 1);
        currMonthEnd.roll(Calendar.DAY_OF_MONTH, -1);
        currMonthEnd.set(Calendar.HOUR_OF_DAY, 23);
        currMonthEnd.set(Calendar.MINUTE, 59);
        currMonthEnd.set(Calendar.SECOND, 59);

        String _currMonthStart = DateUtil.date2String(currMonthStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _currMonthEnd = DateUtil.date2String(currMonthEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _currMonthStart, _currMonthEnd };
    }

    /**
     * 得到上月的起始时间(上月1号到上月最后一天)
     *
     * @MethodName: getLastMonthStartEnd
     * @Description:
     * @return
     * @throws
     */
    public static String[] getLastMonthStartEnd() {
        Calendar lastMonthStart = Calendar.getInstance();
        lastMonthStart.set(Calendar.MONTH,
                lastMonthStart.get(Calendar.MONTH) - 1);
        lastMonthStart.set(Calendar.DATE, 1);
        lastMonthStart.set(Calendar.HOUR_OF_DAY, 0);
        lastMonthStart.set(Calendar.MINUTE, 0);
        lastMonthStart.set(Calendar.SECOND, 0);

        Calendar lastMonthEnd = Calendar.getInstance();
        lastMonthEnd.set(Calendar.MONTH, lastMonthEnd.get(Calendar.MONTH) - 1);
        lastMonthEnd.set(Calendar.DAY_OF_MONTH, 1);
        lastMonthEnd.roll(Calendar.DAY_OF_MONTH, -1);
        lastMonthEnd.set(Calendar.HOUR_OF_DAY, 23);
        lastMonthEnd.set(Calendar.MINUTE, 59);
        lastMonthEnd.set(Calendar.SECOND, 59);

        String _lastMonthStart = DateUtil.date2String(lastMonthStart.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        String _lastMonthEnd = DateUtil.date2String(lastMonthEnd.getTime(),
                "yyyy-MM-dd HH:mm:ss");
        return new String[] { _lastMonthStart, _lastMonthEnd };
    }

    /**
     * 获取几天之后的日期字符串 ：yyyy-MM-dd
     *
     * @MethodName: getDateAfterSomeDays
     * @Description:
     * @param days
     * @return
     * @throws
     */
    public static String getDateAfterSomeDays(int days) {
        GregorianCalendar worldTour = new GregorianCalendar();
        worldTour.add(GregorianCalendar.DATE, days);
        Date d = worldTour.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
        return sdf.format(d);
    }
}
