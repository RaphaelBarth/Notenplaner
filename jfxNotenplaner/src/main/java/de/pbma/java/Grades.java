package de.pbma.java;

public enum Grades {
	ONE(1.0), ONEMINUS(1.3), TWOPLUS(1.7), TWO(2.0), TWOMINUS(2.3), THREEPLUS(2.7), THREE(3.0), THREEMINUS(3.3),
	FOURPLUS(3.7), FOUR(4.0);

	final public double value;

	Grades(double d) {
		this.value = d;
	}

	@Override
	public String toString() {
		return String.format("Grades.%s value is %f", this.name(),this.value);
	}
}
