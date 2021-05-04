package domein.controllers;

import domein.ContractType;
import domein.beheerders.ContractTypeBeheerder;
import javafx.collections.ObservableList;

public class ContractTypeController {
	private ContractTypeBeheerder contractTypeBeheerder;
	public ContractTypeController() {
		this.contractTypeBeheerder = new ContractTypeBeheerder();
	}
	public ObservableList<ContractType> getAllContractTypes() {
		return contractTypeBeheerder.haalContractTypesOp();
	}
	public int getTicketsInBehandelingVanActiefContractType(String naam) {
		return contractTypeBeheerder.getTicketsInBehandelingVanActiefContractType(naam);
	}
	public boolean bestaatContractType(String naam) {
		return contractTypeBeheerder.bestaatContractType(naam);
	}

	public void addContractType(ContractType contractType) {
		contractTypeBeheerder.addContractType(contractType);
	}

	public void editContractType(ContractType contractType) {
		contractTypeBeheerder.editContractType(contractType);
	}
	public void pasFilterAanContractType( String contractTypeNaam,Boolean statusActiefTrue, Boolean statusActiefFalse)
	{
		contractTypeBeheerder.pasFilterAan(contractTypeNaam,statusActiefTrue,statusActiefFalse );
	}
}