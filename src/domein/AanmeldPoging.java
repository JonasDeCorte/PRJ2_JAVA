//test
package domein;

import java.time.LocalDateTime;

public class AanmeldPoging {
	private LocalDateTime tijdstipPoging;
	private boolean isGelukt;	
	private Gebruiker gebruiker;
	
	public AanmeldPoging(boolean isGelukt, Gebruiker gebruiker) {
		this.tijdstipPoging = LocalDateTime.now();
		setGelukt(isGelukt);
		setGebruiker(gebruiker);
	}

	public LocalDateTime getTijdstipPoging() {
		return tijdstipPoging;
	}


	public boolean isGelukt() {
		return isGelukt;
	}

	private void setGelukt(boolean isGelukt) {
		this.isGelukt = isGelukt;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	private void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}	
}
