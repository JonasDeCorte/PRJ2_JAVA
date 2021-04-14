package domein;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "AanmeldPoging.geefLaatste5PogingenVoorInloggen", query = "SELECT a FROM AanmeldPoging a WHERE a.gebruikersNaam = :gebruikersnaam ORDER BY a.tijdstipPoging DESC") })
public class AanmeldPoging{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aanmeldPogingId;
	private LocalDateTime tijdstipPoging;
	private boolean isGelukt;	
	private String gebruikersNaam;
	
	protected AanmeldPoging() {
	}

	public AanmeldPoging(boolean isGelukt, String gebruikersNaam) {
		setTijdstipPoging(LocalDateTime.now());
		setGelukt(isGelukt);
		setGebruikersNaam(gebruikersNaam);
	}

	public LocalDateTime getTijdstipPoging() {
		return tijdstipPoging;
	}


	public void setTijdstipPoging(LocalDateTime tijdstipPoging) {
		if (tijdstipPoging != null) {
			this.tijdstipPoging = tijdstipPoging;
		} else {
			throw new IllegalArgumentException("tijdstipPoging kan niet null zijn.");
		}
	}

	public boolean isGelukt() {
		return isGelukt;
	}

	private void setGelukt(boolean isGelukt) {
		this.isGelukt = isGelukt;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public void setGebruikersNaam(String gebruikersNaam) {
		if (gebruikersNaam != null && !gebruikersNaam.isBlank() && !gebruikersNaam.isEmpty()) {
			this.gebruikersNaam = gebruikersNaam;
		} else {
			throw new IllegalArgumentException("GebruikersNaam kan niet leeg zijn.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tijdstipPoging == null) ? 0 : tijdstipPoging.hashCode());
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
		AanmeldPoging other = (AanmeldPoging) obj;
		if (tijdstipPoging == null) {
			if (other.tijdstipPoging != null)
				return false;
		} else if (!tijdstipPoging.equals(other.tijdstipPoging))
			return false;
		return true;
	}	
}
