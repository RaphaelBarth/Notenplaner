package de.pbma.java;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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

//	public void setButtonDisabled(boolean buttonDisabled) {
//		this.buttonDisabled.setValue(buttonDisabled);
//	}
//
//	public boolean getButtonDisabled() {
//		return this.buttonDisabled.getValue();
//	}

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
		selectedItem.setValue(me);
	}

	public ModuleEntry getSelectedItem() {
		return selectedItem.getValue();
	}

	public void clear() {
		oList.clear();
	}

	public void addSubjectsToTable() {
		// var student = StudentData.getStudentData().getStudent();
		for (var s : CurriculumData.getData().getCurriculum().getSubjects()) {
			// TODO use: student.getGradeForSubject(s).toString()
			oList.add(new ModuleEntry(s.getSubjectShort(), s.getName(), s.getFocus(), s.getSemester(),
					s.getCreditPoints()));
		}
	}

	public void filterList() {
		System.out.println("Filtere nach " + getFilterText());
	}

}
