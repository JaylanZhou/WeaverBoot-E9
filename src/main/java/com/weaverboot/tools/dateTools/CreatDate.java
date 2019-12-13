package com.weaverboot.tools.dateTools;

import com.weaverboot.tools.enumTools.frame.DateTypeCondition;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * 时间创建工具类
 *
 * @Author : Jaylan Zhou
 *
 */
public class CreatDate {
	
	private CreatDate(){}

	/**
	 *
	 * 获取日期
	 *
	 * @return 日期 格式:yyyy-MM-dd
	 */

	public static String getDate() {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String time = dateFormat.format(date);

		return time;

	}

	/**
	 *
	 * 获取当前年份
	 *
	 * @return 年份 格式:yyyy
	 */

	public static String getYear() {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

		String time = dateFormat.format(date);

		return time;

	}

	/**
	 *
	 * 获取日期 + 时间
	 *
	 * @return 日期 + 时间  格式:yyyy-MM-dd hh:mm:ss
	 */

	public static String getDateTime() {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String time = dateFormat.format(date);

		return time;

	}

	/**
	 *
	 * 获取Unix时间戳
	 *
	 * @return
	 */

	public static String getUnixTime() {

		long epoch = System.currentTimeMillis()/1000;

		return String.valueOf(epoch);

	}

	/**
	 *
	 * 获取当前时间(枚举类时间格式)
	 *
	 * @return
	 */

	public static String getDateTime(DateTypeCondition dateTypeCondition) {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTypeCondition.toString());

		String time = dateFormat.format(date);

		return time;

	}

	/**
	 *
	 * 获取当前时间(枚举类时间格式)
	 *
	 * @return
	 */

	public static String getDateTime(String dateType) {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);

		String time = dateFormat.format(date);

		return time;

	}
	
}
