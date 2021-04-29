package domein.beheerders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import domein.Ticket;
import domein.TicketType;
import domein.Werknemer;
import domein.dao.TicketDao;
import domein.dao.WerknemerDao;
import domein.enumerations.TICKETSTATUS;
import domein.enumerations.WERKNEMERROL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.TicketDaoJpa;
import repository.WerknemerDaoJpa;

public class TicketBeheerder {
	
	private TicketDao ticketDao;
	private FilteredList<Ticket> filteredTicketLijst;
	private Werknemer aangemeldeWerknemer;
	private WerknemerDao werknemerDao;

	private TicketBeheerder(TicketDao ticketDao, WerknemerDao werknemerDao) {
		this.ticketDao = ticketDao;
		this.werknemerDao = werknemerDao;
	}

	public TicketBeheerder(Werknemer aangemeldeWerknemer) {
		this(new TicketDaoJpa(), new WerknemerDaoJpa());
		this.aangemeldeWerknemer = aangemeldeWerknemer;
	}

	public void voegTicketToe(Ticket ticket) {	
		if(ticketDao.exists(ticket.getTicketnummer()))
		{
			throw new IllegalArgumentException("Ticket bestaat al.");
		} else {
		ticketDao.startTransaction();
		List<Werknemer> AlleTechniekers = werknemerDao.geefTechniekers();
		if (!AlleTechniekers.isEmpty()) {
			// voorlopig random technieker toekenen aan het ticket tot we een geselecteerde technieker kunnen kiezen 
			ticket.setToegekendeTechnieker(AlleTechniekers.get(new Random().nextInt(AlleTechniekers.size())));
			// status op in behandeling zetten.
			ticket.setTicketStatus(TICKETSTATUS.IN_BEHANDELING);
		}
		ticketDao.insert(ticket);
		ticketDao.commitTransaction();
		}
		haalAlleTicketsOp();
	}

	public void pasTicketAan(Ticket ticket) {
		if(ticketDao.exists(ticket.getTicketnummer())) {
		ticketDao.startTransaction();
		ticketDao.update(ticket);
		ticketDao.commitTransaction();
		}
		else {
			throw new IllegalArgumentException("kies het correct ticket.");
		}
		haalAlleTicketsOp();
	}

	public void pasFilterAan(int ticketnummer, String titel, Set<TICKETSTATUS> status, List<TicketType> Tickettype) {
		List<Predicate<Ticket>> filters = new ArrayList<>();

		if (ticketnummer >= 0) {
			filters.add(ticket -> Integer.toString(ticket.getTicketnummer()).startsWith(Integer.toString(ticketnummer)));
		}

		if (titel != null && !titel.isBlank()) {
			filters.add(ticket -> ticket.getTitel().toLowerCase().contains(titel.toLowerCase()));
		}

		if (status != null && (status.size() > 0 || status.size() >= TICKETSTATUS.values().length)) {
			filters.add(ticket -> status.contains(ticket.getTicketStatus()));
		}
		if (Tickettype != null && (Tickettype.size() > 0)) {
			filters.add(ticket -> Tickettype.contains(ticket.getTicketType()));
		}
		Predicate<Ticket> filter = filters.stream().reduce(Predicate::and).orElse(x -> true);
		filteredTicketLijst.setPredicate(filter);
	}

	public ObservableList<Ticket> getTicketLijst() {
		if (filteredTicketLijst == null) {
			haalAlleTicketsOp();
		}
		return FXCollections.unmodifiableObservableList(filteredTicketLijst);
	}
	// hulp methode 
	private void haalAlleTicketsOp() {
		Predicate<Ticket> filter;
		if (filteredTicketLijst == null) {
			filter = e -> true;
		} else {
			filter =  (Predicate<Ticket>) filteredTicketLijst.getPredicate();
		}
		if (aangemeldeWerknemer.getRol().equals(WERKNEMERROL.TECHNIEKER)) {
			filteredTicketLijst = new FilteredList<>(
					FXCollections.observableArrayList(ticketDao.geefTicketsVanActieveTechnieker(aangemeldeWerknemer)), filter);
		} else {
			filteredTicketLijst = new FilteredList<>(FXCollections.observableArrayList(ticketDao.findAll()), filter);
		}
	}

	public ObservableList<Ticket> getGewijzigdeTickets() {
		if (aangemeldeWerknemer.getRol().equals(WERKNEMERROL.TECHNIEKER)) {
			return FXCollections.observableArrayList(ticketDao.geefGewijzigdeTicketsVanActieveTechnieker(aangemeldeWerknemer));
		} else {
			return FXCollections.observableArrayList(ticketDao.geefAlleGewijzigdeTickets());
		}

	}

	
}