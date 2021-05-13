package domein.beheerders;

import java.util.ArrayList;
import java.util.Comparator;
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
import javafx.collections.transformation.SortedList;
import repository.AanmeldPogingDaoJpa;
import repository.WerknemerDaoJpa;

public class WerknemerBeheerder {
	private WerknemerDao werknemerDao;
	private AanmeldPogingDao aanmeldPogingDao;
	private FilteredList<Werknemer> filteredWerknemerLijst;
	private SortedList<Werknemer>sortedWerknemerList;
	private final Comparator<Werknemer> byLastName = (p1, p2) ->
	p1.getNaam().compareToIgnoreCase(p2.getNaam());
	private final Comparator<Werknemer> byEmail = (p1, p2) ->
	p1.getEmailadres().compareToIgnoreCase(p2.getEmailadres());
	private final Comparator<Werknemer> byFirstName = (p1, p2) ->
	p1.getVoornaam().compareToIgnoreCase(p2.getVoornaam());
	private final Comparator<Werknemer> sortOrder =
			byFirstName.thenComparing(byLastName).
			thenComparing(byEmail);
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
	
	public boolean bestaatPersoneelsnummer(int personeelsnummer) {
		return werknemerDao.bestaatPersoneelsnummer(personeelsnummer);
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
		sortedWerknemerList = new SortedList<>(filteredWerknemerLijst, sortOrder);
	}
	
	public void wijzigWerknemer(Werknemer werknemer, String origineleGebruikersnaam) {
		if(bestaatWerknemer(origineleGebruikersnaam)) {
		werknemerDao.startTransaction();
		werknemerDao.update(werknemer);
		werknemerDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("werknemer bestaat niet.");
			}
		filteredWerknemerLijst = new FilteredList<>(FXCollections.observableArrayList(werknemerDao.findAll()),filteredWerknemerLijst.getPredicate());
		sortedWerknemerList = new SortedList<>(filteredWerknemerLijst, sortOrder);
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
		sortedWerknemerList = new SortedList<>(filteredWerknemerLijst, sortOrder);
		return sortedWerknemerList;
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
