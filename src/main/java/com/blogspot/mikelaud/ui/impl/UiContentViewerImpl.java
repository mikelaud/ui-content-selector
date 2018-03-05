package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiContentSelector;
import com.blogspot.mikelaud.ui.UiContentViewer;
import com.google.inject.Inject;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class UiContentViewerImpl extends UiContentViewer {

	private UiContentSelector UI_CONTENT_SELECTOR;

	private void buildUi() {
		final StackPane stackPane = new StackPane();
		stackPane.getChildren().add(new Button("Button"));
		getItems().addAll(stackPane, UI_CONTENT_SELECTOR);
		setDividerPositions(0.6);
		setMinSize(0, 0);
	}

	@Inject
	private UiContentViewerImpl(UiContentSelector aUiContentSelector) {
		UI_CONTENT_SELECTOR = aUiContentSelector;
		buildUi();
	}

	@Override public UiContentSelector getUiContentSelector() { return UI_CONTENT_SELECTOR; }

}
