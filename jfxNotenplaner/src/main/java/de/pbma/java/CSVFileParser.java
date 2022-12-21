package de.pbma.java;

import java.util.Scanner;

public class ParserCSV {
	public final static String DELIMITER = ";";

	public static Curricula parseCSVStringToCurricula(String string) {
		Curricula courseOfStudies = new Curricula();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(";");
			courseOfStudies.setNameShort(rowScanner.next());
			courseOfStudies.setName(rowScanner.next());
			courseOfStudies.setCredits(rowScanner.nextInt());
		}
		return courseOfStudies;
	}

	public static Subject parseCSVStringToSubject(String string) {
		Subject subject = new Subject();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(";");
			subject.setSubjectShort(rowScanner.next());
			subject.setSubject(rowScanner.next());
			subject.setFocus(rowScanner.next());
			subject.setSemester(rowScanner.nextInt());
			subject.setCredits(rowScanner.nextInt());
		}
		return subject;
	}
	
	public static CompletedSubjects parseCSVStringToCompletedSubjects(String string) {
		CompletedSubjects completedSubjects = new CompletedSubjects();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(";");
			completedSubjects.setSubjectShort(rowScanner.next());
			completedSubjects.setSubject(rowScanner.next());
			//completedSubjects.getGrade(Grades(rowScanner.nextDouble()));
		}
		return completedSubjects;
	}

	public static String parseSubjectToCSVString(Subject subject) {
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

	public static String parseCurriculaToCSVString(Curricula curricula) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(curricula.getNameShort());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curricula.getName());
		stringBuilder.append(DELIMITER);
		stringBuilder.append(curricula.getCredits());
		stringBuilder.append("\n");
		for (var subject : curricula.getSubjects()) {
			var string = ParserCSV.parseSubjectToCSVString(subject);
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

}
