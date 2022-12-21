package de.pbma.java;

import java.io.File;

public class StudentFileLogic {

	private UserFiles userFiles;
	private Student student;

	public StudentFileLogic() {
		this.userFiles = UserFiles.getUserFiles();
	}

	public boolean fileLoad(File file) {
		System.out.format("Datei \"%s\" laden\n", file.toString());
		boolean retval = false;
		CSVFileParser parser = new CSVFileParser(file);
		try {
			var student = parser.getStudent(); // TODO save student to ??
			System.out.println(student);
			StudentData.getStudentData().updateStudentData(student);			
			userFiles.setStudentFile(file);
			retval = true;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return retval;

	}

	public File getCurrentFile() {
		return userFiles.getStudentFile();
	}

	public void saveToFile(File file) {
		System.out.format("Datei \"%s\" speichern\n", file.toString());
		userFiles.setStudentFile(file);
		CSVFileWriter writer = new CSVFileWriter(file);
		writer.saveStudent(student);
	}

	public void fileNew(File file) {
		System.out.format("Datei \"%s\" erstellen\n", file.toString());
	}
}
