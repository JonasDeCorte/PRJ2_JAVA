package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
	@NamedQuery(name = "Ticket.getTicketsInBehandelingVanActiefContractType", query = "SELECT t FROM Ticket t WHERE t.ticketStatus = :status"),
	})
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketnummer;
	private String titel;
	private LocalDateTime datumAangemaakt;
	private LocalDateTime datumAfgesloten;
	private String omschrijving;
	private String opmerkingen;
	/*@OneToOne(mappedBy = "ticket")*/
	private Bijlage oplossing;
	/*@OneToMany(mappedBy = "ticket")*/
	private List<Bijlage> bijlages;	
	@ManyToOne
	private Contract contract;
	@ManyToOne
	private TicketType ticketType;
	@OneToOne
	private Rapport rapport;
	private TICKETSTATUS ticketStatus;
	
	public Ticket() {
		datumAangemaakt = LocalDateTime.now();
		ticketStatus = ticketStatus.AANGEMAAKT;
	}

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
		setTicketStatus(ticketStatus.AANGEMAAKT);
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
		if (titel != null && !titel.isBlank() && !titel.isEmpty()) {
			this.titel = titel;
		} else {
			throw new IllegalArgumentException("Titel mag niet leeg zijn.");
		}
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
		if (omschrijving != null && !omschrijving.isBlank() && !omschrijving.isEmpty()) {
			this.omschrijving = omschrijving;
		} else {
			throw new IllegalArgumentException("omschrijving mag niet leeg zijn.");
		}
	}

	public String getOpmerkingen() {
		return opmerkingen;
	}

	private void setOpmerkingen(String opmerkingen) {
		if (opmerkingen != null && !opmerkingen.isBlank() && !opmerkingen.isEmpty()) {
			this.opmerkingen = opmerkingen;
		} else {
			throw new IllegalArgumentException("opmerkingen mag niet leeg zijn.");
		}
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
		if(contract != null) {
			this.contract = contract;
		}else {
			throw new IllegalArgumentException("Er moet een contract worden opgegeven.");
		}
		
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	private void setTicketType(TicketType ticketType) {
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

	private void setRapport(Rapport rapport) {
		this.rapport = rapport;
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
