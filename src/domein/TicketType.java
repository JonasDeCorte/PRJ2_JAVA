package domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketTypeId;
	private String naam;
	private String omschrijving;
		
	public TicketType(int ticketTypeId, String naam, String omschrijving) {
		setTicketTypeId(ticketTypeId);
		setNaam(naam);
		setOmschrijving(omschrijving);
	}
	
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	
	private void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	
	public String getNaam() {
		return naam;
	}
	
	private void setNaam(String naam) {
		this.naam = naam;
	}
	
	public String getOmschrijving() {
		return omschrijving;
	}
	
	private void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}	
}
