package domein.beheerders;

import domein.Klant;
import domein.dao.KlantDao;

public class KlantBeheerder {
	private KlantDao klantDao;

	public KlantBeheerder(){
		
	}
		
	public KlantBeheerder(KlantDao klantDao) {
		this.klantDao = klantDao;
	}

	public Klant haalKlantOp(int klantnummer) {
		throw new IllegalArgumentException();
	}
	
	public void voegKlantToe(Klant klant) {
		klantDao.startTransaction();
		klantDao.insert(klant);
		klantDao.commitTransaction();
	}
	
	public void verwijderKlant() {
		
	}
	
	public void wijzigKlant() {
		
	}
}