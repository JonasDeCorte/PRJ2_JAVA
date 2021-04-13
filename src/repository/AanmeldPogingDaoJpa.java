package repository;

import java.util.List;

import javax.persistence.NoResultException;
import domein.AanmeldPoging;
import domein.dao.AanmeldPogingDao;

public class AanmeldPogingDaoJpa extends GenericDaoJpa<AanmeldPoging> implements AanmeldPogingDao {
 final  int MAX_AANTAL_GEFAALDE_AANMELDPOGINGEN_VOOR_INLOGGEN = 5;
	public AanmeldPogingDaoJpa() {
		super(AanmeldPoging.class);
	}

	@Override
	public int geefAantalGefaaldeAanmeldPogingen(String gebruikersnaam) {
			int aantalGefaaldePogingenVoorInloggen = 0;
			try {
				List<AanmeldPoging> aanmeldpogingenGebruikerVoorInloggen = em
						.createNamedQuery("AanmeldPoging.geefLaatste5PogingenVoorInloggen", AanmeldPoging.class)
						.setParameter("gebruikersnaam", gebruikersnaam)
						.setMaxResults(MAX_AANTAL_GEFAALDE_AANMELDPOGINGEN_VOOR_INLOGGEN).getResultList();

				for (AanmeldPoging aanmeldpoging : aanmeldpogingenGebruikerVoorInloggen) {
					if (aanmeldpoging.isGelukt() == false) {
						aantalGefaaldePogingenVoorInloggen++;
					}
				}
				return aantalGefaaldePogingenVoorInloggen;
			} catch (NoResultException ex) {
				return 0;
			}
		}
	}