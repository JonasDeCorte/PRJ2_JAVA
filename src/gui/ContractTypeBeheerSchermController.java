package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Bedrijf;
import domein.ContractType;
import domein.controllers.BedrijfsBeheerController;
import domein.controllers.ContractTypeController;
import domein.enumerations.TICKETAANMAAKMETHODE;
import domein.enumerations.TICKETAANMAAKTIJD;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.PasswordField;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ContractTypeBeheerSchermController extends HBox{
	@FXML
	private Label lblFilters;
	@FXML
	private CheckBox chkActief;
	@FXML
	private CheckBox chkNietActief;
	@FXML
	private TextField txfFilterNaam;
	@FXML
	private Button btnClearFilters;
	@FXML
	private TableView<ContractType> tblContractType;
	@FXML
	private TableColumn<ContractType,Integer> tbcContractTypeNr;
	@FXML
	private TableColumn<ContractType,String>tbcNaam;
	@FXML
	private TableColumn<ContractType,Integer> tbcLopendeContracten;
	@FXML
	private TableColumn<ContractType,Boolean> tbcStatus;
	@FXML
	private Label lblContractTypeDetails;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblMinAfhandeltijd;
	@FXML
	private Label lblMaxAfhandeltijd;
	@FXML
	private Label lblPrijsContract;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblManier;
	@FXML
	private Label lblWanneer;
	@FXML
	private TextField txfNaam;
	@FXML
	private TextField txfMinAfhandeltijd;
	@FXML
	private TextField txfMaxAfhandeltijd;
	@FXML
	private TextField txfPrijs;
	@FXML
	private CheckBox chkActief1;
	@FXML
	private CheckBox chkEmail;
	@FXML
	private CheckBox chkTelefoon;
	@FXML
	private CheckBox chkApplicatie;
	@FXML
	private ComboBox<TICKETAANMAAKTIJD> cboTijd;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private Button btnContractTypeWijzigen;
	@FXML
	private Button btnContractTypeToevoegen;
	
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
	    ContractTypeTabelInvullen();
	}
	private void initializeGUIComponenten() {
		tblContractType.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudeContract, NieuweContract) -> {
        	if(NieuweContract != null) {
        		
        		ContractTypeDetailsInvullen(NieuweContract);
        	}   	
        });
		List<TICKETAANMAAKTIJD> ticketAanmaakTijd = new ArrayList<>();
		ticketAanmaakTijd.add(TICKETAANMAAKTIJD.ALTIJD_24_7);
		ticketAanmaakTijd.add(TICKETAANMAAKTIJD.WERKDAGEN_8_TOT_17);
	    cboTijd.getItems().addAll(ticketAanmaakTijd);
	    cboTijd.setOnMouseClicked(e -> {
	    	cboTijd.getValue();
	    });
	}
	private void ContractTypeDetailsInvullen(ContractType contractType){
		ContractTypeDetailsLeegmaken();
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
	private void ContractTypeTabelInvullen() {
		 	tbcContractTypeNr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getContractTypeId()).asObject());
		    tbcNaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
	        //tbcLopendeContracten.setCellValueFactory(cellData -> new SimpleIntegerProperty( cellData.getValue().geefAantalContracten()));
	        tbcStatus.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isStatus()));
		    tblContractType.setItems(contractTypeController.getAllContractTypes());
	        tblContractType.refresh();
	}
	private void ContractTypeDetailsLeegmaken() {
		txfNaam.clear();
		txfMinAfhandeltijd.clear();
		txfMaxAfhandeltijd.clear();
		txfPrijs.clear();
		chkActief1.setSelected(false);
		chkEmail.setSelected(false);
		chkTelefoon.setSelected(false);
		chkApplicatie.setSelected(false);
	}

	// Event Listener on CheckBox[#chkActieveKlanten].onAction
	@FXML
	public void toonActieve(ActionEvent event) {
		ContractTypeTabelFilteren();
	}
	// Event Listener on CheckBox[#chkInactieveKlanten].onAction
	@FXML
	public void toonInactieve(ActionEvent event) {
		ContractTypeTabelFilteren();
	}
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten].onAction
	@FXML
	public void filterNaam(KeyEvent event) {
		ContractTypeTabelFilteren();
	}
	
	
	// Event Listener on Button[#btnClearFilters].onAction
	@FXML
	public void clear(ActionEvent event) {
		txfFilterNaam.clear();
		ContractTypeTabelFilteren();
	}
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten1].onAction
	@FXML
	public void clearContractTypeDetails(ActionEvent event) {
		ContractTypeDetailsLeegmaken();
	}
	@FXML
	public void toonGeannuleerde(ActionEvent event) {
		ContractTypeTabelFilteren();
	}
	
	
	
	private void ContractTypeTabelFilteren() {
		String contractTypeNaam = txfFilterNaam.getText();
		Boolean statusActiefTrue = chkActief.isSelected();
		Boolean statusActiefFalse = chkNietActief.isSelected();
		contractTypeController.pasFilterAanContractType(contractTypeNaam, statusActiefTrue, statusActiefFalse);
	}
}
