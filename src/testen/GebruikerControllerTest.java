package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.AanmeldPoging;
import domein.Klant;
import domein.Werknemer;
import domein.controllers.AanmeldController;
import domein.controllers.GebruikerController;
import domein.dao.WerknemerDao;

@ExtendWith(MockitoExtension.class)
class TestGebruikerController {
	
	@Mock
	private WerknemerDao werknemerDao;
	
	@InjectMocks
	private GebruikerController gebruikerController;
		
	private Werknemer werknemer;
	
	private Klant klant;

	@Test
	void voegWerknemerToe_WerknemerIsNull_ThrowsException() {
		werknemer = null;
		Assertions.assertThrows(NullPointerException.class,()->gebruikerController.voegWerknemerToe(werknemer));
	}
	@Test
	void voegWerknemerToe_KlantIsNull_ThrowsException() {
		klant = null;
		Assertions.assertThrows(NullPointerException.class,()->gebruikerController.voegKlantToe(klant));
	}
	

}
