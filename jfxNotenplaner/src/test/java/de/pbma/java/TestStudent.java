package de.pbma.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class TestStudent {

	Student studentOriginal;

	@Before
	public void setUp() {

		// setup student
		studentOriginal = new Student("Hans Baum", 123456789, "MTB");
		studentOriginal.setGradeForSubject("BVM", Grades.FOUR);
		studentOriginal.setGradeForSubject("MED3", Grades.THREEMINUS);
		studentOriginal.setGradeForSubject("KIM", Grades.FOUR);
		studentOriginal.setGradeForSubject("NE", Grades.PASSED);
		studentOriginal.setGradeForSubject("PLB", Grades.TWO);
		studentOriginal.setGradeForSubject("MST", Grades.THREE);
		studentOriginal.setGradeForSubject("RT", Grades.NOTPASSED);
		studentOriginal.setGradeForSubject("ML", Grades.FOUR);
		studentOriginal.setValuesForSubject("WF1", "Java", Grades.ONE);
		studentOriginal.setNameForSubject("WF2", "Python");

	}

	@Test
	public void testClone() {
		StudentData.getData().setStudent(studentOriginal);
		var studentCopy = StudentData.getData().getStudent();
		assertFalse(studentOriginal == studentCopy);

		studentCopy = StudentData.getData().getStudent();
		studentCopy.setGradeForSubject("HF", Grades.ONE);
		assertNotEquals(studentOriginal, studentCopy);

		studentCopy = StudentData.getData().getStudent();
		studentCopy.setGradeForSubject("HF", Grades.NOTPASSED);
		assertEquals(studentOriginal, studentCopy);
		
		studentCopy = StudentData.getData().getStudent();
		studentCopy.setNameForSubject("WF3", "Stuttgarter Hofbräu");
		assertNotEquals(studentOriginal, studentCopy);
		
		studentCopy = StudentData.getData().getStudent();
		studentCopy.getSubjectGradeMap().put("WF4", Grades.ONEMINUS);
		assertNotEquals(studentOriginal, studentCopy);

		studentCopy = StudentData.getData().getStudent();
		studentCopy.getSubjectNameMap().put("WF5", "Ahornsirup");
		assertNotEquals(studentOriginal, studentCopy);
		
		studentCopy = StudentData.getData().getStudent();
		studentCopy.setGradeForSubject("HF", Grades.ONE);
		studentCopy.setNameForSubject("WF3", "Stuttgarter Hofbräu");
		StudentData.getData().setStudent(studentCopy);
		var studentNew = StudentData.getData().getStudent();
		assertNotEquals(studentOriginal, studentNew);
		assertEquals(studentCopy, studentNew);
	}

}
