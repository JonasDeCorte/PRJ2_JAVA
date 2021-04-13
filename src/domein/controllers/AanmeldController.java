package domein.controllers;

import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.AanmeldPoging;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;
import repository.AanmeldPogingDaoJpa;
import repository.WerknemerDaoJpa;


public class AanmeldController {
	final int MAXIMUM_POGINGEN = 10;
	private Werknemer aangemeldeWerknemer;
	private AanmeldPogingDao aanmeldPogingDao;
	private WerknemerDao werknemerDao;
	
	private AanmeldController(WerknemerDao werknemerDao, AanmeldPogingDao aanmeldPogingDao) {
		this.werknemerDao = werknemerDao;
		this.aanmeldPogingDao = aanmeldPogingDao;
	}

	public AanmeldController() {
		this(new WerknemerDaoJpa(), new AanmeldPogingDaoJpa());
	}

	public void aanmelden(String gebruikersnaam, String wachtwoord) {
		Werknemer werknemer;
		GEBRUIKERSTATUS gebruikerStatus ;
		
		try {
			gebruikerStatus = werknemerDao.geefGebruikerStatus(gebruikersnaam);
		} catch (Exception e) {
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
			System.out.println(werknemer.getGebruikersnaam());
			System.out.println(werknemer.getGebruikerStatus());
		}catch (Exception e) {
			
			aanmeldPogingDao.startTransaction();
			aanmeldPogingDao.insert(new AanmeldPoging(false, gebruikersnaam));
			aanmeldPogingDao.commitTransaction();
			
			if (aanmeldPogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam) >= MAXIMUM_POGINGEN) {						
				werknemerDao.startTransaction();
				werknemerDao.blokkeerWerknemer(gebruikersnaam);
				werknemerDao.commitTransaction();
				throw new IllegalArgumentException(
					"Je account is geblokkeerd, je hebt te vaak het foute wachtwoord opgegeven.");
			}
			throw new IllegalArgumentException("Foutief wachtwoord of gebruikersnaam");

			}
		
		aanmeldPogingDao.startTransaction();
		aanmeldPogingDao.insert(new AanmeldPoging(true, gebruikersnaam));
		aanmeldPogingDao.commitTransaction();	
		this.setAangemeldeWerknemer(werknemer);
		
		
	}

	public Werknemer getAangemeldeWerknemer() {
		return aangemeldeWerknemer;
	}

	public void setAangemeldeWerknemer(Werknemer aangemeldeWerknemer) {
		this.aangemeldeWerknemer = aangemeldeWerknemer;
	}
}
