package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domein.*;
import domein.enumerations.TICKETSTATUS;
import domein.enumerations.WERKNEMERROL;

public class TicketTest {

	 @ParameterizedTest
		@CsvSource({", , ," , " '','' , '' "})
		void new_Ticket_VerkeerdeTitel_Opmerking_Omschrijving_ThrowsException(String titel, String omschrijving, String opmerking) {
			Assertions.assertThrows(IllegalArgumentException.class, ()-> 
			new Ticket(1, titel, omschrijving, opmerking, new Contract(),new TicketType()));
	 }
	 @Test
		void new_TicketAanmakenMetLeegContract_ThrowsException() {
		 Assertions.assertThrows(IllegalArgumentException.class, ()-> 
			new Ticket(1, "test", "test", "test", null,new TicketType()));
}
	 @Test
		void new_TicketAanmakenMetLeegTicketType_ThrowsException() {
		 Assertions.assertThrows(IllegalArgumentException.class, ()-> 
			new Ticket(1, "test", "test", "test", new Contract(),null));
}
		@Test
		void new_TicketAanmakenMetTicketStatusAangemaakt_Succes() {
			Ticket ticket = new Ticket(1, "test", "test", "test", new Contract(),new TicketType());
			assertEquals(TICKETSTATUS.AANGEMAAKT, ticket.getTicketStatus());
			assertNotNull(ticket);
			
		}

}
