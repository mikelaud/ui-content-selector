package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiBook;
import com.google.inject.Inject;

import javafx.scene.control.Button;

public class UiBookImpl extends UiBook {

	@Inject
	private UiBookImpl() {
		setCenter(new Button("UI Button"));
	}

}
