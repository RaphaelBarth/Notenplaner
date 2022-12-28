package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.javatuples.Triplet;


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
				var ele = toCompletedSubjects(line);
				student.setGradeForSubject(ele.getValue0(), ele.getValue1(),ele.getValue2());
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
		CurriculumSubject curriculumSubject = null;
		System.out.println(string);
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var shortName = rowScanner.next();
			var name = rowScanner.next();
			var focus = rowScanner.next();
			var hasGradeAsEvaluation = rowScanner.nextBoolean();
			var semester = rowScanner.nextInt();
			var credits = Double.parseDouble(rowScanner.next());
			curriculumSubject = new CurriculumSubject(shortName, name, focus, hasGradeAsEvaluation, semester, credits);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParserException(e.getMessage());
		}
		return curriculumSubject;
	}

	private static Triplet<String, String, Grades> toCompletedSubjects(String string) throws ParserException {
		Triplet<String, String, Grades> triplet = null;
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			var subjectShort = rowScanner.next();
			var subjectName = rowScanner.next();
			var gradeValue = Double.valueOf(rowScanner.next());
			var grade = Grades.getByValue(gradeValue);
			triplet = new Triplet<>(subjectShort, subjectName, grade);
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return triplet;
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
