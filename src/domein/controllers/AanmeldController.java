package domein.controllers;

import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.AanmeldPoging;
import domein.enumerations.GEBRUIKERSTATUS;
import repository.AanmeldpogingDaoJpa;
import repository.WerknemerDaoJpa;


public class AanmeldController {
	int maximumPogingen = 10;
	private Werknemer aangemeldeWerknemer;
	private AanmeldPogingDao aanmeldPogingDao;
	private WerknemerDao werknemerDao;
	
	private AanmeldController(WerknemerDao werknemerDao, AanmeldPogingDao aanmeldpogingDao) {
		this.werknemerDao = werknemerDao;
		this.aanmeldPogingDao = aanmeldpogingDao;
	}

	public AanmeldController() {
		this(new WerknemerDaoJpa(), new AanmeldpogingDaoJpa());
	}


	public void aanmelden(String gebruikersnaam, String wachtwoord) {
		GEBRUIKERSTATUS gebruikerStatus ;
		Werknemer werknemer = null;
		boolean gefaald = false;
		boolean gelukt = true;
		try {
			gebruikerStatus = werknemerDao.bestaatWerkemer(gebruikersnaam);
		}catch (Exception e) {
				throw new IllegalArgumentException("Gebruikersnaam bestaat niet.");
		}

		if (gebruikerStatus == GEBRUIKERSTATUS.NIET_ACTIEF) {
			throw new IllegalArgumentException("Aanmelden niet mogelijk, uw account is niet actief.");
		}

		if (gebruikerStatus == GEBRUIKERSTATUS.GEBLOKKEERD) {
			throw new IllegalArgumentException("Aanmelden niet mogelijk, uw account is geblokkeerd.");
		}
	
		try {
			werknemer = werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord);
		}catch (Exception e) {
			
			aanmeldPogingDao.startTransaction();
			/* moet volgens mij niet aangezien je de gebruikersnaam gebruikt 
			werknemer.setGebruikersnaam(gebruikersnaam);
			werknemer.setWachtwoord(wachtwoord);
			*/
			aanmeldPogingDao.insert(new AanmeldPoging(gefaald, gebruikersnaam));
			
			aanmeldPogingDao.commitTransaction();
			
			if (aanmeldPogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam) >= 10) {						
			werknemerDao.startTransaction();
			werknemerDao.blokkeerWerknemer(gebruikersnaam);
			werknemerDao.commitTransaction();
			throw new IllegalArgumentException(
					"Je account is geblokkeerd, je hebt te vaak het foute wachtwoord opgegeven.");
			}
			throw new IllegalArgumentException("Foutief wachtwoord of gebruikersnaam");

			}		
		aanmeldPogingDao.startTransaction();
		aanmeldPogingDao.insert(new AanmeldPoging(gelukt, gebruikersnaam));
		aanmeldPogingDao.commitTransaction();
		this.aangemeldeWerknemer = werknemer;
		}	
	}


