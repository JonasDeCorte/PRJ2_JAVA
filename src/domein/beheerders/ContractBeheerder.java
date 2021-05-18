package domein.beheerders;

import java.util.List;

import domein.Contract;
import domein.ContractType;
import domein.Klant;
import domein.dao.ContractDao;
import domein.dao.KlantDao;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import repository.ContractDaoJpa;

public class ContractBeheerder {
	private ContractDao contractDao;
	
	public ContractBeheerder() {
		this.contractDao = new ContractDaoJpa() ;
	}
	
	public ContractType haalContractTypeOp() {
		throw new IllegalArgumentException();
	}
	public ObservableList<Contract> haalContractenOp() {
		return FXCollections.observableArrayList(contractDao.findAll());	
	}
	
	public void voegContractTypeToe() {
		
	}
	
	public void wijzigContractType() {
		
	}
	
	public void verwijderContractType() {
		
	}
}