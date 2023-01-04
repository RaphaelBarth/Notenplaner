package de.pbma.java;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class AnalyseViewModel {

	private IntegerProperty numberOfSemesters = new SimpleIntegerProperty();
	private final ObservableList<Series<Number, Number>> lineChartdata = FXCollections.observableArrayList();
	private final ObservableList<Series<String, Number>> barChartdata = FXCollections.observableArrayList();
	private Map<Integer, DoubleProperty> progressProperties = new HashMap<>();
	private Map<Integer, StringProperty> avgProperties = new HashMap<>();

//	private BooleanProperty pieChartVisible = new SimpleBooleanProperty(false);
//	private final ObservableList<PieChart.Data> focusPieChartData = FXCollections.observableArrayList();
//	private StringProperty selectedSubject = new SimpleStringProperty();
//	private IntegerProperty subjectsPerSelectedFocus = new SimpleIntegerProperty(0);
//	private DoubleProperty avgForSelectedSubject = new SimpleDoubleProperty();

	public AnalyseViewModel() {
		updateValuesForStudent();

		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			progressProperties.clear();
			avgProperties.clear();
			updateValuesForStudent();
		});
		StudentData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			updateValuesForStudent();
		});
	}

	private void updateValuesForStudent() {
		new Thread(() -> {
			var student = StudentData.getData().getStudent();
			var curriculum = CurriculumData.getData().getCurriculum();
			if (curriculum == null || student == null) {
				return;
			}
			final var numberOfSemester = curriculum.getNumberOfSemesters();

			var semesterAvgMap = getAvgOfAllSemesters(curriculum, student.getSubjectGradeMap());
			final XYChart.Series<Number, Number> lineData = new XYChart.Series<Number, Number>();
			for (var semAvgSet : semesterAvgMap.entrySet()) {
				lineData.getData().add(new XYChart.Data<Number, Number>(semAvgSet.getKey(), semAvgSet.getValue()));
			}

			final var barData = getNotenverteilung(student.getSubjectGradeMap().values());

			final Map<Integer, String> avgList = new HashMap<>();
			final Map<Integer, Double> progressList = new HashMap<>();
			for (int sem = 1; sem <= numberOfSemester; sem += 1) {
				var progress = getProgressOfSemester(curriculum, student.getSubjectGradeMap(), sem);
				System.out.format("Sem %d, Progress %.2f\n", sem, progress);
				progressList.put(sem, progress);
				var text = semesterAvgMap.containsKey(sem) ? String.format("%.2f", semesterAvgMap.get(sem)) : "-";
				avgList.put(sem, text);

				progressProperties.putIfAbsent(sem, new SimpleDoubleProperty());
				avgProperties.putIfAbsent(sem, new SimpleStringProperty());

			}
			Platform.runLater(() -> {
				numberOfSemesters.set(numberOfSemester);
				barChartdata.clear();
				barChartdata.add(barData);
				lineChartdata.clear();
				lineChartdata.add(lineData);
				for (int i = 1; i <= progressList.size(); i += 1) {
					System.out.println("setze Progress Werte");
					progressProperties.get(i).set(progressList.get(i));
					avgProperties.get(i).set(avgList.get(i));
				}
			});
		}).start();
	}

	public IntegerProperty getNumberOfSemestersProperty() {
		return numberOfSemesters;
	}

	public int getNumberOfSemesters() {
		return numberOfSemesters.getValue();
	}

	public ObservableList<Series<Number, Number>> getLineChartdataProperty() {
		return lineChartdata;
	}

	public ObservableList<Series<String, Number>> getBarChartdataProperty() {
		return barChartdata;
	}

