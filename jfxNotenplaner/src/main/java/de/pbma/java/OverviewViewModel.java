package de.pbma.java;

import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OverviewViewModel {
	private StringProperty nameProperty = new SimpleStringProperty();
	private IntegerProperty matNrProperty = new SimpleIntegerProperty();
	private StringProperty courseOfStudiesProperty = new SimpleStringProperty();
	private StringProperty abschlussProperty = new SimpleStringProperty();
	private DoubleProperty currentCredidtsProperty = new SimpleDoubleProperty();
	private DoubleProperty currentGradeProperty = new SimpleDoubleProperty();
	private DoubleProperty maxCreditsProperty = new SimpleDoubleProperty();
	private DoubleProperty bestGradeProperty = new SimpleDoubleProperty();
	private DoubleProperty worstGradeProperty = new SimpleDoubleProperty();
	private DoubleProperty progressProperty = new SimpleDoubleProperty();
	private BooleanProperty visibilityProperty = new SimpleBooleanProperty();

	public OverviewViewModel() {
		NumberBinding progress = currentCredidtsProperty.divide(maxCreditsProperty);
		progressProperty.bind(progress);
		visibilityProperty.bind(nameProperty.isNotEmpty());
		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			updateView();
		});
		StudentData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			updateView();
		});
		updateView();
	}

	public void updateView() {
		new Thread(() -> {
			final var student = StudentData.getData().getStudent();
			final var curriculum = CurriculumData.getData().getCurriculum();
			if (student == null || curriculum == null) {
				return;
			}
			
			var currentGradeTmp = 0.0;
			var currentGradeCreditsTmp = 0.0;
			var currentCreditsTmp = 0.0;
			for (var subject : student.getSubjectGradeMap().entrySet()) {
				var credits = curriculum.getSubject(subject.getKey()).getCreditPoints();
				currentCreditsTmp += credits;
				var grade = subject.getValue();
				if (grade == Grades.PASSED || grade == Grades.NOTPASSED) {
					continue;
				}
				currentGradeCreditsTmp += credits;
				currentGradeTmp += (credits * grade.value);
			}
			final var currentGrade = currentGradeTmp / currentGradeCreditsTmp;
			final var currentCredits = currentCreditsTmp;

			var bestGradeTmp = currentGradeTmp;
			var wortsGradeTmp = currentGradeTmp;
			for (var subject : curriculum.getAllSubjects()) {
				if (!subject.hasGradeAsEvaluation()) {
					continue;
				}
				if (student.hasGradeForSubject(subject.getShort())) {
					continue;
				}
				currentGradeCreditsTmp += subject.getCreditPoints();
				bestGradeTmp += subject.getCreditPoints() * Grades.ONE.value;
				wortsGradeTmp += subject.getCreditPoints() * Grades.FOUR.value;
			}
			final var bestGrade = bestGradeTmp / currentGradeCreditsTmp;
			final var wortsGrade = wortsGradeTmp / currentGradeCreditsTmp;
			Platform.runLater(() -> {
				setName(student.getName());
				setMatNr(student.getMatriculationNumber());
				setCourseOfStudies(student.getCourseOfStudies());
				setAbschluss(curriculum.getAbschluss());
				setCurrentCredidts(currentCredits);
				setMaxCredidts(curriculum.getCredits());
				setCurrentGrade(currentGrade);
				setBestGrade(bestGrade);
				setWorstGrade(wortsGrade);
			});
		}).start();
		;
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public String getName() {
		return nameProperty.getValue();
	}

	public void setName(String name) {
		nameProperty.setValue(name);
	}

	public IntegerProperty getMatNrProperty() {
		return matNrProperty;
	}

	public int getMatNr() {
		return matNrProperty.getValue();
	}

	public void setMatNr(int matNr) {
		matNrProperty.setValue(matNr);
	}

	public StringProperty getCourseOfStudiesProperty() {
		return courseOfStudiesProperty;
	}

	public String getCourseOfStudies() {
		return courseOfStudiesProperty.getValue();
	}

	public void setCourseOfStudies(String courseOfStudies) {
		courseOfStudiesProperty.setValue(courseOfStudies);
	}

	public StringProperty getAbschlussProperty() {
		return abschlussProperty;
	}

	public String getAbschluss() {
		return abschlussProperty.getValue();
	}

	public void setAbschluss(String abschluss) {
		abschlussProperty.setValue(abschluss);
	}

	public DoubleProperty getCurrentCredidtsProperty() {
		return currentCredidtsProperty;
	}

	public double getCurrentCredidts() {
		return currentCredidtsProperty.getValue();
	}

	public void setCurrentCredidts(double credits) {
		currentCredidtsProperty.set(credits);
	}

	public DoubleProperty getCurrentGradeProperty() {
		return currentGradeProperty;
	}

	public double getCurrentGrade() {
		return currentGradeProperty.getValue();
	}

	public void setCurrentGrade(double grade) {
		currentGradeProperty.set(grade);
	}

	public DoubleProperty getMaxCreditsProperty() {
		return maxCreditsProperty;
	}

	public double getMaxCredidts() {
		return maxCreditsProperty.getValue();
	}

	public void setMaxCredidts(double credits) {
		maxCreditsProperty.setValue(credits);
	}

	public DoubleProperty getBestGradeProperty() {
		return bestGradeProperty;
	}

	public double getBestGrade() {
		return bestGradeProperty.getValue();
	}

	public void setBestGrade(double grade) {
		bestGradeProperty.set(grade);
	}

	public DoubleProperty getWorstGradeProperty() {
		return worstGradeProperty;
	}

	public double getWorstGrade() {
		return worstGradeProperty.getValue();
	}

	public void setWorstGrade(double grade) {
		worstGradeProperty.set(grade);
	}

	public DoubleProperty getProgressProperty() {
		return progressProperty;
	}

	public BooleanProperty getVisibilityProperty() {
		return visibilityProperty;
	}
}