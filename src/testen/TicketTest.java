package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domein.*;
import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;
import domein.enumerations.TICKETSTATUS;
import domein.enumerations.WERKNEMERROL;

public class TicketTest {
	
	private static final String Dienst = null;
	private static List<TICKETAANMAAKMETHODE> ticketAanmaakMethodeList() {
		List<TICKETAANMAAKMETHODE> ticketAanmaakMethode =  new ArrayList<>();
		ticketAanmaakMethode.add(TICKETAANMAAKMETHODE.VIA_APPLICATIE);
		ticketAanmaakMethode.add(TICKETAANMAAKMETHODE.EMAIL);
		return ticketAanmaakMethode;
	}
	
	private static Stream<Arguments> foutieveParameters() {
		Klant klant = new Klant("eddy123", "passwoord123", "eddy", "wally",
				"eddy.wally@hotmail.com", 2, 
				new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
						new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10")));

		ContractType contractType = new ContractType(1, "Simon", 350, 300 , 1000, ticketAanmaakMethodeList(), 
				TICKETAANMAAKTIJD.ALTIJD_24_7);
		

		Contract contract = new Contract("contract1", 100, LocalDate.now(), 
				LocalDate.now().plusDays(400), contractType, klant);
		
		TicketType ticketType = new TicketType(1, "hier naam", "hier omschrijving");

return Stream.of(
				
				Arguments.of(1, "titell", "hier de omschrijving",
						"hier de opmerkingen",  contract, ticketType),

				Arguments.of(2, "titell", null,"hier de opmerkingen",  contract, ticketType),		
				Arguments.of(3, "titell", "","hier de opmerkingen",  contract, ticketType),
				Arguments.of(4, "titell", "    ","hier de opmerkingen",  contract, ticketType),
				
				Arguments.of(4, "titell", "omschrijving hier","   ",  contract, ticketType),
				Arguments.of(4, "titell", "omschrijving hier",null,  contract, ticketType),
				Arguments.of(4, "titell", "omschrijving hier","",  contract, ticketType),
				
				Arguments.of(4, "", "omschrijving hier","hier de opmerkingen",  contract, ticketType),
				Arguments.of(4, null, "omschrijving hier","hier de opmerkingen",  contract, ticketType),
				Arguments.of(4, "  ", "omschrijving hier","hier de opmerkingen",  contract, ticketType));

	}
	
	private static Stream<Arguments> correcteParameters() {
		Klant klant = new Klant("eddy123", "passwoord123", "eddy", "wally",
				"eddy.wally@hotmail.com", 2, 
				new Bedrijf("actemium", Arrays.asList("+32472359285", "+32472359285"), 
						new Adres("BE", "Zedelgem", "8210", "Guido Gezellelaan", 54, "10")));

		ContractType contractType = new ContractType(1, "Simon", 350, 300 , 1000, ticketAanmaakMethodeList(), 
				TICKETAANMAAKTIJD.ALTIJD_24_7);
		

		Contract contract = new Contract("contract1", 100, LocalDate.now(), 
				LocalDate.now().plusDays(400), contractType, klant);
		
		TicketType ticketType = new TicketType(1, "hier naam", "hier omschrijving");

		return Stream.of(
				Arguments.of(12, "titell1", "hier een omschrijvinh1","hier de opmerkingen1",  contract, ticketType),		
				Arguments.of(34, "titell2", "hier een omschrijvinh2","hier de opmerkingen2",  contract, ticketType),
				Arguments.of(45, "titell3", "hier een omschrijvinh3","hier de opmerkingen3",  contract, ticketType));
	}
	
	@ParameterizedTest
	@MethodSource("correcteParameters")
	public void correctTicketAanmaken_correcteAttributenIngesteld(int ticketnummer, String titel, String omschrijving,
			String opmerkingen, Contract contract, TicketType ticketType) {
		Ticket ticket = new Ticket(ticketnummer, titel, omschrijving, opmerkingen, contract, ticketType);
		Assertions.assertEquals(titel, ticket.getTitel());
		Assertions.assertEquals(ticketnummer, ticket.getTicketnummer());
		Assertions.assertEquals(opmerkingen, ticket.getOpmerkingen());
		Assertions.assertEquals(ticketType, ticket.getTicketType());
		
		Assertions.assertEquals(contract, ticket.getContract());

	}

	
	
	
	@ParameterizedTest
	@MethodSource("foutieveParameters")
	public void foutTicketAanmaken_werpException(int ticketnummer, String titel, String omschrijving,
			String opmerkingen, Contract contract, TicketType ticketType) {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Ticket(ticketnummer, titel, omschrijving, opmerkingen, contract, ticketType));
	}

}
	
	



