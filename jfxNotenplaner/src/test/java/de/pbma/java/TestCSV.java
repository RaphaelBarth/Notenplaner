package de.pbma.java;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestCSV {

	Curricula curricula;
	Student student;

	@Before
	public void setUp() {
		// setup curricula
		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject("PI", "Praktische Informatik", "Software", 1, 5));
		subjects.add(new Subject("ES", "Elektrische Schaltungen", "Elektronik", 1, 5));
		subjects.add(new Subject("WF1", "", "Wahlfach", 3, 5));
		subjects.add(new Subject("EMB", "Embedded Systems", "Embedded", 1, 5));
		subjects.add(new Subject("MA", "Mathematik", "Grundlagen", 1, 5));
		subjects.add(new Subject("DB", "Datenbanken", "Software", 1, 5));
		curricula = new Curricula("MTB", "MedizinTechnik", 210, subjects);

		// setup student
		List<CompletedSubjects> completedSubjects = new ArrayList<>();
		completedSubjects.add(new CompletedSubjects("PI", "Praktische Informatik", Grades.FOURPLUS));
		completedSubjects.add(new CompletedSubjects("ES", "Elektrische Schaltungen", Grades.ONE));
		completedSubjects.add(new CompletedSubjects("WF1", "Java", Grades.ONE));
		completedSubjects.add(new CompletedSubjects("EMB", "Embedded Systems", Grades.TWOMINUS));
		completedSubjects.add(new CompletedSubjects("MA", "Mathematik", Grades.THREEPLUS));
		completedSubjects.add(new CompletedSubjects("DB", "Datenbanken", Grades.TWOPLUS));
		student = new Student("Hans Baum", 123456789, "MTB", completedSubjects);
	}

	@Test
	public void testCurricula() throws ParserException {
		var cFile = new File("c.csv");
		var writeCSV = new WriteCSV(cFile);
		writeCSV.saveCurricula(curricula);
		var parser = new CSVFileParser(cFile);
		assertEquals(curricula, parser.getCurricula());
		cFile.delete();
	}

	@Test
	public void testStudent() throws ParserException {
		var sFile = new File("s.csv");
		var writeCSV = new WriteCSV(sFile);
		writeCSV.saveStudent(student);
		var parser = new CSVFileParser(sFile);
		assertEquals(student,parser.getStudent() );
		sFile.delete();
	}

}
