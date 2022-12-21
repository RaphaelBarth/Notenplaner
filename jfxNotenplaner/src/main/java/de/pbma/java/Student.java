package de.pbma.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {

	private String name;
	private int matriculationNumber;
	private String courseOfStudies;
	private List<CompletedSubjects> completedSubjects;

	public Student() {
		this("", 0, "", new ArrayList<>());
	}

	public Student(String name, int matriculationNumber, String courseOfStudies,
			List<CompletedSubjects> completedSubjects) {
		super();
		this.name = name;
		this.matriculationNumber = matriculationNumber;
		this.courseOfStudies = courseOfStudies;
		this.completedSubjects = completedSubjects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completedSubjects, courseOfStudies, matriculationNumber, name);
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
		return Objects.equals(completedSubjects, other.completedSubjects)
				&& Objects.equals(courseOfStudies, other.courseOfStudies)
				&& matriculationNumber == other.matriculationNumber && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", matriculationNumber=" + matriculationNumber + ", courseOfStudies="
				+ courseOfStudies + ", completedSubjects=" + completedSubjects + "]";
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

	public List<CompletedSubjects> getCompletedSubjects() {
		return completedSubjects;
	}

	public void addCompletedSubject(CompletedSubjects completedSubjects) {
		this.completedSubjects.add(completedSubjects);
	}

}
