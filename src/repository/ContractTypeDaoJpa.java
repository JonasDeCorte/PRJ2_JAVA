package repository;

import java.util.List;

import javax.persistence.NoResultException;

import domein.Ticket;
import domein.enumerations.TICKETSTATUS;
import domein.ContractType;
import domein.dao.ContractTypeDao;

public class ContractTypeDaoJpa  extends GenericDaoJpa<ContractType> implements ContractTypeDao {
	public ContractTypeDaoJpa() {
		super(ContractType.class);
	}
	@Override
	public boolean bestaatContractType(String name) {
		// TODO Auto-generated method stub
		try {
			em.createNamedQuery("ContractType.bestaatContractType", ContractType.class).setParameter("naam", name)
					.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	@Override
	public int getTicketsInBehandelingVanActiefContractType(String name) {
		// TODO Auto-generated method stub
		List<Ticket> tickets = em.createNamedQuery("Ticket.getTicketsInBehandelingVanActiefContractType", Ticket.class)
				.setParameter("status", TICKETSTATUS.AFGEHANDELD).getResultList();
		int aantal = (int) tickets.stream().filter(contract -> contract.getContract().getContracttype().getNaam().equals(name)).count();
		return aantal;
	}
}
