package de.pbma.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {

	private String name;
	private int matriculationNumber;
	private String courseOfStudies;
	private Map<CurriculumSubject, Grades> subjectsGradeMap;

	public Student(String name, int matriculationNumber, String courseOfStudies, List<CurriculumSubject> subjects) {
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courseOfStudies = courseOfStudies;
		subjectsGradeMap = new HashMap<>();
		if (subjects != null) {
			for (var s : subjects) {
				setGradeForSubject(s, Grades.NOTPASSED);
			}
		}
	}

	public Student(String name, int matriculationNumber, String courseOfStudies) {
		this(name, matriculationNumber, courseOfStudies, null);
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
	
	public Map<CurriculumSubject, Grades> getSubjectGradeMap() {
		return subjectsGradeMap;
	}
	
	public boolean hasGradeForSubject(CurriculumSubject subject) {
		return subjectsGradeMap.containsKey(subject);
	}
	
	public Grades getGradeForSubject(CurriculumSubject subject) {
		if(subjectsGradeMap.containsKey(subject)) {
			return subjectsGradeMap.get(subject);
		}
		return Grades.NOTPASSED;
	}

	public void setGradeForSubject(CurriculumSubject subject, Grades grade) {
		subjectsGradeMap.put(subject, grade);
	}

	public void removeGradeForSubject(CurriculumSubject subject) {
		subjectsGradeMap.remove(subject);
	}

}
