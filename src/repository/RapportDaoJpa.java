package repository;


import java.util.List;

import domein.Rapport;
import domein.Ticket;
import domein.dao.RapportDao;

public class RapportDaoJpa extends GenericDaoJpa<Rapport> implements RapportDao {

	public RapportDaoJpa() {
		super(Rapport.class);
		}
	@Override
	public List<Rapport> geefRapportenVanGeselecteerdTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Rapport.geefRapportenVanGeselecteerdTicket", Rapport.class)
				.setParameter("ticketnummer", ticket).getResultList();
	}


}
