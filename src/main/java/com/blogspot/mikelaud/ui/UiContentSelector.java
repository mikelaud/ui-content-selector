package com.blogspot.mikelaud.ui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

public class UiContentSelector extends StackPane {

	private TitledPane newTitledPane(String aTitle) {
		final TitledPane pane = new TitledPane();
		pane.setText(aTitle);
		pane.setContent(new Button(aTitle));
		return pane;
	}

	private Accordion newAccordion() {
		final Accordion accordion = new Accordion();
		accordion.getPanes().add(newTitledPane("Test 1"));
		accordion.getPanes().add(newTitledPane("Test 2"));
		accordion.getPanes().add(newTitledPane("Test 3"));
		return accordion;
	}

	public UiContentSelector() {
		getChildren().add(newAccordion());
	}

}
