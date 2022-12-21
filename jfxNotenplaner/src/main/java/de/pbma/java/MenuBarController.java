package de.pbma.java;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuBarController {
	private StudentFileLogic studentFileLogic;

	public MenuBarController() {
		this.studentFileLogic = new StudentFileLogic();
	}

	@FXML
	private void handleLoadAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		File file = fileChooser.showOpenDialog(null);
		new Thread(() -> {
			var success = studentFileLogic.fileLoad(file);
			if (!success) {
				Platform.runLater(() -> {
					final Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle(""); // TODO warnungen richtig schreiben
					alert.setContentText("");
					alert.show();
				});
			}
		}).start();
	}

	@FXML
	private void handleSaveAction(ActionEvent ae) {
		File filePath = studentFileLogic.getCurrentFile();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		fileChooser.setInitialFileName(filePath.getName());
		fileChooser.setInitialDirectory(filePath.getParentFile());
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		File file = fileChooser.showSaveDialog(null);

		new Thread(() -> {
			studentFileLogic.saveToFile(file);
		});
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("New Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = fileChooser.showSaveDialog(null);
		studentFileLogic.fileNew(file);
	}

	@FXML
	private void handleLightAction(ActionEvent ae) {
		System.out.println("Light Mode Design");
	}

	@FXML
	private void handleDarkAction(ActionEvent ae) {
		System.out.println("Dark Mode Design");
	}
}
