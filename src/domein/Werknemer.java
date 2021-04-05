package domein;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public abstract class Werknemer extends Gebruiker{
	
	private int personeelsnummer;
	@Embedded
	private Adres adres;
	public String wachtwoord;

	public Werknemer(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres,
			int personeelsnummer, Adres adres) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
		setPersoneelsnummer(personeelsnummer);
		setAdres(adres);
	}
	
	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	private void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}

	public Adres getAdres() {
		return adres;
	}

	private void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
}
