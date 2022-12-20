package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteCSV {
		
	public void writeCurricula(File file,Curricula curriculas) {
		String csvString = ParserCSV.parseCurriculaToCSVString(curriculas);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(csvString.getBytes());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
