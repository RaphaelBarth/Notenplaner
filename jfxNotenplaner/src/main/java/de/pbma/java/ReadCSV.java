package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV {

	public Curricula readCurricula(File file) {
		Curricula curricula = null;

		try (Scanner scanner = new Scanner(file);) {
			var header = scanner.nextLine();
			curricula = ParserCSV.parseCSVStringToCurricula(header);
			while (scanner.hasNextLine()) {			
				var line = scanner.nextLine();
				Subject subject = ParserCSV.parseCSVStringToSubject(line);
				curricula.addSubject(subject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return curricula;
	}

	

	

}
