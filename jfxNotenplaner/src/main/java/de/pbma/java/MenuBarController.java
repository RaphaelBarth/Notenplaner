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
		final File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		new Thread(() -> {
			String msgTmp = null;
			if (fileLogic.loadStudentFile(file)) {
				var curriculum = fileLogic.getStudent().getCourseOfStudies();
				var curriculumFile = fileLogic.getCurriculumFiles().get(curriculum);
				if (curriculumFile != null && fileLogic.loadCurriculumFile(curriculumFile)) {
					CurriculumData.getData().update(fileLogic.getCurriculum());
					StudentData.getStudentData().updateStudentData(fileLogic.getStudent());
				} else {
					msgTmp = "Curriculum für Notensatz konnte nicht gefunden werden.";
				}

			} else {
				msgTmp = "Fehler beim Laden des Notensatzes.";
			}
			final String msg = msgTmp;
			if (msgTmp != null) {
				Platform.runLater(() -> {
					final Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fehler beim Laden der Datei");
					alert.setContentText(msg);
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
		final File file = fileChooser.showSaveDialog(null);
		if (file == null) {
			return;
		}
		new Thread(() -> {
			var student = StudentData.getStudentData().getStudent();
			fileLogic.setStudent(student);
			fileLogic.saveStudentFile(file);
		}).start();
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		MenuBarController.class.getResource("NewFile.fxml");
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
