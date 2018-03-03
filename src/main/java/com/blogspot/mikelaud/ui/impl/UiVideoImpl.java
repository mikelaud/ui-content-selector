package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiVideo;
import com.google.inject.Inject;

import javafx.scene.media.MediaView;

public class UiVideoImpl extends UiVideo {

	@Override
	protected void layoutChildren() {
		// void
	}

	@Inject
	private UiVideoImpl() {
		// void
	}

	@Override
	public MediaView getMediaView() {
		return null;
	}

}
