package de.pbma.java;

public class MenuBarLogic {

	public void fileLoad() {
		System.out.println("Datei laden");
	}

	public void fileSave() {
		System.out.println("Datei speichern");

	}

	public void fileNew() {
		System.out.println("Datei neu erstellen");		
	}
	
	public void settingsMode(boolean lightMode) {
		boolean light = true;
		if (lightMode == light) {
			return;
		}
		System.out.println("Farbe wechseln");
		light = lightMode;
	}

}
