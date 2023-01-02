package de.pbma.java;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class EditorViewModel {

	private final StringProperty curriculumNameProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameShortProperty = new SimpleStringProperty();
	private final DoubleProperty totalCreditsProperty = new SimpleDoubleProperty();
	private final ObservableList<CurriculumSubjectEntry> subjectsList = FXCollections.observableArrayList();

	private String errorMessage;

	public EditorViewModel() {
		System.out.println("sadvasd");
		addSubject(new CurriculumSubject("EIP", "Einführung in die Programmierung", "Software", true, 1, 7));
		addSubject(new CurriculumSubject("ET1", "Elektrotechnik 1", "Grundlagen", true, 1, 5));

	}

	public boolean saveCurriculum() {
		errorMessage = "darum";
		return false;
	}

	public StringProperty getCurriculumNameProperty() {
		return curriculumNameProperty;
	}

	public String getCurriculumName() {
		return curriculumNameProperty.getValue();
	}

	public void setCurriculumName(String curriculumName) {
		curriculumNameProperty.setValue(curriculumName);
	}

	public StringProperty getCurriculumNameShortProperty() {
		return curriculumNameShortProperty;
	}

	public String getCurriculumNameShort() {
		return curriculumNameShortProperty.getValue();
	}

	public void setCurriculumNameShort(String curriculumNameShort) {
		curriculumNameProperty.setValue(curriculumNameShort);
	}

	public DoubleProperty getTotalCreditsProperty() {
		return totalCreditsProperty;
	}

	public Double getTotalCredits() {
		return totalCreditsProperty.getValue();
	}

	public void setTotalCredits(Double totalCredits) {
		totalCreditsProperty.setValue(totalCredits);
	}

	public ObservableList<CurriculumSubjectEntry> getSubjectsList() {
		return subjectsList;
	}

	@Deprecated
	public boolean addSubject(CurriculumSubject curriculumSubject) {
		return subjectsList.add(new CurriculumSubjectEntry(curriculumSubject));
	}
	public boolean addSubject(CurriculumSubjectEntry curriculumSubject) {
		return subjectsList.add(curriculumSubject);
	}

	public boolean remouveSubject(CurriculumSubject curriculumSubject) {
		return subjectsList.remove(curriculumSubject);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
