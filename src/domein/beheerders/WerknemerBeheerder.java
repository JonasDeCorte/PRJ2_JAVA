package domein.beheerders;

import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import repository.AanmeldPogingDaoJpa;
import repository.WerknemerDaoJpa;

public class WerknemerBeheerder {
	private WerknemerDao werknemerDao;
	private AanmeldPogingDao aanmeldPogingDao;
	
	public WerknemerBeheerder() {
		this(new WerknemerDaoJpa(), new AanmeldPogingDaoJpa());
	}

	public WerknemerBeheerder(WerknemerDao werknemerDao, AanmeldPogingDao aanmeldPogingDao) {
		this.werknemerDao = werknemerDao;
		this.aanmeldPogingDao = aanmeldPogingDao;
	}

	public Werknemer haalWerknemerOp(int personeelsnummer) {
		throw new IllegalArgumentException();
	}
	
	public void voegWerknemerToe(Werknemer werknemer) {
		werknemerDao.startTransaction();
		werknemerDao.insert(werknemer);
		werknemerDao.commitTransaction();
	}
	
	public void wijzigWerknemer() {
		
	}
	
	public void verwijderWerknemer() {
		
	}
}
