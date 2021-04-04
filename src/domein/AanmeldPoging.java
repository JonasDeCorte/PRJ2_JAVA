//test
package domein;

import java.time.LocalDateTime;

public class AanmeldPoging {
	private int aanmeldPogingId;
	private LocalDateTime tijdstipPoging;
	private boolean isGelukt;	
	private String gebruikersNaam;
	
	public AanmeldPoging(boolean isGelukt, String gebruikersNaam) {
		this.tijdstipPoging = LocalDateTime.now();
		setGelukt(isGelukt);
		setGebruikersNaam(gebruikersNaam);
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

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}

	
}
