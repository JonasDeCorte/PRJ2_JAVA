package domein;

public class Administrator extends Werknemer {

	public Administrator(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres,
			int personeelsnummer, String land, String gemeente, String straat) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, personeelsnummer, land, gemeente, straat);
	}
	
	public Klant voegKlantToe() {
		return null;
	}
	
	public void wijzigKlant() {
		
	}
	
	public void verwijderKlant() {
		
	}
	
	public Klant haalKlantOp(int klantnummmer) {
		return null;
	}
	
	public Werknemer voegWerknemerToe() {
		return null;
	}
	
	public void wijzigWerknemer() {
		
	}
	
	public void verwijderWerknemer() {
		
	}
	
	public Werknemer haalWerknemerOp(int werknemer) {
		return null;
	}
}
