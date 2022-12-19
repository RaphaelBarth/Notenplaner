package de.pbma.java;

import java.io.IOException;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainGUIController {

	@FXML
	private BorderPane bpRoot;
	@FXML
	private TabPane tabp;
	@FXML
	private Tab tabOverview;
	@FXML
	private Tab tabGades;

	public MainGUIController() {
	}

	@FXML
	public void initialize() {
		setCurrentView(tabOverview);
		tabp.getSelectionModel().selectedItemProperty().addListener(this::tabListener);

	}

	private void tabListener(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
		setCurrentView(newValue);
	}

	private void setCurrentView(Tab currentTab) {
		final URL urlOverview = getClass().getResource("Overview.fxml");
		final URL urlGradeView = getClass().getResource("GradeView.fxml");
		URL url = null;
		if (tabOverview.equals(currentTab)) {
			url = urlOverview;
		} else if (tabGades.equals(currentTab)) {
			url = urlGradeView;
		}
		try {
			bpRoot.setCenter(FXMLLoader.load(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
