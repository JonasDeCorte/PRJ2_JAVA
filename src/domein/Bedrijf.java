package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
@Entity
@NamedQueries({
	@NamedQuery(name = "Bedrijf.bestaatBedrijf", query = "SELECT COUNT(b) FROM Bedrijf b WHERE b.bedrijfsnaam = :bedrijfsnaam"),
	@NamedQuery(name = "Bedrijf.geefBedrijf", query = "SELECT b FROM Bedrijf b WHERE b.bedrijfsnaam = :bedrijfsnaam"),
	@NamedQuery(name = "Bedrijf.bestaatBedrijfsnummer", query = "SELECT COUNT(b) FROM Bedrijf b WHERE b.bedrijfId = :bedrijfsnummer")
})
public class Bedrijf implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bedrijfId;
	private String bedrijfsnaam;
	@ElementCollection
	private List<String> telefoonnummers;
	@Embedded
	private Adres adres;
	
	@OneToMany(mappedBy = "Bedrijf", cascade = CascadeType.PERSIST)
	public List<Klant> klanten;

	
	public Bedrijf() {

	}
	
	public Bedrijf(String bedrijfsnaam, List<String> telefoonnummers, Adres adres) {
		setBedrijfsnaam(bedrijfsnaam);
		setTelefoonnummers(telefoonnummers);
		setAdres(adres);
	}

	public int getBedrijfId() {
		return bedrijfId;
	}

	public void setBedrijfId(int bedrijfId) {
		if(bedrijfId > 0)
		this.bedrijfId = bedrijfId;
		else {
			throw new IllegalArgumentException("bedrijfId moet groter zijn dan 0.");
		}
	}

	public String getBedrijfsnaam() {
		return bedrijfsnaam;
	}
	
	public void setBedrijfsnaam(String bedrijfsnaam) {
		if (bedrijfsnaam != null && !bedrijfsnaam.isBlank() && !bedrijfsnaam.isEmpty()) {
			this.bedrijfsnaam = bedrijfsnaam;
		} else {
			throw new IllegalArgumentException("bedrijfsnaam mag niet leeg zijn.");
		}
		this.bedrijfsnaam = bedrijfsnaam;
	}
	
	public List<String> getTelefoonnummers() {
		return telefoonnummers;
	}
	
	public void setTelefoonnummers(List<String> telefoonnummers) {
		this.telefoonnummers = telefoonnummers;
	}

	public List<Klant> getKlanten() {
		return klanten;
	}
	
	public void setKlanten(List<Klant> klanten) {
		this.klanten = klanten;
	}


	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		if(adres != null) {
			this.adres = adres;
		}else {
			throw new IllegalArgumentException("Er moet een adres worden opgegeven.");
		}
		
	}

	@Override
	public String toString() {
		return bedrijfsnaam;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bedrijfsnaam == null) ? 0 : bedrijfsnaam.hashCode());
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
		Bedrijf other = (Bedrijf) obj;
		if (bedrijfsnaam == null) {
			if (other.bedrijfsnaam != null)
				return false;
		} else if (!bedrijfsnaam.equals(other.bedrijfsnaam))
			return false;
		return true;
	}	
}
