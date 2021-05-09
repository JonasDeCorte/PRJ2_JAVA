package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.controllers.AanmeldController;
import domein.controllers.BedrijfsBeheerController;
import domein.controllers.GebruikerController;
import domein.enumerations.GEBRUIKERSTATUS;
import domein.enumerations.WERKNEMERROL;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Observer;
import resourcebundle.Taal;

public class KlantBeheerSchermController extends HBox implements Observer{
	
	private final GebruikerController gebruikerController;
	private BedrijfsBeheerController bedrijfsBeheerController;
	// Header (bovenaan)
	@FXML private Button btnUitloggen;
	@FXML private Label lblTitel;
	@FXML private Label lblBegroeting;
		
	// Navigatie (links)
	@FXML private Button btnHoofdmenu;
	@FXML private Label lblGebruikerBeheer;
	@FXML private Button btnWerknemerBeheer;
	@FXML private Button btnKlantBeheer;
	@FXML private Label lblTicketBeheer;
	@FXML private Button btnTicket;
	@FXML private Button btnTicketType;
	@FXML private Button btnRapport;
	@FXML private Label lblContractBeheer;
	@FXML private Button btnContract;
	@FXML private Button btnContractType;
	@FXML private Label lblTaalWijzigen;
	@FXML private ComboBox<String> cboTaalWijzigen;
	
	// Filters (midden)
	@FXML private Label lblFilters;
	@FXML private CheckBox chkActieveKlanten;
	@FXML private CheckBox chkInactieveKlanten;
	@FXML private CheckBox chkGeblokkeerdeKlanten;
	@FXML private TextField txfFilterGebruikersnaam;
	@FXML private TextField txfFilterVoornaam;
	@FXML private TextField txfFilterNaam;
	@FXML private TextField txfFilterBedrijf;
	@FXML private Button btnClearFilters;
	
	// Klant tabel (midden)
	@FXML private TableView<Klant> tblKlanten;
	@FXML private TableColumn<Klant,Integer> tbcKlantsnr;
	@FXML private TableColumn<Klant,String> tbcGebruikersnaam;
	@FXML private TableColumn<Klant,String> tbcVoornaam;
	@FXML private TableColumn<Klant,String> tbcNaam;
	@FXML private TableColumn<Klant,String> tbcBedrijf;
	@FXML private TableColumn<Klant,String> tbcStatus;
	
	// Klant detail paneel (rechts)
	@FXML private Label lblKlantgegevens;
	@FXML private Label lblKlantnr;
	@FXML private TextField txfKlantnr;
	@FXML private Label lblGebruikersnaam;
	@FXML private TextField txfGebruikersnaam;
	@FXML private Label lblWachtwoord;
	@FXML private PasswordField pwfWachtwoord;
	@FXML private Label lblVoornaam;
	@FXML private TextField txfVoornaam;
	@FXML private Label lblNaam;
	@FXML private TextField txfNaam;
	@FXML private Label lblEmail;
	@FXML private TextField txfEmail;
	@FXML private Label lblStatus;
	@FXML private CheckBox chkStatus;
	@FXML private Label lblBedrijf;
	@FXML private ComboBox<Bedrijf> cboKiesBedrijf;
	@FXML private Button btnClearKlantgegevens;
		
	@FXML private Button btnKlantWijzigen;
	@FXML private Button btnKlantToevoegen;
	private Klant geselecteerdeKlant;
	private String origineleGebruikersnaam;
	private String origineelKlantnummer;
	
	// Constructor
	public KlantBeheerSchermController(){
		gebruikerController = new GebruikerController();
		bedrijfsBeheerController = new BedrijfsBeheerController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    initializeGUIComponenten();	
	}
		
