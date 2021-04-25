package repository;

import domein.Ticket;
import domein.dao.TicketDao;

public class TicketDaoJpa extends GenericDaoJpa<Ticket> implements TicketDao {

	public TicketDaoJpa() {
		super(Ticket.class);
	}

}
