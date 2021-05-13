package gui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.Adres;
import domein.Bedrijf;
import domein.Klant;
import domein.controllers.BedrijfsBeheerController;
import domein.enumerations.GEBRUIKERSTATUS;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.PasswordField;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import resourcebundle.Observer;
import resourcebundle.Taal;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

public class BedrijfBeheerSchermController extends HBox implements Observer{
	@FXML private Label lblFilters;
	@FXML private TextField txfFilterBedrijfsnaam;
	@FXML private TextField txfFilterLand;
	@FXML private TextField txfFilterGemeente;
	@FXML private Button btnFiltersLeegmaken;
	@FXML private TableView<Bedrijf> tblBedrijven;
	@FXML private TableColumn<Bedrijf, Integer> tbcBedrijfsNr;
	@FXML private TableColumn<Bedrijf, String> tbcBedrijfsnaam;
	@FXML private TableColumn<Bedrijf, String> tbcLand;
	@FXML private TableColumn<Bedrijf, String> tbcGemeente;
	@FXML private Label lblBedrijfsgegevens;
	@FXML private Label lblBedrijfsNr;
	@FXML private Label lblBedrijfsnaam;
	@FXML private Label lblLand;
	@FXML private Label lblGemeente;
	@FXML private Label lblPostcode;
	@FXML private Label lblStraat;
	@FXML private Label lblTelefoonnummers;
	@FXML private TextArea txaTelefoonnummers;
	@FXML private TextField txfBedrijfnr;
	@FXML private TextField txfBedrijfsnaam;
	@FXML private TextField txfLand;
	@FXML private TextField txfGemeente;
	@FXML private TextField txfPostcode;
	@FXML private TextField txfStraat;
	@FXML private Label lblHuisnr;
	@FXML private TextField txfHuisnr;
	@FXML private Label lblBusnr;
	@FXML private TextField txfBusnr;
	@FXML private Button btnBedrijfsgegevensLegen;
	@FXML private Button btnBedrijfWijzigen;
	@FXML private Button btnBedrijfToevoegen;

	private Bedrijf geselecteerdBedrijf;
	private final BedrijfsBeheerController bedrijfsBeheerController;
	private String origineelBedrijfsnr;
	private String origineleBedrijfsnaam;
	
	public BedrijfBeheerSchermController(){	
		this.bedrijfsBeheerController = new BedrijfsBeheerController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BedrijfBeheerScherm.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
	    
	    initializeGUIComponenten();
	    bedrijfTabelInvullen();
	}
	
