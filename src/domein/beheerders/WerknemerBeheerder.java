package domein.beheerders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import domein.Klant;
import domein.Werknemer;
import domein.dao.AanmeldPogingDao;
import domein.dao.WerknemerDao;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.AanmeldPogingDaoJpa;
import repository.WerknemerDaoJpa;

public class WerknemerBeheerder {
	private WerknemerDao werknemerDao;
	private AanmeldPogingDao aanmeldPogingDao;
	private FilteredList<Werknemer> filteredWerknemerLijst;
	
	public WerknemerBeheerder() {
		this(new WerknemerDaoJpa(), new AanmeldPogingDaoJpa());
	}

	public WerknemerBeheerder(WerknemerDao werknemerDao, AanmeldPogingDao aanmeldPogingDao) {
		this.werknemerDao = werknemerDao;
		this.aanmeldPogingDao = aanmeldPogingDao;
	}
	public boolean bestaatWerknemer(String gebruikersnaam) {
		return werknemerDao.bestaatWerknemer(gebruikersnaam);
	}
	public Werknemer haalWerknemerOp(int personeelsnummer) {
		throw new IllegalArgumentException();
	}
	
	public void voegWerknemerToe(Werknemer werknemer) {
		if(!bestaatWerknemer(werknemer.getGebruikersnaam())) {
			werknemerDao.startTransaction();
		werknemerDao.insert(werknemer);
		werknemerDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("Werknemer bestaat al.");
		}
		
		filteredWerknemerLijst = new FilteredList<>(FXCollections.observableArrayList(werknemerDao.findAll()),filteredWerknemerLijst.getPredicate());
	}
	
	public void wijzigWerknemer(Werknemer werknemer) {
		if(bestaatWerknemer(werknemer.getGebruikersnaam())) {
		werknemerDao.startTransaction();
		werknemerDao.update(werknemer);
		werknemerDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("werknemer bestaat niet.");
			}
		filteredWerknemerLijst = new FilteredList<>(FXCollections.observableArrayList(werknemerDao.findAll()),filteredWerknemerLijst.getPredicate());
	}
	
	public void verwijderWerknemer(Werknemer werknemer) {
		werknemerDao.startTransaction();
		werknemerDao.delete(werknemer);
		werknemerDao.commitTransaction();
	}
	public ObservableList<Werknemer> haalWerknemersOp() {
		if (filteredWerknemerLijst == null) {
			filteredWerknemerLijst = 
					new FilteredList<>(FXCollections.observableArrayList(
							werknemerDao.findAll())
							,werknemer -> 
					werknemer.getGebruikerStatus() == GEBRUIKERSTATUS.ACTIEF);				
		}
		return FXCollections.unmodifiableObservableList(filteredWerknemerLijst);
	}
	public void pasFilterAan(String gebruikersnaam, String naam, String voornaam, String werknemerFunctie, Set<GEBRUIKERSTATUS> status) 
		{
		List<Predicate<Werknemer>> werknemerFilters = new ArrayList<>();

		if (gebruikersnaam != null && !gebruikersnaam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getGebruikersnaam().toLowerCase().contains(gebruikersnaam.toLowerCase()));		
		}

		if (naam != null && !naam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getNaam().toLowerCase().contains(naam.toLowerCase()));
		}

		if (voornaam != null && !voornaam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getVoornaam().toLowerCase().contains(voornaam.toLowerCase()));
		}

		if (status != null && (status.size() > 0 || status.size() >= GEBRUIKERSTATUS.values().length)) {
			werknemerFilters.add(werknemer -> status.contains(werknemer.getGebruikerStatus()));
		} 

		if (werknemerFunctie != null && !werknemerFunctie.isBlank()) {
			werknemerFilters.add(werknemer ->  werknemer.getRol().toString().toLowerCase().contains(werknemerFunctie.toLowerCase()));
		}

		Predicate<Werknemer> werknemerFilter = werknemerFilters.stream().reduce(Predicate::and).orElse(x -> true);
		filteredWerknemerLijst.setPredicate(werknemerFilter);
	}

}
