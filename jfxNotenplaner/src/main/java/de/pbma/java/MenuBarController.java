package de.pbma.java;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuBarController {
	private FileLogic fileLogic;

	public MenuBarController() {
		fileLogic = new FileLogic();
	}

	@FXML
	private void handleLoadAction(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		new Thread(() -> {
			String msg = null;
			if (fileLogic.loadStudentFile(file)) {
				File cFile = Paths.get("data/" + fileLogic.getStudent().getCourseOfStudies() + ".csv").toFile();
				if (Files.exists(cFile.toPath()) && fileLogic.loadCurriculumFile(cFile)) {
					CurriculumData.getData().update(fileLogic.getCurriculum());
					StudentData.getStudentData().updateStudentData(fileLogic.getStudent());
				} else {
					msg = "Curriculum für Notensatz konnte nicht gefunden werden.";
				}

			} else {
				msg = "Fehler beim Laden des Notensatzes.";
			}
			if (msg != null) {
				Platform.runLater(() -> {
					final Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fehler beim Laden der Datei");
					alert.setContentText(""); // TODO warnungen richtig schreiben
					alert.show();
				});
			}
		}).start();
	}

	@FXML
	private void handleSaveAction(ActionEvent ae) {
		File filePath = fileLogic.getCurrentStudentFile();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		fileChooser.setInitialFileName(filePath.getName());
		fileChooser.setInitialDirectory(filePath.getParentFile());
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		File file = fileChooser.showSaveDialog(null);
		if (file == null) {
			return;
		}
		new Thread(() -> {
			fileLogic.saveStudentFile(file);
		});
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		File file = new File("IEB.csv");
		UserFiles.getUserFiles().setCurriculumFile(file);
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
