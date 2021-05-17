package domein.controllers;

import domein.Rapport;
import domein.Ticket;
import domein.beheerders.RapportBeheerder;
import javafx.collections.ObservableList;

public class RapportController {
	private RapportBeheerder rapportBeheerder;

	public RapportController(/*Ticket geselecteerdeTicket*/) {
		this.rapportBeheerder = new RapportBeheerder(/*geselecteerdeTicket*/);
	}

	public void voegRapportToe(Rapport rapport) {
		rapportBeheerder.voegRapportToe(rapport);
	}

	public void pasRapportAan(Rapport rapport) {
		rapportBeheerder.pasRapportAan(rapport);
	}
	public void pasFilterAan( String rapportnaam, String ticket) {		
		rapportBeheerder.pasFilterAan( rapportnaam, ticket);
	}

	public ObservableList<Rapport> getRapportsLijst() {
		return rapportBeheerder.getRapportLijst();
	}

	
}
