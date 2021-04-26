package repository;

import domein.TicketType;
import domein.dao.TicketTypeDao;

public class TicketTypeDaoJpa extends GenericDaoJpa<TicketType> implements TicketTypeDao {

	public TicketTypeDaoJpa() {
		super(TicketType.class);
	}
	
}
