package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import domein.controllers.AanmeldController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import resourcebundle.Taal;

public class InlogSchermController extends AnchorPane{
	private AanmeldController adc;
	
	@FXML
    private ResourceBundle resources;
	@FXML
    private TextField txtfGebruikersnaam;
	@FXML
    private PasswordField pwfWachtwoord;
	@FXML
    private Text txtActiontarget;
	@FXML
	private Label lblWelkom;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	
	@FXML
    private Button btnLogin;
	
	 @FXML 
	private ComboBox<String> talenCombo; 

	public InlogSchermController(AanmeldController aanmeldController) {
    this.adc = aanmeldController;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("InlogScherm.fxml"));
	loader.setRoot(this);
    loader.setController(this);
    
    try {
        loader.load();
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }	
    
    talenCombo.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
    	if(nieuweTaal != null) {
    		Taal.instellenTaal(talenCombo.getSelectionModel().getSelectedIndex());
    		initialize();
    	}
    });
	}
	
	@FXML
    void login(ActionEvent event) throws SQLException, IOException {
    	if (txtfGebruikersnaam.getText().isEmpty()) {
    		txtActiontarget.setFill(Color.FIREBRICK);
            txtActiontarget.setText(Taal.geefTekst("geenGebruikersnaamFout"));
            return;
        }
        if (pwfWachtwoord.getText().isEmpty()) {
        	txtActiontarget.setFill(Color.FIREBRICK);
        	txtActiontarget.setText(Taal.geefTekst("geenWachtwoordFout"));
            return;
        }
        
        adc.aanmelden(txtfGebruikersnaam.getText(), pwfWachtwoord.getText());
        
       txtfGebruikersnaam.clear();
        pwfWachtwoord.clear();
    	Pane pane = FXMLLoader.load(getClass().getResource("HoofdMenuView.fxml"));
    }
	
	@FXML
	public void initialize() {
		lblWelkom.setText(Taal.geefTekst("welkom"));
		lblGebruikersnaam.setText(Taal.geefTekst("gebruikersnaam"));
		lblWachtwoord.setText(Taal.geefTekst("wachtwoord"));
		talenCombo.setPromptText(Taal.geefTekst("taalKeuze"));
		talenCombo.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
		btnLogin.setText(Taal.geefTekst("inloggen"));	
		txtActiontarget.setText("");
		}
}
