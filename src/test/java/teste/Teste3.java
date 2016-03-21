package teste;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

public class Teste3 {

	public static void main(String[] args) throws ParseException {
		Date d = new DateTime(new DateTime().getYear(),new DateTime().getMonthOfYear(),new DateTime().getDayOfMonth(),0,0).minusDays(8).toDate();
        System.out.println(d.toString());
	}
	
	public static double getDoubleValue(Locale loc, String value)
		    throws ParseException {
		    // use the default locale
		    return NumberFormat.getInstance(loc).parse(value).doubleValue();
	}

}
