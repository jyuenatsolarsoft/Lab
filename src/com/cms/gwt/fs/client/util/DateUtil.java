package com.cms.gwt.fs.client.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtil {

	public static String formatDate(Object value) {
		if (value == null) {
			return "";
		}
		return formatDate(value.toString());
	}

	public static String formatDate(String value) {
		return formatDate(value, "yyyy-MM-dd");
	}

	@SuppressWarnings("deprecation")
	public static String formatDate(String value, String format) {
		String fmtValue = "";

		if (value == null || "".equals(value)) {
			return fmtValue;
		}

		DateTimeFormat fmt = DateTimeFormat.getFormat(format);
		try {
			Date dt = new Date(value);
			fmtValue = fmt.format(dt);
		} catch (Exception e) {
			fmtValue = value;
		}
		return fmtValue;
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String format) {
		if (date == null || "".equals(date.toString())) {
			return "";
		}

		DateTimeFormat fmt = DateTimeFormat.getFormat(format);
		try {
			return fmt.format(date);
		} catch (Exception e) {
			return date.toString();
		}
	}

	public static Date getDateFromString(String strDate) {
		Calendar date = new GregorianCalendar();
		
		if (strDate == null || "".equals(strDate)) {
			return null;
		}
		try {
			String[] arrDate = strDate.split("\\-", 3);
			date.set(Calendar.YEAR, Integer.valueOf(arrDate[0]));
			date.set(Calendar.MONTH, Integer.valueOf(arrDate[1]) - 1);
			date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(arrDate[2]));
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);

		} catch (NumberFormatException e) {
			return null;
		}

		return date.getTime();
	}
	
	public static String formatTime(String time) {
		if (time == null || "".equals(time)) {
			return "";
			
		}

		String[] arrTime = time.split("\\.", 3);		
		return arrTime[0] + ":" + arrTime[1];
	}

}
