package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bedrijf;
import domein.Klant;

class KlantTest {

	@Test
	void new_KlantAanmakenMetLeegBedrijf_ThrowsException() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", "test", "test@gmail.com", 4000,
			null));
	}
	@ParameterizedTest
	@ValueSource(ints = {0, -3, -5, -3, -15, Integer.MIN_VALUE})
	void new_KlantAanmakenMetFoutiefKlantNummer_ThrowsException(int klantummer) {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", "test", "test@gmail.com", klantummer,
			new Bedrijf()));
	}
	
	
	

}
