package repository;

import domein.Klant;
import domein.dao.KlantDao;

public class KlantDaoJpa extends GenericDaoJpa<Klant> implements KlantDao {

	public KlantDaoJpa() {
		super(Klant.class);
	}

}
