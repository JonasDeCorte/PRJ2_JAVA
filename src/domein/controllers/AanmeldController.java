package domein.controllers;

import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;

import javax.persistence.NoResultException;

import domein.AanmeldPoging;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;
import repository.AanmeldPogingDaoJpa;
import repository.WerknemerDaoJpa;

public class AanmeldController {
	final int MAXIMUM_POGINGEN = 5;
	private static Werknemer aangemeldeWerknemer;
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
		GEBRUIKERSTATUS gebruikerStatus;
		try {// gebruikerstatus ophalen + controle
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
		try { // werknemer ophalen en retourneren indien foutief
			werknemer = werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord);

		} catch (NoResultException e) {
			aanmeldPogingDao.startTransaction();
			aanmeldPogingDao.insert(new AanmeldPoging(false, gebruikersnaam)); // nieuwe aanmeldpoging registreren
			aanmeldPogingDao.commitTransaction();
			// kijken hoeveel aanmeldpogingen er zijn en indien meer dan max aantal
			// toegelaten pogingen => blokkeren
			if (aanmeldPogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam) >= MAXIMUM_POGINGEN) {
				werknemerDao.startTransaction();
				werknemerDao.blokkeerWerknemer(gebruikersnaam);
				werknemerDao.commitTransaction();
				throw new IllegalArgumentException(
						"Je account is geblokkeerd, je hebt te vaak het foute wachtwoord opgegeven.");
			}
			throw new IllegalArgumentException("Foutief wachtwoord of gebruikersnaam test");
		}
		aanmeldPogingDao.startTransaction();
		aanmeldPogingDao.insert(new AanmeldPoging(true, gebruikersnaam));
		aanmeldPogingDao.commitTransaction();
		AanmeldController.setAangemeldeWerknemer(werknemer);
	}

	public static Werknemer getAangemeldeWerknemer() {
		return AanmeldController.aangemeldeWerknemer;
	}

	public static void setAangemeldeWerknemer(Werknemer aangemeldeWerknemer) {
		AanmeldController.aangemeldeWerknemer = aangemeldeWerknemer;
	}
	public void afmelden() {
		this.aangemeldeWerknemer = null;
	}

	
}
