package domein.beheerders;

import java.time.chrono.ChronoLocalDate;
import java.util.List;
import domein.Contract;
import domein.Statistiek;
import domein.Ticket;
import domein.dao.ContractDao;
import domein.dao.StatistiekDao;
import domein.dao.TicketDao;
import repository.ContractDaoJpa;
import repository.StatistiekDaoJpa;
import repository.TicketDaoJpa;

public class StatistiekBeheerder {

	private StatistiekDao statistiekDao;
	private TicketDao ticketDao;
	private ContractDao contractDao;
	private List<Ticket> tickets;
	private List<Contract> contracten;
	private StatistiekBeheerder(StatistiekDao statistiekDao, TicketDao ticketDao, ContractDao contractDao) {
		this.statistiekDao = statistiekDao;
		this.ticketDao = ticketDao;
		this.contractDao = contractDao;
	}

	public StatistiekBeheerder() {
		this(new StatistiekDaoJpa(), new TicketDaoJpa(), new ContractDaoJpa());
	}

	public List<Ticket> verwerkDataTicket(ChronoLocalDate startDatum, ChronoLocalDate eindDatum) {
		Statistiek statsTicket = new Statistiek("StatsTicket");
		return statsTicket.verwerkDataTicket(startDatum, eindDatum, getTickets());
		
	}
	public List<Contract> verwerkDataContracten(ChronoLocalDate startDatum, ChronoLocalDate eindDatum) {
		Statistiek StatsContracten = new Statistiek("StatsContracten");
		return StatsContracten.verwerkDataContract(startDatum, eindDatum, getContracten());
		
	}
	public List<Ticket> getTickets() {
		if (tickets == null) {
			tickets = ticketDao.findAll();
		}
		return tickets;
	}
	public List<Contract> getContracten() {
		if (contracten == null) {
			contracten = contractDao.findAll();
		}
		return contracten;
	}
}
