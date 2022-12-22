package de.pbma.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curriculum {
	private String name;
	private String nameShort;
	private double credits;
	private List<Subject> subjects;

	public Curriculum() {
		this("", "", 0, new ArrayList<>());
	}

	public Curriculum(String name, String nameShort, double credits, List<Subject> subjects) {
		this.name = name;
		this.nameShort = nameShort;
		this.credits = credits;
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "courseOfStudies [name=" + name + ", nameShort=" + nameShort + ", credits=" + credits + ", subjects="
				+ subjects + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(credits, name, nameShort, subjects);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curriculum other = (Curriculum) obj;
		return credits == other.credits && Objects.equals(name, other.name)
				&& Objects.equals(nameShort, other.nameShort) && Objects.equals(subjects, other.subjects);
	}

	public String getName() {
		return name;
	}

	public String getNameShort() {
		return nameShort;
	}

	public double getCredits() {
		return credits;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

}
