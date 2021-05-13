package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domein.Bedrijf;
import domein.Contract;
import domein.Gebruiker;
import domein.Klant;
import domein.Rapport;
import domein.Ticket;
import domein.TicketType;
import domein.Werknemer;
import domein.controllers.AanmeldController;
import domein.controllers.ContractController;
import domein.controllers.GebruikerController;
import domein.controllers.TicketController;
import domein.controllers.TicketTypeController;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.TICKETAANMAAKTIJD;
import domein.enumerations.TICKETSTATUS;
import domein.enumerations.WERKNEMERROL;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import resourcebundle.Observer;
import resourcebundle.Taal;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TicketBeheerSchermController  extends HBox implements Observer{
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
	private Label lblTechnieker;
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
	private GridPane grdTicketGegevens;
	
	@FXML
	private TextArea txaOplossing;
	@FXML
	private TextArea txaOmschrijving;
	@FXML
	private ComboBox<Werknemer> cboTechnieker;
	@FXML
	private Button btnTicketWijzigen;
	@FXML
	private Button btnTicketToevoegen;
	
	@FXML
	private TextField txfTicketNr;
	@FXML
	private TextField txfTitel;
	
	
	@FXML
	private TextArea txaRapport;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private TextArea txaOpmerkingen;
	@FXML
	private ComboBox<Klant> cbKlanten;
	@FXML
	private ComboBox<TicketType> cbTicketType;
	@FXML
	private ComboBox<Contract> cbContract;
	@FXML
	private ComboBox<TICKETSTATUS> cbStatus;
	@FXML
	private Label lblDatumAfgehandeld;
	@FXML
	private TextField txfDatumAfgehandeld;
	@FXML
	private TextField txfDatumAangemaakt;
	
	private Ticket geselecteerdeTicket;
	private final TicketController ticketController;
	private final TicketTypeController ticketTypeController;
	private final GebruikerController gebruikerController;

	public TicketBeheerSchermController() {
		
		this.ticketController = new TicketController(AanmeldController.getAangemeldeWerknemer());
		this.ticketTypeController = new TicketTypeController();
		this.gebruikerController = new GebruikerController();
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
		lblFilters.setText(Taal.geefTekst("filters"));
		chkAangemaakteTickets.setText(Taal.geefTekst("aangemaakteTickets"));
		chkInActieveTickets.setText(Taal.geefTekst("actieveTickets"));
		chkAfgehandeldeTickets.setText(Taal.geefTekst("afgehandeldeTickets"));
		txfFilterTitel.setPromptText(Taal.geefTekst("titel"));
		txfFilterDatum.setPromptText(Taal.geefTekst("datum"));
		txfFilterContract.setPromptText(Taal.geefTekst("contract"));
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
		lblTechnieker.setText(Taal.geefTekst("technieker"));
		lblContract.setText(Taal.geefTekst("contract"));
		lblTicketType.setText(Taal.geefTekst("ticketType"));
		lblRapport.setText(Taal.geefTekst("rapport"));
		lblStatus.setText(Taal.geefTekst("status"));
		lblOpmerkingen.setText(Taal.geefTekst("opmerkingen"));
		btnClearFilters1.setText(Taal.geefTekst("leegmaken"));
		btnTicketWijzigen.setDisable(true);
		if(AanmeldController.getAangemeldeWerknemer().getRol().equals(WERKNEMERROL.TECHNIEKER)) {
			btnTicketToevoegen.setDisable(true);
			cboTechnieker.setDisable(true);
			cbKlanten.setDisable(true);
		}
		else {
			List<Werknemer> techniekers = gebruikerController.getAllWerknemer().stream().filter(w->w.getRol().equals(WERKNEMERROL.TECHNIEKER)).distinct().collect(Collectors.toList());
			cboTechnieker.getItems().clear();
			cboTechnieker.getItems().addAll(techniekers);
			cboTechnieker.setOnMouseClicked(e -> {
				cboTechnieker.getValue();
		    });
			cbKlanten.getItems().clear();
			cbKlanten.getItems().addAll(gebruikerController.getAllKlanten());
			cbKlanten.setOnMouseClicked(e -> {
				cbKlanten.getValue();
		    });
		}
		tblTickets.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudeTicket, NieuweTicket) -> {
        	if(NieuweTicket != null) {
        		geselecteerdeTicket = NieuweTicket;
        		TicketDetailsInvullen(NieuweTicket);
        		contractenWeergeven(geselecteerdeTicket.getContract().getKlant());
        		btnTicketWijzigen.setDisable(false);
        		btnTicketToevoegen.setDisable(true);
        	}   	
        });
		
		
		List<TicketType> ticketType = ticketTypeController.haalTicketTypesOp();
		cbTicketType.getItems().clear();
	    cbTicketType.getItems().addAll(ticketType);
	    cbTicketType.setOnMouseClicked(e -> {
	    	cbTicketType.getValue();
	    });
	    cbStatus.getItems().clear();
	    cbStatus.getItems().addAll(TICKETSTATUS.values());
	    cbStatus.setOnMouseClicked(e -> {
	    	cbStatus.getValue();
	    });
	    
	
	}
	@FXML
	void klantGeselecteerd(ActionEvent event) {
		contractenWeergeven(cbKlanten.getValue());
	}
	/*@FXML
	void toonContracten(ActionEvent event) {
		contractenWeergeven(cbKlanten.getValue());
	}*/
	
	
	private void contractenWeergeven(Klant klant) {
		if(klant != null) {
		List<Contract> contracten = klant.getContracten();
		cbContract.getItems().clear();
	    cbContract.getItems().addAll(contracten);
	    cbContract.setOnMouseClicked(e -> {
	    	cbContract.getValue();
	    });
		}
	}
	
	private void TicketDetailsInvullen(Ticket ticket) {
		TicketDetailsLeegmaken();
		txfTicketNr.setText(Integer.toString(ticket.getTicketnummer()));
		txfTicketNr.setEditable(false);
        txfTitel.setText(ticket.getTitel());
        txfDatumAangemaakt.setText(ticket.getDatumAangemaakt().format(DateTimeFormatter.ISO_LOCAL_DATE));
        if(ticket.getDatumAfgesloten() != null)
        txfDatumAfgehandeld.setText(ticket.getDatumAfgesloten().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txaOmschrijving.setText(ticket.getOmschrijving());
        if(ticket.getRapport() != null)
        txaRapport.setText(ticket.getRapport().getBeschrijving());
        cboTechnieker.setValue(ticket.getToegekendeTechnieker());
        cbContract.setValue(ticket.getContract());
        cbTicketType.setValue(ticket.getTicketType());
		txaOpmerkingen.setText(ticket.getOpmerkingen());
		cbStatus.setValue(ticket.getTicketStatus());
	}
	private void TicketDetailsLeegmaken() {
		txfTicketNr.clear();
        txfTitel.clear();
        txfDatumAangemaakt.clear();
        txfDatumAfgehandeld.clear();
        txaOmschrijving.clear();
        txaOplossing.clear();    
		txaOpmerkingen.clear();
		txaRapport.clear();
		btnTicketWijzigen.setDisable(true);
		cbContract.setValue(null);
		cbContract.getItems().clear();
		if(AanmeldController.getAangemeldeWerknemer().getRol().equals(WERKNEMERROL.SUPPORTMANAGER))
		btnTicketToevoegen.setDisable(false);
		txfTicketNr.setEditable(true);
	}
	private void updateTicketAttributen() {
		geselecteerdeTicket.setTicketnummer(Integer.parseInt(txfTicketNr.getText()));
		geselecteerdeTicket.setTitel(txfTitel.getText());
		geselecteerdeTicket.setDatumAangemaakt(LocalDate.parse(txfDatumAangemaakt.getText(),DateTimeFormatter.ISO_LOCAL_DATE));
		if(!txfDatumAfgehandeld.getText().isEmpty())
		geselecteerdeTicket.setDatumAfgesloten(LocalDate.parse(txfDatumAfgehandeld.getText(),DateTimeFormatter.ISO_LOCAL_DATE));
		geselecteerdeTicket.setOmschrijving(txaOmschrijving.getText());
		//geselecteerdeTicket.setOplossing(new Bijlage(".txt",txaOplossing.getText(),geselecteerdeTicket));
		geselecteerdeTicket.setContract(cbContract.getValue());
		geselecteerdeTicket.setTicketType(cbTicketType.getValue());
		if(!cboTechnieker.isDisabled())
			geselecteerdeTicket.setToegekendeTechnieker(cboTechnieker.getValue());
		if(!txaRapport.getText().isEmpty())
		geselecteerdeTicket.setRapport(new Rapport(geselecteerdeTicket.getTicketnummer(), geselecteerdeTicket.getTitel() + "Rapport", txaRapport.getText(), txaOplossing.getText(), geselecteerdeTicket));
		else
		geselecteerdeTicket.setRapport(null);
		geselecteerdeTicket.setTicketStatus(cbStatus.getValue());
		geselecteerdeTicket.setOpmerkingen(txaOpmerkingen.getText());
		btnTicketWijzigen.setDisable(true);
		TicketDetailsLeegmaken();
	}
	@FXML
	void voegTicketToe(ActionEvent event) {
		if(ticketDetailsControleren()) {	
		
		Ticket ticket = new Ticket(Integer.parseInt(txfTicketNr.getText()), txfTitel.getText(), txaOmschrijving.getText(), 
		txaOpmerkingen.getText(), cbContract.getValue(), cbTicketType.getValue());
		ticket.setToegekendeTechnieker(cboTechnieker.getValue());
		
		ticketController.voegTicketToe(ticket);	
		TicketDetailsLeegmaken();;
		TicketTabelInvullen();
		}
	}
	@FXML
	void ticketWijzigen(ActionEvent event) {
		
		if(ticketDetailsControleren()) {
			updateTicketAttributen();
			ticketController.pasTicketAan(geselecteerdeTicket);
			TicketTabelInvullen();
	    	TicketDetailsLeegmaken();
	    	ticketTabelFilteren();
	    	btnTicketWijzigen.setDisable(true);
	    }
	}
	private boolean ticketDetailsControleren() {
		String opsommingFoutmelding = "Volgende fouten zijn opgetreden:\n";
		String foutMelding = opsommingFoutmelding;
		
		if(txfTicketNr.getText().isBlank())
			foutMelding += "- De ticketnummer is verplicht in te vullen\n";
		if(txfTitel.getText().isBlank())
			foutMelding += "- De titel is verplicht in te vullen\n";
		if(txfDatumAangemaakt.getText().isBlank())
			foutMelding += "- Datumaangemaakt is verplicht in te vullen\n";
		if(!txfDatumAangemaakt.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
			foutMelding += "- Ongeldige invoer bij de datumaangemaakt (enkel gehele nummerieke waardes zijn toegestaan [yyyy,mm,dd])\n";
		if(!txfDatumAfgehandeld.getText().isBlank() &&!txfDatumAfgehandeld.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
			foutMelding += "- Ongeldige invoer bij de datumafgehandeld (enkel gehele nummerieke waardes zijn toegestaan [yyyy,mm,dd])\n";
		if(cbContract.getValue() == null)
			foutMelding += "- Er moet een contract geselecteerd zijn\n";
		if(txaOmschrijving.getText().isBlank())
			foutMelding += "- De omschrijving is verplicht in te vullen\n";
		if(!txaRapport.getText().isBlank() && txaOplossing.getText().isBlank())
			foutMelding += "- Voor een rapport te maken moet de oplossing beschreven zijn\n";
		if(txaOpmerkingen.getText().isBlank())
			foutMelding += "- De opmerkingen zijn verplicht in te vullen\n";
		
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Ongeldige invoergegevens");
			alert.setHeaderText("Fout bij het wijzigen van een ticket");
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
	}
	// Event Listener on CheckBox[#chkAangemaakteTickets].onAction
	@FXML
	public void toonAangemaakte(ActionEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on CheckBox[#chkInActieveTickets].onAction
	@FXML
	public void toonActieve(ActionEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on CheckBox[#chkAfgehandeldeTickets].onAction
	@FXML
	public void toonAfgehandelde(ActionEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on TextField[#txfFilterTitel].onKeyReleased
	@FXML
	public void filterTitel(KeyEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on TextField[#txfFilterDatum].onKeyReleased
	@FXML
	public void filterDatum(KeyEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on TextField[#txfFilterContract].onKeyReleased
	@FXML
	public void filterContract(KeyEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on TextField[#txfFilterStatus].onKeyReleased
	// Event Listener on Button[#btnClearFilters].onAction
	@FXML
	public void clear(ActionEvent event) {
		txfFilterTitel.clear();
		txfFilterDatum.clear();
		txfFilterContract.clear();
		ticketTabelFilteren();
	}
	// Event Listener on CheckBox[#chkGeannuleerdeTickets].onAction
	@FXML
	public void toonGeannuleerde(ActionEvent event) {
		ticketTabelFilteren();
	}
	// Event Listener on Button[#btnClearFilters1].onAction
	@FXML
	public void clearTicketGegevens(ActionEvent event) {
		TicketDetailsLeegmaken();
	}
	
	private void ticketTabelFilteren() {
		String titel = txfFilterTitel.getText();
        String datum = txfFilterDatum.getText();
        String contract = txfFilterContract.getText();
        
        Set<TICKETSTATUS> status1 = new HashSet<>();
        if(chkAangemaakteTickets.isSelected())
        	status1.add(TICKETSTATUS.AANGEMAAKT);
        if(chkAfgehandeldeTickets.isSelected())
        	status1.add(TICKETSTATUS.AFGEHANDELD);
        if(chkGeannuleerdeTickets.isSelected())
        	status1.add(TICKETSTATUS.GEANNULEERD);
        if(chkInActieveTickets.isSelected())
        	status1.add(TICKETSTATUS.IN_BEHANDELING);
        
        ticketController.pasFilterAan(titel,datum,contract, status1);
	}
	@Override
	public void update() {
		initializeGUIComponenten();
		
	}	
}
