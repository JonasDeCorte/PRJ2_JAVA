package domein.beheerders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import domein.ContractType;
import domein.dao.ContractTypeDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import repository.ContractTypeDaoJpa;

public class ContractTypeBeheerder {
	private ContractTypeDao contractTypeDao;
	private FilteredList<ContractType> filteredContractTypeLijst;
	private ContractTypeBeheerder(ContractTypeDao contractTypeDao) {
		this.contractTypeDao = contractTypeDao;
	}

	public ContractTypeBeheerder() {
		this(new ContractTypeDaoJpa());
	}

	public ObservableList<ContractType> haalContractTypesOp() {
		if (filteredContractTypeLijst == null) {
			filteredContractTypeLijst = 
					new FilteredList<>(FXCollections.observableArrayList(contractTypeDao.findAll()));
								
		}
		return FXCollections.unmodifiableObservableList(filteredContractTypeLijst);
	}
	// toevoegen van een contract type
	public void addContractType(ContractType contractType) {
		if (!bestaatContractType(contractType.getNaam())) {
			contractTypeDao.startTransaction();
			contractTypeDao.insert(contractType);
			contractTypeDao.commitTransaction();
		} else {
			throw new IllegalArgumentException("ContractType bestaat al.");
		}
		filteredContractTypeLijst = new FilteredList<>(FXCollections.observableArrayList(contractTypeDao.findAll()),filteredContractTypeLijst.getPredicate());

	}

	// editeren van een contracttype
	public void editContractType(ContractType contractType) {
		if (bestaatContractType(contractType.getNaam())) {
			contractTypeDao.startTransaction();
			contractTypeDao.update(contractType);
			contractTypeDao.commitTransaction();
		} else {
			throw new IllegalArgumentException("ContractType bestaat niet.");
		}
		filteredContractTypeLijst = new FilteredList<>(FXCollections.observableArrayList(contractTypeDao.findAll()),filteredContractTypeLijst.getPredicate());
	}

	// bestaat het contract type al?
	public boolean bestaatContractType(String naam) {
		return contractTypeDao.bestaatContractType(naam);
	}

	// get aantal actieve tickets die per contract type in behandeling zijn
	public int getTicketsInBehandelingVanActiefContractType(String naam) {
		return contractTypeDao.getTicketsInBehandelingVanActiefContractType(naam);
	}
	public void pasFilterAan(String naam, boolean statusActiefTrue, boolean StatusActiefFalse) {
		// houdt de filters bij
		List<Predicate<ContractType>> filtersLijst = new ArrayList<>();
		if (naam != null && !naam.isBlank()) {
			filtersLijst.add(ContractType -> ContractType.getNaam().toLowerCase().contains(naam.toLowerCase()));
		}
		if(statusActiefTrue) {
			filtersLijst.add(ContractType -> ContractType.isStatus() == true);
		}
		if(StatusActiefFalse) {		
			filtersLijst.add(ContractType -> ContractType.isStatus() == false);
		}	
		Predicate<ContractType> filterContractTypeLijst = filtersLijst.stream().reduce(Predicate::and).orElse(x -> true);
		filteredContractTypeLijst.setPredicate(filterContractTypeLijst);
	}
}
