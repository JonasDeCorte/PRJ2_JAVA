package domein.dao;

import domein.Werknemer;
import domein.enumerations.GEBRUIKERSTATUS;

import repository.GenericDao;

public interface WerknemerDao extends GenericDao<Werknemer>  {

	Werknemer geefWerknemer(String gebruikersnaam, String wachtwoord);
	
	GEBRUIKERSTATUS geefGebruikerStatus(String gebruikersnaam);
	
	void blokkeerWerknemer(String gebruikersnaam);
	
	boolean bestaatGebruikersnaam(String gebruikersnaam);
}
