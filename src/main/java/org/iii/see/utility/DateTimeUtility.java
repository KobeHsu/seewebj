package org.iii.see.utility;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class DateTimeUtility {

	public static int DATE_END = 0;
	public static int DATE_BEGIN = 1;
	
	public static Timestamp convertDateStringToSqlTimestamp(String dateStr, String format, int dateBeginOrEnd) {
		Timestamp result = null;		
		 		
		SimpleDateFormat sdf = new SimpleDateFormat(format + " HH:mm:ss.SSS");
		try {
			String completedDateStr = dateStr;
			if (dateBeginOrEnd == DATE_END) {
				completedDateStr = completedDateStr.concat(" 23:59:59.999");	
			} else if (dateBeginOrEnd == DATE_BEGIN) {
				completedDateStr = completedDateStr.concat(" 00:00:00.000");
			}
			result = new Timestamp(sdf.parse(completedDateStr).getTime());
		} catch (ParseException e) {
			;
		}		
		
		return result;
	}

	public static Date convertDateStringToSqlDate(String dateStr, String format) {
		Date result = null;		
		 		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			result = new Date(sdf.parse(dateStr).getTime());
		} catch (ParseException e) {
			;
		}		
		
		return result;
	}
	
	public static String now(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new java.util.Date());
	}
	
	public static String convertTimestampToDateTimeString(Timestamp timestamp) {
		if (timestamp == null) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdfTime.format(timestamp);		
	}
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp((new java.util.Date()).getTime());
	}
}
