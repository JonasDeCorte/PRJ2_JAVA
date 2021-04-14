package repository;

import domein.Bedrijf;
import domein.dao.BedrijfDao;

public class BedrijfDaoJpa extends GenericDaoJpa<Bedrijf> implements BedrijfDao {

	public BedrijfDaoJpa() {
		super(Bedrijf.class);
	}
}
