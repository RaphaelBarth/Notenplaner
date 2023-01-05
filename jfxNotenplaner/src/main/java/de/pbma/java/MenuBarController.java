package de.pbma.java;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuBarController {
	private FileLogic fileLogic;
	@FXML
	private CheckMenuItem checkDarkModeItem;

	public MenuBarController() {
		fileLogic = new FileLogic();
	}

	@FXML
	private void handleLoadAction(ActionEvent ae) {
		final Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		final File file = fileChooser.showOpenDialog(owner);
		if (file == null) {
			return;
		}
		new Thread(() -> {
			String msgTmp = null;
			if (fileLogic.loadStudentFile(file)) {
				var curriculumName = fileLogic.getStudent().getCourseOfStudies();
				var curriculumFile = fileLogic.getCurriculumFiles().get(curriculumName);
				if (curriculumFile != null && fileLogic.loadCurriculumFile(curriculumFile)) {
					CurriculumData.getData().setCurriculum(fileLogic.getCurriculum());
					StudentData.getData().setStudent(fileLogic.getStudent());
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
		if (filePath == null) {
			return;
		}
		Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		fileChooser.setInitialFileName(filePath.getName());
		fileChooser.setInitialDirectory(filePath.getParentFile());
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
		final File file = fileChooser.showSaveDialog(owner);
		final var student = StudentData.getData().getStudent();
		if (file == null || student == null) {
			return;
		}
		new Thread(() -> {
			fileLogic.setStudent(student);
			fileLogic.saveStudentFile(file);
		}).start();
	}

	@FXML
	private void handleNewAction(ActionEvent ae) {
		final var url = MenuBarController.class.getResource("NewFile.fxml");
		Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
		Stage stage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(url);
			var scene = new Scene(root);
			scene.getStylesheets().addAll(root.getStylesheets());
			stage.setScene(scene);
			stage.setTitle("My modal window");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(owner);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleDarkAction(ActionEvent ae) {
		final var url = MenuBarController.class.getResource("dark-theme.css").toString();
		Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
		var scene = owner.getScene();
		if (checkDarkModeItem.isSelected()) {
			scene.getStylesheets().add(url);
		} else {
			scene.getStylesheets().remove(url);
		}
	}
}
