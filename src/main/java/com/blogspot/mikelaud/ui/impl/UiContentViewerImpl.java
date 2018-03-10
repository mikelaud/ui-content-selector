package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiContentSelector;
import com.blogspot.mikelaud.ui.UiContentViewer;
import com.blogspot.mikelaud.ui.UiVideo;
import com.google.inject.Inject;

public class UiContentViewerImpl extends UiContentViewer {

	private UiContentSelector UI_CONTENT_SELECTOR;
	private UiVideo UI_VIDEO;

	private void buildUi() {
		getItems().addAll(UI_VIDEO, UI_CONTENT_SELECTOR);
		setDividerPositions(0.6);
		setMinSize(0, 0);
	}

	@Inject
	private UiContentViewerImpl
	(	UiContentSelector aUiContentSelector
	,	UiVideo aUiVideo
	) {
		UI_CONTENT_SELECTOR = aUiContentSelector;
		UI_VIDEO = aUiVideo;
		buildUi();
	}

	@Override public UiContentSelector getUiContentSelector() { return UI_CONTENT_SELECTOR; }
	@Override public UiVideo getUiVideo() { return UI_VIDEO; }

}
