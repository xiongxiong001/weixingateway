package org.demoexm.core.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期工具类
 */
public class MyDateUtil {

	private static Logger logger = Logger.getLogger(MyDateUtil.class);
	/**
	 * 每日开始时间 00:00:00
	 */
	public static final String DAY_START_TIME = " 00:00:00";
	/**
	 * 每日结束时间 23:59:59
	 */
	public static final String DAY_END_TIME = " 23:59:59";
	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static final String DATE_FMT = "yyyy-MM-dd";
	/**
	 * 日期格式yyyy/MM/dd
	 */
	public static final String DATE_FMT_AM = "yyyy/MM/dd";
	/**
	 * 日期格式 HH:mm:ss
	 */
	public static final String TIME_FMT = "HH:mm:ss";
	/**
	 * 日期格式 HH:mm
	 */
	public static final String TIME_FMT_WITHOUT_SECOND = "HH:mm";
	/**
	 * 日期格式 yyyy-MM
	 */
	public static final String MONTH_FMT = "yyyy-MM";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式 yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_TIME_FMT_EN = "yyyy/MM/dd HH:mm:ss";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm
	 */
	public static final String DATE_TIME_HM = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式 yyyyMMddHHmmss
	 */
	public static final String DATETIMEFMT = "yyyyMMddHHmmss";
	/**
	 * 日期格式 yyyy年M月
	 */
	public static final String MONTH_FMT_CN = "yyyy年M月";
	/**
	 * 日期格式 yyyy年M月d日
	 */
	public static final String DATE_FMT_CN = "yyyy年M月d日";
	/**
	 * 日期格式 yyyyMMdd
	 */
	public static final String DATE_FMT_EN = "yyyyMMdd";
	
	/**
	 * 获取指定的日志是星期几
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		return dayOfWeek;
	}
	
	/**获取日期*/
	public static Date getDate(Object date){
		if(date==null) return null;
		if(date instanceof Date)
			return (Date) date;
		if(date instanceof String)
			return dateStrToDate(String.valueOf(date));
		else return null;
		
	}

	/**
	 * 获得本周周一日期
	 *
	 * @return
	 */
	public static Date getMonday() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * ************************************************************************
	 * 判断currDate是否在startdate与enddate之间
	 *
	 * @param currDate
	 *            当前日期
	 * @param startdate
	 *            日期范围开始
	 * @param enddate
	 *            日期范围截止
	 * @return
	 */
	public static boolean isInMiddle(Date currDate, Date startdate, Date enddate) {
		boolean result = false;
		if (null == currDate || null == startdate || null == enddate) {
			return result;
		}
		long currentTimeVal = currDate.getTime();
		result = ((currentTimeVal >= startdate.getTime()) && (currentTimeVal < enddate.getTime()));
		return result;
	}

	/**
	 * 获取指定的日志是星期几
	 *
	 * @param date
	 * @return
	 */
	public static String getWeekDayString(Date date) {
		String weekString = "";
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		weekString = dayNames[dayOfWeek - 1];
		return weekString;
	}

	/**
	 * 按照年月日 星期几的格式输入某天的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getDayString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String tdate = sdf.format(date);
		tdate += " " + getWeekDayString(date);
		return tdate;
	}

	public static String amOrPm(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int hour = Integer.valueOf(sdf.format(date));
		if (hour <= 12) {
			return "上午";
		} else {
			return "下午";
		}
	}

	/*
	 * public static void main(String[] args) { Calendar c =
	 * Calendar.getInstance(); for(int i=18;i<30;i++) { c.set(2012, 10, i);
	 * System.out.println(getWeekDayString(c.getTime())); } c.set(2012, 10, 16);
	 * System.out.println(getDayString(c.getTime()));
	 * System.out.println(amOrPm(new Date())); }
	 */

