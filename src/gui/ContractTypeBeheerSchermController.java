package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.PasswordField;

import javafx.scene.control.CheckBox;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ContractTypeBeheerSchermController extends HBox{
	@FXML
	private Label lblFilters;
	@FXML
	private CheckBox chkActieveKlanten;
	@FXML
	private CheckBox chkInactieveKlanten;
	@FXML
	private CheckBox chkGeblokkeerdeKlanten;
	@FXML
	private TextField txfFilterGebruikersnaam;
	@FXML
	private TextField txfFilterVoornaam;
	@FXML
	private TextField txfFilterNaam;
	@FXML
	private TextField txfFilterBedrijf;
	@FXML
	private Button btnClearFilters;
	@FXML
	private TableView tblKlanten;
	@FXML
	private TableColumn tbcKlantsnr;
	@FXML
	private TableColumn tbcGebruikersnaam;
	@FXML
	private TableColumn tbcVoornaam;
	@FXML
	private TableColumn tbcNaam;
	@FXML
	private TableColumn tbcBedrijf;
	@FXML
	private CheckBox chkGeblokkeerdeKlanten1;
	@FXML
	private Label lblKlantgegevens;
	@FXML
	private Label lblKlantnr;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private Label lblVoornaam;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblBedrijfsnaam;
	@FXML
	private Label lblLand;
	@FXML
	private Label lblTelefoonnummers;
	@FXML
	private TextArea txaTelefoonnummers;
	@FXML
	private TextField txfKlantnr;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private PasswordField pwfWachtwoord;
	@FXML
	private TextField txfBedrijfsnaam;
	@FXML
	private TextField txfLand;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private TextArea txaTelefoonnummers1;
	@FXML
	private Label lblWachtwoord1;
	
public ContractTypeBeheerSchermController() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContractTypeBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    
	    
	}

	// Event Listener on CheckBox[#chkActieveKlanten].onAction
	@FXML
	public void toonActieve(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkInactieveKlanten].onAction
	@FXML
	public void toonInactieve(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten].onAction
	@FXML
	public void toonAfgehandelde(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterGebruikersnaam].onKeyReleased
	@FXML
	public void filterGebruiker(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterVoornaam].onKeyReleased
	@FXML
	public void filterVoornaam(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterNaam].onKeyReleased
	@FXML
	public void filterNaam(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#txfFilterBedrijf].onKeyReleased
	@FXML
	public void filterBedrijf(KeyEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnClearFilters].onAction
	@FXML
	public void clear(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on CheckBox[#chkGeblokkeerdeKlanten1].onAction
	@FXML
	public void toonGeannuleerde(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnClearFilters1].onAction
	@FXML
	public void clearKlantgegevens(ActionEvent event) {
		// TODO Autogenerated
	}
}