package domein.dao;

import java.util.List;

import domein.Klant;
import domein.Rapport;
import domein.Ticket;


public interface RapportDao extends GenericDao<Rapport> {
	List<Rapport> geefRapportenVanGeselecteerdTicket(Ticket ticket);
}
