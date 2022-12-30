package de.pbma.java;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ModuleEntry implements Serializable {
	private static final long serialVersionUID = -8674008613457029157L;
	public static final String KRZ = "krz";
	public static final String NAME = "name";
	public static final String BEREICH = "bereich";
	public static final String SEM = "sem";
	public static final String CPS = "cps";
	public static final String NOTE = "note";

	private final static List<String> normalEvaluation = FXCollections
			.observableArrayList(Arrays.asList(Grades.PASSED.toString(), Grades.NOTPASSED.toString()));
	private final static List<String> gradeEvaluation = FXCollections.observableArrayList(Stream.of(Grades.values())
			.filter(g -> g != Grades.PASSED).map(g -> g.toString()).sorted().collect(Collectors.toList()));

	private ModuleEntryData entryData;
	private StringProperty gradeProperty;
	private StringProperty nameProperty;

	public ModuleEntry(ModuleEntryData entryData) {
		this.entryData = entryData;
		this.gradeProperty = new SimpleStringProperty();
		this.nameProperty = new SimpleStringProperty();
		this.nameProperty.setValue(entryData.getName());
		this.gradeProperty.setValue(entryData.getNote());

	}

	public String getKrz() {
		return entryData.getKrz();
	}

	public void setKrz(String krz) {
		this.entryData.setKrz(krz);
	}

	public String getName() {
		return this.nameProperty.getValue();
	}

	public void setName(String name) {
		this.nameProperty.setValue(name);
		this.entryData.setName(name);
	}

	public String getBereich() {
		return this.entryData.getBereich();
	}

	public int getSem() {
		return this.entryData.getSem();
	}

	public double getCps() {
		return this.entryData.getCps();
	}

	public String getNote() {
		return this.gradeProperty.getValue();
	}

	public void setNote(String note) {
		this.gradeProperty.setValue(note);
		this.entryData.setNote(note);
	}

	public List<String> getPossibleEvaluations() {
		return (this.entryData.isGreadEvaluation()) ? gradeEvaluation : normalEvaluation;
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

	public StringProperty getGradeProperty() {
		return gradeProperty;
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

}
