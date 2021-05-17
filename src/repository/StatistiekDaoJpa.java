package repository;

import java.util.List;


import domein.Statistiek;
import domein.dao.StatistiekDao;

public class StatistiekDaoJpa extends GenericDaoJpa<Statistiek> implements StatistiekDao {

	public StatistiekDaoJpa() {
		super(Statistiek.class);
	}

	@Override
	public void deleteAll() {
		em.createQuery("DELETE FROM Statistiek").executeUpdate();
	}

}
