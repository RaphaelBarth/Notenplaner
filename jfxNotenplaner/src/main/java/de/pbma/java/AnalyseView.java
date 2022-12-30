package de.pbma.java;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class AnalyseView {
	@FXML
	private VBox vBoxAnalytics;

	@FXML
	public void initialize() {
		final CategoryAxis grades = new CategoryAxis();
		final NumberAxis number = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<>(grades, number);
		bc.setTitle("Notenverteilung");
		// grades.setLabel("Note");
		number.setLabel("Anzahl");

		XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
		Random rand = new Random(); // instance of random class
		int upperbound = 10;
		for (var g : Grades.values()) {
			int int_random = rand.nextInt(upperbound);
			serie.getData().add(new XYChart.Data<String, Number>(g.toString(), int_random));
		}

		bc.getData().add(serie);
		bc.setLegendVisible(false);

		// Defining the x axis
		NumberAxis xAxis = new NumberAxis(1, 7, 1);
		xAxis.setLabel("Semester");

		// Defining the y axis
		NumberAxis yAxis = new NumberAxis(4, 1, 0.3);
		yAxis.setLabel("Schnitt");

		// Creating the line chart
		LineChart linechart = new LineChart(xAxis, yAxis);

		// Prepare XYChart.Series objects by setting data
		XYChart.Series<Integer, Number> series = new XYChart.Series<Integer, Number>();
		series.setName("No of schools in an year");
		series.getData().add(new XYChart.Data<Integer, Number>(1, 1.0));
		series.getData().add(new XYChart.Data<Integer, Number>(2, 1.5));
		series.getData().add(new XYChart.Data<Integer, Number>(3, 2.0));
		series.getData().add(new XYChart.Data<Integer, Number>(4, 1.0));
		series.getData().add(new XYChart.Data<Integer, Number>(5, 1.0));
		// series.getData().add(new XYChart.Data<Integer, Number>(6, 2.0));
		series.getData().add(new XYChart.Data<Integer, Number>(7, 1.3));

		// Setting the data to Line chart
		linechart.setPadding(new Insets(5));
		linechart.getData().add(series);
		vBoxAnalytics.getChildren().addAll(bc, linechart);
	}
}
