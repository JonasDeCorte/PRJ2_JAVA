package repository;

import domein.Werknemer;
import domein.dao.WerknemerDao;
import domein.enumerations.GEBRUIKERSTATUS;



public class WerknemerDaoJpa extends GenericDaoJpa<Werknemer> implements WerknemerDao {

	public WerknemerDaoJpa() {
		super(Werknemer.class);
	}

	@Override
	public Werknemer geefWerknemer(String gebruikersnaam, String wachtwoord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GEBRUIKERSTATUS bestaatWerkemer(String gebruikersnaam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void blokkeerWerknemer(String gebruikersnaam) {
		// TODO Auto-generated method stub
		
	}

	
}
