package com.snf.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class DataUtil {
	
	public static Timestamp dateToTimeStamp(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);		
		return new Timestamp(c.getTimeInMillis()) ;	
	}
	
	public static Date diminuirDias(Date d,int dias){
		return new DateTime(d).minusDays(dias).toDate();
	}
	
	public static Date somarDias(Date d,int dias){
		return new DateTime(d).plusDays(dias).toDate();
	}

}
