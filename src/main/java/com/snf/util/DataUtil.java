package com.snf.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class DataUtil {

	private DataUtil() {
	}

	public static Timestamp dateToTimeStamp(Date d) {
		if(dataIsNull(d))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Date diminuirDias(Date d, int dias) {
		if(dataIsNull(d))
			return null;
		return new DateTime(d).minusDays(dias).toDate();
	}

	public static Date somarDias(Date d, int dias) {
		if(dataIsNull(d))
			return null;
		return new DateTime(d).plusDays(dias).toDate();
	}

	public static DateTime getDateTimeHoraZerada() {
		return new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(),
				0, 0);
	}

	public static Date getDataAtualHoraZerada() {
		DateTime dt = new DateTime(new Date()).withTimeAtStartOfDay();
		return dt.toDate();
	}

	public static Date getDataHoraZerada(Date data) {
		if(dataIsNull(data))
			return null;
		DateTime dt = new DateTime(data).withTimeAtStartOfDay();
		return dt.toDate();
	}

	public static Date getDataAtualHoraFinalDia() {
		DateTime dt = new DateTime(new Date()).withTimeAtStartOfDay().plusDays(1).minusSeconds(1);
		return dt.toDate();
	}

	public static Date getDataHoraFinalDia(Date data) {
		if(dataIsNull(data))
			return null;
		DateTime dt = new DateTime(data).withTimeAtStartOfDay().plusDays(1).minusSeconds(1);
		return dt.toDate();
	}

	public static String getDataFormatada(Date data, String formato) {
		if(dataIsNull(data))
			return null;
		return new DateTime(data).toString(formato);
	}
	
	private static boolean dataIsNull(Date data) {
		return data==null;
	}

}
