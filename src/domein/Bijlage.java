package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Bijlage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bestandId;
	private String bestandType;
	private String omschrijving;
	@OneToOne
	private Ticket ticket;
	
	public Bijlage() {

	}

	public Bijlage(String bestandType, String omschrijving, Ticket ticket) {
		setBestandType(bestandType);
		setOmschrijving(omschrijving);
		setTicket(ticket);
	}
	
	public String getBestandType() {
		return bestandType;
	}
	
	private void setBestandType(String bestandType) {
		this.bestandType = bestandType;
	}
	
	public String getOmschrijving() {
		return omschrijving;
	}
	
	private void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	public Ticket getTicket() {
		return ticket;
	}
	
	private void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}	
	
	
}
