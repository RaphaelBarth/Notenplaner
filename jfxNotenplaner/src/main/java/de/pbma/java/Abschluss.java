package de.pbma.java;

public enum Abschluss {
	BACHELOR(210), MASTER(90);

	private final int gesamtCP;
	private final String name;

	private Abschluss(int gesamtCP) {
		this.gesamtCP = gesamtCP;
		String lower = this.name().toLowerCase();
		this.name = lower.substring(0, 1).toUpperCase() + lower.substring(1);
	}

	@Override
	public String toString() {
		return name;
	}

	public int getGesamtCP() {
		return gesamtCP;
	}
}
