package de.pbma.java;

import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		URL url = getClass().getResource("MainGUI.fxml");
		Parent root = FXMLLoader.load(url);
		Scene scene = new Scene(root);
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
