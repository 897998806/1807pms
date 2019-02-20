package com.zscms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Administrator
 *
 */
public class DateUtil {

	public static String getStrDate() {
		// 获得日历对象
		Calendar cal = Calendar.getInstance();
		// 当前的年
		int year = cal.get(Calendar.YEAR);
		// 当前的月月份是从0开始的
		int month = cal.get(Calendar.MONDAY) + 1;
		// 当前的日
		int date = cal.get(Calendar.DAY_OF_MONTH);
		// 当前小时
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		/*// 当前的分
		int minute = cal.get(Calendar.MINUTE);
		// 当前的秒
		int second = cal.get(Calendar.SECOND);*/
		//通过获得的当前小时来判断是上午中午下午
		String str="";
		if (hour>=6&&hour<=11) {
			str="上午好!";
		}else if (hour>11&&hour<=14){
			str="中午好!";
		}else if(hour>14&&hour<=18) {
			str="下午好!";
		}else if(hour>18&&hour<24) {
			str="晚上好!";
		}
		return year + "-" + month + "-" + date +str ;

	}

	/**
	 * 字符串转Date的方法
	 * 
	 * @param time    字符串
	 * @param pattern 输入字符串的时间格式
	 * @return 返回的是date 的时间
	 * @throws ParseException
	 */
	public static Date getStrToDate(String time, String pattern) throws ParseException {
		// 获得格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 返回格式化号的时间
		return sdf.parse(time);
	}

	/**
	 * date 转string 时间的方法
	 * 
	 * @param time    date 类的时间
	 * @param pattern 把时间格式化什么养
	 * @return 字符串类的时间
	 */
	public static String getDateToStr(Date time, String pattern) {
		// 获得格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 返回格式化号的时间
		return sdf.format(time);

	}
}