	private void initializeGUIComponenten() {
	    tblBedrijven.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oudBedrijf, NieuwBedrijf) -> {
        	if(NieuwBedrijf != null) {
        		geselecteerdBedrijf = NieuwBedrijf;
        		bedrijfDetailsInvullen(NieuwBedrijf);
        	}   	
        });  
	    
	    txfFilterBedrijfsnaam.setPromptText(Taal.geefTekst("bedrijfsnaam"));
	    txfFilterGemeente.setPromptText(Taal.geefTekst("gemeente"));
	    txfFilterLand.setPromptText(Taal.geefTekst("land"));
	    tbcBedrijfsNr.setText(Taal.geefTekst("bedrijfsnr"));
	    tbcBedrijfsnaam.setText(Taal.geefTekst("bedrijfsnaam"));
	    tbcLand.setText(Taal.geefTekst("land"));
	    tbcGemeente.setText(Taal.geefTekst("gemeente"));
	    btnFiltersLeegmaken.setText(Taal.geefTekst("leegmaken"));
	    
	    lblBedrijfsNr.setText(Taal.geefTekst("bedrijfsnummer"));
		lblBedrijfsgegevens.setText(Taal.geefTekst("bedrijfsgegevens"));
		lblBedrijfsnaam.setText(Taal.geefTekst("bedrijfsnaam"));
	    lblTelefoonnummers.setText(Taal.geefTekst("telefoonnummers"));
	    lblLand.setText(Taal.geefTekst("land"));
	    lblGemeente.setText(Taal.geefTekst("gemeente"));
	    lblPostcode.setText(Taal.geefTekst("postcode"));
	    lblStraat.setText(Taal.geefTekst("straat"));
	    lblHuisnr.setText(Taal.geefTekst("huisnr"));
	    lblBusnr.setText(Taal.geefTekst("busnr"));	
	    
	    btnBedrijfsgegevensLegen.setText(Taal.geefTekst("leegmaken"));
	    btnBedrijfToevoegen.setText(Taal.geefTekst("bedrijfToevoegen"));
	    btnBedrijfWijzigen.setText(Taal.geefTekst("bedrijfWijzigen"));
	}
	

	@FXML
	public void filterBedrijfsnaam(KeyEvent event) {
		bedrijfTabelFilteren();
	}

	@FXML
	public void filterLand(KeyEvent event) {
		bedrijfTabelFilteren();
	}

	@FXML
	public void filterGemeente(KeyEvent event) {
		bedrijfTabelFilteren();
	}

	@FXML
	public void clear(ActionEvent event) {
		txfFilterBedrijfsnaam.clear();
		txfFilterGemeente.clear();
		txfFilterLand.clear();
		bedrijfTabelFilteren();
	}

	@FXML
	public void clearBedrijfgegevens(ActionEvent event) {
		bedrijfDetailsLeegmaken();
		
	}

	@FXML
	public void bedrijfWijzigen(ActionEvent event) {
		updateBedrijfAttributen();
		if(bedrijfDetailsControleren()) {
			bedrijfsBeheerController.wijzigBedrijf(geselecteerdBedrijf, origineleBedrijfsnaam);
			bedrijfTabelInvullen();
	    	bedrijfDetailsLeegmaken();
	    	btnBedrijfToevoegen.setDisable(false);
	    }
	}
	
	private void updateBedrijfAttributen() {
		geselecteerdBedrijf.setBedrijfId(Integer.parseInt(txfBedrijfnr.getText()));
		geselecteerdBedrijf.setBedrijfsnaam(txfBedrijfsnaam.getText());
		Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(), txfPostcode.getText(),txfStraat.getText(), Integer.parseInt(txfHuisnr.getText()), txfBusnr.getText());
		geselecteerdBedrijf.setAdres(adres);	
		
		List<String> telefoonnummers = Arrays.asList(txaTelefoonnummers.getText().split("\n"));
		geselecteerdBedrijf.setTelefoonnummers(telefoonnummers);	
	}
	
	private boolean bedrijfDetailsControleren() {
		String opsommingFoutmelding = Taal.geefTekst("opsommingFoutmelding");
		String foutMelding = opsommingFoutmelding;
		if(txfBedrijfnr.getText().isBlank()) 
			foutMelding += Taal.geefTekst("verplichtBedrijfsnummer");
		if(txfBedrijfsnaam.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtBedrijfsnaam");
		if(!txfBedrijfsnaam.getText().isBlank() && bedrijfsBeheerController.bestaatBedrijf(txfBedrijfsnaam.getText()) && !txfBedrijfsnaam.getText().equals(origineleBedrijfsnaam))
			foutMelding += Taal.geefTekst("bedrijfsnaamAlGebruikt");
		if(txfLand.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtLand");
		if(txfGemeente.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtGemeente");
		if(txfPostcode.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtPostcode");
		if(txfStraat.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtStraat");
		if(txfHuisnr.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtHuisnr");
		if(txaTelefoonnummers.getText().isBlank())
			foutMelding += Taal.geefTekst("verplichtTelefoonnummers");

		if(foutMelding.equals(opsommingFoutmelding)) {
			return true;
		} else {
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle(Taal.geefTekst("foutmeldingTitel"));
			alert.setHeaderText(Taal.geefTekst("foutmeldingHeaderBedrijf"));
			alert.setContentText(foutMelding);
			alert.showAndWait();
			return false;
		}
	}

	@FXML
	public void voegBedrijfToe(ActionEvent event) {
		if(bedrijfDetailsControleren()) {	
			Adres adres = new Adres(txfLand.getText(), txfGemeente.getText(), txfPostcode.getText(), 
					txfStraat.getText(), Integer.parseInt(txfHuisnr.getText()), txfBusnr.getText());
			List<String> telefoonnummers = Arrays.asList(txaTelefoonnummers.getText().split("\n"));
			Bedrijf bedrijf = new Bedrijf(txfBedrijfsnaam.getText(),telefoonnummers, adres);
			bedrijfsBeheerController.voegBedrijfToe(bedrijf);
			bedrijfTabelInvullen();
			bedrijfDetailsLeegmaken();
			
			}
	}
	
	private void bedrijfTabelFilteren() {
		String bedrijfsnaam,land,gemeente;
		bedrijfsnaam = txfFilterBedrijfsnaam.getText();
		land = txfFilterLand.getText();
		gemeente = txfFilterGemeente.getText();
		bedrijfsBeheerController.pasFilterAanBedrijf(bedrijfsnaam, land, gemeente);
	}
	
	private void bedrijfTabelInvullen() {
		 tbcBedrijfsNr.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getBedrijfId()).asObject());
		    tbcBedrijfsnaam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBedrijfsnaam()));
	        tbcGemeente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdres().getGemeente()));
	        tbcLand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdres().getLand()));
	        tblBedrijven.setItems(bedrijfsBeheerController.getAllBedrijven());
	        bedrijfTabelFilteren();
	        bedrijfDetailsLeegmaken();
	        tblBedrijven.refresh();
	}

	private void bedrijfDetailsLeegmaken() {
		txfBedrijfnr.clear();
        txfBedrijfsnaam.clear();
        txfLand.clear();
        txfGemeente.clear();    
        txfPostcode.clear();
        txfStraat.clear();
        txfHuisnr.clear();
        txfBusnr.clear();
        txaTelefoonnummers.clear();
        btnBedrijfToevoegen.setDisable(false);
        origineelBedrijfsnr = null;
        origineleBedrijfsnaam = null;
        txfBedrijfnr.setEditable(true);
	}
	
	private void bedrijfDetailsInvullen(Bedrijf bedrijf) {
		bedrijfDetailsLeegmaken();
		txfBedrijfnr.setText(Integer.toString(bedrijf.getBedrijfId()));
		txfBedrijfnr.setEditable(false);
		origineelBedrijfsnr = txfBedrijfnr.getText();
        txfBedrijfsnaam.setText(bedrijf.getBedrijfsnaam());
        origineleBedrijfsnaam = txfBedrijfsnaam.getText();
        txfLand.setText(bedrijf.getAdres().getLand());
        txfGemeente.setText(bedrijf.getAdres().getGemeente());     
        txfPostcode.setText(bedrijf.getAdres().getPostcode());
        txfStraat.setText(bedrijf.getAdres().getStraat());
        txfHuisnr.setText(String.valueOf(bedrijf.getAdres().getHuisnummer()));
        txfBusnr.setText(bedrijf.getAdres().getBusnummer());  
        ObservableList<String> nrs = FXCollections.observableArrayList(bedrijf.getTelefoonnummers());
        bedrijf.getTelefoonnummers().stream()
        .forEach(t-> txaTelefoonnummers.setText(txaTelefoonnummers.getText() + t +"\n" ));
    
        btnBedrijfToevoegen.setDisable(true);		
	}

	@Override
	public void update() {
		initializeGUIComponenten();
		
	}
}
