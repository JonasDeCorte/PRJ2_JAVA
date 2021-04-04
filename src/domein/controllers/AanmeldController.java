package domein.controllers;

import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.AanmeldPoging;

import domein.enumerations.GEBRUIKERSTATUS;

public class AanmeldController {
	int maximumPogingen = 10;
	private Werknemer werknemer;
	private AanmeldPogingDao aanmeldPogingDao;
	private WerknemerDao werknemerDao;
	
	private AanmeldController(Werknemer werknemer, AanmeldPogingDao aanmeldPogingDao, WerknemerDao werknemerDao) {
		this.werknemer = werknemer;
		this.aanmeldPogingDao = aanmeldPogingDao;
		this.werknemerDao = werknemerDao;
	}

	
	public void aanmelden(String gebruikersnaam, String wachtwoord) {
		GEBRUIKERSTATUS gebruikerStatus ;
		Werknemer werknemer = null;
		boolean gefaald = false;
		try {
			gebruikerStatus = werknemerDao.bestaatWerkemer(gebruikersnaam);
		}catch (Exception e) {
				throw new IllegalArgumentException("Gebruikersnaam bestaat niet.");
		}

		if (gebruikerStatus == GEBRUIKERSTATUS.NIET_ACTIEF) {
			throw new IllegalArgumentException("Aanmelden niet mogelijk, uw account is niet actief.");
		}

		if (gebruikerStatus == GEBRUIKERSTATUS.GEBLOKKERD) {
			throw new IllegalArgumentException("Aanmelden niet mogelijk, uw account is niet geblokkeerd.");
		}
	
		try {
			werknemer = werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord);
		}catch (Exception e) {
			
			aanmeldPogingDao.startTransaction();
			
			werknemer.gebruikersnaam = gebruikersnaam;
			werknemer.wachtwoord = wachtwoord;
			aanmeldPogingDao.insert(new AanmeldPoging(gefaald, werknemer));
			
			aanmeldPogingDao.commitTransaction();
			throw new IllegalArgumentException("Foutief wachtwoord");
			
			if (aanmeldPogingDao.geefAantalGefaaldeAanmeldPogingen(
					gebruikersnaam) >= 10) {
				
			werknemerDao.startTransaction();
			werknemerDao.blokkeerWerknemer(gebruikersnaam);
			werknemerDao.commitTransaction();
			throw new IllegalArgumentException(
					"Je account is geblokkeerd, je hebt te vaak het foute wachtwoord opgegeven.");
			}
			}
			
		}
	
		
	}
}

