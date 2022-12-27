package de.pbma.java;

public class CurriculumData {
	private Curriculum curriculum;
	private static CurriculumData curriculumData = null;

	private CurriculumData() {
	}
	
	public static synchronized CurriculumData getData() {
		if(curriculumData == null) {
			curriculumData = new CurriculumData();
		}
		return curriculumData;
	}
	
	public void update(Curriculum c) {
		this.curriculum = c;
	}
	
	public Curriculum getCurriculum() {
		return curriculum;
	}

}
