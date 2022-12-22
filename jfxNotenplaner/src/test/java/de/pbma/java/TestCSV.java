package de.pbma.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestCSV {

	Curriculum curriculum;
	Student student;

	@Before
	public void setUp() {
		// setup curriculum
		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject("EIP", "Einführung in die Programmierung", "Software", 1, 7));
		subjects.add(new Subject("ET1", "Elektrotechnik 1", "Grundlagen", 1, 5));
		subjects.add(new Subject("MED1", "Grundlagen der Medizin", "Medizin", 1, 3.5));
		subjects.add(new Subject("DT", "Digitaltechnik", "Grundlagen", 1, 5));
		subjects.add(new Subject("MA1", "Mathematik 1", "Grundlagen", 1, 5));
		subjects.add(new Subject("PH", "Physik", "Grundlagen", 1, 6));

		subjects.add(new Subject("MED2", "Anatomie, Physiologie, Biochemie", "Medizin", 2, 3.5));
		subjects.add(new Subject("WST", "Wechselstromtechnik", "Elektronik", 2, 4));
		subjects.add(new Subject("GST", "Grundlagen der Schaltungstechnik", "Elektronik", 2, 4));
		subjects.add(new Subject("MA2", "Mathematik 2", "Grundlagen", 2, 6));
		subjects.add(new Subject("OOP", "Objektorientierte Programmierung", "Software", 2, 6));

		subjects.add(new Subject("HPS", "Höhere Programmiersprachen", "Software", 3, 5));
		subjects.add(new Subject("EMT", "Einführung in die Medizintechnik", "Medizin", 3, 1));
		subjects.add(new Subject("EEL", "Entwurf elektronischer Schaltungen", "Elektronik", 3, 5));
		subjects.add(new Subject("WPF1a", "Felder", "Elektronik", 3, 5));
		subjects.add(new Subject("DB", "Datenbanken", "Software", 3, 5));
		subjects.add(new Subject("SS", "Signale und Systeme", "Signalverarbeitung", 3, 5));
		subjects.add(new Subject("DMC", "Digital- und Microcomputertechnik", "Embedded", 3, 5));
		subjects.add(new Subject("MA3", "Mathematik 3", "Grundlagen", 3, 6));

		subjects.add(new Subject("SOE", "Software-Engineering", "Software", 4, 5));
		subjects.add(new Subject("SET", "Software-Entwicklungsmethoden und -Entwicklungstools", "Software", 4, 5));
		subjects.add(new Subject("APH", "Angewandte Physik", "Grundlagen", 4, 5));
		subjects.add(new Subject("DSV", "Digitale Signalverarbeitung", "Signalverarbeitung", 4, 5));
		subjects.add(new Subject("EMB", "Embedded Systems", "Embedded", 4, 5));
		subjects.add(new Subject("BS", "Betriebssysteme", "Software", 4, 5));
		subjects.add(new Subject("HF", "Hochfrequenztechnik", "Elektronik", 4, 5));

		subjects.add(new Subject("PS", "Praxissemester", "Software", 5, 25));
		subjects.add(new Subject("BV", "Blockveranstaltung", "Software", 5, 5));

		subjects.add(new Subject("BVM", "Bilgebende Verfahren in der Medizintechnik", "Medizin", 6, 5));
		subjects.add(new Subject("MED3", "Klinische Medizin", "Medizin", 6, 4));
		subjects.add(new Subject("KIM", "Künstliche Intelligenz in der Medizintechnik", "Medizin", 6, 5));
		subjects.add(new Subject("NE", "Neural Engineering", "Elektronik", 6, 5));
		subjects.add(new Subject("PLB", "Programmierbare Logikbausteine", "Schaltungen", 6, 5));
		subjects.add(new Subject("MST", "Mess- und Sensortechnik", "Elektronik", 6, 5));
		subjects.add(new Subject("RT", "Regelungstechnik", "Elektronik", 6, 5));
		subjects.add(new Subject("ML", "Machine Learning", "Software", 6, 5));

		subjects.add(new Subject("WF1", "", "Wahlfach", 7, 5));
		subjects.add(new Subject("WF2", "", "Wahlfach", 7, 5));
		subjects.add(new Subject("ZMP", "Zulassung von Medizinprodukten", "Medizin", 7, 5));

		subjects.add(new Subject("BA", "Bachelorarbeit", "Wahlfach", 7, 15));
		curriculum = new Curriculum("Medizintechnik", "MTB", 210, subjects);

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
	public void testCurricula() throws ParserException, FileNotFoundException {
		var cFile = new File(curriculum.getNameShort() + ".csv");
		var writeCSV = new CSVFileWriter(cFile);
		writeCSV.saveCurriculum(curriculum);
		CSVFileParser parser = null;
		try {
			parser = new CSVFileParser(cFile);
		} catch (FileNotFoundException e) {
			assertTrue(true);
		}
		assertEquals(curriculum, parser.getCurriculum());
		// cFile.delete();
	}

	@Test
	public void testStudent() throws ParserException, FileNotFoundException {
		var sFile = new File("s.csv");
		var writeCSV = new CSVFileWriter(sFile);
		writeCSV.saveStudent(student);
		var parser = new CSVFileParser(sFile);
		assertEquals(student, parser.getStudent());
		sFile.delete();
	}

}
