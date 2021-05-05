package domein.controllers;

import java.util.List;

import domein.TicketType;
import domein.beheerders.TicketTypeBeheerder;

public class TicketTypeController {
	private TicketTypeBeheerder ticketTypeBeheerder;
	
	public TicketTypeController() {
		this.ticketTypeBeheerder = new TicketTypeBeheerder();
		// TODO Auto-generated constructor stub
	}

	public List<TicketType> haalTicketTypesOp() {
		return ticketTypeBeheerder.haalTicketTypesOp();
	}
}