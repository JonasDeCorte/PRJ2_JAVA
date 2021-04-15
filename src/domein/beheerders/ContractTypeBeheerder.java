package domein.beheerders;

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
}
