package domein;

import java.util.List;

public class Technieker extends Werknemer{

	private List<Ticket> tickets;
	
	public Technieker(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres,
			int personeelsnummer, String land, String gemeente, String straat) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, personeelsnummer, land, gemeente, straat);
	}
	
	public void wijzigTicket(Ticket ticket) {
		
	}

}
