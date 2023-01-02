package de.pbma.java;

import java.util.Objects;

public class Subject {
	private String subjectShort;
	private String name;
	private String focus;
	private boolean gradeAsEvaluation;

	public Subject(String subjectShort, String subject, String focus, boolean gradeAsEvaluation) {
		name = subject;
		this.subjectShort = subjectShort;
		this.focus = focus;
		this.gradeAsEvaluation = gradeAsEvaluation;
	}

	public Subject(String subjectShort, String subject, String focus) {
		this(subjectShort, subject, focus, true);
	}



	@Override
	public String toString() {
		return "Subject [subjectShort=" + subjectShort + ", name=" + name + ", focus=" + focus + ", gradeAsEvaluation="
				+ gradeAsEvaluation + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(focus, name, subjectShort);
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
		return Objects.equals(focus, other.focus) && Objects.equals(name, other.name)
				&& Objects.equals(subjectShort, other.subjectShort);
	}

	public String getFocus() {
		return focus;
	}

	public boolean isWahlfach() {
		return subjectShort.startsWith("WF");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShort() {
		return subjectShort;
	}

	public boolean hasGradeAsEvaluation() {
		return gradeAsEvaluation;
	}

}
