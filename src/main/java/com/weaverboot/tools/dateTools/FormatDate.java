package com.weaverboot.tools.dateTools;

import com.weaverboot.tools.enumTools.frame.DateTypeCondition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 时间格式化类
 *
 * @Author : Jaylan Zhou
 *
 */
public class FormatDate {



	private FormatDate() {}

	/**
	 *
	 * 格式化时间（格式枚举类）
	 *
	 * @param date 需要转换的时间
	 * @param beDateTypeCondition 最初的时间类型
	 * @param toDateTypeCondition 转换成的时间类型
	 * @return 时间字符串
	 */
	public static String formatDateReturnString(String date, DateTypeCondition beDateTypeCondition,DateTypeCondition toDateTypeCondition){

		Date d = null;

		try {

			d = new SimpleDateFormat(beDateTypeCondition.toString()).parse(date);

			String time = new SimpleDateFormat(toDateTypeCondition.toString()).format(d);

			return time;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 格式化时间（时间类型字符串）
	 *
	 * @param date 需要转换的时间
	 * @param beDateTypeCondition 最初的时间类型
	 * @param toDateTypeCondition 转换成的时间类型
	 * @return 时间字符串
	 */
	public static String formatDateReturnString(String date, String beDateTypeCondition,String toDateTypeCondition){

		Date d = null;

		try {

			d = new SimpleDateFormat(beDateTypeCondition).parse(date);

			String time = new SimpleDateFormat(toDateTypeCondition).format(d);

			return time;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 格式化时间（枚举类）
	 *
	 * @param date 需要转换的时间
	 * @param dateTypeCondition 需要转换成的时间类型
	 * @return Date 实体类
	 */

	public static Date formatDateReturnDate(String date, DateTypeCondition dateTypeCondition){

		Date d = null;

		try {

			d = new SimpleDateFormat(dateTypeCondition.toString()).parse(date);

			return d;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 时间类型转换（时间类型字符串）
	 *
	 * @param date 需要转换的时间
	 * @param dateTypeCondition 转换的时间类型
	 * @return Date 实体类
	 */
	public static Date formatDateReturnDate(String date, String dateTypeCondition){

		Date d = null;

		try {

			d = new SimpleDateFormat(dateTypeCondition).parse(date);

			return d;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 日期时间格式化
	 *
	 * @param date 日期时间String 格式：yyyy-MM-dd hh:mm:ss
	 * @return 日期时间String 格式：yyyy-MM-dd hh:mm:ss
	 */

	public static String formatDateTimeReturnString(String date) {

		Date d = null;

		try {

			d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);

			String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);

			return time;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 日期时间格式化
	 *
	 * @param date 日期时间 String 格式：yyyy-MM-dd hh:mm:ss
	 * @return Date类
	 */

	public static Date formatDateTimeReturnDate(String date) {

		Date d = null;

		try {

			d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);

			return d;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 日期格式化
	 *
	 * @param date
	 * @return
	 */

	public static String formatDateReturnString(String date) {

		Date d = null;

		try {

			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

			String time = new SimpleDateFormat("yyyy-MM-dd").format(d);

			return time;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 日期格式化
	 *
	 * @param date
	 * @return
	 */

	public static Date formatDateReturnDate(String date) {

		Date d = null;

		try {

			d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

			return d;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	public static String formatTimeReturnString(String time) {

		Date d = null;

		try {

			d = new SimpleDateFormat("HH:mm:ss").parse(time);

			String formatTime = new SimpleDateFormat("HH:mm:ss").format(d);

			return formatTime;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 取到日期的年份
	 *
	 * @param time 日期年份
	 * @return
	 */

	public static String formatTimeReturnYearString(String time) {

		Date d = null;

		try {

			d = new SimpleDateFormat("yyyy").parse(time);

			String formatTime = new SimpleDateFormat("yyyy").format(d);

			return formatTime;//

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}

	/**
	 *
	 * 时间格式化
	 *
	 * @param time
	 * @return
	 */

	public static Date formatTimeReturnDate(String time) {

		Date d = null;

		try {

			d = new SimpleDateFormat("HH:mm:ss").parse(time);

			return d;

		} catch (ParseException e) {

			throw new RuntimeException("格式化日期失败,原因为:"+e.getMessage());

		}

	}



}
