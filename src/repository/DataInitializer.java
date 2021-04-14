// test
package repository;

import java.util.Arrays;

import domein.*;
import domein.enumerations.WERKNEMERROL;

public class DataInitializer {
	private WerknemerDaoJpa werknemerDao = new WerknemerDaoJpa();
	private KlantDaoJpa klantDao = new KlantDaoJpa();
	
	public void initializeData() {
		initializeWerknemers();
		//initializeKlanten();
		initializeContractTypes();
		initializeContracten();
		initializeTicketTypes();
		initializeTickets();
		GenericDaoJpa.closePersistency();
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
	}
	
	private void initializeKlanten() {
		klantDao.startTransaction();
		
		Bedrijf BEEGO = new Bedrijf("BEEGO", Arrays.asList("+32 567 85 44 23"), new Adres("België", "Gent", "9000", "Koestraat", 47, "8"));
		Bedrijf Microsoft = new Bedrijf("Microsoft", Arrays.asList("+33 895 58 65 10"), new Adres("Frankrijk", "Parijs", "75008", "Rue Chambiges", 85, "3C"));
		
		klantDao.insert(new Klant("Klant1", "Klant1123", "Joeri", "Kools", "Joeri.Kools@BEEGO.com", BEEGO));
		klantDao.insert(new Klant("Klant2", "Klant2123", "Joeri", "Kools", "Joeri.Kools@BEEGO.com", BEEGO));	
		klantDao.insert(new Klant("Klant3", "Klant3123", "Juliette", "Debois", "Juliette.Debois@Microsoft.com", Microsoft));
		
		klantDao.commitTransaction();		
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