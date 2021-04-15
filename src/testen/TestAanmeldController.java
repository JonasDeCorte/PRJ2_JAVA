package testen;

import domein.Adres;
import domein.Werknemer;
import domein.controllers.AanmeldController;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestAanmeldController {
	@Mock
	private AanmeldPogingDao aanmeldpogingDao;
	
	@Mock
	private WerknemerDao werknemerDao;
	
	@InjectMocks
	private AanmeldController aanmeldController;
	
	private Werknemer werknemer;
	
	
	// trainen aanmelden
	
	private void aanmeldenMetFoutWachtwoordTrainen(String wachtwoord, String gebruikersnaam, GEBRUIKERSTATUS status, int aantalGefaaldeAanmeldPogingen) {
		
		Mockito.when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenReturn(status);
		
		Mockito.lenient().when(werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord)).thenThrow(NoResultException.class);
		
		Mockito.lenient().when(aanmeldpogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam)).thenReturn(aantalGefaaldeAanmeldPogingen);

	}
	
	
	private void aanmeldenMetCorrectWachtwoordTrainen(String wachtwoord, String gebruikersnaam, GEBRUIKERSTATUS status, int aantalGefaaldeAanmeldPogingen) 
	{
		werknemer = new Werknemer("EddyWally", "passwoord1" , "eddy" , "wally" , "eddy.wally@hotmail.com", 4, Arrays.asList("+32472485968", "+32472457912"), WERKNEMERROL.ADMINISTRATOR, new Adres("België«", "Loppem" ,"8200" , "Guido Gezellelaan", 22, ""));

		Mockito.when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenReturn(status);
		
		Mockito.lenient().when(werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord)).thenReturn(werknemer);	
		Mockito.lenient().when(aanmeldpogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam)).thenReturn(aantalGefaaldeAanmeldPogingen);
	}
	

	
	
	@Test
	public void afmelden_GebruikerNietAangemeld_Null() {
		// Act
		aanmeldController.afmelden();

		// Assert
		Assertions.assertNull(aanmeldController.getAangemeldeWerknemer());
	}
	
	@Test
	public void aanmelden_GebruikerNietInDatabank_ThrowsException() {
		
		String wachtwoord = "Wachtwoord1";
		
		String gebruikersnaam = "Gebruiker1";
		
		Mockito.lenient().when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenThrow(NoResultException.class);

		
		Assertions.assertThrows(EntityNotFoundException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		// Assert
		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}