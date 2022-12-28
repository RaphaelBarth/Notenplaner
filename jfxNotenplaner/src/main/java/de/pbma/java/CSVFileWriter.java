package de.pbma.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CSVFileWriter {

	private final String DELIMITER = ";";
	private File file;

	public CSVFileWriter(File file) {
		this.file = file;
	}

	public boolean saveCurriculum(Curriculum curriculum) {
		String csvString = this.toCSVString(curriculum);
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

	private String toCSVString(Curriculum curriculum) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(curriculum.getNameShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curriculum.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curriculum.getCredits());
		stringBuilder.append("\n");
		for (var subject : curriculum.getSubjects()) {
			var string = this.toCSVString(subject);
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

	private String toCSVString(CurriculumSubject subject) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(subject.getShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getFocus());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.hasGradeAsEvaluation());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getSemester());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getCreditPoints());
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
		for (var ele : student.getSubjectGradeMap().entrySet()) {
			System.out.println(student);
			System.out.println(ele);
			var string = this.toCSVString(ele.getKey(), ele.getValue().value);
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

	private Object toCSVString(CurriculumSubject subject, double grade) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(subject.getShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(subject.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(grade);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

}
