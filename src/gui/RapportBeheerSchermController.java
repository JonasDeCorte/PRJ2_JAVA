package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import domein.controllers.BedrijfsBeheerController;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

import javafx.scene.input.KeyEvent;

import javafx.scene.control.TableView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import resourcebundle.Observer;
import javafx.scene.control.TableColumn;

public class RapportBeheerSchermController extends HBox implements Observer {
	@FXML
	private Label lblFilters;
	@FXML
	private TextField txfFilterTitel;
	@FXML
	private Button btnClearFilters;
	@FXML
	private TableView tblTickets;
	@FXML
	private TableColumn tbcTicketNr;
	@FXML
	private TableColumn tbcTitel;
	@FXML
	private TableColumn tbcDatumAangemaakt;
	@FXML
	private TableColumn tbcContract;
	@FXML
	private TableColumn tbcStatus;
	@FXML
	private TextField txfFilterTitel1;
	@FXML
	private GridPane grdTicketGegevens;
	@FXML
	private Label lblTicketgegevens;
	@FXML
	private Label lblRapportNr;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblBeschrijving;
	@FXML
	private Label lblOplossing;
	@FXML
	private TextArea txaOplossing;
	@FXML
	private TextField txfRapportNr;
	@FXML
	private TextField txfNaam;
	@FXML
	private Button btnClearFilters1;
	@FXML
	private TextArea txaBeschrijving;
	@FXML
	private Button btnRapportWijzigen;
	@FXML
	private Label lblTicket;
	@FXML
	private ComboBox cbTicket;

	//private final RapportBeheerSchermController rapportBeheerController;
	
	public RapportBeheerSchermController() {
		// TODO Auto-generated constructor stub	
		//this.rapportBeheerController = new RapportBeheerSchermController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RapportBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    
	   
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
