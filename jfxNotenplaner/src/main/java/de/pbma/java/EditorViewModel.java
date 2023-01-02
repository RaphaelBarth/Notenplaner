package de.pbma.java;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorViewModel {

	private final StringProperty curriculumNameProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameShortProperty = new SimpleStringProperty();
	private final Property<String> totalCreditsProperty = new SimpleStringProperty();

	private String  errorMessage;

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

	public Property<String> getTotalCreditsProperty() {
		return totalCreditsProperty;
	}

	public String getTotalCredits() {
		return totalCreditsProperty.getValue();
	}

	public void setTotalCredits(String totalCredits) {
		totalCreditsProperty.setValue(totalCredits);
	}


	public String getErrorMessage() {
		return errorMessage;
	}

}
