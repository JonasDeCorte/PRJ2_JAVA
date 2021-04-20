package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.controllers.AanmeldController;
import domein.controllers.GebruikerController;
import domein.enumerations.GEBRUIKERSTATUS;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class KlantBeheerSchermController extends AnchorPane{
	private AanmeldController adc;
	private GebruikerController gebruikerController;
	
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
	@FXML private Label lblBedrijfsgegevens;
	@FXML private Label lblBedrijfsnaam;
	@FXML private TextField txfBedrijfsnaam;
	@FXML private Label lblLand;
	@FXML private TextField txfLand;
	@FXML private Label lblGemeente;
	@FXML private TextField txfGemeente;
	@FXML private Label lblPostcode;
	@FXML private TextField txfPostcode;
	@FXML private Label lblStraat;
	@FXML private TextField txfStraat;
	@FXML private Label lblTelefoonnummers;
	@FXML private TextArea txaTelefoonnummers;
	@FXML private Label lblHuisnr;
	@FXML private TextField txfHuisnr;
	@FXML private Label lblBusnr;
	@FXML private TextField txfBusnr;
		
	@FXML private Button btnKlantWijzigen;
	@FXML private Button btnKlantToevoegen;
	
	public KlantBeheerSchermController(AanmeldController aanmeldController) {
		this.adc = aanmeldController;
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
	
	@FXML
    void Hoofdmenu(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setTitle("HoofdMenuAdministrator");
		HoofdMenuAdministratorController root = new HoofdMenuAdministratorController(adc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }
	
	@FXML
    void KlantBeheren(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setTitle("KlantBeheren");
		KlantBeheerSchermController root1 = new KlantBeheerSchermController(adc);
		Scene scene = new Scene(root1);
		stage.setScene(scene);
		
		stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }
	
	@FXML
    void WerknemerBeheren(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setTitle("WerknemerBeheren");
		WerknemerBeheerSchermController root = new WerknemerBeheerSchermController(adc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }
	
	@FXML
	void uitloggen(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Uitloggen bevestigen");
		alert.setHeaderText("Bent u zeker dat u wil uitloggen?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setTitle("Actemium");
			InlogSchermController root = new InlogSchermController(adc);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			
			stage.setOnShown((WindowEvent t) -> {
	            stage.setMinWidth(stage.getWidth());
	            stage.setMinHeight(stage.getHeight());
	        });
	        stage.show();
		}
		if (result.get() == ButtonType.CANCEL) {
			alert.close();
		}	
	}
	
	@FXML
	void voegKlantToe(ActionEvent event) {
		if(klantDetailsControleren()) {	
		Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(), txfPostcode.getText(), 
				txfStraat.getText(), Integer.parseInt(txfHuisnr.getText()), txfBusnr.getText());
		Bedrijf bedrijf = new Bedrijf(txfBedrijfsnaam.getText(), Arrays.asList(txaTelefoonnummers.getText()), adres);
		Klant klant = new Klant(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), 
				txfNaam.getText(), txfEmail.getText(), Integer.parseInt(txfKlantnr.getText()), bedrijf);
		
		if(chkStatus == null) {
			klant.setGebruikerStatus(GEBRUIKERSTATUS.NIET_ACTIEF);
		}
		
		gebruikerController.voegKlantToe(klant);	
		klantDetailsLeegmaken();
		klantTabelInvullen();
		}
	}
	
	private void initializeGUIComponenten() {		
		
		cboTaalWijzigen.setPromptText(Taal.geefTekst("taalKeuze"));
		cboTaalWijzigen.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
	    cboTaalWijzigen.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
	    	if(nieuweTaal != null) {
	    		Taal.instellenTaal(cboTaalWijzigen.getSelectionModel().getSelectedIndex());
	    		initializeGUIComponenten();
	    	}
	    });
	    
	    klantTabelInvullen();

	}
	
	private void klantTabelInvullen() {
		this.gebruikerController = new GebruikerController();
	    tbcKlantsnr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getKlantnummer()).asObject());
	    tbcGebruikersnaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikersnaam()));
        tbcNaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
        tbcVoornaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoornaam()));
        tbcBedrijf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBedrijf().getBedrijfsnaam()));
        tbcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikerStatus().toString()));
        tblKlanten.setItems(gebruikerController.getAllKlanten());    
	}
	
	private void klantDetailsLeegmaken() {
		txfKlantnr.clear();
		txfGebruikersnaam.clear();
		pwfWachtwoord.clear();
		txfVoornaam.clear();
		txfNaam.clear();
		txfEmail.clear();
		txfBedrijfsnaam.clear();
		txfLand.clear();
		txfGemeente.clear();
		txfPostcode.clear();
		txfStraat.clear();
		txfHuisnr.clear();
		txfBusnr.clear();	
		txaTelefoonnummers.clear();
	}
	
	private boolean klantDetailsControleren() {
		String opsommingFoutmelding = "Volgende fouten zijn opgetreden: \n";
		String foutMelding = opsommingFoutmelding;
		
		if(txfKlantnr.getText().isBlank()) 
			foutMelding += "- Het klantnummer is verplicht in te vullen\n";
		if(txfGebruikersnaam.getText().length() < 4) 
			foutMelding += "- De gebruikersnaam moet minstens 4 karakters lang zijn\n";
		if(txfVoornaam.getText().isBlank()) 
			foutMelding += "- De voornaam is verplicht in te vullen\n";
		if(txfNaam.getText().isBlank())
			foutMelding += "- De naam is verplicht in te vullen\n";
		if(pwfWachtwoord.getText().isBlank())
			foutMelding += "- Het wachtwoord is verplicht in te vullen\n";
		if(txfEmail.getText().isBlank())
			foutMelding += "- Het emailadres is verplicht in te vullen\n";
		if(!txfEmail.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			foutMelding += "- Het emailadres is ongeldig\n";
		if(txfBedrijfsnaam.getText().isBlank())
			foutMelding += "- De bedrijfsnaam is verplicht in te vullen\n";
		if(txfLand.getText().isBlank())
			foutMelding += "- Het land is verplicht in te vullen\n";
		if(txfGemeente.getText().isBlank())
			foutMelding += "- De gemeente is verplicht in te vullen\n";
		if(txfPostcode.getText().isBlank())
			foutMelding += "- De postcode is verplicht in te vullen\n";
		if(txfStraat.getText().isBlank())
			foutMelding += "- De straat is verplicht in te vullen\n";
		if(txfHuisnr.getText().isBlank())
			foutMelding += "- Het huisnummer is verplicht in te vullen\n";
		if(txaTelefoonnummers.getText().isBlank()) 
			foutMelding += "- Het telefoonnummer is verplicht in te vullen\n";
		
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Ongeldige invoergegevens");
			alert.setHeaderText("Fout bij het aanmaken van een nieuwe klant");
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
	}	
}
