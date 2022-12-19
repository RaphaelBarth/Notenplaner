package de.pbma.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuBarController {
	private MenuBarLogic menuBarLogic;

	public MenuBarController() {
		this.menuBarLogic = new MenuBarLogic();
	}

	@FXML
	private void handleLoadAction(ActionEvent ae) {
		menuBarLogic.fileLoad();
	}

	@FXML
	private void handleSaveAction(ActionEvent ae) {
		menuBarLogic.fileSave();
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		menuBarLogic.fileNew();
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
