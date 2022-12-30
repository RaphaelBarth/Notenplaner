package de.pbma.java;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class GradeView implements Initializable {
	@FXML
	private TableView<ModuleEntry> tvGrades;
	@FXML
	private Button btFilter;
	@FXML
	private TextField tfFilter;

	GradeViewModel gradeViewModel = new GradeViewModel();

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

		TableColumn<ModuleEntry, String> tcBereich = new TableColumn<>("Bereich");
		tcBereich.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.BEREICH));

		TableColumn<ModuleEntry, Integer> tcSem = new TableColumn<>("Fachsem");
		tcSem.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.SEM));

		TableColumn<ModuleEntry, Double> tcCps = new TableColumn<>("CPs");
		tcCps.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.CPS));

		TableColumn<ModuleEntry, String> tcNote = new TableColumn<>("Note");
		tcNote.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NOTE));
		Callback<TableColumn<ModuleEntry, String>, TableCell<ModuleEntry, String>> defaultTextFieldCellFactory = TextFieldTableCell
				.<ModuleEntry>forTableColumn();

		tcName.setCellFactory(col -> {
			TableCell<ModuleEntry, String> cell = defaultTextFieldCellFactory.call(col);
			cell.itemProperty().addListener((obs, oldValue, newValue) -> {
				TableRow<ModuleEntry> row = cell.getTableRow();
				if (row == null) {
					cell.setEditable(false);
				} else {
					ModuleEntry item = (ModuleEntry) cell.getTableRow().getItem();
					if (item == null) {
						cell.setEditable(false);
					} else {
						cell.setEditable(item.nameIsEditable());
					}
				}
			});
			return cell;
		});

		// TODO auf Änderungen in der ComboBox und im Namen reagiern

		tcNote.setCellFactory(cellFactoryComboBoxColumn);
		tvGrades.getColumns().addAll(Arrays.asList(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote));
		// Default: sortieren nach Fachsemester
		tvGrades.getSortOrder().addAll(Arrays.asList(tcSem, tcKrz, tcName));
	}

	@FXML
	public void onFilter() {
		gradeViewModel.filterList();
	}

	private Callback<TableColumn<ModuleEntry, String>, TableCell<ModuleEntry, String>> cellFactoryComboBoxColumn = new Callback<TableColumn<ModuleEntry, String>, TableCell<ModuleEntry, String>>() {
		@Override
		public TableCell<ModuleEntry, String> call(final TableColumn<ModuleEntry, String> param) {
			final TableCell<ModuleEntry, String> cell = new TableCell<ModuleEntry, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
					} else {
						var moduleEntry = getTableView().getItems().get(getIndex());
						var tableViewComboBox = new ComboBox<String>();
						tableViewComboBox.setItems((ObservableList<String>) moduleEntry.getPossibleEvaluations());
						tableViewComboBox.setPrefWidth(param.getWidth() - 2);
						tableViewComboBox.setValue(moduleEntry.getGrade());
						tableViewComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
							moduleEntry.setGrade(newValue);
							gradeViewModel.setGrade(moduleEntry.getShortName(), moduleEntry.getSubjectName(), newValue);
						});
						setGraphic(tableViewComboBox);
					}
				}
			};
			return cell;
		}
	};
}
