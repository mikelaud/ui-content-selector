package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiChapter;
import com.google.inject.Inject;

import javafx.scene.control.Button;

public class UiChapterImpl extends UiChapter {

	@Inject
	private UiChapterImpl() {
		setCenter(new Button("UI Chapter"));
	}

}
