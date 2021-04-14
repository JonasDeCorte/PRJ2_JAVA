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
import javax.persistence.OneToMany;
@Entity
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
	
	/*
	@OneToMany(mappedBy = "Bedrijf", cascade = CascadeType.PERSIST)
	public List<Klant> klanten;
	*/
	
	public Bedrijf() {

	}
	
	public Bedrijf(String bedrijfsnaam, List<String> telefoonnummers, Adres adres) {
		setBedrijfsnaam(bedrijfsnaam);
		setTelefoonnummers(telefoonnummers);
		setAdres(adres);
	}

	public String getBedrijfsnaam() {
		return bedrijfsnaam;
	}
	
	private void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam = bedrijfsnaam;
	}
	
	public List<String> getTelefoonnummers() {
		return telefoonnummers;
	}
	
	private void setTelefoonnummers(List<String> telefoonnummers) {
		this.telefoonnummers = telefoonnummers;
	}
	/*
	public List<Klant> getKlanten() {
		return klanten;
	}
	
	private void setKlanten(List<Klant> klanten) {
		this.klanten = klanten;
	}
	*/

	public Adres getAdres() {
		return adres;
	}

	private void setAdres(Adres adres) {
		this.adres = adres;
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
