package de.pbma.java;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

	public NumberTextField() {
		super();
	}

	public NumberTextField(String text) {
		super(text);
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if (text.matches("[0-9]*")) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (text.matches("[0-9]*")) {
			super.replaceSelection(text);
		}
	}

}
