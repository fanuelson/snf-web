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
	
	public static DateTime getDateTimeHoraZerada(){
		return new DateTime(new DateTime().getYear(),new DateTime().getMonthOfYear(),new DateTime().getDayOfMonth(),0,0);
	}
	
	public static Date getDataAtualHoraZerada() {
		DateTime dt = new DateTime(new Date()).withTimeAtStartOfDay();
		return dt.toDate();
	}
	public static Date getDataHoraZerada(Date data) {
		DateTime dt = new DateTime(data).withTimeAtStartOfDay();
		return dt.toDate();
	}
	
	public static Date getDataAtualHoraFinalDia() {
		DateTime dt = new DateTime(new Date()).withTimeAtStartOfDay()
				.plusDays(1)
				.minusSeconds(1);
		return dt.toDate();
	}
	
	public static Date getDataHoraFinalDia(Date data) {
		DateTime dt = new DateTime(data).withTimeAtStartOfDay()
				.plusDays(1)
				.minusSeconds(1);
		return dt.toDate();
	}
	
	public static String getDataFormatada(Date data, String formato) {
		return new DateTime(data).toString(formato);
	}

}
