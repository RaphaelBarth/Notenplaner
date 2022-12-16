package de.pbma.java;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
  private List<String> params;
	
	@Override
	public void init() throws Exception {
		// Läuft noch im Launcher-Thread, nichts mit UI machen
		// Hier nur Parameter auslesen, wenn notwendig
		params = getParameters().getRaw();
		if (!params.isEmpty()) {
			System.out.format("#params: %d\n", params.size());
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// Läuft im JavaFx Application Thread, alles mit UI
		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("Datei");
		MenuItem openItem = new MenuItem("Öffnen");
		MenuItem saveItem = new MenuItem("Speichern");
		menuFile.getItems().addAll(openItem, saveItem);
		Menu menuSettings = new Menu("Einstellungen");
		Menu menuHelp = new Menu("Hilfe");
		menuBar.getMenus().addAll(menuFile, menuSettings, menuHelp);
		root.setTop(menuBar);
		Scene scene = new Scene(root, 400, 300);
		stage.setTitle("Notenplaner");
		stage.setScene(scene);
		stage.show();
	}
  
	@Override
	public void stop() {
		// Läuft im JavaFx Application Thread, UI möglich
	}
  
	// Achtung: Ein statischer Launch einer Klasse, die von Application
	// erbt ist *nur* möglich, wenn man Module verwendet (Hölle).
	// Verwenden Sie Module, denken Sie nicht darüber nach, um Ihre
	// geistige Gesundheit nicht zu gefähren ;-)
	public static void main(String[] args) {
		launch(args);
	}
  
}
