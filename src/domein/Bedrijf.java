package domein;

import java.util.List;

public class Bedrijf {
	private int bedrijfId;
	private String bedrijfsnaam;
	private String[] telefoonnummers;
	private Adres adres;
	
	private List<Klant> klanten;
			
	public Bedrijf(String bedrijfsnaam, String[] telefoonnummers, Adres adres) {
		setBedrijfsnaam(bedrijfsnaam);
		setTelefoonnummers(telefoonnummers);
		setAdres(adres);
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
	
	public List<Klant> getKlanten() {
		return klanten;
	}
	
	private void setKlanten(List<Klant> klanten) {
		this.klanten = klanten;
	}

	public Adres getAdres() {
		return adres;
	}

	private void setAdres(Adres adres) {
		this.adres = adres;
	}	
}