	// 返回日期型yyyy-MM-dd HH:mm:ss
	public static Date getDateTimeByStr(String date) {
		Date date1 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (date != null)
				date1 = sdf.parse(date);
		} catch (Exception e) {
			logger.error("将字符串转换成yyyy-MM-dd HH:mm:ss日期出错" + e.getMessage(), e);
		}
		return date1;
	}

	public static Date getDateByStr(String date) {
		Date date1 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (date != null)
				date1 = sdf.parse(date);
		} catch (Exception e) {
			logger.error("将字符串转换成yyyy-MM-dd日期出错" + e.getMessage(), e);
		}
		return date1;
	}
	
	public static Date getDateByStr(String date, String fmt) {
		Date date1 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			if (date != null)
				date1 = sdf.parse(date);
		} catch (Exception e) {
			logger.error("将字符串转换成"+fmt+"日期出错" + e.getMessage(), e);
		}
		return date1;
	}

	// 返回字符串"yyyy-MM-dd"
	public static String getDateStrByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	// 返回字符串"yyyy-MM-dd"
	public static String getDateStrByDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * desc: 返回字符串"yyyy-MM-dd HH:mm:ss"
	 *
	 * @param date
	 * @return auther: 陈军 mail：chenjun@hyxt.com date: Feb 26, 2014 2:47:06 PM
	 */
	public static String getDateTimeStrByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 *
	 * @param date
	 *            日期
	 * @param dateType
	 *            类型
	 * @param amount
	 *            数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	public static String getYearMonth(Date date) {
		if (null == date) {
			date = new Date();
		}
		return dateToDateStr(MONTH_FMT, date);
	}

	/**
	 * 获取日期
	 */
	public static Date getCurrentDateTime() {

		return getCurrentDateTime(DATE_TIME_FMT);
	}

	public static Date getCurrentDate() {

		return getCurrentDate(DATE_FMT);
	}

	public static Date getCurrentTime() {

		return getCurrentTime(TIME_FMT);
	}

	public static Date getCurrentDateTime(String fmt) {

		return dateStrToDate(fmt, getCurrentDateTimeStr(fmt));
	}

	public static Date getCurrentDate(String fmt) {

		return dateStrToDate(fmt, getCurrentDateStr(fmt));
	}

	public static Date getCurrentTime(String fmt) {

		return dateStrToDate(fmt, getCurrentTimeStr(fmt));
	}

	public static String getCurrentDateTimeStr() {

		return getCurrentDateTimeStr(DATE_TIME_FMT);
	}

	public static String getCurrentTimeStr() {

		return getCurrentTimeStr(TIME_FMT);
	}

	public static String getCurrentDateStr() {

		return getCurrentDateStr(DATE_FMT);
	}

	public static String getCurrentDateTimeStr(String fmt) {

		String temp = new SimpleDateFormat(fmt).format(new Date());

		return temp;
	}

	public static String getCurrentTimeStr(String fmt) {

		String temp = new SimpleDateFormat(fmt).format(new Date());

		return temp;
	}

	public static String getCurrentDateStr(String fmt) {

		String temp = new SimpleDateFormat(fmt).format(new Date());

		return temp;
	}

	public static String dateToDateStr(Date date) {

		String temp = new SimpleDateFormat(DATE_TIME_FMT).format(date);

		return temp;
	}

	public static String dateToDateStr(String fmt, Date date) {

		String temp = new SimpleDateFormat(fmt).format(date);

		return temp;
	}

	/**
	 * 转换为日期对象
	 */
	public static Date dateStrToDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		Date temp = null;
		try {
			temp = new SimpleDateFormat(DATE_FMT).parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return temp;
	}

	/**
	 * 字符串转换为日期时间格式对象
	 */
	public static Date dateStrToDatetime(String date) {
		if (date == null) {
			return null;
		}
		Date temp = null;
		try {
			temp = new SimpleDateFormat(DATE_TIME_FMT).parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return temp;
	}

	public static Date dateStrToDate(String fmt, String date) {
		Date temp = null;
		try {
			temp = new SimpleDateFormat(fmt).parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return temp;
	}

	/**
	 * 格式化日期
	 */
	public static String formatDateTime(Date date) {

		return formatDateTime(DATE_TIME_FMT, date);
	}

	public static String formatDateTime(String fmt, Date date) {
		if (StringUtils.isEmpty(fmt) || null == date) {
			return null;
		}
		String temp = new SimpleDateFormat(fmt).format(date);

		return temp;
	}

	public static String formatTime(Date date) {
		return formatTime(TIME_FMT, date);
	}

	public static String formatTime(String fmt, Date date) {
		if (StringUtils.isEmpty(fmt) || null == date) {
			return null;
		}
		String temp = new SimpleDateFormat(fmt).format(date);

		return temp;
	}

	public static String formatDate(Date date) {
		return formatDate(DATE_FMT, date);
	}

	public static String formatDate(String fmt, Date date) {
		if (StringUtils.isEmpty(fmt) || null == date) {
			return null;
		}
		String temp = new SimpleDateFormat(fmt).format(date);

		return temp;
	}

	public static String formatNumber(String fmt, Object value) {
		if (StringUtils.isEmpty(fmt) || null == value) {
			return null;
		}
		String temp = new DecimalFormat(fmt).format(value);

		return temp;
	}

	/**
	 * 比较两个日期相差的天数
	 */
	public static int compareDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		/*
		 * 经过上面的处理，保证d2在d1之后
		 * 下面这个days可能小于0，因为d2和d1可能不在同一年里，这样的话虽然d1的年份小，但其在一年中的"第几天"却可能比d2大。
		 */
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) - d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {// 如果不在同一年
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				/*
				 * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。 例如，在某些年份中，MONTH 字段的实际最大值是
				 * 12，而在希伯来日历系统的其他年份中，该字段的实际最大值是 13。 DAY_OF_YEAR：闰年366？
				 */
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;

	}

	/**
	 * 比较两个日期相差的周数
	 */
	public static int compareWeek(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		if (c1.equals(c2)) {
			return 0;
		}

		if (c1.after(c2)) {
			Calendar temp = c1;
			c1 = c2;
			c2 = temp;
		}
		// 计算差值
		int result = c2.get(Calendar.WEEK_OF_MONTH) - c1.get(Calendar.WEEK_OF_MONTH);
		return result;
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int compareMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return iMonth;
	}

	/**
	 * 计算两个日期之间相差的月数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareMonthNew(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			// if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) <
			// objCalendarDate1
			// .get(Calendar.DAY_OF_MONTH))
			// flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return iMonth;
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int compareMonth(String strdate1, String strdate2) {
		if (StringUtils.isEmpty(strdate1) || StringUtils.isEmpty(strdate2)) {
			return 0;
		}
		Date date1 = dateStrToDate(strdate1);
		Date date2 = dateStrToDate(strdate2);
		try {
			return compareMonth(date1, date2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 比较两个日期相差的天数
	 */
	public static int compareDay(String strdate1, String strdate2) {
		if (StringUtils.isEmpty(strdate1) || StringUtils.isEmpty(strdate2)) {
			return 0;
		}
		Date date1 = dateStrToDate(strdate1);
		Date date2 = dateStrToDate(strdate2);
		try {
			return compareDay(date1, date2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 比较两个日期相差的秒数
	 */
	public static long compareTime(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar c = Calendar.getInstance();

		c.setTime(date1);
		long l1 = c.getTimeInMillis();

		c.setTime(date2);
		long l2 = c.getTimeInMillis();

		return (l2 - l1) / 1000;
	}

	// 设置时间
	public static Date addDateTime(Date date, int type, int num) {
		if (date == null) {
			return null;
		}
		// 初始化日历对象
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 根据类型添加
		switch (type) {
		case 1: // 添加年
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + num);
			break;
		case 2: // 添加月
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + num);
			break;
		case 3: // 添加日
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
			break;
		case 4: // 添加时
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + num);
			break;
		case 5: // 添加分
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + num);
			break;
		case 6: // 添加秒
			cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + num);
			break;
		}

		// 返回操作结果
		return cal.getTime();
	}

	// 设置日期时间
	private static Date setDateTime(Date date, int type, int num) {
		if (date == null) {
			return null;
		}
		// 初始化日历对象
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 根据类型添加
		switch (type) {
		case 1: // 添加年
			cal.set(Calendar.YEAR, num);
			break;
		case 2: // 添加月
			cal.set(Calendar.MONTH, num);
			break;
		case 3: // 添加日
			cal.set(Calendar.DATE, num);
			break;
		case 4: // 添加时
			cal.set(Calendar.HOUR_OF_DAY, num);
			break;
		case 5: // 添加分
			cal.set(Calendar.MINUTE, num);
			break;
		case 6: // 添加秒
			cal.set(Calendar.SECOND, num);
			break;
		}

		// 返回操作结果
		return cal.getTime();
	}

	/**
	 * 设置年、月、日
	 */
	public static Date setYMD(Date date, int year, int month, int day) {

		return setYear(setMonth(setDate(date, day), month), year);
	}

	public static Date setYear(Date date, int num) {
		return addDateTime(date, 1, num);
	}

	public static Date setMonth(Date date, int num) {
		return addDateTime(date, 2, num);
	}

	public static Date setDate(Date date, int num) {
		return addDateTime(date, 3, num);
	}

	/**
	 * 设置时、分、秒
	 */
	public static Date setHMS(Date date, int hour, int minute, int second) {

		return setHour(setMinute(setSecond(date, second), minute), hour);
	}

	public static Date setHour(Date date, int num) {
		return setDateTime(date, 4, num);
	}

	public static Date setMinute(Date date, int num) {
		return setDateTime(date, 5, num);
	}

	public static Date setSecond(Date date, int num) {
		return setDateTime(date, 6, num);
	}

	// /** 添加年、月、日、时、分、秒 */
	// public static Date addYear(Date date, int num) {
	// return addDateTime(date, 1, num);
	// }

	public static Date addMonth(Date date, int num) {
		return addDateTime(date, 2, num);
	}

	public static Date addDate(Date date, int num) {
		return addDateTime(date, 3, num);
	}

	/**
	 * 添加年、月、日
	 */
	public static Date addYMD(Date date, int year, int month, int day) {

		return addYear(addMonth(addDate(date, day), month), year);
	}

	public static Date addHour(Date date, int num) {
		return addDateTime(date, 4, num);
	}

	public static Date addMinute(Date date, int num) {
		return addDateTime(date, 5, num);
	}

	public static Date addSecond(Date date, int num) {
		return addDateTime(date, 6, num);
	}

	/**
	 * 添加时、分、秒
	 */
	public static Date addHMS(Date date, int hour, int minute, int second) {

		return addHour(addMinute(addSecond(date, second), minute), hour);
	}

	public static int getYear(Date date) {
		Calendar cale = Calendar.getInstance();
		if (date != null) {
			cale.setTime(date);
		}
		return cale.get(Calendar.YEAR);
	}

	/**
	 * 得到某年的第一天
	 *
	 * @return
	 * @author dylan_xu
	 * @date Mar 11, 2012
	 */
	public static String getYearFirstDate(Date date) {
		return getYear(date) + "-01-01";
	}

	/**
	 * 得到某年某月的第一天
	 *
	 * @return
	 * @author dylan_xu
	 * @date Mar 11, 2012
	 */
	public static String getMonthFirstDate(Date date) {
		return getYearMonth(date) + "-01";
	}

	/**
	 * 得到某年某月的最后一天
	 *
	 * @return
	 * @author dylan_xu
	 * @date Mar 11, 2012
	 */
	public static String getMonthEndDate(Date date) {
		String endDate = "";
		Calendar c = Calendar.getInstance();
		if (date == null) {
			c.setTime(date);
		}
		endDate = getYearMonth(c.getTime()) + "-" + c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return endDate;
	}

	/**
	 * 转化一个时间段内的的每一天格式 为 2014-05-26
	 */
	public static String getDateScope(Date date, int d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (d == 0)
			return sdf.format(date);
		long time = date.getTime();
		Date t = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(t);
		c.add(Calendar.DAY_OF_YEAR, d);
		String str = sdf.format(c.getTime());
		return str;
	}

	/**
	 * 获取近XX天及指定格式时间列表
	 *
	 * @param recentlyDay
	 * @param format
	 * @return
	 * @author weichengpei
	 * @create 2014年12月13日 下午3:58:25
	 */
	public static List<String> getRecentDate(int recentlyDay, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		List<String> recentDatelist = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		for (int i = recentlyDay - 1; i >= 0; i--) {
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, -i);
			String day = df.format(calendar.getTime());
			recentDatelist.add(day);
		}
		return recentDatelist;
	}

	/**
	 * 获取指定开始日期和截止日期近XX天时间列表
	 *
	 * @param startStr
	 * @param endStr
	 * @param format
	 * @return
	 * @author weichengpei
	 * @create 2014年12月13日 下午4:16:26
	 */
	public static List<String> getRecentDateByStartEnd(String startStr, String endStr, String format) {
		List<String> list = new ArrayList<String>();
		if (startStr.trim().equals(endStr.trim())) {
			list.add(startStr);
			return list;
		}
		if (StringUtils.isNotEmpty(startStr) && StringUtils.isNotEmpty(endStr)) {
			Date startDate = MyDateUtil.getDateByStr(startStr);
			Date endDate = MyDateUtil.getDateByStr(endStr);
			if (startDate.before(endDate)) {
				int days = MyDateUtil.compareDay(startDate, endDate);
				list = getRecentDateByEndDate(days + 1, endDate, format);
			}
		}
		return list;
	}

	/**
	 * 获取截止日期近XX天及指定格式时间列表
	 *
	 * @param recentlyDay
	 * @param endDate
	 * @param format
	 * @return
	 * @author weichengpei
	 * @create 2014年12月13日 下午4:16:45
	 */
	public static List<String> getRecentDateByEndDate(int recentlyDay, Date endDate, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		List<String> recentDatelist = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		for (int i = recentlyDay - 1; i >= 0; i--) {
			calendar.setTime(endDate);
			calendar.add(Calendar.DAY_OF_YEAR, -i);
			String day = df.format(calendar.getTime());
			recentDatelist.add(day);
		}
		return recentDatelist;
	}

	/**
	 * 获取指定日期所在月份的第一天和最后一天日期
	 *
	 * @param date
	 * @author weichengpei
	 * @create 2014年12月13日 下午5:25:10
	 */
	public static Date[] getMonthStartEndByDate(Date date) {
		Date[] resDates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), minDay);
		resDates[0] = calendar.getTime();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay);
		resDates[1] = calendar.getTime();
		return resDates;
	}

	/**
	 * 获取指定日期所在星期的第一天和最后一天日期
	 *
	 * @param date
	 * @return
	 * @author weichengpei
	 * @create 2014年12月15日 下午12:11:27
	 * @description 周日是星期第一天, 周六是星期最后一天
	 */
	public static Date[] getWeekStartEndByDate(Date date) {
		Date[] resDates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = getWeekDay(calendar.getTime());
		calendar.add(Calendar.DAY_OF_YEAR, -dayOfWeek + 1);
		Date startDate = calendar.getTime();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR, 6);
		Date endDate = calendar.getTime();
		resDates[0] = startDate;
		resDates[1] = endDate;
		return resDates;
	}

	public static Date[] getWeekStartEndByDate(int year, int week) {
		Date[] resDates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		// 设置年份
		calendar.set(Calendar.YEAR, year);
		// 设置周
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		// 设置该周第一天为星期一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		resDates[0] = calendar.getTime();
		resDates[1] = MyDateUtil.addDate(resDates[0], 6);
		return resDates;
	}

	// public static int getMonthSpace(Date date1, Date date2)
	// {
	// if (date1 == null || date2 == null)
	// return 0;
	// int result = 0;
	// Calendar c1 = Calendar.getInstance();
	// Calendar c2 = Calendar.getInstance();
	// c1.setTime(date1);
	// c2.setTime(date2);
	// result = c2.get(Calendar.) - c1.get(Calendar.MONTH);
	// return result == 0 ? 1 : Math.abs(result);
	//
	// }

	/**
	 * 把一个时间调整到一天的开始：00:00:00
	 *
	 * @param date
	 * @return
	 * @author qhq
	 * @create 2015年1月12日 下午4:38:15
	 */
	public static Date dateLast(Date date) {
		Date last = MyDateUtil.dateStrToDate(DATE_FMT, MyDateUtil.formatDate(DATE_FMT, date));
		last = MyDateUtil.addHour(last, 23);
		last = MyDateUtil.addMinute(last, 59);
		last = MyDateUtil.addSecond(last, 59);
		return last;

	}

	/**
	 * 把一个时间调整到一天的最后：23:59:59
	 *
	 * @param date
	 * @return
	 * @author qhq
	 * @create 2015年1月12日 下午4:38:15
	 */
	public static Date dateStart(Date date) {
		Date start = MyDateUtil.dateStrToDate(DATE_FMT, MyDateUtil.formatDate(DATE_FMT, date));
		return start;

	}

	/**
	 * 获取指定日期00:00:00和59:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date[] getDateStartAndEnd(Date date) {
		return new Date[] { dateStart(date), dateLast(date) };
	}

	/**
	 * 获取指定日期00:00:00和59:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseStrToDate(String dateStr) {
		Date date = null;
		if (!StringUtils.isEmpty(dateStr)) {
			try {
				date = MyDateUtil.dateStrToDate(MyDateUtil.DATE_TIME_FMT, dateStr);
			} catch (Exception ea) {
				date = MyDateUtil.dateStrToDate(MyDateUtil.DATE_FMT, dateStr);
			}
		}
		return date;
	}

	/**
	 * 转换日期字符串为yyyy-MM-dd HH:mm:ss格式 如果格式已经是yyyy-MM-dd HH:mm:ss直接返回，否则添加上
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param timeString
	 *            要加上的时分秒
	 * @return yyyy-MM-dd HH:mm:ss格式字符串
	 */
	@Deprecated
	public static String getDateStartString(String dateString, String timeString) {
		if (StringUtils.isEmpty(dateString)) {
			return "";
		}
		if (dateString.length() == MyDateUtil.DATE_FMT.length()) {
			dateString = dateString + timeString;
		}
		return dateString;
	}

	/**
	 * 改变日期起止的yyyy-MM-dd HH:mm:ss格式字符串
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param start
	 *            日期开始或结束
	 * @return
	 */
	public static String getDate2StringYMDHMS(String dateString, boolean start) {
		if (StringUtils.isEmpty(dateString)) {
			return "";
		}
		if (dateString.length() == MyDateUtil.DATE_FMT.length()) {
			dateString = start ? (dateString + DAY_START_TIME) : (dateString + DAY_END_TIME);
		}
		if (dateString.length() == MyDateUtil.DATE_TIME_HM.length()) {
			dateString = start ? (dateString + ":00") : (dateString + ":59");
		}
		return dateString;
	}

	/**
	 * 获取两个时间的差值
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDiscrepantDays(final Date startDate, final Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}

		if (startDate.before(endDate)) {
			return (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
		}

		return 0;
	}

	public static void main(String args[]) {

		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 1);
		System.out.println(MyDateUtil.formatDate(MyDateUtil.DATETIMEFMT, new Date()));

		MyDateUtil.formatDate(MyDateUtil.DATETIMEFMT, new Date());
	}
	
	 /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     *  
     * @param beginDate 
     * @param endDate 
     * @return List 
     */  
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    }  
    
    
}
