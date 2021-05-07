package domein.beheerders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import domein.Bedrijf;
import domein.dao.BedrijfDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.BedrijfDaoJpa;

public class BedrijfBeheerder {
	private BedrijfDao bedrijfDao;
	private FilteredList<Bedrijf> filteredBedrijfLijst;
	public BedrijfBeheerder(BedrijfDao bedrijfDao) {
		this.bedrijfDao = bedrijfDao;
	}
	public BedrijfBeheerder() {
		// TODO Auto-generated constructor stub
		this(new BedrijfDaoJpa());
	}
	public boolean bestaatBedrijf(String bedrijfsnaam) {
		return bedrijfDao.bestaatBedrijf(bedrijfsnaam);
	}
	
	public ObservableList<Bedrijf> haalBedrijvenOp() {
		if (filteredBedrijfLijst == null) {
			filteredBedrijfLijst = 
					new FilteredList<>(FXCollections.observableArrayList(bedrijfDao.findAll()));								
		}
		return FXCollections.unmodifiableObservableList(filteredBedrijfLijst);
	}
	public void voegBedrijfToe(Bedrijf bedrijf) {
		if(!bestaatBedrijf(bedrijf.getBedrijfsnaam())) {
			bedrijfDao.startTransaction();
		bedrijfDao.insert(bedrijf);
		bedrijfDao.commitTransaction();
		}
		else {
			throw new IllegalArgumentException("bedrijf bestaat al.");
		}
		filteredBedrijfLijst = new FilteredList<>(FXCollections.observableArrayList(bedrijfDao.findAll()),filteredBedrijfLijst.getPredicate());
		
	}
	public void wijzigBedrijf(Bedrijf bedrijf, String origineleBedrijfsnaam) {
		if(bestaatBedrijf(origineleBedrijfsnaam)) {
			bedrijfDao.startTransaction();
		bedrijfDao.update(bedrijf);
		bedrijfDao.commitTransaction();
		}else {
			throw new IllegalArgumentException("bedrijf bestaat niet.");
		}
		filteredBedrijfLijst = new FilteredList<>(FXCollections.observableArrayList(bedrijfDao.findAll()),filteredBedrijfLijst.getPredicate());
		
		
	}
	public void pasFilterAan(String bedrijfsnaam,String land, String Gemeente) {
		// houdt de filters bij
		List<Predicate<Bedrijf>> filtersLijst = new ArrayList<>();
		if (bedrijfsnaam != null && !bedrijfsnaam.isBlank()) {
			filtersLijst.add(bedrijf -> bedrijf.getBedrijfsnaam().toLowerCase().contains(bedrijfsnaam.toLowerCase()));		
		}
		if (land != null && !land.isBlank()) {
			filtersLijst.add(bedrijf -> bedrijf.getAdres().getLand().toLowerCase().contains(land.toLowerCase()));
		}
		if (Gemeente != null && !Gemeente.isBlank()) {
			filtersLijst.add(bedrijf ->  bedrijf.getAdres().getGemeente().toLowerCase().contains(Gemeente.toLowerCase()));
		}
		Predicate<Bedrijf> filterBedrijfLijst = filtersLijst.stream().reduce(Predicate::and).orElse(x -> true);
		// predikaat toepassen op de filteredklantlijst 
		filteredBedrijfLijst.setPredicate(filterBedrijfLijst);
	}
}
