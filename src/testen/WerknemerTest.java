package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.Werknemer;
import domein.enumerations.WERKNEMERROL;


public class WerknemerTest {
	private static Stream<Arguments> foutieveParameters() {
		return Stream.of(
				
				//foutief adres
				Arguments.of("Simon", "wachtwoord", "simon", "bekaert", "simon.bekaert2000@hotmail.com",
				1, Arrays.asList("+32472485464"), WERKNEMERROL.ADMINISTRATOR ,
				null),
				
				//foutief werknemerrol
				Arguments.of("Simon", "wachtwoord", "simon", "bekaert", "simon.bekaert2000@hotmail.com",
						1, Arrays.asList("+32472485464"), null ,
						new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10")));

			
	}

	private static Stream<Arguments> correcteParameters() {
		return Stream.of(
				Arguments.of("Simon", "wachtwoord", "simon", "bekaert", "simon.bekaert2000@hotmail.com",
						1, Arrays.asList("+32472485464"), WERKNEMERROL.ADMINISTRATOR ,
						new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10")));
	}

	@ParameterizedTest
	@MethodSource("correcteParameters")
	public void correcteWerknemerAanmaken_steltAtributenCorrectIn(String gebruikersnaam, String wachtwoord, 
			String voornaam, String naam, String emailadres, int personeelsnummer,  List<String> telefoonnummers, 
			WERKNEMERROL werknemerRol , Adres adres) {
		Werknemer werknemer = new Werknemer(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, personeelsnummer, 
				telefoonnummers, werknemerRol, adres);
		
		Assertions.assertEquals(gebruikersnaam, werknemer.getGebruikersnaam());
		Assertions.assertEquals(wachtwoord, werknemer.getWachtwoord());
		Assertions.assertEquals(voornaam, werknemer.getVoornaam());
		Assertions.assertEquals(naam, werknemer.getNaam());
		Assertions.assertEquals(emailadres, werknemer.getEmailadres());
		Assertions.assertEquals(personeelsnummer, werknemer.getPersoneelsnummer());
		Assertions.assertEquals(telefoonnummers, werknemer.getTelefoonnummers());
		Assertions.assertEquals(adres, werknemer.getAdres());
	}

	@ParameterizedTest
	@MethodSource("foutieveParameters")
	public void foutieveWerknemerAanmaken_werptException(String gebruikersnaam, String wachtwoord, 
			String voornaam, String naam, String emailadres, int personeelsnummer,  List<String> telefoonnummers, 
			WERKNEMERROL werknemerRol , Adres adres) {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Werknemer(gebruikersnaam, wachtwoord, voornaam, naam, emailadres, personeelsnummer, 
						telefoonnummers, werknemerRol, adres));
	}
}
