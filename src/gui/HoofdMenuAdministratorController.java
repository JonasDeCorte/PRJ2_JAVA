package gui;

import java.io.IOException;

import domein.controllers.AanmeldController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class HoofdMenuAdministratorController extends AnchorPane {
	private AanmeldController adc;
	public HoofdMenuAdministratorController(AanmeldController aanmeldController) {
		this.adc = aanmeldController;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdMenuAdministrator.fxml"));
		loader.setRoot(this);
	    loader.setController(this);
	    
	    try {
	        loader.load();
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }
		
	}
}
