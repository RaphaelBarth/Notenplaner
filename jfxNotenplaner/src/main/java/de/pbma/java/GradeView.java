package de.pbma.java;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class GradeView implements Initializable {
	@FXML
	private TableView<ModuleEntry> tvGrades;
	@FXML
	private Button btFilter;
	@FXML
	private TextField tfFilter;

	GradeViewModel gradeViewModel = new GradeViewModel();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfFilter.textProperty().bindBidirectional(gradeViewModel.filterProperty());

		tfFilter.disableProperty().bind(gradeViewModel.curriculumListEmpty());
		btFilter.disableProperty().bind(gradeViewModel.buttonDisabledProperty());

		tvGrades.setItems(gradeViewModel.getOListProperty());
		gradeViewModel.getSelectedItemProperty().bind(tvGrades.getSelectionModel().selectedItemProperty());
		tvGrades.setEditable(true);
		tvGrades.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<ModuleEntry, String> tcKrz = new TableColumn<>("Kürzel");
		tcKrz.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.KRZ));
		TableColumn<ModuleEntry, String> tcName = new TableColumn<>("Name");
		tcName.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NAME));
		tcName.setCellFactory(TextFieldTableCell.forTableColumn());
		TableColumn<ModuleEntry, String> tcBereich = new TableColumn<>("Bereich");
		tcBereich.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.BEREICH));
		TableColumn<ModuleEntry, Integer> tcSem = new TableColumn<>("Fachsem");
		tcSem.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.SEM));
		TableColumn<ModuleEntry, Integer> tcCps = new TableColumn<>("CPs");
		tcCps.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.CPS));
		TableColumn<ModuleEntry, String> tcNote = new TableColumn<>("Note");
		tcNote.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NOTE));
		ObservableList<String> gradeOList = FXCollections.observableArrayList();
		Arrays.asList(Grades.values()).forEach(v -> gradeOList.add(v.toString()));
		tcNote.setCellFactory(ComboBoxTableCell.forTableColumn(gradeOList.sorted()));
		// TODO auf Änderungen in der ComboBox reagiern
		// TODO ComboBox Optionen abhängig davon ob Fach Noten bekommen kann

		tvGrades.getColumns().addAll(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote);

		// Default: sortieren nach Fachsemester
		tvGrades.getSortOrder().addAll(tcSem, tcKrz, tcName);
	}

	@FXML
	public void onFilter() {
		gradeViewModel.filterList();
	}
}
