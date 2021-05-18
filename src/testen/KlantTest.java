package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;

class KlantTest {
	
	Klant klant = new Klant("eddy123", "passwoord123", "eddy", "wally",
			"eddy.wally@hotmail.com", 2, 
			new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
					new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10")));


	
	private static Stream<Arguments> foutieveParameters() {
		return Stream.of(
				//foute klantnummer
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", 0, 
						new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
								new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10"))),	
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", -1, 
						new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
								new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10"))),	
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", null, 
						new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
								new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10"))),	
				
				
				
				//fout bedrijf
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", 2, 
						null));
			
	}
	
	private static Stream<Arguments> correcteParameters() {
		return Stream.of(
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", 2, 
						new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
								new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10"))),	
				Arguments.of("eddy123", "passwoord123", "eddy", "wally",
						"eddy.wally@hotmail.com", 3, 
						new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
								new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10"))));	
	}
	
	
	
	@ParameterizedTest
	@MethodSource("correcteParameters")
	public void correcteKlantAanmaken_steltAtributenCorrectIn(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, int klantnummer,
			Bedrijf bedrijf) {
		Klant klant = new Klant(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, klantnummer, bedrijf);
		Assertions.assertEquals(gebruikersnaam, klant.getGebruikersnaam());
		Assertions.assertEquals(wachtwoord, klant.getWachtwoord());
		Assertions.assertEquals(voornaam, klant.getVoornaam());
		Assertions.assertEquals(naam, klant.getNaam());
		Assertions.assertEquals(emailadres, klant.getEmailadres());
		Assertions.assertEquals(klantnummer, klant.getKlantnummer());
		Assertions.assertEquals(bedrijf, klant.getBedrijf());
	}
	
	
	@ParameterizedTest
	@MethodSource("foutieveParameters")
	public void foutieveKlantAanmaken_werptException(String gebruikersnaam, String wachtwoord, String voornaam, String naam, String emailadres, int klantnummer,
			Bedrijf bedrijf) {

		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Klant(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, klantnummer, bedrijf));
	}
	
	
	
	
	
	

}
