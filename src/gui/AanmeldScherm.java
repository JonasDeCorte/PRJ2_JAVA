package gui;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import domain.DCLoginUser;
import domein.controllers.AanmeldController;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class AanmeldScherm extends VBox {

	private AanmeldController dc;

	@FXML
	private TextField txfGebruikersNaam;
	@FXML
	private PasswordField pwfWachtwoord;
	
	
	public AanmeldScherm(AanmeldController dc) {
		this.dcLoginUser = new DCLoginUser();
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUserView.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			System.out.println("Error bij weergeven aanmeldScherm");
			throw new RuntimeException(ex);
		}
	}
	public void setAsScene(Stage stage, Scene scene) {
		scene.setRoot(this);
		stage.setMinWidth(700);
		stage.setMinHeight(700);
	}
	
	@FXML
	public void btnCancelOnAction(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	

}
