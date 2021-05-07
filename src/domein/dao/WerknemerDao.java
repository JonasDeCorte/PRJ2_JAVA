package domein.dao;

import java.util.List;

import domein.Werknemer;
import domein.enumerations.GEBRUIKERSTATUS;

public interface WerknemerDao extends GenericDao<Werknemer>  {

	Werknemer geefWerknemer(String gebruikersnaam, String wachtwoord);
	
	GEBRUIKERSTATUS geefGebruikerStatus(String gebruikersnaam);
	
	void blokkeerWerknemer(String gebruikersnaam);
	
	boolean bestaatWerknemer(String gebruikersnaam);

	boolean bestaatPersoneelsnummer(int personeelsnummer);
	
	List<Werknemer> geefTechniekers();
}
