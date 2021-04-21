package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import domein.Adres;
import domein.Werknemer;
import domein.controllers.AanmeldController;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class WerknemerBeheerSchermController extends AnchorPane{
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
	@FXML private CheckBox chkActieveWerknemers;
	@FXML private CheckBox chkInactieveWerknemers;
	@FXML private CheckBox chkGeblokkeerdeWerknemers;
	@FXML private TextField txfFilterGebruikersnaam;
	@FXML private TextField txfFilterVoornaam;
	@FXML private TextField txfFilterNaam;
	@FXML private TextField txfFilterFunctie;
	@FXML private Button btnClearFilters;
	
	// Werknemers tabel (midden)
	@FXML private TableView<Werknemer> tblWerknemers;
	@FXML private TableColumn<Werknemer,Integer> tbcPersoneelsnr;
	@FXML private TableColumn<Werknemer,String> tbcGebruikersnaam;
	@FXML private TableColumn<Werknemer,String> tbcVoornaam;
	@FXML private TableColumn<Werknemer,String> tbcNaam;
	@FXML private TableColumn<Werknemer,String> tbcFunctie;
	@FXML private TableColumn<Werknemer,String> tbcStatus;
	
	// Werknemer detail paneel (rechts)
	@FXML private Label lblPersonneelsgegevens;
	@FXML private Label lblPersoneelsnr;
	@FXML private TextField txfPersoneelsnr;
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
	@FXML private Label lblFunctie;
	@FXML private ComboBox<WERKNEMERROL> cboFunctie;
	@FXML private Label lblAdresgegevens;
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
		
	@FXML private Button btnWerknemerWijzigen;
	@FXML private Button btnWerknemerToevoegen;
	
	public WerknemerBeheerSchermController(AanmeldController aanmeldController) {
		this.adc = aanmeldController;
		this.gebruikerController = new GebruikerController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemerBeheerScherm.fxml"));
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
	void voegWerknemerToe(ActionEvent event) {	
		if(werknemerDetailsControleren()) {
			Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(), txfPostcode.getText(), txfStraat.getText(), 
					Integer.parseInt(txfHuisnr.getText()), txfBusnr.getText());
			Werknemer werknemer = new Werknemer(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), 
					txfNaam.getText(), txfEmail.getText(), Integer.parseInt(txfPersoneelsnr.getText()),  Arrays.asList(txaTelefoonnummers.getText()),
					cboFunctie.getValue(), adres);
				
			gebruikerController.voegWerknemerToe(werknemer);
			werknemerDetailsLeegmaken();	
		} 
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
    void WerknemerBeheren(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setTitle(Taal.geefTekst("werknemerbeheer"));
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
	
	private void initializeGUIComponenten() {		
		btnUitloggen.setText(Taal.geefTekst("uitloggen"));
		lblTitel.setText(Taal.geefTekst("werknemerbeheer"));
		
		cboTaalWijzigen.setPromptText(Taal.geefTekst("taalKeuze"));
		cboTaalWijzigen.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
	    cboTaalWijzigen.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
	    	if(nieuweTaal != null) {
	    		Taal.instellenTaal(cboTaalWijzigen.getSelectionModel().getSelectedIndex());
	    		initializeGUIComponenten();
	    	}
	    });
	    
	    cboFunctie.getItems().addAll(WERKNEMERROL.values());
	    cboFunctie.setOnMouseClicked(e -> {
	    	cboFunctie.getValue();
	    });
	    cboFunctie.getSelectionModel().select(1);
	    
	    werknemerTabelInvullen();
	    filteren();

	}
	private void werknemerTabelInvullen() {
		tbcPersoneelsnr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getPersoneelsnummer()).asObject());
	    tbcGebruikersnaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikersnaam()));
        tbcNaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
        tbcVoornaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoornaam()));
        tbcFunctie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRol().toString()));
        tbcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGebruikerStatus().toString()));
        tblWerknemers.setItems(gebruikerController.getAllWerknemer());
	}
	
	private void werknemerDetailsLeegmaken() {
		txfPersoneelsnr.setText("");
		txfGebruikersnaam.setText("");
		pwfWachtwoord.setText("");
		txfVoornaam.setText("");
		txfNaam.setText("");
		txfEmail.setText("");
		txaTelefoonnummers.setText("");
		txfLand.setText("");
		txfGemeente.setText("");
		txfPostcode.setText("");
		txfStraat.setText("");
		txfHuisnr.setText("");
		txfBusnr.setText("");
		cboFunctie.getSelectionModel().select(1);
	}
	
	private boolean werknemerDetailsControleren() {
		String opsommingFoutmelding = "Volgende fouten zijn opgetreden: \n";
		String foutMelding = opsommingFoutmelding;
		
		if(txfPersoneelsnr.getText().isBlank()) 
			foutMelding += "- Het personeelsnummer is verplicht in te vullen\n";
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
		if(txaTelefoonnummers.getText().isBlank()) 
			foutMelding += "- Het telefoonnummer is verplicht in te vullen\n";
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
		
		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Ongeldige invoergegevens");
			alert.setHeaderText("Fout bij het aanmaken van een nieuwe werknemer");
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
		

	}
	//	Filters
		@FXML
	    private void filterGebruiker(KeyEvent event) {
			filteren();
		}	
		@FXML
	    private void filterVoornaam(KeyEvent event) {
			filteren();
		}	
		@FXML
	    private void filterNaam(KeyEvent event) {
			filteren();
		}	
		@FXML
	    private void filterFunctie(KeyEvent event) {
	        filteren();
		}
		@FXML
	    private void clear(ActionEvent actionEvent) {
			txfFilterGebruikersnaam.clear();
			txfFilterNaam.clear();
			txfFilterVoornaam.clear();
			txfFilterFunctie.clear();
			filteren();
			
		}
		@FXML
	    private void toonActieve(ActionEvent actionEvent) {
			filteren();
			
		}
		@FXML
	    private void toonInactieve(ActionEvent actionEvent) {
			filteren();
		}
		@FXML
	    private void toonGeblokkeerde(ActionEvent actionEvent) {
			filteren();
		}
		private void filteren() {
			String gebruikersnaam = txfFilterGebruikersnaam.getText();
	        String naam = txfFilterNaam.getText();
	        String voornaam = txfFilterVoornaam.getText();
	        String functie = txfFilterFunctie.getText();
	        Set<GEBRUIKERSTATUS> status = new HashSet<>();;
	        if(chkActieveWerknemers.isSelected())
	        	status.add(GEBRUIKERSTATUS.ACTIEF);
	        if(chkInactieveWerknemers.isSelected())
	        	status.add(GEBRUIKERSTATUS.NIET_ACTIEF);
	        if(chkGeblokkeerdeWerknemers.isSelected())
	        	status.add(GEBRUIKERSTATUS.GEBLOKKEERD);
	        
	        gebruikerController.pasFilterAanWerknemer(gebruikersnaam, naam, voornaam, functie,status);
		}
		
		
}