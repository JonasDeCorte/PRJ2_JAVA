package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
@Entity
@NamedQueries({
	@NamedQuery(name = "Rapport.geefRapportenVanGeselecteerdTicket", query = "SELECT t FROM Rapport t WHERE t.ticket = :ticketnummer")
})
public class Rapport implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rapportNummer;
	private String rapportNaam;
	private String beschrijving;
	private String oplossing;
	@OneToOne
	private Ticket ticket;
		
	public Rapport() {

	}

	public Rapport(int rapportNummer, String rapportNaam, String beschrijving, String oplossing, Ticket ticket) {
		setRapportNummer(rapportNummer);
		setRapportNaam(rapportNaam);
		setBeschrijving(beschrijving);
		setOplossing(oplossing);
		setTicket(ticket);
	}

	public int getRapportNummer() {
		return rapportNummer;
	}

	public void setRapportNummer(int rapportNummer) {
		if(rapportNummer > 0)
		this.rapportNummer = rapportNummer;
		else {
			throw new IllegalArgumentException("rapportNummer moet groter zijn dan 0.");
		}
	}

	public String getRapportNaam() {
		return rapportNaam;
	}

	public void setRapportNaam(String rapportNaam) {
		if (rapportNaam != null && !rapportNaam.isBlank() && !rapportNaam.isEmpty()) {
			this.rapportNaam = rapportNaam;
		} else {
			throw new IllegalArgumentException("rapportNaam mag niet leeg zijn.");
		}
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		if (beschrijving != null && !beschrijving.isBlank() && !beschrijving.isEmpty()) {
			this.beschrijving = beschrijving;
		} else {
			throw new IllegalArgumentException("beschrijving mag niet leeg zijn.");
		}
	}

	public String getOplossing() {
		return oplossing;
	}

	public void setOplossing(String oplossing) {
		if (oplossing != null && !oplossing.isBlank() && !oplossing.isEmpty()) {
			this.oplossing = oplossing;
		} else {
			throw new IllegalArgumentException("oplossing mag niet leeg zijn.");
		}
	
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		if(ticket != null)
		this.ticket = ticket;
		else {
			throw new IllegalArgumentException("ticket mag niet leeg zijn.");
		}
	}
}
