package com.weaverboot.tools.dateTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 时间操作类
 *
 * @Author : Jaylan Zhou
 *
 */

public class OperaDate {

	private OperaDate() {}

	/**
	 *
	 * 在原日期的基础上增加天数得到新日期
	 *
	 * @param num 增加的天数
	 * @param today 基本日期
	 * @return 基本日期增加天数后的日期
	 */
	public static String addDate(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为:" + e.getMessage());

		}
		c.setTime(date);

		int day = c.get(Calendar.DATE);

		c.set(Calendar.DATE, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		return dayAfter;  // 返回当前时间，格式，2017-03-29 10:05:37 （年、月、日、时、分、秒；24小时制）

	}

	/**
	 *
	 * 在原日期时间上增加天数得到新日期时间
	 *
	 * @param num 增加的天数
	 * @param today 日期时间
	 * @return 新的日期时间
	 */

	public static String addDateTime(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为");

		}

		c.setTime(date);

		int day = c.get(Calendar.DATE);

		c.set(Calendar.DATE, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime());

		return dayAfter;

	}

	/**
	 *
	 * 在原日期的基础上增加月份得到新日期
	 *
	 * @param num 增加的月份
	 * @param today 基本日期
	 * @return 基本日期增加天数后的日期
	 */
	public static String addMonthDate(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为:" + e.getMessage());

		}
		c.setTime(date);

		int day = c.get(Calendar.MONTH);

		c.set(Calendar.MONTH, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		return dayAfter;  // 返回当前时间，格式，2017-03-29 10:05:37 （年、月、日、时、分、秒；24小时制）

	}

	/**
	 *
	 * 在原日期时间上增加月份得到新日期时间
	 *
	 * @param num 增加的月份
	 * @param today 日期时间
	 * @return 新的日期时间
	 */

	public static String addMonthDateTime(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为");

		}

		c.setTime(date);

		int day = c.get(Calendar.MONTH);

		c.set(Calendar.MONTH, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime());

		return dayAfter;

	}

	/**
	 *
	 * 在原日期的基础上增加年份得到新日期
	 *
	 * @param num 增加的年份
	 * @param today 基本日期
	 * @return 基本日期增加天数后的日期
	 */
	public static String addYearDate(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为:" + e.getMessage());

		}
		c.setTime(date);

		int day = c.get(Calendar.YEAR);

		c.set(Calendar.YEAR, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

		return dayAfter;  // 返回当前时间，格式，2017-03-29 10:05:37 （年、月、日、时、分、秒；24小时制）

	}

	/**
	 *
	 * 在原日期时间上增加月份得到新日期时间
	 *
	 * @param num 增加的月份
	 * @param today 日期时间
	 * @return 新的日期时间
	 */

	public static String addYearDateTime(int num, String today) {

		Calendar c = Calendar.getInstance();

		Date date = null;

		try {

			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(today);

		} catch (ParseException e) {

			throw new RuntimeException("增加日期失败,原因为");

		}

		c.setTime(date);

		int day = c.get(Calendar.YEAR);

		c.set(Calendar.YEAR, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime());

		return dayAfter;

	}

	/**
	 *
	 * 判断两个日期之间的天数
	 *
	 * @param smdate 开始日期
	 * @param bdate 结束日期
	 * @return
	 * @throws ParseException
	 */

	public static int daysBetween(String smdate, String bdate) throws ParseException {

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
	 *
	 * 比较第一个日期是否比第二个日期小
	 *
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return 小则返回true，否则返回false
	 * @throws Exception
	 */
	public static boolean compareDateSmall(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateReturnDate(startDate);

		Date date2 = FormatDate.formatDateReturnDate(endDate);

		if (date1.before(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较第一个日期是否比第二个日期大
	 *
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return 若大则返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareDateBig(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateReturnDate(startDate);

		Date date2 = FormatDate.formatDateReturnDate(endDate);

		if (date1.after(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较两个日期是否相等
	 *
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return 相等返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareDateEquals(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateReturnDate(startDate);

		Date date2 = FormatDate.formatDateReturnDate(endDate);

		if (date1.equals(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较第一个日期时间是否比第二个日期时间小
	 *
	 * @param startDate 开始日期时间
	 * @param endDate 结束日期时间
	 * @return 若小返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareDateTimeSmall(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateTimeReturnDate(startDate);

		Date date2 = FormatDate.formatDateTimeReturnDate(endDate);

		if (date1.before(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较第一个日期时间是否比第二个日期时间大
	 *
	 * @param startDate 开始日期时间
	 * @param endDate 结束日期时间
	 * @return 若大返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareDateTimeBig(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateTimeReturnDate(startDate);

		Date date2 = FormatDate.formatDateTimeReturnDate(endDate);

		if (date1.after(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较两个日期时间是否相等
	 *
	 * @param startDate 开始日期时间
	 * @param endDate 结束日期时间
	 * @return 相等返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareDateTimeEquals(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatDateTimeReturnDate(startDate);

		Date date2 = FormatDate.formatDateTimeReturnDate(endDate);

		if (date1.equals(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较第一个时间是否比第二个时间小
	 *
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 若小返回true，否则false
	 * @throws Exception
	 */

	public static boolean compareTimeSmall(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatTimeReturnDate(startDate);

		Date date2 = FormatDate.formatTimeReturnDate(endDate);

		if (date1.before(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较第一个时间是否比第二个时间大
	 *
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 若大返回true，否则false
	 * @throws Exception
	 */
	public static boolean compareTimeBig(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatTimeReturnDate(startDate);

		Date date2 = FormatDate.formatTimeReturnDate(endDate);

		if (date1.after(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 比较两个时间是否相等
	 *
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 若相等返回true,否则false
	 * @throws Exception
	 */

	public static boolean compareTimeEquals(String startDate, String endDate) throws Exception {

		Date date1 = FormatDate.formatTimeReturnDate(startDate);

		Date date2 = FormatDate.formatTimeReturnDate(endDate);

		if (date1.equals(date2)) {

			return true;

		}else {

			return false;

		}

	}

	/**
	 *
	 * 获取本月第一天
	 *
	 * @return 第一天日期
	 */

	public static String getMonthFirstDay(){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, 0);

		c.set(Calendar.DAY_OF_MONTH,1);

		String first = format.format(c.getTime());

		return first;

	}

	/**
	 *
	 * 获取本月最后一天
	 *
	 * @return 最后一天日期
	 */
	public static String getMonthLastDay(){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar ca = Calendar.getInstance();

		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));

		String last = format.format(ca.getTime());

		return last;

	}

	/**
	 *
	 * 获取上个月的第一天
	 *
	 * @return 上个月第一天日期
	 */

	public static String getLastMonthFirstDay(){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.MONTH, -1);

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		String first = format.format(calendar.getTime());

		return first;

	}

	/**
	 *
	 * 获取上个月的最后一天
	 *
	 * @return 上个月的最后一天日期
	 */
	public static String getLastMonthLastDay(){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.add(Calendar.DATE, -1);

		String last = format.format(calendar.getTime());

		return last;

	}

	/**
	 * 获取前一天的时间
	 *
	 * @return 前一天的时间
	 */

	public static String lastDay() {

		Calendar calendar = Calendar.getInstance();

		//得到前一天
		calendar.add(Calendar.DATE, -1);

		Date date = calendar.getTime();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String lastdate = df.format(date);

		return lastdate;

	}

	public static int calculateRemainDay() throws ParseException {

		String nowTime = CreatDate.getDate();

		String lastDate = CreatDate.getYear() + "-12-31";

		int remainDay = OperaDate.daysBetween(nowTime,lastDate);

		return remainDay;

	}

}
