package de.pbma.java;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradeViewModel {
	private final ObservableList<ModuleEntry> oList = FXCollections.observableArrayList();
	private final ObjectProperty<ModuleEntry> selectedItem = new SimpleObjectProperty<>();

	public GradeViewModel() {
		// var student = StudentData.getStudentData().getStudent();
		for (var s : CurriculumData.getData().getCurriculum().getSubjects()) {
			// TODO use: student.getGradeForSubject(s).toString()
			oList.add(new ModuleEntry(s.getSubjectShort(), s.getName(), s.getFocus(), s.getSemester(),
					s.getCreditPoints()));
		}
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

}
