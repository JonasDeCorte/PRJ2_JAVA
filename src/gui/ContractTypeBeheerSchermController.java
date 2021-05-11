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
import domein.controllers.GebruikerController;
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
import resourcebundle.Observer;
import resourcebundle.Taal;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ContractTypeBeheerSchermController extends HBox implements Observer{
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
	private ContractType geselecteerdContractType;
	
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
		lblFilters.setText(Taal.geefTekst("filters"));
		txfFilterNaam.setPromptText(Taal.geefTekst("naam"));
		chkActief.setText(Taal.geefTekst("actief"));
		chkNietActief.setText(Taal.geefTekst("nietActief"));
		btnClearFilters.setText(Taal.geefTekst("leegmaken"));
		
		tbcContractTypeNr.setText(Taal.geefTekst("contractTypeNr"));
		tbcNaam.setText(Taal.geefTekst("naam"));
		tbcLopendeContracten.setText(Taal.geefTekst("lopendeContracten"));
		tbcStatus.setText(Taal.geefTekst("status"));	
		tblContractType.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudContractType, nieuwContractType) -> {
        	if(nieuwContractType != null) {
        		geselecteerdContractType = nieuwContractType;
        		contractTypeDetailsInvullen(nieuwContractType);
        	}   	
        });
		
		lblContractTypeDetails.setText(Taal.geefTekst("contractTypeDetails"));
		lblNaam.setText(Taal.geefTekst("naam"));
		lblMinAfhandeltijd.setText(Taal.geefTekst("minAfhandeltijd"));
		lblMaxAfhandeltijd.setText(Taal.geefTekst("maxAfhandeltijd"));
		lblPrijsContract.setText(Taal.geefTekst("prijs"));
		lblStatus.setText(Taal.geefTekst("status"));
		chkActief1.setText(Taal.geefTekst("actief"));
		lblManier.setText(Taal.geefTekst("manierAanmakenTickets"));
		chkApplicatie.setText(Taal.geefTekst("applicatie"));
		chkTelefoon.setText(Taal.geefTekst("telefoon"));
		chkEmail.setText(Taal.geefTekst("e-mail"));
		lblWanneer.setText(Taal.geefTekst("wanneerAanmakenTickets"));
		cboTijd.getItems().clear();
	    cboTijd.getItems().addAll(TICKETAANMAAKTIJD.values());
	    cboTijd.setOnMouseClicked(e -> {
	    	cboTijd.getValue();
	    });
	    
	    btnContractTypeToevoegen.setText(Taal.geefTekst("wijzigen"));
	    btnContractTypeWijzigen.setText(Taal.geefTekst("toevoegen"));
	    btnClearFilters1.setText(Taal.geefTekst("leegmaken"));
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
	
	@FXML
	void wijzigContractType(ActionEvent event) {

		if(geselecteerdContractType != null && geselecteerdContractType.geefAantalContracten() != 0) {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle(Taal.geefTekst("foutmeldingTitleContractTypeWijzigen"));
			alert.setHeaderText(Taal.geefTekst("foutmeldingHeaderContractTypeWijzigen"));
			alert.setContentText("foutmeldingContextContractTypeWijzigen");
			alert.showAndWait();
		}
		else if(contractTypeDetailsControleren()) {
			updateContractTypeAttributen();
			contractTypeController.editContractType(geselecteerdContractType);
			contractTypeTabelInvullen();
			contractTypeDetailsLeegmaken();
			} 
		}
	
	private void updateContractTypeAttributen() {
		geselecteerdContractType.setNaam(txfNaam.getText());
		geselecteerdContractType.setPrijs(Double.parseDouble(txfPrijs.getText()));
		geselecteerdContractType.setMaximaleAfhandelTijd(Integer.parseInt(txfMaxAfhandeltijd.getText()));
		geselecteerdContractType.setMinimaleDoorloopTijd(Integer.parseInt(txfMinAfhandeltijd.getText()));
		
		if(chkActief1.isSelected()) {
			geselecteerdContractType.setStatus(true);
		} else {
			geselecteerdContractType.setStatus(false);
		}
		
		List<TICKETAANMAAKMETHODE> aanmaakMethodes = new ArrayList<>();
		if(chkApplicatie.isSelected())
			aanmaakMethodes.add(TICKETAANMAAKMETHODE.VIA_APPLICATIE);
		if(chkTelefoon.isSelected())
			aanmaakMethodes.add(TICKETAANMAAKMETHODE.TELEFONISCH);
		if(chkEmail.isSelected())
			aanmaakMethodes.add(TICKETAANMAAKMETHODE.EMAIL);
		geselecteerdContractType.setTicketAanmaakMethode(aanmaakMethodes);
		
		geselecteerdContractType.setTicketAanmaakTijd(cboTijd.getValue());
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
		String opsommingFoutmelding = Taal.geefTekst("opsommingFoutmelding");
		String foutMelding = opsommingFoutmelding;
		
		if(txfNaam.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtNaam");
		if(txfMinAfhandeltijd.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtMinAfhandeltijd");
		if(!txfMinAfhandeltijd.getText().matches("[0-9]"))
			foutMelding += Taal.geefTekst("ongeldigeInvoerMinAfhandeltijd");
		if(txfMaxAfhandeltijd.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtMaxAfhandeltijd");
		if(!txfMaxAfhandeltijd.getText().matches("[0-9]"))
			foutMelding += Taal.geefTekst("ongeldigeInvoerMaxAfhandeltijd");
		if(txfPrijs.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtPrijs");
		if(!txfPrijs.getText().matches("[0-9, /.]+"))
			foutMelding += Taal.geefTekst("ongeldigeInvoerPrijs");
		if(!chkApplicatie.isSelected() & !chkTelefoon.isSelected() & !chkEmail.isSelected()) 
			foutMelding += Taal.geefTekst("verplichtTicketManier");
		
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle(Taal.geefTekst("foutmeldingTitel"));
			alert.setHeaderText(Taal.geefTekst("foutmeldingHeaderContractType"));
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

	@Override
	public void update() {
		initializeGUIComponenten();	
	}
}
