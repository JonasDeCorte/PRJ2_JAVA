package gui;

import java.io.IOException;
import java.sql.SQLException;

import domein.controllers.AanmeldController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class InlogSchermController extends AnchorPane{
	private AanmeldController adc;
	@FXML private AnchorPane loginPane;
		
	@FXML private Label lblGebruikersnaam;
	@FXML private TextField txtfGebruikersnaam;
	@FXML private Label lblWachtwoord;
	@FXML private PasswordField pwfWachtwoord;
	@FXML private Text txtActiontarget;
	@FXML private Label lblWelkom;	
	@FXML private Button btnLogin;
	@FXML private ComboBox<String> talenCombo; 

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
        Stage stage = (Stage) this.getScene().getWindow();
        
        	stage.setTitle("HoofdMenuAdministrator");
        	HoofdMenuController root1 = new HoofdMenuController();
        	Scene scene = new Scene(root1);
    		stage.setScene(scene);
    		
    		stage.setOnShown((WindowEvent t) -> {
                stage.setMinWidth(stage.getWidth());
                stage.setMinHeight(stage.getHeight());
            });
            stage.show();
    }
	
	@FXML
	public void initialize() {		
	    talenCombo.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
	    	if(nieuweTaal != null) {
	    		Taal.instellenTaal(talenCombo.getSelectionModel().getSelectedIndex());
	    		initialize();
	    	}
	    });
		lblWelkom.setText(Taal.geefTekst("welkom"));
		lblGebruikersnaam.setText(Taal.geefTekst("gebruikersnaam"));
		lblWachtwoord.setText(Taal.geefTekst("wachtwoord"));
		talenCombo.setPromptText(Taal.geefTekst("taalKeuze"));
		talenCombo.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
		btnLogin.setText(Taal.geefTekst("inloggen"));	
		txtActiontarget.setText("");
		}
}
