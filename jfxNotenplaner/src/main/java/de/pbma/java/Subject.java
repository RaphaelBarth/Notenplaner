package de.pbma.java;

import java.util.Objects;

public class Subject {
	private String subjectShort;
	private String subject;
	private String focus;
	private int semester;
	private double credits;

	public Subject() {
		this("", "", "", 0, 0);
	}

	public Subject(String subjectShort,String subject, String focus, int semester, double credits) {
		this.subject = subject;
		this.subjectShort = subjectShort;
		this.focus = focus;
		this.semester = semester;
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Subject [subject=" + subject + ", subjectShort=" + subjectShort + ", focus=" + focus + ", semester="
				+ semester + ", credits=" + credits + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(credits, focus, semester, subject, subjectShort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return credits == other.credits && Objects.equals(focus, other.focus) && semester == other.semester
				&& Objects.equals(subject, other.subject) && Objects.equals(subjectShort, other.subjectShort);
	}

	public String getSubject() {
		return subject;
	}

	public String getSubjectShort() {
		return subjectShort;
	}

	public String getFocus() {
		return focus;
	}

	public int getSemester() {
		return semester;
	}

	public double getCredits() {
		return credits;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setSubjectShort(String subjectShort) {
		this.subjectShort = subjectShort;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

}
