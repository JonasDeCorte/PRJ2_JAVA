package domein.controllers;

import domein.Contract;
import domein.Klant;
import domein.beheerders.ContractBeheerder;
import javafx.collections.ObservableList;

public class ContractController {
	private ContractBeheerder contractBeheerder;
	
	public ContractController() {
		contractBeheerder = new ContractBeheerder();
	}
	
	public ObservableList<Contract> getAllContracten() {
		return contractBeheerder.haalContractenOp();
	}
}
