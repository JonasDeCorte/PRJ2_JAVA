package domein;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Klant extends Gebruiker {
	
	private Bedrijf bedrijfsgegevens;
	@OneToMany(mappedBy = "klant", cascade = CascadeType.PERSIST)
	private List<Contract> contracten;
	//private List<Ticket> tickets;

	public Klant(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, 
			Bedrijf bedrijfsgegevens) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
		setBedrijfsgegevens(bedrijfsgegevens);
	}
	
	
	public Bedrijf getBedrijfsgegevens() {
		return bedrijfsgegevens;
	}

	private void setBedrijfsgegevens(Bedrijf bedrijfsgegevens) {
		this.bedrijfsgegevens = bedrijfsgegevens;
	}
}
