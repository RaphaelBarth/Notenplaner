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
				CurriculumSubject subject = toCurriculumSubject(line);
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
				toSubjects(student, line);
			}
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
		return student;
	}

	private static Curriculum toCurriculum(String string) throws ParserException {
		Curriculum curriculum = null;
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var nameShort = rowScanner.next();
			var name = rowScanner.next();
			var credits = Double.parseDouble(rowScanner.next());
			curriculum = new Curriculum(nameShort, name, credits);
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return curriculum;
	}

	private static CurriculumSubject toCurriculumSubject(String string) throws ParserException {
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var shortName = rowScanner.next();
			var name = rowScanner.next();
			var focus = rowScanner.next();
			var hasGradeAsEvaluation = rowScanner.nextBoolean();
			var semester = rowScanner.nextInt();
			var credits = Double.parseDouble(rowScanner.next());
			CurriculumSubject curriculumSubject = new CurriculumSubject(shortName, name, focus, hasGradeAsEvaluation,
					semester, credits);
			return curriculumSubject;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParserException(e.getMessage());
		}
	}

	private static void toSubjects(Student student, String string) throws ParserException {
		var stringArray = string.split(DELIMITER, -1);
		if (stringArray.length != 3) {
			throw new ParserException("Studentfile error while parsing Subjects");
		}
		var subjectShort = stringArray[0];
		var subjectName = stringArray[1];
		var subjectGrade = stringArray[2];
		// System.out.format("(%s) (%s) (%s)\n",subjectShort,subjectName,subjectGrade);
		if (!subjectName.isEmpty()) {
			student.setNameForSubject(subjectShort, subjectName);
		}
		if (!subjectGrade.isEmpty()) {
			student.setGradeForSubject(subjectShort, Grades.valueOf(subjectGrade));
		}
		return;
	}

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
