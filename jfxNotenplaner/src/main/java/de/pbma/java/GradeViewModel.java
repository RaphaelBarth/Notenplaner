package de.pbma.java;

import java.util.zip.DataFormatException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class GradeViewModel {
	private StringProperty textFieldText = new SimpleStringProperty();
	private BooleanProperty buttonDisabled = new SimpleBooleanProperty();
	private BooleanProperty curriculumListEmpty = new SimpleBooleanProperty();
	private final ObservableList<ModuleEntry> oList = FXCollections.observableArrayList();
	private final ObjectProperty<ModuleEntry> selectedItem = new SimpleObjectProperty<>();

	public GradeViewModel() {
		curriculumListEmpty.bind(Bindings.size(oList).isEqualTo(0));
		buttonDisabled.bind(textFieldText.isEmpty()); // you can not filter, if nothing is entered
		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			updateView();
		});
		StudentData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			updateView();
		});
		updateView();
		oList.addListener(new ListChangeListener<ModuleEntry>() {
			@Override
			public void onChanged(Change<? extends ModuleEntry> c) {

				System.out.println(c);

			}
		});
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
			var grade = student.getGradeForSubject(curriculumSubject.getShort()).toString();
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

	public BooleanProperty buttonDisabledProperty() {
		return buttonDisabled;
	}

	public BooleanProperty curriculumListEmpty() {
		return curriculumListEmpty;
	}

	public ObservableList<ModuleEntry> getOListProperty() {
		return oList;
	}

	public ObjectProperty<ModuleEntry> getSelectedItemProperty() {
		return selectedItem;
	}

	public void setSelectedItem(ModuleEntry me) {
		System.out.println(me);
		selectedItem.setValue(me);
	}

	public ModuleEntry getSelectedItem() {
		return selectedItem.getValue();
	}

	public void clear() {
		oList.clear();
	}

	public void filterList() {
		System.out.println("Filtere nach " + getFilterText());
	}

	public void setGrade(String subjectShort, String subjectName, String newGrade) {
		var student = StudentData.getData().getStudent();
		Grades grade;
		try {
			grade = Grades.fromString(newGrade);
			System.out.format("neuer Note: %s\n", newGrade);
			student.setGradeForSubject(subjectShort, grade);
			StudentData.getData().setStudent(student);
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSubjectName(String subjectShort, String subjectName) {
		var student = StudentData.getData().getStudent();
		student.setNameForSubject(subjectShort, subjectName);
		StudentData.getData().setStudent(student);
		System.out.format("neuer Name: %s\n", subjectName);

	}

}
