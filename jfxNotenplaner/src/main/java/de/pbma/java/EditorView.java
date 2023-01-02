package de.pbma.java;

import java.net.URL;
import java.util.Arrays;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.Alert.AlertType;

public class EditorView implements Initializable {

	@FXML
	TextField tfCoursOfStudiesShort;
	@FXML
	TextField tfCoursOfStudies;
	@FXML
	TextField tfTotalCredits;
	@FXML
	TableView<CurriculumSubjectEntry> tvSubjects;
	@FXML
	Button btnSave;

	@FXML
	TextField tfSubjectName;
	@FXML
	TextField tfSubjectNameShort;
	@FXML
	TextField tfFocus;
	@FXML
	TextField tfCredits;
	@FXML
	ComboBox<Integer> cbSemester;
	@FXML
	CheckBox cbGrade; // TODO add in FXML
	@FXML
	Button btnAdd;

	private EditorViewModel editorViewModel;

	public EditorView() {
		this.editorViewModel = new EditorViewModel();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfCoursOfStudies.textProperty().bindBidirectional(editorViewModel.getCurriculumNameProperty());
		tfCoursOfStudiesShort.textProperty().bindBidirectional(editorViewModel.getCurriculumNameShortProperty());
		tfTotalCredits.textProperty().bindBidirectional(editorViewModel.getTotalCreditsProperty(),
				new NumberStringConverter());
		tfTotalCredits.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*|\\d+\\.\\d*")) {
				tfTotalCredits.setText(newValue.replaceAll("[^\\d]", ""));

			}
		});
		btnSave.disableProperty()
				.bind(Bindings.isEmpty(tfCoursOfStudiesShort.textProperty())
						.or(Bindings.isEmpty(tfCoursOfStudies.textProperty()))
						.or(Bindings.isEmpty(tfTotalCredits.textProperty())));

		btnAdd.disableProperty().bind(Bindings.isEmpty(tfSubjectName.textProperty())
				.or(Bindings.isEmpty(tfSubjectNameShort.textProperty())).or(Bindings.isEmpty(tfCredits.textProperty()))
				.or(Bindings.isEmpty(tfFocus.textProperty())).or(Bindings.isNull(cbSemester.valueProperty())));
		
		tfCredits.textProperty().bindBidirectional(editorViewModel.getTotalCreditsProperty(),
				new NumberStringConverter());
		tfCredits.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*|\\d+\\.\\d*")) {
				tfCredits.setText(newValue.replaceAll("[^\\d]", ""));

			}
		});

		cbSemester.setItems(FXCollections.observableArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));

		TableColumn<CurriculumSubjectEntry, String> tcKrz = new TableColumn<>("Kürzel");
		tcKrz.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.SHORT));
		tcKrz.setCellFactory(TextFieldTableCell.forTableColumn());
		tcKrz.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNameShort(e.getNewValue()));

		TableColumn<CurriculumSubjectEntry, String> tcName = new TableColumn<>("Name");
		tcName.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.NAME));
		tcName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcName.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue()));

		TableColumn<CurriculumSubjectEntry, String> tcBereich = new TableColumn<>("Bereich");
		tcBereich.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.FOCUS));
		tcBereich.setCellFactory(TextFieldTableCell.forTableColumn());
		tcBereich.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setFocus(e.getNewValue()));

		TableColumn<CurriculumSubjectEntry, Integer> tcSem = new TableColumn<>("Fachsem");
		tcSem.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.SEMESTER));
		var hasSemesterList = FXCollections.observableArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		tcSem.setCellFactory(ComboBoxTableCell.forTableColumn(hasSemesterList));
		tcSem.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setSemester(e.getNewValue()));

		TableColumn<CurriculumSubjectEntry, Double> tcCps = new TableColumn<>("Credits");
		tcCps.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.CREDITS));
		tcCps.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		tcCps.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCredits(e.getNewValue()));
		tfTotalCredits.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*|\\d+\\.\\d*")) {
				tfTotalCredits.setText(newValue.replaceAll("[^\\d]", ""));

			}
		});
		
		TableColumn<CurriculumSubjectEntry, Boolean> tcNote = new TableColumn<>("Note");
		tcNote.setCellValueFactory(new PropertyValueFactory<>(CurriculumSubjectEntry.HASGRADE));
		var hasGradeList = FXCollections.observableArrayList(Arrays.asList(true, false));
		tcNote.setCellFactory(ComboBoxTableCell.forTableColumn(hasGradeList));
		tcNote.setOnEditCommit(
				e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setHasGrade(e.getNewValue()));

		TableColumn<CurriculumSubjectEntry, CurriculumSubjectEntry> tcDelete = new TableColumn<>("Entfernen");
		tcDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tcDelete.setCellFactory(param -> new TableCell<CurriculumSubjectEntry, CurriculumSubjectEntry>() {
			private final Button deleteButton = new Button("X");

			@Override
			protected void updateItem(CurriculumSubjectEntry subjectEntry, boolean empty) {
				super.updateItem(subjectEntry, empty);

				if (subjectEntry == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> getTableView().getItems().remove(subjectEntry));
			}
		});

		tvSubjects.getColumns().addAll(Arrays.asList(tcKrz, tcName, tcBereich, tcSem, tcCps, tcNote, tcDelete));

		tvSubjects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tvSubjects.setItems(editorViewModel.getSubjectsList());
		tvSubjects.setEditable(true);
	}

	@FXML
	public void btnSaveClicked() {
		if (!editorViewModel.saveCurriculum()) {
			var errorMsg = editorViewModel.getErrorMessage();
			Platform.runLater(() -> {
				final Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setContentText(errorMsg);
				alert.show();
			});
		}
	}

	@FXML
	public void btnAddClicked() {
		var name = tfSubjectName.getText();
		var nameShort = tfSubjectNameShort.getText();
		var credits = Double.parseDouble(tfCredits.getText());
		var focus = tfFocus.getText();
		var semester = cbSemester.getValue();
		var hasGrade = cbGrade.isSelected();
		CurriculumSubject subject = new CurriculumSubject(nameShort, name, focus, hasGrade, semester, credits);
		editorViewModel.addSubject(subject);
		System.out.println("new subject: " + subject);
	}

	@FXML
	public void btnEditClicked() {

	}

}
