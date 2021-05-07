package domein.controllers;

import java.util.Set;

import domein.Klant;
import domein.Werknemer;
import domein.beheerders.KlantBeheerder;
import domein.beheerders.WerknemerBeheerder;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;
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
	public boolean bestaatKlantnummer(int klantnummer) {
		return klantBeheerder.bestaatKlantnummer(klantnummer);
	}

	public void voegKlantToe(Klant klant) {
		klantBeheerder.voegKlantToe(klant);
	}

	public void wijzigKlant(Klant klant, String origineleGebruikersnaam) {
		klantBeheerder.wijzigKlant(klant, origineleGebruikersnaam);
	}

	public void pasFilterAanKlant( String gebruikersnaam, String naam,String voornaam, String bedrijfsnaam,Set<GEBRUIKERSTATUS> status)
	{
		klantBeheerder.pasFilterAan( gebruikersnaam,naam,voornaam, bedrijfsnaam, status);
	}
	public void pasFilterAanWerknemer(String gebruikersnaam, String naam,String voornaam,String functie,Set<GEBRUIKERSTATUS> status)
	{
		werknemerBeheerder.pasFilterAan(gebruikersnaam, naam, voornaam, functie,status);
	}

	public ObservableList<Klant> getAllKlanten() {
		return klantBeheerder.haalKlantenOp();
	}
	
	public ObservableList<Werknemer> getAllWerknemer() {
		return werknemerBeheerder.haalWerknemersOp();
	}
	public void voegWerknemerToe (Werknemer werknemer) {
		werknemerBeheerder.voegWerknemerToe(werknemer);
	}
	public void wijzigWerknemer(Werknemer werknemer) {
		werknemerBeheerder.wijzigWerknemer(werknemer);
	}
}