package de.pbma.java;

import java.io.IOException;
import java.net.URL;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MainGUIController {

	@FXML
	private BorderPane bpRoot;
	@FXML
	private TabPane tabp;
	@FXML
	private Tab tabOverview;
	@FXML
	private Tab tabGrades;
	@FXML
	private Label lblStatus;

	public MainGUIController() {
	}

	@FXML
	public void initialize() {
		lblStatus.setText("BananenPalme");
		setCurrentView(ContentType.OVERVIEW);
		tabp.getSelectionModel().selectedItemProperty().addListener(this::tabListener);

	}

	private void tabListener(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
		var contentType = ContentType.getContentType(newValue.getId());
		setCurrentView(contentType);
	}

	private void setCurrentView(ContentType contentType) {
		try {
			bpRoot.setCenter(FXMLLoader.load(contentType.url));
			System.out.println("Changed content to: " + contentType.name());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private enum ContentType {
		OVERVIEW(ContentType.class.getResource("Overview.fxml"), "tabOverview"),
		GRADEVIEW(ContentType.class.getResource("GradeView.fxml"), "tabGrades"),
		ANALYSEVIEW(ContentType.class.getResource(""), ""), EDITORVIEW(ContentType.class.getResource(""), "");

		public final URL url;
		public final String id;

		ContentType(URL url, String string) {
			this.url = url;
			this.id = string;
		}

		public static ContentType getContentType(String id) {
			System.out.println(id);
			for (ContentType contentType : ContentType.values()) {
				if (contentType.id.equalsIgnoreCase(id)) {
					return contentType;
				}
			}
			return null;
		}
	}
}
