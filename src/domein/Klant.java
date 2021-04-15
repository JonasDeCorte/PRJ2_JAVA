package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Klant extends Gebruiker implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int klantnummer;
	@ManyToOne
	private Bedrijf bedrijf;
	@OneToMany(mappedBy = "klant", cascade = CascadeType.PERSIST)
	private List<Contract> contracten;
			
	public Klant() {
		super();
	}

	public Klant(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, int klantnummer,
			Bedrijf bedrijf) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
		setKlantnummer(klantnummer);
		setBedrijf(bedrijf);
	}
			
	public int getKlantnummer() {
		return klantnummer;
	}

	public Bedrijf getBedrijf() {
		return bedrijf;
	}

	private void setBedrijf(Bedrijf bedrijf) {
		this.bedrijf = bedrijf;
	}

	public void setKlantnummer(int klantnummer) {
		this.klantnummer = klantnummer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + klantnummer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klant other = (Klant) obj;
		if (klantnummer != other.klantnummer)
			return false;
		return true;
	}
}
