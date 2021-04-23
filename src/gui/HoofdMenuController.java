package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domein.controllers.AanmeldController;
import domein.controllers.GebruikerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class HoofdMenuController extends GridPane {
	//Header
	@FXML private Label lblTitel;
	@FXML private Label lblBegroeting;
	@FXML private Button btnUitloggen;
	//Navigatie links
	@FXML private VBox vboxNavigatie;
	@FXML private Button btnHoofdmenu;
	@FXML private Label lblTaalWijzigen;
	@FXML private ComboBox<String> cboTaalWijzigen;
	
	private final StatistiekenSchermController statistiekenSchermController;
	private  KlantBeheerSchermController klantBeheerSchermController;
	private  WerknemerBeheerSchermController werknemerBeheerSchermController;
	private  TicketBeheerSchermController ticketBeheerSchermController;
	private ContractTypeBeheerSchermController contractTypeBeheerSchermController;
	private Object controller = new Object();
	public HoofdMenuController() {
		statistiekenSchermController = new StatistiekenSchermController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenu.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    controller = statistiekenSchermController;
	    this.add((Node) controller, 1, 1,3,3);
	    initializeGUIComponenten();
	    knoppenToevoegen();
	    
	}

	public void knoppenToevoegen() {
		switch(AanmeldController.getAangemeldeWerknemer().getRol()) {
		case ADMINISTRATOR : 
        	Label lblGebruikerBeheer = new Label("GebruikerBeheer");
        	Button btnKlantenBeheer = new Button("KlantenBeheer");
        	btnKlantenBeheer.setOnAction(this::klantenBeheer);
        	Button btnWerknemerBeheer = new Button("WerknemerBeheer");
        	btnWerknemerBeheer.setOnAction(this::werknemerBeheer);
        	vboxNavigatie.getChildren().addAll(lblGebruikerBeheer,btnKlantenBeheer,btnWerknemerBeheer);
        	break;
        
    	case SUPPORTMANAGER : 
    		Label lblContractBeheer = new Label("contractBeheer");
			Button btnContractBeheer= new Button("contractBeheer");
			btnContractBeheer.setOnAction(this::contractBeheer);
        	vboxNavigatie.getChildren().addAll(lblContractBeheer,btnContractBeheer);
        	break;
    	
		case TECHNIEKER : 
			Label lblTicketBeheer = new Label("ticketBeheer");
    		Button btnTicketBeheer = new Button("ticketBeheer");
    		btnTicketBeheer.setOnAction(this::ticketBeheer);
			
        	vboxNavigatie.getChildren().addAll(lblTicketBeheer,btnTicketBeheer);
        	break;
		}
	}
	public void klantenBeheer(ActionEvent event) {
		klantBeheerSchermController = new KlantBeheerSchermController();
		schermAanpassen(klantBeheerSchermController);
	}
	public void werknemerBeheer(ActionEvent event) {
		werknemerBeheerSchermController = new WerknemerBeheerSchermController();
		schermAanpassen(werknemerBeheerSchermController);
	}
	public void ticketBeheer(ActionEvent event) {
		ticketBeheerSchermController = new TicketBeheerSchermController();
		schermAanpassen(ticketBeheerSchermController);
	}
	public void contractBeheer(ActionEvent event) {
		contractTypeBeheerSchermController = new ContractTypeBeheerSchermController();
		schermAanpassen(contractTypeBeheerSchermController);
	}
	@FXML
	public void hoofdmenu(ActionEvent event) {
		schermAanpassen(statistiekenSchermController);
	}
	public void schermAanpassen(Object o) {
		this.getChildren().remove(controller);
		controller = o;
		this.add((Node) controller,1, 1,3,3);
	}
	// Event Listener on Button[#btnUitloggen].onAction
	@FXML
	public void uitloggen(ActionEvent event) {
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
		lblTitel.setText(Taal.geefTekst("werknemerbeheer"));
		lblBegroeting.setText(Taal.geefTekst("begroeting") + " " + Taal.geefTekst("administrator"));
		
		btnHoofdmenu.setText(Taal.geefTekst("hoofdmenu"));
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
