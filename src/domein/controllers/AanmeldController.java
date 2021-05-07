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
import resourcebundle.Taal;

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

	public String aanmelden(String gebruikersnaam, String wachtwoord) {
		Werknemer werknemer;
		GEBRUIKERSTATUS gebruikerStatus;
		try {// gebruikerstatus ophalen + controle
			gebruikerStatus = werknemerDao.geefGebruikerStatus(gebruikersnaam);
		} catch (Exception e) {
			return Taal.geefTekst("foutieveInloggegevens");
		}
		if (gebruikerStatus == GEBRUIKERSTATUS.NIET_ACTIEF) {
			return Taal.geefTekst("inactiefAccount");
		}
		if (gebruikerStatus == GEBRUIKERSTATUS.GEBLOKKEERD) {
			return Taal.geefTekst("geblokkeerdAccount");
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
				return Taal.geefTekst("geblokkeerdAccount");
			}
			return Taal.geefTekst("foutieveInloggegevens");
		}
		aanmeldPogingDao.startTransaction();
		aanmeldPogingDao.insert(new AanmeldPoging(true, gebruikersnaam));
		aanmeldPogingDao.commitTransaction();
		AanmeldController.setAangemeldeWerknemer(werknemer);
		return Taal.geefTekst("succesvolAangemeld");
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
