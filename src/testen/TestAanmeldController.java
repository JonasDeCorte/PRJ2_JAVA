package testen;

import domein.AanmeldPoging;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
	
	
	@Captor
	private ArgumentCaptor<AanmeldPoging> aanmeldPogingCaptor;
	
	
	private Werknemer werknemer;
	
	
	// trainen aanmelden
	
	private void aanmeldenMetFoutWachtwoordTrainen(String wachtwoord, String gebruikersnaam, GEBRUIKERSTATUS status, int aantalGefaaldeAanmeldPogingen) {
		
		Mockito.when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenReturn(status);
		
		Mockito.lenient().when(werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord)).thenThrow(NoResultException.class);
		
		Mockito.lenient().when(aanmeldpogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam)).thenReturn(aantalGefaaldeAanmeldPogingen);

	}
	
	
	private void aanmeldenMetCorrectWachtwoordTrainen(String wachtwoord, String gebruikersnaam, GEBRUIKERSTATUS status, int aantalGefaaldeAanmeldPogingen) 
	{
		werknemer = new Werknemer("EddyWally", "passwoord1" , "eddy" , "wally" , "eddy.wally@hotmail.com", Arrays.asList("+32472485968", "+32472457912"), WERKNEMERROL.ADMINISTRATOR, new Adres("België«", "Loppem" ,"8200" , "Guido Gezellelaan", 22, ""));

		Mockito.when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenReturn(status);
		
		Mockito.lenient().when(werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord)).thenReturn(werknemer);	
		Mockito.lenient().when(aanmeldpogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam)).thenReturn(aantalGefaaldeAanmeldPogingen);
	}
	

	
	//afmelden testen
	@Test
	public void afmelden_GebruikerNietAangemeld_Null() {
		
		aanmeldController.afmelden();

		
		Assertions.assertNull(aanmeldController.getAangemeldeWerknemer());
	}
	
	
	//aanmelden testen
	
	//foutief aanmelden, testen als het dan exception smijt
	@Test
	public void aanmelden_GebruikerNietInDatabank_ThrowsException() {
		
		String wachtwoord = "Wachtwoord1";
		
		String gebruikersnaam = "Gebruiker1";
		
		Mockito.lenient().when(werknemerDao.geefGebruikerStatus(gebruikersnaam)).thenThrow(NoResultException.class);

		
		Assertions.assertThrows(EntityNotFoundException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		
		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
	}

	@Test
	public void aanmelden_FoutiefWachtwoord_ThrowsException() {
		
		String wachtwoord = "Wachtwoord1";
		
		String gebruikersnaam = "Gebruiker1";
		
		int aantalGefaaldeAanmeldPogingen = 0;
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;

		aanmeldenMetFoutWachtwoordTrainen(wachtwoord, gebruikersnaam, status, aantalGefaaldeAanmeldPogingen);

		
		Assertions.assertThrows(EntityNotFoundException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		
		Mockito.verify(werknemerDao).geefWerknemer(gebruikersnaam, wachtwoord);
		
		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
	}
	
	@ParameterizedTest
	
	@EnumSource(value = GEBRUIKERSTATUS.class, names = { "NIET_ACTIEF", "GEBLOKKEERD" })
	
	public void aanmelden_NietActieveWerknemer_ThrowsException(GebruikersStatus status) {
		
		String wachtwoord = "passwoord1";
		
		String gebruikersnaam = "EddyWally";
		
		int aantalGefaaldeAanmeldPogingen = 0;
		
		aanmeldenJuistWachtwoordTrainen(wachtwoord, gebruikersnaam,  status, aantalGefaaldeAanmeldPogingen);

		
		Assertions.assertThrows(IllegalArgumentException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		
		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
	}

	
	
	
	//correct aanmelden testen
	@Test
	public void aanmelden_ActieveWerknemer_MeldAan() {
		
		String wachtwoord = "passwoord1";
		
		String gebruikersnaam = "EddyWally";
		
		int aantalGefaaldeAanmeldPogingen = 0;
		
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;
		
		aanmeldenMetCorrectWachtwoordTrainen(wachtwoord, gebruikersnaam,  status, aantalGefaaldeAanmeldPogingen);

	
		
		aanmeldController.aanmelden(gebruikersnaam, wachtwoord);

		
		Assertions.assertEquals(werknemer, aanmeldController.getAangemeldeWerknemer());

		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
		
		Mockito.verify(werknemerDao).geefWerknemer(gebruikersnaam, wachtwoord);
	}
	
	

	// testen blokkeren gebruiker
	
	@ParameterizedTest
	@ValueSource(ints = { 5, 6 })
	public void aanmelden_FoutWachtwoordMaxPogingenOverschreden_BlokkerenGebruiker(int aantalGefaaldeAanmeldPogingen) {
		
		String wachtwoord = "Wachtwoord1";
		
		String gebruikersnaam = "Gebruiker1";
		
		
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;

		aanmeldenMetCorrectWachtwoordTrainen(wachtwoord, gebruikersnaam, status, aantalGefaaldeAanmeldPogingen);

		
		Assertions.assertThrows(IllegalArgumentException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		
		Mockito.verify(werknemerDao).blokkeerWerknemer(gebruikersnaam);

		Mockito.verify(werknemerDao).bestaatGebruikersnaam(gebruikersnaam);
		
		Mockito.verify(aanmeldpogingDao).geefAantalGefaaldeAanmeldPogingen(gebruikersnaam);
	}

	
	@ParameterizedTest
	
	@ValueSource(ints = { 0, 1, 5 })
	
	public void aanmelden_FoutWachtwoordMaxPogingenNietOverschreden_NietBlokkeren(int aantalGefaaldeAanmeldPogingen) {
		
		
		String wachtwoord = "Wachtwoord1";
		
		String gebruikersnaam = "Gebruiker1";
		
		
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;

		aanmeldenMetFoutWachtwoordTrainen(wachtwoord, gebruikersnaam,  status, aantalGefaaldeAanmeldPogingen);

		
		Assertions.assertThrows(EntityNotFoundException.class, () -> aanmeldController.aanmelden(gebruikersnaam, wachtwoord));

		Assertions.assertEquals(GEBRUIKERSTATUS.ACTIEF, werknemer.getGebruikerStatus());
		
		
		Mockito.verify(werknemerDao).blokkeerWerknemer(gebruikersnaam);

		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
		
		Mockito.verify(aanmeldpogingDao).geefAantalGefaaldeAanmeldPogingen(gebruikersnaam);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//aanmeldpogingaanmaken testen wanneer het aanmelden slaagt en faalt
	@Test()
	public void aanmelden_CorrectWachtwoord_MaaktGelukteAanmeldPogingAan() {
		
		String wachtwoord = "passwoord1";
		
		String gebruikersnaam = "EddyWally";
		
		int aantalGefaaldeAanmeldPogingen = 0;
		
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;

		aanmeldenMetCorrectWachtwoordTrainen(wachtwoord, gebruikersnaam,  status, aantalGefaaldeAanmeldPogingen);

		
		aanmeldController.aanmelden(gebruikersnaam, wachtwoord);

		
		
		Mockito.verify(aanmeldpogingDao);
		
		AanmeldPoging aanmeldpoging;
		
		
		Assertions.assertEquals(gebruikersnaam, aanmeldpoging.getGebruikersNaam());
		
		Assertions.assertTrue(aanmeldpoging.isGelukt());

		Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
		Mockito.verify(werknemerDao).geefWerknemer(gebruikersnaam, wachtwoord);
	}


	@Test()
	public void aanmelden_FoutWachtwoord_MaaktGefaaldeAanmeldPogingAan() {
		
		String wachtwoord = "passwoord1";
		
		String gebruikersnaam = "EddyWally";
		
		int aantalGefaaldeAanmeldPogingen = 0;
		
		GEBRUIKERSTATUS status = GEBRUIKERSTATUS.ACTIEF;

		aanmeldenMetFoutWachtwoordTrainen(wachtwoord, gebruikersnaam,  status, aantalGefaaldeAanmeldPogingen);

	
		
		Mockito.verify(aanmeldpogingDao).insert(aanmeldPogingCaptor.capture());
		
		AanmeldPoging capturedAanmeldpoging = aanmeldPogingCaptor.getValue();

		

		Assertions.assertFalse(capturedAanmeldpoging.isGelukt());

		
		
		Assertions.assertEquals(gebruikersnaam, capturedAanmeldpoging.getGebruikersNaam());
		

		//  Mockito.verify(werknemerDao).geefGebruikerStatus(gebruikersnaam);
		//  Mockito.verify(werknemerDao).geefWerknemer(gebruikersnaam, wachtwoord);
		
		Mockito.verify(aanmeldpogingDao).geefAantalGefaaldeAanmeldPogingen(gebruikersnaam);
	}
	
	
	
	
	
	

	
	
}