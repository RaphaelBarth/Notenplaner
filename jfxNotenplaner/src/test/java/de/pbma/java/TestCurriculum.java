package de.pbma.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class TestCurriculum {

	Curriculum curriculumOriginal;
	CurriculumSubject curriculumSubject1;
	CurriculumSubject curriculumSubject2;

	Subject subject;

	@Before
	public void setUp() {
		// setup curriculum
		curriculumOriginal = new Curriculum("Medizintechnik", "MTB", 210);
		curriculumOriginal.addSubject(new CurriculumSubject("EIP", "Einführung in die Programmierung", "Software", true, 1, 7));
		curriculumOriginal.addSubject(new CurriculumSubject("ET1", "Elektrotechnik 1", "Grundlagen", true, 1, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("MED1", "Grundlagen der Medizin", "Medizin", true, 1, 3.5));
		curriculumOriginal.addSubject(new CurriculumSubject("DT", "Digitaltechnik", "Grundlagen", true, 1, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("MA1", "Mathematik 1", "Grundlagen", true, 1, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("PH", "Physik", "Grundlagen", true, 1, 6));
		curriculumOriginal
				.addSubject(new CurriculumSubject("MED2", "Anatomie, Physiologie, Biochemie", "Medizin", true, 2, 3.5));
		curriculumOriginal.addSubject(new CurriculumSubject("WST", "Wechselstromtechnik", "Elektronik", true, 2, 4));
		curriculumOriginal
				.addSubject(new CurriculumSubject("GST", "Grundlagen der Schaltungstechnik", "Elektronik", true, 2, 4));
		curriculumOriginal.addSubject(new CurriculumSubject("MA2", "Mathematik 2", "Grundlagen", true, 2, 6));
		curriculumOriginal.addSubject(new CurriculumSubject("OOP", "Objektorientierte Programmierung", "Software", true, 2, 6));

		curriculumOriginal.addSubject(new CurriculumSubject("HPS", "Höhere Programmiersprachen", "Software", true, 3, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("EMT", "Einführung in die Medizintechnik", "Medizin", true, 3, 1));
		curriculumOriginal.addSubject(
				new CurriculumSubject("EEL", "Entwurf elektronischer Schaltungen", "Elektronik", true, 3, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("WPF1a", "Felder", "Elektronik", true, 3, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("DB", "Datenbanken", "Software", true, 3, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("SS", "Signale und Systeme", "Signalverarbeitung", true, 3, 5));
		curriculumOriginal
				.addSubject(new CurriculumSubject("DMC", "Digital- und Microcomputertechnik", "Embedded", true, 3, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("MA3", "Mathematik 3", "Grundlagen", true, 3, 6));

		curriculumOriginal.addSubject(new CurriculumSubject("SOE", "Software-Engineering", "Software", true, 4, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("SET", "Software-Entwicklungsmethoden und -Entwicklungstools",
				"Software", true, 4, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("APH", "Angewandte Physik", "Grundlagen", true, 4, 5));
		curriculumOriginal.addSubject(
				new CurriculumSubject("DSV", "Digitale Signalverarbeitung", "Signalverarbeitung", true, 4, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("EMB", "Embedded Systems", "Embedded", true, 4, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("BS", "Betriebssysteme", "Software", true, 4, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("HF", "Hochfrequenztechnik", "Elektronik", true, 4, 5));

		curriculumOriginal.addSubject(new CurriculumSubject("PS", "Praxissemester", "Software", false, 5, 25));
		curriculumOriginal.addSubject(new CurriculumSubject("BV", "Blockveranstaltung", "Software", false, 5, 5));

		curriculumOriginal.addSubject(
				new CurriculumSubject("BVM", "Bilgebende Verfahren in der Medizintechnik", "Medizin", true, 6, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("MED3", "Klinische Medizin", "Medizin", true, 6, 4));
		curriculumOriginal.addSubject(
				new CurriculumSubject("KIM", "Künstliche Intelligenz in der Medizintechnik", "Medizin", true, 6, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("NE", "Neural Engineering", "Elektronik", true, 6, 5));
		curriculumOriginal
				.addSubject(new CurriculumSubject("PLB", "Programmierbare Logikbausteine", "Schaltungen", true, 6, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("MST", "Mess- und Sensortechnik", "Elektronik", true, 6, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("RT", "Regelungstechnik", "Elektronik", true, 6, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("ML", "Machine Learning", "Software", true, 6, 5));

		curriculumOriginal.addSubject(new CurriculumSubject("WF1", "", "Wahlfach", true, 7, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("WF2", "", "Wahlfach", true, 7, 5));
		curriculumOriginal.addSubject(new CurriculumSubject("ZMP", "Zulassung von Medizinprodukten", "Medizin", true, 7, 5));

		curriculumOriginal.addSubject(new CurriculumSubject("BA", "Bachelorarbeit", "Wahlfach", true, 7, 15));
		
		subject = new Subject("AA", "Ananas", "Obst");
		curriculumSubject1 = new CurriculumSubject(subject, 0, 7);
		curriculumSubject2 = new CurriculumSubject("BB","Bambus","Strauch",false,0,6);
		curriculumOriginal.addSubject(curriculumSubject2);
	}

	@Test
	public void testClone()  {
		CurriculumData.getData().setCurriculum(curriculumOriginal);
		var curriculumCopy = CurriculumData.getData().getCurriculum();
		assertFalse(curriculumCopy == curriculumOriginal);
		
		curriculumCopy = CurriculumData.getData().getCurriculum();
		curriculumCopy.addSubject(curriculumSubject1);
		assertNotEquals(curriculumOriginal,curriculumCopy);
		
		curriculumCopy = CurriculumData.getData().getCurriculum();
		curriculumCopy.addSubject(subject,1,2);
		assertNotEquals(curriculumOriginal,curriculumCopy);
		
		curriculumCopy = CurriculumData.getData().getCurriculum();
		curriculumCopy.removeSubject(curriculumSubject2);
		assertNotEquals(curriculumOriginal,curriculumCopy);
		
		curriculumCopy = CurriculumData.getData().getCurriculum();
		curriculumCopy.getAllSubjects().add(curriculumSubject1);
		assertNotEquals(curriculumOriginal,curriculumCopy);
		
		curriculumCopy = CurriculumData.getData().getCurriculum();
		curriculumCopy.addSubject(curriculumSubject1);
		CurriculumData.getData().setCurriculum(curriculumCopy);
		var curriculumNew = CurriculumData.getData().getCurriculum();
		assertNotEquals(curriculumOriginal, curriculumNew);
		assertEquals(curriculumCopy, curriculumNew);
	}


}
