package domein;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {
	private int ticketnummer;
	private String titel;
	private LocalDateTime datumAangemaakt;
	private LocalDateTime datumAfgesloten;
	private String omschrijving;
	private String opmerkingen;
	
	private Bijlage oplossing;
	private List<Bijlage> bijlages;	
	private Contract contract;
	private TicketType ticketType;
	private Rapport rapport;
	private TicketStatus ticketStatus;
	
	public Ticket(int ticketnummer, String titel, String omschrijving,
			String opmerkingen, List<Bijlage> bijlages, Contract contract, TicketType ticketType) {
		setTicketnummer(ticketnummer);
		setTitel(titel);
		datumAangemaakt = LocalDateTime.now();
		setOmschrijving(omschrijving);
		setOpmerkingen(opmerkingen);
		setBijlages(bijlages);
		setContract(contract);
		setTicketType(ticketType);
		ticketStatus = ticketStatus.AANGEMAAKT;
	}

	public int getTicketnummer() {
		return ticketnummer;
	}

	private void setTicketnummer(int ticketnummer) {
		this.ticketnummer = ticketnummer;
	}

	public String getTitel() {
		return titel;
	}

	private void setTitel(String titel) {
		this.titel = titel;
	}

	public LocalDateTime getDatumAangemaakt() {
		return datumAangemaakt;
	}
	
	public LocalDateTime getDatumAfgesloten() {
		return datumAfgesloten;
	}

	private void setDatumAfgesloten(LocalDateTime datumAfgesloten) {
		this.datumAfgesloten = datumAfgesloten;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	private void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public String getOpmerkingen() {
		return opmerkingen;
	}

	private void setOpmerkingen(String opmerkingen) {
		this.opmerkingen = opmerkingen;
	}

	public Bijlage getOplossing() {
		return oplossing;
	}

	private void setOplossing(Bijlage oplossing) {
		this.oplossing = oplossing;
	}

	public List<Bijlage> getBijlages() {
		return bijlages;
	}

	private void setBijlages(List<Bijlage> bijlages) {
		this.bijlages = bijlages;
	}
	public Contract getContract() {
		return contract;
	}

	private void setContract(Contract contract) {
		this.contract = contract;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	private void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	private void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Rapport getRapport() {
		return rapport;
	}

	private void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}	
}
