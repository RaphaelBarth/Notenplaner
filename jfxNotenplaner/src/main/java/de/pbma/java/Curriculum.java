package de.pbma.java;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Curriculum implements Cloneable {

	private Abschluss abschluss;
	private String name;
	private String nameShort;
	private double credits;
	private Set<CurriculumSubject> subjects;

	public Curriculum(String nameShort, String name, double credits) {
		this.name = name;
		this.nameShort = nameShort;
		this.credits = credits;
		this.subjects = new HashSet<CurriculumSubject>();
		if (nameShort.endsWith("B")) {
			abschluss = Abschluss.BACHELOR;
		} else if (nameShort.endsWith("M")) {
			abschluss = Abschluss.MASTER;
		} else {
			// throw new Exception("Abschluss konnte nicht bestimmt werden");
			return;
		}
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

	public Set<CurriculumSubject> getAllSubjects() {
		return subjects;
	}

	public CurriculumSubject getSubject(String subjectShort) {
		for (var subject : subjects) {
			if (subject.getShort().equals(subjectShort)) {
				return subject;
			}
		}
		return null;
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

	@Override
	public Object clone() {
		Curriculum newCurriculum = null;
		newCurriculum = new Curriculum(this.nameShort, this.name, this.credits);
		for (var ele : this.subjects) {
			if (ele == null) {
				continue;
			}
			var nameShort = ele.getShort();
			var name = ele.getName();
			var focus = ele.getFocus();
			var eval = ele.hasGradeAsEvaluation();
			var semester = ele.getSemester();
			var credits = ele.getCreditPoints();
			var curriculumSubject = new CurriculumSubject(nameShort, name, focus, eval, semester, credits);
			newCurriculum.addSubject(curriculumSubject);
		}
		return newCurriculum;

	}

}
