package repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import domein.*;
import domein.enumerations.CONTRACTSTATUS;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;
import domein.enumerations.TICKETSTATUS;
import domein.enumerations.WERKNEMERROL;

public class DataInitializer {
	private WerknemerDaoJpa werknemerDao = new WerknemerDaoJpa();
	private KlantDaoJpa klantDao = new KlantDaoJpa();
	private BedrijfDaoJpa bedrijfDao = new BedrijfDaoJpa();
	private ContractTypeDaoJpa contractTypeDao = new ContractTypeDaoJpa();
	private ContractDaoJpa contractDao = new ContractDaoJpa();				
	private TicketDaoJpa ticketDao = new TicketDaoJpa();
	private TicketTypeDaoJpa ticketTypeDao = new TicketTypeDaoJpa();
	
	public void initializeData() {
		werknemerDao.startTransaction();
		
		Werknemer Administrator = new Werknemer("admin", "admin", "Kees", "Schoens", "Kees.Schoens@Actemium.be", 1001,
				Arrays.asList("+32 456 25 67 85", "+32 458 95 62 36"), WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brussel","1000","Stationsstraat", 45, ""));
		werknemerDao.insert(Administrator);
		
		Werknemer Technieker = new Werknemer("tech", "tech", "Bert", "Weens", "Bert.Weens@Actemium.be", 1002,
				Arrays.asList("+32 558 67 42 33"), WERKNEMERROL.TECHNIEKER, new Adres("België", "Gent", "9000", "Moerkerksesteenweg", 7, "2"));
		werknemerDao.insert(Technieker);
		
		Werknemer SupportManager = new Werknemer("supp", "supp","Sophie", "Vermeersch", "Sophie.Vermeersch@Actemium.be", 1004,
				Arrays.asList("+32 895 32 44 85"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brugge", "8000", "Brugsestraat", 48, "3"));
		werknemerDao.insert(SupportManager);
				
		Werknemer ExSupportManager = new Werknemer("ExSupportManager", "ExSupportManager123", "Bram", "Skoets", "Bram.Skoets@Actemium.be", 1003,
				Arrays.asList("+32 556 55 47 82"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Gent", "9000", "Gentsesteenweg", 45, ""));
		ExSupportManager.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		werknemerDao.insert(ExSupportManager);
		
		Werknemer BlokSupportManager = new Werknemer("BlokSupportManager","BlokSupportManager123", "Laura", "Verstappe", "Laura.Verstappe@Actemium.be", 1005,
				Arrays.asList("+32 567 85 65 44"), WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brussel", "1000", "Marktplein", 77, "7C"));
		BlokSupportManager.setGebruikerStatus(GEBRUIKERSTATUS.GEBLOKKEERD);
		werknemerDao.insert(BlokSupportManager);
		
		werknemerDao.commitTransaction();		
		bedrijfDao.startTransaction();
		
		Bedrijf BEEGO = new Bedrijf("BEEGO", Arrays.asList("+32 567 85 44 23"), new Adres("België", "Gent", "9000", "Koestraat", 47, "8"));
		bedrijfDao.insert(BEEGO);
		
		Bedrijf Microsoft = new Bedrijf("Microsoft", Arrays.asList("+33 895 58 65 10"), new Adres("Frankrijk", "Parijs", "75008", "Rue Chambiges", 85, "3C"));
		bedrijfDao.insert(Microsoft);
		
		bedrijfDao.commitTransaction();
		klantDao.startTransaction();
		
		Klant Klant1 = new Klant("Klant1", "Klant1123", "Joeri", "Kools", "Joeri.Kools@BEEGO.com", 1001, BEEGO);
		klantDao.insert(Klant1);
		
		Klant Klant2 = new Klant("Klant2", "Klant2123", "Seppe", "Meens", "Seppe.Meens@BEEGO.com", 1002, BEEGO);	
		klantDao.insert(Klant2);
		
		Klant Klant3 = new Klant("Klant3", "Klant3123", "Juliette", "Debois", "Juliette.Debois@Microsoft.com", 1003, Microsoft);	
		klantDao.insert(Klant3);
		
		Klant Klant4 = new Klant("Klant4", "Klant4123", "Céline", "Klavers", "Celine.Klavers@Microsoft.com", 1004, Microsoft);
		Klant4.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		klantDao.insert(Klant4);
		
		Klant Klant5 = new Klant("Klant5", "Klant5123", "Bob", "Schoens", "Bob.Schoens@Microsft.com", 1005, Microsoft);
		Klant5.setGebruikerStatus(GEBRUIKERSTATUS.GEBLOKKEERD);
		klantDao.insert(Klant5);
		
		klantDao.commitTransaction();
		contractTypeDao.startTransaction();
		
		ContractType ContractType1 = new ContractType(1, "Defecten in productie", 3, 1, 199.99, 
				Arrays.asList(TICKETAANMAAKMETHODE.VIA_APPLICATIE), TICKETAANMAAKTIJD.ALTIJD_24_7);
		ContractType1.setStatus(false);
		contractTypeDao.insert(ContractType1);
		
		ContractType ContractType2 = new ContractType(2, "Defecten in magazijn", 5, 2, 149.99, 
				Arrays.asList(TICKETAANMAAKMETHODE.VIA_APPLICATIE, TICKETAANMAAKMETHODE.EMAIL, TICKETAANMAAKMETHODE.TELEFONISCH) , TICKETAANMAAKTIJD.ALTIJD_24_7);
		contractTypeDao.insert(ContractType2);
		
		ContractType ContractType3 = new ContractType(3, "Defecten in molding", 5, 2, 149.99, Arrays.asList(
				TICKETAANMAAKMETHODE.EMAIL, TICKETAANMAAKMETHODE.TELEFONISCH) , TICKETAANMAAKTIJD.WERKDAGEN_8_TOT_17);
		contractTypeDao.insert(ContractType3);
		
		ContractType ContractType4 = new ContractType(4, "Defecten in assembly", 5, 2, 149.99, Arrays.asList(
				TICKETAANMAAKMETHODE.VIA_APPLICATIE) , TICKETAANMAAKTIJD.ALTIJD_24_7);
		contractTypeDao.insert(ContractType4);
		
		contractTypeDao.commitTransaction();
		contractDao.startTransaction();
		
		Contract Contract1 = new Contract("Jaar Contract productie", 1, LocalDate.of(2020,2,8), LocalDate.of(2021, 2, 8), ContractType1, Klant1);
		Contract1.setContractstatus(CONTRACTSTATUS.BEËINDIGD);
		contractDao.insert(Contract1);
		
		Contract Contract2 = new Contract("Contract magazijn 1 jaar", 1, LocalDate.of(2019,5,27), LocalDate.of(2020, 5, 27), ContractType2, Klant2);
		Contract2.setContractstatus(CONTRACTSTATUS.BEËINDIGD);
		contractDao.insert(Contract2);
		
		Contract Contract3 = new Contract("Contract magazijn 2 jaar", 2, LocalDate.of(2020,5,27), LocalDate.of(2022, 5, 27), ContractType2, Klant2);
		contractDao.insert(Contract3);
		
		Contract Contract4 = new Contract("Contract molding 3 jaar", 3, LocalDate.of(2020,8,15), LocalDate.of(2023, 8, 15), ContractType3, Klant3);
		contractDao.insert(Contract4);
		
		Contract Contract5 = new Contract("Contract assembly 2 jaar", 2, LocalDate.of(2021, 8, 15), LocalDate.of(2022, 8, 15), ContractType4, Klant3);
		contractDao.insert(Contract5);
		
		contractDao.commitTransaction();
		ticketTypeDao.startTransaction();
		
		TicketType HogePrio = new TicketType(1, "Hoge prioriteit", "Productie geïmpacteerd, binnen 2 uur is er een oplossing nodig.");
		ticketTypeDao.insert(HogePrio);
		
		TicketType MediumPrio = new TicketType(2, "Medium prioriteit", "Productie zal binnen een tijd stilvallen, binnen 4 uur is er een oplossing nodig.");
		ticketTypeDao.insert(MediumPrio);
		
		TicketType LagePrio = new TicketType(3, "Lage prioriteit", "Geen productie impact, binnen 3 dagen een antwoord");
		ticketTypeDao.insert(LagePrio);
		
		ticketTypeDao.commitTransaction();
		ticketDao.startTransaction();
		
		
		List<Werknemer> AlleTechniekers = werknemerDao.geefTechniekers();
		
		Ticket Ticket1 =  new Ticket(1001, "Defecte productie rolband ", "Rolband kan niet meer rollen.", "Lokale technieker kan het niet vinden" , Contract1, HogePrio);
		Ticket1.setTicketStatus(TICKETSTATUS.AFGEHANDELD);
		Ticket1.setToegekendeTechnieker(AlleTechniekers.get(0));
		ticketDao.insert(Ticket1);
		
		Ticket Ticket2 = new Ticket(1002, "Defect karton machine", "Kartonnen dozen worden niet gelijmd", "Lijm is aangevuld", Contract2, MediumPrio);	
		Ticket2.setTicketStatus(TICKETSTATUS.AFGEHANDELD);
		Ticket2.setToegekendeTechnieker(AlleTechniekers.get(0));
		ticketDao.insert(Ticket2);
		
		Ticket Ticket3 = new Ticket(1003, "Defect plooi mechanisme karton machine", "Kartonnen dozen van medium size worden niet geplooid", "Opgelost door lokale technieker", Contract2, MediumPrio);	
		Ticket3.setTicketStatus(TICKETSTATUS.GEANNULEERD);
		Ticket3.setToegekendeTechnieker(AlleTechniekers.get(0));
		ticketDao.insert(Ticket3);
		
		Ticket Ticket4 = new Ticket(1004, "Defect plak mechanisme karton machine", "Kartonnen dozen van medium size worden niet geplakt", "Plakband is aangevuld en correct geïnstalleerd", Contract3, MediumPrio);	
		Ticket4.setTicketStatus(TICKETSTATUS.IN_BEHANDELING);
		Ticket4.setToegekendeTechnieker(AlleTechniekers.get(0));
		ticketDao.insert(Ticket4);
		
		Ticket Ticket5 = new Ticket(1005, "Molding machine gx200 is defect", "We hebben geen idee, de machine start niet meer op", "Nieuwe mold sinds gisteren", Contract4, HogePrio);	
		Ticket5.setTicketStatus(TICKETSTATUS.IN_BEHANDELING);
		Ticket5.setToegekendeTechnieker(AlleTechniekers.get(0));
		ticketDao.insert(Ticket5);
		
		ticketDao.commitTransaction();
		GenericDaoJpa.closePersistency();
	}
}