package de.pbma.java;

import java.io.Serializable;
import java.util.Objects;

public class ModuleEntry implements Serializable {
	private static final long serialVersionUID = -8674008613457029157L;
	public static final String KRZ = "krz";
	public static final String NAME = "name";
	public static final String BEREICH = "bereich";
	public static final String SEM = "sem";
	public static final String CPS = "cps";
	public static final String NOTE = "note";

	private String krz;
	private String name;
	private String bereich;
	private int sem;
	private int cps;
	private double note;

	public ModuleEntry(String krz, String name, String bereich, int sem, int cps, double note) {
		this.krz = krz;
		this.name = name;
		this.bereich = bereich;
		this.sem = sem;
		this.cps = cps;
		this.note = note;
	}

	public ModuleEntry(String krz, String name, String bereich, int sem, int cps) {
		this(krz, name, bereich, sem, cps, 0.0);
	}

	public String getKrz() {
		return krz;
	}

	public void setKrz(String krz) {
		this.krz = krz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBereich() {
		return bereich;
	}

	public void setBereich(String bereich) {
		this.bereich = bereich;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

	public int getCps() {
		return cps;
	}

	public void setCps(int cps) {
		this.cps = cps;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "ModuleEntry [" + (krz != null ? "krz=" + krz + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (bereich != null ? "bereich=" + bereich + ", " : "") + "sem=" + sem + ", cps=" + cps + ", note="
				+ note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bereich, cps, krz, name, note, sem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleEntry other = (ModuleEntry) obj;
		return Objects.equals(bereich, other.bereich) && cps == other.cps && Objects.equals(krz, other.krz)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(note) == Double.doubleToLongBits(other.note) && sem == other.sem;
	}
}
