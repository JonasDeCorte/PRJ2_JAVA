package domein.dao;

import java.util.List;

import domein.Werknemer;
import domein.Ticket;

public interface TicketDao extends GenericDao<Ticket> {
	//TO DO
	List<Ticket> geefTicketsVanActieveTechnieker(Werknemer werknemer);

	List<Ticket> geefGewijzigdeTicketsVanActieveTechnieker(Werknemer aangemeldeWerknemer);

	List<Ticket> geefAlleGewijzigdeTickets();

	
}
