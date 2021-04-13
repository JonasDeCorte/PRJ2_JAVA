package resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Taal {
	private static ResourceBundle labels;
	private static Locale locale;
	
	public static void instellenTaal(int keuze) {
		switch(keuze) {
			case 0: {
				locale = new Locale("Nederlands");
				break;
			}
			case 1: {
				locale = new Locale("Engels");
				break;
			}
			case 2:{
				locale = new Locale("Frans");
				break;
			}
			default:
				locale = new Locale("Nederlands");
		}
		labels = ResourceBundle.getBundle("resourcebundle.LanguageBundle", locale);
	}
	
	public static String geefTekst(String woord) {
		return labels.getString(woord);
	}
}
