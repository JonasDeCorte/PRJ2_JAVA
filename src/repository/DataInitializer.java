package repository;

import domein.*;
import domein.dao.*;
import domein.enumerations.WERKNEMERROL;

public class DataInitializer {
	private WerknemerDaoJpa werknemerDao = new WerknemerDaoJpa();
	
	public void initializeData() {
		werknemerDao.startTransaction();
		
		String[] telefoonnummersKees = {"+32 456 25 67 85", "+32 458 95 62 36"};
		werknemerDao.insert(new Werknemer("AdminKees", "AdminKees123", "Kees", "Schoens", "Kees.Schoens@Actemium.be",
				telefoonnummersKees, WERKNEMERROL.ADMINISTRATOR, new Adres("België", "Brussel","1000","Stationsstraat", 45, "")));
		
		String[] telefoonnumersBert = {"+32 558 67 42 33"};
		werknemerDao.insert(new Werknemer("TechBert", "TechBert123", "Bert", "Weens", "Bert.Weens@Actemium.be",
				telefoonnumersBert, WERKNEMERROL.TECHNIEKER, new Adres("België", "Gent", "9000", "Moerkerksesteenweg", 7, "2")));
		
		String[] telefoonnummersSophie = {"+32 895 32 44 85"};
		werknemerDao.insert(new Werknemer("SuppSophie", "SuppSophie123@","Sophie", "Vermeersch", "Sophie.Vermeersch@Actemium.be",
				telefoonnummersSophie, WERKNEMERROL.SUPPORTMANAGER, new Adres("België", "Brugge", "8000", "Brugsestraat", 48, "3")));
	}
}
