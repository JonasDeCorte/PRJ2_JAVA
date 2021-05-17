package domein.beheerders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import domein.Rapport;
import domein.Ticket;
import domein.dao.RapportDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.RapportDaoJpa;

public class RapportBeheerder {
	private RapportDao rapportDao;
	private FilteredList<Rapport> filteredRapportLijst;
	//private Ticket geselecteerdeTicket;
	
	private RapportBeheerder(RapportDao rapportDao) {
		this.rapportDao = rapportDao;
	}
	public  RapportBeheerder(/*Ticket ticket*/) {
		this(new RapportDaoJpa());
	//this.geselecteerdeTicket = ticket;
	}
	public void voegRapportToe(Rapport rapport) {	
		if(rapportDao.exists(rapport.getRapportNummer()))
		{
			throw new IllegalArgumentException("Rapport bestaat al.");
		} else {
		rapportDao.startTransaction();
		rapportDao.insert(rapport);
		rapportDao.commitTransaction();
		}
		haalAlleRapportenOp();
	}

	public void pasRapportAan(Rapport rapport) {
		if(rapportDao.exists(rapport.getRapportNummer())) {		
				rapportDao.startTransaction();
				rapportDao.update(rapport);
				rapportDao.commitTransaction();
			} 
		else {
			throw new IllegalArgumentException("kies het correct ticket.");
		}
		haalAlleRapportenOp();
	}

	public void pasFilterAan( String rapportnaam, String ticket) {
		List<Predicate<Rapport>> filters = new ArrayList<>();

		if (rapportnaam != null && !rapportnaam.isBlank()) {
			filters.add(rapport -> rapport.getRapportNaam().toLowerCase().contains(rapportnaam.toLowerCase()));
		}
		if (ticket != null && !ticket.isBlank()) {
			filters.add(rapport -> rapport.getTicket().getTitel().toLowerCase().contains(ticket.toLowerCase()));
		}
		Predicate<Rapport> filter = filters.stream().reduce(Predicate::and).orElse(x -> true);
		filteredRapportLijst.setPredicate(filter);
	}

	public ObservableList<Rapport> getRapportLijst() {
		if (filteredRapportLijst == null) {
			haalAlleRapportenOp();
		}
		return FXCollections.unmodifiableObservableList(filteredRapportLijst);
	}
	
	private void haalAlleRapportenOp() {
		Predicate<Rapport> filter;
		if (filteredRapportLijst == null) {
			filter = e -> true;
		} else {
			filter =  (Predicate<Rapport>) filteredRapportLijst.getPredicate();
		}
		/*if(geselecteerdeTicket != null) {
			filteredRapportLijst = new FilteredList<>(
					FXCollections.observableArrayList(rapportDao.geefRapportenVanGeselecteerdTicket(geselecteerdeTicket)), filter);
		}*/
		//else {
			filteredRapportLijst = new FilteredList<>(FXCollections.observableArrayList(rapportDao.findAll()), filter);
		//}
	
	}
}