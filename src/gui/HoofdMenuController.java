package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.SQLException;
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
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblBegroeting;
	@FXML
	private Button btnUitloggen;
	@FXML
	private VBox vboxNavigatie;
	
	private final StatistiekenSchermController statistiekenSchermController;
	private  KlantBeheerSchermController klantBeheerSchermController;
	private  WerknemerBeheerSchermController werknemerBeheerSchermController;
	private  TicketBeheerSchermController ticketBeheerSchermController;
	private ContractTypeBeheerSchermController contractTypeBeheerSchermController;
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
	    this.add(statistiekenSchermController, 1, 1,3,3);
	    knoppenToevoegen();
	    
	}

	public void knoppenToevoegen() {
		switch(AanmeldController.getAangemeldeWerknemer().getRol()) {
		case ADMINISTRATOR : 
			klantBeheerSchermController = new KlantBeheerSchermController();
			werknemerBeheerSchermController = new WerknemerBeheerSchermController();
        	Label lblGebruikerBeheer = new Label("GebruikerBeheer");
        	Button btnKlantenBeheer = new Button("KlantenBeheer");
        	btnKlantenBeheer.setOnAction(this::klantenBeheer);
        	Button btnWerknemerBeheer = new Button("WerknemerBeheer");
        	btnWerknemerBeheer.setOnAction(this::werknemerBeheer);
        	vboxNavigatie.getChildren().addAll(lblGebruikerBeheer,btnKlantenBeheer,btnWerknemerBeheer);
        	break;
        
    	case SUPPORTMANAGER : 
    		contractTypeBeheerSchermController = new ContractTypeBeheerSchermController();
    		Label lblContractBeheer = new Label("contractBeheer");
			Button btnContractBeheer= new Button("contractBeheer");
			btnContractBeheer.setOnAction(this::contractBeheer);
        	vboxNavigatie.getChildren().addAll(lblContractBeheer,btnContractBeheer);
        	break;
    	
		case TECHNIEKER : 
			ticketBeheerSchermController = new TicketBeheerSchermController();
			Label lblTicketBeheer = new Label("ticketBeheer");
    		Button btnTicketBeheer = new Button("ticketBeheer");
    		btnTicketBeheer.setOnAction(this::ticketBeheer);
			
        	vboxNavigatie.getChildren().addAll(lblTicketBeheer,btnTicketBeheer);
        	break;
		}
	}
	public void klantenBeheer(ActionEvent event) {
		this.getChildren().removeAll(statistiekenSchermController,klantBeheerSchermController,werknemerBeheerSchermController);
		this.add(klantBeheerSchermController,1, 1,3,3);
	}
	public void werknemerBeheer(ActionEvent event) {
		this.getChildren().removeAll(statistiekenSchermController,klantBeheerSchermController,werknemerBeheerSchermController);
		this.add(werknemerBeheerSchermController,1, 1,3,3);
	}
	public void ticketBeheer(ActionEvent event) {
		this.getChildren().removeAll(statistiekenSchermController,ticketBeheerSchermController);
		this.add(ticketBeheerSchermController,1, 1,3,3);
	}
	public void contractBeheer(ActionEvent event) {
		this.getChildren().removeAll(statistiekenSchermController,contractTypeBeheerSchermController);
		this.add(contractTypeBeheerSchermController,1, 1,3,3);
	}
	@FXML
	public void hoofdmenu(ActionEvent event) {
		this.getChildren().removeAll(statistiekenSchermController,klantBeheerSchermController,werknemerBeheerSchermController,ticketBeheerSchermController,contractTypeBeheerSchermController);
		this.add(statistiekenSchermController,1, 1,3,3);
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
}
