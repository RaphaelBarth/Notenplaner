package de.pbma.java;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewFileController {
	
	@FXML
	private TextField tfStudentName;
	@FXML
	private TextField tfMatriculationNumber;
	@FXML
	private ComboBox<String> cbCourseOfStudies;
	@FXML
	private Button btCancel;
	@FXML
	private Button btOkay;
	
	private FileLogic fileLogic;
	 
	public NewFileController() {
		// TODO combobox mit Studiengängen füllen
		fileLogic = new FileLogic();
		var coursesOfStudies = fileLogic.getCurriculumFiles().keySet();
		var oList = FXCollections.observableArrayList(coursesOfStudies);
		cbCourseOfStudies.setItems(oList);
	}
	
	@FXML
	public void onCancel() {
		System.out.println("Abgebrochen");
		//TODO Fenster schließen
	}
	
	@FXML
	public void onOkay() {
		System.out.println("Speichern");
		System.out.println(cbCourseOfStudies.getSelectionModel().getSelectedItem());
	}
	

}
