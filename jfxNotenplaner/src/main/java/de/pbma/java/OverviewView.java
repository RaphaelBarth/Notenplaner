package de.pbma.java;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class OverviewView implements Initializable {

	@FXML
	private Label lblStudentName;
	@FXML
	private Label lblMatriculationNumber;
	@FXML
	private Label lblCourseOfStudies;
	@FXML
	private Label lblAbschluss;
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

	OverviewViewModel overviewViewModel = new OverviewViewModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblStudentName.textProperty().bind(overviewViewModel.getNameProperty());
		lblMatriculationNumber.textProperty().bind(overviewViewModel.getMatNrProperty().asString());
		lblCourseOfStudies.textProperty().bind(overviewViewModel.getCourseOfStudiesProperty());
		lblAbschluss.textProperty().bind(overviewViewModel.getAbschlussProperty());
		lblCurrentGrade.textProperty().bind(overviewViewModel.getCurrentGradeProperty().asString("%.1f"));
		lblCurrentCredits.textProperty().bind(overviewViewModel.getCurrentCredidtsProperty().asString());
		lblMaxCredits.textProperty().bind(overviewViewModel.getMaxCreditsProperty().asString());
		lblBestGrade.textProperty().bind(overviewViewModel.getBestGradeProperty().asString("%.1f"));
		lblWorstGrade.textProperty().bind(overviewViewModel.getWorstGradeProperty().asString("%.1f"));
		pgiCredits.progressProperty().bind(overviewViewModel.getProgressProperty());
		lblMatriculationNumber.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		lblCurrentGrade.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		lblMaxCredits.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		lblBestGrade.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		lblWorstGrade.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		lblCurrentCredits.visibleProperty().bind(overviewViewModel.getVisibilityProperty());
		pgiCredits.visibleProperty().bind(overviewViewModel.getVisibilityProperty());

	}

}
