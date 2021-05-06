package resourcebundle;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;



public class Taal implements Subject{
	private static ResourceBundle labels;
	private static Locale locale;
	private static Set<Observer> observers = new HashSet<Observer>();
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
		notifyObservers();
	}
	
	public static String geefTekst(String woord) {
		return labels.getString(woord);
	}

	public static void addObservers(Observer obs) {
		observers.add(obs);
		obs.update();
		
	}

	public static void removeObservers(Observer obs) {
		observers.remove(obs);
		
	}
	private static void notifyObservers() {
		observers.forEach(obs -> obs.update());
	}

	@Override
	public void addObserver(Observer obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(Observer obs) {
		// TODO Auto-generated method stub
		
	}
}
