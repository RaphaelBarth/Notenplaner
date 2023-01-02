package de.pbma.java;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
	public void initialize() {

		int sem_max = 7;
		double percentWidth = 1.0 / sem_max * 100;
		System.out.println(percentWidth);
		for (int sem = 1; sem <= sem_max; sem += 1) {
			var pi = new ProgressIndicator();
			var columnConstraint = new ColumnConstraints();
			columnConstraint.setPercentWidth(percentWidth);
			pi.setProgress(0.5);
			pi.setMinHeight(45); // otherwise not visible
			var title = new Label(String.format("Sem %d", sem));
			progressBarBox.add(pi, sem - 1, 1);
			progressBarBox.add(title, sem - 1, 0);
			progressBarBox.getColumnConstraints().add(columnConstraint);
		}

		XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
		Random rand = new Random(); // instance of random class
		int upperbound = 10;
		for (var g : Grades.values()) {
			int int_random = rand.nextInt(upperbound);
			final var data = new XYChart.Data<String, Number>(g.toString(), int_random);
			var tooltip = new Tooltip(String.valueOf(data.getYValue()));
			Tooltip.install(data.getNode(), tooltip);
			serie.getData().add(data);
		}

		barChartGrades.getData().add(serie);

		// Prepare XYChart.Series objects by setting data
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<Number, Number>(1, 1.0));
		series.getData().add(new XYChart.Data<Number, Number>(2, 1.5));
		series.getData().add(new XYChart.Data<Number, Number>(3, 2.0));
		series.getData().add(new XYChart.Data<Number, Number>(4, 1.0));
		series.getData().add(new XYChart.Data<Number, Number>(5, 1.0));
		// series.getData().add(new XYChart.Data<Number, Number>(6, 2.0));
		series.getData().add(new XYChart.Data<Number, Number>(7, 1.3));

		// Setting the data to Line chart
		lineChartGrades.getData().add(series);

		((NumberAxis) lineChartGrades.getXAxis()).setUpperBound(sem_max);
	}
}
