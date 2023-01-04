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

public class AnalyseView {
	@FXML
	private GridPane gridProgress;
	@FXML
	private LineChart<Number, Number> lineChartGrades;
	@FXML
	private BarChart<String, Number> barChartGrades;
	@FXML
	private HBox hBoxFocus;
	@FXML
	private PieChart pieChartFocus;
	@FXML
	private Label lbSelectedFocus;

	private AnalyseViewModel analyseViewModel = new AnalyseViewModel();

	@FXML
	public void initialize() {
		hBoxFocus.visibleProperty().bind(analyseViewModel.getPieChartVisibleProperty());
		((NumberAxis) lineChartGrades.getXAxis()).upperBoundProperty()
				.bind(analyseViewModel.getNumberOfSemestersProperty());
		barChartGrades.setData(analyseViewModel.getBarChartdataProperty());
		lineChartGrades.setData(analyseViewModel.getLineChartdataProperty());
		pieChartFocus.setData(analyseViewModel.getPieChartDataProperty());
		lbSelectedFocus.textProperty().bind(analyseViewModel.getSelectedFocusDataProperty());
		analyseViewModel.getNumberOfSemestersProperty().addListener((observable, oldValue, newValue) -> {
			updateGrid();
		});
	}

	private void updateGrid() {
		System.out.println("full Grid update");
		gridProgress.getChildren().clear();

		var progressPropertyMap = analyseViewModel.getProgressPropertyMap();
		var avgPropertyMap = analyseViewModel.getAvgPropertyMap();

		double percentWidth = 0.85 / progressPropertyMap.size() * 100;
		var firstColumnConstraint = new ColumnConstraints();
		firstColumnConstraint.setPercentWidth(15);
		gridProgress.getColumnConstraints().clear();
		gridProgress.getColumnConstraints().add(firstColumnConstraint);

		gridProgress.add(new Label("Semester"), 0, 0);
		gridProgress.add(new Label("Fortschritt"), 0, 1);
		gridProgress.add(new Label("Schnitt"), 0, 2);

		for (var propertyEntry : progressPropertyMap.entrySet()) {
			int sem = propertyEntry.getKey();
			var progressProperty = propertyEntry.getValue();
			var pi = new ProgressIndicator();
			pi.progressProperty().bind(progressProperty);
//			// Rote Farbe
////			var adjust = new ColorAdjust();
////			adjust.setHue(16);
////			pi.setEffect(adjust);
			pi.setMinHeight(45); // otherwise not visible
			GridPane.setHalignment(pi, HPos.CENTER);
			gridProgress.add(pi, sem, 1);

			var title = new Label(String.valueOf(sem));
			title.setUnderline(true);
			GridPane.setHalignment(title, HPos.CENTER);
			gridProgress.add(title, sem, 0);

			var avgLabel = new Label();
			avgLabel.textProperty().bind(avgPropertyMap.getOrDefault(sem, null));
			GridPane.setHalignment(avgLabel, HPos.CENTER);
			gridProgress.add(avgLabel, sem, 2);

			var columnConstraint = new ColumnConstraints();
			columnConstraint.setPercentWidth(percentWidth);
			gridProgress.getColumnConstraints().add(columnConstraint);
		}
	}
}
