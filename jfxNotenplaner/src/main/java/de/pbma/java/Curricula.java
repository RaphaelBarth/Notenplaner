package de.pbma.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curricula {
	private String name;
	private String nameShort;
	private int credits;
	
	private List<Subject> subjects;
	
	public Curricula() {
		this("", "", 0);
	}
	
	public Curricula(String name, String nameShort, int credits) {
		super();
		this.name = name;
		this.nameShort = nameShort;
		this.credits = credits;
		this.subjects = new ArrayList<>();
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
		Curricula other = (Curricula) obj;
		return credits == other.credits && Objects.equals(name, other.name)
				&& Objects.equals(nameShort, other.nameShort) && Objects.equals(subjects, other.subjects);
	}

	public String getName() {
		return name;
	}

	public String getNameShort() {
		return nameShort;
	}

	public int getCredits() {
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

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	
	
}
