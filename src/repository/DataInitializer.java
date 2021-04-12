package repository;

import java.util.Arrays;

import domein.*;
import domein.enumerations.WERKNEMERROL;

public class DataInitializer {
	private WerknemerDaoJpa werknemerDao = new WerknemerDaoJpa();
	
	public void initializeData() {
		initializeWerknemers();
		initializeKlanten();
		initializeContractTypes();
		initializeContracten();
		initializeTicketTypes();
		initializeTickets();
	}

	private void initializeWerknemers() {
		werknemerDao.startTransaction();
	
		werknemerDao.insert(new Werknemer("Administrator", "Administrator123", "Kees", "Schoens", "Kees.Schoens@Actemium.be",
				Arrays.asList("+32 456 25 67 85", "+32 458 95 62 36"), WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brussel","1000","Stationsstraat", 45, "")));

		werknemerDao.insert(new Werknemer("Technieker", "Technieker123", "Bert", "Weens", "Bert.Weens@Actemium.be",
				Arrays.asList("+32 558 67 42 33"), WERKNEMERROL.TECHNIEKER, new Adres("België", "Gent", "9000", "Moerkerksesteenweg", 7, "2")));
		
		werknemerDao.insert(new Werknemer("SupportManager", "SupportManager123","Sophie", "Vermeersch", "Sophie.Vermeersch@Actemium.be",
				Arrays.asList("+32 895 32 44 85"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brugge", "8000", "Brugsestraat", 48, "3")));
		
		werknemerDao.commitTransaction();
		GenericDaoJpa.closePersistency();
	}
	
	private void initializeKlanten() {
		//TO DO
	}
	
	private void initializeContractTypes() {
		//TO DO
	}
	
	private void initializeContracten() {
		//TO DO
	}
	
	private void initializeTicketTypes() {
		//TO DO
	}
	
	private void initializeTickets() {
		// TO DO
	}
}