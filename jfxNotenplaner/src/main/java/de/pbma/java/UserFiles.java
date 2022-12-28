package de.pbma.java;

import java.io.File;

public class UserFiles {
	private static UserFiles userFiles;

	private File studentFile;
	private File curriculumFile;
	private Object lock;

	// TODO string property for filepath ?

	private UserFiles() {
		this.lock = new Object();
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
		synchronized (lock) {
			return this.studentFile;
		}
	}

	public void setStudentFile(File studentFile) {
		synchronized (lock) {
			this.studentFile = studentFile;			
		}
	}

	public File getCurriculumFile() {
		synchronized (lock) {
			return this.curriculumFile;			
		}
	}

	public void setCurriculumFile(File curriculumFile) {
		synchronized (lock) {
			this.curriculumFile = curriculumFile;			
		}
	}

}
