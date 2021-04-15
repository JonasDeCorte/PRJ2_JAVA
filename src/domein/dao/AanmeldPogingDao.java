package domein.dao;

import domein.AanmeldPoging;

public interface AanmeldPogingDao extends GenericDao<AanmeldPoging> {
	
	int geefAantalGefaaldeAanmeldPogingen(String gebruikersnaam);
	
}