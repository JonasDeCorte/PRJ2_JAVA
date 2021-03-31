package domein;

import java.util.List;

import Persistentie.mapperExample;

public class DomeinController {
	private Gebruiker actieveGebruiker;
	private final mapperExample mapperexample;
	public DomeinController() {
		mapperexample = new mapperExample();
	}
	public void test() {
		List<TicketType> types = mapperexample.geefTicketTypes();
		types.forEach(System.out::println);
		
	}
	
}
