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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class InlogSchermController extends AnchorPane{
	private AanmeldController adc;
	
	@FXML
    private ResourceBundle resources;
	@FXML
    private TextField txtfGebruikersNaam;
	@FXML
    private PasswordField pwfWachtwoord;
	@FXML
    private Text txtActiontarget;
	
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
	}
	@FXML
    void login(ActionEvent event) throws SQLException, IOException {
    	if (txtfGebruikersNaam.getText().isEmpty()) {
    		txtActiontarget.setFill(Color.FIREBRICK);
            txtActiontarget.setText("Gebruikersnaam moet ingevuld worden");
            return;
        }
        if (pwfWachtwoord.getText().isEmpty()) {
        	txtActiontarget.setFill(Color.FIREBRICK);
        	txtActiontarget.setText("Wachtwoord moet ingevuld worden");
            return;
        }
        
        adc.aanmelden(txtfGebruikersNaam.getText(), pwfWachtwoord.getText());
        
       txtfGebruikersNaam.clear();
        pwfWachtwoord.clear();
    	Pane pane = FXMLLoader.load(getClass().getResource("HoofdMenuView.fxml"));
    }
	@FXML
	public void initialize() {
		talenCombo.getItems().setAll("Nederlands","Frans","Engels");
	}
	
}
