package com.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTool {
    /** 日期格式器--年 */
    public static final String DATE_FORMAT_YEAR = "yyyy";
    /** 日期格式器 */
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";
    /** 日期格式器 */
    public static final String DATE_FORMAT_MONTH_DAY = "MM-dd";
    /** 日期格式器 */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /** 日期格式器 */
    public static final String DATE_FORMAT_HH = "yyyy-MM-dd HH";
    /** 日期格式器 */
    public static final String DATE_FORMAT_MM = "yyyy-MM-dd HH:mm";
    /** 时间格式器 */
    public static final String TIME_FORMAT = "HH:mm:ss";
    /** 日期时间格式器 */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 日期时间格式器 */
    public static final String DATETIME_FORMAT_1 = "HH:mm:ss 00:00:00";
    /** 日期戳 */
    public static final String DATE_STAMP = "yyyyMMdd";
    /** 时间戳 */
    public static final String TIME_STAMP = "HHmmssSSS";
    /** 时间戳（精确到秒） */
    public static final String TIME_STAMP_SECOND = "HHmmss";
    /** 日期时间戳 */
    public static final String DATETIME_STAMP = "yyyyMMddHHmmssSSS";
    /** 日期时间戳（精确到秒） */
    public static final String DATETIME_STAMP_SECOND = "yyyyMMddHHmmss";
    /** UTC时间戳*/
    public static final SimpleDateFormat UTC_TIME_STAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    /** UTC时间格式化器 */
    public static final SimpleDateFormat UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    /** GMT时间格式化器 */
    public static final SimpleDateFormat GMT_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    static {
        UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        UTC_TIME_STAMP_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        GMT_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    /** 天 */
    public static final String DAY_STR = "天";
    /** 小时 */
    public static final String HOUR_STR = "小时";
    /** 分钟 */
    public static final String MINUTE_STR = "分钟";

    /** 将字符串解析成yyyy-MM-dd 的日期 */
    public static Date parseDate(String value) {
        try {
            return new SimpleDateFormat(DATE_FORMAT, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /** 将字符串解析成HH:mm:ss的时间 */
    public static Date parseTime(String value) {
        try {
            return new SimpleDateFormat(TIME_FORMAT, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 将字符串解析成yyyy-MM-dd HH:mm:ss的日期时间 */
    public static Date parseDateTime(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATETIME_FORMAT, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 将字符串解析成自定义格式的日期时间 */
    public static Date parseDateTime(String value, String format) {
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 将字符串解析成yyyyMMddHHmmssSSS的日期时间 */
    public static Date parseDateTimeStamp(String value) {
        try {
            return new SimpleDateFormat(DATETIME_STAMP, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 将字符串解析成yyyyMMddHHmmss的日期时间 */
    public static Date parseDateTimeSecond(String value) {
        try {
            return new SimpleDateFormat(DATETIME_STAMP_SECOND, Locale.CHINA).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /** 将时间解析成yyyy-MM-dd的字符串 */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT, Locale.CHINA).format(date);
    }

    /** 将时间解析成yyyy的字符串 */
    public static String formatDateToYear(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_YEAR, Locale.CHINA).format(date);
    }

    /** 将日期解析成HH:mm:ss的字符串 */
    public static String formatTime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(TIME_FORMAT, Locale.CHINA).format(date);
    }

    /** 将日期时间解析成yyyy-MM-dd HH:mm:ss的字符串 */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_FORMAT, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyyMMddHHmmssSSS的字符串 */
    public static String formatDateTimestamp(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_STAMP, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyyMMddHHmmss的字符串 */
    public static String formatDateTimeSecond(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_STAMP_SECOND, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyyMMdd的字符串 */
    public static String formatDatestamp(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_STAMP, Locale.CHINA).format(date);
    }

    /** 将日期解析成HHmmssSSS的字符串 */
    public static String formatTimestamp(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(TIME_STAMP, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyy-MM-dd HH的字符串 */
    public static String formatDateHH(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_HH, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyy-MM-dd HH:mm的字符串 */
    public static String formatDateMM(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_MM, Locale.CHINA).format(date);
    }

    /** 将日期解析成yyyy-MM的字符串 */
    public static String formatYearMonth(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_MONTH, Locale.CHINA).format(date);
    }

    /** 将日期解析成MM-dd的字符串 */
    public static String formatMonthDay(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT_MONTH_DAY, Locale.CHINA).format(date);
    }

    /** 获取当天的开始时间, 如:2014-02-24 00:00:00 */
    public static Date getBeginOfDate() {
        return getBeginOfDate(null);
    }

    /** 获取当天的结束时间, 如:2014-02-24 23:59:59 */
    public static Date getEndOfDate() {
        return getEndOfDate(null);
    }

    /** 获取指定日期的开始时间, 如:2014-02-24 00:00:00 */
    public static Date getBeginOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return setDayToBegin(calendar).getTime();
    }

    /** 获取指定日期的结束时间, 如:2014-02-24 23:59:59 */
    public static Date getEndOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return setDayToEnd(calendar).getTime();
    }

    /**
     * @return 获取当年天数
     */
    public static int getDaysInYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(calendar.get(Calendar.YEAR)) ? 366 : 365;
    }

    /**
     * 格式化当前日期
     *
     * @return date型时间
     */
    public static Date getSysDateOfDate() {
        return parseDateTime(formatDateTime(new Date()));
    }

    /**
     * @param calendarType Calendar.DATE、Calendar.MONTH、Calendar.YEAR等
     * @param num          对应日期类型对应的数量
     * @return 指定date型时间 比如：离现在30天前的日期、获取上个月日期
     */
    public static Date getDefineDate(int calendarType, int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(calendarType, num);
        return cal.getTime();
    }

    /**
     * @param specifiedDate 指定的时间，即需要比较的时间
     * @param largeDate     到期的时间，即比较大的时间
     * @return 天数差
     * 计算指定时间与当前时间的天数差
     */
    public static int getDaysDifference(Date specifiedDate, Date largeDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(largeDate);
        cReturnDate.setTime(specifiedDate);
        setDayToBegin(cNow);
        setDayToBegin(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / (1000 * 86400));
    }

    /**
     * @param referenceDate 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date          被比较的时间  为空(null)则为当前时间
     * @param stype         返回值类型   0为多少天，1为多少个月，2为多少年
     * @throws ParseException
     */
    public static int compareDate(String referenceDate, String date, int stype) throws ParseException {
        int n = 0;
        String formatStyle = stype == 1 ? DATE_FORMAT_MONTH : DATE_FORMAT;

        String dateStr = date;
        if (dateStr == null) {
            dateStr = formatDate(new Date());
        }

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(df.parse(referenceDate));
        c2.setTime(df.parse(dateStr));

        // 循环对比，直到相等，n 就是所要的结果
        while (!c1.after(c2)) {
            n++;
            if (stype == 1) {
                // 比较月份，月份+1
                c1.add(Calendar.MONTH, 1);
            } else {
                // 比较天数，日期+1
                c1.add(Calendar.DATE, 1);
            }
        }

        n = n - 1;

        if (stype == 2) {
            n = n / 365;
        }

        return n;
    }

    /**
     * @param format   日期格式
     * @param variable 正数代表时间之后，负数代表时间之前
     * @param field    单位，年，月，日
     * @return 获取当前时间之前/之后多少年/月/日的时间
     */
    public static String getDiffDate(String format, int variable, int field) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        c.set(field, c.get(field) + variable);
        return formatter.format(c.getTime());
    }

    /**
     * @param date 传入相加时间
     * @param field 时间格式
     * @param amount 日期或时间的数量
     * @return 相加后的时间
     */
    public static Date dateAdd(Date date, int field, int amount) {
        Calendar ca = Calendar.getInstance();
        if (date != null) {
            ca.setTime(date);
        }
        ca.add(field,amount);
        return ca.getTime();
    }

    /**
     * @param field 时间格式
     * @param amount 相加的时间天数
     * @return 相加后的时间
     */
    public static Date dateAdd(int field, int amount) {
        return dateAdd(null, field, amount);
    }

    /**
     * 时间范围
     */
    public static class DateRange {
        /** 开始时间 */
        private Date begin;
        /** 结束时间 */
        private Date end;

        public DateRange() {

        }

        public DateRange(Date begin, Date end) {
            this.begin = begin;
            this.end = end;
        }

        public Date getBegin() {
            return begin;
        }

        public Date getEnd() {
            return end;
        }
    }

    /**
     * 获取指定日期的当天时间范围如(2014-06-09 00:00:00 2014-06-09 23:59:59)
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getDayRange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        range.begin = setDayToBegin(calendar).getTime();
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    /**
     * 获取指定日期本周的时间范围如
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getWeekReange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        range.begin = setDayToBegin(calendar).getTime();

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 6);
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    /**
     * 获取指定日期本月的时间范围如
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getMonthReange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        // 获取第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        range.begin = setDayToBegin(calendar).getTime();
        // 获取最后一天
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    /**
     * 获取指定日期本季度的时间范围如
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getSeasonReange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - (month % 3));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        range.begin = setDayToBegin(calendar).getTime();

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    /**
     * 获取指定日期本半年的时间范围如
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getHalfYearReange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int current = calendar.get(Calendar.MONTH) + 1;
        int month = Calendar.JANUARY;
        if (current > 6) {
            month = Calendar.JULY;
        }
        calendar.set(calendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
        range.begin = calendar.getTime();

        calendar.set(Calendar.MONTH, month + 6);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    /**
     * 获取指定日期本半年的时间范围如
     *
     * @param date 时间,为空表示当前时间
     * @return 时间范围
     */
    public static DateRange getYearReange(Date date) {
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        range.begin = calendar.getTime();

        calendar.set(Calendar.MONTH, 12);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        range.end = setDayToEnd(calendar).getTime();

        return range;
    }

    private static Calendar setDayToEnd(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    private static Calendar setDayToBegin(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     * 判断时间date1是否在时间date2之前
     *
     * @param date1 时间
     * @param date2 时间
     */
    public static boolean isDateBefore(String date1, String date2) {
        return DateTool.parseDateTime(date1).before(DateTool.parseDateTime(date2));
    }

    /**
     * 验证当前时间是否在指定时间范围内(例如9：00-22：30)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return bool
     */
    public static boolean isRangeTime(String startTime, String endTime) {
        boolean isRangeTime = false;
        String nowDay = DateTool.formatDate(new Date());
        String start = startTime, end = endTime;
        start = nowDay + " " + start + ":00";
        end = nowDay + " " + end + ":00";
        // 开始时间不在结束时间前，表示时间进行了跨天设置
        if (!isDateBefore(start, end)) {
            // 跨天设置的时间需要当前时间大于结束时间并且小于开始时间
            if (isDateBefore(DateTool.formatDateTime(new Date()), end) || isDateBefore(start, DateTool.formatDateTime(new Date()))) {
                isRangeTime = true;
            }
        } else {
            if (isDateBefore(DateTool.formatDateTime(new Date()), end) && isDateBefore(start, DateTool.formatDateTime(new Date()))) {
                isRangeTime = true;
            }
        }
        return isRangeTime;
    }

    /**
     * utc时间转为date
     * @param value "2004-08-04T19:09:02.768Z";
     * @return
     */
    public static Date parseUTCDateTimeStamp(String value) {
        if(!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return UTC_TIME_STAMP_FORMAT.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatUTCDateTimeStamp(Date date) {
        return UTC_TIME_STAMP_FORMAT.format(date);
    }

    /**
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return utc时间
     */
    public static String formatUTCDateTimeStamp(String dateStr) {
        Date d = parseDateTime(dateStr);
        return UTC_TIME_STAMP_FORMAT.format(d);
    }

    /**
     * 把日期解析为UTC时间
     */
    public static String formatUTCDate(Date ...dates) {
        Date date = new Date();
        if (dates != null && dates.length > 0) {
            date = dates[0];
        }
        return UTC_FORMAT.format(date);
    }

    /**
     * 把日期解析为GMT时间
     */
    public static String formatGMTDate(Date date) {
        return GMT_FORMAT.format(date);
    }

    /**
     * 获取今天是一周的星期几
     * @return
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //星期天对应1，星期一从2开始
        if(day == 1) {
            return 7;
        }
        return day - 1;
    }

    /**获取某年某月的最后一天
     * @param year 年
     * @param month 月
     * @return 最后一天
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    /**获取某年某月的第一天
     * @param year 年
     * @param month 月
     * @return 第一天
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }

    /** 获取当前时间 */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

}
