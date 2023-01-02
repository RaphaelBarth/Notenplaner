package de.pbma.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class AnalyseViewModel {

	private Student currentStudent;
	private Curriculum currentCurriculum;

	public AnalyseViewModel() {
		currentStudent = StudentData.getData().getStudent();
		currentCurriculum = CurriculumData.getData().getCurriculum();
	}

	public int getMaxSemester() {
		if (currentStudent == null) {
			return 0;
		}
		return currentCurriculum.getNumberOfSemesters();
	}

	public double getProgressOfSemester(int sem) {
		double collectedCredits = 0.0;
		for (var entrySet : currentStudent.getSubjectGradeMap().entrySet()) {
			var subject = currentCurriculum.getSubject(entrySet.getKey());
			if (subject.getSemester() != sem) {
				continue;
			}
			var grade = entrySet.getValue();
			if (grade == Grades.NOTPASSED) {
				continue;
			}

			collectedCredits += subject.getCreditPoints();
		}
		return collectedCredits / currentCurriculum.getCreditsForSemester(sem);
	}

	public OptionalDouble getAvgOfSemester(int sem) {
		if (getProgressOfSemester(sem) == 0.0) {
			return OptionalDouble.empty();
		}
		var currentGradeTmp = 0.0;
		var currentGradeCreditsTmp = 0.0;
		for (var entrySet : currentStudent.getSubjectGradeMap().entrySet()) {
			var subject = currentCurriculum.getSubject(entrySet.getKey());
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
		return OptionalDouble.of(currentGrade);
	}

	public XYChart.Series<String, Number> getNotenverteilung() {
		XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
		if (currentStudent == null) {
			return serie;
		}
		var grades = currentStudent.getSubjectGradeMap().values();
		for (var g : Grades.values()) {
			var data = new XYChart.Data<String, Number>(g.toString(), Collections.frequency(grades, g));
			serie.getData().add(data);
		}
		return serie;
	}

	public XYChart.Series<Number, Number> getAvgOfSemesters() {
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		if (currentStudent == null) {
			return series;
		}
		for (int sem = 1; sem <= getMaxSemester(); sem += 1) {
			var avg = getAvgOfSemester(sem);
			if (avg.isPresent()) {
				series.getData().add(new XYChart.Data<Number, Number>(sem, avg.getAsDouble()));
			}
		}

		return series;
	}

	public ObservableList<PieChart.Data> getFocusPieData() {
		if (currentStudent == null) {
			return null;
		}
		Map<String, Double> collectedFocusCreditsMap = new HashMap<>();

		for (var subject : currentCurriculum.getAllSubjects()) {
			var grade = currentStudent.getGradeForSubject(subject.getShort());
			if (grade == Grades.NOTPASSED) {
				continue;
			}
			collectedFocusCreditsMap.put(subject.getFocus(),
					collectedFocusCreditsMap.getOrDefault(subject.getFocus(), 0.0) + subject.getCreditPoints());
		}
		if (collectedFocusCreditsMap.keySet().size() < 2) {
			return null;
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (var entrySet : collectedFocusCreditsMap.entrySet()) {
			pieChartData.add(new PieChart.Data(entrySet.getKey(), entrySet.getValue()));
		}
		return pieChartData;
	}

	public int getNumberOfSubjectsPerFocus(String focus) {
		int number = 0;
		for (var entrySet : currentStudent.getSubjectGradeMap().entrySet()) {
			var subjectFocus = currentCurriculum.getSubject(entrySet.getKey()).getFocus();
			if (!focus.equals(subjectFocus) || entrySet.getValue() == Grades.NOTPASSED) {
				continue;
			}
			number++;
		}
		return number;
	}

}
