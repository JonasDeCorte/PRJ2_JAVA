package domein;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import domein.enumerations.CONTRACTSTATUS;
import domein.enumerations.TICKETSTATUS;

public class Statistiek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statistiekNummer;
	private String titel;
	public Statistiek(String titel) {
		// TODO Auto-generated constructor stub
		setTitel(titel);
	}

	
public int getStatistiekNummer() {
		return statistiekNummer;
	}

	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


public List<Ticket> verwerkDataTicket(ChronoLocalDate startDatum, ChronoLocalDate eindDatum,List<Ticket> data) {
	List<Predicate<Ticket>> predicaten = new ArrayList<>();

	if (startDatum != null) {
		predicaten.add(ticket -> ticket.getDatumAangemaakt().isAfter(startDatum));
	}
	if (eindDatum != null) {
		predicaten.add(ticket -> ticket.getTicketStatus().equals(TICKETSTATUS.AFGEHANDELD) && ticket.getDatumAfgesloten().isBefore(eindDatum));
	}

	Predicate<Ticket> filter = predicaten.stream().reduce(Predicate::and).orElse(x -> true);
	return data.stream().filter(filter).collect(Collectors.toList());
}
public List<Contract> verwerkDataContract(ChronoLocalDate startDatum, ChronoLocalDate eindDatum,List<Contract> data) {
	List<Predicate<Contract>> predicaten = new ArrayList<>();

	if (startDatum != null) {
		predicaten.add(Contract -> Contract.getStartdatum().isAfter(startDatum));
	}
	if (eindDatum != null) {
		predicaten.add(Contract -> Contract.getContractstatus().equals(CONTRACTSTATUS.BEËINDIGD) && Contract.getEinddatum().isBefore(eindDatum));
	}

	Predicate<Contract> filter = predicaten.stream().reduce(Predicate::and).orElse(x -> true);
	return data.stream().filter(filter).collect(Collectors.toList());
}
}