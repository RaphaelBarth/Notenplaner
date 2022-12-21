package de.pbma.java;

import java.io.Serializable;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData implements Serializable {
	private static final long serialVersionUID = -1395111219643358238L;
	private static StudentData studentData;

	private final StringProperty name = new SimpleStringProperty(this, "name");
	private final StringProperty courseOfStudies = new SimpleStringProperty(this, "courseOfStudies");
	private final IntegerProperty matriculationNumber = new SimpleIntegerProperty(this, "matriculationNumber");
	private final IntegerProperty currentCredits = new SimpleIntegerProperty(this, "currentCredits");
	private final DoubleProperty currentGrade = new SimpleDoubleProperty(this, "currentGrade");

	private StudentData() {
	}

	public static StudentData getStudentData() {
		if (studentData == null) {
			studentData = new StudentData();
		}
		return studentData;
	}

	public void updateStudentData(Student student) {
		final String name = student.getName();
		final String courseOfStudies = student.getCourseOfStudies();
		final int matriculationNumber = student.getMatriculationNumber();
		//TODO  calculate StudentData
		final int currentGrade = 0;
		final int currentCredits = 0;

		Platform.runLater(() -> {
			this.name.set(name);
			this.courseOfStudies.setValue(courseOfStudies);
			this.matriculationNumber.setValue(matriculationNumber);
			this.currentCredits.setValue(currentCredits);
			this.currentGrade.setValue(currentGrade);
		});

	}

	public StringProperty getNameProperty() {
		return name;
	}

	public String getName() {
		return name.getValue();
	}

	public StringProperty getCourseOfStudiesProperty() {
		return courseOfStudies;
	}

	public String getCourseOfStudies() {
		return courseOfStudies.getValue();
	}

	public IntegerProperty getMatriculationNumberProperty() {
		return matriculationNumber;
	}

	public int getMatriculationNumber() {
		return matriculationNumber.getValue();
	}

	public IntegerProperty getCurrentCreditsProperty() {
		return currentCredits;
	}

	public int getCurrentCredits() {
		return currentCredits.getValue();
	}

	public DoubleProperty getCurrentGradeProperty() {
		return currentGrade;
	}

	public double getCurrentGrade() {
		return currentGrade.getValue();
	}

}
