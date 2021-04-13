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
	public GEBRUIKERSTATUS geefGebruikerStatus(String gebruikersnaam) {
		return em.createNamedQuery("Werknemer.geefGebruikerStatus", GEBRUIKERSTATUS.class)
				.setParameter("gebruikersnaam", gebruikersnaam).getSingleResult();
	}

	@Override
	public void blokkeerWerknemer(String gebruikersnaam) {
		try {
			em.createNamedQuery("Werknemer.updateStatusGeblokkeerdeGebruiker", Werknemer.class).setParameter("gebruikersnaam", gebruikersnaam)
				.setParameter("gebruikerStatus", GEBRUIKERSTATUS.GEBLOKKEERD).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean bestaatGebruikersnaam(String gebruikersnaam) {
		return em.createNamedQuery("Werknemer.bestaatGebruikersnaam", Long.class)
				.setParameter("gebruikersnaam", gebruikersnaam).getSingleResult() == 1;
	}	
}