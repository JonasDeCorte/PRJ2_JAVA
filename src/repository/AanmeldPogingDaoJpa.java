package repository;

import domein.AanmeldPoging;
import domein.dao.AanmeldPogingDao;

public class AanmeldPogingDaoJpa extends GenericDaoJpa<AanmeldPoging> implements AanmeldPogingDao {

	public AanmeldPogingDaoJpa() {
		super(AanmeldPoging.class);
	}

	@Override
	public int geefAantalGefaaldeAanmeldPogingen(String gebruikersnaam) {
		// TODO Auto-generated method stub
		return 0;
	}
}