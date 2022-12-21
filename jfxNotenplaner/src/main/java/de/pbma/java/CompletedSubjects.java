package de.pbma.java;

import java.util.Objects;

public class CompletedSubjects {
	private String subjectShort;
	private String subject;
	private Grades grade;

	public CompletedSubjects() {
		this("","",Grades.FIVE);
	}
	
	public CompletedSubjects(String subjectShort, String subject, Grades grade) {
		super();
		this.subjectShort = subjectShort;
		this.subject = subject;
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grade, subject, subjectShort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompletedSubjects other = (CompletedSubjects) obj;
		return grade == other.grade && Objects.equals(subject, other.subject)
				&& Objects.equals(subjectShort, other.subjectShort);
	}

	@Override
	public String toString() {
		return "CompletedSubjects [subjectShort=" + subjectShort + ", subject=" + subject + ", grade=" + grade + "]";
	}

	public String getSubjectShort() {
		return subjectShort;
	}

	public void setSubjectShort(String subjectShort) {
		this.subjectShort = subjectShort;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Grades getGrade() {
		return grade;
	}

	public void setGrade(Grades grade) {
		this.grade = grade;
	}

}
