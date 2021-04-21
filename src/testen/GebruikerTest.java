package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domein.Bedrijf;
import domein.Klant;

class GebruikerTest {

	@Test
	void new_GebruikerMetGebruikersnaam3karakters_ThrowsException() {
		//gebruikersnaam moet minstens 4 karakters bevatten
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "tes", "test", "test", "test", "test@gmail.com", 4000,
			new Bedrijf()));
	}
	@Test
	void new_GebruikerMetLegeVoornaam_ThrowsException() {
		//gebruikersnaam moet minstens 4 karakters bevatten
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", null, "test", "test@gmail.com", 4000,
				new Bedrijf()));
	}
	@Test
	void new_GebruikerMetLegeNaam_ThrowsException() {
		//gebruikersnaam moet minstens 4 karakters bevatten
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", null, "test@gmail.com", 4000,
				new Bedrijf()));
	}
	@Test
	void new_GebruikerMetLegeEmail_ThrowsException() {
		//gebruikersnaam moet minstens 4 karakters bevatten
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", "test", null, 4000,
				new Bedrijf()));
	}
	@ParameterizedTest
	@CsvSource({"testgmail.com","test@gmailcom","test@","testgmailcom","test.com","@gmail.com"})
	void new_GebruikerMetVerkeerdeEmail_ThrowsException(String email) {
		//gebruikersnaam moet minstens 4 karakters bevatten
		Assertions.assertThrows(IllegalArgumentException.class, ()-> new Klant( "test", "test", "test", "test", email, 4000,
				new Bedrijf()));
	}

}
