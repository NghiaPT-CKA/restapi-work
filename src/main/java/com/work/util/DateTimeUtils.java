package com.work.util;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String HHMMSS = "HHmmss";
	static DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYYMMDD);
	static DateTimeFormatter tf = DateTimeFormatter.ofPattern(HHMMSS);

	/**
	 * String to LocalDate
	 * 
	 * @param StringDate
	 * @return LocalDate
	 */
	public static LocalDate stringToLocalDate(String date) {
		LocalDate ld = LocalDate.parse(date, df);
		return ld;
	}

	/**
	 * String to date
	 * 
	 * @param StringDate
	 * @return Sql.date
	 */
	public static Date stringToSQLDate(String date) {
		Date SQLDate = Date.valueOf(date);
		return SQLDate;
	}

	/**
	 * LocalDate to String
	 * 
	 * @param LocalDate
	 * @return String
	 */
	public static String localDatetoString(LocalDate date) {
		if (date == null) {
			return "";
		}
		String dateToLocalDate = date.format(df);
		return dateToLocalDate;
	}

	/**
	 * Date to String
	 * 
	 * @param date
	 * @return String
	 */
	public static String datetoString(Date date) {
		if (date == null) {
			return "";
		}
		return localDatetoString(dateToLocalDate(date));
	}

	/**
	 * Date to LocalDate
	 * 
	 * @param sql.date
	 * @return LocalDate
	 */
	public static LocalDate dateToLocalDate(Date date) {
		LocalDate ld = date.toLocalDate();
		return ld;
	}

	/**
	 * LocalDate to date
	 * 
	 * @param LocalDate
	 * @return sql.date
	 */
	public static Date localDateToSQLDate(LocalDate date) {
		Date SQLDate = Date.valueOf(date);
		return SQLDate;
	}

	/**
	 * LocalTime to Time
	 * 
	 * @param LocalTime
	 * @return sql.time
	 */
	public static Time localTimetoSQLTime(LocalTime time) {
		Time sqlTime = Time.valueOf(time);
		return sqlTime;
	}

	/**
	 * Time to localtime
	 * 
	 * @param sql.time
	 * @return localTime
	 */
	public static LocalTime timeToLocalTime(Time time) {
		LocalTime lt = time.toLocalTime();
		return lt;
	}

	/**
	 * String to localtime
	 * 
	 * @param String
	 * @return localTime
	 */
	public static LocalTime stringToLocalTime(String time) {
		LocalTime lt = LocalTime.parse(time, tf);
		return lt;
	}

	/**
	 * LocalTime to String
	 * 
	 * @param Localtime
	 * @return String
	 */
	public static String localTimeToString(LocalTime time) {
		if (time == null) {
			return "";
		}
		String timelc = time.format(tf);
		return timelc;
	}

	/**
	 * Time to String
	 * 
	 * @param sql.time
	 * @return String
	 */
	public static String timeToString(Time time) {
		if (time == null) {
			return "";
		}
		return localTimeToString(timeToLocalTime(time));
	}

	/**
	 * Check is valid date string
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean isValid(String dateStr) {
		DateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
