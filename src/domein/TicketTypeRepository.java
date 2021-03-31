package domein;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Persistentie.mapperExample;

public class TicketTypeRepository {
	private final mapperExample mapper;
private List<TicketType> ticketTypes;
	
	//Constructor
	public TicketTypeRepository() throws SQLException{
		mapper = new mapperExample();
		ticketTypes = new ArrayList<>();
	}
	//Geeft lijst met spelers in db terug
	public List<TicketType> geefTicketTypes(){
		return ticketTypes = mapper.geefTicketTypes();
	}
}
