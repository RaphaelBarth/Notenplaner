package de.pbma.java;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ModuleEntry implements Serializable {
	private static final long serialVersionUID = -8674008613457029157L;
	public static final String KRZ = "shortName";
	public static final String NAME = "subjectName";
	public static final String BEREICH = "focus";
	public static final String SEM = "sem";
	public static final String CPS = "cps";
	public static final String NOTE = "grade";

	private final static List<Grades> normalEvaluation = FXCollections
			.observableArrayList(Arrays.asList(Grades.NOTPASSED, Grades.PASSED));
	private final static List<Grades> gradeEvaluation = FXCollections.observableArrayList(
			Stream.of(Grades.values()).filter(g -> g != Grades.PASSED).sorted().collect(Collectors.toList()));

	private ModuleEntryData entryData;
	private ObjectProperty<Grades> gradeProperty;
	private StringProperty nameProperty;

	public ModuleEntry(ModuleEntryData entryData) {
		this.entryData = entryData;
		this.gradeProperty = new SimpleObjectProperty<Grades>();
		this.nameProperty = new SimpleStringProperty();
		this.nameProperty.setValue(entryData.getSubjectName());
		this.gradeProperty.setValue(entryData.getGrade());

	}

	public String getShortName() {
		return entryData.getShortName();
	}

	public void setShortName(String shortName) {
		this.entryData.setShortName(shortName);
	}

	public String getSubjectName() {
		return this.nameProperty.getValue();
	}

	public void setSubjectName(String name) {
		this.nameProperty.setValue(name);
		this.entryData.setSubjectName(name);
	}

	public String getFocus() {
		return this.entryData.getFocus();
	}

	public int getSem() {
		return this.entryData.getSem();
	}

	public double getCps() {
		return this.entryData.getCps();
	}

	public Grades getGrade() {
		return this.gradeProperty.getValue();
	}

	public void setGrade(Grades grade) {
		this.gradeProperty.setValue(grade);
		this.entryData.setGrade(grade);
	}

	public List<Grades> getPossibleEvaluations() {
		return (this.entryData.hasGradeAsEvaluation()) ? gradeEvaluation : normalEvaluation;
	}

	public Boolean nameIsEditable() {
		return this.entryData.getIsWahlfach();
	}

	@Override
	public String toString() {
		return "ModuleEntry [entryData=" + entryData + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(entryData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleEntry other = (ModuleEntry) obj;
		return Objects.equals(entryData, other.entryData);
	}

	public ObjectProperty<Grades> getGradeProperty() {
		return gradeProperty;
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

}
