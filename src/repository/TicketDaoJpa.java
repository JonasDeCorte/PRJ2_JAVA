package repository;

import java.util.List;

import domein.Werknemer;
import domein.Ticket;
import domein.dao.TicketDao;

public class TicketDaoJpa extends GenericDaoJpa<Ticket> implements TicketDao {

	public TicketDaoJpa() {
		super(Ticket.class);
	}
	@Override
	public List<Ticket> geefTicketsVanActieveTechnieker(Werknemer werknemer) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Ticket.geefTicketsVanActieveTechnieker", Ticket.class)
				.setParameter("personeelsnummer", werknemer).getResultList();
	}

	@Override
	public List<Ticket> geefGewijzigdeTicketsVanActieveTechnieker(Werknemer aangemeldeWerknemer) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Ticket.geefGewijzigdeTicketsVanActieveTechnieker", Ticket.class)
				.setParameter("werknemer", aangemeldeWerknemer).getResultList();
	}

	@Override
	public List<Ticket> geefAlleGewijzigdeTickets() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Ticket.geefAlleGewijzigdeTickets", Ticket.class).getResultList();
	}
	

}
