package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import resourcebundle.Observer;
import resourcebundle.Taal;

import java.io.IOException;
import java.util.Arrays;

import domein.Adres;
import domein.Gebruiker;
import domein.Klant;
import domein.Werknemer;
import domein.controllers.AanmeldController;
import domein.controllers.GebruikerController;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.PasswordField;

public class GebruikerInstellingenSchermController extends GridPane implements Observer{
	
	private final GebruikerController gebruikerController;

	@FXML
	private Label lblWachtwoord;
	@FXML
	private Label lblNieuwWachtwoord;
	@FXML
	private Label lblHerhaalWachtwoord;
	@FXML
	private Label lblLand;
	@FXML
	private Label lblGemeente;
	@FXML
	private Label lblPostcode;
	@FXML
	private Label lblStraat;
	@FXML
	private Label lblHuisnummer;
	@FXML
	private Label lblBusnummer;
	
	@FXML
	private Label lblPersoneelsnummer;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblHierNummer;
	@FXML
	private Label lblHierNaam;
	
	@FXML
	private Label lblVoornaam;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblEmail;
	
	@FXML
	private Label lblTelefoonnummers;
	
	@FXML
	private Button btnGegevensWijzigen;
	
	
	@FXML
	private TextField txfLand;
	@FXML
	private TextField txfGemeente;
	@FXML
	private TextField txfPostcode;
	@FXML
	private TextField txfStraat;
	@FXML
	private TextField txfHuisnummer;
	@FXML
	private TextField txfBusnummer;

	@FXML
	private TextField txfVoornaam;
	@FXML
	private TextField txfNaam;
	@FXML
	private TextField txfEmail;
	@FXML
	private TextArea txaTelefoonnummers;
	
	
	@FXML
	private PasswordField pfNieuwWachtwoord;
	@FXML
	private PasswordField pfWachtwoord;
	@FXML
	private PasswordField pfHerhaalWachtwoord;

	private Werknemer actieveWerknemer;
	
	
	public GebruikerInstellingenSchermController(){
		gebruikerController = new GebruikerController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GebruikerInstellingenScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    initializeGUIComponenten();	
	    
	    Werknemer actieveWerknemer = AanmeldController.getAangemeldeWerknemer(); // !!!! dit moet weg en dus vervangen worden door de actieve werknemer
	    
	    alBeschikbareGegevensInvullen(actieveWerknemer);
	}
	
	private void initializeGUIComponenten() {		
	   
		lblWachtwoord.setText(Taal.geefTekst("wachtwoord"));
		lblNieuwWachtwoord.setText(Taal.geefTekst("nieuwWachtwoord"));
		lblHerhaalWachtwoord.setText(Taal.geefTekst("herhaalWachtwoord"));
	    lblLand.setText(Taal.geefTekst("land"));
	    lblGemeente.setText(Taal.geefTekst("gemeente"));
	    lblPostcode.setText(Taal.geefTekst("postcode"));
	    lblStraat.setText(Taal.geefTekst("straat"));
	    lblHuisnummer.setText(Taal.geefTekst("huisnr"));
	    lblBusnummer.setText(Taal.geefTekst("busnr"));
	    lblGebruikersnaam.setText(Taal.geefTekst("gebruikersnaam"));
	    lblPersoneelsnummer.setText(Taal.geefTekst("personeelsnummer"));
	    lblVoornaam.setText(Taal.geefTekst("voornaam"));
	    lblNaam.setText(Taal.geefTekst("naam"));
	    lblEmail.setText(Taal.geefTekst("e-mail"));
	    lblTelefoonnummers.setText(Taal.geefTekst("telefoonnummers"));
	    btnGegevensWijzigen.setText(Taal.geefTekst("gegevensWijzigen"));
	}
	
	private void alBeschikbareGegevensInvullen(Werknemer Werknemer) {
		
		lblHierNummer.setText(String.valueOf((Werknemer.getPersoneelsnummer())));
		lblHierNaam.setText(Werknemer.getNaam());
		
        txfLand.setText(Werknemer.getAdres().getLand());
        txfGemeente.setText(Werknemer.getAdres().getGemeente());
        txfPostcode.setText(Werknemer.getAdres().getPostcode());
        txfStraat.setText(Werknemer.getAdres().getStraat());
        txfHuisnummer.setText(String.valueOf(Werknemer.getAdres().getHuisnummer()));
        txfBusnummer.setText(Werknemer.getAdres().getBusnummer());  
        txfVoornaam.setText(Werknemer.getVoornaam());
        txfNaam.setText(Werknemer.getNaam());
        txfEmail.setText(Werknemer.getEmailadres());
        Werknemer.getTelefoonnummers().stream()
        .forEach(t-> txaTelefoonnummers.setText(txaTelefoonnummers.getText() + t +"\n" ));
        
       
	}
	
	
	@FXML
	void gegevensWijzigen(ActionEvent event) {
		if(gegevensControleren()) {
			updateGegevens();
			gebruikerController.wijzigWerknemer(actieveWerknemer);
			//werknemerTabelInvullen();
			werknemerWachtwoordVeldenLeegmaken();
	    }
	}

	
	
	
	private boolean gegevensControleren() {
		String opsommingFoutmelding = Taal.geefTekst("opsommingFoutmelding");
		String foutMelding = opsommingFoutmelding;
		
		if(pfNieuwWachtwoord.getText()!= pfHerhaalWachtwoord.getText())
			foutMelding += Taal.geefTekst("wachtwoordenAnders");
		
		if(pfWachtwoord.getText().isEmpty()) 
			foutMelding += Taal.geefTekst("geenWachtwoordFout");
		
		if(pfWachtwoord.getText() != actieveWerknemer.getWachtwoord())
			foutMelding += Taal.geefTekst("verkeerdWachtwoord");
			
		if(txfVoornaam.getText().isBlank()) 
			foutMelding += Taal.geefTekst("verplichtVoornaam");
		if(txfNaam.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtNaam");
		if(pfWachtwoord.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtWachtwoord");
		if(txfEmail.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtEmail");
		if(!txfEmail.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			foutMelding += Taal.geefTekst("ongeldigEmail");
		if(txfLand.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtLand");
		if(txfGemeente.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtGemeente");
		if(txfPostcode.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtPostcode");
		if(txfStraat.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtStraat");
		if(txfHuisnummer.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtHuisnr");
		if(txaTelefoonnummers.getText().isBlank()) 
			foutMelding += Taal.geefTekst("verplichtTelefoonnummers");
		
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
	

	private void updateGegevens() {
		actieveWerknemer.setWachtwoord(pfHerhaalWachtwoord.getText());
		Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(),
				txfPostcode.getText(),txfStraat.getText(),Integer.parseInt(txfHuisnummer.getText()), txfBusnummer.getText());
		actieveWerknemer.setAdres(adres);
		actieveWerknemer.setVoornaam(txfVoornaam.getText());
		actieveWerknemer.setNaam(txfNaam.getText());
		actieveWerknemer.setEmailadres(txfEmail.getText());
		actieveWerknemer.setTelefoonnummers(Arrays.asList(txaTelefoonnummers.getText()));
	}
	
	private void werknemerWachtwoordVeldenLeegmaken() {
		pfWachtwoord.clear();
		pfNieuwWachtwoord.clear();
		pfHerhaalWachtwoord.clear();
	}

	@Override
	public void update() {
		initializeGUIComponenten();
		
	}
	

}





