package domein;

public abstract class Werknemer extends Gebruiker{
	// JPA autogenerated id
	private int personeelsnummer;
	private String land;
	private String gemeente;
	private String straat;
	// enum functie
	public Werknemer(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres,
			int personeelsnummer, String land, String gemeente, String straat) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
		setPersoneelsnummer(personeelsnummer);
		setLand(land);
		setGemeente(gemeente);
		setStraat(straat);
	}

	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	private void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
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
}
