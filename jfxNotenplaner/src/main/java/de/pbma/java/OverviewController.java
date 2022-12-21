package de.pbma.java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class OverviewController {

	@FXML
	private Label lblMatriculationNumber;
	@FXML
	private Label lblStudentName;
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
		// TODO connect to studentData
		var data = StudentData.getStudentData();
		lblStudentName.textProperty().bind(data.getNameProperty());
		data.getMatriculationNumberProperty()
				.addListener((observable, oldVal, newVal) -> lblMatriculationNumber.setText(newVal.toString()));
		lblCourseOfStudies.textProperty().bind(data.getCourseOfStudiesProperty());
		data.getCurrentCreditsProperty()
				.addListener((observable, oldVal, newVal) -> lblCurrentCredits.setText(newVal.toString()));
//		lblMaxCredits.textProperty().bind(null);
		data.getCurrentGradeProperty()
				.addListener((observable, oldVal, newVal) -> lblCurrentGrade.setText(newVal.toString()));
//		lblBestGrade.textProperty().bind(null);
//		lblWorstGrade.textProperty().bind(null);
//		pgiCredits.progressProperty();

	}

}
