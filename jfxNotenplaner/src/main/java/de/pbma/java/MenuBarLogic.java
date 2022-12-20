package de.pbma.java;

import java.io.File;

public class MenuBarLogic {

	private File file;

	public MenuBarLogic() {
		this.file = null;
	}

	public void fileLoad(File file) {
		this.file = file;
		System.out.format("Datei \"%s\" laden\n", file.toString());
	}

	public void fileSave(File file) {
		System.out.format("Datei \"%s\" speichern\n", file.toString());

	}

	public void fileNew(File file) {
		System.out.format("Datei \"%s\" erstellen\n", file.toString());
	}


	public void settingsMode(boolean lightMode) {
		boolean light = true;
		if (lightMode == light) {
			return;
		}
		System.out.println("Farbe wechseln");
		light = lightMode;
	}

	public File fileToSave() {
		return this.file;
	}



}
