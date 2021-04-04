package domein;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import domein.enumerations.TICKETSTATUS;
@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketnummer;
	private String titel;
	private LocalDateTime datumAangemaakt;
	private LocalDateTime datumAfgesloten;
	private String omschrijving;
	private String opmerkingen;
	@OneToOne(mappedBy = "ticket")
	private Bijlage oplossing;
	@OneToMany(mappedBy = "ticket")
	private List<Bijlage> bijlages;	
	@ManyToOne
	private Contract contract;
	@ManyToOne
	private TicketType ticketType;
	@OneToOne
	private Rapport rapport;
	private TICKETSTATUS ticketStatus;
	
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

	public TICKETSTATUS getTicketStatus() {
		return ticketStatus;
	}

	private void setTicketStatus(TICKETSTATUS ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Rapport getRapport() {
		return rapport;
	}

	private void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}	
}
