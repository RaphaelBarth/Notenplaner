package de.pbma.java;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;

public class AnalyseViewModel {

	private IntegerProperty numberOfSemesters = new SimpleIntegerProperty();
	private final ObservableList<Series<Number, Number>> lineChartdata = FXCollections.observableArrayList();
	private final ObservableList<Series<String, Number>> barChartdata = FXCollections.observableArrayList();
	private Map<Integer, DoubleProperty> progressProperties = new HashMap<>();
	private Map<Integer, StringProperty> avgProperties = new HashMap<>();

	private BooleanProperty pieChartVisible = new SimpleBooleanProperty(false);
	private final ObservableList<PieChart.Data> focusPieChartData = FXCollections.observableArrayList();
	private StringProperty selectedFocus = new SimpleStringProperty();
	private IntegerProperty subjectsPerSelectedFocus = new SimpleIntegerProperty(0);
	private StringProperty selectedFocusData = new SimpleStringProperty("Klicken Sie auf einen Bereich.");

	public AnalyseViewModel() {
		updateValuesForStudent();
		pieChartVisible.bind(Bindings.size(focusPieChartData).greaterThan(1));

		selectedFocus.addListener((observable, oldValue, newValue) -> {
			new Thread(() -> {
				var student = StudentData.getData().getStudent();
				final String text;
				var curriculum = CurriculumData.getData().getCurriculum();
				if (curriculum == null || student == null) {
					return;
				}
				if (newValue.isEmpty()) {
					text = "Klicken Sie auf einen Bereich.";
				} else {
					var number = getNumberOfSubjectsPerFocus(curriculum, student.getSubjectGradeMap(), newValue);
					var avg = getAvgOfFocus(curriculum, student.getSubjectGradeMap(), newValue);
					var avgString = (avg.isPresent()) ? String.format("Schnitt:%.1f", avg.getAsDouble()) : "";
					text = String.format("%s\nbelegte Fächer: %d\n%s", newValue, number, avgString);
				}

				Platform.runLater(() -> {
					selectedFocusData.set(text);
				});

			}).start();
		});

		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			progressProperties.clear();
			avgProperties.clear();
			Platform.runLater(() -> {
				selectedFocus.set("");
			});
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

			var pieData = getFocusPieData(curriculum, student.getSubjectGradeMap());
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
				focusPieChartData.clear();
				if (pieData == null) {
					// Pie Chart Daten gibt es erst ab mind. 2 Fächern in mind. 2 verschiedenen
					// Bereichen
					return;
				}
				for (var entrySet : pieData.entrySet()) {
					var data = new PieChart.Data(entrySet.getKey(), entrySet.getValue());
					focusPieChartData.add(data);
					data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							setSelectedFocus(data.getName());
						}
					});
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

	public Map<Integer, DoubleProperty> getProgressPropertyMap() {
		return progressProperties;
	}

	public Map<Integer, StringProperty> getAvgPropertyMap() {
		return avgProperties;
	}

	public double getProgressOfSemester(Curriculum curriculum, Map<String, Grades> subjectGradeMap, int sem) {
		if (1 > sem || sem > curriculum.getNumberOfSemesters()) {
			return 0.0;
		}
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

	public XYChart.Series<String, Number> getNotenverteilung(Collection<Grades> collection) {
		XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
		for (var grade : Grades.values()) {
			if (grade == Grades.NOTPASSED) {
				continue;
			}
			var data = new XYChart.Data<String, Number>(grade.toString(), Collections.frequency(collection, grade));
			serie.getData().add(data);
		}
		return serie;
	}

	public IntegerProperty getSubjectsPerSelectedFocusProperty() {
		return subjectsPerSelectedFocus;
	}

	public BooleanProperty getPieChartVisibleProperty() {
		return pieChartVisible;
	}

	public ObservableList<PieChart.Data> getPieChartDataProperty() {
		return focusPieChartData;
	}

	public StringProperty getSelectedFocusDataProperty() {
		return selectedFocusData;
	}

	public StringProperty getSelectedFocusProperty() {
		return selectedFocus;
	}

	public String getSelectedFocus() {
		return selectedFocus.getValue();
	}

	public void setSelectedFocus(String focus) {
		selectedFocus.setValue(focus);
	}

	public Map<String, Double> getFocusPieData(Curriculum curriculum, Map<String, Grades> subjectGradeMap) {
		Map<String, Double> collectedFocusCreditsMap = new HashMap<>();
		for (var entrySet : subjectGradeMap.entrySet()) {
			if (entrySet.getValue() == Grades.NOTPASSED) {
				continue;
			}
			var subject = curriculum.getSubject(entrySet.getKey());
			collectedFocusCreditsMap.put(subject.getFocus(),
					collectedFocusCreditsMap.getOrDefault(subject.getFocus(), 0.0) + subject.getCreditPoints());
		}
		if (collectedFocusCreditsMap.keySet().size() < 2) {
			return null;
		}

		return collectedFocusCreditsMap;
	}

	public int getNumberOfSubjectsPerFocus(Curriculum curriculum, Map<String, Grades> subjectGradeMap, String focus) {
		int number = 0;
		for (var entrySet : subjectGradeMap.entrySet()) {
			var subjectFocus = curriculum.getSubject(entrySet.getKey()).getFocus();
			if (!focus.equals(subjectFocus) || entrySet.getValue() == Grades.NOTPASSED) {
				continue;
			}
			number++;
		}
		return number;
	}

	public OptionalDouble getAvgOfFocus(Curriculum curriculum, Map<String, Grades> subjectGradeMap, String focus) {
		var currentGradeTmp = 0.0;
		var currentGradeCreditsTmp = 0.0;
		for (var entrySet : subjectGradeMap.entrySet()) {
			var grade = entrySet.getValue();
			if (grade == Grades.PASSED || grade == Grades.NOTPASSED) {
				continue;
			}
			var subject = curriculum.getSubject(entrySet.getKey());
			if (!subject.getFocus().equals(focus)) {
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
}
