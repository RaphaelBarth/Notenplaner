package de.pbma.java;

public class CurriculumData {
	private static CurriculumData curriculumData = null;
	private Curriculum curriculum;
	private Object lock;

	private CurriculumData() {
		this.lock = new Object();
	}

	public static synchronized CurriculumData getData() {
		if (curriculumData == null) {
			curriculumData = new CurriculumData();
		}
		return curriculumData;
	}

	public void update(Curriculum c) {
		synchronized (lock) {
			this.curriculum = c;			
		}
	}

	public Curriculum getCurriculum() {
		synchronized (lock) {
			return (curriculum == null) ? null : (Curriculum) curriculum.clone();

		}
	}

}
