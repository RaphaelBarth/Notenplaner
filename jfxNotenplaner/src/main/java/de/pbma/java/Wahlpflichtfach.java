package de.pbma.java;

import java.util.ArrayList;
import java.util.List;

public class Wahlpflichtfach {
	public List<Subject> possibleSubjects;

	public Wahlpflichtfach(List<Subject> subjects) {
		possibleSubjects = subjects;
	}

	public Wahlpflichtfach(Subject first, Subject second) {
		possibleSubjects = new ArrayList<Subject>();
		possibleSubjects.add(first);
		possibleSubjects.add(second);
	}
}
