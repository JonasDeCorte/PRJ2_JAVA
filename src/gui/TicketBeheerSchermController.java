package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import domein.Ticket;
import domein.controllers.AanmeldController;
import domein.controllers.TicketController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import resourcebundle.Taal;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TicketBeheerSchermController  extends HBox{
	@FXML
	private Label lblFilters;
	@FXML
	private CheckBox chkAangemaakteTickets;
	@FXML
	private CheckBox chkInActieveTickets;
	@FXML
	private CheckBox chkAfgehandeldeTickets;
	@FXML
	private TextField txfFilterTitel;
	@FXML
	private TextField txfFilterDatum;
	@FXML
	private TextField txfFilterContract;
	@FXML
	private TextField txfFilterStatus;
	@FXML
	private Button btnClearFilters;
	@FXML
	private TableView<Ticket> tblTickets;
	@FXML
	private TableColumn<Ticket,Integer> tbcTicketNr;
	@FXML
	private TableColumn<Ticket,String> tbcTitel;
	@FXML
	private TableColumn<Ticket,String> tbcDatumAangemaakt;
	@FXML
	private TableColumn<Ticket,String> tbcContract;
	@FXML
	private TableColumn<Ticket,String> tbcStatus;
	@FXML
	private CheckBox chkGeannuleerdeTickets;
	@FXML
	private Label lblTicketgegevens;
	@FXML
	private Label lblTicketNr;
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblDatumAangemaakt;
	@FXML
	private Label lblOmschrijving;
	@FXML
	private Label lblOplossing;
	@FXML
	private Label lblContract;
	@FXML
	private Label lblTicketType;
	@FXML
	private Label lblRapport;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblOpmerkingen;
	
	
	@FXML
	private TextArea txaOplossing;
	@FXML
	private TextArea txaOmschrijving;
	
	
	@FXML
	private TextField txfTicketNr;
	@FXML
	private TextField txfTitel;
	
	
	@FXML
	private TextField txfRapport;
	@FXML
	private TextField txfStatus;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private TextArea txaOpmerkingen;
	@FXML
	private ComboBox cbTicketType;
	@FXML
	private ComboBox cbContract;
	@FXML
	private Label lblDatumAfgehandeld;
	@FXML
	private TextField txfDatumAfgehandeld;
	@FXML
	private TextField txfDatumAangemaakt;
	
	
	private final TicketController ticketController;
	

	public TicketBeheerSchermController() {
		
		this.ticketController = new TicketController(AanmeldController.getAangemeldeWerknemer());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    
	    initializeGUIComponenten();	
	    TicketTabelInvullen();
	}
	private void TicketTabelInvullen() {
	 	tbcTicketNr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getTicketnummer()).asObject());
	    tbcTitel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitel()));
        tbcContract.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getContract().getTitel()));
        tbcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicketStatus().toString()));
        tbcDatumAangemaakt.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getDatumAangemaakt().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        tblTickets.setItems(ticketController.getTicketsLijst());
        tblTickets.refresh();
}
	
	private void initializeGUIComponenten() {
		// TODO Auto-generated method stub
		
		lblFilters.setText(Taal.geefTekst("filters"));
		chkAangemaakteTickets.setText(Taal.geefTekst("aangemaakteTickets"));
		chkInActieveTickets.setText(Taal.geefTekst("actieveTickets"));
		chkAfgehandeldeTickets.setText(Taal.geefTekst("afgehandeldeTickets"));
		txfFilterTitel.setPromptText(Taal.geefTekst("titel"));
		txfFilterDatum.setPromptText(Taal.geefTekst("datum"));
		txfFilterContract.setPromptText(Taal.geefTekst("contract"));
		txfFilterStatus.setPromptText(Taal.geefTekst("status"));
		btnClearFilters.setText(Taal.geefTekst("leegmaken"));
		tbcTicketNr.setText(Taal.geefTekst("ticketNr"));
		tbcTitel.setText(Taal.geefTekst("titel"));
		tbcDatumAangemaakt.setText(Taal.geefTekst("datumAangemaakt"));
		tbcContract.setText(Taal.geefTekst("contract"));
		tbcStatus.setText(Taal.geefTekst("status"));
		chkGeannuleerdeTickets.setText(Taal.geefTekst("geannuleerdeTickets"));
		lblTicketgegevens.setText(Taal.geefTekst("ticketGegevens"));            
		lblTicketNr.setText(Taal.geefTekst("ticketNr"));
		lblTitel.setText(Taal.geefTekst("titel"));
		lblDatumAangemaakt.setText(Taal.geefTekst("datumAangemaakt"));
		lblOmschrijving.setText(Taal.geefTekst("omschrijving"));              
		lblOplossing.setText(Taal.geefTekst("oplossing"));							
		lblContract.setText(Taal.geefTekst("contract"));
		lblTicketType.setText(Taal.geefTekst("ticketType"));
		lblRapport.setText(Taal.geefTekst("rapport"));
		lblStatus.setText(Taal.geefTekst("status"));
		lblOpmerkingen.setText(Taal.geefTekst("opmerkingen"));
		btnClearFilters1.setText(Taal.geefTekst("leegmaken"));
		



		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// Event Listener on CheckBox[#chkAangemaakteTickets].onAction
	@FXML
	public void toonAangemaakte(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkInActieveTickets].onAction
	@FXML
	public void toonActieve(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkAfgehandeldeTickets].onAction
	@FXML
	public void toonAfgehandelde(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterTitel].onKeyReleased
	@FXML
	public void filterGebruiker(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterDatum].onKeyReleased
	@FXML
	public void filterVoornaam(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterContract].onKeyReleased
	@FXML
	public void filterNaam(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterStatus].onKeyReleased
	@FXML
	public void filterBedrijf(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnClearFilters].onAction
	@FXML
	public void clear(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkGeannuleerdeTickets].onAction
	@FXML
	public void toonGeannuleerde(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnClearFilters1].onAction
	@FXML
	public void clearKlantgegevens(ActionEvent event) {
		// TODO Autogenerated
	}
}
