package de.pbma.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteCSV {

	private final String DELIMITER = ";";
	private File file;

	public WriteCSV(File file) {
		super();
		this.file = file;
	}

	public boolean saveCurricula(Curricula curricula) {
		String csvString = this.toCSVString(curricula);
		return writeToCSV(csvString);
	}

	public boolean saveStudent(Student student) {
		String csvString = this.toCSVString(student);
		return writeToCSV(csvString);
	}

	private boolean writeToCSV(String csvString) {
		boolean retval = false;
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			fileOutputStream.write(csvString.getBytes());
			retval = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retval;
	}

	private String toCSVString(Curricula curricula) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(curricula.getNameShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curricula.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curricula.getCredits());
		stringBuilder.append("\n");
		for (var subject : curricula.getSubjects()) {
			var string = this.toCSVString(subject);
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

	private String toCSVString(Subject subject) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(subject.getSubjectShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getSubject());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getFocus());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getSemester());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getCredits());
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

	private String toCSVString(Student student) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(student.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(student.getMatriculationNumber());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(student.getCourseOfStudies());
		stringBuilder.append("\n");
		for (var completedSubject : student.getCompletedSubjects()) {
			var string = this.toCSVString(completedSubject);
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

	private String toCSVString(CompletedSubjects completedSubject) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(completedSubject.getSubjectShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(completedSubject.getSubject());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(completedSubject.getGrade().value);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}
