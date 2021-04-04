package domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Rapport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rapportNummer;
	private String rapportNaam;
	private String beschrijving;
	private String oplossing;
	@OneToOne
	private Ticket ticket;
	
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

	private void setRapportNummer(int rapportNummer) {
		this.rapportNummer = rapportNummer;
	}

	public String getRapportNaam() {
		return rapportNaam;
	}

	private void setRapportNaam(String rapportNaam) {
		this.rapportNaam = rapportNaam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	private void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String getOplossing() {
		return oplossing;
	}

	private void setOplossing(String oplossing) {
		this.oplossing = oplossing;
	}

	public Ticket getTicket() {
		return ticket;
	}

	private void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
