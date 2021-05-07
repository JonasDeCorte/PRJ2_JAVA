package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.Werknemer;
import domein.enumerations.WERKNEMERROL;

class WerknemerTest {
	
	  
public Werknemer wn;
	@Test
	void new_WerknemerAanmakenMetLeegAdres_ThrowsException() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Werknemer("test", "test", "test", "test", "test@gmail.com", 5000,  new ArrayList<String>(), WERKNEMERROL.ADMINISTRATOR , null));
	}
	@Test
	void new_WerknemerAanmakenMetLeegWerknemerrol_ThrowsException() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Werknemer("test", "test", "test", "test", "test@gmail.com", 5000,  new ArrayList<String>(), null, new Adres()));
	}
	@ParameterizedTest
	 @EnumSource(value = WERKNEMERROL.class)
	void new_WerknemerAanmakenMetWerknemerrol_Succes(WERKNEMERROL werknemerRol) {
	 wn = 	new Werknemer("test", "test", "test", "test", "test@gmail.com", 5000,  new ArrayList<String>(), werknemerRol, new Adres());
	assertNotNull(wn);
		
	}


}
