package de.pbma.java;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private TextField tfFilter;
	@FXML
	private ComboBox<String> cbCategory;

	GradeViewModel gradeViewModel = new GradeViewModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfFilter.textProperty().bindBidirectional(gradeViewModel.filterProperty());
		tfFilter.disableProperty().bind(gradeViewModel.filterDisabledProperty());
		cbCategory.disableProperty().bind(gradeViewModel.filterDisabledProperty());

		tvGrades.setItems(gradeViewModel.getOListProperty());
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

		TableColumn<ModuleEntry, Grades> tcNote = new TableColumn<>("Note");
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
			cell.itemProperty().addListener((obs, oldName, newName) -> {
				TableRow<ModuleEntry> row = cell.getTableRow();
				if (row == null) {
					return;
				} else {
					ModuleEntry item = (ModuleEntry) cell.getTableRow().getItem();
					if (item == null || !item.nameIsEditable()) {
						return;
					} else {
						if (oldName == null || newName == null) {
							return;
						}
						if (!newName.equals(item.getSubjectName())) {
							item.setSubjectName(newName);
							gradeViewModel.setSubjectName(item.getShortName(), newName);
						}
					}
				}
			});
			return cell;
		});

		tcNote.setCellFactory(cellFactoryComboBoxColumn);
		// 4. Bind the SortedList comparator to the TableView comparator.
		gradeViewModel.getSortedListProperty().comparatorProperty().bind(tvGrades.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tvGrades.setItems(gradeViewModel.getSortedListProperty());

		tvGrades.getColumns().addAll(Arrays.asList(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote));
		// Default: sortieren nach Fachsemester, Kürzel und Name
		tvGrades.getSortOrder().addAll(Arrays.asList(tcSem, tcKrz, tcName));
		//use no selectionmodul
		tvGrades.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			Platform.runLater(() -> {
				tvGrades.getSelectionModel().clearSelection();
			});
		});

		cbCategory.getItems().addAll("überall", "Name", "Kürzel", "Bereich");
		cbCategory.getSelectionModel().selectFirst();
		gradeViewModel.getSelectedFilterCategoryProperty().bind(cbCategory.getSelectionModel().selectedItemProperty());
	}

	private Callback<TableColumn<ModuleEntry, Grades>, TableCell<ModuleEntry, Grades>> cellFactoryComboBoxColumn = new Callback<TableColumn<ModuleEntry, Grades>, TableCell<ModuleEntry, Grades>>() {
		@Override
		public TableCell<ModuleEntry, Grades> call(final TableColumn<ModuleEntry, Grades> param) {
			final TableCell<ModuleEntry, Grades> cell = new TableCell<ModuleEntry, Grades>() {
				@Override
				public void updateItem(Grades item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
					} else {
						var moduleEntry = getTableView().getItems().get(getIndex());
						var tableViewComboBox = new ComboBox<Grades>();
						tableViewComboBox.setItems((ObservableList<Grades>) moduleEntry.getPossibleEvaluations());
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
