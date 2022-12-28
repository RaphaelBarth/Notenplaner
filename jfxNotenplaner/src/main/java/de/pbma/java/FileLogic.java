package de.pbma.java;

import java.io.File;
import java.io.FileNotFoundException;

public class FileLogic {

	private UserFiles userFiles;
	private Student student;
	private Curriculum curriculum;

	public FileLogic() {
		this.userFiles = UserFiles.getUserFiles();
		this.curriculum = null;
		this.student = null;
	}

	public boolean loadStudentFile(File file) {
		System.out.format("Datei \"%s\" laden\n", file.toString());
		boolean retval = false;
		try {
			student = CSVFileParser.getStudent(file);
			System.out.println(student);
			userFiles.setStudentFile(file);
			retval = true;
		} catch (ParserException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return retval;

	}

	public void saveStudentFile(File file) {
		System.out.format("Datei \"%s\" speichern\n", file.toString());
		userFiles.setStudentFile(file);
		CSVFileWriter writer = new CSVFileWriter(file);
		writer.saveStudent(student);
	}

	public File getCurrentStudentFile() {
		return userFiles.getStudentFile();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean loadCurriculumFile(File file) {
		System.out.format("Datei \"%s\" laden\n", file.toString());
		boolean retval = false;
		try {
			setCurriculum(CSVFileParser.getCurriculum(file));
			userFiles.setCurriculumFile(file);
			retval = true;
		} catch (ParserException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return retval;

	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public File getCurrentCurriculumFile() {
		return userFiles.getCurriculumFile();
	}
}
