package repository;

import domein.Klant;
import domein.dao.KlantDao;

public class KlantDaoJpa extends GenericDaoJpa<Klant> implements KlantDao {

	public KlantDaoJpa() {
		super(Klant.class);
	}

	@Override
	public boolean bestaatKlant(String gebruikersnaam) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Klant.bestaatKlant", Long.class).setParameter("gebruikersnaam", gebruikersnaam)
				.getSingleResult() == 1;
	}
	
	@Override
	public boolean bestaatKlantnummer(int klantnummer) {
		return em.createNamedQuery("Klant.bestaatKlantnummer", Long.class).setParameter("klantnummer", klantnummer)
				.getSingleResult() == 1;
	}

}
