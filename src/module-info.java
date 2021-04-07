module projecten2 {
	exports gui;
	exports main;
	exports domein;
	exports persistentie;
	exports testen;
	requires java.sql;
	requires java.persistence;
	requires java.instrument;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens gui to javafx.graphics, javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
	
}