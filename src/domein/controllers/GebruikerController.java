package domein.controllers;

import domein.Klant;
import domein.Werknemer;
import domein.beheerders.KlantBeheerder;
import domein.beheerders.WerknemerBeheerder;

public class GebruikerController {
	private KlantBeheerder klantBeheerder;
	private WerknemerBeheerder werknemerBeheerder;
	
	public GebruikerController(KlantBeheerder klantBeheerder, WerknemerBeheerder werknemerBeheerder) {
		this.klantBeheerder = klantBeheerder;
		this.werknemerBeheerder = werknemerBeheerder;
	}
	
	public void voegWerknemerToe (Werknemer werknemer) {
		werknemerBeheerder.voegWerknemerToe(werknemer);
	}
	
	public void voegKlantToe (Klant klant) {
		klantBeheerder.voegKlantToe(klant);
	}	
}