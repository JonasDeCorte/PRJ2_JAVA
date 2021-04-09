package repository;

import javax.persistence.NoResultException;

import domein.Werknemer;
import domein.dao.WerknemerDao;
import domein.enumerations.GEBRUIKERSTATUS;

public class WerknemerDaoJpa extends GenericDaoJpa<Werknemer> implements WerknemerDao {

	public WerknemerDaoJpa() {
		super(Werknemer.class);
	}

	@Override
	public Werknemer geefWerknemer(String gebruikersnaam, String wachtwoord) throws NoResultException {
		return em.createNamedQuery("Werknemer.geefWerknemer", Werknemer.class)
				.setParameter("gebruikersnaam", gebruikersnaam).setParameter("wachtwoord", wachtwoord)
				.getSingleResult();
	}
	@Override
	public GEBRUIKERSTATUS bestaatWerkemer(String gebruikersnaam) {
		return em.createNamedQuery("Werknemer.geefStatus", GEBRUIKERSTATUS.class)
				.setParameter("gebruikersnaam", gebruikersnaam).getSingleResult();
	}

	@Override
	public void blokkeerWerknemer(String gebruikersnaam) {
		// TODO Auto-generated method stub		
	}	
}