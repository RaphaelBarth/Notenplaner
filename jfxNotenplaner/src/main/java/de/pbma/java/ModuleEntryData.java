package de.pbma.java;

import java.util.Objects;

public class ModuleEntryData {
	private Boolean gradeEvaluations;
	private Boolean isWahlfach;
	private String shortName;
	private String subjectName;
	private String focus;
	private int sem;
	private double cps;
	private String grade;

	public ModuleEntryData(CurriculumSubject curriculumSubject, String name, String grade) {
		this.subjectName = name;
		this.grade = grade;
		this.shortName = curriculumSubject.getShort();
		this.focus = curriculumSubject.getFocus();
		this.sem = curriculumSubject.getSemester();
		this.cps = curriculumSubject.getCreditPoints();
		this.isWahlfach = (curriculumSubject.isWahlfach()) ? true : false;
		gradeEvaluations = curriculumSubject.hasGradeAsEvaluation();
	}

	public ModuleEntryData(CurriculumSubject curriculumSubject, String name) {
		this(curriculumSubject, name, Grades.NOTPASSED.toString());
	}

	public ModuleEntryData(CurriculumSubject curriculumSubject) {
		this(curriculumSubject, curriculumSubject.getName(), Grades.NOTPASSED.toString());
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String name) {
		this.subjectName = name;
	}

	public String getFocus() {
		return focus;
	}

	public int getSem() {
		return sem;
	}

	public double getCps() {
		return cps;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Boolean getIsWahlfach() {
		return isWahlfach;
	}

	public boolean hasGradeAsEvaluation() {
		return gradeEvaluations;
	}

	@Override
	public String toString() {
		return "ModuleEntryData [gradeEvaluations=" + gradeEvaluations + ", isWahlfach=" + isWahlfach + ", krz="
				+ shortName + ", name=" + subjectName + ", bereich=" + focus + ", sem=" + sem + ", cps=" + cps
				+ ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(focus, cps, gradeEvaluations, isWahlfach, shortName, subjectName, grade, sem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleEntryData other = (ModuleEntryData) obj;
		return Objects.equals(focus, other.focus) && Double.doubleToLongBits(cps) == Double.doubleToLongBits(other.cps)
				&& Objects.equals(gradeEvaluations, other.gradeEvaluations)
				&& Objects.equals(isWahlfach, other.isWahlfach) && Objects.equals(shortName, other.shortName)
				&& Objects.equals(subjectName, other.subjectName) && Objects.equals(grade, other.grade)
				&& sem == other.sem;
	}

}
