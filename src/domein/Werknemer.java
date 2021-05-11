package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import domein.enumerations.WERKNEMERROL;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
	@NamedQuery(name = "Werknemer.bestaatWerknemer", query = "SELECT COUNT(w) FROM Werknemer w WHERE w.gebruikersnaam = :gebruikersnaam"),
	@NamedQuery(name = "Werknemer.updateStatusGeblokkeerdeGebruiker", query = "UPDATE Werknemer SET gebruikerStatus = :gebruikerStatus WHERE gebruikersnaam = :gebruikersnaam"),
	@NamedQuery(name = "Werknemer.geefWerknemer", query = "SELECT w FROM Werknemer w WHERE w.gebruikersnaam = :gebruikersnaam AND w.wachtwoord = :wachtwoord"),
	@NamedQuery(name = "Werknemer.geefGebruikerStatus", query = "SELECT w.gebruikerStatus FROM Werknemer w WHERE w.gebruikersnaam = :gebruikersnaam"),
	@NamedQuery(name = "Werknemer.geefAlleTechniekers", query = "SELECT w FROM Werknemer w WHERE w.rol = :rol"),
	@NamedQuery(name = "Werknemer.bestaatWerknemerPersoneelsnummer", query = "SELECT COUNT(w) FROM Werknemer w WHERE w.personeelsnummer = :personeelsnummer")
})

public class Werknemer extends Gebruiker implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int personeelsnummer;
	@Embedded
	private Adres adres;
	
	@ElementCollection
	private List<String> telefoonnummers;
	
	private WERKNEMERROL rol;
	
	public Werknemer() {
		super();
	}

	public Werknemer(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, int personeelsnummer,  List<String> telefoonnummers, WERKNEMERROL werknemerRol , Adres adres) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
		setPersoneelsnummer(personeelsnummer);
		setTelefoonnummers(telefoonnummers);
		setRol(werknemerRol);
		setAdres(adres);
	}
	
	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		if (adres != null) {
			this.adres = adres;
		} else {
			throw new IllegalArgumentException("Er moet een adres worden opgegeven.");
		}
	}
	
	public List<String> getTelefoonnummers() {
		return telefoonnummers;
	}

	public void setTelefoonnummers(List<String> telefoonnummers) {
		this.telefoonnummers = telefoonnummers;
	}

	public WERKNEMERROL getRol() {
		return rol;
	}

	public void setRol(WERKNEMERROL rol) {
		if (rol == WERKNEMERROL.ADMINISTRATOR || rol == WERKNEMERROL.SUPPORTMANAGER || rol == WERKNEMERROL.TECHNIEKER) {	
			this.rol = rol;
		} else {
			throw new IllegalArgumentException("Ongeldige werknemerrol toegekend.");
		}
	}
	
	public void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}

	@Override
	public String toString() {
		return getVoornaam() + " " + getNaam();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + personeelsnummer;
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
		Werknemer other = (Werknemer) obj;
		if (personeelsnummer != other.personeelsnummer)
			return false;
		return true;
	}
}
