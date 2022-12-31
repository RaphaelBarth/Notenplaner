package de.pbma.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Student implements Cloneable {

	private String name;
	private int matriculationNumber;
	private String courseOfStudies;
	private Map<String, Grades> subjectsGradeMap;
	private Map<String, String> subjectsNameMap;

	public Student(String name, int matriculationNumber, String courseOfStudies) {
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courseOfStudies = courseOfStudies;
		subjectsGradeMap = new HashMap<>();
		subjectsNameMap = new HashMap<>();

	}

	public String getName() {
		return name;
	}

	public int getMatriculationNumber() {
		return matriculationNumber;
	}

	public String getCourseOfStudies() {
		return courseOfStudies;
	}

	public Map<String, Grades> getSubjectGradeMap() {
		return subjectsGradeMap;
	}

	public Map<String, String> getSubjectNameMap() {
		return subjectsNameMap;
	}

	public Set<String> getAllSubjectKeys() {
		Set<String> keySet = new HashSet<>();
		keySet.addAll(subjectsGradeMap.keySet());
		keySet.addAll(subjectsNameMap.keySet());
		return keySet;
	}

	public boolean hasGradeForSubject(String subjectShort) {
		return subjectsGradeMap.containsKey(subjectShort);
	}

	public boolean hasNameForSubject(String subjectShort) {
		return subjectsNameMap.containsKey(subjectShort);
	}

	public Grades getGradeForSubject(String subjectShort) {
		return subjectsGradeMap.getOrDefault(subjectShort, Grades.NOTPASSED);
	}

	public String getNameForSubject(String subjectShort) {
		return subjectsNameMap.getOrDefault(subjectShort, null);
	}

	public void setGradeForSubject(String subjectShort, Grades grade) {
		if (grade == Grades.NOTPASSED) {
			removeGradeForSubject(subjectShort);
		} else {
			subjectsGradeMap.put(subjectShort, grade);
		}
	}

	public void setNameForSubject(String subjectShort, String subjectName) {
		subjectsNameMap.put(subjectShort, subjectName);
	}

	@Deprecated
	public void setValuesForSubject(String subjectShort, String subjectName, Grades grades) {
		setGradeForSubject(subjectShort, grades);
		setNameForSubject(subjectShort, subjectName);
	}

	public void removeGradeForSubject(String subjectShort) {
		subjectsGradeMap.remove(courseOfStudies);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", matriculationNumber=" + matriculationNumber + ", courseOfStudies="
				+ courseOfStudies + ", subjectsGradeMap=" + subjectsGradeMap + ", subjectsNameMap=" + subjectsNameMap
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseOfStudies, matriculationNumber, name, subjectsGradeMap, subjectsNameMap);
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
				&& Objects.equals(subjectsGradeMap, other.subjectsGradeMap)
				&& Objects.equals(subjectsNameMap, other.subjectsNameMap);
	}

	@Override
	public Object clone() {
		Student newStudent = null;
		newStudent = new Student(this.name, this.matriculationNumber, this.courseOfStudies);
		for (var ele : this.subjectsGradeMap.entrySet()) {
			newStudent.setGradeForSubject(ele.getKey(), ele.getValue());
		}
		for (var ele : this.subjectsNameMap.entrySet()) {
			newStudent.setNameForSubject(ele.getKey(), ele.getValue());
		}
		return newStudent;

	}
}
