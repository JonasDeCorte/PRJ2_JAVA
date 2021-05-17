package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import domein.enumerations.TICKETSTATUS;
@Entity
@NamedQueries({
	@NamedQuery(name = "Ticket.getTicketsInBehandelingVanActiefContractType", query = "SELECT t FROM Ticket t WHERE t.ticketStatus = :ticketStatus"),
	@NamedQuery(name = "Ticket.geefTicketsVanActieveTechnieker", query = "SELECT t FROM Ticket t WHERE t.toegekendeTechnieker = :personeelsnummer"),
	@NamedQuery(name = "Ticket.geefGewijzigdeTicketsVanActieveTechnieker", query = "SELECT t FROM Ticket t WHERE t.toegekendeTechnieker = :werknemer AND t.wijzigingAangebracht = true "),
	@NamedQuery(name = "Ticket.geefAlleGewijzigdeTickets", query = "SELECT t FROM Ticket t WHERE t.wijzigingAangebracht = true "), 
	})
	
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketnummer;
	private String titel;
	private LocalDate datumAangemaakt;
	private LocalDate datumAfgesloten;
	private String omschrijving;
	private String opmerkingen;
	@ManyToOne
	private Contract contract;
	@ManyToOne
	private TicketType ticketType;
	@OneToOne
	private Rapport rapport;
	private TICKETSTATUS ticketStatus;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Werknemer toegekendeTechnieker;
	private boolean wijzigingAangebracht;
	
	public Ticket() {
		datumAangemaakt = LocalDate.now();
		ticketStatus = ticketStatus.AANGEMAAKT;
	}

	public Ticket(int ticketnummer, String titel, String omschrijving,
			String opmerkingen, Contract contract, TicketType ticketType) {
		setTicketnummer(ticketnummer);
		setTitel(titel);
		datumAangemaakt = LocalDate.now();
		setOmschrijving(omschrijving);
		setOpmerkingen(opmerkingen);
		setContract(contract);
		setTicketType(ticketType);
		setTicketStatus(ticketStatus.AANGEMAAKT);
	}
	
	public int getTicketnummer() {
		return ticketnummer;
	}

	public void setTicketnummer(int ticketnummer) {
		this.ticketnummer = ticketnummer;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		if (titel != null && !titel.isBlank() && !titel.isEmpty()) {
			this.titel = titel;
		} else {
			throw new IllegalArgumentException("Titel mag niet leeg zijn.");
		}
	}

	public LocalDate getDatumAangemaakt() {
		return datumAangemaakt;
	}
	
	public LocalDate getDatumAfgesloten() {
		return datumAfgesloten;
	}

	public void setDatumAfgesloten(LocalDate datumAfgesloten) {
		this.datumAfgesloten = datumAfgesloten;
	}

	public void setDatumAangemaakt(LocalDate datumAangemaakt) {
		this.datumAangemaakt = datumAangemaakt;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		if (omschrijving != null && !omschrijving.isBlank() && !omschrijving.isEmpty()) {
			this.omschrijving = omschrijving;
		} else {
			throw new IllegalArgumentException("omschrijving mag niet leeg zijn.");
		}
	}

	public String getOpmerkingen() {
		return opmerkingen;
	}

	public void setOpmerkingen(String opmerkingen) {
		if (opmerkingen != null && !opmerkingen.isBlank() && !opmerkingen.isEmpty()) {
			this.opmerkingen = opmerkingen;
		} else {
			throw new IllegalArgumentException("opmerkingen mag niet leeg zijn.");
		}
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		if(contract != null) {
			this.contract = contract;
		}else {
			throw new IllegalArgumentException("Er moet een contract worden opgegeven.");
		}
		
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		if (ticketType != null) {
			this.ticketType = ticketType;
		} else {
			throw new IllegalArgumentException("ticketType mag niet leeg zijn.");
		}
	}

	public TICKETSTATUS getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TICKETSTATUS ticketStatus) {
		if(ticketStatus != null) {
		this.ticketStatus = ticketStatus;
	}else {
		throw new IllegalArgumentException("Er moet een status worden opgegeven.");
	}
	}

	public Rapport getRapport() {
		return rapport;
	}

	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}
	

	public Werknemer getToegekendeTechnieker() {
		return toegekendeTechnieker;
	}

	public void setToegekendeTechnieker(Werknemer toegekendeTechnieker) {
		this.toegekendeTechnieker = toegekendeTechnieker;
		
	}

	public boolean isWijzigingAangebracht() {
		return wijzigingAangebracht;
	}

	public void setWijzigingAangebracht(boolean wijzigingAangebracht) {
		this.wijzigingAangebracht = wijzigingAangebracht;
	}

	@Override
	public String toString() {
		return titel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}	
}
