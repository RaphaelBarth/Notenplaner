package de.pbma.java;

import java.util.Objects;

public class CurriculumSubject {
	Subject subject;
	int semester;
	double credits;

	public CurriculumSubject(Subject s, int semester, double credits) {
		subject = s;
		this.semester = semester;
		this.credits = credits;
	}

	public double getCreditPoints() {
		return credits;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getFocus() {
		return subject.getFocus();
	}

	public String getSubjectShort() {
		return subject.getShort();
	}

	public int getSemester() {
		return semester;
	}
	
	public String getName() {
		return subject.getName();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(credits, semester, subject.getShort());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurriculumSubject other = (CurriculumSubject) obj;
		return Double.doubleToLongBits(credits) == Double.doubleToLongBits(other.credits) && semester == other.semester
				&& Objects.equals(subject.getShort(), other.subject.getShort());
	}


}
