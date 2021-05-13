package testen;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domein.Adres;

class AdresTest {
	 @ParameterizedTest
		@CsvSource({", , , ,0 , ," , " '','' , '' , '' , 0 , '' "})
		void new_Adres_VerkeerdeParameterss_ThrowsException(String land, String gemeente, String postcode, String straat, int huisnummer, String busnummer) {
			Assertions.assertThrows(IllegalArgumentException.class, ()-> 
			new Adres(land, gemeente, postcode, straat,huisnummer,busnummer));
	 }
		
		  @Test void new_AdresAanmakenMet_Succes() { Adres Adres
		  = new Adres("test", "test", "test", "test", 1, "B");
		  assertNotNull(Adres);
		  
		  }
		
}
