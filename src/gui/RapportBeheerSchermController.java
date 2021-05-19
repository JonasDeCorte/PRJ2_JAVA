package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import domein.Bedrijf;
import domein.Rapport;
import domein.Ticket;
import domein.controllers.AanmeldController;
import domein.controllers.BedrijfsBeheerController;
import domein.controllers.RapportController;
import domein.controllers.TicketController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

import javafx.scene.input.KeyEvent;

import javafx.scene.control.TableView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import resourcebundle.Observer;
import resourcebundle.Taal;
import javafx.scene.control.TableColumn;

public class RapportBeheerSchermController extends HBox implements Observer {
	@FXML
	private Label lblFilters;
	@FXML
	private TextField txfFilterNaam;
	@FXML
	private Button btnClearFilters;
	@FXML
	private TableView<Rapport> tblRapporten;
	@FXML
	private TableColumn<Rapport,Integer> tbcRapportNr;
	@FXML
	private TableColumn<Rapport,String> tbcNaam;
	@FXML
	private TableColumn<Rapport,String> tbcBeschrijving;
	@FXML
	private TableColumn<Rapport,String> tbcOplossing;
	@FXML
	private TableColumn<Rapport,String> tbcTicket;
	@FXML
	private TextField txfFilterTicket;
	@FXML
	private GridPane grdTicketGegevens;
	@FXML
	private Label lblTicketgegevens;
	@FXML
	private Label lblRapportNr;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblBeschrijving;
	@FXML
	private Label lblOplossing;
	@FXML
	private TextArea txaOplossing;
	@FXML
	private TextField txfRapportNr;
	@FXML
	private TextField txfNaam;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private TextArea txaBeschrijving;
	@FXML
	private Button btnRapportWijzigen;
	@FXML
	private Label lblTicket;
	@FXML
	private ComboBox<Ticket> cbTicket;

	private final RapportController rapportController;
	private final TicketController ticketController;
	private Rapport geselecteerdeRapport;
	
	public RapportBeheerSchermController() {
		this.rapportController = new RapportController();
		this.ticketController = new TicketController(AanmeldController.getAangemeldeWerknemer());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RapportBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    
	    initializeGUIComponenten();
	    rapportTabelInvullen();
	}

	private void initializeGUIComponenten() {
		lblFilters.setText(Taal.geefTekst("filters"));
		txfFilterNaam.setText(Taal.geefTekst("naam"));
		txfFilterTicket.setText(Taal.geefTekst("ticket"));
		btnClearFilters.setText(Taal.geefTekst("leegmaken"));
		tbcRapportNr.setText(Taal.geefTekst("rapportNummer"));
		tbcNaam.setText(Taal.geefTekst("naam"));
		tbcBeschrijving.setText(Taal.geefTekst("beschrijving"));
		tbcOplossing.setText(Taal.geefTekst("oplossing"));
		tbcTicket.setText(Taal.geefTekst("ticket"));
		btnClearFilters1.setText(Taal.geefTekst("leegmaken"));
		lblTicketgegevens.setText(Taal.geefTekst("ticketGegevens"));
		lblRapportNr.setText(Taal.geefTekst("rapportNummer"));
		lblNaam.setText(Taal.geefTekst("naam"));
		lblBeschrijving.setText(Taal.geefTekst("beschrijving"));
		lblTicket.setText(Taal.geefTekst("ticket"));
		cbTicket.setPromptText(Taal.geefTekst("ticket"));
		lblOplossing.setText(Taal.geefTekst("oplossing"));
		btnRapportWijzigen.setText(Taal.geefTekst("rapportWijzigen"));
		btnRapportWijzigen.setDisable(true);
		
		tblRapporten.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudRapport, NieuwRapport) -> {
        	if(NieuwRapport != null) {
        		geselecteerdeRapport = NieuwRapport;
        		RapportDetailsInvullen(NieuwRapport);
        	}   	
        });  
		List<Ticket> tickets = ticketController.getTicketsLijst();
	    cbTicket.getItems().clear();
	    cbTicket.getItems().addAll(tickets);
	    cbTicket.setOnMouseClicked(e -> {
	    	cbTicket.getValue();
	    });
		
	}
	private void RapportDetailsInvullen(Rapport rapport) {
		btnRapportWijzigen.setDisable(false);
		txfRapportNr.setText(Integer.toString(rapport.getRapportNummer()));
		txfNaam.setText(rapport.getRapportNaam());
		txaBeschrijving.setText(rapport.getBeschrijving());
		cbTicket.setValue(rapport.getTicket());
		txaOplossing.setText(rapport.getOplossing());
		
	}

	private void rapportTabelInvullen() {
		 tbcRapportNr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getRapportNummer()).asObject());
		 tbcNaam.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getRapportNaam()));
		 tbcBeschrijving.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getBeschrijving()));
		 tbcOplossing.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getOplossing()));
		 tbcTicket.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTicket().getTitel()));
		 tblRapporten.setItems(rapportController.getRapportsLijst());
		 tblRapporten.refresh();
	}
	@FXML
	private void rapportWijzigen(ActionEvent event) {
		updateRapportAtributen();
		rapportController.pasRapportAan(geselecteerdeRapport);
		rapportTabelInvullen();
		rapportgegevensLeegmaken();
	}
	private void updateRapportAtributen() {
		geselecteerdeRapport.setRapportNummer(Integer.parseInt(txfRapportNr.getText()));
		geselecteerdeRapport.setRapportNaam(txfNaam.getText());
		geselecteerdeRapport.setBeschrijving(txaBeschrijving.getText());
		geselecteerdeRapport.setTicket(cbTicket.getValue());
		geselecteerdeRapport.setOplossing(txaOplossing.getText());
	}
	private void rapportgegevensLeegmaken() {
		txfNaam.clear();
		txfRapportNr.clear();
		txaBeschrijving.clear();
		txaOplossing.clear();
		cbTicket.setValue(null);
		btnRapportWijzigen.setDisable(true);
	}

	@FXML
	public void filterNaam(KeyEvent event) {
		rapportTabelFilteren();
	}
	@FXML
	public void filterTicket(KeyEvent event) {
		rapportTabelFilteren();
	}
	@FXML
	public void clear(ActionEvent event) {
		txfFilterNaam.clear();
		txfFilterTicket.clear();
		rapportTabelFilteren();
	}
	@FXML
	public void rapportgegevensLeegmaken(ActionEvent event) {
		rapportgegevensLeegmaken();
	}

	private void rapportTabelFilteren() {
		String naam,ticket;
		naam = txfFilterNaam.getText();
		ticket = txfFilterTicket.getText();
		rapportController.pasFilterAan(naam, ticket);
	}

	@Override
	public void update() {
		initializeGUIComponenten();
		
	}
	
}
