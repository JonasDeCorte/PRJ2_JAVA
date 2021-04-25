package domein.beheerders;

import domein.ContractType;
import domein.dao.ContractTypeDao;
import repository.ContractTypeDaoJpa;

public class ContractTypeBeheerder {
	private ContractTypeDao contractTypeDao;

	private ContractTypeBeheerder(ContractTypeDao contractTypeDao) {
		this.contractTypeDao = contractTypeDao;
	}

	public ContractTypeBeheerder() {
		this(new ContractTypeDaoJpa());
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

	}

	// bestaat het contract type al?
	public boolean bestaatContractType(String naam) {
		return contractTypeDao.bestaatContractType(naam);
	}

	// get aantal actieve tickets die per contract type in behandeling zijn
	public int getTicketsInBehandelingVanActiefContractType(String naam) {
		return contractTypeDao.getTicketsInBehandelingVanActiefContractType(naam);
	}
}
