package de.pbma.java;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CurriculumData {
	private static CurriculumData curriculumData = null;
	private ObjectProperty<Curriculum> objectProperty ;
	private Object lock;

	private CurriculumData() {
		this.lock = new Object();
		this.objectProperty = new SimpleObjectProperty<>(this,"curriculum");
	}

	public static synchronized CurriculumData getData() {
		if (curriculumData == null) {
			curriculumData = new CurriculumData();
		}
		return curriculumData;
	}

	public void setCurriculum(Curriculum c) {
		synchronized (lock) {
			this.objectProperty.setValue(c);		
		}
	}
	
	public void update() {
		synchronized (lock) {
			return;
		}
	}

	public Curriculum getCurriculum() {
		synchronized (lock) {
			//return (curriculum == null) ? null : (Curriculum) curriculum.clone();
			return this.objectProperty.getValue();
		}
	}

	public ObjectProperty<Curriculum> getObjectProperty() {
		synchronized (lock) {
			return objectProperty;			
		}
	}

}
