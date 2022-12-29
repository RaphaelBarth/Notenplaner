package de.pbma.java;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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

	private ObservableList<String> shortList;
	private ObservableList<String> allGradesList;

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
		// tcName.setCellFactory(TextFieldTableCell.forTableColumn());

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
						cell.setEditable(item.getKrz().startsWith("WF"));
					}
				}
			});
			return cell;
		});

		TableColumn<ModuleEntry, String> tcBereich = new TableColumn<>("Bereich");
		tcBereich.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.BEREICH));
		TableColumn<ModuleEntry, Integer> tcSem = new TableColumn<>("Fachsem");
		tcSem.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.SEM));
		TableColumn<ModuleEntry, Integer> tcCps = new TableColumn<>("CPs");
		tcCps.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.CPS));
		TableColumn<ModuleEntry, String> tcNote = new TableColumn<>("Note");
		tcNote.setCellValueFactory(new PropertyValueFactory<>(ModuleEntry.NOTE));
		allGradesList = FXCollections.observableArrayList();
		Arrays.asList(Grades.values()).forEach(v -> allGradesList.add(v.toString()));
		allGradesList.remove(Grades.PASSED.toString());
		allGradesList = allGradesList.sorted();
		shortList = FXCollections.observableArrayList(Grades.PASSED.toString(), Grades.NOTPASSED.toString());
		// TODO auf Änderungen in der ComboBox und im Namen reagiern

		Callback<TableColumn<ModuleEntry, String>, TableCell<ModuleEntry, String>> cellFactoryComboBoxColumn = new Callback<TableColumn<ModuleEntry, String>, TableCell<ModuleEntry, String>>() {
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
							boolean fullList = CurriculumData.getData().getCurriculum().getSubject(moduleEntry.getKrz())
									.hasGradeAsEvaluation();
							var tableViewComboBox = new ComboBox<String>();
							if (fullList) {
								tableViewComboBox.setItems(allGradesList);
							} else {
								tableViewComboBox.setItems(shortList);
							}
							tableViewComboBox.setPrefWidth(tcNote.getWidth() - 2);
							tableViewComboBox.setValue(moduleEntry.getNote());
							setGraphic(tableViewComboBox);
						}
					}
				};

				return cell;
			}
		};

		tcNote.setCellFactory(cellFactoryComboBoxColumn);

		tvGrades.getColumns().addAll(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote);

		// Default: sortieren nach Fachsemester
		tvGrades.getSortOrder().addAll(tcSem, tcKrz, tcName);
	}

	@FXML
	public void onFilter() {
		gradeViewModel.filterList();
	}
}
