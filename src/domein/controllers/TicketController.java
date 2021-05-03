  package domein.controllers;

import java.util.List;
import java.util.Set;

import domein.Ticket;
import domein.TicketType;
import domein.Werknemer;
import domein.beheerders.TicketBeheerder;
import domein.enumerations.TICKETSTATUS;
import javafx.collections.ObservableList;

public class TicketController {
	private TicketBeheerder ticketBeheerder;

	public TicketController(Werknemer aangemeldeWerknemer) {
		this.ticketBeheerder = new TicketBeheerder(aangemeldeWerknemer);
	}

	public void voegTicketToe(Ticket ticket) {
		ticketBeheerder.voegTicketToe(ticket);
	}

	public void pasTicketAan(Ticket ticket) {
		ticketBeheerder.pasTicketAan(ticket);
	}

	public void pasFilterAan( String titel,String datum,String contract, Set<TICKETSTATUS> status) {		
		ticketBeheerder.pasFilterAan( titel,datum,contract, status);
	}

	public ObservableList<Ticket> getTicketsLijst() {
		return ticketBeheerder.getTicketLijst();
	}

	public ObservableList<Ticket> getGewijzigdeTickets() {
		return ticketBeheerder.getGewijzigdeTickets();
	}
}