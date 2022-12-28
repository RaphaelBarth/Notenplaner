package de.pbma.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.javatuples.Pair;

public class Student {

	private String name;
	private int matriculationNumber;
	private String courseOfStudies;
	private Map<String, Pair<String, Grades>> subjectsGradeMap;

	public Student(String name, int matriculationNumber, String courseOfStudies) {
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courseOfStudies = courseOfStudies;
		subjectsGradeMap = new HashMap<>();
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", matriculationNumber=" + matriculationNumber + ", courseOfStudies="
				+ courseOfStudies + ", subjectsGradeMap=" + subjectsGradeMap + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatriculationNumber() {
		return matriculationNumber;
	}

	public void setMatriculationNumber(int matriculationNumber) {
		this.matriculationNumber = matriculationNumber;
	}

	public String getCourseOfStudies() {
		return courseOfStudies;
	}

	public void setCourseOfStudies(String courseOfStudies) {
		this.courseOfStudies = courseOfStudies;
	}

	public Map<String, Pair<String, Grades>> getSubjectGradeMap() {
		return subjectsGradeMap;
	}

	public boolean hasValueForSubject(String subjectShort) {
		return subjectsGradeMap.containsKey(subjectShort);
	}

	public Grades getGradeForSubject(String subjectShort) {
		if (!hasValueForSubject(subjectShort)) {
			return Grades.NOTPASSED;
		}
		return subjectsGradeMap.get(subjectShort).getValue1();
	}

	public String getNameForSubject(String subjectShort) {
		if (!hasValueForSubject(subjectShort)) {
			return null;
		}
		return subjectsGradeMap.get(subjectShort).getValue0();
	}

	public void setGradeForSubject(String subjectShort, String subjectName, Grades grade) {
		subjectsGradeMap.put(subjectShort, new Pair<String, Grades>(subjectName, grade));
	}

	public void removeGradeForSubject(String subjectShort) {
		subjectsGradeMap.remove(courseOfStudies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseOfStudies, matriculationNumber, name, subjectsGradeMap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courseOfStudies, other.courseOfStudies)
				&& matriculationNumber == other.matriculationNumber && Objects.equals(name, other.name)
				&& Objects.equals(subjectsGradeMap, other.subjectsGradeMap);
	}

}
