package domein.beheerders;

import domein.dao.AanmeldPogingDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import domein.Klant;
import domein.dao.KlantDao;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import repository.AanmeldPogingDaoJpa;
import repository.KlantDaoJpa;

public class KlantBeheerder {
	private KlantDao klantDao;
	private AanmeldPogingDao aanmeldpogingDao;
	private FilteredList<Klant> filteredKlantLijst;
	private SortedList<Klant>sortedKlantList;
	private final Comparator<Klant> byLastName = (p1, p2) ->
	p1.getNaam().compareToIgnoreCase(p2.getNaam());
	private final Comparator<Klant> byEmail = (p1, p2) ->
	p1.getEmailadres().compareToIgnoreCase(p2.getEmailadres());
	private final Comparator<Klant> byFirstName = (p1, p2) ->
	p1.getVoornaam().compareToIgnoreCase(p2.getVoornaam());
	private final Comparator<Klant> sortOrder =
			byFirstName.thenComparing(byLastName).
			thenComparing(byEmail);

	public KlantBeheerder(KlantDao klantDao,AanmeldPogingDao aanmeldpogingDao ){
		this.klantDao = klantDao;
		this.aanmeldpogingDao = aanmeldpogingDao;
	}

	public KlantBeheerder() {
		this(new KlantDaoJpa(), new AanmeldPogingDaoJpa());
	}
	public boolean bestaatKlant(String gebruikersnaam) {
		return klantDao.bestaatKlant(gebruikersnaam);
	}
	
	public boolean bestaatKlantnummer(int klantnummer) {
		return klantDao.bestaatKlantnummer(klantnummer);
	}
	
	public ObservableList<Klant> haalKlantenOp() {
		if (filteredKlantLijst == null) {
			filteredKlantLijst = 
					new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll())
							,klant -> 
					klant.getGebruikerStatus() == GEBRUIKERSTATUS.ACTIEF);				
		}
		sortedKlantList = new SortedList<>(filteredKlantLijst, sortOrder);
				
		return sortedKlantList;
		
	}

	public void voegKlantToe(Klant klant) {
		if(!bestaatKlant(klant.getGebruikersnaam())) {
		klantDao.startTransaction();
		klantDao.insert(klant);
		klantDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("Klant bestaat al");
		}
		
		filteredKlantLijst = new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll()),filteredKlantLijst.getPredicate());
		sortedKlantList = new SortedList<>(filteredKlantLijst, sortOrder);
				

	}

	public void verwijderKlant(Klant klant) {
		klantDao.startTransaction();
		klantDao.delete(klant);
		klantDao.commitTransaction();
	}

	public void wijzigKlant(Klant klant, String origineleGebruikersnaam) {
		if(bestaatKlant(origineleGebruikersnaam)) {
			klantDao.startTransaction();
		klantDao.update(klant);
		klantDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("klant bestaat niet.");
		}
		
		filteredKlantLijst = new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll()),filteredKlantLijst.getPredicate());
		sortedKlantList = new SortedList<>(filteredKlantLijst, sortOrder);
	}
	public void pasFilterAan(String gebruikersnaam,String naam,String voornaam, String bedrijfsnaam,Set<GEBRUIKERSTATUS> status) {
		// houdt de filters bij
		List<Predicate<Klant>> filtersLijst = new ArrayList<>();


		if (gebruikersnaam != null && !gebruikersnaam.isBlank()) {
			filtersLijst.add(klant -> klant.getGebruikersnaam().toLowerCase().contains(gebruikersnaam.toLowerCase()));		
		}

		if (naam != null && !naam.isBlank()) {
			filtersLijst.add(klant -> klant.getNaam().toLowerCase().contains(naam.toLowerCase()));
		}

		if (voornaam != null && !voornaam.isBlank()) {
			filtersLijst.add(klant -> klant.getVoornaam().toLowerCase().contains(voornaam.toLowerCase()));
		}
		
		if (bedrijfsnaam != null && !bedrijfsnaam.isBlank()) {
			filtersLijst.add(klant ->  klant.getBedrijf().getBedrijfsnaam().toLowerCase().contains(bedrijfsnaam.toLowerCase()));
		}

		if (status != null && (status.size() > 0 || status.size() >= GEBRUIKERSTATUS.values().length)) {
			filtersLijst.add(klant -> status.contains(klant.getGebruikerStatus()));
		} 
		// filter wordt toegekend aan een predikaat 
		/*
		 * If you have a Collection<Predicate<T>> filters you can always create a single predicate out of it using the process called reduction:
		 * Predicate<T> pred=filters.stream().reduce(Predicate::and).orElse(x->true);
		 *  */ 
		Predicate<Klant> filterKlantLijst = filtersLijst.stream().reduce(Predicate::and).orElse(x -> true);
		// predikaat toepassen op de filteredklantlijst 
		filteredKlantLijst.setPredicate(filterKlantLijst);
	}
}