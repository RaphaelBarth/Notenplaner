package de.pbma.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curriculum {

	private Abschluss abschluss;
	private String name;
	private String nameShort;
	private double credits;
	private List<CurriculumSubject> subjects;

	public Curriculum(String name, String nameShort, double credits, List<CurriculumSubject> subjects) {
		this.name = name;
		this.nameShort = nameShort;
		this.credits = credits;
		this.subjects = subjects;
		if (nameShort.endsWith("B")) {
			abschluss = Abschluss.BACHELOR;
		} else if (nameShort.endsWith("B")) {
			abschluss = Abschluss.MASTER;
		} else {
			// throw new Exception("Abschluss konnte nicht bestimmt werden");
			return;
		}
	}

	public Curriculum(String name, String nameShort, double credits) {
		this(name, nameShort, credits, new ArrayList<CurriculumSubject>());
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

	public List<CurriculumSubject> getSubjects() {
		return subjects;
	}

	public boolean addSubject(Subject subject, int semester, double credits) {
		var newSubject = new CurriculumSubject(subject, semester, credits);
		return addSubject(newSubject);
	}

	public boolean addSubject(CurriculumSubject newSubject) {
		if (subjects.contains(newSubject)) {
			return false;
		}
		return subjects.add(newSubject);
	}

	public void removeSubject(CurriculumSubject subject) {
		subjects.remove(subject);
	}

	public double getCreditsForSemester(int semester) {
		double cp = 0;
		for (var subject : subjects) {
			// TODO nur eins der beiden WPF nehmen
			if (subject.getSemester() == semester) {
				cp += subject.getCreditPoints();
			}
		}
		return cp;
	}

	public String getAbschluss() {
		return abschluss.toString();
	}

}
