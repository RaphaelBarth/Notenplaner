package de.pbma.java;

import java.util.OptionalDouble;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

public class AnalyseViewModel {

	public AnalyseViewModel() {
		// TODO Auto-generated constructor stub
	}

	public int getMaxSemester() {
		return 7;
	}

	public double getProgressOfSemester(int sem) {
		return 0.5;
	}

	public OptionalDouble getAvgOfSemester(int sem) {
		return OptionalDouble.of(1.3);
	}

	public XYChart.Series<String, Number> getNotenverteilung() {
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
		return serie;
	}

	public XYChart.Series<Number, Number> getAvgOfSemesters() {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		for (int sem = 1; sem <= getMaxSemester(); sem += 1) {
			var avg = getAvgOfSemester(sem);
			if (avg.isPresent()) {
				series.getData().add(new XYChart.Data<Number, Number>(sem, avg.getAsDouble()));
			}
		}

		return series;
	}

	public ObservableList<PieChart.Data> getFocusPieData() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		return pieChartData;
	}

}