	// Initializen van het scherm (Vertaling, invullen data tabel) 
	private void initializeGUIComponenten() {		
	    tblKlanten.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudeKlant, NieuweKlant) -> {
        	if(NieuweKlant != null) {
        		geselecteerdeKlant = NieuweKlant;
        		klantDetailsInvullen(NieuweKlant);
        	}   	
        });
		
		lblFilters.setText(Taal.geefTekst("filters"));
		chkActieveKlanten.setText(Taal.geefTekst("actieveKlanten"));
		chkInactieveKlanten.setText(Taal.geefTekst("inactieveKlanten"));
		chkGeblokkeerdeKlanten.setText(Taal.geefTekst("geblokkeerdeKlanten"));
		txfFilterGebruikersnaam.setPromptText(Taal.geefTekst("gebruikersnaam"));
		txfFilterVoornaam.setPromptText(Taal.geefTekst("voornaam"));
		txfFilterNaam.setPromptText(Taal.geefTekst("naam"));
		txfFilterBedrijf.setPromptText(Taal.geefTekst("bedrijf"));
		btnClearFilters.setText(Taal.geefTekst("leegmaken"));
	    
		tbcKlantsnr.setText(Taal.geefTekst("klantnr"));
		tbcGebruikersnaam.setText(Taal.geefTekst("gebruikersnaam"));
		tbcVoornaam.setText(Taal.geefTekst("voornaam"));
		tbcNaam.setText(Taal.geefTekst("naam"));
		tbcBedrijf.setText(Taal.geefTekst("bedrijf"));
		tbcStatus.setText(Taal.geefTekst("status"));
	    klantTabelInvullen();
	    
	    lblKlantgegevens.setText(Taal.geefTekst("klantgegevens"));
	    lblKlantnr.setText(Taal.geefTekst("klantnummer"));
	    lblGebruikersnaam.setText(Taal.geefTekst("gebruikersnaam"));
	    lblWachtwoord.setText(Taal.geefTekst("wachtwoord"));
	    lblVoornaam.setText(Taal.geefTekst("voornaam"));
	    lblNaam.setText(Taal.geefTekst("naam"));
	    lblEmail.setText(Taal.geefTekst("e-mail"));
	    lblStatus.setText(Taal.geefTekst("status"));
	    chkStatus.setText(Taal.geefTekst("actief"));
	    lblBedrijf.setText(Taal.geefTekst("bedrijf")); 
	    cboKiesBedrijf.setPromptText(Taal.geefTekst("bedrijf"));
	    btnClearKlantgegevens.setText(Taal.geefTekst("leegmaken"));
	    
	    
	    List<Bedrijf> bedrijven = bedrijfsBeheerController.getAllBedrijven();
	    cboKiesBedrijf.getItems().clear();
	    cboKiesBedrijf.getItems().addAll(bedrijven);
	    cboKiesBedrijf.setOnMouseClicked(e -> {
	    	cboKiesBedrijf.getValue();
	    });

	    btnKlantToevoegen.setText(Taal.geefTekst("klantToevoegen"));
	    btnKlantWijzigen.setText(Taal.geefTekst("klantWijzigen"));
	}
	
	// Hoofd functionaliteiten (onderaan rechts)
	@FXML
	void voegKlantToe(ActionEvent event) {
		if(klantDetailsControleren()) {	
		
		Klant klant = new Klant(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), 
				txfNaam.getText(), txfEmail.getText(), Integer.parseInt(txfKlantnr.getText()), cboKiesBedrijf.getValue());
		
		if(chkStatus == null) {
			klant.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		}
		
		gebruikerController.voegKlantToe(klant);	
		klantDetailsLeegmaken();
		klantTabelInvullen();
		}
	}
	
	@FXML
	void KlantWijzigen(ActionEvent event) {
		updateKlantAttributen();
		if(klantDetailsControleren()) {
			gebruikerController.wijzigKlant(geselecteerdeKlant, origineleGebruikersnaam);
			klantTabelInvullen();
	    	klantDetailsLeegmaken();
	    	btnKlantToevoegen.setDisable(false);
	    }
	}

	// Hulp methodes
	private void klantDetailsInvullen(Klant klant) {
		klantDetailsLeegmaken();
		txfKlantnr.setText(Integer.toString(klant.getKlantnummer()));
		origineelKlantnummer = txfKlantnr.getText();
        txfGebruikersnaam.setText(klant.getGebruikersnaam());
        origineleGebruikersnaam = txfGebruikersnaam.getText();
        pwfWachtwoord.setText(klant.getWachtwoord());
        txfVoornaam.setText(klant.getVoornaam());
        txfNaam.setText(klant.getNaam());
        txfEmail.setText(klant.getEmailadres());     
        if(klant.getGebruikerStatus() == GEBRUIKERSTATUS.ACTIEF)
        	chkStatus.setSelected(true);
        cboKiesBedrijf.setValue(klant.getBedrijf());
        //klant.getBedrijf().getTelefoonnummers().stream().forEach(t-> txaTelefoonnummers.setText(txaTelefoonnummers.getText() + t +"\n" ));
        btnKlantToevoegen.setDisable(true);
	}
	
	private void updateKlantAttributen() {
		geselecteerdeKlant.setKlantnummer(Integer.parseInt(txfKlantnr.getText()));
		geselecteerdeKlant.setGebruikersnaam(txfGebruikersnaam.getText());
		geselecteerdeKlant.setWachtwoord(pwfWachtwoord.getText());
		geselecteerdeKlant.setVoornaam(txfVoornaam.getText());
		geselecteerdeKlant.setNaam(txfNaam.getText());
		geselecteerdeKlant.setEmailadres(txfEmail.getText());
		if(chkStatus.isSelected()) {
			geselecteerdeKlant.setGebruikerStatus(GEBRUIKERSTATUS.ACTIEF);	
		} else {
			geselecteerdeKlant.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);	
		}
		geselecteerdeKlant.setBedrijf(cboKiesBedrijf.getValue());
	}
	
	private void klantTabelInvullen() {
	    tbcKlantsnr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getKlantnummer()).asObject());
	    tbcGebruikersnaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikersnaam()));
        tbcNaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
        tbcVoornaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoornaam()));
        tbcBedrijf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBedrijf().getBedrijfsnaam()));
        tbcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikerStatus().toString()));
        tblKlanten.setItems(gebruikerController.getAllKlanten());   
        klantTabelFilteren();
        klantDetailsLeegmaken();
        tblKlanten.refresh();
	}
	
	private void klantDetailsLeegmaken() {
		txfKlantnr.clear();
		txfGebruikersnaam.clear();
		pwfWachtwoord.clear();
		txfVoornaam.clear();
		txfNaam.clear();
		txfEmail.clear();
		cboKiesBedrijf.getSelectionModel().clearSelection();
		origineelKlantnummer = null;
		origineleGebruikersnaam = null;
	}
	
	@FXML
    private void clearKlantgegevens(ActionEvent actionEvent) {
		klantDetailsLeegmaken();
		btnKlantToevoegen.setDisable(false);
	}
	
	private boolean klantDetailsControleren() {
		String opsommingFoutmelding = Taal.geefTekst("opsommingFoutmelding");
		String foutMelding = opsommingFoutmelding;
		
		if(txfKlantnr.getText().isBlank()) 
			foutMelding += Taal.geefTekst("verplichtKlantnummer");
		
		if(!txfKlantnr.getText().isBlank() && gebruikerController.bestaatKlantnummer(Integer.parseInt(txfKlantnr.getText())) && !txfKlantnr.getText().equals(origineelKlantnummer))
			foutMelding += Taal.geefTekst("klantnummerAlGebruikt");

		if(txfGebruikersnaam.getText().length() < 4) 
			foutMelding += Taal.geefTekst("teKortGebruikersnaam");
			
		if(!txfGebruikersnaam.getText().isBlank() && gebruikerController.bestaatKlant(txfGebruikersnaam.getText()) && !txfGebruikersnaam.getText().equals(origineleGebruikersnaam))
				foutMelding += Taal.geefTekst("gebruikersnaamAlGebruikt");

		if(txfVoornaam.getText().isBlank()) 
			foutMelding += Taal.geefTekst("verplichtVoornaam");
		if(txfNaam.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtNaam");
		if(pwfWachtwoord.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtWachtwoord");
		if(txfEmail.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtEmail");
		if(!txfEmail.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			foutMelding += Taal.geefTekst("ongeldigEmail");
		if(cboKiesBedrijf.getValue() == null) 
			foutMelding += Taal.geefTekst("verplichtBedrijf");
	
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle(Taal.geefTekst("foutmeldingTitel"));
			alert.setHeaderText(Taal.geefTekst("foutmeldingHeaderKlant"));
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
	}
		//	Filters
		@FXML
	    private void filterGebruiker(KeyEvent event) {
			klantTabelFilteren();
		}	
		@FXML
	    private void filterVoornaam(KeyEvent event) {
			klantTabelFilteren();
		}	
		@FXML
	    private void filterNaam(KeyEvent event) {
			klantTabelFilteren();
		}	
		@FXML
	    private void filterBedrijf(KeyEvent event) {
			klantTabelFilteren();
		}
		@FXML
	    private void clear(ActionEvent actionEvent) {
			txfFilterGebruikersnaam.clear();
			txfFilterNaam.clear();
			txfFilterVoornaam.clear();
			txfFilterBedrijf.clear();
			klantTabelFilteren();
			
		}
		@FXML
	    private void toonActieve(ActionEvent actionEvent) {
			klantTabelFilteren();			
		}
		@FXML
	    private void toonInactieve(ActionEvent actionEvent) {
			klantTabelFilteren();
		}
		@FXML
	    private void toonGeblokkeerde(ActionEvent actionEvent) {
			klantTabelFilteren();
		}
		
		private void klantTabelFilteren() {
			String gebruikersnaam = txfFilterGebruikersnaam.getText();
	        String naam = txfFilterNaam.getText();
	        String voornaam = txfFilterVoornaam.getText();
	        String bedrijf = txfFilterBedrijf.getText();
	        Set<GEBRUIKERSTATUS> status = new HashSet<>();
	        if(chkActieveKlanten.isSelected())
	        	status.add(GEBRUIKERSTATUS.ACTIEF);
	        if(chkInactieveKlanten.isSelected())
	        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
	        if(chkGeblokkeerdeKlanten.isSelected())
	        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
	        
	        gebruikerController.pasFilterAanKlant(gebruikersnaam, naam, voornaam, bedrijf,status);
		}


		@Override
		public void update() {
			initializeGUIComponenten();
			
		}	
}
