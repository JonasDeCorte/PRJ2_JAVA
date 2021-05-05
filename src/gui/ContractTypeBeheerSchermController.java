package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.ContractType;
import domein.controllers.ContractTypeController;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;
import domein.enumerations.TICKETSTATUS;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import resourcebundle.Taal;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ContractTypeBeheerSchermController extends HBox{
	@FXML private Label lblFilters;
	@FXML private CheckBox chkActief;
	@FXML private CheckBox chkNietActief;
	@FXML private TextField txfFilterNaam;
	@FXML private Button btnClearFilters;
	@FXML private TableView<ContractType> tblContractType;
	@FXML private TableColumn<ContractType,Integer> tbcContractTypeNr;
	@FXML private TableColumn<ContractType,String>tbcNaam;
	@FXML private TableColumn<ContractType,Integer> tbcLopendeContracten;
	@FXML private TableColumn<ContractType,Boolean> tbcStatus;
	@FXML private Label lblContractTypeDetails;
	@FXML private Label lblNaam;
	@FXML private Label lblMinAfhandeltijd;
	@FXML private Label lblMaxAfhandeltijd;
	@FXML private Label lblPrijsContract;
	@FXML private Label lblStatus;
	@FXML private Label lblManier;
	@FXML private Label lblWanneer;
	@FXML private TextField txfNaam;
	@FXML private TextField txfMinAfhandeltijd;
	@FXML private TextField txfMaxAfhandeltijd;
	@FXML private TextField txfPrijs;
	@FXML private CheckBox chkActief1;
	@FXML private CheckBox chkEmail;
	@FXML private CheckBox chkTelefoon;
	@FXML private CheckBox chkApplicatie;
	@FXML private ComboBox<TICKETAANMAAKTIJD> cboTijd;
	@FXML private Button btnClearFilters1;
	@FXML private Button btnContractTypeWijzigen;
	@FXML private Button btnContractTypeToevoegen;
	
	private final ContractTypeController contractTypeController;
	
	public ContractTypeBeheerSchermController() {	
		this.contractTypeController = new ContractTypeController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContractTypeBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    initializeGUIComponenten();
	    contractTypeTabelInvullen();
	}
	
	private void initializeGUIComponenten() {
		tblContractType.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudeContract, NieuweContract) -> {
        	if(NieuweContract != null) {
        		
        		contractTypeDetailsInvullen(NieuweContract);
        	}   	
        });
	    cboTijd.getItems().addAll(TICKETAANMAAKTIJD.values());
	    cboTijd.setOnMouseClicked(e -> {
	    	cboTijd.getValue();
	    });
	}
	
	@FXML
	void voegContractTypeToe(ActionEvent event) {
		if(contractTypeDetailsControleren()) {
			List<TICKETAANMAAKMETHODE> aanmaakMethodes = new ArrayList<>();
			if(chkApplicatie.isSelected())
				aanmaakMethodes.add(TICKETAANMAAKMETHODE.VIA_APPLICATIE);
			if(chkTelefoon.isSelected())
				aanmaakMethodes.add(TICKETAANMAAKMETHODE.TELEFONISCH);
			if(chkEmail.isSelected())
				aanmaakMethodes.add(TICKETAANMAAKMETHODE.EMAIL);
			
			ContractType contractType = new ContractType(0, txfNaam.getText(), Integer.parseInt(txfMaxAfhandeltijd.getText()), 
					Integer.parseInt(txfMinAfhandeltijd.getText()), Double.parseDouble(txfPrijs.getText()), aanmaakMethodes , cboTijd.getValue());
		
			if(chkActief1 == null) {
				contractType.setStatus(false);
			}
			
			contractTypeController.addContractType(contractType);
			contractTypeDetailsLeegmaken();
			contractTypeTabelInvullen();
		}
	}
	
	private void contractTypeDetailsInvullen(ContractType contractType){
		contractTypeDetailsLeegmaken();
		txfNaam.setText(contractType.getNaam());
		txfMinAfhandeltijd.setText( Integer.toString(contractType.getMinimaleDoorloopTijd()));
		txfMaxAfhandeltijd.setText(Integer.toString(contractType.getMaximaleAfhandelTijd()));
		txfPrijs.setText(Double.toString(contractType.getPrijs()));
		chkActief1.setSelected(contractType.isStatus());
		chkEmail.setSelected(contractType.getTicketAanmaakMethode().contains(TICKETAANMAAKMETHODE.EMAIL));
		chkTelefoon.setSelected(contractType.getTicketAanmaakMethode().contains(TICKETAANMAAKMETHODE.TELEFONISCH));
		chkApplicatie.setSelected(contractType.getTicketAanmaakMethode().contains(TICKETAANMAAKMETHODE.VIA_APPLICATIE));
		cboTijd.setValue(contractType.getTicketAanmaakTijd());
	}
	
	private void contractTypeTabelInvullen() {
		 	tbcContractTypeNr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getContractTypeId()).asObject());
		    tbcNaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
	        //tbcLopendeContracten.setCellValueFactory(cellData -> new SimpleIntegerProperty( cellData.getValue().geefAantalContracten()));
	        tbcStatus.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isStatus()));
		    tblContractType.setItems(contractTypeController.getAllContractTypes());
	        tblContractType.refresh();
	}
	
	private void contractTypeDetailsLeegmaken() {
		txfNaam.clear();
		txfMinAfhandeltijd.clear();
		txfMaxAfhandeltijd.clear();
		txfPrijs.clear();
		chkActief1.setSelected(false);
		chkEmail.setSelected(false);
		chkTelefoon.setSelected(false);
		chkApplicatie.setSelected(false);
	}
	
	private boolean contractTypeDetailsControleren() {
		String opsommingFoutmelding = "Volgende fouten zijn opgetreden:\n";
		String foutMelding = opsommingFoutmelding;
		
		if(txfNaam.getText().isBlank())
			foutMelding += "- De naam is verplicht in te vullen\n";
		if(txfMinAfhandeltijd.getText().isBlank())
			foutMelding += "- De minimale afhandeltijd is verplicht in te vullen\n";
		if(!txfMinAfhandeltijd.getText().matches("[0-9]"))
			foutMelding += "- Ongeldige invoer bij de minimale afhandeltijd (enkel gehele nummerieke waardes zijn toegestaan)\n";
		if(txfMaxAfhandeltijd.getText().isBlank())
			foutMelding += "- De maximale afhandeltijd is verplicht in te vullen\n";
		if(!txfMaxAfhandeltijd.getText().matches("[0-9]"))
			foutMelding += "- Ongeldige invoer bij de maximale afhandeltijd (enkel gehele nummerieke waardes zijn toegestaan)\n";
		if(txfPrijs.getText().isBlank())
			foutMelding += "- De prijs is verplicht in te vullen\n";
		if(!txfPrijs.getText().matches("[0-9, /.]+"))
			foutMelding += "- Ongeldige invoer bij de prijs (enkel nummerieke waardes zijn toegestaan)\n";
		if(!chkApplicatie.isSelected() & !chkTelefoon.isSelected() & !chkEmail.isSelected()) 
			foutMelding += "- Er moet verplicht één manier gekozen zijn om tickets aan te maken\n";
		
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Ongeldige invoergegevens");
			alert.setHeaderText("Fout bij het aanmaken/wijzigen van een contract type");
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
	}

	// Event Listener on CheckBox[#chkActieveKlanten].onAction
	@FXML
	public void toonActieve(ActionEvent event) {
		contractTypeTabelFilteren();
	}
	// Event Listener on CheckBox[#chkInactieveKlanten].onAction
	@FXML
	public void toonInactieve(ActionEvent event) {
		contractTypeTabelFilteren();
	}
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten].onAction
	@FXML
	public void filterNaam(KeyEvent event) {
		contractTypeTabelFilteren();
	}
		
	// Event Listener on Button[#btnClearFilters].onAction
	@FXML
	public void clear(ActionEvent event) {
		txfFilterNaam.clear();
		contractTypeTabelFilteren();
	}
	
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten1].onAction
	@FXML
	public void clearContractTypeDetails(ActionEvent event) {
		contractTypeDetailsLeegmaken();
	}
	
	@FXML
	public void toonGeannuleerde(ActionEvent event) {
		contractTypeTabelFilteren();
	}

	private void contractTypeTabelFilteren() {
		String contractTypeNaam = txfFilterNaam.getText();
		Boolean statusActiefTrue = chkActief.isSelected();
		Boolean statusActiefFalse = chkNietActief.isSelected();
		contractTypeController.pasFilterAanContractType(contractTypeNaam, statusActiefTrue, statusActiefFalse);
	}
}
