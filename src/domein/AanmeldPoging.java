package domein;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class AanmeldPoging{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aanmeldPogingId;
	private LocalDateTime tijdstipPoging;
	private boolean isGelukt;	
	private String gebruikersNaam;
	
	public AanmeldPoging() {
		this.tijdstipPoging = LocalDateTime.now();
	}

	public AanmeldPoging(boolean isGelukt, String gebruikersNaam) {
		this.tijdstipPoging = LocalDateTime.now();
		setGelukt(isGelukt);
		setGebruikersNaam(gebruikersNaam);
	}

	public LocalDateTime getTijdstipPoging() {
		return tijdstipPoging;
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
		this.gebruikersNaam = gebruikersNaam;
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
