package domein;

public class Adres {
	private String land;
	private String gemeente;
	private String postcode;
	private String straat;
	private int huisnummer;
	private String busnummer;
		
	public Adres(String land, String gemeente, String postcode, String straat, int huisnummer, String busnummer) {
		setLand(land);
		setGemeente(gemeente);
		setPostcode(postcode);
		setStraat(straat);
		setHuisnummer(huisnummer);
		setBusnummer(busnummer);
	}

	public String getLand() {
		return land;
	}
	
	private void setLand(String land) {
		this.land = land;
	}
	
	public String getGemeente() {
		return gemeente;
	}
	
	private void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	private void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getStraat() {
		return straat;
	}
	
	private void setStraat(String straat) {
		this.straat = straat;
	}
	
	public int getHuisnummer() {
		return huisnummer;
	}
	
	private void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}
	
	public String getBusnummer() {
		return busnummer;
	}
	
	private void setBusnummer(String busnummer) {
		this.busnummer = busnummer;
	}	
}