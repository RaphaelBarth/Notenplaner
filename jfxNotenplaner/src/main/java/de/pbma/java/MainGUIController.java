package de.pbma.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class MainGUIController {
	@FXML private MenuBar menuBar;
	private boolean isLight;
	
	public MainGUIController(){
		isLight=true;
	}
	
	@FXML
	private void handleLoadAction(ActionEvent ae) {
		System.out.println("Datei laden");
	}
	
	@FXML
	private void handleSaveAction(ActionEvent ae) {
		System.out.println("Datei speichern");
	}
	
	@FXML
	private void handleNewAction(ActionEvent ae) {
		System.out.println("Neu");
	}
	
	@FXML
	private void handleLightAction(ActionEvent ae) {
		setMode(true);
	}
	
	@FXML
	private void handleDarkAction(ActionEvent ae) {
		setMode(false);
	}
	
	private void setMode(boolean light) {
		if(isLight==light) {
			return;
		}
		System.out.println("Farbe wechseln");
		isLight = light;
	}
}
