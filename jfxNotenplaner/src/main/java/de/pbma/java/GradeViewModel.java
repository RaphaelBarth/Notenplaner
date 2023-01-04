package de.pbma.java;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.DataFormatException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class GradeViewModel {
	private StringProperty textFieldText = new SimpleStringProperty();
	private BooleanProperty filterDisabled = new SimpleBooleanProperty();
	private final ObservableList<ModuleEntry> oList = FXCollections.observableArrayList();
	private FilteredList<ModuleEntry> filteredData;
	private SortedList<ModuleEntry> sortedData;
	private final StringProperty selectedFilterCategory = new SimpleStringProperty();
	private AtomicBoolean updateUI;

	public GradeViewModel() {
		updateUI = new AtomicBoolean(true);
		filterDisabled.bind(Bindings.size(oList).isEqualTo(0)); // you can not filter, if no subjects
		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			if (updateUI.get()) {
				updateView();
			}
			updateUI.set(true);
		});
		StudentData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			if (updateUI.get()) {
				updateView();
			}
			updateUI.set(true);
		});
		updateView();

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		filteredData = new FilteredList<>(oList, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		textFieldText.addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(moduleEntry -> {
				// If filter text is empty, display all
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				return filterGradeList(moduleEntry, newValue);
			});
		});

		selectedFilterCategory.addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(moduleEntry -> {
				// If filter text is empty, display all
				if (filterProperty().isEmpty().getValue()) {
					return true;
				}

				return filterGradeList(moduleEntry, getFilterText());
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		sortedData = new SortedList<>(filteredData);
	}

	public void updateView() {
		var student = StudentData.getData().getStudent();
		var curriculum = CurriculumData.getData().getCurriculum();
		if (curriculum == null) {
			return;
		}
		oList.clear();
		if (student == null) {
			addSubjectsToTable(curriculum);
		} else {
			addSubjectsAndGradesToTable(curriculum, student);
		}
	}

	private void addSubjectsToTable(Curriculum curriculum) {
		for (var curriculumSubject : curriculum.getAllSubjects()) {
			var entryData = new ModuleEntryData(curriculumSubject);
			oList.add(new ModuleEntry(entryData));
		}
	}

	private void addSubjectsAndGradesToTable(Curriculum curriculum, Student student) {
		for (var curriculumSubject : curriculum.getAllSubjects()) {
			var grade = student.getGradeForSubject(curriculumSubject.getShort());
			var specificName = student.getNameForSubject(curriculumSubject.getShort());
			var subjectName = specificName == null ? curriculumSubject.getName() : specificName;
			var entryData = new ModuleEntryData(curriculumSubject, subjectName, grade);
			oList.add(new ModuleEntry(entryData));
		}
	}

	public StringProperty filterProperty() {
		return textFieldText;
	}

	public void setFilterText(String textFieldText) {
		this.textFieldText.setValue(textFieldText);
	}

	public String getFilterText() {
		return this.textFieldText.getValue();
	}

	public BooleanProperty filterDisabledProperty() {
		return filterDisabled;
	}

	public ObservableList<ModuleEntry> getOListProperty() {
		return oList;
	}

	public SortedList<ModuleEntry> getSortedListProperty() {
		return sortedData;
	}

	public StringProperty getSelectedFilterCategoryProperty() {
		return selectedFilterCategory;
	}

	public String getSelectedFilterCategory() {
		return selectedFilterCategory.getValue();
	}

	public void clear() {
		oList.clear();
	}

	public boolean filterGradeList(ModuleEntry moduleEntry, String filterText) {
		String lowerCaseFilter = filterText.toLowerCase();

		switch (selectedFilterCategory.getValue()) {
		case "überall":
			if (moduleEntry.getShortName().toLowerCase().contains(lowerCaseFilter)) {
				return true; // Filter matches first name.
			} else if (moduleEntry.getSubjectName().toLowerCase().contains(lowerCaseFilter)) {
				return true; // Filter matches last name.
			} else if (moduleEntry.getFocus().toLowerCase().contains(lowerCaseFilter)) {
				return true; // Filter matches last name.
			}
			return false; // Does not match.
		case "Kürzel":
			return moduleEntry.getShortName().toLowerCase().contains(lowerCaseFilter);
		case "Name":
			return moduleEntry.getSubjectName().toLowerCase().contains(lowerCaseFilter);
		case "Bereich":
			return moduleEntry.getFocus().toLowerCase().contains(lowerCaseFilter);
		default:
			return false;
		}

	}

	public void setGrade(String subjectShort, String subjectName, Grades newGrade) {
		System.out.format("neuer Note: %s\n", newGrade);
		var student = StudentData.getData().getStudent();
		student.setGradeForSubject(subjectShort, newGrade);
		updateUI.set(false);
		StudentData.getData().setStudent(student);
	}

	public void setSubjectName(String subjectShort, String subjectName) {
		System.out.format("neuer Name: %s\n", subjectName);
		var student = StudentData.getData().getStudent();
		student.setNameForSubject(subjectShort, subjectName);
		updateUI.set(false);
		StudentData.getData().setStudent(student);

	}

}
