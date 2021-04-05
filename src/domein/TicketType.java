package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketTypeId;
	private String naam;
	private String omschrijving;
			
	public TicketType() {

	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		TicketType other = (TicketType) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}	
}
