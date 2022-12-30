package de.pbma.java;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewFileController implements Initializable {

	@FXML
	private TextField tfStudentName;
	@FXML
	private TextField tfMatriculationNumber;
	@FXML
	private ComboBox<String> cbCourseOfStudies;
	@FXML
	private Button btCancel;
	@FXML
	private Button btOkay;

	private final ObservableList<String> oList;

	private FileLogic fileLogic;
	private final String newCurriculum = "externes Curriculum";

	public NewFileController() {
		fileLogic = new FileLogic();
		var coursesOfStudies = fileLogic.getCurriculumFiles().keySet();
		oList = FXCollections.observableArrayList(coursesOfStudies);
		oList.add(newCurriculum);
		System.out.println(oList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btOkay.disableProperty()
				.bind(Bindings.isEmpty(tfMatriculationNumber.textProperty())
						.or(Bindings.isEmpty(tfStudentName.textProperty()))
						.or(Bindings.isNull(cbCourseOfStudies.valueProperty())));
		cbCourseOfStudies.setItems(oList);
		tfStudentName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-ZäöüÄÖÜß*")) {
				tfStudentName.setText(newValue.replaceAll("[^\\sa-zA-ZäöüÄÖÜß]", ""));
			}
		});
	}

	@FXML
	public void onCancel() {
		System.out.println("Abgebrochen");
		exit();
	}

	@FXML
	public void onOkay() {
		System.out.println("neue notenübersicht erstellen");
		Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);

		var curriculumType = cbCourseOfStudies.getSelectionModel().getSelectedItem();
		File fileTmp = fileLogic.getCurriculumFiles().getOrDefault(curriculumType, null);
		final var externesCurriculum = (fileTmp==null)?true:false;
		if (externesCurriculum) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
			fileTmp = fileChooser.showOpenDialog(owner);
		}
		if (fileTmp == null) {
			return;
		}
		final File file = fileTmp;
		new Thread(() -> {
			if (!fileLogic.loadCurriculumFile(file)) {
				errorDialog("Banane", "Erdbeere");
			}
			Curriculum curriculum = fileLogic.getCurriculum();
			if(externesCurriculum) {
				fileLogic.saveCurriculumFile(curriculum.getNameShort());
			}
			CurriculumData.getData().setCurriculum(curriculum);
			var name = tfStudentName.getText();
			var matriculationNumber = Integer.parseInt(tfMatriculationNumber.getText());
			Student student = new Student(name, matriculationNumber, curriculum.getNameShort());
			StudentData.getData().setStudentData(student);
			UserFiles.getUserFiles().setStudentFile(Paths.get(student.getName()+".csv").toFile());
		}).start();
		exit();

	}

	private void errorDialog(final String titel, final String msg) {
		Platform.runLater(() -> {
			final Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(titel);
			alert.setContentText(msg);
			alert.show();
		});
	}

	private void exit() {
		Stage stage = (Stage) btCancel.getScene().getWindow();
		stage.close();
	}

}
