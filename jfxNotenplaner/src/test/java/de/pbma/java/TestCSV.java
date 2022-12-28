package de.pbma.java;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class TestCSV {

	Curriculum curriculum;
	Student student;

	@Before
	public void setUp() {
		// setup curriculum
		curriculum = new Curriculum("Medizintechnik", "MTB", 210);
		curriculum.addSubject(new CurriculumSubject("EIP", "Einführung in die Programmierung", "Software", true, 1, 7));
		curriculum.addSubject(new CurriculumSubject("ET1", "Elektrotechnik 1", "Grundlagen", true, 1, 5));
		curriculum.addSubject(new CurriculumSubject("MED1", "Grundlagen der Medizin", "Medizin", true, 1, 3.5));
		curriculum.addSubject(new CurriculumSubject("DT", "Digitaltechnik", "Grundlagen", true, 1, 5));
		curriculum.addSubject(new CurriculumSubject("MA1", "Mathematik 1", "Grundlagen", true, 1, 5));
		curriculum.addSubject(new CurriculumSubject("PH", "Physik", "Grundlagen", true, 1, 6));
		curriculum
				.addSubject(new CurriculumSubject("MED2", "Anatomie, Physiologie, Biochemie", "Medizin", true, 2, 3.5));
		curriculum.addSubject(new CurriculumSubject("WST", "Wechselstromtechnik", "Elektronik", true, 2, 4));
		curriculum
				.addSubject(new CurriculumSubject("GST", "Grundlagen der Schaltungstechnik", "Elektronik", true, 2, 4));
		curriculum.addSubject(new CurriculumSubject("MA2", "Mathematik 2", "Grundlagen", true, 2, 6));
		curriculum.addSubject(new CurriculumSubject("OOP", "Objektorientierte Programmierung", "Software", true, 2, 6));

		curriculum.addSubject(new CurriculumSubject("HPS", "Höhere Programmiersprachen", "Software", true, 3, 5));
		curriculum.addSubject(new CurriculumSubject("EMT", "Einführung in die Medizintechnik", "Medizin", true, 3, 1));
		curriculum.addSubject(
				new CurriculumSubject("EEL", "Entwurf elektronischer Schaltungen", "Elektronik", true, 3, 5));
		curriculum.addSubject(new CurriculumSubject("WPF1a", "Felder", "Elektronik", true, 3, 5));
		curriculum.addSubject(new CurriculumSubject("DB", "Datenbanken", "Software", true, 3, 5));
		curriculum.addSubject(new CurriculumSubject("SS", "Signale und Systeme", "Signalverarbeitung", true, 3, 5));
		curriculum
				.addSubject(new CurriculumSubject("DMC", "Digital- und Microcomputertechnik", "Embedded", true, 3, 5));
		curriculum.addSubject(new CurriculumSubject("MA3", "Mathematik 3", "Grundlagen", true, 3, 6));

		curriculum.addSubject(new CurriculumSubject("SOE", "Software-Engineering", "Software", true, 4, 5));
		curriculum.addSubject(new CurriculumSubject("SET", "Software-Entwicklungsmethoden und -Entwicklungstools",
				"Software", true, 4, 5));
		curriculum.addSubject(new CurriculumSubject("APH", "Angewandte Physik", "Grundlagen", true, 4, 5));
		curriculum.addSubject(
				new CurriculumSubject("DSV", "Digitale Signalverarbeitung", "Signalverarbeitung", true, 4, 5));
		curriculum.addSubject(new CurriculumSubject("EMB", "Embedded Systems", "Embedded", true, 4, 5));
		curriculum.addSubject(new CurriculumSubject("BS", "Betriebssysteme", "Software", true, 4, 5));
		curriculum.addSubject(new CurriculumSubject("HF", "Hochfrequenztechnik", "Elektronik", true, 4, 5));

		curriculum.addSubject(new CurriculumSubject("PS", "Praxissemester", "Software", false, 5, 25));
		curriculum.addSubject(new CurriculumSubject("BV", "Blockveranstaltung", "Software", false, 5, 5));

		curriculum.addSubject(
				new CurriculumSubject("BVM", "Bilgebende Verfahren in der Medizintechnik", "Medizin", true, 6, 5));
		curriculum.addSubject(new CurriculumSubject("MED3", "Klinische Medizin", "Medizin", true, 6, 4));
		curriculum.addSubject(
				new CurriculumSubject("KIM", "Künstliche Intelligenz in der Medizintechnik", "Medizin", true, 6, 5));
		curriculum.addSubject(new CurriculumSubject("NE", "Neural Engineering", "Elektronik", true, 6, 5));
		curriculum
				.addSubject(new CurriculumSubject("PLB", "Programmierbare Logikbausteine", "Schaltungen", true, 6, 5));
		curriculum.addSubject(new CurriculumSubject("MST", "Mess- und Sensortechnik", "Elektronik", true, 6, 5));
		curriculum.addSubject(new CurriculumSubject("RT", "Regelungstechnik", "Elektronik", true, 6, 5));
		curriculum.addSubject(new CurriculumSubject("ML", "Machine Learning", "Software", true, 6, 5));

		curriculum.addSubject(new CurriculumSubject("WF1", "", "Wahlfach", true, 7, 5));
		curriculum.addSubject(new CurriculumSubject("WF2", "", "Wahlfach", true, 7, 5));
		curriculum.addSubject(new CurriculumSubject("ZMP", "Zulassung von Medizinprodukten", "Medizin", true, 7, 5));

		curriculum.addSubject(new CurriculumSubject("BA", "Bachelorarbeit", "Wahlfach", true, 7, 15));

		// setup student
		student = new Student("Hans Baum", 123456789, "MTB");
		student.setGradeForSubject("BVM", "Bilgebende Verfahren in der Medizintechnik", Grades.FOUR);
		student.setGradeForSubject("MED3", "Klinische Medizin", Grades.THREEMINUS);
		student.setGradeForSubject("KIM", "Künstliche Intelligenz in der Medizintechnik", Grades.FOUR);
		student.setGradeForSubject("NE", "Neural Engineering", Grades.PASSED);
		student.setGradeForSubject("PLB", "Programmierbare Logikbausteine", Grades.TWO);
		student.setGradeForSubject("MST", "Mess- und Sensortechnik", Grades.THREE);
		student.setGradeForSubject("RT", "Regelungstechnik", Grades.NOTPASSED);
		student.setGradeForSubject("ML", "Machine Learning", Grades.FOUR);

	}

	@Test
	public void testCurriculum() throws ParserException, FileNotFoundException {
		var cFile = new File(curriculum.getNameShort() + ".csv");
		var writeCSV = new CSVFileWriter(cFile);
		writeCSV.saveCurriculum(curriculum);
		assertEquals(curriculum, CSVFileParser.getCurriculum(cFile));
		// cFile.delete();
	}

	@Test
	public void testStudent() throws ParserException, FileNotFoundException {
		var sFile = new File(student.getName() + ".csv");
		var writeCSV = new CSVFileWriter(sFile);
		writeCSV.saveStudent(student);
		assertEquals(student, CSVFileParser.getStudent(sFile));
		// sFile.delete();
	}

}
