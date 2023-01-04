package de.pbma.java;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

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
//	@FXML
//	private Label lblStatus;

	public MainGUIController() {
	}

	@FXML
	public void initialize() {

		// lblStatus.setText("BananenPalme");
		tabp.getSelectionModel().selectedItemProperty().addListener(this::tabListener);
		var tabs = Stream.of(ContentType.values()).map(content -> {
			var tab = new Tab();
			tab.setId(content.id);
			tab.setText(content.name);
			return tab;
		}).toList();
		tabp.getTabs().addAll(tabs);
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
		OVERVIEW(ContentType.class.getResource("Overview.fxml"), "tabOverview", "Übersicht"),
		GRADEVIEW(ContentType.class.getResource("GradeView.fxml"), "tabGrades", "Noten"),
		ANALYSEVIEW(ContentType.class.getResource("AnalyseView.fxml"), "AnalyseView", "Analyse"),
		EDITORVIEW(ContentType.class.getResource("EditorView.fxml"), "EditorView", "Editor");

		public final URL url;
		public final String id;
		public final String name;

		ContentType(URL url, String string, String name) {
			this.url = url;
			this.id = string;
			this.name = name;
		}

		public static ContentType getContentType(String id) {
			for (ContentType contentType : ContentType.values()) {
				if (contentType.id.equalsIgnoreCase(id)) {
					return contentType;
				}
			}
			return null;
		}
	}
}
