package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.Optional;

import domein.controllers.AanmeldController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Observer;
import resourcebundle.Taal;

public class HoofdMenuController extends BorderPane implements Observer{
	//Header
	@FXML private Label lblTitel;
	@FXML private Label lblBegroeting;
	@FXML private Button btnUitloggen;
	@FXML private Button btnGebruikersInstellingen;
	@FXML private HBox hboxHeader;
	//Navigatie links
	@FXML private VBox vboxNavigatie;
	@FXML private Button btnHoofdmenu;
	@FXML private Label lblTaalWijzigen;
	@FXML private ComboBox<String> cboTaalWijzigen;
	//Navigatie Buttons Admin
	private Label lblGebruikerBeheer;
	private Button btnWerknemerBeheer;
	private Button btnKlantBeheer;
	private Button btnBedrijfBeheer;
	//Navigatie Buttons SupportManager
	private Label lblContractBeheer;
	private Button btnContractBeheer;
	//Navigatie Buttons Technieker
	private Label lblTicketBeheer;
	private Button btnTicketBeheer;
	private Button btnRapportBeheer;
	
	private final StatistiekenSchermController statistiekenSchermController;
	private Object controller = new Object();
	
	public HoofdMenuController() {
		statistiekenSchermController = new StatistiekenSchermController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenu.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    controller = statistiekenSchermController;
	    Taal.addObservers((Observer) controller);
	    this.setCenter((Node) controller);
	    knoppenToevoegen();
	    initializeGUIComponenten();	    
	}

