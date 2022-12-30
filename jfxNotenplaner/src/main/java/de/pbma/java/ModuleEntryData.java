package de.pbma.java;

import java.util.Objects;

public class ModuleEntryData {
	private Boolean gradeEvaluations;
	private Boolean isWahlfach;
	private String krz;
	private String name;
	private String bereich;
	private int sem;
	private double cps;
	private String note;

	public ModuleEntryData(CurriculumSubject curriculumSubject, String name, String note) {
		this.name = name;
		this.note = note;
		this.krz = curriculumSubject.getShort();
		this.bereich = curriculumSubject.getFocus();
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

	public String getKrz() {
		return krz;
	}

	public void setKrz(String krz) {
		this.krz = krz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name gesetzt");
		this.name = name;
	}

	public String getBereich() {
		return bereich;
	}

	public int getSem() {
		return sem;
	}

	public double getCps() {
		return cps;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		System.out.println("note gesetzt");
		this.note = note;
	}

	public Boolean getIsWahlfach() {
		return isWahlfach;
	}

	public boolean isGreadEvaluation() {
		return gradeEvaluations;
	}

	@Override
	public String toString() {
		return "ModuleEntryData [gradeEvaluations=" + gradeEvaluations + ", isWahlfach=" + isWahlfach + ", krz=" + krz
				+ ", name=" + name + ", bereich=" + bereich + ", sem=" + sem + ", cps=" + cps + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bereich, cps, gradeEvaluations, isWahlfach, krz, name, note, sem);
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
		return Objects.equals(bereich, other.bereich)
				&& Double.doubleToLongBits(cps) == Double.doubleToLongBits(other.cps)
				&& Objects.equals(gradeEvaluations, other.gradeEvaluations)
				&& Objects.equals(isWahlfach, other.isWahlfach) && Objects.equals(krz, other.krz)
				&& Objects.equals(name, other.name) && Objects.equals(note, other.note) && sem == other.sem;
	}

	
}
