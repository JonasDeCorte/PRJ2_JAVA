package domein.dao;

import domein.Klant;

public interface KlantDao extends GenericDao<Klant> {
	public boolean bestaatKlant(String gebruikersnaam);
}