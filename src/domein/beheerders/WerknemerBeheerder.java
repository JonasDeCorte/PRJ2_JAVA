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
		werknemerDao.startTransaction();
		werknemerDao.insert(werknemer);
		werknemerDao.commitTransaction();
		//filteredWerknemerLijst = new FilteredList<>(FXCollections.observableArrayList(werknemerDao.findAll()),filteredWerknemerLijst.getPredicate());
	}
	
	public void wijzigWerknemer(Werknemer werknemer) {
		werknemerDao.startTransaction();
		werknemerDao.update(werknemer);
		werknemerDao.commitTransaction();
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
	public void pasFilterAan(int personeelsNummer, String gebruikersnaam, String naam, String voornaam,	List<GEBRUIKERSTATUS> gebruikerStatus, List<WERKNEMERROL> werknemerRol) 
		{
		List<Predicate<Werknemer>> werknemerFilters = new ArrayList<>();

		if (personeelsNummer >= 0) {
			werknemerFilters.add(werknemer -> Integer.toString(werknemer.getPersoneelsnummer()).startsWith(Integer.toString(personeelsNummer)));
		}

		if (gebruikersnaam != null && !gebruikersnaam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getGebruikersnaam().toLowerCase().contains(gebruikersnaam.toLowerCase()));		
		}

		if (naam != null && !naam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getNaam().toLowerCase().contains(naam.toLowerCase()));
		}

		if (voornaam != null && !voornaam.isBlank()) {
			werknemerFilters.add(werknemer -> werknemer.getVoornaam().toLowerCase().contains(voornaam.toLowerCase()));
		}

		if (gebruikerStatus != null && (gebruikerStatus.size() > 0 || gebruikerStatus.size() >= GEBRUIKERSTATUS.values().length)) {
			werknemerFilters.add(werknemer -> gebruikerStatus.contains(werknemer.getGebruikerStatus()));
		}

		if (werknemerRol != null && (werknemerRol.size() > 0 || werknemerRol.size() >= WERKNEMERROL.values().length)) {
			werknemerFilters.add(werknemer -> werknemerRol.contains(werknemer.getRol()));
		}

		Predicate<Werknemer> werknemerFilter = werknemerFilters.stream().reduce(Predicate::and).orElse(x -> true);
		filteredWerknemerLijst.setPredicate(werknemerFilter);
	}

}
