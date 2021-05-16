package testen;

import java.util.Set;
import java.util.stream.Stream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import domein.ContractType;
import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;

public class ContractTypeTest {
	
	private static List<TICKETAANMAAKMETHODE> ticketAanmaakMethodeList() {
		List<TICKETAANMAAKMETHODE> ticketAanmaakMethode =  new ArrayList<>();
		ticketAanmaakMethode.add(TICKETAANMAAKMETHODE.VIA_APPLICATIE);
		ticketAanmaakMethode.add(TICKETAANMAAKMETHODE.EMAIL);
		return ticketAanmaakMethode;
	}

	private static Stream<Arguments> correcteParameters() {
		return Stream.of(
				Arguments.of(1, "Simon", 350, 300 , 1000, ticketAanmaakMethodeList(), 
						TICKETAANMAAKTIJD.ALTIJD_24_7),
				
				
				
				
				
				Arguments.of(2, "Eddy", 700, 650 , 2000, ticketAanmaakMethodeList(), 
						 TICKETAANMAAKTIJD.WERKDAGEN_8_TOT_17));
	}

	
	private static Stream<Arguments> foutieveParameters() {
		return Stream.of(
				Arguments.of(1, "", 350, 300 , 1000, ticketAanmaakMethodeList(), 
						TICKETAANMAAKTIJD.ALTIJD_24_7),
				
				Arguments.of(1, null, 350, 300 , 1000, ticketAanmaakMethodeList(), 
						TICKETAANMAAKTIJD.ALTIJD_24_7),
				
				Arguments.of(1, "  ", 350, 300 , 1000, ticketAanmaakMethodeList(), 
						TICKETAANMAAKTIJD.ALTIJD_24_7),
				
				// foute TicketAanmaakTijd
				Arguments.of(1, "Simon", 350, 300 , 1000, ticketAanmaakMethodeList(), 
						null),
				
				
				// foute ticketAanmaakTijdMethode
				Arguments.of(1, "Simon", 350, 300 , 1000, null, 
						TICKETAANMAAKTIJD.ALTIJD_24_7));
	}
	
	@ParameterizedTest
	@MethodSource("correcteParameters")
	public void correctContractTypeAanmaken_correcteVelden(int contractTypeId, String naam, int maximaleAfhandelTijd, int minimaleDoorloopTijd, 
			double prijs, List<TICKETAANMAAKMETHODE> ticketAanmaakMethode, TICKETAANMAAKTIJD ticketAanmaakTijd) 
	
	
	
	{
		ContractType contractType = new ContractType(contractTypeId, naam, maximaleAfhandelTijd, minimaleDoorloopTijd,
				prijs, ticketAanmaakMethode, ticketAanmaakTijd );
		Assertions.assertEquals(contractTypeId, contractType.getContractTypeId());
		Assertions.assertEquals(naam, contractType.getNaam());
		Assertions.assertEquals(ticketAanmaakMethode, contractType.getTicketAanmaakMethode());
		Assertions.assertEquals(ticketAanmaakTijd, contractType.getTicketAanmaakTijd());
		Assertions.assertEquals(maximaleAfhandelTijd, contractType.getMaximaleAfhandelTijd());
		Assertions.assertEquals(minimaleDoorloopTijd, contractType.getMinimaleDoorloopTijd());
		Assertions.assertEquals(prijs, contractType.getPrijs());
		
	}
	
	@ParameterizedTest
	@MethodSource("foutieveParameters")
	public void foutiefContractAanmaken_werptIllegalArgumentException(int contractTypeId, String naam, int maximaleAfhandelTijd, int minimaleDoorloopTijd, 
			double prijs, List<TICKETAANMAAKMETHODE> ticketAanmaakMethode, TICKETAANMAAKTIJD ticketAanmaakTijd) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new ContractType(contractTypeId, naam, maximaleAfhandelTijd, minimaleDoorloopTijd,
				prijs, ticketAanmaakMethode, ticketAanmaakTijd ));

	}

}
