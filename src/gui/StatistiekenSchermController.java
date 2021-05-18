package gui;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domein.Klant;
import domein.TicketType;
import domein.controllers.AanmeldController;
import domein.controllers.ContractController;
import domein.controllers.GebruikerController;
import domein.controllers.TicketController;
import domein.controllers.TicketTypeController;
import domein.enumerations.CONTRACTSTATUS;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.TICKETSTATUS;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import resourcebundle.Observer;

public class StatistiekenSchermController extends GridPane implements Observer{
	
	@FXML
	private Label lblKlanten;
	@FXML
	private Label lblWerknemers;
	@FXML
	private Label lblTickets;
	@FXML
	private Label lblContracten;
	@FXML
	private BarChart<String, Integer> chrTickets;
	
	private final GebruikerController gebruikerController;
	private final TicketController ticketController;
	private final ContractController contractController;
	private final TicketTypeController ticketTypeController;
public StatistiekenSchermController() {
	gebruikerController = new GebruikerController();
	ticketController = new TicketController(AanmeldController.getAangemeldeWerknemer());
	contractController = new ContractController();
	ticketTypeController = new TicketTypeController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiekenScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    initializeData();
	}

	private void initializeData() {
		int actieveKlanten = gebruikerController.getAllKlanten().size();
		Set<GEBRUIKERSTATUS> status = new HashSet<>();
        	status.add(GEBRUIKERSTATUS.ACTIEF);
        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
		gebruikerController.pasFilterAanKlant("", "", "", "", status);
		int alleKlanten = gebruikerController.getAllKlanten().size();
		lblKlanten.setText(String.format("Totaal aantal klanten:%d%nActief:%d", alleKlanten,actieveKlanten));
		int actieveWerknemers = gebruikerController.getAllWerknemer().size();
		Set<GEBRUIKERSTATUS> status1 = new HashSet<>();
        	status.add(GEBRUIKERSTATUS.ACTIEF);
        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
		gebruikerController.pasFilterAanWerknemer("", "", "", "", status);
		int alleWerknemers = gebruikerController.getAllKlanten().size();
		lblWerknemers.setText(String.format("Totaal aantal Werknemers:%d%nActief:%d", alleWerknemers,actieveWerknemers));
		int alleTickets = ticketController.getTicketsLijst().size();
		int aangemaakteTickets = ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.AANGEMAAKT))).collect(Collectors.toList()).size();
		int inbehandelingTickets =ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.IN_BEHANDELING))).collect(Collectors.toList()).size();
		int afgehandeldeTickets =ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.AFGEHANDELD))).collect(Collectors.toList()).size();
		int geannuleerdeTickets = ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.GEANNULEERD))).collect(Collectors.toList()).size();
		lblTickets.setText(String.format("Totaal Aantal Tickets: %d %n Aangemaakt: %d%n In Behandeling: %d%n Afgehandeld: %d%n Geannuleerd: %d",
				alleTickets,aangemaakteTickets,inbehandelingTickets,afgehandeldeTickets,geannuleerdeTickets));
		int totaleContracten = contractController.getAllContracten().size();
		int InBehandelingContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.IN_BEHANDELING)).collect(Collectors.toList()).size();
		int lopendeContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.LOPEND)).collect(Collectors.toList()).size();
		int AfgeslotenContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.BEËINDIGD)).collect(Collectors.toList()).size();
		lblContracten.setText(String.format("Totaal Aantal Contracten: %d%n In Behandeling: %d%n Lopend: %d%n Afgesloten: %d%n ", totaleContracten,InBehandelingContracten,lopendeContracten,AfgeslotenContracten));
		
		CategoryAxis xAxis    = new CategoryAxis();
		xAxis.setLabel("TicketType");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Aantal tickets");
		
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("Openstaande tickets");
		for(TicketType t : ticketTypeController.haalTicketTypesOp()) {
			int aantalTickets = ticketController.getTicketsLijst()
					.stream().filter(ticket->ticket.getTicketType().equals(t))
					.filter(ticket->ticket.getTicketStatus() ==TICKETSTATUS.AANGEMAAKT || ticket.getTicketStatus() == TICKETSTATUS.IN_BEHANDELING)
					.collect(Collectors.toList()).size();
			
			dataSeries1.getData().add(new XYChart.Data(t.toString(), aantalTickets));
		}
		chrTickets.getData().add(dataSeries1);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
