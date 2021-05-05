package domein.beheerders;

import java.util.List;

import domein.Klant;
import domein.TicketType;
import domein.dao.TicketTypeDao;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.TicketTypeDaoJpa;

public class TicketTypeBeheerder {
	private TicketTypeDao ticketTypeDao;
	
	public TicketTypeBeheerder(TicketTypeDao ticketTypeDao) {
		this.ticketTypeDao = ticketTypeDao;
	}
	public TicketTypeBeheerder() {
		this(new TicketTypeDaoJpa());
	}

	public List<TicketType> haalTicketTypesOp() {
		return ticketTypeDao.findAll();
	}
}
