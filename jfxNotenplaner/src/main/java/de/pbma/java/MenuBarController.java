package de.pbma.java;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MenuBarController {
	private MenuBarLogic menuBarLogic;

	public MenuBarController() {
		this.menuBarLogic = new MenuBarLogic();
	}

	@FXML
	private void handleLoadAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("maven", "*.xml")); // TODO: filter anpassen
		File file = fileChooser.showOpenDialog(null);
		menuBarLogic.fileLoad(file);
	}

	@FXML
	private void handleSaveAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		File filePath = menuBarLogic.fileToSave();
		fileChooser.setInitialFileName(filePath.getName());
		fileChooser.setInitialDirectory(filePath.getParentFile());
		fileChooser.getExtensionFilters().add(new ExtensionFilter("maven", "*.xml")); // TODO: filter anpassen

		File file = fileChooser.showSaveDialog(null);
		menuBarLogic.fileSave(file);
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("New Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = fileChooser.showSaveDialog(null);
		menuBarLogic.fileNew(file);
	}

	@FXML
	private void handleLightAction(ActionEvent ae) {
		menuBarLogic.settingsMode(true);
	}

	@FXML
	private void handleDarkAction(ActionEvent ae) {
		menuBarLogic.settingsMode(false);
	}
}
