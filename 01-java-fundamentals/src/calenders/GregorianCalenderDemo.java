package calenders;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class GregorianCalenderDemo {

	public static void main(String[] args) {
		// get the supported ids for GMT-02:00 (East European Time)
		 String[] ids = TimeZone.getAvailableIDs(2 * 60 * 60 * 1000);
		 // if no ids were returned, something is wrong. get out.
		 if (ids.length == 0)
		     System.exit(0);

		  // begin output
		 System.out.println("Current Time");

		 // create a Pacific Standard Time time zone
		 SimpleTimeZone eet = new SimpleTimeZone(2 * 60 * 60 * 1000, ids[0]);

		 // set up rules for Daylight Saving Time
		 eet.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		 eet.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

		 // create a GregorianCalendar with the Pacific Daylight time zone
		 // and the current date and time
		 Locale bgLocale = new Locale("bg", "BG");
		 Calendar calendar = new GregorianCalendar(eet, bgLocale);
		 Date trialTime = new Date();
		 calendar.setTime(trialTime);

		 // print out a bunch of interesting things
		 System.out.println("ERA: " + calendar.get(Calendar.ERA));
		 System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		 System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		 System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
		 System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
		 System.out.println("DATE: " + calendar.get(Calendar.DATE));
		 System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
		 System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
		 System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		 System.out.println("DAY_OF_WEEK_IN_MONTH: "
		                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		 System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
		 System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
		 System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
		 System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
		 System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
		 System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
		 System.out.println("ZONE_OFFSET: "
		                    + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
		 System.out.println("DST_OFFSET: "
		                    + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
		 
//		 String[] newMonths = {
//				  "яннуари", "февруари", "март", "април", "май", "юни", 
//				  "юли", "август", "септемви", "октомври", "ноември", "декемви"};
//		 DateFormatSymbols dfs = DateFormatSymbols.getInstance(bgLocale);
//		 dfs.setMonths(newMonths);
		 
		 SimpleDateFormat fmt = new SimpleDateFormat("dd MMMM yyyy");
//		 DateFormat fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("bg", "BG"));
		 fmt.setCalendar(calendar);
//		 calendar.add(Calendar.MONTH, 5);
		 System.out.printf("\nCurrent date and time: %s\n\n", fmt.format(calendar.getTimeInMillis()));


//		 System.out.println("Current Time, with hour reset to 3");
//		 calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
//		 calendar.set(Calendar.HOUR, 3);
//		 System.out.println("ERA: " + calendar.get(Calendar.ERA));
//		 System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
//		 System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//		 System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
//		 System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
//		 System.out.println("DATE: " + calendar.get(Calendar.DATE));
//		 System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//		 System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
//		 System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
//		 System.out.println("DAY_OF_WEEK_IN_MONTH: "
//		                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//		 System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
//		 System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
//		 System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
//		 System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
//		 System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
//		 System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
//		 System.out.println("ZONE_OFFSET: "
//		        + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000))); // in hours
//		 System.out.println("DST_OFFSET: "
//		        + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000))); // in hours
		 
		 
	}

}
