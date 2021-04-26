package repository;

import domein.Contract;
import domein.dao.ContractDao;

public class ContractDaoJpa extends GenericDaoJpa<Contract> implements ContractDao {

	public ContractDaoJpa() {
		super(Contract.class);
	}

}
