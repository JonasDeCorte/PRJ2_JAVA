package domein.dao;
import domein.AanmeldPoging;
import repository.GenericDao;


public interface AanmeldPogingDao extends GenericDao<AanmeldPoging> {

	
	int geefAantalGefaaldeAanmeldPogingen(String gebruikersnaam);
}
