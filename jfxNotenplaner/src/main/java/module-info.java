module de.pbma.java {
	// with that in module one can launch the main JavaFx stuff in a
	// Main JavaFx class (not forcing to use a separate class)
	exports de.pbma.java;
    
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	
	// for fxml
	// requires transitive javafx.fxml;
	// opens de.pbma.java to javafx.fxml;
}
