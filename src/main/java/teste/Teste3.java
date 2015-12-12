package teste;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Teste3 {

	public static void main(String[] args) throws ParseException {
	    Locale meuLocal = new Locale( "pt","br");   
        NumberFormat nfVal = NumberFormat.getCurrencyInstance( meuLocal );   
       double teste = getDoubleValue(meuLocal, (String) nfVal.format("5.99"));  
        //teste = Double.parseDouble(nfVal.format(teste).trim());
        System.out.println(teste);
	}
	
	public static double getDoubleValue(Locale loc, String value)
		    throws ParseException {
		    // use the default locale
		    return NumberFormat.getInstance(loc).parse(value).doubleValue();
	}

}
