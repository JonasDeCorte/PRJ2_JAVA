package repository;

import java.util.Arrays;

import domein.*;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;

public class DataInitializer {
	private WerknemerDaoJpa werknemerDao = new WerknemerDaoJpa();
	private KlantDaoJpa klantDao = new KlantDaoJpa();
	private BedrijfDaoJpa bedrijfDao = new BedrijfDaoJpa();
	
	public void initializeData() {
		initializeWerknemers();
		initializeKlantenEnBedrijven();
		initializeContractTypes();
		initializeContracten();
		initializeTicketTypes();
		initializeTickets();
		GenericDaoJpa.closePersistency();
	}

	private void initializeWerknemers() {
		werknemerDao.startTransaction();
		
		werknemerDao.insert(new Werknemer("Administrator", "Administrator123", "Kees", "Schoens", "Kees.Schoens@Actemium.be", 1001,
				Arrays.asList("+32 456 25 67 85", "+32 458 95 62 36"), WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brussel","1000","Stationsstraat", 45, "")));

		werknemerDao.insert(new Werknemer("Technieker", "Technieker123", "Bert", "Weens", "Bert.Weens@Actemium.be", 1002,
				Arrays.asList("+32 558 67 42 33"), WERKNEMERROL.TECHNIEKER, new Adres("België", "Gent", "9000", "Moerkerksesteenweg", 7, "2")));
			
		Werknemer Bram = new Werknemer("ExSupportManager", "ExSupportManager123", "Bram", "Skoets", "Bram.Skoets@Actemium.be", 1003,
				Arrays.asList("+32 556 55 47 82"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Gent", "9000", "Gentsesteenweg", 45, ""));
		Bram.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		werknemerDao.insert(Bram);
		
		werknemerDao.insert(new Werknemer("SupportManager", "SupportManager123","Sophie", "Vermeersch", "Sophie.Vermeersch@Actemium.be", 1004,
				Arrays.asList("+32 895 32 44 85"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brugge", "8000", "Brugsestraat", 48, "3")));
		
		Werknemer Laura = new Werknemer("BlokSupportManager","BlokSupportManager123", "Laura", "Verstappe", "Laura.Verstappe@Actemium.be", 1005,
				Arrays.asList("+32 567 85 65 44"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brussel", "1000", "Marktplein", 77, "7C"));
		Laura.setGebruikerStatus(GEBRUIKERSTATUS.GEBLOKKEERD);
		werknemerDao.insert(Laura);
		
		werknemerDao.commitTransaction();
	}
	
	private void initializeKlantenEnBedrijven() {
		bedrijfDao.startTransaction();
		Bedrijf BEEGO = new Bedrijf("BEEGO", Arrays.asList("+32 567 85 44 23"), new Adres("België", "Gent", "9000", "Koestraat", 47, "8"));
		Bedrijf Microsoft = new Bedrijf("Microsoft", Arrays.asList("+33 895 58 65 10"), new Adres("Frankrijk", "Parijs", "75008", "Rue Chambiges", 85, "3C"));
		bedrijfDao.insert(BEEGO);
		bedrijfDao.insert(Microsoft);
		bedrijfDao.commitTransaction();
			
		klantDao.startTransaction();	
		klantDao.insert(new Klant("Klant1", "Klant1123", "Joeri", "Kools", "Joeri.Kools@BEEGO.com", 1001, BEEGO));
		klantDao.insert(new Klant("Klant2", "Klant2123", "Seppe", "Meens", "Seppe.Meens@BEEGO.com", 1002, BEEGO));	
		klantDao.insert(new Klant("Klant3", "Klant3123", "Juliette", "Debois", "Juliette.Debois@Microsoft.com", 1003, Microsoft));	
		
		Klant Céline = new Klant("Klant4", "Klant4123", "Céline", "Klavers", "Céline.Klavers@Microsoft.com", 1004, Microsoft);
		Céline.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		klantDao.insert(Céline);
		
		Klant Bob = new Klant("Klant5", "Klant5123", "Bob", "Schoens", "Bob.Schoens@Microsft.com", 1005, Microsoft);
		Bob.setGebruikerStatus(GEBRUIKERSTATUS.GEBLOKKEERD);
		klantDao.insert(Bob);
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