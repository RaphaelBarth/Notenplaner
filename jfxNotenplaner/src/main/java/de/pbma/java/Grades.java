package de.pbma.java;

import java.util.HashMap;
import java.util.Map;

public enum Grades {
	ONE(1.0), ONEMINUS(1.3), TWOPLUS(1.7), TWO(2.0), TWOMINUS(2.3), THREEPLUS(2.7), THREE(3.0), THREEMINUS(3.3),
	FOURPLUS(3.7), FOUR(4.0), NOTPASSED(5.0), PASSED(0.0);

	final public double value;

	Grades(double d) {
		this.value = d;
	}

	@Override
	public String toString() {
		return String.format("%s(%.1f)", name(), value);
	}

	public String text() {
		if (value == 0) {
			return "bestanden";
		}
		if (value < 1.7) {
			return "sehr gut";
		}
		if (value < 2.7) {
			return "gut";
		}
		if (value < 3.7) {
			return "befriedigend";
		}
		if (value <= 4.0) {
			return "ausreichend";
		}
		return "nicht ausreichend";
	}

	private static final Map<Double, Grades> BY_VALUE = new HashMap<>();
	static {
		for (Grades e : values()) {
			BY_VALUE.put(e.value, e);
		}
	}

	public static Grades getByValue(Double value) {
		return BY_VALUE.getOrDefault(value, NOTPASSED);
	}

}
