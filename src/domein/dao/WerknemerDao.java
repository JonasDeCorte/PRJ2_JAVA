package domein.dao;
import java.util.List;

import domein.Werknemer;
import domein.enumerations.GEBRUIKERSTATUS;

import repository.GenericDao;

public interface WerknemerDao extends GenericDao<Werknemer>  {

	Werknemer geefWerknemer(String gebruikersnaam, String wachtwoord);
	
	GEBRUIKERSTATUS bestaatWerkemer(String gebruikersnaam);
	
	void blokkeerWerknemer(String gebruikersnaam);

	
	
}
