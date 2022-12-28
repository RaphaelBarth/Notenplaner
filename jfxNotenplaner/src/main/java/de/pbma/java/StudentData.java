package de.pbma.java;

public class StudentData {
	private static StudentData studentData;
	private Student student;
	private Object lock;

	private StudentData() {
		this.lock = new Object();
	}

	public static synchronized StudentData getStudentData() {
		if (studentData == null) {
			studentData = new StudentData();
		}
		return studentData;
	}

	public void updateStudentData(Student student) {
		synchronized (lock) {
			this.student = student;
		}

	}

	public Student getStudent() {
		synchronized (lock) {
			return (student == null) ? null : (Student) student.clone();
		}
	}
}
