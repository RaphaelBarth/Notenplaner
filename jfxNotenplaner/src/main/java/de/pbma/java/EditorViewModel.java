package de.pbma.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class EditorViewModel {

	private final StringProperty errorProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameShortProperty = new SimpleStringProperty();
	private final StringProperty totalCreditsProperty = new SimpleStringProperty();
	private final ObservableList<CurriculumSubjectEntry> subjectsList = FXCollections.observableArrayList();

	public EditorViewModel() {
		addSubject(new CurriculumSubject("EIP", "Einführung in die Programmierung", "Software", true, 1, 7));
		addSubject(new CurriculumSubject("ET1", "Elektrotechnik 1", "Grundlagen", true, 1, 5));
	}

	public void saveCurriculum() {
		final var totalCredits = Double.parseDouble(getTotalCredits());
		final var curriculumName = getCurriculumName();
		final var curriculumShort = getCurriculumNameShort();

		new Thread(() -> {
			double credits = subjectsList.stream().mapToDouble(s -> s.getCredits()).sum();
			System.out.println(credits);
			System.out.println(totalCredits);
			System.out.println(credits < totalCredits);
			if (credits < totalCredits) {
				errorProperty.set("Summe der Credits kleiner als gesammt Credits");
				return;
			}
			final Curriculum curriculum = new Curriculum(curriculumShort, curriculumName, totalCredits);
			subjectsList.stream().map(s -> s.getCurriculumSubject()).forEach(s -> curriculum.addSubject(s));
			System.out.println("save new curriculum: " + curriculum);

			Platform.runLater(() -> {
				Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save Resource File");
				fileChooser.setInitialFileName(curriculum.getNameShort() + ".csv");
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
				final File file = fileChooser.showSaveDialog(owner);
				new Thread(() -> {
					if (file != null) {
						var fileLogic = new FileLogic();
						fileLogic.setCurriculum(curriculum);
						fileLogic.saveCurriculumFile(file);
					}
				}).start();
			});
		}).start();
		;
		return;
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

	public StringProperty getTotalCreditsProperty() {
		return totalCreditsProperty;
	}

	public String getTotalCredits() {
		return totalCreditsProperty.getValue();
	}

	public void setTotalCredits(String totalCredits) {
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
		System.out.println("new subject: " + curriculumSubject);
		return subjectsList.add(curriculumSubject);
	}

	public void addSubject(String nameShort, String name, String focus, boolean hasGrade, Integer semester,
			double credits) {
		var curiculumSubject = new CurriculumSubject(nameShort, name, focus, hasGrade, semester, credits);
		addSubject(new CurriculumSubjectEntry(curiculumSubject));
	}

	public boolean removeSubject(CurriculumSubjectEntry curriculumSubject) {
		return subjectsList.remove(curriculumSubject);
	}

	public StringProperty getErrorMessage() {
		return errorProperty;
	}

}
