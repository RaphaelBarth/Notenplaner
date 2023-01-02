package de.pbma.java;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CurriculumSubjectEntry implements Serializable {
	private static final long serialVersionUID = -6368196586219058483L;

	public static final String SHORT = "nameShort";

	public static final String NAME = "name";

	public static final String FOCUS = "focus";

	public static final String SEMESTER = "semester";

	public static final String CREDITS = "credits";

	public static final String HASGRADE = "hasGrade";

	private final StringProperty nameProperty = new SimpleStringProperty();
	private final StringProperty nameShortProperty = new SimpleStringProperty();
	private final StringProperty focusProperty = new SimpleStringProperty();
	private final IntegerProperty semesterProperty = new SimpleIntegerProperty();
	private final DoubleProperty creditsProperty = new SimpleDoubleProperty();
	private final BooleanProperty hasGradeProperty = new SimpleBooleanProperty();

	public CurriculumSubjectEntry(CurriculumSubject curriculumSubject) {
		this.nameProperty.setValue(curriculumSubject.getName());
		this.nameShortProperty.setValue(curriculumSubject.getShort());
		this.focusProperty.setValue(curriculumSubject.getFocus());
		this.semesterProperty.setValue(curriculumSubject.getSemester());
		this.creditsProperty.setValue(curriculumSubject.getCreditPoints());
		this.hasGradeProperty.set(curriculumSubject.hasGradeAsEvaluation());
	}

	public CurriculumSubject getCurriculumSubject() {
		var name = this.nameProperty.getValue();
		var nameShort = this.nameShortProperty.getValue();
		var focus = this.focusProperty.getValue();
		var semester = this.semesterProperty.getValue();
		var credits = this.creditsProperty.getValue();
		var hasGrade = this.hasGradeProperty.getValue();
		return new CurriculumSubject(nameShort, name, focus, hasGrade, semester, credits);
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public String getName() {
		return nameProperty.getValue();
	}

	public void setName(String name) {
		System.out.println("setNAme");
		nameProperty.setValue(name);
	}

	public StringProperty getNameShortProperty() {
		return nameShortProperty;
	}

	public String getNameShort() {
		return nameShortProperty.getValue();
	}

	public void setNameShort(String nameShort) {
		nameShortProperty.setValue(nameShort);
	}

	public StringProperty getFocusProperty() {
		return focusProperty;
	}

	public String getFocus() {
		return focusProperty.getValue();
	}

	public void setFocus(String focus) {
		focusProperty.setValue(focus);
	}

	public IntegerProperty getSemesterProperty() {
		return semesterProperty;
	}

	public Integer getSemester() {
		return semesterProperty.getValue();
	}

	public void setSemester(Integer semester) {
		semesterProperty.setValue(semester);
	}

	public DoubleProperty getCreditsProperty() {
		return creditsProperty;
	}

	public Double getCredits() {
		return creditsProperty.getValue();
	}

	public void setCredits(Double credits) {
		creditsProperty.setValue(credits);
	}

	public BooleanProperty getHasGradeProperty() {
		return hasGradeProperty;
	}

	public Boolean getHasGrade() {
		return hasGradeProperty.getValue();
	}

	public void setHasGrade(Boolean hasGrade) {
		hasGradeProperty.setValue(hasGrade);
	}

}
