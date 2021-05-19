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
import resourcebundle.Taal;

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
	
	@FXML
	private Label lblOpenstaandeTickets;
	
	private final GebruikerController gebruikerController;
	private final TicketController ticketController;
	private final ContractController contractController;
	private final TicketTypeController ticketTypeController;
	
	private final XYChart.Series dataSeries1;
public StatistiekenSchermController() {
	gebruikerController = new GebruikerController();
	ticketController = new TicketController(AanmeldController.getAangemeldeWerknemer());
	contractController = new ContractController();
	ticketTypeController = new TicketTypeController();
	dataSeries1 = new XYChart.Series();
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StatistiekenScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    chrTickets.getData().add(dataSeries1);
	    initializeData();
	    grafiekInitializeren();
	}

	private void initializeData() {
		lblOpenstaandeTickets.setText(Taal.geefTekst("openstaandeTicketsPerType"));
		dataSeries1.setName(Taal.geefTekst("openstaandeTickets"));
		
		int actieveKlanten = gebruikerController.getAllKlanten().size();
		Set<GEBRUIKERSTATUS> status = new HashSet<>();
        	status.add(GEBRUIKERSTATUS.ACTIEF);
        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
		gebruikerController.pasFilterAanKlant("", "", "", "", status);
		int alleKlanten = gebruikerController.getAllKlanten().size();
		status.remove(GEBRUIKERSTATUS.ACTIEF);
		gebruikerController.pasFilterAanKlant("", "", "", "", status);
		int inactieveKlanten = gebruikerController.getAllKlanten().size();
		lblKlanten.setText(String.format("%s: %d%n%s: %d%n%s:%d",Taal.geefTekst("totaalKlanten"), alleKlanten,Taal.geefTekst("actief"),actieveKlanten,Taal.geefTekst("nietActief"),inactieveKlanten));
		int actieveWerknemers = gebruikerController.getAllWerknemer().size();
		Set<GEBRUIKERSTATUS> status1 = new HashSet<>();
        	status.add(GEBRUIKERSTATUS.ACTIEF);
        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
		gebruikerController.pasFilterAanWerknemer("", "", "", "", status);
		int alleWerknemers = gebruikerController.getAllWerknemer().size();
		status.remove(GEBRUIKERSTATUS.ACTIEF);
		gebruikerController.pasFilterAanWerknemer("", "", "", "", status);
		int inactieveWerknemers = gebruikerController.getAllWerknemer().size();
		lblWerknemers.setText(String.format("%s: %d%n%s: %d%n%s: %d",Taal.geefTekst("totaalWerknemers"), alleWerknemers,Taal.geefTekst("actief"),actieveWerknemers,Taal.geefTekst("nietActief"),inactieveWerknemers));
		int alleTickets = ticketController.getTicketsLijst().size();
		int aangemaakteTickets = ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.AANGEMAAKT))).collect(Collectors.toList()).size();
		int inbehandelingTickets =ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.IN_BEHANDELING))).collect(Collectors.toList()).size();
		int afgehandeldeTickets =ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.AFGEHANDELD))).collect(Collectors.toList()).size();
		int geannuleerdeTickets = ticketController.getTicketsLijst().stream().filter((t->t.getTicketStatus().equals(TICKETSTATUS.GEANNULEERD))).collect(Collectors.toList()).size();
		lblTickets.setText(String.format("%s: %d %n %s: %d%n %s: %d%n %s: %d%n %s: %d",
				Taal.geefTekst("totaalTickets"),alleTickets,Taal.geefTekst("aangemaakt"),aangemaakteTickets,Taal.geefTekst("inBehandeling"),inbehandelingTickets,Taal.geefTekst("afgehandeld"),afgehandeldeTickets,Taal.geefTekst("geannuleerd"),geannuleerdeTickets));
		int totaleContracten = contractController.getAllContracten().size();
		int InBehandelingContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.IN_BEHANDELING)).collect(Collectors.toList()).size();
		int lopendeContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.LOPEND)).collect(Collectors.toList()).size();
		int AfgeslotenContracten = contractController.getAllContracten().stream().filter(c->c.getContractstatus().equals(CONTRACTSTATUS.BEËINDIGD)).collect(Collectors.toList()).size();
		lblContracten.setText(String.format("%s: %d%n %s: %d%n %s: %d%n %s: %d%n ",
				Taal.geefTekst("totaalContracten"),totaleContracten,Taal.geefTekst("inBehandeling"),InBehandelingContracten,Taal.geefTekst("lopend"),lopendeContracten,Taal.geefTekst("afgesloten"),AfgeslotenContracten));
		}
	private void grafiekInitializeren() {
		dataSeries1.getData().clear();
		
		for(TicketType t : ticketTypeController.haalTicketTypesOp()) {
			int aantalTickets = ticketController.getTicketsLijst()
					.stream().filter(ticket->ticket.getTicketType().equals(t))
					.filter(ticket->ticket.getTicketStatus() ==TICKETSTATUS.AANGEMAAKT || ticket.getTicketStatus() == TICKETSTATUS.IN_BEHANDELING)
					.collect(Collectors.toList()).size();
			
			dataSeries1.getData().add(new XYChart.Data<String, Integer>(t.toString(), aantalTickets));
		}
		
		
	}

	@Override
	public void update() {
		initializeData();
	}
}
