package main;

import domein.controllers.AanmeldController;
import gui.InlogSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcebundle.Taal;

public class StartUp extends Application{
    public static void main(String... args) {
    	 Taal.instellenTaal(1);
    	 Application.launch(StartUp.class, args);
    }


	@Override
	public void start(Stage stage) throws Exception {
		InlogSchermController root = new InlogSchermController(new AanmeldController());
		Scene scene = new Scene(root);
		stage.setTitle("Actemium");
		stage.setScene(scene);
		
		stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
	}
    
}
