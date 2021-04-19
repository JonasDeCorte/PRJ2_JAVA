package domein.controllers;

import java.util.Set;

import domein.Klant;
import domein.Werknemer;
import domein.beheerders.KlantBeheerder;
import domein.beheerders.WerknemerBeheerder;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.collections.ObservableList;

public class GebruikerController {
	private KlantBeheerder klantBeheerder;
	private WerknemerBeheerder werknemerBeheerder;
	
	public GebruikerController() {
		this.klantBeheerder = new KlantBeheerder();
		this.werknemerBeheerder = new WerknemerBeheerder();
	}
	public boolean bestaatKlant(String gebruikersNaam) {
		return klantBeheerder.bestaatKlant(gebruikersNaam);
	}

	public void voegKlantToe(Klant klant) {
		klantBeheerder.voegKlantToe(klant);
	}

	public void wijzigKlant(Klant klant) {
		klantBeheerder.wijzigKlant(klant);
	}

	public void pasFilterAanKlant(int klantnummer, String gebruikersnaam, String bedrijfsnaam,Set<GEBRUIKERSTATUS> status)
	{
		klantBeheerder.pasFilterAan(klantnummer, gebruikersnaam, bedrijfsnaam, status);
	}

	public ObservableList<Klant> getAllKlanten() {
		return klantBeheerder.haalKlantenOp();
	}
	public void voegWerknemerToe (Werknemer werknemer) {
		werknemerBeheerder.voegWerknemerToe(werknemer);
	}
}