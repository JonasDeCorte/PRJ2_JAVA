package domein;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import domein.enumerations.WERKNEMERROL;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Werknemer extends Gebruiker implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int personeelsnummer;
	
	@Embedded
	private Adres adres;
	
	private String[] telefoonnummers;
	private WERKNEMERROL rol;
	
	public Werknemer(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
	}

	public Werknemer(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, String[] telefoonnummers, WERKNEMERROL werknemerRol , Adres adres) {
		super(gebruikersnaam, wachtwoord, voornaam, naam, emailadres);
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

	private void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public String[] getTelefoonnummers() {
		return telefoonnummers;
	}

	public void setTelefoonnummers(String[] telefoonnummers) {
		this.telefoonnummers = telefoonnummers;
	}

	public WERKNEMERROL getRol() {
		return rol;
	}

	public void setRol(WERKNEMERROL rol) {
		this.rol = rol;
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
