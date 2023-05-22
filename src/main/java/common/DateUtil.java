package common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	public static Timestamp addDays(Timestamp date, int days, int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		cal.add(Calendar.HOUR, hours);
		cal.add(Calendar.MINUTE, minutes);
		cal.add(Calendar.SECOND, seconds);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp addMilli(Timestamp d1, long diff) {
		Calendar timeout = Calendar.getInstance();
		timeout.setTimeInMillis(d1.getTime() + diff);
		Timestamp tsNew = new Timestamp(timeout.getTime().getTime());
		return tsNew;

	}

	public static boolean leapCheck(Integer year) 
	{
		boolean isLeap = false;

		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			isLeap = true;
		}
		return isLeap;
	}

	public static boolean isValidDate(String dateStr, DateTimeFormatter dateFormatter) 
	{
		try 
		{
			LocalDate.parse(dateStr, dateFormatter);
		} 
		catch (DateTimeParseException e) 
		{
			return false;
		}
		return true;
	}
	
	public static Timestamp prepareTS(Integer day, Integer mnth, Integer year, DateTimeFormatter frmt) 
	{
		String currDateStr = null;
		String mthStr = null;
		String yrStr = null;
		String mthYrStr = null;
		String dayStr = null;
		LocalDateTime frDt = null;		
		
		yrStr = Integer.toString(year).trim();
		
		if(day<10)
		{
		dayStr = '0'+Integer.toString(day).trim();
		}
		else
		{
		dayStr = Integer.toString(day).trim();	
		}

		if (mnth >= 10) {
			mthStr = Integer.toString(mnth).trim();
		} else {
			mthStr = '0' + Integer.toString(mnth).trim();
		}
		
		mthYrStr = mthStr.trim() + '-' + yrStr.trim();
		currDateStr=dayStr+'-'+mthYrStr;
		frDt = LocalDateTime.parse(currDateStr, frmt);						
		return Timestamp.valueOf(frDt);
	}


	public static String prepareTSString(Integer day, Integer mnth, Integer year, DateTimeFormatter frmt) 
	{
		String currDateStr = null;
		String mthStr = null;
		String yrStr = null;
		String mthYrStr = null;
		String dayStr = null;
		LocalDateTime frDt = null;
		Timestamp ts = null;
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		yrStr = Integer.toString(year).trim();
		
		if(day<10)
		{
		dayStr = "0"+Integer.toString(day).trim();
		}
		else
		{
		dayStr = Integer.toString(day).trim();	
		}

		if (mnth >= 10) {
			mthStr = Integer.toString(mnth).trim();
		} else {
			mthStr = "0" + Integer.toString(mnth).trim();
		}

		currDateStr = yrStr.trim()+"-"+mthStr.trim()+"-"+dayStr.trim()+" "+"10:09:25";
	    Date date=null;
		try {
			date = formatter.parse(currDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Timestamp timeStampDate = new Timestamp(date.getTime());
		return frmt.format(timeStampDate.toLocalDateTime());
	}

	
}