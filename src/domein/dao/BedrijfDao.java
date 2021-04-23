package domein.dao;

import domein.Bedrijf;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
	Bedrijf geefBedrijf(String bedrijfsNaam);
	
	boolean bestaatBedrijf(String bedrijfsNaam);
}