//
//	public IntegerProperty getSubjectsPerSelectedFocusProperty() {
//		return subjectsPerSelectedFocus;
//	}
//
	public Map<Integer, DoubleProperty> getProgressPropertyMap() {
		return progressProperties;
	}

	public Map<Integer, StringProperty> getAvgPropertyMap() {
		return avgProperties;
	}

	public double getProgressOfSemester(Curriculum curriculum, Map<String, Grades> subjectGradeMap, int sem) {
		double collectedCredits = 0.0;
		for (var entrySet : subjectGradeMap.entrySet()) {
			var subject = curriculum.getSubject(entrySet.getKey());
			if (subject.getSemester() != sem) {
				continue;
			}
			var grade = entrySet.getValue();
			if (grade == Grades.NOTPASSED) {
				continue;
			}

			collectedCredits += subject.getCreditPoints();
		}
		return collectedCredits / curriculum.getCreditsForSemester(sem);
	}

	public OptionalDouble getAvgOfSemester(Curriculum curriculum, Map<String, Grades> subjectGradeMap, int sem) {
		var currentGradeTmp = 0.0;
		var currentGradeCreditsTmp = 0.0;
		for (var entrySet : subjectGradeMap.entrySet()) {
			var subject = curriculum.getSubject(entrySet.getKey());
			if (subject.getSemester() != sem) {
				continue;
			}
			var grade = entrySet.getValue();
			if (grade == Grades.PASSED || grade == Grades.NOTPASSED) {
				continue;
			}
			var credits = subject.getCreditPoints();
			currentGradeCreditsTmp += credits;
			currentGradeTmp += (credits * grade.value);
		}
		var currentGrade = currentGradeTmp / currentGradeCreditsTmp;
		if (Double.isNaN(currentGrade)) {
			return OptionalDouble.empty();
		}
		return OptionalDouble.of(currentGrade);
	}

	public XYChart.Series<String, Number> getNotenverteilung(Collection<Grades> collection) {
		XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
		for (var g : Grades.values()) {
			var data = new XYChart.Data<String, Number>(g.toString(), Collections.frequency(collection, g));
			serie.getData().add(data);
		}
		return serie;
	}

	public Map<Integer, Double> getAvgOfAllSemesters(Curriculum curriculum, Map<String, Grades> subjectGradeMap) {
		Map<Integer, Double> semesterAvgMap = new HashMap<>();
		for (int sem = 1; sem <= curriculum.getNumberOfSemesters(); sem += 1) {
			var avg = getAvgOfSemester(curriculum, subjectGradeMap, sem);
			if (avg.isPresent()) {
				semesterAvgMap.put(sem, avg.getAsDouble());
			}
		}
		return semesterAvgMap;
	}

//	public BooleanProperty getPieChartVisibleProperty() {
//		return pieChartVisible;
//	}

//	public ObservableList<PieChart.Data> getFocusPieData() {
//		if (currentStudent == null) {
//			return null;
//		}
//		Map<String, Double> collectedFocusCreditsMap = new HashMap<>();
//
//		for (var subject : currentCurriculum.getAllSubjects()) {
//			var grade = currentStudent.getGradeForSubject(subject.getShort());
//			if (grade == Grades.NOTPASSED) {
//				continue;
//			}
//			collectedFocusCreditsMap.put(subject.getFocus(),
//					collectedFocusCreditsMap.getOrDefault(subject.getFocus(), 0.0) + subject.getCreditPoints());
//		}
//		if (collectedFocusCreditsMap.keySet().size() < 2) {
//			return null;
//		}
//		focusPieChartData = FXCollections.observableArrayList();
//		for (var entrySet : collectedFocusCreditsMap.entrySet()) {
//			var data = new PieChart.Data(entrySet.getKey(), entrySet.getValue());
//			focusPieChartData.add(data);
//		}
//		return focusPieChartData;
//	}
//
//	public int getNumberOfSubjectsPerFocus(String focus) {
//		int number = 0;
//		for (var entrySet : currentStudent.getSubjectGradeMap().entrySet()) {
//			var subjectFocus = currentCurriculum.getSubject(entrySet.getKey()).getFocus();
//			if (!focus.equals(subjectFocus) || entrySet.getValue() == Grades.NOTPASSED) {
//				continue;
//			}
//			number++;
//		}
//		subjectsPerSelectedFocus.set(number);
//		return number;
//	}

}
