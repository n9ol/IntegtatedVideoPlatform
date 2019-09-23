package com.zzrenfeng.zhsx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 日期工具类
 * 
 * @author 田杰熠
 * @version 1.0
 */
public class DateUtil {

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整日期 yyyy-MM-dd */
	public static final String simpleShort = "yyyy-MM-dd";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simpleLong = "yyyy-MM-dd HH:mm:ss";

	/** 时间 HH:mm:ss */
	public static final String time = "HH:mm:ss";

	/** 星期 E */
	public static final String E = "E";

	public static void main(String[] args) {
		try {
//			Date d1 = getDateByString("2019-03-05 08:00:00", simpleLong);
//			d1.setTime(d1.getTime() + 5000L);
			Date d2 = new Date();
			String d2Str_1 = getStringDate(d2, simpleLong);
			d2.setTime(d2.getTime() + 5000L);
			String d2Str_2 = getStringDate(d2, simpleLong);
			System.out.println("--------------------->>>>str1=" + d2Str_1 + ", str2=" + d2Str_2);
			System.out.println("=====================>>>>time=" + getStringDate(new Date(), time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DateFormat getDateFormat(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df;
	}

	/**
	 * 获取系统当前日期 格式：yyyy-MM-dd
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date NowDateShort() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(simpleShort);
		String nowdate = sdf.format(new Date());
		Date date = sdf.parse(nowdate);
		return date;
	}

	/**
	 * 获取系统当前日期 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date NowDateLong() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(simpleLong);
		String nowdate = sdf.format(new Date());
		Date date = sdf.parse(nowdate);
		return date;
	}

	/**
	 * 获取系统当前日期 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String NowDateLongStr() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(simpleLong);
		String nowdate = sdf.format(new Date());
		return nowdate;
	}
	
	public static String getCurrDateStr(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowdate = sdf.format(new Date());
		return nowdate;
	}

	/**
	 * 获得指定时间,指定模板的date
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateDate(Date date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowdate = sdf.format(date);
		Date datef = sdf.parse(nowdate);
		return datef;
	}
	
	/**
	 * 获取指定日期字符的Date类型
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByString(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date datef = sdf.parse(dateStr);
		return datef;
	}

	/**
	 * 获得指定时间,指定模板的字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStringDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String stringDate = sdf.format(date);
		return stringDate;
	}

	/**
	 * 获取当前系统 字符串类型星期几 格式 ：星期一
	 * 
	 * @return
	 */
	public static String getStringWeekDay() {
		return getDateFormat(E).format(new Date());
	}

	/**
	 * 获取当前系统 int类型星期几 格式 ：1
	 * 
	 * @return
	 */
	public static int getIntWeekDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取指定的时间是 int类型星期几 格式 ：1
	 * 
	 * @return
	 */
	public static int getIntWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获得当前系统前几天，或后几天的时间
	 * 
	 * @param n
	 *            前一天 -1, 后一天 1
	 * @param format
	 *            返回数据模板
	 * @return
	 */
	public static String getNextDay(int n, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, n);
		Date date = calendar.getTime();
		return getDateFormat(format).format(date);
	}

	/**
	 * 获得指定日期 前几天，或后几天的时间
	 * 
	 * @param n
	 *            前一天 -1, 后一天 1
	 * @param format
	 * @return
	 */
	public static String getNextDay(Date date, int n, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		date = calendar.getTime();
		return getDateFormat(format).format(date);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int getdaysBetween(Date smdate, Date bdate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int getdaysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 计算两个日期之间相差的毫秒数 startDate 开始时间 endDate 结束时间
	 */
	public static long getDateBetween(Date startDate, Date endDate) {
		long timeLength = endDate.getTime() - startDate.getTime();
		return timeLength;
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	/**
	 * 获得指定时间所在周的日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Map<String, Object> getOneWeekDate(Date date, String format) {
		int e = getIntWeekDay(date);
		String SundayDate = getNextDay(date, -e, format);
		String MondayDate = getNextDay(date, 1 - e, format);
		String TuesdayDate = getNextDay(date, 2 - e, format);
		String WednesdayDate = getNextDay(date, 3 - e, format);
		String ThursdayDate = getNextDay(date, 4 - e, format);
		String FridayDate = getNextDay(date, 5 - e, format);
		String SaturdayDate = getNextDay(date, 6 - e, format);
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("SundayDate", SundayDate);
		hm.put("MondayDate", MondayDate);
		hm.put("TuesdayDate", TuesdayDate);
		hm.put("WednesdayDate", WednesdayDate);
		hm.put("ThursdayDate", ThursdayDate);
		hm.put("FridayDate", FridayDate);
		hm.put("SaturdayDate", SaturdayDate);
		return hm;
	}

	/**
	 * 计算两个时间之间相差的分钟数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return
	 * @throws ParseException
	 */
	public static int getminutesBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fromDate = sdf.format(smdate);
		String toDate = sdf.format(bdate);
		long from = sdf.parse(fromDate).getTime();
		long to = sdf.parse(toDate).getTime();
		int minutes = (int) ((to - from) / (1000 * 60));
		return minutes;
	}

	/**
	 * 通过毫秒数计算时间差
	 * 
	 * @param timeLength
	 *            两个时间相差的毫秒数
	 * @return
	 */
	public static String getTimeBy(long timeLength) {
		long minute = 60 * 1000;// 1分钟
		long hour = 60 * minute;// 1小时
		long day = 24 * hour;// 1天
		long month = 31 * day;// 月
		long year = 12 * month;// 年

		long r = 0;
		if (timeLength > year) {
			r = (timeLength / year);
			return r + "年前";
		}
		if (timeLength > month) {
			r = (timeLength / month);
			return r + "个月前";
		}
		if (timeLength > day) {
			r = (timeLength / day);
			return r + "天前";
		}
		if (timeLength > hour) {
			r = (timeLength / hour);
			return r + "个小时前";
		}
		if (timeLength > minute) {
			r = (timeLength / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}

	/**
	 * 获指定时间是一年中的第几周 today 要定义的时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static int getCurrentWeek(String days) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(days);
		Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);//换成中国时间 以星期一为第一天
		// 去掉了就和数据库计算的结果是一致了
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	/**
	 * 获得两个日期之间的月差值
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthsBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(sdf.parse(smdate));
		int month1 = cal.get(Calendar.MONTH);
		int year1 = cal.get(Calendar.YEAR);
		
		cal.setTime(sdf.parse(bdate));
		int month2 = cal.get(Calendar.MONTH);
		int year2 = cal.get(Calendar.YEAR);
		
		int between_months = month2 - month1;
		int between_years_M = (year2-year1)*12;
		
		return Math.abs(between_years_M + between_months);
	}
	/**
	 * 获得指定日期 前几个月，或后几个月的时间
	 * 
	 * @param n
	 *            前1个月-1, 后一个月 1
	 * @param format
	 * @return
	 */
	public static String getNextMonth( int n, String format) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		date = calendar.getTime();
		return getDateFormat(format).format(date);
	}

}
