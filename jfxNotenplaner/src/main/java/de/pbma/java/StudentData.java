package de.pbma.java;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class StudentData {
	private static StudentData studentData;
	private ObjectProperty<Student> objectProperty;
	private Object lock;

	private StudentData() {
		this.lock = new Object();
		this.objectProperty = new SimpleObjectProperty<>(this, "student");
	}

	public static synchronized StudentData getData() {
		if (studentData == null) {
			studentData = new StudentData();
		}
		return studentData;
	}

	public void setStudentData(Student student) {
		synchronized (lock) {
			this.objectProperty.setValue(student);
		}
	}
	
	public void updateStudentData() {
		synchronized (lock) {
			return;
		}
	}

	public Student getStudent() {
		synchronized (lock) {
			var student =this.objectProperty.getValue(); 
			//return (student == null) ? null : (Student) student.clone();
			return student;
		}
	}

	public ObjectProperty<Student> getObjectProperty() {
		return objectProperty;
	}


}
