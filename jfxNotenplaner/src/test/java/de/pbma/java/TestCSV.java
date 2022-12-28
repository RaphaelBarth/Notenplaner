package de.pbma.java;

import static org.junit.Assert.assertEquals;

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
		List<CurriculumSubject> CurriculumSubjects = new ArrayList<>();
//		CurriculumSubjects.add(new CurriculumSubject("EIP", "Einführung in die Programmierung", "Software", 1, 7));
//		CurriculumSubjects.add(new CurriculumSubject("ET1", "Elektrotechnik 1", "Grundlagen", 1, 5));
//		CurriculumSubjects.add(new CurriculumSubject("MED1", "Grundlagen der Medizin", "Medizin", 1, 3.5));
//		CurriculumSubjects.add(new CurriculumSubject("DT", "Digitaltechnik", "Grundlagen", 1, 5));
//		CurriculumSubjects.add(new CurriculumSubject("MA1", "Mathematik 1", "Grundlagen", 1, 5));
//		CurriculumSubjects.add(new CurriculumSubject("PH", "Physik", "Grundlagen", 1, 6));
//
//		CurriculumSubjects.add(new CurriculumSubject("MED2", "Anatomie, Physiologie, Biochemie", "Medizin", 2, 3.5));
//		CurriculumSubjects.add(new CurriculumSubject("WST", "Wechselstromtechnik", "Elektronik", 2, 4));
//		CurriculumSubjects.add(new CurriculumSubject("GST", "Grundlagen der Schaltungstechnik", "Elektronik", 2, 4));
//		CurriculumSubjects.add(new CurriculumSubject("MA2", "Mathematik 2", "Grundlagen", 2, 6));
//		CurriculumSubjects.add(new CurriculumSubject("OOP", "Objektorientierte Programmierung", "Software", 2, 6));
//
//		CurriculumSubjects.add(new CurriculumSubject("HPS", "Höhere Programmiersprachen", "Software", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("EMT", "Einführung in die Medizintechnik", "Medizin", 3, 1));
//		CurriculumSubjects.add(new CurriculumSubject("EEL", "Entwurf elektronischer Schaltungen", "Elektronik", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("WPF1a", "Felder", "Elektronik", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("DB", "Datenbanken", "Software", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("SS", "Signale und Systeme", "Signalverarbeitung", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("DMC", "Digital- und Microcomputertechnik", "Embedded", 3, 5));
//		CurriculumSubjects.add(new CurriculumSubject("MA3", "Mathematik 3", "Grundlagen", 3, 6));
//
//		CurriculumSubjects.add(new CurriculumSubject("SOE", "Software-Engineering", "Software", 4, 5));
//		CurriculumSubjects.add(
//				new CurriculumSubject("SET", "Software-Entwicklungsmethoden und -Entwicklungstools", "Software", 4, 5));
//		CurriculumSubjects.add(new CurriculumSubject("APH", "Angewandte Physik", "Grundlagen", 4, 5));
//		CurriculumSubjects.add(new CurriculumSubject("DSV", "Digitale Signalverarbeitung", "Signalverarbeitung", 4, 5));
//		CurriculumSubjects.add(new CurriculumSubject("EMB", "Embedded Systems", "Embedded", 4, 5));
//		CurriculumSubjects.add(new CurriculumSubject("BS", "Betriebssysteme", "Software", 4, 5));
//		CurriculumSubjects.add(new CurriculumSubject("HF", "Hochfrequenztechnik", "Elektronik", 4, 5));
//
//		CurriculumSubjects.add(new CurriculumSubject("PS", "Praxissemester", "Software", 5, 25));
//		CurriculumSubjects.add(new CurriculumSubject("BV", "Blockveranstaltung", "Software", 5, 5));
//
//		CurriculumSubjects
//				.add(new CurriculumSubject("BVM", "Bilgebende Verfahren in der Medizintechnik", "Medizin", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("MED3", "Klinische Medizin", "Medizin", 6, 4));
//		CurriculumSubjects
//				.add(new CurriculumSubject("KIM", "Künstliche Intelligenz in der Medizintechnik", "Medizin", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("NE", "Neural Engineering", "Elektronik", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("PLB", "Programmierbare Logikbausteine", "Schaltungen", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("MST", "Mess- und Sensortechnik", "Elektronik", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("RT", "Regelungstechnik", "Elektronik", 6, 5));
//		CurriculumSubjects.add(new CurriculumSubject("ML", "Machine Learning", "Software", 6, 5));
//
//		CurriculumSubjects.add(new CurriculumSubject("WF1", "", "Wahlfach", 7, 5));
//		CurriculumSubjects.add(new CurriculumSubject("WF2", "", "Wahlfach", 7, 5));
//		CurriculumSubjects.add(new CurriculumSubject("ZMP", "Zulassung von Medizinprodukten", "Medizin", 7, 5));
//
		CurriculumSubjects.add(new CurriculumSubject("BA", "Bachelorarbeit", "Wahlfach",true, 7, 15));
		curriculum = new Curriculum("Medizintechnik", "MTB", 210, CurriculumSubjects);

		// setup student
		student = new Student("Hans Baum", 123456789, "MTB");
		student.setGradeForSubject(
				new CurriculumSubject("BVM", "Bilgebende Verfahren in der Medizintechnik", "Medizin",true, 6, 5),
				Grades.ONE);
		student.setGradeForSubject(new CurriculumSubject("MED3", "Klinische Medizin", "Medizin",true, 6, 4),
				Grades.THREEMINUS);
		student.setGradeForSubject(
				new CurriculumSubject("KIM", "Künstliche Intelligenz in der Medizintechnik", "Medizin",true, 6, 5),
				Grades.FOUR);
		student.setGradeForSubject(new CurriculumSubject("NE", "Neural Engineering", "Elektronik",true, 6, 5),
				Grades.PASSED);
		student.setGradeForSubject(new CurriculumSubject("PLB", "Programmierbare Logikbausteine", "Schaltungen",true, 6, 5),
				Grades.TWO);
		student.setGradeForSubject(new CurriculumSubject("MST", "Mess- und Sensortechnik", "Elektronik",true, 6, 5),
				Grades.THREE);
		student.setGradeForSubject(new CurriculumSubject("RT", "Regelungstechnik", "Elektronik",true, 6, 5),
				Grades.NOTPASSED);
		student.setGradeForSubject(new CurriculumSubject("ML", "Machine Learning", "Software",true, 6, 5), Grades.FOUR);

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
		var sFile = new File("s.csv");
		var writeCSV = new CSVFileWriter(sFile);
		writeCSV.saveStudent(student);
		assertEquals(student, CSVFileParser.getStudent(sFile));
		sFile.delete();
	}

}
