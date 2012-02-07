package com.ect.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ect.enumerations.EnumOrderStatus;

import android.util.Log;

/**
 * Class with collection function to general converting.
 * @author Edy Cu Tjong
 * @version 1.2, 11/26/11
 */
public final class Convert
{
	// Debugging
	public static boolean D = false;
	private final static String TAG = "Convert";
	
	// Database format
	public final static String FORMAT_DATETIME_DATABASE = "yyyy-MM-dd HH:mm:ss";
	private final static String FORMAT_DATETIME_STRING_HOUR = "h:mm a";
	private final static String FORMAT_DATETIME_STRING_WEEK = "EEEE";
	private final static String FORMAT_DATETIME_STRING_YEAR = "MMMM d";
	private final static String FORMAT_DATETIME_STRING_DATE = "MMMM d yyyy";
	private final static String FORMAT_DATE_FOR_WHERE_CLAUSE = "yyyy-MM-dd";
	
	// Time multiple
	private final static long MILISECOND_IN_SECOND = 1000;
	private final static long SECOND_IN_MINUTE = 60;
	private final static long MINUTE_IN_HOUR = 60;
	private final static long HOUR_IN_DAY = 24;
	private final static long DAY_IN_WEEK = 7;
	
	private final static long SECOND_IN_HOUR = SECOND_IN_MINUTE * MINUTE_IN_HOUR;
	private final static long SECOND_IN_DAY = SECOND_IN_HOUR * HOUR_IN_DAY;
	private final static long MILISECOND_IN_WEEK = MILISECOND_IN_SECOND * SECOND_IN_DAY * DAY_IN_WEEK;
	
	private final static int TODAY_TO_YESTERDAY_IN_DAY_OF_YEAR = -1;
	
	// Order by database
	static final String ORDER_BY_DEFAULT = "";
	static final String ORDER_BY_ASCENDING = "ASC";
	static final String ORDER_BY_DECENDING = "DESC";
	
