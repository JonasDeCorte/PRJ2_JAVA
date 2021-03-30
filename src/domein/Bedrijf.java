package domein;

import java.util.List;

public class Bedrijf {
	private String bedrijfsnaam;
	private String[] telefoonnummers;
	private String land;
	private String gemeente;
	private String straat;
	private List<Klant> klanten;
		
	public Bedrijf(String bedrijfsnaam, String[] telefoonnummers, String land, String gemeente, String straat, Klant klant) {
		setBedrijfsnaam(bedrijfsnaam);
		setTelefoonnummers(telefoonnummers);
		setLand(land);
		setGemeente(gemeente);
		setStraat(straat);
		klanten.add(klant);
	}
	
	public String getBedrijfsnaam() {
		return bedrijfsnaam;
	}
	
	private void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam = bedrijfsnaam;
	}
	
	public String[] getTelefoonnummers() {
		return telefoonnummers;
	}
	
	private void setTelefoonnummers(String[] telefoonnummers) {
		this.telefoonnummers = telefoonnummers;
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
	
	public String getStraat() {
		return straat;
	}
	
	private void setStraat(String straat) {
		this.straat = straat;
	}

	public List<Klant> getKlanten() {
		return klanten;
	}
}
