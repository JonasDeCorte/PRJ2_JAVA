package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.controllers.AanmeldController;
import domein.controllers.GebruikerController;
import domein.dao.BedrijfDao;
import domein.enumerations.WERKNEMERROL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import repository.BedrijfDaoJpa;
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
	@FXML private TableColumn tbcKlantsnr;
	@FXML private TableColumn tbcGebruikersnaam;
	@FXML private TableColumn tbcVoornaam;
	@FXML private TableColumn tbcNaam;
	@FXML private TableColumn tbcBedrijf;
	@FXML private TableColumn tbcStatus;
	
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
		this.gebruikerController = new GebruikerController();
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
	void voegKlantToe(ActionEvent event) {
		Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(), txfPostcode.getText(), 
				txfStraat.getText(), Integer.parseInt(txfHuisnr.getText()), txfBusnr.getText());
		Bedrijf bedrijf = new Bedrijf(txfBedrijfsnaam.getText(), Arrays.asList(txaTelefoonnummers.getText()), adres);
		Klant klant = new Klant(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), 
				txfNaam.getText(), txfEmail.getText(), Integer.parseInt(txfKlantnr.getText()), bedrijf);
		gebruikerController.voegKlantToe(klant);		
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
		
		cboTaalWijzigen.setPromptText(Taal.geefTekst("taalKeuze"));
		cboTaalWijzigen.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
	    cboTaalWijzigen.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
	    	if(nieuweTaal != null) {
	    		Taal.instellenTaal(cboTaalWijzigen.getSelectionModel().getSelectedIndex());
	    		initializeGUIComponenten();
	    	}
	    });
	}
}
