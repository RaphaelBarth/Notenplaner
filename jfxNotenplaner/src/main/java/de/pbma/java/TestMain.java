package de.pbma.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMain {

	public static void main(String[] args) {
		File fileName = new File("test.csv");
		creatingFile(fileName);
		
		File fileName2 = new File("test2.csv");
		var c = new ReadCSV().readCurricula(fileName);
		
		System.out.println(c);
		new WriteCSV().writeCurricula(fileName2, c);
	}

	private static void creatingFile(File file) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("MTB;Medizintechnik;210\n");
		stringBuilder.append("PI;Praktische Informatik;Software;1;5\n");
		stringBuilder.append("MA;Mathematik;Grundlagen;1;6\n");
		stringBuilder.append("DB;Datenbanken;Software;6;5\n");

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