	/**
	 * Convert string to date represent moment in time.
	 * @param database_date  String from date time in database.
	 * @return  Date object.
	 */
	public static Date fromStringToDate(String database_date)
	{
		if (D) Log.d(TAG, "fromStringToDate");
		
		Date result_date = new Date();
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_DATABASE);
			result_date = format_date.parse(database_date);
		}
		catch (ParseException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_date;
	} // end of method
	
	/**
	 * Convert date object to string with format from database.
	 * @param date_object  Date object.
	 * @return  String with format from database.
	 */
	public static String fromDateToString(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToString");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_DATABASE);
			result_string = new String(format_date.format(date_object));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	} // end of method
	
	/**
	 * Convert date object to string hour with format from database.
	 * @param date_object  Date object.
	 * @return  String with format from database.
	 */
	public static String fromDateToStringHour(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStringHour");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_STRING_HOUR);
			result_string = new String(format_date.format(date_object));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	} // end of method
	
	/**
	 * Convert date object to string week with format from database.
	 * @param date_object  Date object.
	 * @return  String with format from database.
	 */
	public static String fromDateToStringWeek(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStringWeek");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_STRING_WEEK);
			result_string = new String(format_date.format(date_object));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	} // end of method
	
	/**
	 * Convert date object to string date with format from database.
	 * @param date_object  Date object
	 * @return  String with format from database.
	 */
	public static String fromDateToStringDate(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStringDate");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_STRING_DATE);
			result_string = new String(format_date.format(date_object));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	} // end of method
	
	/**
	 * Convert date object to string year with format from database.
	 * @param date_object  Date object.
	 * @return  String with format from database.
	 */
	public static String fromDateToStringYear(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStringYear");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATETIME_STRING_YEAR);
			result_string = new String(format_date.format(date_object));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	} // end of method
	
	/**
	 * Convert from different time in second to statement string.
	 * @param differenttimeinsecond Different time in second.
	 * @return Statement string in second.
	 */
	public static String fromDifferentTime1MinuteToString(long differenttimeinsecond)
	{
		if (D) Log.d(TAG, "fromDifferentTime1MinuteToString");
		
		String result = String.format(
				"%s second%s ago"
				, differenttimeinsecond > 1 ? differenttimeinsecond : "about an"
				, differenttimeinsecond > 1 ? "s" : "");
		return result;
	}
	
	/**
	 * Convert from different time in hour to statement string.
	 * @param differenttimeinsecond  Different time in second.
	 * @return Statement string in minute.
	 */
	public static String fromDifferentTime1HourToString(long differenttimeinsecond)
	{
		if (D) Log.d(TAG, "fromDifferentTime1HourToString");
			
		long differenttimeinhour = differenttimeinsecond / SECOND_IN_MINUTE;
		String result = String.format(
				"%s minute%s ago"
				, differenttimeinhour > 1 ? differenttimeinhour : "about an"
				, differenttimeinhour > 1 ? "s" : "");
		return result;
	}
	
	/**
	 * Convert from different time in one day to statement string.
	 * @param differenttimeinsecond  Different time in second.
	 * @return Statement string in hours
	 */
	public static String fromDifferentTime1DayToString(long differenttimeinsecond)
	{
		if (D) Log.d(TAG, "fromDifferentTime1DayToString");
		
		long differenttimeinday = differenttimeinsecond / SECOND_IN_HOUR;
		String result = String.format(
				"%s hour%s ago"
				, differenttimeinday > 1 ? differenttimeinday : "about an"
				, differenttimeinday > 1 ? "s" : "");
		
		return result;
	}
	
	/**
	 * Convert from date object in yesterday to statement string.
	 * @param date_object  Date object in yesterday.
	 * @return Statement string of yesterday.
	 */
	public static String fromDifferentYesterdayToString(Date date_object)
	{
		if (D) Log.d(TAG, "fromDifferentYesterdayToString");
		
		String result = String.format("Yesterday at %s"
				, Convert.fromDateToStringHour(date_object));
		
		return result;
	}
	
	/**
	 * Convert from date object in less than one week to statement string.
	 * @param date_object  Date object less than one week.
	 * @return Statement string of one week.
	 */
	public static String fromDateLessThan1WeekToString(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateLessThan1WeekToString");
		
		String result = String.format("%s at %s"
				, Convert.fromDateToStringWeek(date_object)
				, Convert.fromDateToStringHour(date_object));
		
		return result;
	}
	
	/**
	 * Convert date object to string current year. 
	 * @param date_object  Date object.
	 * @return String date at current year.
	 */
	public static String fromDateAtCurrentYearToString(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateAtCurrentYearToString");
		
		String result = String.format("%s at %s"
				, Convert.fromDateToStringYear(date_object)
				, Convert.fromDateToStringHour(date_object));
		
		return result;
	}
	
	/**
	 * Convert date to string general display date time.
	 * @param date_object  Date object.
	 * @return String general display date time.
	 */
	public static String fromDateToStringDisplay(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStringDisplay");
		
		String result = String.format("%s at %s"
				, Convert.fromDateToStringDate(date_object)
				, Convert.fromDateToStringHour(date_object));
		
		return result;
	}
	
	/**
	 * Reset time to 0.
	 * @param date_object  Date object.
	 * @return Date that already reset time to 0.
	 */
	public static Date getZeroTimeDate(Date date_object)
	{
		if (D) Log.d(TAG, "getZeroTimeDate");
		
	    Date res = date_object;
	    Calendar calendar = Calendar.getInstance();

	    calendar.setTime( date_object );
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    res = calendar.getTime();

	    return res;
	}
	
	/**
	 * Convert date object to string statement.
	 * @param date_object  Date object
	 * @return String statement of date.
	 */
	public static String fromDateToStatement(Date date_object)
	{
		if (D) Log.d(TAG, "fromDateToStatement");
		
		// Declare result statement
		String result = null;

		// Declare calendar now, yesterday, and date object
		Calendar calendarNow = Calendar.getInstance();
		Calendar calendarYesterday = Calendar.getInstance();
		calendarYesterday.add(Calendar.DAY_OF_YEAR, TODAY_TO_YESTERDAY_IN_DAY_OF_YEAR);
		Calendar calenderObject = Calendar.getInstance();
		calenderObject.setTime(date_object);
		
		// Compare whether date without time is same
		if (getZeroTimeDate(calendarNow.getTime()).compareTo(getZeroTimeDate(date_object)) == 0)
		{
			// Declare and initialize different time in mili second and second
			long differentTimeInMilisecond = calendarNow.getTimeInMillis() - calenderObject.getTimeInMillis();
			long differentTimeInSecond = differentTimeInMilisecond / MILISECOND_IN_SECOND;

			// Different in 1 minute
			if (differentTimeInSecond < SECOND_IN_MINUTE)
			{
				result = fromDifferentTime1MinuteToString(differentTimeInSecond);
			}
			// Different in 1 hour
			else if (differentTimeInSecond < SECOND_IN_HOUR)
			{
				result = fromDifferentTime1HourToString(differentTimeInSecond);
			}
			// Different in 1 day
			else if (differentTimeInSecond < SECOND_IN_DAY)
			{
				result = fromDifferentTime1DayToString(differentTimeInSecond);
			}
		}
		// Compare whether date is at yesterday
		else if (getZeroTimeDate(calendarYesterday.getTime()).compareTo(getZeroTimeDate(date_object)) == 0)
		{
			result = fromDifferentYesterdayToString(date_object);
		}
		// Compare whether date is at least 1 week ago
		else if (MILISECOND_IN_WEEK > calendarNow.getTime().getTime() - date_object.getTime())
		{
			result = fromDateLessThan1WeekToString(date_object);
		}
		// Compare whether data is in before current year
		else if (calenderObject.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR))
		{
			result = fromDateAtCurrentYearToString(date_object);
		}
		// When data is after current year
		else
		{
			result = fromDateToStringDisplay(date_object);
		}
		
		return result;
	} // end of method
	
	/**
	 * Convert int from database to enumeration order status.
	 * @param status  Status in integer.
	 * @return Enumeration of order status.
	 */
	public static EnumOrderStatus toEnumOrderStatus(int status)
	{
		EnumOrderStatus result = EnumOrderStatus.PROCESS_SERVED;
		
		switch(status)
		{
		case 0: result = EnumOrderStatus.PROCESS_SERVED; break;
		case 1: result = EnumOrderStatus.ALREADY_SERVED; break;
		case 2: result = EnumOrderStatus.ALREADY_PAY; break;
		}
		
		return result;
	}
	
	/**
	 * Convert from date time to date only.
	 * @param date  Date time.
	 * @return Date in string.
	 */
	public static String fromDateToDateOnly(Date date)
	{
		if (D) Log.d(TAG, "fromDateToDateOnly");
		
		String result_string = null;
		
		try
		{
			SimpleDateFormat format_date = new SimpleDateFormat(FORMAT_DATE_FOR_WHERE_CLAUSE);
			result_string = new String(format_date.format(date));
		}
		catch (NullPointerException e)
		{
			Log.e(TAG, e.toString());
		}
		catch (IllegalArgumentException e)
		{
			Log.e(TAG, e.toString());
		}
		
		return result_string;
	}
	
} // end of class
