package aeroport.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public  class Converter {
	
	public static LogicalDuration ConvertFromDoubleMinuteToLogicalDuration(double value){
		int minute = (int) value;
		int seconde = (int)((value-minute)*60);
		return LogicalDuration.ofSeconds(minute*60+seconde);
	}
	public static String ConvertFromLogicalDurationSecondToDoubleMinute(LogicalDuration d){
		double m = d.DoubleValue()/60.0;
		return String.valueOf(m);
	}
	
	public static void main(String[] args){
		System.out.println(getDay(new LogicalDateTime("05/01/2016 20:00:00")));
		
	}
	public static boolean isWeekEndDay(LogicalDateTime d){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse(d.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date=null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if((c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)||(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)){
			return true;
		}
		else
			return false;
	}
	public static String getDay(LogicalDateTime d){

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse(d.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (c.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
			return "MONDAY";
		}
		else if (c.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY){
			return "TUESDAY";
		}
		else if (c.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY){
			return "WEDNESDAY";
		}
		else if (c.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY){
			return "THURSDAY";
		}
		else if (c.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
			return "FRIDAY";
		}
		else if (c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
			return "SATURDAY";
		}
		else
			return "SUNDAY";
	}

	
}