	public void knoppenToevoegen() {
		switch(AanmeldController.getAangemeldeWerknemer().getRol()) {
		case ADMINISTRATOR : 
			lblGebruikerBeheer = new Label(Taal.geefTekst("gebruikerBeheer"));
        	vboxNavigatie.setMargin(lblGebruikerBeheer, new Insets(10,0,0,10));
        	
        	btnKlantBeheer = new Button(Taal.geefTekst("klantBeheer"));
        	vboxNavigatie.setMargin(btnKlantBeheer, new Insets(10,0,0,10));
        	btnKlantBeheer.setOnAction(this::klantenBeheer);
        	btnKlantBeheer.setId("buttonleft");
        	btnKlantBeheer.setPrefWidth(118);
        	btnKlantBeheer.setPrefHeight(26);
        	
        	btnBedrijfBeheer = new Button(Taal.geefTekst("bedrijfsbeheer"));
        	vboxNavigatie.setMargin(btnBedrijfBeheer, new Insets(10,0,0,10));
        	btnBedrijfBeheer.setOnAction(this::bedrijfBeheer);
        	btnBedrijfBeheer.setId("buttonleft");
        	btnBedrijfBeheer.setPrefWidth(118);
        	btnBedrijfBeheer.setPrefHeight(26);
        	
        	btnWerknemerBeheer = new Button(Taal.geefTekst("werknemerbeheer"));
        	vboxNavigatie.setMargin(btnWerknemerBeheer, new Insets(10,0,0,10));
        	btnWerknemerBeheer.setOnAction(this::werknemerBeheer);
        	btnWerknemerBeheer.setId("buttonleft");
        	btnWerknemerBeheer.setPrefWidth(118);
        	btnWerknemerBeheer.setPrefHeight(26);
        	
        	
        	vboxNavigatie.getChildren().addAll(lblGebruikerBeheer,btnKlantBeheer, btnBedrijfBeheer, btnWerknemerBeheer);
        	break;
        
    	case SUPPORTMANAGER : 
    		lblContractBeheer = new Label(Taal.geefTekst("contractBeheer"));
    		vboxNavigatie.setMargin(lblContractBeheer, new Insets(10,0,0,10));
			btnContractBeheer= new Button(Taal.geefTekst("contract"));
			vboxNavigatie.setMargin(btnContractBeheer, new Insets(10,0,0,10));
			btnContractBeheer.setOnAction(this::contractBeheer);
			btnContractBeheer.setId("buttonleft");
			
			lblTicketBeheer = new Label(Taal.geefTekst("ticketBeheer"));
			vboxNavigatie.setMargin(lblTicketBeheer, new Insets(10,0,0,10));
    		btnTicketBeheer = new Button(Taal.geefTekst("ticket"));
    		vboxNavigatie.setMargin(btnTicketBeheer, new Insets(10,0,0,10));
    		btnTicketBeheer.setOnAction(this::ticketBeheer);
    		btnTicketBeheer.setId("buttonleft");
    		
    		btnRapportBeheer = new Button(Taal.geefTekst("rapport"));
    		vboxNavigatie.setMargin(btnRapportBeheer, new Insets(10,0,0,10));
    		btnRapportBeheer.setOnAction(this::rapportBeheer);
    		btnRapportBeheer.setId("buttonleft");
			
        	vboxNavigatie.getChildren().addAll(lblContractBeheer,btnContractBeheer,lblTicketBeheer,btnTicketBeheer,btnRapportBeheer);
        	break;
    	
		case TECHNIEKER : 
			lblTicketBeheer = new Label(Taal.geefTekst("ticketBeheer"));
			vboxNavigatie.setMargin(lblTicketBeheer, new Insets(10,0,0,10));
    		btnTicketBeheer = new Button(Taal.geefTekst("ticket"));
    		vboxNavigatie.setMargin(btnTicketBeheer, new Insets(10,0,0,10));
    		btnTicketBeheer.setOnAction(this::ticketBeheer);
    		btnTicketBeheer.setId("buttonleft");
			
        	vboxNavigatie.getChildren().addAll(lblTicketBeheer,btnTicketBeheer);
        	break;
		}
	}
	public void klantenBeheer(ActionEvent event) {
		KlantBeheerSchermController klantBeheerSchermController = new KlantBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("klant"));
		schermAanpassen(klantBeheerSchermController);
	}
	public void werknemerBeheer(ActionEvent event) {
		WerknemerBeheerSchermController werknemerBeheerSchermController = new WerknemerBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("werknemer"));
		schermAanpassen(werknemerBeheerSchermController);
	}
	public void bedrijfBeheer(ActionEvent event) {
		BedrijfBeheerSchermController bedrijfBeheerSchermController = new BedrijfBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("bedrijf"));
		schermAanpassen(bedrijfBeheerSchermController);
	}
	public void ticketBeheer(ActionEvent event) {
		TicketBeheerSchermController ticketBeheerSchermController = new TicketBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("ticketBeheer"));
		schermAanpassen(ticketBeheerSchermController);
	}
	public void rapportBeheer(ActionEvent event) {
		RapportBeheerSchermController rapportBeheerSchermController = new RapportBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("rapportBeheer"));
		schermAanpassen(rapportBeheerSchermController);
	}
	public void contractBeheer(ActionEvent event) {
		ContractTypeBeheerSchermController contractTypeBeheerSchermController = new ContractTypeBeheerSchermController();
		lblTitel.setText(Taal.geefTekst("contractBeheer"));
		schermAanpassen(contractTypeBeheerSchermController);
	}
	@FXML
	public void hoofdmenu(ActionEvent event) {
		Taal.addObservers(statistiekenSchermController);
		schermAanpassen(statistiekenSchermController);
	}
	public void schermAanpassen(Object o) {
		this.getChildren().remove(controller);
		Taal.removeObservers((Observer) controller);
		controller = o;
		Taal.addObservers((Observer) controller);
		this.setCenter((Node) controller);
	}
	// Event Listener on Button[#btnUitloggen].onAction
	@FXML
	public void uitloggen(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Taal.geefTekst("uitloggenTitel"));
		alert.setHeaderText(Taal.geefTekst("uitloggenHeader"));
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
			AanmeldController.setAangemeldeWerknemer(null);
			Taal.removeObservers(this);
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
	
	// Event Listener on Button[#btnGebruikersInstellingen].onAction
		@FXML
		public void gebruikersInstellingen(ActionEvent event) {
			GebruikerInstellingenSchermController gebruikerInstellingenSchermController = new GebruikerInstellingenSchermController();
			lblTitel.setText(Taal.geefTekst("ticketBeheer"));
			Taal.addObservers(gebruikerInstellingenSchermController);
			schermAanpassen(gebruikerInstellingenSchermController);
		
			}
		
	private void initializeGUIComponenten() {		
		btnUitloggen.setText(Taal.geefTekst("uitloggen"));
		btnGebruikersInstellingen.setText(Taal.geefTekst("accountOverzicht"));
		btnGebruikersInstellingen.setAlignment(Pos.CENTER);
		lblTitel.setText(Taal.geefTekst("hoofdmenu"));	
		btnHoofdmenu.setText(Taal.geefTekst("hoofdmenu"));
		
		String rol = "gebruiker";	
		switch(AanmeldController.getAangemeldeWerknemer().getRol()) {
		case ADMINISTRATOR :
		lblGebruikerBeheer.setText(Taal.geefTekst("gebruikerBeheer"));
		btnWerknemerBeheer.setText(Taal.geefTekst("werknemer"));
		btnKlantBeheer.setText(Taal.geefTekst("klant"));
		btnBedrijfBeheer.setText(Taal.geefTekst("bedrijf"));
		rol = "administrator";
		break;
		
		case SUPPORTMANAGER :
		lblContractBeheer.setText(Taal.geefTekst("contractBeheer"));
		btnContractBeheer.setText(Taal.geefTekst("contractType"));
		lblTicketBeheer.setText(Taal.geefTekst("ticketBeheer"));
		btnTicketBeheer.setText(Taal.geefTekst("ticket"));
		btnRapportBeheer.setText(Taal.geefTekst("rapport"));
		rol = "supportManager";
		break;
		
		case TECHNIEKER :
		lblTicketBeheer.setText(Taal.geefTekst("ticketBeheer"));
		btnTicketBeheer.setText(Taal.geefTekst("ticket"));
		rol = "technieker";
		break;
		}
				
		lblBegroeting.setText(Taal.geefTekst("begroeting") + " " + Taal.geefTekst(rol) + " " + AanmeldController.getAangemeldeWerknemer().getVoornaam());
		
		lblTaalWijzigen.setText(Taal.geefTekst("taalWijzigen"));
		cboTaalWijzigen.setPromptText(Taal.geefTekst("taalKeuze"));
		cboTaalWijzigen.getItems().setAll(Taal.geefTekst("taakKeuzeNL"), Taal.geefTekst("taalKeuzeEN"), Taal.geefTekst("taalKeuzeFR"));
	    cboTaalWijzigen.getSelectionModel().selectedIndexProperty().addListener((observableValie, oudeTaal, nieuweTaal) -> {
	    	if(nieuweTaal != null) {
	    		Taal.instellenTaal(cboTaalWijzigen.getSelectionModel().getSelectedIndex());
	    	}
	    });	    
	}

	@Override
	public void update() {
		initializeGUIComponenten();
		
	}
}
