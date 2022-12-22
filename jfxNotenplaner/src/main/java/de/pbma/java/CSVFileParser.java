package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVFileParser {
	private final String DELIMITER = ";";
	private File file;

	public CSVFileParser(File file) throws FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		this.file = file;
	}

	public Curriculum getCurriculum() throws ParserException {
		Curriculum curriculum = new Curriculum();

		try (Scanner scanner = new Scanner(file)) {
			var header = scanner.nextLine();
			curriculum = toCurriculum(header);
			while (scanner.hasNextLine()) {
				var line = scanner.nextLine();
				Subject subject = this.toSubject(line);
				curriculum.addSubject(subject);
			}
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
		return curriculum;
	}

	public Student getStudent() throws ParserException {
		Student student = new Student();

		try (Scanner scanner = new Scanner(file)) {
			var header = scanner.nextLine();
			student = this.toStudent(header);
			while (scanner.hasNextLine()) {
				var line = scanner.nextLine();
				CompletedSubjects completedSubjects = this.toCompletedSubjects(line);
				student.addCompletedSubject(completedSubjects);
			}
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
		return student;
	}

	private Curriculum toCurriculum(String string) throws ParserException {
		Curriculum c = new Curriculum();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			c.setNameShort(rowScanner.next());
			c.setName(rowScanner.next());
			c.setCredits(Double.parseDouble(rowScanner.next()));
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return c;
	}

	private Subject toSubject(String string) throws ParserException {
		Subject subject = new Subject();
		System.out.println(string);
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			subject.setSubjectShort(rowScanner.next());
			subject.setSubject(rowScanner.next());
			subject.setFocus(rowScanner.next());
			subject.setSemester(rowScanner.nextInt());
			subject.setCredits(Double.parseDouble(rowScanner.next()));
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return subject;
	}

	private CompletedSubjects toCompletedSubjects(String string) throws ParserException {
		CompletedSubjects completedSubjects = new CompletedSubjects();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			completedSubjects.setSubjectShort(rowScanner.next());
			completedSubjects.setSubject(rowScanner.next());
			Double gradeValue = Double.valueOf(rowScanner.next());
			completedSubjects.setGrade(Grades.getByValue(gradeValue));
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return completedSubjects;
	}

	private Student toStudent(String string) throws ParserException {
		Student student = new Student();
		try (Scanner rowScanner = new Scanner(string)) {
			rowScanner.useDelimiter(DELIMITER);
			student.setName(rowScanner.next());
			student.setMatriculationNumber(rowScanner.nextInt());
			student.setCourseOfStudies(rowScanner.next());
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}
		return student;
	}

}
