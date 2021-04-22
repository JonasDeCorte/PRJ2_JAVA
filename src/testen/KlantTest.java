package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;
import domein.Klant;

class KlantTest {

	@Test
	void new_KlantAanmakenMetLeegBedrijf_ThrowsException() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", "test", "test@gmail.com", 4000,
			null));
	}

}
