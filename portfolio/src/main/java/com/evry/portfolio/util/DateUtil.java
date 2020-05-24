package com.evry.portfolio.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtil {
	protected final static Log logger = LogFactory.getLog(DateUtil.class);

	public static Date getUtilDateFromString(String stringDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse(stringDate);
			logger.info("===getUtilDateFromString()====>" + date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDateFromString(String stringDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(stringDate);
			logger.info("===getDateFromString()====>" + date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getCurrentDateAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate = formatter.format(date);
		logger.info("===getCurrentDateAsString()====>" + currentDate);
		return currentDate;
	}

	public static String getCurrentDateTimeAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDateTime = formatter.format(date);
		logger.info("===getCurrentDateTimeAsString()====>" + currentDateTime);

		return currentDateTime;
	}

	public static String getDateAsString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = formatter.format(date);
		logger.info("===getDateAsString()====>" + currentDate);
		return currentDate;
	}
	public static Date getDateGivenDaysBefore(int noOfDays){
		 Date d = new Date();//
		 Date dateBefore = new Date(d.getTime() - noOfDays * 24 * 3600 * 1000L); 
		 return dateBefore;
	}
	public static String convertUtilDateToDateWithAmPm(Date date,String formatString){
		String dateString2="";
		if(null!=date){
		DateFormat dateFormat2 = new SimpleDateFormat(formatString);
    	 dateString2 = dateFormat2.format(date).toString();
		}
    	return dateString2;
	}
}
