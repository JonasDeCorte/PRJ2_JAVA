package domein.dao;

import domein.ContractType;

public interface ContractTypeDao extends GenericDao<ContractType> {
	boolean bestaatContractType(String naam);
	int getTicketsInBehandelingVanActiefContractType(String naam);
}
