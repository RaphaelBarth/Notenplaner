package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVFileParser {
	private static final String DELIMITER = ";";

	public static Curriculum getCurriculum(File file) throws ParserException, FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}

		Curriculum curriculum = null;

		try (Scanner scanner = new Scanner(file)) {
			var header = scanner.nextLine();
			curriculum = CSVFileParser.toCurriculum(header);
			while (scanner.hasNextLine()) {
				var line = scanner.nextLine();
				CurriculumSubject subject = toSubject(line);
				curriculum.addSubject(subject);
			}
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
		return curriculum;
	}

	public static Student getStudent(File file) throws ParserException, FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}

		Student student = null;

		try (Scanner scanner = new Scanner(file)) {
			var header = scanner.nextLine();
			student = CSVFileParser.toStudent(header);
			while (scanner.hasNextLine()) {
				var line = scanner.nextLine();
				// CompletedSubject completedSubjects = CSVFileParser.toCompletedSubjects(line);
				// student.addCompletedSubject(completedSubjects);
			}
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
		return student;
	}

	private static Curriculum toCurriculum(String string) throws ParserException {
		Curriculum c = null;
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			c = new Curriculum(rowScanner.next(), rowScanner.next(), Double.parseDouble(rowScanner.next()));
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return c;
	}

	private static CurriculumSubject toSubject(String string) throws ParserException {
		CurriculumSubject subject = null;
		System.out.println(string);
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var shortName = rowScanner.next();
			var name = rowScanner.next();
			var focus = rowScanner.next();
			var s = new Subject(shortName, name, focus);
			subject = new CurriculumSubject(s, rowScanner.nextInt(), Double.parseDouble(rowScanner.next()));
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return subject;
	}

//	private CompletedSubject toCompletedSubjects(String string) throws ParserException {
//		CompletedSubject completedSubjects = new CompletedSubject();
//		try (Scanner rowScanner = new Scanner(string)) {
//			rowScanner.useDelimiter(DELIMITER);
//			completedSubjects.setSubjectShort(rowScanner.next());
//			completedSubjects.setSubject(rowScanner.next());
//			Double gradeValue = Double.valueOf(rowScanner.next());
//			completedSubjects.setGrade(Grades.getByValue(gradeValue));
//		} catch (Exception e) {
//			throw new ParserException(e.getMessage());
//		}
//		return completedSubjects;
//	}

	private static Student toStudent(String string) throws ParserException {
		Student student = null;
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var name = rowScanner.next();
			var matriculationNumber = rowScanner.nextInt();
			var courseOfStudies = rowScanner.next();
			student = new Student(name, matriculationNumber, courseOfStudies);
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return student;
	}

}
