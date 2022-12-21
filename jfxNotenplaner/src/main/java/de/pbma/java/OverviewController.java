package de.pbma.java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class OverviewController {

	@FXML
	private Label lblMatriculationNumber; //TODO in FXML hinzufügen
	@FXML
	private Label lblStudentName; //TODO in FXML hinzufügen
	@FXML
	private Label lblCourseOfStudies;
	@FXML
	private Label lblCurrentCredits;
	@FXML
	private Label lblMaxCredits;
	@FXML
	private Label lblCurrentGrade;
	@FXML
	private Label lblBestGrade;
	@FXML
	private Label lblWorstGrade;
	@FXML
	private ProgressIndicator pgiCredits;
	
	@FXML
	public void initialize() {
		//TODO connect to studentData
		var data = StudentData.getStudentData();
//		lblStudentName.textProperty().bind(null);
//		lblMatriculationNumber.textProperty().bind(data.getMatriculationNumberProperty());
		lblCourseOfStudies.textProperty().bind(data.getCourseOfStudiesProperty());
//		lblCurrentCredits.textProperty().bind(null);
//		lblMaxCredits.textProperty().bind(null);
//		lblCurrentGrade.textProperty().bind(null);
//		lblBestGrade.textProperty().bind(null);
//		lblWorstGrade.textProperty().bind(null);
//		pgiCredits.progressProperty();

	}
}
