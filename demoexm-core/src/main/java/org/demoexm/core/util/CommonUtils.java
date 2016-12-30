package org.demoexm.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 本类集合一些常用的工具方法，所有的零散的算法或者工具方法都在该类中统一集中实现。
 * 
 * @author xyqu 2011/07/21
 * 
 */
public final class CommonUtils extends Object {

	/**
	 * 从target中统计source出现的次数
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static int getStringCount(String source, String target) {
		int result = 0;
		boolean flag = true;
		while (flag) {
			int index = target.indexOf(source);
			if (index == -1) {
				flag = false;
			} else {
				result++;
				target = target.replaceFirst(source, "");
			}
		}
		return result;
	}

	/**
	 * 取到关联日期，直接传入Calendar.YEAR/Calendar.MONTH/Calendar.DATE为单位
	 * 
	 * @param date
	 * @param unit
	 * @param step
	 * @return
	 * @throws Exception
	 */
	public static String getRelativeDate(String date, int unit, int step) throws Exception {
		if (step == 0) {
			return date;
		}
		Calendar calendar = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		calendar.setTime(df.parse(date));
		calendar.add(unit, step);
		return df.format(calendar.getTime());
	}

	public final static String toRealString(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 将value保留scale位小数，并以字符串形式返回。
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public final static String toRealString(double value, int scale) {
		// 先在scale位时四舍五入
		value = value * Math.pow(10, scale);
		value = Math.round(value);
		value = value / Math.pow(10, scale);

		String result = Double.toString(value);
		int index = result.indexOf(".");
		if (index < 0) {
			result = result + ".";
			for (int i = 0; i < scale; i++) {
				result += "0";
			}
			return result;
		}

		int length = result.length() - index - 1;
		length = scale - length;
		for (int i = 0; i < length; i++) {
			result += "0";
		}
		return result;
	}
}