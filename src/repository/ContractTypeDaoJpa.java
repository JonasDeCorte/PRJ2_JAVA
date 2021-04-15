package repository;

import domein.ContractType;
import domein.dao.ContractTypeDao;

public class ContractTypeDaoJpa  extends GenericDaoJpa<ContractType> implements ContractTypeDao {
	public ContractTypeDaoJpa() {
		super(ContractType.class);
	}
}
