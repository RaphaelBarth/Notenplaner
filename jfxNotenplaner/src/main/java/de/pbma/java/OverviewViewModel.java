package de.pbma.java;

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
		if (StudentData.getStudentData().getStudent() != null) {
			showStudent(StudentData.getStudentData().getStudent());
		}
		if (CurriculumData.getData().getCurriculum() != null) {
			showCurriculumData(CurriculumData.getData().getCurriculum());
		}
	}

	public void showStudent(Student student) {
		setName(student.getName());
		setMatNr(student.getMatriculationNumber());
		setCourseOfStudies(student.getCourseOfStudies());
	}

	public void showCurriculumData(Curriculum curriculum) {
		setAbschluss(curriculum.getAbschluss());
		setMaxCredidts(curriculum.getCredits());
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
		maxCreditsProperty.set(credits);
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