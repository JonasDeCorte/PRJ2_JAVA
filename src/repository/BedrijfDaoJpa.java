package repository;

import domein.Bedrijf;
import domein.dao.BedrijfDao;

public class BedrijfDaoJpa extends GenericDaoJpa<Bedrijf> implements BedrijfDao {

	public BedrijfDaoJpa() {
		super(Bedrijf.class);
	} 
	@Override
	public Bedrijf geefBedrijf(String bedrijfsNaam) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Bedrijf.geefBedrijf", Bedrijf.class).setParameter("bedrijfsnaam", bedrijfsNaam).getSingleResult();
	}

	@Override
	public boolean bestaatBedrijf(String bedrijfsNaam) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Bedrijf.bestaatBedrijf", long.class).setParameter("bedrijfsnaam", bedrijfsNaam).getSingleResult()== 1;
	}
}
