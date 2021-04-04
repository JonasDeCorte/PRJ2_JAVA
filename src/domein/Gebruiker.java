package domein;

import java.time.LocalDateTime;
import java.util.List;

import domein.enumerations.GEBRUIKERSTATUS;

public abstract class Gebruiker {
	public String gebruikersnaam;
	private String wachtwoord;
	private String voornaam;
	private String naam;
	private String emailadres;
	private LocalDateTime registratiedatum;
	
	private List<AanmeldPoging> aanmeldPogingen;
	private List<Ticket> tickets;
	private GEBRUIKERSTATUS gebruikerStatus;
		
	public Gebruiker(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		setVoornaam(voornaam);
		setNaam(naam);
		setEmailadres(emailadres);
		registratiedatum = LocalDateTime.now();
		setGebruikerStatus(GEBRUIKERSTATUS.ACTIEF);
	}
	
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	private void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	private void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String getNaam() {
		return naam;
	}
	
	private void setNaam(String naam) {
		this.naam = naam;
	}
	
	public String getEmailadres() {
		return emailadres;
	}
	
	private void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}
	
	public LocalDateTime getRegistratiedatum() {
		return registratiedatum;
	}
		
	public GEBRUIKERSTATUS getGebruikerStatus() {
		return gebruikerStatus;
	}

	private void setGebruikerStatus(GEBRUIKERSTATUS gebruikerStatus) {
		this.gebruikerStatus = gebruikerStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	//TO DO Enkel gebruikers met status "actief" mogen getoond worden
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
}
