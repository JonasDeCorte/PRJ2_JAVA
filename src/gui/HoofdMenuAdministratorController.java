package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import domein.controllers.AanmeldController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class HoofdMenuAdministratorController extends AnchorPane {
	
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
	
	// Statistieken en grafieken (midden)
	// TODO
	
	public HoofdMenuAdministratorController() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenuAdministrator.fxml"));
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
    void KlantBeheren(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setTitle("KlantBeheren");
		KlantBeheerSchermController root1 = new KlantBeheerSchermController();
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
		WerknemerBeheerSchermController root = new WerknemerBeheerSchermController();
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
		alert.setTitle(Taal.geefTekst("uitloggenTitel"));
		alert.setHeaderText(Taal.geefTekst("uitloggenHeader"));
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setTitle("Actemium");
			InlogSchermController root = new InlogSchermController(new AanmeldController());
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
	
	private void initializeGUIComponenten() {		
		btnUitloggen.setText(Taal.geefTekst("uitloggen"));
		lblTitel.setText(Taal.geefTekst("hoofdmenu"));
		lblBegroeting.setText(Taal.geefTekst("begroeting") + " " + Taal.geefTekst("administrator"));
		
		btnHoofdmenu.setText(Taal.geefTekst("hoofdmenu"));
		lblGebruikerBeheer.setText(Taal.geefTekst("gebruikerBeheer"));
		btnKlantBeheer.setText(Taal.geefTekst("klant"));
		btnWerknemerBeheer.setText(Taal.geefTekst("werknemer"));
		lblTaalWijzigen.setText(Taal.geefTekst("taalWijzigen"));
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
