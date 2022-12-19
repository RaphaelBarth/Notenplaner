package de.pbma.java;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class GradeViewController implements Initializable {
	@FXML
	private TableView<ModuleEntry> tvGrades;
	@FXML
	private Button btFilter;

	public GradeViewController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: echte Daten laden
		ObservableList<ModuleEntry> olist = FXCollections.observableArrayList(
				new ModuleEntry("BA", "Bachelorarbeit", "Sonstiges", 7, 5),
				new ModuleEntry("DB", "Datenbanken", "Software", 3, 5));
		tvGrades.setItems(olist);
		tvGrades.setEditable(true);
		tvGrades.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<ModuleEntry, String> tcKrz = new TableColumn<>("Kürzel");
		tcKrz.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.KRZ));
		TableColumn<ModuleEntry, String> tcName = new TableColumn<>("Name");
		tcName.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NAME));
		TableColumn<ModuleEntry, String> tcBereich = new TableColumn<>("Bereich");
		tcBereich.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.BEREICH));
		TableColumn<ModuleEntry, Integer> tcSem = new TableColumn<>("Fachsem");
		tcSem.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.SEM));
		TableColumn<ModuleEntry, Integer> tcCps = new TableColumn<>("CPs");
		tcCps.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.CPS));
		TableColumn<ModuleEntry, Double> tcNote = new TableColumn<>("Note");
		tcNote.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NOTE));
		// TODO alle möglichen Noten und - als Optionen anzeigen
		tcNote.setCellFactory(ComboBoxTableCell.<ModuleEntry, Double>forTableColumn(1.0, 2.0));

		tvGrades.getColumns().addAll(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote);

		// Default: sortieren nach Fachsemester
		tvGrades.getSortOrder().addAll(tcSem, tcKrz, tcName);
	}
}
