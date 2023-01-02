package de.pbma.java;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnalyseView {
	@FXML
	private VBox vBoxAnalytics;
	@FXML
	private GridPane progressBarBox;
	@FXML
	private BarChart<String, Number> barChartGrades;
	@FXML
	private LineChart<Number, Number> lineChartGrades;
	@FXML
	private PieChart pieChartFocus;
	@FXML
	private Label lbFocus;
	@FXML
	private Label lbFocusAvg;
	@FXML
	private HBox hBoxFocus;

	private AnalyseViewModel analyseViewModel = new AnalyseViewModel();

	@FXML
	public void initialize() {
//		StudentData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {	
//			updateView();
//		});
		updateView();
	}

	public void updateView() {
		int sem_max = analyseViewModel.getMaxSemester();
		double percentWidth = 0.85 / sem_max * 100;
		var firstColumnConstraint = new ColumnConstraints();
		firstColumnConstraint.setPercentWidth(15);
		progressBarBox.getColumnConstraints().add(firstColumnConstraint);
		for (int sem = 1; sem <= sem_max; sem += 1) {
			var pi = new ProgressIndicator();
			// Rote Farbe
//			var adjust = new ColorAdjust();
//			adjust.setHue(16);
//			pi.setEffect(adjust);
			var columnConstraint = new ColumnConstraints();
			columnConstraint.setPercentWidth(percentWidth);
			pi.setProgress(analyseViewModel.getProgressOfSemester(sem));
			pi.setMinHeight(45); // otherwise not visible
			GridPane.setHalignment(pi, HPos.CENTER);
			var title = new Label(String.valueOf(sem));
			title.setUnderline(true);
			GridPane.setHalignment(title, HPos.CENTER);
			var avg = analyseViewModel.getAvgOfSemester(sem);
			var text = avg.isPresent() ? String.format("%.2f", avg.getAsDouble()) : "-";
			var avgLabel = new Label(text);
			GridPane.setHalignment(avgLabel, HPos.CENTER);
			progressBarBox.add(title, sem, 0);
			progressBarBox.add(pi, sem, 1);
			progressBarBox.add(avgLabel, sem, 2);
			progressBarBox.getColumnConstraints().add(columnConstraint);
		}

		barChartGrades.getData().add(analyseViewModel.getNotenverteilung());

		// Setting the data to Line chart
		lineChartGrades.getData().add(analyseViewModel.getAvgOfSemesters());

		((NumberAxis) lineChartGrades.getXAxis()).setUpperBound(sem_max);

		var data = analyseViewModel.getFocusPieData();
		if (data == null) {
			hBoxFocus.setVisible(false);
		} else {
			pieChartFocus.setData(data);
			lbFocus.setText(
					String.format("Belegte Fächer: %d", analyseViewModel.getNumberOfSubjectsPerFocus("Grundlagen")));
		}
	}
}
