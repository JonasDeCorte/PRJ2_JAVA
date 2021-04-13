package testen;

import domein.Adres;
import domein.Werknemer;
import domein.controllers.AanmeldController;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;

import java.util.Arrays;
import java.util.List;

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
	private AanmeldController controller;
	
	private Werknemer werknemer;
	
	
	
	private void aanmeldenJuistWachtwoordTrainen(String wachtwoord, String gebruikersnaam, GEBRUIKERSTATUS status, int aantalGefaaldeAanmeldPogingen) 
	{
	
		werknemer = new Werknemer("EddyWally", "passwoord1" , "eddy" , "wally" , "eddy.wally@hotmail.com", Arrays.asList("+32472485968", "+32472457912"), WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Loppem" ,"8200" , "Guido Gezellelaan", 22, 2));

		Mockito.when(werknemerDao.bestaatWerkemer(gebruikersnaam)).thenReturn(status);
		
		Mockito.lenient().when(werknemerDao.geefWerknemer(gebruikersnaam, wachtwoord)).thenReturn(werknemer);
		
		Mockito.lenient().when(aanmeldpogingDao.geefAantalGefaaldeAanmeldPogingen(gebruikersnaam)).thenReturn(aantalGefaaldeAanmeldPogingen);
	}
}

// 

