package de.pbma.java;

import java.io.File;

public class UserFiles {
	private static UserFiles userFiles;

	private File studentFile;
	private File curriculumFile;

	// TODO string property for filepath ?

	private UserFiles() {
	}

	public synchronized static UserFiles getUserFiles() {
		if (userFiles == null) {
			userFiles = new UserFiles();
		}
		return userFiles;
	}

	@Override
	public String toString() {
		return "UserFiles [studentFile=" + studentFile + ", CurriculumFile=" + curriculumFile + "]";
	}

	public File getStudentFile() {
		return studentFile;
	}

	public void setStudentFile(File studentFile) {
		this.studentFile = studentFile;
	}

	public File getCurriculumFile() {
		return this.curriculumFile;
	}

	public void setCurriculumFile(File curriculumFile) {
		this.curriculumFile = curriculumFile;
	}

}
