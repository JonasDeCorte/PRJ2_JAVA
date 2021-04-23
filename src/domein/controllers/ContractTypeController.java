package domein.controllers;

import domein.ContractType;
import domein.beheerders.ContractTypeBeheerder;

public class ContractTypeController {
	private ContractTypeBeheerder contractTypeBeheerder;
	public ContractTypeController() {
		this.contractTypeBeheerder = new ContractTypeBeheerder();
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
}