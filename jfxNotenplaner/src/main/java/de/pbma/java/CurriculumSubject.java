package de.pbma.java;

import java.util.Objects;

public class CurriculumSubject extends Subject {
	int semester;
	double credits;

	public CurriculumSubject(String subjectShort, String subject, String focus, boolean hasGradeAsEvaluation,
			int semester, double credits) {
		super(subjectShort, subject, focus, hasGradeAsEvaluation);
		this.semester = semester;
		this.credits = credits;
	}

	public CurriculumSubject(Subject s, int semester, double credits) {
		this(s.getShort(), s.getName(), s.getFocus(), s.hasGradeAsEvaluation(), semester, credits);
	}

	public double getCreditPoints() {
		return credits;
	}

	public int getSemester() {
		return semester;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, semester);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurriculumSubject other = (CurriculumSubject) obj;
		if (Double.doubleToLongBits(credits) != Double.doubleToLongBits(other.credits))
			return false;
		if (semester != other.semester)
			return false;
		return true;
	}

}
