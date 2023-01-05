package de.pbma.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class EditorViewModel {

	private final StringProperty errorProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameProperty = new SimpleStringProperty();
	private final StringProperty curriculumNameShortProperty = new SimpleStringProperty();
	private final StringProperty totalCreditsProperty = new SimpleStringProperty();
	private final ObservableList<CurriculumSubjectEntry> subjectsList = FXCollections.observableArrayList();
	private final BooleanProperty fileAvailableProperty = new SimpleBooleanProperty();

	private AtomicBoolean newCurriculum;
	private AtomicBoolean curriculumUpdated;

	public EditorViewModel() {
		newCurriculum = new AtomicBoolean(false);
		curriculumUpdated = new AtomicBoolean(false);

		loadCurriculum();
		CurriculumData.getData().getObjectProperty().addListener((observable, oldValue, newValue) -> {
			if (!newCurriculum.get()) {
				Platform.runLater(() -> {
					loadCurriculum();
				});
			}
			curriculumUpdated.set(false);
		});
	}

	public boolean createNewCurriculum() {
		if (this.newCurriculum.get()) {
			this.newCurriculum.set(false);
			loadCurriculum();
		} else {
			this.newCurriculum.set(true);
			this.fileAvailableProperty.set(true);
			this.setCurriculumName("");
			this.setCurriculumNameShort("");
			this.setTotalCredits("");
			subjectsList.clear();
		}
		return this.newCurriculum.get();
	}

	public void saveCurriculum() {
		final var totalCredits = Double.parseDouble(getTotalCredits());
		final var curriculumName = getCurriculumName();
		final var curriculumShort = getCurriculumNameShort();

		new Thread(() -> {
			double credits = subjectsList.stream().mapToDouble(s -> s.getCredits()).sum();
			if (credits < totalCredits) {
				setErrorMessage("Creditssumme der Fächer ist kleiner als die gesammt Credits des Studiums");
				return;
			}
			final Curriculum curriculum = new Curriculum(curriculumShort, curriculumName, totalCredits);
			subjectsList.stream().map(s -> s.getCurriculumSubject()).forEach(s -> curriculum.addSubject(s));
			System.out.println("save new curriculum: " + curriculum);
			if (newCurriculum.get()) {
				Platform.runLater(() -> {
					Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save Resource File");
					fileChooser.setInitialFileName(curriculum.getNameShort() + ".csv");
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Value", "*.csv"));
					final File file = fileChooser.showSaveDialog(owner);
					new Thread(() -> {
						if (file != null) {
							var fileLogic = new FileLogic();
							fileLogic.setCurriculum(curriculum);
							fileLogic.saveCurriculumFile(file);
						}
					}).start();
				});
			} else {
				var fileLogic = new FileLogic();
				var file = fileLogic.getCurriculumFiles().getOrDefault(curriculum.getNameShort(), null);
				if (file != null) {
					fileLogic.setCurriculum(curriculum);
					fileLogic.saveCurriculumFile(file);
					CurriculumData.getData().setCurriculum(curriculum);
					curriculumUpdated.set(true);
				} else {
					setErrorMessage("Fehler beim speichern");
				}
			}
		}).start();
		;
		return;
	}

	public BooleanProperty getFileAvailableProperty() {
		return fileAvailableProperty;
	}

	public Boolean getFileAvailable() {
		return fileAvailableProperty.get();
	}

	public void setFileAvailable(boolean b) {
		System.out.println(b);
		fileAvailableProperty.set(b);
	}

	public StringProperty getCurriculumNameProperty() {
		return curriculumNameProperty;
	}

	public String getCurriculumName() {
		return curriculumNameProperty.getValue();
	}

	public void setCurriculumName(String curriculumName) {
		curriculumNameProperty.setValue(curriculumName);
	}

	public StringProperty getCurriculumNameShortProperty() {
		return curriculumNameShortProperty;
	}

	public String getCurriculumNameShort() {
		return curriculumNameShortProperty.getValue();
	}

	public void setCurriculumNameShort(String curriculumNameShort) {
		curriculumNameShortProperty.setValue(curriculumNameShort);
	}

	public StringProperty getTotalCreditsProperty() {
		return totalCreditsProperty;
	}

	public String getTotalCredits() {
		return totalCreditsProperty.getValue();
	}

	public void setTotalCredits(String totalCredits) {
		totalCreditsProperty.setValue(totalCredits);
	}

	public ObservableList<CurriculumSubjectEntry> getSubjectsList() {
		return subjectsList;
	}

	@Deprecated
	public boolean addSubject(CurriculumSubject curriculumSubject) {
		return subjectsList.add(new CurriculumSubjectEntry(curriculumSubject));
	}

	public boolean addSubject(CurriculumSubjectEntry curriculumSubject) {
		System.out.println("new subject: " + curriculumSubject);
		return subjectsList.add(curriculumSubject);
	}

	public void addSubject(String nameShort, String name, String focus, boolean hasGrade, Integer semester,
			double credits) {
		var curiculumSubject = new CurriculumSubject(nameShort, name, focus, hasGrade, semester, credits);
		addSubject(new CurriculumSubjectEntry(curiculumSubject));
	}

	public boolean removeSubject(CurriculumSubjectEntry curriculumSubject) {
		return subjectsList.remove(curriculumSubject);
	}

	public StringProperty getErrorMessage() {
		return errorProperty;
	}
	public void setErrorMessage(String string) {
		errorProperty.setValue("");
		errorProperty.setValue(string);
	}

	private void loadCurriculum() {
		var curriculum = CurriculumData.getData().getCurriculum();
		if (curriculum == null) {
			return;
		}
		setFileAvailable(true);
		this.setCurriculumName(curriculum.getName());
		this.setCurriculumNameShort(curriculum.getNameShort());
		this.setTotalCredits(String.valueOf(curriculum.getCredits()));
		for (var subject : curriculum.getAllSubjects()) {
			this.addSubject(new CurriculumSubjectEntry(subject));
		}
	}

}
