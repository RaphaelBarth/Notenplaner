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
	private Student student;

	private StudentData() {
	}

	public static StudentData getStudentData() {
		if (studentData == null) {
			studentData = new StudentData();
		}
		return studentData;
	}

	public void updateStudentData(Student student) {
		this.student = student;

	}

	public Student getStudent() {
		return student;
	}
}
