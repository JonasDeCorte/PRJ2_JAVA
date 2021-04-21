package domein.beheerders;

import domein.dao.AanmeldPogingDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import domein.Klant;
import domein.dao.KlantDao;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.AanmeldPogingDaoJpa;
import repository.KlantDaoJpa;

public class KlantBeheerder {
	private KlantDao klantDao;
	private AanmeldPogingDao aanmeldpogingDao;
	private FilteredList<Klant> filteredKlantLijst;
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
	public ObservableList<Klant> haalKlantenOp() {
		if (filteredKlantLijst == null) {
			filteredKlantLijst = 
					new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll())
							,klant -> 
					klant.getGebruikerStatus() == GEBRUIKERSTATUS.ACTIEF);				
		}
		return FXCollections.unmodifiableObservableList(filteredKlantLijst);
	}

	public void voegKlantToe(Klant klant) {
		klantDao.startTransaction();
		klantDao.insert(klant);
		klantDao.commitTransaction();
		//filteredKlantLijst = new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll()),filteredKlantLijst.getPredicate());

	}

	public void verwijderKlant(Klant klant) {
		klantDao.startTransaction();
		klantDao.delete(klant);
		klantDao.commitTransaction();
	}

	public void wijzigKlant(Klant klant) {
		klantDao.startTransaction();
		klantDao.update(klant);
		klantDao.commitTransaction();
		filteredKlantLijst = new FilteredList<>(FXCollections.observableArrayList(klantDao.findAll()),filteredKlantLijst.getPredicate());

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